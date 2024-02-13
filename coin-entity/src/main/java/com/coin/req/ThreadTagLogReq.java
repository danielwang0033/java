package com.coin.req;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ThreadTagLogReq extends CommonReq {

    // 帖子id
    private Integer threadId;

    // 标签
    private String tag;

    // 0-去掉 1-增加
    private Integer action;
}
