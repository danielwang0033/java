package com.coin.req;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class MatchBbtechsReq extends CommonReq {

    // 主队ID
    private Integer homeId;

    // 篮板
    private Integer homeBackboard;

    // 犯规
    private Integer homeFoul;

    // 三分
    private Integer homeThreeScore;

    // 二分
    private Integer homeTwoScore;

    // 罚篮分
    private Integer homePenalty;

    // 罚篮命中率
    private String homePenaltyRate;

    // 前场篮板
    private Integer homeBackboardF;

    // 后场篮板
    private Integer homeBackboardB;

    // 助攻
    private Integer homeAssists;

    // 抢断
    private Integer homeSnatch;

    // 盖帽
    private Integer homeBlock;

    // 失误
    private Integer homeFault;

    // 客队ID
    private Integer awayId;

    private Integer awayBackboard;

    private Integer awayFoul;

    private Integer awayThreeScore;

    private Integer awayTwoScore;

    private Integer awayPenalty;

    private String awayPenaltyRate;

    private Integer awayBackboardF;

    private Integer awayBackboardB;

    private Integer awayAssists;

    private Integer awaySnatch;

    private Integer awayBlock;

    private Integer awayFault;

    // 比赛ID
    private Integer matchId;
}
