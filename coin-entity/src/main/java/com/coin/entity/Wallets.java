package com.coin.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class Wallets implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String holderType;
    private Long holderId;
    private String name;
    private String slug;
    private String uuid;
    private String description;
    private String meta;
    private BigDecimal balance;
    private Short decimalPlaces;
    private Date createdAt;
    private Date updatedAt;

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                " [" +
                "Hash = " + hashCode() +
                ", id=" + id +
                ", holderType=" + holderType +
                ", holderId=" + holderId +
                ", name=" + name +
                ", slug=" + slug +
                ", uuid=" + uuid +
                ", description=" + description +
                ", meta=" + meta +
                ", balance=" + balance +
                ", decimalPlaces=" + decimalPlaces +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", serialVersionUID=" + serialVersionUID +
                "]";
    }
}