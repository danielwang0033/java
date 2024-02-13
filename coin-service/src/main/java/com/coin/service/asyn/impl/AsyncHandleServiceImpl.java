package com.coin.service.asyn.impl;

import cn.hutool.core.util.ObjectUtil;
import com.coin.enums.CacheKeyEnum;
import com.coin.mapper.ext.ArticlesExtMapper;
import com.coin.mapper.ext.GuessExtMapper;
import com.coin.mapper.ext.ReportsExtMapper;
import com.coin.mapper.ext.ThreadsExtMapper;
import com.coin.service.asyn.AsyncHandleService;
import com.coin.service.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class AsyncHandleServiceImpl implements AsyncHandleService {

    @Resource
    private RedisUtil redisUtil;
    @Resource
    private GuessExtMapper guessExtMapper;
    @Resource
    private ArticlesExtMapper articlesExtMapper;
    @Resource
    private ThreadsExtMapper threadsExtMapper;
    @Resource
    private ReportsExtMapper reportsExtMapper;

    @Async("sysThreadPool")
    @Override
    public void addGuessCommentAmount(Long guessId) {
        guessExtMapper.addCommentCount(guessId);
    }

    @Override
    public void subtractGuessCommentAmount(Long guessId) {
        guessExtMapper.subtractCommentAmount(guessId);
    }

    @Async("sysThreadPool")
    @Override
    public void addGuessVisitsAmount(Long guessIdLong) {
        String id = guessIdLong + "";
        String key = CacheKeyEnum.GUESS_READ_COUNT.getKeyName();
        HashOperations<String, Object, Object> hashOperations = redisUtil.getRedisTemplateForHash();
        Object countObj = hashOperations.get(key, id);
        if (ObjectUtil.isNotNull(countObj)) {
            int count = (int) countObj;
            if (count >= 6) {
                guessExtMapper.addVisitsAmount(guessIdLong, 6);
                hashOperations.delete(key, id);
            } else {
                hashOperations.increment(key, id, 1);
            }
        } else {
            hashOperations.put(key, id, 1);
        }
    }

    @Override
    public void addArticleVisitsAmount(Long articleId) {
        String id = articleId + "";
        String key = CacheKeyEnum.ARTICLE_READ_COUNT.getKeyName();
        HashOperations<String, Object, Object> hashOperations = redisUtil.getRedisTemplateForHash();
        Object countObj = hashOperations.get(key, id);
        if (ObjectUtil.isNotNull(countObj)) {
            int count = (int) countObj;
            if (count >= 6) {
                articlesExtMapper.addVisitsAmount(articleId, 6);
                hashOperations.delete(key, id);
            } else {
                hashOperations.increment(key, id, 1);
            }
        } else {
            hashOperations.put(key, id, 1);
        }
    }

    @Override
    public void addThreadVisitsAmount(Long threadId) {
        String id = threadId + "";
        String key = CacheKeyEnum.THREAD_READ_COUNT.getKeyName();
        HashOperations<String, Object, Object> hashOperations = redisUtil.getRedisTemplateForHash();
        Object countObj = hashOperations.get(key, id);
        if (ObjectUtil.isNotNull(countObj)) {
            int count = (int) countObj;
            if (count >= 6) {
                threadsExtMapper.addVisitsAmount(threadId, 6);
                hashOperations.delete(key, id);
            } else {
                hashOperations.increment(key, id, 1);
            }
        } else {
            hashOperations.put(key, id, 1);
        }
    }

    @Override
    public void addReportVisitsAmount(Long reportId) {
        String id = reportId + "";
        String key = CacheKeyEnum.REPORT_READ_COUNT.getKeyName();
        HashOperations<String, Object, Object> hashOperations = redisUtil.getRedisTemplateForHash();
        Object countObj = hashOperations.get(key, id);
        if (ObjectUtil.isNotNull(countObj)) {
            int count = (int) countObj;
            if (count >= 6) {
                reportsExtMapper.addVisitsAmount(reportId, 6);
                hashOperations.delete(key, id);
            } else {
                hashOperations.increment(key, id, 1);
            }
        } else {
            hashOperations.put(key, id, 1);
        }
    }
}
