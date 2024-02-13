package com.coin.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class UserLevel implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Integer level;
    private String name;
    private String badge;
    private Integer needExp;
    private String privilege;
    private Date createdAt;
    private Date updatedAt;

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                " [" +
                "Hash = " + hashCode() +
                ", id=" + id +
                ", level=" + level +
                ", name=" + name +
                ", badge=" + badge +
                ", needExp=" + needExp +
                ", privilege=" + privilege +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", serialVersionUID=" + serialVersionUID +
                "]";
    }
}