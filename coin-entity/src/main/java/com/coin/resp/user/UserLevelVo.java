package com.coin.resp.user;

import com.coin.cache.UserLevelCache;
import lombok.Data;

@Data
public class UserLevelVo {

    private UserLevelCache userLevel;

    private UserLevelCache nextLevel;
}
