package com.coin.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Articles implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String title;
    private String thumbs;
    private String description;
    private String content;
    private String pics;
    private Integer comments;
    private Integer status;
    private Long userId;
    private String userName;
    private Integer visits;
    private Long artGroupId;
    private Date createdAt;
    private Date updatedAt;
    private Integer incrLikes;

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                " [" +
                "Hash = " + hashCode() +
                ", id=" + id +
                ", title=" + title +
                ", thumbs=" + thumbs +
                ", description=" + description +
                ", content=" + content +
                ", pics=" + pics +
                ", comments=" + comments +
                ", status=" + status +
                ", userId=" + userId +
                ", userName=" + userName +
                ", visits=" + visits +
                ", artGroupId=" + artGroupId +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", incrLikes=" + incrLikes +
                ", serialVersionUID=" + serialVersionUID +
                "]";
    }
}