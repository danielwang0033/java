package com.coin.service;

import com.coin.entity.AdSlots;
import com.coin.req.AdSlotsReq;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface AdSlotsService {

    void add(AdSlotsReq req);

    void delete(AdSlotsReq req);

    void update(AdSlotsReq req);

    AdSlots getById(Long id);

    List<AdSlots> getByPlatform(Integer platform);

    PageInfo<AdSlots> pageList(AdSlotsReq req);
}
