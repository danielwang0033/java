package com.coin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.coin.entity.Activity;
import com.coin.entity.ActivityCheckIn;
import com.coin.entity.ActivityExample;
import com.coin.entity.ActivityPrize;
import com.coin.enums.*;
import com.coin.i18n.I18nUtil;
import com.coin.mapper.ActivityMapper;
import com.coin.req.ActivityReq;
import com.coin.resp.activity.*;
import com.coin.service.ActivityCheckInService;
import com.coin.service.ActivityPrizeService;
import com.coin.service.ActivityService;
import com.coin.service.UsersService;
import com.coin.service.constant.CodeCons;
import com.coin.service.exception.BizException;
import com.coin.service.util.ImageUtil;
import com.coin.service.util.LocalCache;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ActivityServiceImpl implements ActivityService {

    private static final Logger logger = LoggerFactory.getLogger(ActivityServiceImpl.class);

    @Resource
    private ActivityMapper activityMapper;
    @Resource
    private ActivityPrizeService activityPrizeService;
    @Resource
    private UsersService usersService;
    @Resource
    private ActivityCheckInService activityCheckInService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void add(ActivityReq req) {
        List<Activity> list = checkTitle(req);
        if (CollectionUtil.isNotEmpty(list)) {
            throw new BizException(CodeCons.ERROR, "活动名称已存在");
        }
        saveOrUpdate(req, false);
    }

    private List<Activity> checkTitle(ActivityReq req) {
        ActivityExample example = new ActivityExample();
        ActivityExample.Criteria criteria = example.createCriteria();
        criteria.andActivityTypeEqualTo(req.getActivityType());
        criteria.andActivityTitleEqualTo(req.getActivityTitle());
        criteria.andStatusNotEqualTo(ActivityStatusEnum.DELETED.getCode());
        return activityMapper.selectByExample(example);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(ActivityReq req) {
        saveOrUpdate(req, true);
    }

    private void saveOrUpdate(ActivityReq req, boolean isUpdate) {
        // 如果是发布, 则校验是否存在同类型的活动
        checkEnableTypeUnique(req, isUpdate);
        Integer priority = req.getPriority();
        if (ObjectUtil.isNotNull(priority)) {
            if (priority < 1 || priority > 100) {
                throw new BizException(CodeCons.ERROR, "排序值不符合要求");
            }
        }
        Date showTimeStart = req.getShowTimeStart();
        Date showTimeEnd = req.getShowTimeEnd();
        if (DateUtil.compare(showTimeStart, showTimeEnd) > 0) {
            throw new BizException(CodeCons.ERROR, "显示开始时间必须小于结束时间");
        }
        Date activityTimeStart = req.getActivityTimeStart();
        Date activityTimeEnd = req.getActivityTimeEnd();
        if (DateUtil.compare(activityTimeStart, activityTimeEnd) > 0) {
            throw new BizException(CodeCons.ERROR, "活动开始时间必须小于结束时间");
        }
        // 显示时间包含活动时间
        if (DateUtil.compare(activityTimeStart, showTimeStart) < 0) {
            throw new BizException(CodeCons.ERROR, "活动开始时间不能小于展示时间");
        }
        if (DateUtil.compare(activityTimeEnd, showTimeEnd) > 0) {
            throw new BizException(CodeCons.ERROR, "活动结束时间不能大于展示时间");
        }

        ActivityTypeEnum activityTypeEnum = ActivityTypeEnum.checkAndGet(req.getActivityType());
        ActivityStatusEnum activityStatusEnum = ActivityStatusEnum.checkAndGet(req.getStatus());
        Date now = new Date();
        Activity activity = new Activity();
        if (isUpdate) {
            Activity old = getById(req.getId());
            activity.setId(old.getId());
            activity.setUpdatedBy(req.getOptLoginName());
            activity.setUpdatedAt(now);
            // 校验活动名称
            List<Activity> list = checkTitle(req);
            if (CollectionUtil.isNotEmpty(list)) {
                if (list.size() == 1) {
                    if (!list.get(0).getId().equals(old.getId())) {
                        throw new BizException(CodeCons.ERROR, "活动名称已存在");
                    }
                } else {
                    throw new BizException(CodeCons.ERROR, "活动名称已存在");
                }
            }
        } else {
            activity.setCreatedBy(req.getOptLoginName());
            activity.setCreatedAt(now);
        }
        activity.setActivityTitle(req.getActivityTitle());
        activity.setActivityType(activityTypeEnum.getCode());
        activity.setPriority(priority);
        activity.setShowTimeStart(showTimeStart);
        activity.setShowTimeEnd(showTimeEnd);
        activity.setActivityTimeStart(activityTimeStart);
        activity.setActivityTimeEnd(activityTimeEnd);
        activity.setFloatButtonImage(req.getFloatButtonImage());
        activity.setFloatButtonImageH5(req.getFloatButtonImageH5());
        activity.setBackgroundImage(req.getBackgroundImage());
        activity.setBackgroundImageH5(req.getBackgroundImageH5());
        activity.setHeadImage(req.getHeadImage());
        activity.setHeadImageH5(req.getHeadImageH5());
        activity.setRuleImage(req.getRuleImage());
        activity.setRuleImageH5(req.getRuleImageH5());
        activity.setStatus(activityStatusEnum.getCode());
        int i;
        if (isUpdate) {
            i = activityMapper.updateByPrimaryKeySelective(activity);
        } else {
            i = activityMapper.insertSelective(activity);
        }
        if (i == 1) {
            List<ActivityPrizeVo> prizeList = req.getPrizeList();
            if (ActivityStatusEnum.ENABLE == activityStatusEnum) {
                switch (activityTypeEnum) {
                    case DRAW:
                        if (CollectionUtil.isEmpty(prizeList)) {
                            throw new BizException(CodeCons.ERROR, "抽奖活动未设置奖品");
                        }
                        BigDecimal total = BigDecimal.ZERO;
                        for (ActivityPrizeVo item : prizeList) {
                            item.setCheckInType(null);
                            item.setCheckInDays(null);
                            String prizeName = item.getPrizeName();
                            Integer prizeQuantity = item.getPrizeQuantity();
                            String prizeImage = item.getPrizeImage();
                            checkPrize(prizeName, prizeQuantity, prizeImage);
                            ActivityPrizeTypeEnum.checkAndGet(item.getPrizeType());

                            BigDecimal probability = item.getProbability();
                            if (probability.compareTo(BigDecimal.ZERO) < 0) {
                                throw new BizException(CodeCons.ERROR, "中奖概率不能小于零");
                            }
                            if (probability.compareTo(BigDecimal.ONE) > 0) {
                                throw new BizException(CodeCons.ERROR, "中奖概率不能大于百分之百");
                            }
                            total = total.add(probability);
                        }
                        if (total.compareTo(BigDecimal.ONE) != 0) {
                            throw new BizException(CodeCons.ERROR, "中奖总概率必须为百分之百");
                        }
                        break;
                    case CHECK_IN:
                        if (CollectionUtil.isEmpty(prizeList)) {
                            throw new BizException(CodeCons.ERROR, "签到活动未设置奖品");
                        }
                        Set<Integer> generalDays = new HashSet<>();
                        Set<Integer> serialDays = new HashSet<>();
                        long betweenDay = DateUtil.betweenDay(activityTimeStart, activityTimeEnd, true);
                        for (ActivityPrizeVo item : prizeList) {
                            item.setProbability(null);
                            String prizeName = item.getPrizeName();
                            Integer prizeQuantity = item.getPrizeQuantity();
                            checkPrize(prizeName, prizeQuantity, "-");
                            ActivityPrizeTypeEnum.checkAndGet(item.getPrizeType());

                            Integer checkInDays = item.getCheckInDays();
                            if (ObjectUtil.isNull(checkInDays)) {
                                throw new BizException(CodeCons.ERROR, "签到天数不能为空");
                            }
                            if (checkInDays <= 0) {
                                throw new BizException(CodeCons.ERROR, "签到天数不能小于零");
                            }
                            if (checkInDays > betweenDay) {
                                throw new BizException(CodeCons.ERROR, "签到天数不能大于活动天数");
                            }

                            ActivityCheckInTypeEnum checkInTypeEnum = ActivityCheckInTypeEnum.checkAndGet(item.getCheckInType());
                            if (ActivityCheckInTypeEnum.GENERAL == checkInTypeEnum) {
                                if (generalDays.contains(checkInDays)) {
                                    throw new BizException(CodeCons.ERROR, "存在重复天数");
                                }
                                generalDays.add(checkInDays);
                                // 计算讲评对应的日期
                                DateTime checkInDate = DateUtil.offsetDay(activityTimeStart, checkInDays - 1);
                                item.setCheckInDate(DateUtil.formatDate(checkInDate));
                            } else if (ActivityCheckInTypeEnum.SERIAL == checkInTypeEnum) {
                                if (serialDays.contains(checkInDays)) {
                                    throw new BizException(CodeCons.ERROR, "存在重复天数");
                                }
                                serialDays.add(checkInDays);
                            }
                        }
                        break;
                }
            }
            // 先清空活动奖品
            if (ObjectUtil.isNotNull(req.getId())) {
                activityPrizeService.deleteByActivityId(req.getId());
            }
            if (CollectionUtil.isNotEmpty(prizeList)) {
                activityPrizeService.addBatch(activity, prizeList);
            }
        } else {
            throw new BizException(CodeCons.ERROR, "活动数据更新失败");
        }
        // 清理缓存
        this.clearCache();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(ActivityReq req) {
        Long id = req.getId();
        Activity activity = getById(id);
        Date activityTimeStart = activity.getActivityTimeStart();
        Date now = new Date();
        if (DateUtil.compare(activityTimeStart, now) > 0) {
            throw new BizException(CodeCons.ERROR, "活动已经开始");
        }
        Activity update = new Activity();
        update.setId(activity.getId());
        update.setStatus(ActivityStatusEnum.DELETED.getCode());
        update.setUpdatedAt(now);
        update.setUpdatedBy(req.getOptLoginName());
        // 逻辑删除
        activityMapper.updateByPrimaryKeySelective(update);
        // 清理缓存
        this.clearCache();
    }

    @Override
    public Activity getById(Long id) {
        Activity activity = activityMapper.selectByPrimaryKey(id);
        if (ObjectUtil.isNull(activity)) {
            throw new BizException(CodeCons.ERROR, "记录不存在");
        }
        return activity;
    }

    @Override
    public ActivityVo getDetailById(Long id) {
        Activity activity = getById(id);
        ActivityVo activityVo = BeanUtil.copyProperties(activity, ActivityVo.class);
        if (ActivityTypeEnum.DRAW.getCode() == activity.getActivityType() || ActivityTypeEnum.CHECK_IN.getCode() == activityVo.getActivityType()) {
            List<ActivityPrize> prizeList = activityPrizeService.selectByActivity(activity.getId());
            List<ActivityPrizeVo> activityPrizeVos = BeanUtil.copyToList(prizeList, ActivityPrizeVo.class);
            if (CollectionUtil.isNotEmpty(activityPrizeVos)) {
                for (ActivityPrizeVo item : activityPrizeVos) {
                    item.setPrizeImage(ImageUtil.completeImageUrl(item.getPrizeImage()));
                }
            }
            activityVo.setPrizeList(activityPrizeVos);
        }
        return activityVo;
    }

    @Override
    public PageInfo<Activity> pageList(ActivityReq req) {
        ActivityExample example = new ActivityExample();
        ActivityExample.Criteria criteria = example.createCriteria();
        if (StrUtil.isNotBlank(req.getActivityTitle())) {
            criteria.andActivityTitleLike("%" + req.getActivityTitle() + "%");
        }
        if (ObjectUtil.isNotNull(req.getActivityType())) {
            criteria.andActivityTypeEqualTo(req.getActivityType());
        }
        if (ObjectUtil.isNotNull(req.getStatus())) {
            criteria.andStatusEqualTo(req.getStatus());
        }
        if (ObjectUtil.isNotNull(req.getShowTimeFrom()) && ObjectUtil.isNotNull(req.getShowTimeTo())) {
            criteria.andShowTimeStartGreaterThanOrEqualTo(req.getShowTimeFrom());
            criteria.andShowTimeStartLessThanOrEqualTo(req.getShowTimeTo());
            criteria.andShowTimeEndGreaterThanOrEqualTo(req.getShowTimeFrom());
            criteria.andShowTimeEndLessThanOrEqualTo(req.getShowTimeTo());
        }
        criteria.andStatusNotEqualTo(ActivityStatusEnum.DELETED.getCode());
        example.setOrderByClause(" status desc, priority asc");
        PageHelper.startPage(req.getPage(), req.getPagesize());
        List<Activity> datas = activityMapper.selectByExample(example);
        return new PageInfo<>(datas);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateActivityStatus(ActivityReq req) {
        Activity activity = getById(req.getId());
        ActivityStatusEnum activityStatusEnum = ActivityStatusEnum.checkAndGet(activity.getStatus());
        ActivityStatusEnum reqStatusEnum = ActivityStatusEnum.checkAndGet(req.getStatus());
        if (activityStatusEnum != reqStatusEnum) {
            if (ActivityStatusEnum.DISABLED == reqStatusEnum) {
                // 如果是下线活动, 则直接更新活动状态
                Activity update = new Activity();
                update.setId(activity.getId());
                update.setStatus(req.getStatus());
                update.setUpdatedAt(new Date());
                update.setUpdatedBy(req.getOptLoginName());
                activityMapper.updateByPrimaryKeySelective(update);
                // 清理缓存
                this.clearCache();
            } else if (ActivityStatusEnum.ENABLE == reqStatusEnum) {
                // 如果是开启活动, 则需要校验
                ActivityReq updateReq = BeanUtil.copyProperties(activity, ActivityReq.class);
                updateReq.setStatus(ActivityStatusEnum.ENABLE.getCode());
                List<ActivityPrize> prizeList = activityPrizeService.selectByActivity(activity.getId());
                if (CollectionUtil.isNotEmpty(prizeList)) {
                    List<ActivityPrizeVo> activityPrizeVos = BeanUtil.copyToList(prizeList, ActivityPrizeVo.class);
                    updateReq.setPrizeList(activityPrizeVos);
                }
                this.update(updateReq);
            }
        }
    }

    private void checkEnableTypeUnique(ActivityReq req, boolean isUpdate) {
        ActivityTypeEnum activityTypeEnum = ActivityTypeEnum.checkAndGet(req.getActivityType());
        ActivityStatusEnum activityStatusEnum = ActivityStatusEnum.checkAndGet(req.getStatus());
        if (ActivityStatusEnum.ENABLE == activityStatusEnum) {
            ActivityExample example = new ActivityExample();
            ActivityExample.Criteria criteria = example.createCriteria();
            criteria.andActivityTypeEqualTo(activityTypeEnum.getCode());
            criteria.andStatusEqualTo(ActivityStatusEnum.ENABLE.getCode());
            List<Activity> checkList = activityMapper.selectByExample(example);
            if (CollectionUtil.isNotEmpty(checkList)) {
                if (checkList.size() > 1) {
                    throw new BizException(CodeCons.ERROR, "已存在同类型启用活动");
                }
                Activity oldActivity = checkList.get(0);
                if (isUpdate) {
                    if (!NumberUtil.equals(oldActivity.getId(), req.getId())) {
                        throw new BizException(CodeCons.ERROR, "已存在同类型启用活动");
                    }
                } else {
                    throw new BizException(CodeCons.ERROR, "已存在同类型启用活动");
                }
            }
        }
    }

    private void checkPrize(String prizeName, Integer prizeQuantity, String prizeImage) {
        if (StrUtil.isBlank(prizeName)) {
            throw new BizException(CodeCons.ERROR, "奖品名称不能为空");
        }
        if (ObjectUtil.isNull(prizeQuantity)) {
            throw new BizException(CodeCons.ERROR, "奖品数量不能为空");
        }
        if (prizeQuantity <= 0) {
            throw new BizException(CodeCons.ERROR, "奖品数量必须为正数");
        }
        if (StrUtil.isBlank(prizeImage)) {
            throw new BizException(CodeCons.ERROR, "奖品图片不能为空");
        }
    }

    @Override
    public List<ActivityOngoingVo> ongoingActivities(ActivityReq req) {
        List<Activity> activityList = getEnableActivitiesWithCache();
        List<ActivityOngoingVo> result = new ArrayList<>();
        Date now = new Date();
        for (Activity item : activityList) {
            // 排除未到展示时间的活动
            if (DateUtil.compare(now, item.getShowTimeStart()) < 0
                    || DateUtil.compare(now, item.getShowTimeEnd()) > 0) {
                continue;
            }
            ActivityOngoingVo vo = new ActivityOngoingVo();
            vo.setId(item.getId());
            if (req.isH5Flag()) {
                vo.setFloatButtonImage(ImageUtil.completeImageUrl(item.getFloatButtonImageH5()));
            } else {
                vo.setFloatButtonImage(ImageUtil.completeImageUrl(item.getFloatButtonImage()));
            }
            ActivityTypeEnum activityTypeEnum = ActivityTypeEnum.checkAndGet(item.getActivityType());
            String path = "";
            switch (activityTypeEnum) {
                case INVITE:
                    path = "inviteFriends";
                    break;
                case DRAW:
                    path = "lottery";
                    break;
                case CHECK_IN:
                    path = "sign";
                    break;
            }
            vo.setPath(path);
            result.add(vo);
        }
        return result;
    }

    private List<Activity> getEnableActivitiesWithCache() {
        List<Activity> cache = LocalCache.getCache(LocalCache.ACTIVITY_CACHE);
        if (cache == null) {
            ActivityExample example = new ActivityExample();
            ActivityExample.Criteria criteria = example.createCriteria();
            criteria.andStatusEqualTo(ActivityStatusEnum.ENABLE.getCode());
            example.setOrderByClause(" priority asc");
            List<Activity> activityList = activityMapper.selectByExample(example);
            LocalCache.putCache(LocalCache.ACTIVITY_CACHE, activityList);
            return activityList;
        }
        return cache;
    }

    @Override
    public ActivityDetailVo showDetail(ActivityReq req) {
        Activity activity = verifyActivity(req.getId());
        ActivityTypeEnum activityTypeEnum = ActivityTypeEnum.checkAndGet(activity.getActivityType());
        ActivityDetailVo detailVo = new ActivityDetailVo();
        detailVo.setId(activity.getId());
        detailVo.setActivityTitle(activity.getActivityTitle());
        detailVo.setActivityType(activity.getActivityType());
        detailVo.setPriority(activity.getPriority());
        detailVo.setShowTimeStart(activity.getShowTimeStart());
        detailVo.setShowTimeEnd(activity.getShowTimeEnd());
        detailVo.setActivityTimeStart(activity.getActivityTimeStart());
        detailVo.setActivityTimeEnd(activity.getActivityTimeEnd());
        if (req.isH5Flag()) {
            detailVo.setBackgroundImage(ImageUtil.completeImageUrl(activity.getBackgroundImageH5()));
            detailVo.setHeadImage(ImageUtil.completeImageUrl(activity.getHeadImageH5()));
            detailVo.setRuleImage(ImageUtil.completeImageUrl(activity.getRuleImageH5()));
        } else {
            detailVo.setBackgroundImage(ImageUtil.completeImageUrl(activity.getBackgroundImage()));
            detailVo.setHeadImage(ImageUtil.completeImageUrl(activity.getHeadImage()));
            detailVo.setRuleImage(ImageUtil.completeImageUrl(activity.getRuleImage()));
        }
        switch (activityTypeEnum) {
            case INVITE:
                // 获取登录用户邀请码
                processInviteActivity(detailVo, req.getLoginUserId());
                break;
            case DRAW:
                // 查询活动奖品
                processDrawActivity(detailVo, req.getLoginUserId());
                break;
            case CHECK_IN:
                // 计算当前周签到和连续签到天数
                processCheckInActivity(detailVo, req.getLoginUserId());
                break;
        }
        return detailVo;
    }

    private void processInviteActivity(ActivityDetailVo detailVo, Long loginUserId) {
        if (ObjectUtil.isNotNull(loginUserId)) {
            String inviteCode = usersService.userInviteCode(loginUserId);
            detailVo.setInviteCode(inviteCode);
        }
    }

    private void processDrawActivity(ActivityDetailVo detailVo, Long loginUserId) {
        List<ActivityPrize> prizeList = activityPrizeService.selectByActivity(detailVo.getId());
        List<ActivityDetailPrizeVo> prizeVoList = new ArrayList<>();
        for (ActivityPrize item : prizeList) {
            ActivityDetailPrizeVo vo = new ActivityDetailPrizeVo();
            vo.setId(item.getId());
            vo.setPrizeName(item.getPrizeName());
            vo.setPrizeType(item.getPrizeType());
            vo.setPrizeQuantity(item.getPrizeQuantity());
            vo.setPrizeImage(ImageUtil.completeImageUrl(item.getPrizeImage()));
            prizeVoList.add(vo);
        }
        detailVo.setPrizeList(prizeVoList);

        // 用户抽奖次数
        if (ObjectUtil.isNotNull(loginUserId)) {
            Integer drawNumber = usersService.userDrawNumber(loginUserId);
            detailVo.setDrawNumber(drawNumber);
        }
    }

    private void processCheckInActivity(ActivityDetailVo detailVo, Long loginUserId) {
        // 查询当前用户当前活动签到记录
        Date now = new Date();
        Map<String, ActivityCheckIn> checkInMap = new HashMap<>();
        if (ObjectUtil.isNotNull(loginUserId)) {
            Long activityId = detailVo.getId();
            List<ActivityCheckIn> checkInList = activityCheckInService.findUserCheckInRecord(activityId, loginUserId);
            if (CollectionUtil.isNotEmpty(checkInList)) {
                // 将签到记录转化未 2024-01-01 -> 记录 的形式
                for (ActivityCheckIn item : checkInList) {
                    String dateStr = DateUtil.formatDate(item.getCreatedAt());
                    checkInMap.put(dateStr, item);
                }
                // 计算连续签到天数
                countSerialDays(now, checkInMap, detailVo);
            }
        }
        // 统计本周签到
        countThisWeekCheckIn(now, checkInMap, detailVo);
    }

    private void countThisWeekCheckIn(Date now, Map<String, ActivityCheckIn> checkInMap, ActivityDetailVo detailVo) {
        String todayStr = DateUtil.formatDate(now);
        now = DateUtil.beginOfDay(now);
        DateTime currentDate = DateUtil.beginOfDay(DateUtil.beginOfWeek(now));
        List<CheckInRecordVo> checkInRecordVoList = new ArrayList<>();
        String[] weekArr;
        if (I18nUtil.LANGUAGE.equals("zh")) {
            weekArr = new String[]{"周一", "周二", "周三", "周四", "周五", "周六", "周日"};
        } else if (I18nUtil.LANGUAGE.equals("vi")) {
            weekArr = new String[]{"THỨ 2", "THỨ 3", "THỨ 4", "THỨ 5", "THỨ 6", "THỨ 7", "CN"};
        } else {
            weekArr = new String[]{"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        }
        List<String> noCheckInDateList = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            String dateStr = DateUtil.formatDate(currentDate);
            CheckInRecordVo checkInRecordVo = new CheckInRecordVo();
            String format;
            if (I18nUtil.LANGUAGE.equals("zh")) {
                format = "MM月dd日";
            } else {
                format = "MM/dd";
            }
            String dayOfMonth = DateUtil.format(currentDate, format);
            if (dateStr.equals(todayStr)) {
                dayOfMonth = I18nUtil.translateBiz("今天");
                checkInRecordVo.setToday(true);
            }
            checkInRecordVo.setDateStr(dateStr);
            checkInRecordVo.setDayOfWeek(weekArr[i]);
            checkInRecordVo.setDayOfMonth(dayOfMonth);
            if (ObjectUtil.isNotNull(checkInMap.get(dateStr))) {
                checkInRecordVo.setSignStatus(SignStatusEnum.SIGNED.getMessage());
                checkInRecordVo.setSignStatusType(SignStatusEnum.SIGNED.getCode());
            } else {
                if (DateUtil.compare(currentDate, now) >= 0) {
                    checkInRecordVo.setSignStatus(SignStatusEnum.NOT_SIGNED.getMessage());
                    checkInRecordVo.setSignStatusType(SignStatusEnum.NOT_SIGNED.getCode());
                    // 统计未签到日期
                    noCheckInDateList.add(dateStr);
                } else {
                    checkInRecordVo.setSignStatus(SignStatusEnum.EXPIRED_NOT_SIGNED.getMessage());
                    checkInRecordVo.setSignStatusType(SignStatusEnum.EXPIRED_NOT_SIGNED.getCode());
                }
            }
            checkInRecordVoList.add(checkInRecordVo);
            currentDate = DateUtil.offsetDay(currentDate, 1);
        }
        // 封装未签到日期奖品
        if (CollectionUtil.isNotEmpty(noCheckInDateList)) {
            List<ActivityPrize> prizeList = activityPrizeService.selectByActivityAndDateList(detailVo.getId(), detailVo.getActivityType(), noCheckInDateList);
            if (CollectionUtil.isNotEmpty(prizeList)) {
                Map<String, ActivityPrize> datePrizeMap = prizeList.stream().collect(Collectors.toMap(ActivityPrize::getCheckInDate, Function.identity()));
                for (CheckInRecordVo item : checkInRecordVoList) {
                    String dateStr = item.getDateStr();
                    ActivityPrize activityPrize = datePrizeMap.get(dateStr);
                    if (ObjectUtil.isNotNull(activityPrize)) {
                        ActivityDetailPrizeVo detailPrizeVo = new ActivityDetailPrizeVo();
                        detailPrizeVo.setId(activityPrize.getId());
                        detailPrizeVo.setPrizeName(activityPrize.getPrizeName());
                        detailPrizeVo.setPrizeType(activityPrize.getPrizeType());
                        detailPrizeVo.setPrizeQuantity(activityPrize.getPrizeQuantity());
                        detailPrizeVo.setPrizeImage(activityPrize.getPrizeImage());
                        item.setPrize(detailPrizeVo);
                    }
                }
            }
        }
        detailVo.setCheckInList(checkInRecordVoList);
    }

    @Override
    public Activity verifyActivity(Long activityId) {
        List<Activity> activityList = getEnableActivitiesWithCache();
        if (CollectionUtil.isEmpty(activityList)) {
            throw new BizException(CodeCons.ERROR, "活动不存在");
        }
        Activity activity = null;
        for (Activity item : activityList) {
            if (item.getId().equals(activityId)) {
                activity = item;
                break;
            }
        }
        if (ObjectUtil.isNull(activity)) {
            throw new BizException(CodeCons.ERROR, "活动不存在");
        }

        Integer status = activity.getStatus();
        if (ActivityStatusEnum.ENABLE.getCode() != status) {
            throw new BizException(CodeCons.ERROR, "活动未开启");
        }
        Date now = new Date();
        if (DateUtil.compare(now, activity.getShowTimeStart()) < 0) {
            throw new BizException(CodeCons.ERROR, "活动未到展示时间");
        }
        if (DateUtil.compare(now, activity.getShowTimeEnd()) > 0) {
            throw new BizException(CodeCons.ERROR, "活动已过展示时间");
        }
        return activity;
    }

    private void countSerialDays(Date now, Map<String, ActivityCheckIn> checkInMap, ActivityDetailVo detailVo) {
        Date activityTimeStart = DateUtil.beginOfDay(detailVo.getActivityTimeStart());
        Date activityTimeEnd = DateUtil.beginOfDay(detailVo.getActivityTimeEnd());
        Integer serialDays = activityCheckInService.calcSerialDays(now, checkInMap, activityTimeStart, activityTimeEnd);
        detailVo.setSerialDays(serialDays);
    }

    // 清除缓存
    private void clearCache() {
        LocalCache.deleteCache(LocalCache.ACTIVITY_CACHE);
    }
}
