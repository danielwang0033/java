package com.coin.resp.user;

import lombok.Data;

import java.util.Date;

@Data
public class UserVo {

    private Long id;
    // 名字
    private String name;
    private String email;
    private String avatar;
    private String bio;
    // 用户当前经验值
    private Integer exp;
    private UserRelationLevelVo level;
    private Integer bobi;
    // 是否在线：1-是 0-否
    private boolean isOnline;
    private boolean isFollowedByMe;
    private Long followeeCount;
    private Long followerCount;
    private Long threadCount;
    private Long reportCount;
    private Date registeredAt;

    private Long unreadNotifyCount;

    private String qq;
    private String wechat;
    private String phone;
    private boolean emailModified;
    private boolean emailVerified;
}
