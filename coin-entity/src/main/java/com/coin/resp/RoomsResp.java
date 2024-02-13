package com.coin.resp;

import lombok.Data;

@Data
public class RoomsResp {

    private Long id;
    private String rid;
    private String rocketUserId;
    private Integer owner;
    private Integer mute;
    private String matchName;

    private String userName;
}
