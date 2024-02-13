package com.coin.service;

import com.coin.entity.Transfers;
import com.coin.req.TransfersReq;
import com.github.pagehelper.PageInfo;

public interface TransfersService {

    int add(TransfersReq req);

    int addRollback(long fromId, long toId, long depositId, long withdrawId);

    Transfers getById(Long id);

    PageInfo<Transfers> pageList(TransfersReq req);
}
