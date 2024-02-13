package com.coin.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class AdminUsers implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String username;
    private String password;
    private String name;
    private String avatar;
    private String rememberToken;
    private Date createdAt;
    private Date updatedAt;
    private Long roleId;

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                " [" +
                "Hash = " + hashCode() +
                ", id=" + id +
                ", username=" + username +
                ", password=" + password +
                ", name=" + name +
                ", avatar=" + avatar +
                ", rememberToken=" + rememberToken +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", roleId=" + roleId +
                ", serialVersionUID=" + serialVersionUID +
                "]";
    }
}