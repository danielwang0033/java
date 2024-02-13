package com.coin.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ActivityInviteFriend implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long activityId;
    private Long userId;
    private String inviteCode;
    private Long inviteeUserId;
    private String createdBy;
    private Date createdAt;

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                " [" +
                "Hash = " + hashCode() +
                ", id=" + id +
                ", activityId=" + activityId +
                ", userId=" + userId +
                ", inviteCode=" + inviteCode +
                ", inviteeUserId=" + inviteeUserId +
                ", createdBy=" + createdBy +
                ", createdAt=" + createdAt +
                ", serialVersionUID=" + serialVersionUID +
                "]";
    }
}