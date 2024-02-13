package com.coin.req;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class GuessReplyReq extends CommonReq {

    // 竞猜id
    private Long guessId;

    // 用户id
    private Long userId;

    // 用户名称
    private String userName;

    // 用户头像
    private String userAvatar;

    // 评论内容
    private String content;

    // 状态 0:删除 1:正常
    private Integer status;
}
