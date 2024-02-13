package com.coin.resp;

import lombok.Data;

import java.util.Date;

@Data
public class NotificationsResp {

    private String id;
    private String type;
    private String notifiableType;
    private Long notifiableId;
    private String data;
    private Date readAt;
    private Date createdAt;
    private Date updatedAt;
}
