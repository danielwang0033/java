package com.coin.service;

import com.coin.entity.MatchLeagues;
import com.coin.req.MatchLeaguesReq;
import com.github.pagehelper.PageInfo;

public interface MatchLeaguesService {

    void add(MatchLeaguesReq req);

    void delete(MatchLeaguesReq req);

    void update(MatchLeaguesReq req);

    MatchLeagues getById(Long id);

    PageInfo<MatchLeagues> pageList(MatchLeaguesReq req);
}
