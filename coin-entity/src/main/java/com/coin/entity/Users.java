package com.coin.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Users implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String name;
    private String email;
    private Date emailModifiedAt;
    private Date emailVerifiedAt;
    private String password;
    private Integer isBaned;
    private Integer exp;
    private Integer isForumAdmin;
    private Integer isSuperAdmin;
    private Integer isBanPost;
    private Integer isBanForum;
    private String rocketUsername;
    private String rocketUserLoginToken;
    private String rocketUserId;
    private Date onlineUntil;
    private Date lastLoginDate;
    private String bio;
    private String avatar;
    private String rememberToken;
    private Date createdAt;
    private Date updatedAt;
    private String qq;
    private String wechat;
    private String phone;
    private String pwd;
    private String rocketStatus;
    private Integer aUid;
    private Integer drawNumberBalance;
    private Integer nameCardBalance;
    private String inviteCode;
    private Long invitedByUserId;

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                " [" +
                "Hash = " + hashCode() +
                ", id=" + id +
                ", name=" + name +
                ", email=" + email +
                ", emailModifiedAt=" + emailModifiedAt +
                ", emailVerifiedAt=" + emailVerifiedAt +
                ", password=" + password +
                ", isBaned=" + isBaned +
                ", exp=" + exp +
                ", isForumAdmin=" + isForumAdmin +
                ", isSuperAdmin=" + isSuperAdmin +
                ", isBanPost=" + isBanPost +
                ", isBanForum=" + isBanForum +
                ", rocketUsername=" + rocketUsername +
                ", rocketUserLoginToken=" + rocketUserLoginToken +
                ", rocketUserId=" + rocketUserId +
                ", onlineUntil=" + onlineUntil +
                ", lastLoginDate=" + lastLoginDate +
                ", bio=" + bio +
                ", avatar=" + avatar +
                ", rememberToken=" + rememberToken +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", qq=" + qq +
                ", wechat=" + wechat +
                ", phone=" + phone +
                ", pwd=" + pwd +
                ", rocketStatus=" + rocketStatus +
                ", aUid=" + aUid +
                ", drawNumberBalance=" + drawNumberBalance +
                ", nameCardBalance=" + nameCardBalance +
                ", inviteCode=" + inviteCode +
                ", invitedByUserId=" + invitedByUserId +
                ", serialVersionUID=" + serialVersionUID +
                "]";
    }
}