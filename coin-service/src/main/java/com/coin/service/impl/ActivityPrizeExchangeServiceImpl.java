package com.coin.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import com.coin.entity.Activity;
import com.coin.entity.ActivityPrize;
import com.coin.entity.ActivityPrizeExchange;
import com.coin.entity.ActivityPrizeExchangeExample;
import com.coin.enums.*;
import com.coin.i18n.I18nUtil;
import com.coin.mapper.ActivityPrizeExchangeMapper;
import com.coin.req.ActivityPrizeExchangeReq;
import com.coin.req.UsersReq;
import com.coin.resp.activity.ClickDrawVo;
import com.coin.resp.activity.WinningRecordVo;
import com.coin.service.ActivityPrizeExchangeService;
import com.coin.service.ActivityPrizeService;
import com.coin.service.ActivityService;
import com.coin.service.UsersService;
import com.coin.service.asyn.BobiAndExpService;
import com.coin.service.constant.CodeCons;
import com.coin.service.exception.BizException;
import com.coin.service.util.PageUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ActivityPrizeExchangeServiceImpl implements ActivityPrizeExchangeService {

    private static final Logger logger = LoggerFactory.getLogger(ActivityPrizeExchangeServiceImpl.class);

    @Resource
    private ActivityPrizeExchangeMapper activityPrizeExchangeMapper;
    @Resource
    private ActivityService activityService;
    @Resource
    private UsersService usersService;
    @Resource
    private ActivityPrizeService activityPrizeService;
    @Resource
    private BobiAndExpService bobiAndExpService;

    @Override
    public PageInfo<WinningRecordVo> winningRecord(ActivityPrizeExchangeReq req) {
        ActivityPrizeExchangeExample example = new ActivityPrizeExchangeExample();
        ActivityPrizeExchangeExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(req.getLoginUserId());
        criteria.andActivityIdEqualTo(req.getActivityId());
        example.setOrderByClause(" created_at desc");

        PageHelper.startPage(req.getPage(), req.getPagesize());
        List<ActivityPrizeExchange> datas = activityPrizeExchangeMapper.selectByExample(example);
        PageInfo<ActivityPrizeExchange> page = new PageInfo<>(datas);
        List<WinningRecordVo> rspList = datas.stream().map(this::convertRsp).collect(Collectors.toList());
        return PageUtil.pageInfo2PageRsp(page, rspList);
    }

    private WinningRecordVo convertRsp(ActivityPrizeExchange exchange) {
        WinningRecordVo vo = new WinningRecordVo();
        vo.setId(exchange.getId());
        vo.setPrizeName(exchange.getPrizeName());
        vo.setCreatedAt(exchange.getCreatedAt());
        return vo;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ClickDrawVo clickDraw(ActivityPrizeExchangeReq req) {
        Activity activity = activityService.verifyActivity(req.getActivityId());
        Date now = new Date();
        if (DateUtil.compare(now, activity.getActivityTimeStart()) < 0) {
            throw new BizException(CodeCons.ERROR, "活动未开始");
        }
        if (DateUtil.compare(now, activity.getActivityTimeEnd()) > 0) {
            throw new BizException(CodeCons.ERROR, "活动已过期");
        }
        ActivityTypeEnum activityTypeEnum = ActivityTypeEnum.checkAndGet(activity.getActivityType());
        if (ActivityTypeEnum.DRAW != activityTypeEnum) {
            throw new BizException(CodeCons.ERROR, "活动类型异常");
        }
        // 校验用户抽奖次数
        Integer drawNumber = usersService.userDrawNumber(req.getLoginUserId());
        if (ObjectUtil.isNull(drawNumber) || drawNumber <= 0) {
            throw new BizException(CodeCons.ERROR, "抽奖次数已用完");
        }
        ClickDrawVo result = new ClickDrawVo();
        // 获取活动奖品
        List<ActivityPrize> prizeList = activityPrizeService.selectByActivity(activity.getId());
        if (CollectionUtil.isEmpty(prizeList)) {
            return result;
        }
        // 过滤概率大于0的数据
        List<ActivityPrize> drawList = new ArrayList<>();
        for (ActivityPrize item : prizeList) {
            if (ActivityTypeEnum.checkAndGet(item.getActivityType()) == ActivityTypeEnum.DRAW
                    && ObjectUtil.isNotNull(item.getProbability())
                    && item.getProbability().compareTo(BigDecimal.ZERO) > 0
                    && item.getProbability().compareTo(BigDecimal.ONE) <= 0) {
                drawList.add(item);
            }
        }
        if (CollectionUtil.isEmpty(drawList)) {
            return result;
        }
        // 随机打乱奖品集合
        Collections.shuffle(drawList);
        int from = 1;
        int to = 0;
        BigDecimal bg10000 = new BigDecimal(10000);
        int randomInt = RandomUtil.randomInt(1, 10000);
        for (ActivityPrize item : drawList) {
            BigDecimal probability = item.getProbability();
            BigDecimal multiply = probability.multiply(bg10000);
            to = to + multiply.intValue();
            // 校验是否抽中
            if (randomInt >= from && randomInt <= to) {
                result.setPrizeId(item.getId());
                ActivityPrizeTypeEnum prizeTypeEnum = ActivityPrizeTypeEnum.checkAndGet(item.getPrizeType());
                result.setPrizeType(prizeTypeEnum.getCode());
                if (ActivityPrizeTypeEnum.DID_NOT_WIN == prizeTypeEnum) {
                    result.setTipMessage(I18nUtil.translateBiz("您暂未中奖,再接再厉哦～"));
                    processDrawDidNotWin(req, item, activity, now);
                } else {
                    if (I18nUtil.LANGUAGE.equals("zh")) {
                        result.setTipMessage("恭喜您获得" + item.getPrizeName() + "!!!");
                    } else {
                        result.setTipMessage(I18nUtil.translateBiz("恭喜您获得") + " " + item.getPrizeName() + "!!!");
                    }
                    processDrawWinning(req, item, activity);
                }
                break;
            }
            from = to + 1;
        }
        return result;
    }

    private void processDrawDidNotWin(ActivityPrizeExchangeReq req, ActivityPrize item, Activity activity, Date now) {
        // 扣减用户抽奖次数
        usersService.deductionDrawNumber(req.getLoginUserId());
        // 记录未中奖记录
        ActivityPrizeExchange activityPrizeExchange = new ActivityPrizeExchange();
        activityPrizeExchange.setActivityId(activity.getId());
        activityPrizeExchange.setActivityType(activity.getActivityType());
        activityPrizeExchange.setUserId(req.getLoginUserId());
        activityPrizeExchange.setPrizeType(ActivityPrizeTypeEnum.DID_NOT_WIN.getCode());
        activityPrizeExchange.setPrizeName(item.getPrizeName());
        activityPrizeExchange.setPrizeQuantity(item.getPrizeQuantity());
        activityPrizeExchange.setPrizeImage(item.getPrizeImage());
        activityPrizeExchange.setStatus(PrizeExchangeStatusEnum.DID_NOT_WIN.getCode());
        activityPrizeExchange.setRemark(I18nUtil.translateBiz("未中奖"));
        activityPrizeExchange.setCreatedBy(req.getOptLoginName());
        activityPrizeExchange.setCreatedAt(now);
        activityPrizeExchangeMapper.insertSelective(activityPrizeExchange);
    }

    private void processDrawWinning(ActivityPrizeExchangeReq req, ActivityPrize item, Activity activity) {
        // 扣减用户抽奖次数
        usersService.deductionDrawNumber(req.getLoginUserId());
        // 记录获奖记录
        this.saveExchangeLog(req.getLoginUserId(), req.getOptLoginName(), activity, item, I18nUtil.translateBiz("转盘抽奖"));
    }

    @Override
    public ActivityPrizeExchange saveExchangeLog(Long userId, String userName, Activity activity, ActivityPrize item, String remark) {
        // 记录抽奖记录
        ActivityPrizeExchange exchange = new ActivityPrizeExchange();
        exchange.setActivityId(activity.getId());
        exchange.setActivityType(activity.getActivityType());
        exchange.setUserId(userId);
        exchange.setPrizeType(item.getPrizeType());
        exchange.setPrizeName(item.getPrizeName());
        exchange.setPrizeQuantity(item.getPrizeQuantity());
        exchange.setPrizeImage(item.getPrizeImage());
        exchange.setStatus(PrizeExchangeStatusEnum.EXCHANGED.getCode());
        exchange.setRemark(remark);
        exchange.setCreatedBy(userName);
        exchange.setCreatedAt(new Date());
        activityPrizeExchangeMapper.insertSelective(exchange);

        // 即时兑换奖品
        ActivityPrizeTypeEnum prizeTypeEnum = ActivityPrizeTypeEnum.checkAndGet(exchange.getPrizeType());
        UsersReq usersReq = new UsersReq();
        usersReq.setActivity(activity);
        usersReq.setId(exchange.getUserId());
        usersReq.setOptLoginName(userName);
        switch (prizeTypeEnum) {
            case BOBI:
                bobiAndExpService.activityAddBobi(exchange.getUserId(), exchange.getPrizeQuantity(),
                        remark + I18nUtil.translateBiz("获得博币"));
                break;
            case EXP:
                bobiAndExpService.activityAddExp(exchange.getUserId(), exchange.getPrizeQuantity(),
                        remark + I18nUtil.translateBiz("获得经验"));
                break;
            case DRAW_NUMBER:
                usersReq.setAddDrawNumber(exchange.getPrizeQuantity());
                usersReq.setRemark(remark);
                usersService.addDrawNumber(usersReq, DrawNumChangeTypeEnum.ACTIVITY_GIVE);
                break;
            case CHANGE_NAME_CARD:
                usersReq.setNameCardNumber(exchange.getPrizeQuantity());
                usersReq.setRemark(remark);
                usersService.addNameCard(usersReq, NameCardChangeTypeEnum.ACTIVITY_GIVE);
                break;
            case PHYSICAL_PRIZE:
                break;
        }
        return exchange;
    }

    @Override
    public List<ActivityPrizeExchange> findByIdList(List<Long> exchangeIdList, Long userId) {
        ActivityPrizeExchangeExample example = new ActivityPrizeExchangeExample();
        ActivityPrizeExchangeExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userId);
        criteria.andIdIn(exchangeIdList);
        return activityPrizeExchangeMapper.selectByExample(example);
    }
}
