package com.coin.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Activity implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String activityTitle;
    private Integer activityType;
    private Integer priority;
    private Date showTimeStart;
    private Date showTimeEnd;
    private Date activityTimeStart;
    private Date activityTimeEnd;
    private String floatButtonImage;
    private String floatButtonImageH5;
    private String backgroundImage;
    private String backgroundImageH5;
    private String headImage;
    private String headImageH5;
    private String ruleImage;
    private String ruleImageH5;
    private Integer status;
    private String createdBy;
    private Date createdAt;
    private String updatedBy;
    private Date updatedAt;
    private String deletedBy;
    private Date deletedAt;

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                " [" +
                "Hash = " + hashCode() +
                ", id=" + id +
                ", activityTitle=" + activityTitle +
                ", activityType=" + activityType +
                ", priority=" + priority +
                ", showTimeStart=" + showTimeStart +
                ", showTimeEnd=" + showTimeEnd +
                ", activityTimeStart=" + activityTimeStart +
                ", activityTimeEnd=" + activityTimeEnd +
                ", floatButtonImage=" + floatButtonImage +
                ", floatButtonImageH5=" + floatButtonImageH5 +
                ", backgroundImage=" + backgroundImage +
                ", backgroundImageH5=" + backgroundImageH5 +
                ", headImage=" + headImage +
                ", headImageH5=" + headImageH5 +
                ", ruleImage=" + ruleImage +
                ", ruleImageH5=" + ruleImageH5 +
                ", status=" + status +
                ", createdBy=" + createdBy +
                ", createdAt=" + createdAt +
                ", updatedBy=" + updatedBy +
                ", updatedAt=" + updatedAt +
                ", deletedBy=" + deletedBy +
                ", deletedAt=" + deletedAt +
                ", serialVersionUID=" + serialVersionUID +
                "]";
    }
}