package com.coin.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Rooms implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String rid;
    private String rocketUserId;
    private Integer owner;
    private Integer mute;
    private Date updatedAt;
    private Date createdAt;

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                " [" +
                "Hash = " + hashCode() +
                ", id=" + id +
                ", rid=" + rid +
                ", rocketUserId=" + rocketUserId +
                ", owner=" + owner +
                ", mute=" + mute +
                ", updatedAt=" + updatedAt +
                ", createdAt=" + createdAt +
                ", serialVersionUID=" + serialVersionUID +
                "]";
    }
}