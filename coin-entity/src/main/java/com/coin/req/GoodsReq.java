package com.coin.req;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
public class GoodsReq extends CommonReq {

    private String name;

    private String pic;

    private String h5pic;

    private String pics;

    private String tags;

    private Integer stock;

    private String content;

    private BigDecimal price;

    private Integer sort;

    private Integer status;

    // 时间参数 day 当天,week 本周,month 本月,6month 最近六月,year 今年， 默认本月
    private String time;
}
