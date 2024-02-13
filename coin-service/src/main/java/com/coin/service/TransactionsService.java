package com.coin.service;

import com.coin.entity.Transactions;
import com.coin.req.TransactionsReq;
import com.coin.req.UsersReq;
import com.coin.resp.TransactionsResp;
import com.github.pagehelper.PageInfo;

public interface TransactionsService {

    void add(TransactionsReq req);

    Transactions getById(Long id);

    PageInfo<Transactions> pageList(TransactionsReq req);

    PageInfo<TransactionsResp> getBobiLogList(UsersReq req);
}
