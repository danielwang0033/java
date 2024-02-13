package com.coin.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.coin.entity.*;
import com.coin.enums.ActivityCheckInTypeEnum;
import com.coin.enums.ActivityTypeEnum;
import com.coin.i18n.I18nUtil;
import com.coin.mapper.ActivityCheckInMapper;
import com.coin.req.ActivityCheckInReq;
import com.coin.resp.activity.CheckInHistoryVo;
import com.coin.resp.activity.ClickCheckInVo;
import com.coin.service.ActivityCheckInService;
import com.coin.service.ActivityPrizeExchangeService;
import com.coin.service.ActivityPrizeService;
import com.coin.service.ActivityService;
import com.coin.service.constant.CodeCons;
import com.coin.service.exception.BizException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ActivityCheckInServiceImpl implements ActivityCheckInService {

    private static final Logger logger = LoggerFactory.getLogger(ActivityCheckInServiceImpl.class);

    @Resource
    private ActivityCheckInMapper activityCheckInMapper;
    @Resource
    private ActivityService activityService;
    @Resource
    private ActivityPrizeService activityPrizeService;
    @Resource
    private ActivityPrizeExchangeService activityPrizeExchangeService;

    @Override
    public List<ActivityCheckIn> findUserCheckInRecord(Long activityId, Long loginUserId) {
        ActivityCheckInExample example = new ActivityCheckInExample();
        ActivityCheckInExample.Criteria criteria = example.createCriteria();
        criteria.andActivityIdEqualTo(activityId);
        criteria.andUserIdEqualTo(loginUserId);
        return activityCheckInMapper.selectByExample(example);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ClickCheckInVo clickCheckIn(ActivityCheckInReq req) {
        Activity activity = activityService.verifyActivity(req.getActivityId());
        Date now = new Date();
        if (DateUtil.compare(now, activity.getActivityTimeStart()) < 0) {
            throw new BizException(CodeCons.ERROR, "活动未开始");
        }
        if (DateUtil.compare(now, activity.getActivityTimeEnd()) > 0) {
            throw new BizException(CodeCons.ERROR, "活动已过期");
        }
        ActivityTypeEnum activityTypeEnum = ActivityTypeEnum.checkAndGet(activity.getActivityType());
        if (ActivityTypeEnum.CHECK_IN != activityTypeEnum) {
            throw new BizException(CodeCons.ERROR, "活动类型异常");
        }
        // 校验今日是否已经签到
        ActivityCheckInExample example = new ActivityCheckInExample();
        ActivityCheckInExample.Criteria criteria = example.createCriteria();
        criteria.andActivityIdEqualTo(activity.getId());
        criteria.andUserIdEqualTo(req.getLoginUserId());
        criteria.andCreatedAtGreaterThanOrEqualTo(DateUtil.beginOfDay(now));
        criteria.andCreatedAtLessThanOrEqualTo(DateUtil.endOfDay(now));
        List<ActivityCheckIn> checkInList = activityCheckInMapper.selectByExample(example);
        if (CollectionUtil.isNotEmpty(checkInList)) {
            throw new BizException(CodeCons.ERROR, "今日已签到");
        }
        // 记录签到日志
        ActivityCheckIn activityCheckIn = new ActivityCheckIn();
        // 普通签到送奖品
        ActivityPrize generalCheckInPrize = activityPrizeService.selectByActivityAndDate(activity, DateUtil.formatDate(now));
        if (ObjectUtil.isNotNull(generalCheckInPrize)) {
            ActivityPrizeExchange exchange = activityPrizeExchangeService.saveExchangeLog(req.getLoginUserId(),
                    req.getOptLoginName(), activity, generalCheckInPrize, I18nUtil.translateBiz("普通签到"));
            activityCheckIn.setDayPrizeExchangeId(exchange.getId());
        }
        // 连续签到送奖品,先计算连续签到天数
        Integer serialDayCount = countSerialDays(now, req.getLoginUserId(), activity);
        if (ObjectUtil.isNull(serialDayCount)){
            serialDayCount = 1;
        }else {
            // 默认在原有连续签到基础上加1天
            serialDayCount = serialDayCount + 1;
        }
        if (ObjectUtil.isNotNull(serialDayCount) && serialDayCount > 0) {
            ActivityPrize serialCheckInPrize = activityPrizeService.selectByActivityAndSerialCount(activity, serialDayCount);
            if (ObjectUtil.isNotNull(serialCheckInPrize)) {
                ActivityPrizeExchange exchange = activityPrizeExchangeService.saveExchangeLog(req.getLoginUserId(),
                        req.getOptLoginName(), activity, serialCheckInPrize, I18nUtil.translateBiz("连续签到") + serialDayCount + I18nUtil.translateBiz("天"));
                activityCheckIn.setSerialPrizeExchangeId(exchange.getId());
                activityCheckIn.setSerialDays(serialDayCount);
            }
        }
        activityCheckIn.setActivityId(activity.getId());
        activityCheckIn.setUserId(req.getLoginUserId());
        activityCheckIn.setCreatedBy(req.getOptLoginName());
        activityCheckIn.setCreatedAt(now);
        activityCheckInMapper.insertSelective(activityCheckIn);
        ClickCheckInVo result = new ClickCheckInVo();
        result.setTipMessage(I18nUtil.translateBiz("签到成功"));
        return result;
    }

    @Override
    public List<CheckInHistoryVo> checkInHistory(ActivityCheckInReq req) {
        List<CheckInHistoryVo> result = new ArrayList<>();
        List<ActivityCheckIn> userCheckInRecord = this.findUserCheckInRecord(req.getActivityId(), req.getLoginUserId());
        if (CollectionUtil.isEmpty(userCheckInRecord)) {
            return result;
        }
        List<Long> exchangeIdList = new ArrayList<>();
        for (ActivityCheckIn item : userCheckInRecord) {
            if (ObjectUtil.isNotEmpty(item.getDayPrizeExchangeId())) {
                exchangeIdList.add(item.getDayPrizeExchangeId());
            }
            if (ObjectUtil.isNotEmpty(item.getSerialPrizeExchangeId())) {
                exchangeIdList.add(item.getSerialPrizeExchangeId());
            }
        }
        Map<Long, ActivityPrizeExchange> exchangeMap = null;
        if (CollectionUtil.isNotEmpty(exchangeIdList)) {
            // 查询所有签到奖品
            List<ActivityPrizeExchange> exchangeList = activityPrizeExchangeService.findByIdList(exchangeIdList, req.getLoginUserId());
            exchangeMap = exchangeList.stream().collect(Collectors.toMap(ActivityPrizeExchange::getId, Function.identity()));
        }
        // 封装响应结果
        for (ActivityCheckIn item : userCheckInRecord) {
            Date createdAt = item.getCreatedAt();
            String date = DateUtil.formatDate(createdAt);
            String time = DateUtil.format(createdAt, DatePattern.NORM_DATETIME_PATTERN);
            // 处理普通签到
            CheckInHistoryVo generalVo = new CheckInHistoryVo();
            generalVo.setDate(date);
            generalVo.setTime(time);
            generalVo.setCheckInType(ActivityCheckInTypeEnum.GENERAL.getCode());
            if (ObjectUtil.isNotEmpty(item.getDayPrizeExchangeId()) && CollectionUtil.isNotEmpty(exchangeMap)) {
                ActivityPrizeExchange exchange = exchangeMap.get(item.getDayPrizeExchangeId());
                if (ObjectUtil.isNotNull(exchange)) {
                    generalVo.setPrizeAmount(exchange.getPrizeQuantity());
                    generalVo.setPrizeType(exchange.getPrizeType());
                }
            }
            result.add(generalVo);

            // 处理连续签到
            if (ObjectUtil.isNotEmpty(item.getSerialPrizeExchangeId()) && CollectionUtil.isNotEmpty(exchangeMap)) {
                ActivityPrizeExchange exchange = exchangeMap.get(item.getSerialPrizeExchangeId());
                if (ObjectUtil.isNotNull(exchange)) {
                    CheckInHistoryVo serialVo = new CheckInHistoryVo();
                    serialVo.setCheckInType(ActivityCheckInTypeEnum.SERIAL.getCode());
                    serialVo.setDate(date);
                    serialVo.setTime(time);
                    serialVo.setPrizeAmount(exchange.getPrizeQuantity());
                    serialVo.setPrizeType(exchange.getPrizeType());
                    serialVo.setSerialDays(item.getSerialDays());
                    result.add(serialVo);
                }
            }
        }
        return result;
    }

    @Override
    public Integer calcSerialDays(Date now, Map<String, ActivityCheckIn> checkInMap, Date activityTimeStart, Date activityTimeEnd) {
        Date currentDate;
        if (DateUtil.compare(DateUtil.beginOfDay(now), activityTimeEnd) > 0) {
            // 如果当前时间比活动截止时间还大, 则从活动截止时间开始计算
            currentDate = activityTimeEnd;
        } else {
            currentDate = now;
        }
        int count = 0;
        int activityMaxDays = 120;
        for (int i = 0; i < activityMaxDays; i++) {
            if (DateUtil.compare(currentDate, activityTimeStart) < 0) {
                break;
            }
            String dateStr = DateUtil.formatDate(currentDate);
            ActivityCheckIn activityCheckIn = checkInMap.get(dateStr);
            if (ObjectUtil.isNotNull(activityCheckIn)) {
                count = count + 1;
                currentDate = DateUtil.offsetDay(currentDate, -1);
            } else {
                if (dateStr.equals(DateUtil.formatDate(now))) {
                    currentDate = DateUtil.offsetDay(currentDate, -1);
                } else {
                    break;
                }
            }
        }
        return count;
    }

    private Integer countSerialDays(Date now, Long loginUserId, Activity detailVo) {
        Map<String, ActivityCheckIn> checkInMap = new HashMap<>();
        Long activityId = detailVo.getId();
        List<ActivityCheckIn> checkInList = this.findUserCheckInRecord(activityId, loginUserId);
        if (CollectionUtil.isNotEmpty(checkInList)) {
            // 将签到记录转化未 2024-01-01 -> 记录 的形式
            for (ActivityCheckIn item : checkInList) {
                String dateStr = DateUtil.formatDate(item.getCreatedAt());
                checkInMap.put(dateStr, item);
            }
            Date activityTimeStart = DateUtil.beginOfDay(detailVo.getActivityTimeStart());
            Date activityTimeEnd = DateUtil.beginOfDay(detailVo.getActivityTimeEnd());
            return this.calcSerialDays(now, checkInMap, activityTimeStart, activityTimeEnd);
        }
        return null;
    }
}
