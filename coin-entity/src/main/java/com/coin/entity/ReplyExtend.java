package com.coin.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ReplyExtend implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Integer towerType;
    private Long towerId;
    private Long towerUserId;
    private Long floorId;
    private Long floorUserId;
    private Long parentId;
    private Long parentUserId;
    private Long userId;
    private Integer towerOwnerFlag;
    private Integer floorOwnerFlag;
    private String content;
    private Date createdAt;
    private Date updatedAt;
    private Date deletedAt;

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                " [" +
                "Hash = " + hashCode() +
                ", id=" + id +
                ", towerType=" + towerType +
                ", towerId=" + towerId +
                ", towerUserId=" + towerUserId +
                ", floorId=" + floorId +
                ", floorUserId=" + floorUserId +
                ", parentId=" + parentId +
                ", parentUserId=" + parentUserId +
                ", userId=" + userId +
                ", towerOwnerFlag=" + towerOwnerFlag +
                ", floorOwnerFlag=" + floorOwnerFlag +
                ", content=" + content +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", deletedAt=" + deletedAt +
                ", serialVersionUID=" + serialVersionUID +
                "]";
    }
}