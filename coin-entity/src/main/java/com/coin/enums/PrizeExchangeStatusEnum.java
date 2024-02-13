package com.coin.enums;

import java.util.HashMap;
import java.util.Map;

public enum PrizeExchangeStatusEnum {

    // 状态, -1:未中奖 1:等待兑换 2:已兑换
    DID_NOT_WIN(-1, "未中奖"),
    WAITING_FOR_EXCHANGE(1, "等待兑换"),
    EXCHANGED(2, "已兑换");

    private static final Map<Integer, PrizeExchangeStatusEnum> enumMap = new HashMap<>();

    static {
        for (PrizeExchangeStatusEnum item : PrizeExchangeStatusEnum.values()) {
            enumMap.put(item.code, item);
        }
    }

    private int code;
    private String message;

    PrizeExchangeStatusEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static PrizeExchangeStatusEnum checkAndGet(Integer code) {
        PrizeExchangeStatusEnum e = enumMap.get(code);
        if (e != null) {
            return e;
        }
        throw new RuntimeException("奖品兑换状态code:" + code + " 不存在!");
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
