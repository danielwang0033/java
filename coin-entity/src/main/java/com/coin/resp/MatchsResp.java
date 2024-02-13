package com.coin.resp;

import lombok.Data;

import java.util.Date;

@Data
public class MatchsResp {

    private Long id;
    private String matchid;
    private String matchId;
    // 运动类型:5:篮球，6:足球
    private Integer gametype;
    private String gameTypeStr;
    private Integer matchtime;
    private Integer matchTime;
    private String leagueid;
    private String leagueId;
    private String leaguename;
    private String leagueCn;
    private String leaguelogo;
    // 比赛状态，足球比赛代码：0（未），-1（完），1（上半场）,2（中场）,3（下半场），4（加时）,5（点球）；篮球比赛代码：-1(已完场) 0（未），1-4（第几节）,5（加时） 50(中场)
    private Integer statusid;
    private Integer statusId;
    private String statusIdStr;
    // 是否正在播放 1播放 0未播
    private String status;
    // 是否正在播放 1播放 0未播
    private Byte playing;
    private String playingStr;
    private String videos;
    private String hasVideos;
    private String thirdid;
    private Byte lineup;
    // 0未发布，1发布
    private Integer publish;
    private Date createdAt;
    private Date updatedAt;
    private String rid;
    // 首页：1显示 0不显示
    private Integer ishome;
    // 主队名称
    private String homeTeamName;
    // 主队名称
    private String homeCnName;
    // 主队
    private MatchScoresResp home;
    // 客队
    private MatchScoresResp away;
    // 客队名称
    private String awayTeamName;
    private String awayCnName;
    // 客队名称
    private String streamname;
    // 客队名称
    private Integer sort;
    // 客队名称
    private String videoUrl;

    private java.util.List<String> streamList;

    public String getMatchId() {
        return matchid;
    }

    public Integer getGameType() {
        return gametype;
    }

    public Integer getMatchTime() {
        return matchtime;
    }

    public String getLeagueId() {
        return leagueid;
    }

    public String getLeagueCn() {
        return leaguename.split("\\|")[0];
    }

    public Integer getStatusId() {
        return statusid;
    }
}
