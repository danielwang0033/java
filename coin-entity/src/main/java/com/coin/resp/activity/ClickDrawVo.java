package com.coin.resp.activity;

import lombok.Data;

@Data
public class ClickDrawVo {

    // 奖品id
    private Long prizeId;
    // 提示消息
    private String tipMessage;
    // 奖品类型
    private Integer prizeType;
}
