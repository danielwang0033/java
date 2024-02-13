package com.coin.req;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class GoodTagsReq extends CommonReq {

    private String tagContent;

    private String tagImageUrl;
}
