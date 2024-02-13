package com.coin.resp;

import lombok.Data;

import java.util.Date;

@Data
public class FavoritesResp {

    private Long id;

    // user_id
    private Long userId;
    private String favoriteableType;
    private Long favoriteableId;
    private Date createdAt;
    private Date updatedAt;
}
