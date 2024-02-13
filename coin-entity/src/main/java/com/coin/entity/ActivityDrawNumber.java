package com.coin.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ActivityDrawNumber implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long activityId;
    private Integer changeType;
    private Long userId;
    private Integer beforeChange;
    private Integer afterChange;
    private Integer changeQuantity;
    private String remark;
    private String createdBy;
    private Date createdAt;

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                " [" +
                "Hash = " + hashCode() +
                ", id=" + id +
                ", activityId=" + activityId +
                ", changeType=" + changeType +
                ", userId=" + userId +
                ", beforeChange=" + beforeChange +
                ", afterChange=" + afterChange +
                ", changeQuantity=" + changeQuantity +
                ", remark=" + remark +
                ", createdBy=" + createdBy +
                ", createdAt=" + createdAt +
                ", serialVersionUID=" + serialVersionUID +
                "]";
    }
}