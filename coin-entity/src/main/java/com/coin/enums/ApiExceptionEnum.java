package com.coin.enums;

/**
 * 异常代码
 */
public enum ApiExceptionEnum {

    NO_PERMISSION_TO_POST_THREAD(100021, "您无权权限,具体信息请联系客服"),
    NO_PERMISSION_TO_EDIT(100026, "无法编辑,您不是发布者/版主,或者帖子被锁定"),
    NO_PERMISSION_TO_DELETE(100027, "无法删除,您不是发布者/版主,或者帖子被锁定");

    private int code;
    private String message;

    ApiExceptionEnum(int code, String message) {
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
