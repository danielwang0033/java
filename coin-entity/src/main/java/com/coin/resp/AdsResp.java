package com.coin.resp;

import lombok.Data;

import java.util.Date;

@Data
public class AdsResp {

    private Long id;

    // uuid
    private String uuid;

    // 广告id
    private Integer adSlotId;

    private String adSlotName;

    private Integer platform;

    // 物料
    private String file;

    // 点击链接
    private String link;

    // 开始投放时间
    private Date start;

    // 结束投放时间
    private Date end;

    // 状态
    private Integer status;

    private String statusStr;
    private Date createdAt;
    private Date updatedAt;

    // 广告排序
    private Integer sort;
}
