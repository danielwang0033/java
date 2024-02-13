package com.coin.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class GuessType implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String name;
    private String alias;
    private String color;
    private String image;
    private Integer status;
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
                ", color=" + color +
                ", image=" + image +
                ", status=" + status +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", serialVersionUID=" + serialVersionUID +
                "]";
    }
}