package com.coin.resp.reply;

import lombok.Data;

import java.util.Date;

@Data
public class ReplyExtendMgrVo {

    private Long id;

    private Integer towerType;

    private Long towerId;

    private Long towerUserId;

    private Long floorId;

    private Long floorUserId;

    private Long parentId;

    private Long parentUserId;

    private Long userId;

    private Integer towerOwnerFlag;

    private Integer floorOwnerFlag;

    private String content;

    private Date createdAt;

    private Date updatedAt;

    private Date deletedAt;

    // name
    private String towerUserName;

    private String floorUserName;

    private String parentUserName;

    private String userName;
}