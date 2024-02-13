package com.coin.enums;

import java.util.HashMap;
import java.util.Map;

public enum ActivityStatusEnum {

    // 活动状态, -1:删除 0: 停用 1:启用
    DELETED(-1, "已删除"),
    DISABLED(0, "已停用"),
    ENABLE(1, "启用");

    private static final Map<Integer, ActivityStatusEnum> enumMap = new HashMap<>();

    static {
        for (ActivityStatusEnum item : ActivityStatusEnum.values()) {
            enumMap.put(item.code, item);
        }
    }

    private int code;
    private String message;

    ActivityStatusEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static ActivityStatusEnum checkAndGet(Integer code) {
        ActivityStatusEnum e = enumMap.get(code);
        if (e != null) {
            return e;
        }
        throw new RuntimeException("活动状态code:" + code + " 不存在!");
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
