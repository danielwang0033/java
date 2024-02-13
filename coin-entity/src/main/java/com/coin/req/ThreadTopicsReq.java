package com.coin.req;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class ThreadTopicsReq extends CommonReq {

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

    private String sortStr;


    private List<Long> ids;

    private String categoryAlias;
}
