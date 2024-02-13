package com.coin.req;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ArticleCommentsReq extends CommonReq {

    // 文章id
    private Long articleId;

    // 评论者id
    private Long userId;

    // 评论者
    private String userName;

    // 内容
    private String content;

    // 引用id
    private String parentId;
}
