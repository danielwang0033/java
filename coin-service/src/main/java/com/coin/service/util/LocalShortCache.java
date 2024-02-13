package com.coin.service.util;

import cn.hutool.cache.Cache;
import cn.hutool.cache.CacheUtil;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.util.ObjectUtil;

/**
 * 自动过期缓存_短时间
 */
public class LocalShortCache {

    /**
     * 聊天消息缓存
     */
    public static final String CHAT_MESSAGE = "CHAT_MESSAGE";

    // 默认30秒缓存
    private static final Cache<String, Object> TIME_CACHE = CacheUtil.newTimedCache(DateUnit.SECOND.getMillis() * 30);

    public static <T> T getCache(String key) {
        Object o = TIME_CACHE.get(key);
        if (ObjectUtil.isNotNull(o)) {
            return (T) o;
        }
        return null;
    }

    public static void putCache(String key, Object object, int second) {
        TIME_CACHE.put(key, object, DateUnit.SECOND.getMillis() * second);
    }
}
