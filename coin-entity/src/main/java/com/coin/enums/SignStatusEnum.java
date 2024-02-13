package com.coin.enums;

import java.util.HashMap;
import java.util.Map;

public enum SignStatusEnum {

    // 签到状态: 1:已签到 2:未签到 3:过期未签到
    SIGNED(1, "已签到"),
    NOT_SIGNED(2, "未签到"),
    EXPIRED_NOT_SIGNED(3, "过期未签到");

    private static final Map<Integer, SignStatusEnum> enumMap = new HashMap<>();

    static {
        for (SignStatusEnum item : SignStatusEnum.values()) {
            enumMap.put(item.code, item);
        }
    }

    private int code;
    private String message;

    SignStatusEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static SignStatusEnum checkAndGet(Integer code) {
        SignStatusEnum e = enumMap.get(code);
        if (e != null) {
            return e;
        }
        throw new RuntimeException("签到状态code:" + code + " 不存在!");
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
