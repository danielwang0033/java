package com.coin.resp.admin;

import lombok.Data;

@Data
public class AdminUsersVo {

    private Long id;

    private String username;

    private String name;

    private Long roleId;

    private String avatar;
}
