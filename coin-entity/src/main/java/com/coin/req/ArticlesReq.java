package com.coin.req;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class ArticlesReq extends CommonReq {

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

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createdAtMin;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createdAtMax;

    // 列表类型：目前只有1普通=最新 2热门 3他的热门
    private String type;

    // 文章类型：1狗庄揭秘 2行业资讯 不填就是狗庄揭秘
    private String groupId;

    private Integer incrLikes;
}
