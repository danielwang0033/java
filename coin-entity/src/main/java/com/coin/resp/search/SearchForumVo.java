package com.coin.resp.search;

import lombok.Data;

import java.util.Date;

@Data
public class SearchForumVo {

    private Long id;

    private Long forumId;

    private ForumsVo forum;

    private Long userId;

    private UserShortVo user;

    private String subject;

    private String desc;

    private Integer readCount;

    private Integer replyCount;

    private Date createdAt;

    private Date postTime;
}