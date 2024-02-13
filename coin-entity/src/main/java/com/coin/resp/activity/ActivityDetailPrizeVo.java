package com.coin.resp.activity;

import lombok.Data;

@Data
public class ActivityDetailPrizeVo {

    private Long id;

    // 奖品名称
    private String prizeName;

    // 奖品类型
    private Integer prizeType;

    // 奖品数量
    private Integer prizeQuantity;

    // 奖品图片
    private String prizeImage;
}
