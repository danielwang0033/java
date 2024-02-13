package com.coin.service;

import com.coin.entity.MatchVideos;
import com.coin.req.MatchVideosReq;
import com.github.pagehelper.PageInfo;

public interface MatchVideosService {

    PageInfo<MatchVideos> pageList(MatchVideosReq req);
}
