package com.coin.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ForumSubNavs implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long forumId;
    private String name;
    private String topics;
    private Integer status;
    private Integer sort;
    private Date createdAt;
    private Date updatedAt;

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                " [" +
                "Hash = " + hashCode() +
                ", id=" + id +
                ", forumId=" + forumId +
                ", name=" + name +
                ", topics=" + topics +
                ", status=" + status +
                ", sort=" + sort +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", serialVersionUID=" + serialVersionUID +
                "]";
    }
}