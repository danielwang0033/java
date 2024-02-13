package com.coin.resp;

import lombok.Data;

import java.util.Date;

@Data
public class GuessTypeResp {

    private Long id;

    // 名称
    private String name;

    // 别名
    private String alias;

    // 颜色
    private String color;

    // 状态, 0: 停用 1:启用
    private Integer status;

    // 创建时间
    private Date createdAt;

    // 修改时间
    private Date updatedAt;
}
