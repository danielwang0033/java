package com.coin.resp;

import lombok.Data;

import java.util.Date;

@Data
public class LikesResp {

    private Long id;

    // user_id
    private Long userId;
    private String likeableType;
    private Long likeableId;
    private Date createdAt;
    private Date updatedAt;
}
