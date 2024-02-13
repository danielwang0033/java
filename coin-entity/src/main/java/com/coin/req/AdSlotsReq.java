package com.coin.req;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class AdSlotsReq extends CommonReq {

    // 广告位名称
    private String name;

    // 别名
    private String alias;

    // 广告类型1-单图 2-多图 3-视频
    private Integer type;

    // 投放平台1-pc,2-h5
    private Integer platform;
}
