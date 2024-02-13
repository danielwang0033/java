package com.coin.req;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ThreadReplyReq extends CommonReq {

    // 帖子ID
    private Long threadId;

    // 帖子ID
    private Long replyId;

    // 作者用户ID
    private Long userId;

    // 楼层号
    private Integer floorNo;

    // 内容
    private String content;

    // 内容中所包含的图片
    private String pics;

    // 是否需要处理图片
    private Integer needProcessPics;

    // 最后编辑者用户id
    private Integer lastModifyUserId;

    private Long forumId;

    private String threadSubject;
}
