package com.coin.req;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ReportTagsReq extends CommonReq {

    // 标签内容
    private String tagContent;

    // 标签图片地址
    private String tagImageUrl;
}
