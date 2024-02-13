package com.coin.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class Goods implements Serializable {
    private static final long serialVersionUID = 1L;
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
    private Boolean canBuy = true;

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                " [" +
                "Hash = " + hashCode() +
                ", id=" + id +
                ", name=" + name +
                ", pic=" + pic +
                ", h5pic=" + h5pic +
                ", pics=" + pics +
                ", tags=" + tags +
                ", stock=" + stock +
                ", content=" + content +
                ", price=" + price +
                ", sort=" + sort +
                ", status=" + status +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", deletedAt=" + deletedAt +
                ", serialVersionUID=" + serialVersionUID +
                "]";
    }
}