package com.coin.resp;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class GoodsResp {

    private Long id;
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
    private Date createdAt;
    private Date updatedAt;
    private Date deletedAt;

    private Integer totalExchange;
    private Integer redeemed;

    private List<String> tagStrs;
}
