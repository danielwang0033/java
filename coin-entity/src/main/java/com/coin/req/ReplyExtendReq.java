package com.coin.req;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class ReplyExtendReq extends CommonReq {

    // 回复类型: 1:楼层回复  2:对话回复
    private Integer replyType;

    // 楼类型, 1: 帖子 2:老哥帮手 3:行业资讯 4:竞猜
    private Integer towerType;

    // 楼id
    private Long towerId;

    // 楼用户id
    private Long towerUserId;

    // 楼层id
    private Long floorId;

    // 楼层用户id
    private Long floorUserId;

    // 父节点id
    private Long parentId;

    // 父节点用户id
    private Long parentUserId;

    // 用户id
    private Long userId;

    // 是否是楼主 1:是 0:否
    private Integer towerOwnerFlag;

    // 是否是楼层主 1:是 0:否
    private Integer floorOwnerFlag;

    // 内容
    private String content;

    private List<Long> floorIdList;

    // --------提醒-------------
    private String fromContent;

    private String fromContentReply;

    private String parentReplyContent;

    private Long artGroupId;
}
