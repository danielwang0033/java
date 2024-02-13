package com.coin.resp;

import lombok.Data;

@Data
public class ChatInfoResp {

    private Long id;
    private String wsUrl;
    private String httpUrl;
    private String userId;
    private String username;
    private String name;
    private String avatar;
    private Integer isSuperAdmin;
    private Integer owner;
    private String userToken;
    private String roomId;
    private Integer mute;
}
