package com.coin.web.controller;

import com.coin.entity.UserExpLogs;
import com.coin.req.UserExpLogsReq;
import com.coin.resp.MyResp;
import com.coin.service.UserExpLogsService;
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
@RequestMapping("/userExpLogs")
public class UserExpLogsController {

    private static final Logger logger = LoggerFactory.getLogger(UserExpLogsController.class);

    @Resource
    private UserExpLogsService userExpLogsService;

    @PostMapping("/mgr/pageList")
    @OfficeSecure
    public MyResp<PageInfo<UserExpLogs>> mgrPageList(@RequestBody UserExpLogsReq req) {
        logger.info("mgr-userExpLogs-pageList-req={}", req);
        try {
            ParamUtil.check(req.getUserId(), "userId", req.getPage(), "page", req.getPagesize(), "pageSize");
            PageInfo<UserExpLogs> page = userExpLogsService.pageList(req);
            return new MyResp<>(CodeCons.SUCCESS, "", page);
        } catch (BizException e) {
            logger.error("mgr-userExpLogs-pageList-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("mgr-userExpLogs-pageList-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "查询失败");
    }
}