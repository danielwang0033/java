package com.coin.service;

import com.coin.entity.UserLevel;
import com.coin.req.UserLevelReq;
import com.coin.resp.user.UserLevelVo;
import com.github.pagehelper.PageInfo;

public interface UserLevelService {

    PageInfo<UserLevel> pageList(UserLevelReq req);

    void add(UserLevelReq req);

    void update(UserLevelReq req);

    void del(UserLevelReq req);

    UserLevelVo matchLevelByExp(Integer exp);
}
