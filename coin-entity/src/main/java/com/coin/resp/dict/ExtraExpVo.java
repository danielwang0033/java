package com.coin.resp.dict;

import lombok.Data;

@Data
public class ExtraExpVo {

    private String count;

    private String reason;

    public ExtraExpVo(String count, String reason) {
        this.count = count;
        this.reason = reason;
    }
}
