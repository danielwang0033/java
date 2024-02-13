package com.coin.service;

import com.coin.cache.UserThreadCountVo;
import com.coin.entity.Threads;
import com.coin.req.FavoritesReq;
import com.coin.req.ThreadsReq;
import com.coin.req.search.SearchReq;
import com.coin.req.thread.ThreadsVoReq;
import com.coin.resp.dict.ExtraMsgVo;
import com.coin.resp.fav.FavVo;
import com.coin.resp.like.LikeVo;
import com.coin.resp.search.SearchForumVo;
import com.coin.resp.thread.ThreadsDetailVo;
import com.coin.resp.thread.ThreadsVo;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Set;

public interface ThreadsService {

    void delete(ThreadsReq req);

    void update(ThreadsReq req);

    void addReadCount(ThreadsReq req);

    void addLikeCount(ThreadsReq req);

    Threads getById(Long id);

    PageInfo<ThreadsVo> pageList(ThreadsReq req);

    List<UserThreadCountVo> countByUserIdList(Set<Long> userIdList);

    PageInfo<ThreadsVo> threadLists(ThreadsVoReq req);

    List<ThreadsVo> findByIdList(List<Long> idList);

    ThreadsDetailVo threadById(ThreadsReq req);

    PageInfo<ThreadsVo> favoriteThreads(FavoritesReq req);

    PageInfo<ThreadsVo> myThreads(FavoritesReq req);

    PageInfo<ThreadsVo> threadsByUserId(FavoritesReq req);

    Long countByUserId(Long userId);

    ExtraMsgVo bbsNewThread(ThreadsReq req);

    void modifyThreadById(ThreadsReq req);

    void deleteThread(ThreadsReq req, Long id);

    LikeVo like(ThreadsReq req);

    FavVo favorite(ThreadsReq req);

    PageInfo<SearchForumVo> searchForum(SearchReq req);

    Integer addReplyCount(Long id);

    void addBestTag(Threads threads);

    void addHotTag(Threads threads);
}
