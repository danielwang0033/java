package com.coin.service.impl;

import com.coin.entity.ActivityNameCard;
import com.coin.mapper.ActivityNameCardMapper;
import com.coin.resp.activity.NameCardNumberVo;
import com.coin.service.ActivityNameCardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class ActivityNameCardServiceImpl implements ActivityNameCardService {

    private static final Logger logger = LoggerFactory.getLogger(ActivityNameCardServiceImpl.class);

    @Resource
    private ActivityNameCardMapper activityNameCardMapper;

    @Override
    public void addLog(NameCardNumberVo req) {
        Date now = new Date();
        ActivityNameCard add = new ActivityNameCard();
        add.setActivityId(req.getActivityId());
        add.setChangeType(req.getChangeType().getCode());
        add.setUserId(req.getUserId());
        add.setBeforeChange(req.getBeforeChange());
        add.setAfterChange(req.getAfterChange());
        add.setChangeQuantity(req.getChangeQuantity());
        add.setRemark(req.getRemark());
        add.setCreatedBy(req.getCreatedBy());
        add.setCreatedAt(now);
        activityNameCardMapper.insertSelective(add);
    }
}
