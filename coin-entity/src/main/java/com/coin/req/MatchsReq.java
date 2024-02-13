package com.coin.req;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class MatchsReq extends CommonReq {

    private String matchId;

    private Integer gameType;

    private Integer matchTime;

    private String leagueId;

    private String leagueName;

    private String leagueLogo;

    // 比赛状态，足球比赛代码：0（未），-1（完），1（上半场）,2（中场）,3（下半场），4（加时）,5（点球）；篮球比赛代码：-1(已完场) 0（未），1-4（第几节）,5（加时） 50(中场)
    private Integer statusId;

    private String status;

    private Integer playing;

    private String videos;

    private String thirdId;

    private Integer lineUp;

    // 0未发布，1发布
    private Integer publish;

    private String rid;

    // 首页：1显示 0不显示
    private Integer isHome;

    private Integer sort;
    private String sortOther;
    private String sortLeague;
    private String videoUrl;
    private String streamName;
    private String statusIds;

    private Integer matchTimeGe;
    private Integer matchTimeLe;
    private Integer starting;
}
