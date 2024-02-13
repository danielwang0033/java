package com.coin.service;

import com.coin.entity.Forums;
import com.coin.req.ForumsReq;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ForumsService {

    void add(ForumsReq req);

    void update(ForumsReq req);

    Forums getById(Long id);

    PageInfo<Forums> pageList(ForumsReq req);

    List<Forums> category();

    Forums selectByAlias(String categoryAlias);

    List<Forums> getByIdList(List<Long> forumIdList);
}
