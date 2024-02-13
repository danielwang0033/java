package com.coin.web.controller;

import com.coin.req.ReplyExtendReq;
import com.coin.resp.MyResp;
import com.coin.resp.reply.ReplyExtendMgrVo;
import com.coin.service.ReplyExtendService;
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
@RequestMapping("/replyExtend")
public class ReplyExtendController {

    private static final Logger logger = LoggerFactory.getLogger(ReplyExtendController.class);

    @Resource
    private ReplyExtendService replyExtendService;

    @PostMapping("/mgr/pageList")
    @OfficeSecure
    public MyResp<PageInfo<ReplyExtendMgrVo>> mgrPageList(@RequestBody ReplyExtendReq req) {
        logger.info("mgr-replyExtend-pageList-req={}", req);
        try {
            ParamUtil.check(req.getPage(), "page", req.getPagesize(), "pageSize",
                    req.getTowerType(), "towerType", req.getTowerId(), "towerId", req.getFloorId(), "floorId");
            PageInfo<ReplyExtendMgrVo> page = replyExtendService.pageList(req);
            return new MyResp<>(CodeCons.SUCCESS, "", page);
        } catch (BizException e) {
            logger.error("mgr-replyExtend-pageList-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("mgr-replyExtend-pageList-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "查询失败");
    }
}