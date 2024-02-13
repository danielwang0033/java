package com.coin.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Ads implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String uuid;
    private Integer adSlotId;
    private String file;
    private String link;
    private Date start;
    private Date end;
    private Integer status;
    private Date createdAt;
    private Date updatedAt;
    private Integer sort;

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                " [" +
                "Hash = " + hashCode() +
                ", id=" + id +
                ", uuid=" + uuid +
                ", adSlotId=" + adSlotId +
                ", file=" + file +
                ", link=" + link +
                ", start=" + start +
                ", end=" + end +
                ", status=" + status +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", sort=" + sort +
                ", serialVersionUID=" + serialVersionUID +
                "]";
    }
}