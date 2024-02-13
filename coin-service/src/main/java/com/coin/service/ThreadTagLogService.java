package com.coin.service;

import com.coin.entity.ThreadTagLog;

public interface ThreadTagLogService {

    ThreadTagLog findByTag(Long threadId, String tag);
}
