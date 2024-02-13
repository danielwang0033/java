package com.coin.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class Transfers implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String fromType;
    private Long fromId;
    private String toType;
    private Long toId;
    private String status;
    private String statusLast;
    private Long depositId;
    private Long withdrawId;
    private BigDecimal discount;
    private BigDecimal fee;
    private String uuid;
    private Date createdAt;
    private Date updatedAt;

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                " [" +
                "Hash = " + hashCode() +
                ", id=" + id +
                ", fromType=" + fromType +
                ", fromId=" + fromId +
                ", toType=" + toType +
                ", toId=" + toId +
                ", status=" + status +
                ", statusLast=" + statusLast +
                ", depositId=" + depositId +
                ", withdrawId=" + withdrawId +
                ", discount=" + discount +
                ", fee=" + fee +
                ", uuid=" + uuid +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", serialVersionUID=" + serialVersionUID +
                "]";
    }
}