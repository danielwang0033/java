package com.coin.resp;

import lombok.Data;

import java.util.Date;

@Data
public class ThreadTagsResp {

    private Long id;

    // 别名
    private String alias;

    // 标签名
    private String name;
    private Date createdAt;
    private Date updatedAt;
}
