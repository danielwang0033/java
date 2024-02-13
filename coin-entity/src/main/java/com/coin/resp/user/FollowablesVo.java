package com.coin.resp.user;

import lombok.Data;

@Data
public class FollowablesVo {

    private Long followerCount;
    private Boolean isFollowedByMe;
}
