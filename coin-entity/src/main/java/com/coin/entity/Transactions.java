package com.coin.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class Transactions implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String payableType;
    private Long payableId;
    private Long walletId;
    private String type;
    private BigDecimal amount;
    private Boolean confirmed;
    private String meta;
    private String uuid;
    private Date createdAt;
    private Date updatedAt;

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                " [" +
                "Hash = " + hashCode() +
                ", id=" + id +
                ", payableType=" + payableType +
                ", payableId=" + payableId +
                ", walletId=" + walletId +
                ", type=" + type +
                ", amount=" + amount +
                ", confirmed=" + confirmed +
                ", meta=" + meta +
                ", uuid=" + uuid +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", serialVersionUID=" + serialVersionUID +
                "]";
    }
}