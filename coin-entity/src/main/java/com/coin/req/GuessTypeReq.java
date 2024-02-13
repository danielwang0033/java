package com.coin.req;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class GuessTypeReq extends CommonReq {

    // 名称
    private String name;

    // 别名
    private String alias;

    // 颜色
    private String color;

    // 状态, 0: 停用 1:启用
    private Integer status;

    // 背景图
    private String image;
}
