package com.coin.resp.user;

import com.coin.cache.UserLevelCache;
import lombok.Data;

import java.util.Date;

@Data
public class UserSimpleInfoVo {

    private Long id;

    private String name;

    private String email;

    // 用户是否被禁用
    private Integer isBaned;

    // 用户当前经验值
    private Integer exp;

    // 是否是论坛版主
    private Integer isForumAdmin;

    // 是否是超级管理员
    private Integer isSuperAdmin;

    // 用户是否禁止发帖
    private Integer isBanPost;

    // 用户是否禁止戒赌吧聊天
    private Integer isBanForum;

    private String avatar;

    private String bio;

    private Date createdAt;

    private UserLevelCache level;
}
