package com.coin.req;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class FavoritesReq extends CommonReq {

    // user_id
    private Long userId;

    private String favoriteableType;

    private Long favoriteableId;
}
