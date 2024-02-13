package com.coin.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class MatchScores implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String name;
    private String logo;
    private Integer type;
    private Integer score;
    private Integer overtimescore;
    private Integer halfscore;
    private Integer penaltyscore;
    private Integer firstscore;
    private Integer secondscore;
    private Integer thirdscore;
    private Integer fourthscore;
    private String matchid;
    private Date updatedAt;
    private Date createdAt;

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                " [" +
                "Hash = " + hashCode() +
                ", id=" + id +
                ", name=" + name +
                ", logo=" + logo +
                ", type=" + type +
                ", score=" + score +
                ", overtimescore=" + overtimescore +
                ", halfscore=" + halfscore +
                ", penaltyscore=" + penaltyscore +
                ", firstscore=" + firstscore +
                ", secondscore=" + secondscore +
                ", thirdscore=" + thirdscore +
                ", fourthscore=" + fourthscore +
                ", matchid=" + matchid +
                ", updatedAt=" + updatedAt +
                ", createdAt=" + createdAt +
                ", serialVersionUID=" + serialVersionUID +
                "]";
    }
}