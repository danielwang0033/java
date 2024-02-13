package com.coin.resp;

import lombok.Data;

import java.util.Date;

@Data
public class UserExpLogsResp {

    private Long id;

    // 用户id
    private Integer userId;

    // 所获取经验值
    private Integer exp;

    // 原因
    private String info;
    private Date createdAt;
    private Date updatedAt;
}
