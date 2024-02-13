package com.coin.resp.reply;

import com.coin.resp.user.UserLevelVo;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ReplyVo {

    private Long id;

    private Long towerId;

    private Long userId;

    private String content;

    private String pics;

    private Date createdAt;

    private String userName;

    private String userAvatar;

    private Integer bobi;

    private Integer exp;

    private Long threadCount;

    private List<ReplyExtendVo> replyExtendList;

    private UserLevelVo userLevel;

    private boolean allowEdit;

    private boolean allowDelete;
}
