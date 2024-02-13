package com.coin.service.impl;

import com.coin.entity.ThreadTags;
import com.coin.entity.ThreadTagsExample;
import com.coin.mapper.ThreadTagsMapper;
import com.coin.req.ForumsReq;
import com.coin.service.ThreadTagsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ThreadTagsServiceImpl implements ThreadTagsService {

    private static final Logger logger = LoggerFactory.getLogger(ThreadTagsServiceImpl.class);

    @Resource
    private ThreadTagsMapper threadTagsMapper;

    @Override
    public List<ThreadTags> tags(ForumsReq req) {
        ThreadTagsExample example = new ThreadTagsExample();
        return threadTagsMapper.selectByExample(example);
    }
}
