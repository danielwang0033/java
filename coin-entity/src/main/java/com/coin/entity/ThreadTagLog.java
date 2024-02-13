package com.coin.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ThreadTagLog implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Integer threadId;
    private String tag;
    private Integer action;
    private Date createdAt;
    private Date updatedAt;

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                " [" +
                "Hash = " + hashCode() +
                ", id=" + id +
                ", threadId=" + threadId +
                ", tag=" + tag +
                ", action=" + action +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", serialVersionUID=" + serialVersionUID +
                "]";
    }
}