package com.coin.resp.thread;

import lombok.Data;

import java.util.Date;

@Data
public class ThreadReplyVo {

    // 回帖ID
    private Long id;

    // 帖子ID
    private Long threadId;

    // 帖子名称
    private String threadName;

    // 作者用户ID
    private Long userId;

    // 作者用户名字
    private String userName;

    private String avatar;

    // 内容
    private String content;

    private String pics;

    private Date createdAt;

    // 论坛板块id
    private Long forumId;

    private String forumName;

    private ThreadUserSubjectVo thread;

    private String threadUserName;

    private String threadUserAvatar;
}
