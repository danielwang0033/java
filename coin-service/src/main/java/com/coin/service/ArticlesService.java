package com.coin.service;

import com.coin.entity.Articles;
import com.coin.req.ArticlesReq;
import com.coin.req.FavoritesReq;
import com.coin.req.article.ArticlesVoReq;
import com.coin.req.search.SearchReq;
import com.coin.resp.ArticlesResp;
import com.coin.resp.article.ArticlesVoResp;
import com.coin.resp.fav.FavVo;
import com.coin.resp.like.LikeVo;
import com.coin.resp.search.SearchArticleVo;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ArticlesService {

    void add(ArticlesReq req);

    void addVisits(ArticlesReq req);

    void addLikeCount(ArticlesReq req);

    void delete(ArticlesReq req);

    void update(ArticlesReq req);

    Articles getById(Long id);

    PageInfo<ArticlesResp> pageList(ArticlesReq req);

    PageInfo<ArticlesVoResp> lists(ArticlesVoReq req);

    ArticlesVoResp getDetailById(ArticlesReq req);

    PageInfo<ArticlesVoResp> favoriteArticles(FavoritesReq req);

    List<ArticlesVoResp> findByIdList(List<Long> idList);

    List<Articles> findList(List<Long> idList);

    LikeVo like(ArticlesVoReq req);

    FavVo favorite(ArticlesVoReq req);

    PageInfo<SearchArticleVo> searchArticle(SearchReq req);
}
