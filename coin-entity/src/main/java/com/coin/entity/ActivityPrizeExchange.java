package com.coin.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ActivityPrizeExchange implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long activityId;
    private Integer activityType;
    private Long userId;
    private Integer prizeType;
    private String prizeName;
    private Integer prizeQuantity;
    private String prizeImage;
    private Integer status;
    private String remark;
    private String createdBy;
    private Date createdAt;
    private String updatedBy;
    private Date updatedAt;

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                " [" +
                "Hash = " + hashCode() +
                ", id=" + id +
                ", activityId=" + activityId +
                ", activityType=" + activityType +
                ", userId=" + userId +
                ", prizeType=" + prizeType +
                ", prizeName=" + prizeName +
                ", prizeQuantity=" + prizeQuantity +
                ", prizeImage=" + prizeImage +
                ", status=" + status +
                ", remark=" + remark +
                ", createdBy=" + createdBy +
                ", createdAt=" + createdAt +
                ", updatedBy=" + updatedBy +
                ", updatedAt=" + updatedAt +
                ", serialVersionUID=" + serialVersionUID +
                "]";
    }
}