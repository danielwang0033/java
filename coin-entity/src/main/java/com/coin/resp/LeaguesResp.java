package com.coin.resp;

import lombok.Data;

@Data
public class LeaguesResp {

    private String leagueid;
    private String leaguename;
    private String leaguelogo;
    private String leagueCn;
    private String leagueId;

    public String getLeagueCn() {
        return leaguename.split("\\|")[0];
    }

    public String getLeagueId() {
        return leagueid;
    }

}
