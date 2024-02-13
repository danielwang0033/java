package com.coin.service.impl;

import com.coin.entity.ActivityDrawNumber;
import com.coin.mapper.ActivityDrawNumberMapper;
import com.coin.resp.activity.ActivityDrawNumberVo;
import com.coin.service.ActivityDrawNumberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class ActivityDrawNumberServiceImpl implements ActivityDrawNumberService {

    private static final Logger logger = LoggerFactory.getLogger(ActivityDrawNumberServiceImpl.class);

    @Resource
    private ActivityDrawNumberMapper activityDrawNumberMapper;

    @Override
    public void addLog(ActivityDrawNumberVo req) {
        Date now = new Date();
        ActivityDrawNumber add = new ActivityDrawNumber();
        add.setActivityId(req.getActivityId());
        add.setChangeType(req.getChangeType().getCode());
        add.setUserId(req.getUserId());
        add.setBeforeChange(req.getBeforeChange());
        add.setAfterChange(req.getAfterChange());
        add.setChangeQuantity(req.getChangeQuantity());
        add.setRemark(req.getRemark());
        add.setCreatedBy(req.getCreatedBy());
        add.setCreatedAt(now);
        activityDrawNumberMapper.insertSelective(add);
    }
}
