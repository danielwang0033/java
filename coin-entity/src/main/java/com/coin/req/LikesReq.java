package com.coin.req;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class LikesReq extends CommonReq {

    // user_id
    private Long userId;

    private String likeableType;

    private Long likeableId;
}
