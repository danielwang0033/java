package com.coin.req;

import com.coin.entity.Activity;
import com.coin.entity.Users;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class UsersReq extends CommonReq {

    // 名字
    private String username;
    // 名字
    private String name;

    private String email;

    // 邮箱验证时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date emailVerifiedAt;

    private String password;

    // 用户是否被禁用
    private Integer isBaned;

    // 用户当前经验值
    private Integer exp;

    private Integer bobi;

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
    // rocket chat用户login token
    private String rocketStatus;

    // rocket chat用户id
    private String rocketUserId;

    // 用户在线状态,如果此时间小于当前时间则为离线
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date onlineUntil;

    private String bio;

    private String avatar;

    private String rememberToken;


    // qq号
    private String qq;

    // 微信
    private String wechat;

    // 手机号
    private String phone;

    // 房间ID
    private String roomid;

    // 必填 取值 all,read ,all = 全部 read=已读
    private String scope;

    private String currentPassword;
    private String newPassword;
    private String newPasswordConfirmation;

    private String betNote;
    private BigDecimal betAmount;
    private BigDecimal settleAmount;
    private String settleNote;

    private Integer type;    //1全部  2在房间  3不在房间

    private Integer addDrawNumber;

    private Integer nameCardNumber;

    private Users invitedByUser;

    private Activity activity;

    private String remark;
}
