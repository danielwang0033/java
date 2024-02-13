package com.coin.resp;

import lombok.Data;

import java.util.Date;

@Data
public class ThreadTopicsResp {

    private Long id;

    // 所属板块
    private Long forumId;

    // 名称
    private String name;

    // 缩略图
    private String pic;

    // 文字颜色
    private String color;

    // 简介
    private String desc;

    // 状态
    private Integer status;

    // 帖子数量
    private Integer threadCount;

    // 置顶
    private Integer onlyAdmin;

    // 阅读量
    private Integer readCount;

    // 热门
    private Integer isHot;

    // 排序
    private Integer sort;
    private Date createdAt;
    private Date updatedAt;
}
