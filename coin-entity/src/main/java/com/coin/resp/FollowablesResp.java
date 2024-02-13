package com.coin.resp;

import lombok.Data;

import java.util.Date;

@Data
public class FollowablesResp {

    private Long id;

    // user_id
    private Long userId;
    private String followableType;
    private Long followableId;
    private Date acceptedAt;
    private Date createdAt;
    private Date updatedAt;
}
