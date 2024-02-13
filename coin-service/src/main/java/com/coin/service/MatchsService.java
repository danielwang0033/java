package com.coin.service;

import com.coin.entity.Matchs;
import com.coin.req.MatchsReq;
import com.coin.resp.LeaguesResp;
import com.coin.resp.MatchsResp;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface MatchsService {

    void batchAddStream();

    void batchDelete();

    void delete(MatchsReq req);

    void update(MatchsReq req);

    Matchs getById(Long id);

    Matchs getByMatchId(String matchId);

    MatchsResp getDetailByMatchId(String matchId);

    PageInfo<MatchsResp> pageList(MatchsReq req);

    List<LeaguesResp> LeaguePageList(MatchsReq req);

    PageInfo<MatchsResp> matchPageList(MatchsReq req);

    void publish(MatchsReq req);
}
