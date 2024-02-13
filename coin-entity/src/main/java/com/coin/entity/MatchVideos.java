package com.coin.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class MatchVideos implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String matchid;
    private Date createdAt;
    private Date updatedAt;
    private Integer streamid;
    private Integer pushing;
    private Integer streamtype;
    private String streamname;
    private String m3u8;
    private String line;

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                " [" +
                "Hash = " + hashCode() +
                ", id=" + id +
                ", matchid=" + matchid +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", streamid=" + streamid +
                ", pushing=" + pushing +
                ", streamtype=" + streamtype +
                ", streamname=" + streamname +
                ", m3u8=" + m3u8 +
                ", line=" + line +
                ", serialVersionUID=" + serialVersionUID +
                "]";
    }
}