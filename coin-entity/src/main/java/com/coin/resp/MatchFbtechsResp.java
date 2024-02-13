package com.coin.resp;

import lombok.Data;

import java.util.Date;

@Data
public class MatchFbtechsResp {
    // 主队ID
    private Integer homeId;
    private Long id;

    // 角球
    private Integer homeCorner;

    // 红牌数
    private Integer homeRed;
    private Integer homeYellow;

    // 空球率
    private String homeBallRate;

    // 进球数
    private Integer homeAttack;

    // 危险进攻数
    private Integer homeDangerAttack;

    // 射门数
    private Integer homeShoot;

    // 射正数
    private Integer homeShootOn;

    // 客队ID
    private Integer awayId;
    private Integer awayCorner;
    private Integer awayRed;
    private Integer awayYellow;
    private String awayBallRate;
    private Integer awayAttack;
    private Integer awayDangerAttack;
    private Integer awayShoot;
    private Integer awayShootOn;

    // 比赛ID
    private Integer matchId;
    private Date updatedAt;
    private Date createdAt;
}
