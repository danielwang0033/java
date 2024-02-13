package com.coin.resp.search;

import lombok.Data;

import java.util.Date;

@Data
public class SearchArticleVo {

    private Long id;

    private String title;

    private String content;

    private Integer status;

    private Long userId;

    private UserShortVo user;

    private Integer visits;

    private Integer comments;

    private Date createdAt;

    private Date postTime;
}