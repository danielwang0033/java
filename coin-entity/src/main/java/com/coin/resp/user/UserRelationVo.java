package com.coin.resp.user;

import lombok.Data;

@Data
public class UserRelationVo {

    private Long id;

    private String name;

    private String avatar;

    private String bio;

    // 用户当前经验值
    private Integer exp;

    private UserRelationLevelVo level;

    private boolean isOnline;

    private boolean isFollowedByMe;

    private Long followeeCount;

    private Long followerCount;

    private Long threadCount;
}
