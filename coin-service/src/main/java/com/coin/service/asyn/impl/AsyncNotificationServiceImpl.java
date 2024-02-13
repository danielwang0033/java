package com.coin.service.asyn.impl;

import com.coin.entity.Notifications;
import com.coin.mapper.NotificationsMapper;
import com.coin.service.asyn.AsyncNotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class AsyncNotificationServiceImpl implements AsyncNotificationService {

    @Resource
    private NotificationsMapper notificationsMapper;

    @Override
    @Async("sysThreadPool")
    public void sendExtendReplyNotification(Notifications notifications) {
        notificationsMapper.insertSelective(notifications);
    }
}
