package com.coin.resp;

import lombok.Data;

import java.util.Date;

@Data
public class ReportTagsResp {
    // 主键
    private Long id;

    // 标签内容
    private String tagContent;

    // 标签图片地址
    private String tagImageUrl;
    private Date createdAt;
    private Date updatedAt;
}
