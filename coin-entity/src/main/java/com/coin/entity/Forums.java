package com.coin.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Forums implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String name;
    private String alias;
    private Integer status;
    private String labelColor;
    private Integer subjectCount;
    private Date createdAt;
    private Date updatedAt;

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                " [" +
                "Hash = " + hashCode() +
                ", id=" + id +
                ", name=" + name +
                ", alias=" + alias +
                ", status=" + status +
                ", labelColor=" + labelColor +
                ", subjectCount=" + subjectCount +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", serialVersionUID=" + serialVersionUID +
                "]";
    }
}