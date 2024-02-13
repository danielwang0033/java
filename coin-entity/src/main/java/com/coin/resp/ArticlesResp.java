package com.coin.resp;

import lombok.Data;

import java.util.Date;

@Data
public class ArticlesResp {

    private Long id;

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

    private String artGroupName;
    private Date createdAt;
    private Date updatedAt;
    private Integer incrLikes;
}
