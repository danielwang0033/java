package com.coin.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ArticleComments implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long articleId;
    private String articleTitle;
    private Long userId;
    private String userName;
    private String content;
    private String parentId;
    private Date createdAt;
    private Date updatedAt;

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                " [" +
                "Hash = " + hashCode() +
                ", id=" + id +
                ", articleId=" + articleId +
                ", userId=" + userId +
                ", userName=" + userName +
                ", content=" + content +
                ", parentId=" + parentId +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", serialVersionUID=" + serialVersionUID +
                "]";
    }
}