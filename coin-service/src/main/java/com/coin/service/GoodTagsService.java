package com.coin.service;

import com.coin.entity.GoodTags;
import com.coin.req.GoodTagsReq;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface GoodTagsService {

    void add(GoodTagsReq req);

    void delete(GoodTagsReq req);

    void update(GoodTagsReq req);

    List<GoodTags> getAllList(boolean needDel);

    GoodTags getById(Long id);

    PageInfo<GoodTags> pageList(GoodTagsReq req);
}
