package com.coin.service;

import com.coin.req.OrdersReq;
import com.coin.resp.OrdersResp;
import com.github.pagehelper.PageInfo;

public interface OrdersService {

    void add(OrdersReq req);

    void doApproval(OrdersReq req);

    PageInfo<OrdersResp> pageList(OrdersReq req, boolean needDetail);

    PageInfo<OrdersResp> myPageList(OrdersReq req);

    Integer totalByStatus(Long goodId, Integer status);
}
