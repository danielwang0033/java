package com.coin.resp;

import com.coin.cache.UserLevelCache;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class UsersResp {

    private Long id;

    // 名字
    private String name;
    private String email;

    // 用户修改邮箱时间
    private Date emailModifiedAt;

    // 邮箱验证时间
    private Date emailVerifiedAt;

    // 用户是否被禁用
    private Integer isBaned;

    // 是否在线：1-是 0-否
    private Integer isOnline;

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

    // rocket chat用户名
    private String rocketUsername;

    // rocket chat用户login token
    private String rocketUserLoginToken;

    // rocket chat用户id
    private String rocketUserId;

    // 用户在线状态,如果此时间小于当前时间则为离线
    private Date onlineUntil;
    private Date lastLoginDate;
    private String bio;
    private String avatar;
    private String rememberToken;
    private Date createdAt;
    private Date updatedAt;

    // qq号
    private String qq;

    // 微信
    private String wechat;

    // 手机号
    private String phone;

    // 房间ID
    private String roomid;

    // 用户等级
    private UserLevelCache userLevel;
    private UserLevelCache nextLevel;

    //今日发帖数
    private Integer todayArticleNum;
    //发帖总数
    private Integer totalArticleNum;
    //今日投诉
    private Integer todayReportNum;
    //投诉总数
    private Integer totalReportNum;
    //关注数
    private Integer totalFollowableNum;
    //粉丝数
    private Integer totalFans;
    //博币数
    private BigDecimal bobi;

    // 抽奖次数
    private Integer drawNumberBalance;
    // 邀请码
    private String inviteCode;
    // 来自邀请用户
    private Long invitedByUserId;

    // 是否存在邀请用户
    private boolean inviteListFlag;
}
