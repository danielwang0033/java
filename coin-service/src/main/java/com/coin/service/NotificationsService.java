package com.coin.service;

import com.coin.req.NotificationsReq;
import com.coin.resp.notification.NotificationsVo;
import com.github.pagehelper.PageInfo;

public interface NotificationsService {

    PageInfo<NotificationsVo> notificationPageList(NotificationsReq req);

    void readOne(NotificationsReq req);

    void readAll(NotificationsReq req);

    Long unreadNotifyCount(Long userid);

    void buildNotification(NotificationsReq req);
}
