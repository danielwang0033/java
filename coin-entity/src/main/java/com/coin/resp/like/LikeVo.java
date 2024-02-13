package com.coin.resp.like;

import lombok.Data;

@Data
public class LikeVo {

    // 点赞状态
    private boolean likeStatus;

    private Long likeCount;
}
