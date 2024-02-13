package com.coin.resp.notification;

import lombok.Data;

import java.util.Date;

@Data
public class NotificationsVo {
    private String id;

    private boolean isRead;

    private String type;

    private String message;

    private Date messageTime;

    private Object extraData;
}
