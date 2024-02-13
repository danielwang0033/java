package com.coin.enums;

import java.util.HashMap;
import java.util.Map;

public enum ActivityPrizeTypeEnum {

    // 活动奖品类型, 1:博币 2:经验 3:抽奖次数 4:改名卡 5:实物 6:未中奖
    BOBI(1, "博币"),
    EXP(2, "经验"),
    DRAW_NUMBER(3, "抽奖次数"),
    CHANGE_NAME_CARD(4, "改名卡"),
    PHYSICAL_PRIZE(5, "实物"),
    DID_NOT_WIN(6, "未中奖");

    private static final Map<Integer, ActivityPrizeTypeEnum> enumMap = new HashMap<>();

    static {
        for (ActivityPrizeTypeEnum item : ActivityPrizeTypeEnum.values()) {
            enumMap.put(item.code, item);
        }
    }

    private int code;
    private String message;

    ActivityPrizeTypeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static ActivityPrizeTypeEnum checkAndGet(Integer code) {
        ActivityPrizeTypeEnum e = enumMap.get(code);
        if (e != null) {
            return e;
        }
        throw new RuntimeException("活动奖品类型code:" + code + " 不存在!");
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
