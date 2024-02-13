package com.coin.resp.fav;

import lombok.Data;

@Data
public class FavVo {

    // 收藏状态
    private boolean favStatus;

    private Long favCount;
}
