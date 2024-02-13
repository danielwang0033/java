package com.coin.service;

import com.coin.entity.ThreadTopics;
import com.coin.req.ThreadTopicsReq;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Set;

public interface ThreadTopicsService {

    void add(ThreadTopicsReq req);

    void delete(ThreadTopicsReq req);

    void update(ThreadTopicsReq req);

    void updateBatchReadCount();

    ThreadTopics getById(Long id);

    List<ThreadTopics> getList(ThreadTopicsReq req);

    PageInfo<ThreadTopics> pageList(ThreadTopicsReq req);

    List<ThreadTopics> threadTopics(ThreadTopicsReq req);

    List<ThreadTopics> allTopics(ThreadTopicsReq req);

    List<ThreadTopics> getByIdSet(Set<Long> topicIdSet);
}
