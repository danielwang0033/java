package com.coin.enums;

import lombok.Getter;

@Getter
public enum CacheKeyEnum {

    USER_DEFAULT_AVATAR("USER_DEFAULT_AVATAR", "用户默认头像"),
    USER_LEVEL_LIST("USER_LEVEL_LIST", "用户等级列表"),
    GUESS_READ_COUNT("GUESS_READ_COUNT", "竞猜查看次数"),
    ARTICLE_READ_COUNT("ARTICLE_READ_COUNT", "文章查看次数"),
    THREAD_READ_COUNT("THREAD_READ_COUNT", "帖子查看次数"),
    REPORT_READ_COUNT("REPORT_READ_COUNT", "老哥帮手查看次数"),
    USER_EXT_INFO("USER_EXT_INFO", "用户扩展信息"),

    FORUM_CATEGORY("category", ""),
    FORUM_SUB_NAV("forum_sub_nav", ""),
    THREAD_ID_LISTS("thread_id_lists", ""),
    THREAD_TOPIC_ID_LISTS("thread_topic_id_lists", ""),
    REPORT_ID_LISTS("report_id_lists", ""),
    THREAD_REPLY_ID_LISTS("thread_reply_id_lists", ""),
    REPORT_REPLY_ID_LISTS("report_reply_id_lists", ""),
    GOOD_ID_LISTS("good_id_lists", ""),
    ORDER_COUNTS_ID_LISTS_DESC("order_good_list", ""),
    ORDER_ID_LISTS("order_id_lists", ""),
    ORDER_ID_LISTS_USER("order_list_user", ""),
    ARTICLE_ID_LISTS("article_id_lists", "行业资讯列表"),
    CHAT("chat", ""),
    AD_LIST("adlist", ""),

    // *****************积分和博币相关缓存***************************
    BOBI_CONFIG("BOBI_CONFIG", ""),
    LEVEL_CONFIG("LEVEL_EXP_CONFIG", ""),
    ADD_BOBI_EXP_ACTIVE_EVERY_DAY("ADD_BOBI_EXP_ACTIVE_EVERY_DAY_%s", ""),
    ADD_BOBI_EXP_DAILY_THREAD("ADD_BOBI_EXP_DAILY_THREAD_%s", ""),
    ADD_BOBI_EXP_REPLY_OR_COMMENT("ADD_BOBI_EXP_REPLY_OR_COMMENT_%s", ""),
    ADD_BOBI_EXP_BE_REPLY_OR_COMMENT("ADD_BOBI_EXP_BE_REPLY_OR_COMMENT_%s", ""),
    ADD_BOBI_EXP_LOGIN("ADD_BOBI_EXP_LOGIN_%s", "");

    private String keyName;
    private String keyDesc;

    CacheKeyEnum(String keyName, String keyDesc) {
        this.keyName = keyName;
        this.keyDesc = keyDesc;
    }

    public void setKeyName(String keyName) {
        this.keyName = keyName;
    }

    public void setKeyDesc(String keyDesc) {
        this.keyDesc = keyDesc;
    }
}
