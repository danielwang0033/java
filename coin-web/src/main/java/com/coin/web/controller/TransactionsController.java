package com.coin.web.controller;

import com.coin.entity.Transactions;
import com.coin.req.TransactionsReq;
import com.coin.resp.MyResp;
import com.coin.service.TransactionsService;
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
@RequestMapping("/transactions")
public class TransactionsController {

    private static final Logger logger = LoggerFactory.getLogger(TransactionsController.class);

    @Resource
    private TransactionsService transactionsService;

    @PostMapping("/mgr/pageList")
    @OfficeSecure
    public MyResp<PageInfo<Transactions>> mgrPageList(@RequestBody TransactionsReq req) {
        logger.info("mgr-transactions-pageList-req={}", req);
        try {
            ParamUtil.check(req.getUserId(), "userId", req.getPage(), "page", req.getPagesize(), "pageSize");
            PageInfo<Transactions> page = transactionsService.pageList(req);
            return new MyResp<>(CodeCons.SUCCESS, "", page);
        } catch (BizException e) {
            logger.error("mgr-transactions-pageList-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("mgr-transactions-pageList-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "查询失败");
    }
}