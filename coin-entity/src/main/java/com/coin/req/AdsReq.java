package com.coin.req;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class AdsReq extends CommonReq {

    // uuid
    private String uuid;

    // 广告id
    private Integer adSlotId;

    // 物料
    private String file;

    // 点击链接
    private String link;

    // 开始投放时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date start;

    // 结束投放时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date end;

    // 状态
    private Integer status;

    // 广告排序
    private Integer sort;

    private Integer platform;

    // 确认是否是H5端
    private boolean h5Flag;
}
