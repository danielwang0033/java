package com.coin.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class ActivityPrize implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long activityId;
    private Integer activityType;
    private Integer prizeType;
    private String prizeName;
    private Integer prizeQuantity;
    private String prizeImage;
    private BigDecimal probability;
    private Integer checkInType;
    private Integer checkInDays;
    private String checkInDate;
    private String createdBy;
    private Date createdAt;

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                " [" +
                "Hash = " + hashCode() +
                ", id=" + id +
                ", activityId=" + activityId +
                ", activityType=" + activityType +
                ", prizeType=" + prizeType +
                ", prizeName=" + prizeName +
                ", prizeQuantity=" + prizeQuantity +
                ", prizeImage=" + prizeImage +
                ", probability=" + probability +
                ", checkInType=" + checkInType +
                ", checkInDays=" + checkInDays +
                ", checkInDate=" + checkInDate +
                ", createdBy=" + createdBy +
                ", createdAt=" + createdAt +
                ", serialVersionUID=" + serialVersionUID +
                "]";
    }
}