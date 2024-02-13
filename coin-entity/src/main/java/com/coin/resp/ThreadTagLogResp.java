package com.coin.resp;

import lombok.Data;

import java.util.Date;

@Data
public class ThreadTagLogResp {

    private Long id;

    // 帖子id
    private Integer threadId;

    // 标签
    private String tag;

    // 0-去掉 1-增加
    private Integer action;
    private Date createdAt;
    private Date updatedAt;
}
