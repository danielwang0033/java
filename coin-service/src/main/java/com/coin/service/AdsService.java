package com.coin.service;

import com.coin.entity.Ads;
import com.coin.req.AdsReq;
import com.coin.resp.AdsResp;
import com.coin.resp.ad.AdSlotsRespVo;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

public interface AdsService {

    void add(AdsReq req);

    void delete(AdsReq req);

    void update(AdsReq req);

    Ads getById(Long id);

    PageInfo<AdsResp> pageList(AdsReq req);

    Map<String, AdSlotsRespVo> clientPageList(AdsReq req);

    List<String> popUps(AdsReq req);

    void readPopUps(AdsReq req);
}
