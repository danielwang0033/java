package com.coin.enums;

public enum DrawNumChangeTypeEnum {

    // 变更类型,1:管理员加次数 2:改名使用 3:活动赠送
    ADMIN_ADD(1, "管理员手动加抽奖次数"),
    DRAW_USED(2, "抽奖使用"),
    ACTIVITY_GIVE(3, "活动中奖");

    private int code;
    private String message;

    DrawNumChangeTypeEnum(int code, String message) {
        this.code = code;
        this.message = message;
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
