package com.coin.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Likes implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long userId;
    private String likeableType;
    private Long likeableId;
    private Date createdAt;
    private Date updatedAt;

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                " [" +
                "Hash = " + hashCode() +
                ", id=" + id +
                ", userId=" + userId +
                ", likeableType=" + likeableType +
                ", likeableId=" + likeableId +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", serialVersionUID=" + serialVersionUID +
                "]";
    }
}