package com.coin.resp;

import lombok.Data;

import java.util.Date;

@Data
public class ArticleGroupsResp {

    private Long id;

    // 名称
    private String name;

    // 别名
    private String alias;

    // 描述
    private String description;
    private Date createdAt;
    private Date updatedAt;
}
