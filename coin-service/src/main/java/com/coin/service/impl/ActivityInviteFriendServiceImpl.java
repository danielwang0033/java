package com.coin.service.impl;

import com.coin.entity.Activity;
import com.coin.entity.ActivityInviteFriend;
import com.coin.entity.Users;
import com.coin.mapper.ActivityInviteFriendMapper;
import com.coin.mapper.ext.ActivityInviteFriendExtMapper;
import com.coin.service.ActivityInviteFriendService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class ActivityInviteFriendServiceImpl implements ActivityInviteFriendService {

    private static final Logger logger = LoggerFactory.getLogger(ActivityInviteFriendServiceImpl.class);

    @Resource
    private ActivityInviteFriendMapper activityInviteFriendMapper;
    @Resource
    private ActivityInviteFriendExtMapper activityInviteFriendExtMapper;

    @Override
    public void saveInvitedLog(Users users, Activity activity, Users invitedByUser) {
        Date now = new Date();
        ActivityInviteFriend activityInviteFriend = new ActivityInviteFriend();
        activityInviteFriend.setActivityId(activity.getId());
        activityInviteFriend.setUserId(invitedByUser.getId());
        activityInviteFriend.setInviteCode(invitedByUser.getInviteCode());
        activityInviteFriend.setInviteeUserId(users.getId());
        activityInviteFriend.setCreatedBy(invitedByUser.getName());
        activityInviteFriend.setCreatedAt(now);
        activityInviteFriendMapper.insertSelective(activityInviteFriend);
    }

    @Override
    public List<Long> checkByInviteCode(List<Long> hasInviteCodeList) {
        return activityInviteFriendExtMapper.checkByInviteCode(hasInviteCodeList);
    }
}
