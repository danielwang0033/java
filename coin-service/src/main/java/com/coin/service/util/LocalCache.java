package com.coin.service.util;

import cn.hutool.cache.Cache;
import cn.hutool.cache.CacheUtil;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.util.ObjectUtil;

public class LocalCache {

    /**
     * 活动缓存
     */
    public static final String ACTIVITY_CACHE = "ACTIVITY_CACHE";
    /**
     * 首页setting
     */
    public static final String SETTING_CACHE = "SETTING_CACHE";
    /**
     * 模块开关
     */
    public static final String MODEL_SWITCH_CACHE = "MODEL_SWITCH_CACHE";
    /**
     * 广告
     */
    public static final String AD_CACHE = "AD_CACHE_%s";
    /**
     * 国际化字典
     */
    public static final String I18N_CACHE = "I18N_CACHE";
    /**
     * 首次进入弹窗
     */
    public static final String EVERY_DAY_FIRST_POP_UPS = "EVERY_DAY_FIRST_POP_UPS";
    /**
     * XSS忽略处理字段
     */
    public static final String XSS_IGNORE_FIELD = "XSS_IGNORE_FIELD";

    /**
     * XSS富文本处理字段
     */
    public static final String XSS_RICH_TEXT_FIELD = "XSS_RICH_TEXT_FIELD";

    // 默认最大缓存时间
    private static final Cache<String, Object> LOCAL_CACHE = CacheUtil.newTimedCache(Long.MAX_VALUE);

    public static <T> T getCache(String key) {
        Object o = LOCAL_CACHE.get(key);
        if (ObjectUtil.isNotNull(o)) {
            return (T) o;
        }
        return null;
    }

    public static void putCache(String key, Object object) {
        LOCAL_CACHE.put(key, object);
    }

    public static void putCache(String key, Object object, long second) {
        LOCAL_CACHE.put(key, object, DateUnit.SECOND.getMillis() * second);
    }

    public static void deleteCache(String key) {
        LOCAL_CACHE.remove(key);
    }
}
