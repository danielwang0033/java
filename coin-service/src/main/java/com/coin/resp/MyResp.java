package com.coin.resp;

import com.coin.i18n.I18nUtil;
import lombok.Getter;
import lombok.Setter;

@Setter
public class MyResp<T> {

    private String code;
    private String msg;
    @Getter
    private T data;

    public MyResp(String code, String msg) {
        this.code = code;
        this.msg = I18nUtil.translate(msg);
    }

    public MyResp(String code, String msg, T data) {
        this.code = code;
        this.msg = I18nUtil.translate(msg);
        this.data = data;
    }

    public Integer getCode() {
        return Integer.parseInt(code);
    }

    public String getMessage() {
        return msg;
    }
}
