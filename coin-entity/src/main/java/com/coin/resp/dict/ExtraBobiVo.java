package com.coin.resp.dict;

import lombok.Data;

@Data
public class ExtraBobiVo {

    private String count;

    private String reason;

    public ExtraBobiVo(String count, String reason) {
        this.count = count;
        this.reason = reason;
    }
}
