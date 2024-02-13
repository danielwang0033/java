package com.coin.entity;

import java.io.Serializable;
import java.util.Date;

public class ActivityCheckIn implements Serializable {
    private Long id;

    private Long activityId;

    private Long userId;

    private Long dayPrizeExchangeId;

    private Integer serialDays;

    private Long serialPrizeExchangeId;

    private String createdBy;

    private Date createdAt;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getDayPrizeExchangeId() {
        return dayPrizeExchangeId;
    }

    public void setDayPrizeExchangeId(Long dayPrizeExchangeId) {
        this.dayPrizeExchangeId = dayPrizeExchangeId;
    }

    public Integer getSerialDays() {
        return serialDays;
    }

    public void setSerialDays(Integer serialDays) {
        this.serialDays = serialDays;
    }

    public Long getSerialPrizeExchangeId() {
        return serialPrizeExchangeId;
    }

    public void setSerialPrizeExchangeId(Long serialPrizeExchangeId) {
        this.serialPrizeExchangeId = serialPrizeExchangeId;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", activityId=").append(activityId);
        sb.append(", userId=").append(userId);
        sb.append(", dayPrizeExchangeId=").append(dayPrizeExchangeId);
        sb.append(", serialDays=").append(serialDays);
        sb.append(", serialPrizeExchangeId=").append(serialPrizeExchangeId);
        sb.append(", createdBy=").append(createdBy);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}