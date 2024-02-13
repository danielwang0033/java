package com.coin.resp;

import lombok.Data;

import java.util.Date;

@Data
public class UserLevelResp {

    private Long id;

    // 用户等级
    private Integer level;

    // 等级名称
    private String name;

    // 徽章图片
    private String badge;

    // 该等级所需经验值
    private Integer needExp;

    // 等级权限
    private String privilege;
    private Date createdAt;
    private Date updatedAt;
}
