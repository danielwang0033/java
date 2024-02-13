package com.coin.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.coin.entity.Activity;
import com.coin.entity.ActivityPrize;
import com.coin.entity.ActivityPrizeExample;
import com.coin.enums.ActivityCheckInTypeEnum;
import com.coin.enums.ActivityTypeEnum;
import com.coin.mapper.ActivityPrizeMapper;
import com.coin.resp.activity.ActivityPrizeVo;
import com.coin.service.ActivityPrizeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class ActivityPrizeServiceImpl implements ActivityPrizeService {

    private static final Logger logger = LoggerFactory.getLogger(ActivityPrizeServiceImpl.class);

    @Resource
    private ActivityPrizeMapper activityPrizeMapper;

    @Override
    public void addBatch(Activity activity, List<ActivityPrizeVo> prizeList) {
        Long activityId = activity.getId();
        Integer activityType = activity.getActivityType();
        Date now = new Date();
        for (ActivityPrizeVo item : prizeList) {
            ActivityPrize activityPrize = new ActivityPrize();
            activityPrize.setActivityId(activityId);
            activityPrize.setActivityType(activityType);
            activityPrize.setPrizeType(item.getPrizeType());
            activityPrize.setPrizeName(item.getPrizeName());
            activityPrize.setPrizeQuantity(item.getPrizeQuantity());
            activityPrize.setPrizeImage(item.getPrizeImage());
            activityPrize.setProbability(item.getProbability());
            activityPrize.setCheckInType(item.getCheckInType());
            activityPrize.setCheckInDays(item.getCheckInDays());
            activityPrize.setCheckInDate(item.getCheckInDate());
            if (StrUtil.isNotBlank(activity.getUpdatedBy())) {
                activityPrize.setCreatedBy(activity.getUpdatedBy());
            } else if (StrUtil.isNotBlank(activity.getCreatedBy())) {
                activityPrize.setCreatedBy(activity.getCreatedBy());
            }
            activityPrize.setCreatedAt(now);
            activityPrizeMapper.insertSelective(activityPrize);
        }
    }

    @Override
    public void deleteByActivityId(Long activityId) {
        ActivityPrizeExample example = new ActivityPrizeExample();
        ActivityPrizeExample.Criteria criteria = example.createCriteria();
        criteria.andActivityIdEqualTo(activityId);
        activityPrizeMapper.deleteByExample(example);
    }

    @Override
    public List<ActivityPrize> selectByActivity(Long activityId) {
        ActivityPrizeExample example = new ActivityPrizeExample();
        ActivityPrizeExample.Criteria criteria = example.createCriteria();
        criteria.andActivityIdEqualTo(activityId);
        return activityPrizeMapper.selectByExample(example);
    }

    @Override
    public ActivityPrize selectByActivityAndDate(Activity activity, String dateStr) {
        ActivityPrizeExample example = new ActivityPrizeExample();
        ActivityPrizeExample.Criteria criteria = example.createCriteria();
        criteria.andActivityIdEqualTo(activity.getId());
        criteria.andActivityTypeEqualTo(activity.getActivityType());
        criteria.andCheckInDateEqualTo(dateStr);
        criteria.andCheckInTypeEqualTo(ActivityCheckInTypeEnum.GENERAL.getCode());
        List<ActivityPrize> prizeList = activityPrizeMapper.selectByExample(example);
        if (CollectionUtil.isEmpty(prizeList)) {
            return null;
        }
        return prizeList.get(0);
    }

    @Override
    public List<ActivityPrize> selectByActivityAndDateList(Long activityId, Integer activityType, List<String> dateStrList) {
        ActivityPrizeExample example = new ActivityPrizeExample();
        ActivityPrizeExample.Criteria criteria = example.createCriteria();
        criteria.andActivityIdEqualTo(activityId);
        criteria.andActivityTypeEqualTo(activityType);
        criteria.andCheckInDateIn(dateStrList);
        criteria.andCheckInTypeEqualTo(ActivityCheckInTypeEnum.GENERAL.getCode());
        return activityPrizeMapper.selectByExample(example);
    }

    @Override
    public ActivityPrize selectByActivityAndSerialCount(Activity activity, int serialDayCount) {
        ActivityPrizeExample example = new ActivityPrizeExample();
        ActivityPrizeExample.Criteria criteria = example.createCriteria();
        criteria.andActivityIdEqualTo(activity.getId());
        criteria.andActivityTypeEqualTo(ActivityTypeEnum.CHECK_IN.getCode());
        criteria.andCheckInTypeEqualTo(ActivityCheckInTypeEnum.SERIAL.getCode());
        criteria.andCheckInDaysEqualTo(serialDayCount);
        List<ActivityPrize> prizeList = activityPrizeMapper.selectByExample(example);
        if (CollectionUtil.isEmpty(prizeList)) {
            return null;
        }
        return prizeList.get(0);
    }
}
