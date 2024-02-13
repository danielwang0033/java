package com.coin.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Orders implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String sn;
    private Long userId;
    private Long goodId;
    private Integer status;
    private Date createdAt;
    private Date updatedAt;
    private Integer transferId;

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                " [" +
                "Hash = " + hashCode() +
                ", id=" + id +
                ", sn=" + sn +
                ", userId=" + userId +
                ", goodId=" + goodId +
                ", status=" + status +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", transferId=" + transferId +
                ", serialVersionUID=" + serialVersionUID +
                "]";
    }
}