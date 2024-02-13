package com.coin.service.asyn;

import com.coin.entity.Notifications;

public interface AsyncNotificationService {

    void sendExtendReplyNotification(Notifications notifications);
}
