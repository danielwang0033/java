package com.coin.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Matchs implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String matchid;
    private Integer gametype;
    private Integer matchtime;
    private String leagueid;
    private String leaguename;
    private String leaguelogo;
    private Integer statusid;
    private String status;
    private Byte playing;
    private String videos;
    private String thirdid;
    private Byte lineup;
    private Integer publish;
    private Date createdAt;
    private Date updatedAt;
    private String rid;
    private Integer ishome;
    private Integer sort;
    private String videoUrl;
    private Integer streamid;
    private String streamname;

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                " [" +
                "Hash = " + hashCode() +
                ", id=" + id +
                ", matchid=" + matchid +
                ", gametype=" + gametype +
                ", matchtime=" + matchtime +
                ", leagueid=" + leagueid +
                ", leaguename=" + leaguename +
                ", leaguelogo=" + leaguelogo +
                ", statusid=" + statusid +
                ", status=" + status +
                ", playing=" + playing +
                ", videos=" + videos +
                ", thirdid=" + thirdid +
                ", lineup=" + lineup +
                ", publish=" + publish +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", rid=" + rid +
                ", ishome=" + ishome +
                ", sort=" + sort +
                ", videoUrl=" + videoUrl +
                ", streamid=" + streamid +
                ", streamname=" + streamname +
                ", serialVersionUID=" + serialVersionUID +
                "]";
    }
}