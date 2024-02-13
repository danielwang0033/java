package com.coin.resp;

import lombok.Data;

@Data
public class MatchScoresResp {

    private String id;
    private String name;
    private String nameCn;
    private String logo;
    // 1主队2客队
    private Integer type;
    private Integer score;
    private Integer overTimeScore;
    private Integer halfScore;
    private Integer penaltyScore;
    private Integer firstScore;
    private Integer secondScore;
    private Integer thirdScore;
    private Integer fourthScore;
    private String matchId;

    public String getNameCn() {
        return name.split("\\|")[0];
    }
}
