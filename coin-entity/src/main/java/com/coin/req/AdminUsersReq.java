package com.coin.req;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class AdminUsersReq extends CommonReq {

    private String username;

    private String password;

    private String newPassword;

    private String name;

    private String avatar;

    private String rememberToken;
}
