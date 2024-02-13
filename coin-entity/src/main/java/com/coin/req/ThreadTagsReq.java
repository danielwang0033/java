package com.coin.req;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ThreadTagsReq extends CommonReq {

    // 别名
    private String alias;

    // 标签名
    private String name;
}
