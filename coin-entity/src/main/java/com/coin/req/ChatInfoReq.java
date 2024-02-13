package com.coin.req;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ChatInfoReq extends CommonReq {

    private String rid;

    private String rocketUserId;
    private String rocketUserName;

    private Integer owner;

    private Integer mute;

    private String name;
    private String avatar;
    private String email;
    private String userName;

    private String matchName;

    private String count = "50";
}