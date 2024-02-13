package com.coin.resp.guess;

import lombok.Data;

/**
 * 竞猜类型
 */
@Data
public class GuessTypeVo {

    private Long id;

    // 名称
    private String name;

    // 别名
    private String alias;

    // 颜色
    private String color;

    // 背景图
    private String image;
}
