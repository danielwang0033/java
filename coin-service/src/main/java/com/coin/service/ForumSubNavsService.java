package com.coin.service;

import com.coin.entity.ForumSubNavs;
import com.coin.req.ForumSubNavsReq;
import com.coin.resp.thread.ForumSubNavsVo;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ForumSubNavsService {

    void add(ForumSubNavsReq req);

    void delete(ForumSubNavsReq req);

    void update(ForumSubNavsReq req);

    ForumSubNavs getById(Long id);

    PageInfo<ForumSubNavsVo> pageList(ForumSubNavsReq req);

    List<ForumSubNavsVo> threadSubNavs(ForumSubNavsReq req);
}
