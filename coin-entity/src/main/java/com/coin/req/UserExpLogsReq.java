package com.coin.req;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserExpLogsReq extends CommonReq {

    // 用户id
    private Integer userId;

    // 所获取经验值
    private Integer exp;

    // 原因
    private String info;
}
