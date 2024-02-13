package com.coin.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ThreadTopics implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long forumId;
    private String name;
    private String pic;
    private String color;
    private String desc;
    private Integer status;
    private Integer threadCount;
    private Integer onlyAdmin;
    private Integer readCount;
    private Integer isHot;
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
                ", pic=" + pic +
                ", color=" + color +
                ", desc=" + desc +
                ", status=" + status +
                ", threadCount=" + threadCount +
                ", onlyAdmin=" + onlyAdmin +
                ", readCount=" + readCount +
                ", isHot=" + isHot +
                ", sort=" + sort +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", serialVersionUID=" + serialVersionUID +
                "]";
    }
}