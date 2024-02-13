package com.coin.service.impl;

import com.coin.entity.ThreadTagLog;
import com.coin.mapper.ext.ThreadTagLogExtMapper;
import com.coin.service.ThreadTagLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ThreadTagLogServiceImpl implements ThreadTagLogService {

    @Resource
    private ThreadTagLogExtMapper threadTagLogExtMapper;

    @Override
    public ThreadTagLog findByTag(Long threadId, String tag) {
        return threadTagLogExtMapper.findByTag(threadId, tag, 0);
    }
}
