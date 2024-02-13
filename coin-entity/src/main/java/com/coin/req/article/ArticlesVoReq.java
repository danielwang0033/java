package com.coin.req.article;

import com.coin.req.CommonReq;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ArticlesVoReq extends CommonReq {

    // 文章标题
    private String title;

    // 缩略图
    private String thumbs;

    // 文章描述
    private String description;

    // 文章内容
    private String content;

    // 图片地址列表，逗号分隔
    private String pics;

    // 评论数
    private Integer comments;

    // 是否发布
    private Integer status;

    // 作者id
    private Long userId;

    // 作者
    private String userName;

    // 访问量
    private Integer visits;

    // 分类
    private Long artGroupId;

    // 列表类型：目前只有1普通=最新 2热门 3他的热门
    private String type;

    // 文章类型：1狗庄揭秘 2行业资讯 不填就是狗庄揭秘
    private String groupId;
}
