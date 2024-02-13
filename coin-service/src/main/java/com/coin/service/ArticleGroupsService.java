package com.coin.service;

import com.coin.entity.ArticleGroups;
import com.coin.req.ArticleGroupsReq;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Set;

public interface ArticleGroupsService {

    List<ArticleGroups> getList(ArticleGroupsReq req);

    PageInfo<ArticleGroups> pageList(ArticleGroupsReq req);

    ArticleGroups getById(Long id);

    void add(ArticleGroupsReq req);

    void update(ArticleGroupsReq req);

    List<ArticleGroups> findByIdList(Set<Long> artGroupIdSet);
}
