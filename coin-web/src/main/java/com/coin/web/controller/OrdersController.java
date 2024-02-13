package com.coin.web.controller;

import com.coin.req.OrdersReq;
import com.coin.resp.MyResp;
import com.coin.resp.OrdersResp;
import com.coin.service.OrdersService;
import com.coin.service.constant.CodeCons;
import com.coin.service.exception.BizException;
import com.coin.service.util.ParamUtil;
import com.coin.web.annotation.OfficeSecure;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/orders")
public class OrdersController {

    private static final Logger logger = LoggerFactory.getLogger(OrdersController.class);

    @Resource
    private OrdersService ordersService;

    @PostMapping("/mgr/doApproval")
    @OfficeSecure
    public MyResp<String> doApproval(@RequestBody OrdersReq req) {
        logger.info("mgr-orders-doApproval-req={}", req);
        try {
            ParamUtil.check(req.getId(), "ID", req.getStatus(), "status");
            ordersService.doApproval(req);
            return new MyResp<>(CodeCons.SUCCESS, "审批成功");
        } catch (BizException e) {
            logger.error("mgr-orders-doApproval-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("mgr-orders-doApproval-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "审批失败");
    }

    @PostMapping("/mgr/pageList")
    @OfficeSecure
    public MyResp<PageInfo<OrdersResp>> mgrPageList(@RequestBody OrdersReq req) {
        logger.info("mgr-orders-pageList-req={}", req);
        try {
            ParamUtil.check(req.getPage(), "page", req.getPagesize(), "pageSize");
            PageInfo<OrdersResp> page = ordersService.pageList(req, true);
            return new MyResp<>(CodeCons.SUCCESS, "", page);
        } catch (BizException e) {
            logger.error("mgr-orders-pageList-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("mgr-orders-pageList-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "查询失败");
    }
}