package com.coin.service;

import com.coin.entity.UserExpLogs;
import com.coin.req.UserExpLogsReq;
import com.github.pagehelper.PageInfo;

public interface UserExpLogsService {

    void add(UserExpLogsReq req);

    PageInfo<UserExpLogs> pageList(UserExpLogsReq req);

    PageInfo<UserExpLogs> clientPageList(UserExpLogsReq req);
}
