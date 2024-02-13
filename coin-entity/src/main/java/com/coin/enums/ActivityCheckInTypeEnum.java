package com.coin.enums;

import java.util.HashMap;
import java.util.Map;

public enum ActivityCheckInTypeEnum {

    // 签到类型 0:连续签到 1:普通签到
    SERIAL(0, "连续签到"),
    GENERAL(1, "普通签到");

    private static final Map<Integer, ActivityCheckInTypeEnum> enumMap = new HashMap<>();

    static {
        for (ActivityCheckInTypeEnum item : ActivityCheckInTypeEnum.values()) {
            enumMap.put(item.code, item);
        }
    }

    private int code;
    private String message;

    ActivityCheckInTypeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static ActivityCheckInTypeEnum checkAndGet(Integer code) {
        ActivityCheckInTypeEnum e = enumMap.get(code);
        if (e != null) {
            return e;
        }
        throw new RuntimeException("签到类型code:" + code + " 不存在!");
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
