package com.coin.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class MatchLeagues implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String leagueId;
    private String leagueName;
    private String leagueLogo;
    private Date createdAt;
    private Date updatedAt;
    private Integer type;
    private Integer sort;

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                " [" +
                "Hash = " + hashCode() +
                ", id=" + id +
                ", leagueId=" + leagueId +
                ", leagueName=" + leagueName +
                ", leagueLogo=" + leagueLogo +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", type=" + type +
                ", sort=" + sort +
                ", serialVersionUID=" + serialVersionUID +
                "]";
    }
}