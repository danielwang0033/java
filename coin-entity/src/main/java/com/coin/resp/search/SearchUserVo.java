package com.coin.resp.search;

import lombok.Data;

@Data
public class SearchUserVo {

    private Long id;

    private String name;

    private String avatar;

    private Integer exp;

    private Long followeeCount;

    private Long followerCount;

    private Long threadCount;

    private boolean isFollowedByMe;

    private String userLevelBadge;
}