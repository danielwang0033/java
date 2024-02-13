package com.coin.resp.guess;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 评论
 */
@Data
public class GuessReplyVo {

    private Long id;

    // 用户id
    private Long userId;

    // 用户名称
    private String userName;

    // 用户头像
    private String userAvatar;

    // 评论内容
    private String content;

    // 创建时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date createdAt;
}
