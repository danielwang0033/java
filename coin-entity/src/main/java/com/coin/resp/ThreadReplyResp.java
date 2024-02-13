package com.coin.resp;

import lombok.Data;

import java.util.Date;

@Data
public class ThreadReplyResp {
    // 回帖ID
    private Long id;

    // 帖子ID
    private Long threadId;

    // 帖子名称
    private String threadName;

    // 帖子ID
    private Long replyId;

    // 作者用户ID
    private Long userId;

    // 作者用户名字
    private String userName;

    // 楼层号
    private Integer floorNo;

    // 内容
    private String content;

    // 内容中所包含的图片
    private String pics;

    // 是否需要处理图片
    private Integer needProcessPics;

    // 最后编辑时间
    private Date lastModifyAt;

    // 最后编辑者用户id
    private Integer lastModifyUserId;
    private Date createdAt;
    private Date updatedAt;
    private Date deletedAt;

    // 论坛板块id
    private Long forumId;

    private String forumName;
}
