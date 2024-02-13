package com.coin.enums;

import java.util.HashMap;
import java.util.Map;

public enum ActivityTypeEnum {

    // 活动类型, 1:邀请好友 2: 签到 3:抽奖
    INVITE(1, "邀请好友"),
    CHECK_IN(2, "签到"),
    DRAW(3, "抽奖");

    private static final Map<Integer, ActivityTypeEnum> enumMap = new HashMap<>();

    static {
        for (ActivityTypeEnum item : ActivityTypeEnum.values()) {
            enumMap.put(item.code, item);
        }
    }

    private int code;
    private String message;

    ActivityTypeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static ActivityTypeEnum checkAndGet(Integer code) {
        ActivityTypeEnum e = enumMap.get(code);
        if (e != null) {
            return e;
        }
        throw new RuntimeException("活动类型code:" + code + " 不存在!");
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
