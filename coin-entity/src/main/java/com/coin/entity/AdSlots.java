package com.coin.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class AdSlots implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String name;
    private String alias;
    private Integer type;
    private Integer platform;
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
                ", type=" + type +
                ", platform=" + platform +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", serialVersionUID=" + serialVersionUID +
                "]";
    }
}