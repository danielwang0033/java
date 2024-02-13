package com.coin.req;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class MatchLeaguesReq extends CommonReq {

    // 赛程ID
    private String leagueId;

    // 赛程名称
    private String leagueName;

    // 赛程logo
    private String leagueLogo;

    // 6足球 5篮球
    private Integer type;
    // 赛程logo
    private Integer sort;
}
