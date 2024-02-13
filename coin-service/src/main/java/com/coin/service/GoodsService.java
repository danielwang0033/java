package com.coin.service;

import com.coin.entity.Goods;
import com.coin.req.GoodsReq;
import com.coin.resp.GoodsResp;
import com.github.pagehelper.PageInfo;

public interface GoodsService {

    void add(GoodsReq req);

    void delete(GoodsReq req);

    void update(GoodsReq req);

    void exchange(GoodsReq req, Long id);

    int rollback(Long id, int num);

    Goods getById(Long id);

    PageInfo<Goods> pageList(GoodsReq req);

    PageInfo<GoodsResp> pageDatas(GoodsReq req);

    PageInfo<GoodsResp> exchangeLeaderboard(GoodsReq req);
}
