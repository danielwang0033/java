package com.coin.req;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class MatchScoresReq extends CommonReq {

    private String name;

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
}
