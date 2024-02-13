package com.coin.resp.user;

import lombok.Data;

@Data
public class FollowResultVo {

    private boolean isFollowedByMe;

    private Long followerCount;
}
