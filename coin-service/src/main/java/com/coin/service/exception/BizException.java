package com.coin.service.exception;

import lombok.Getter;

@Getter
public class BizException extends RuntimeException {

    private final String code;

    private final String errMsg;

    public BizException(String errorCode, String errorMsg) {
        this.code = errorCode;
        this.errMsg = errorMsg;
    }
}
