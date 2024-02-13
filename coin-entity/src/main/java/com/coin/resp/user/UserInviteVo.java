package com.coin.resp.user;

import lombok.Data;

import java.util.Date;

@Data
public class UserInviteVo {

    private Long id;
    private String name;
    private String email;
    private Date createAt;
}
