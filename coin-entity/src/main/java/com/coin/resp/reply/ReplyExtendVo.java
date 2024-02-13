package com.coin.resp.reply;

import lombok.Data;

import java.util.Date;

@Data
public class ReplyExtendVo {

    private Long id;

    private Integer towerType;

    private Long floorId;

    private Long parentId;

    private Long parentUserId;

    private Long userId;

    private Integer towerOwnerFlag;

    private Integer floorOwnerFlag;

    private String content;

    private Date createdAt;

    // 补充字段
    private String userName;
    private String userAvatar;
    private String parentUserName;

    private boolean allowEdit;

    private boolean allowDelete;
}
