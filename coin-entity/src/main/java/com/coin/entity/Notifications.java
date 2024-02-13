package com.coin.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Notifications implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String type;
    private String notifiableType;
    private Long notifiableId;
    private String data;
    private Date readAt;
    private Date createdAt;
    private Date updatedAt;

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                " [" +
                "Hash = " + hashCode() +
                ", id=" + id +
                ", type=" + type +
                ", notifiableType=" + notifiableType +
                ", notifiableId=" + notifiableId +
                ", data=" + data +
                ", readAt=" + readAt +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", serialVersionUID=" + serialVersionUID +
                "]";
    }
}