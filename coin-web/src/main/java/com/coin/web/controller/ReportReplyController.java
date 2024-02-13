package com.coin.web.controller;

import com.coin.req.ReportReplyReq;
import com.coin.resp.MyResp;
import com.coin.resp.ReportReplyResp;
import com.coin.service.ReportReplyService;
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
@RequestMapping("/reportReply")
public class ReportReplyController {

    private static final Logger logger = LoggerFactory.getLogger(ReportReplyController.class);

    @Resource
    private ReportReplyService reportReplyService;

    @PostMapping("/mgr/add")
    @OfficeSecure
    public MyResp<String> mgrAdd(@RequestBody ReportReplyReq req) {
        logger.info("mgr-reportReply-add-req={}", req);
        try {
            ParamUtil.check(req.getContent(), "content", req.getUserId(),
                    "userId", req.getReportId(), "reportId");
            reportReplyService.add(req);
            return new MyResp<>(CodeCons.SUCCESS, "添加成功");
        } catch (BizException e) {
            logger.error("mgr-reportReply-add-be:", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("mgr-reportReply-add-error:", e);
        }
        return new MyResp<>(CodeCons.ERROR, "添加失败");
    }

    @PostMapping("/mgr/del")
    @OfficeSecure
    public MyResp<String> mgrDel(@RequestBody ReportReplyReq req) {
        logger.info("mgr-reportReply-del-req={}", req);
        try {
            ParamUtil.check(req.getId(), "ID");
            reportReplyService.delete(req);
            return new MyResp<>(CodeCons.SUCCESS, "删除成功");
        } catch (BizException e) {
            logger.error("mgr-reportReply-del-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("mgr-reportReply-del-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "删除失败");
    }

    @PostMapping("/mgr/update")
    @OfficeSecure
    public MyResp<String> mgrUpdate(@RequestBody ReportReplyReq req) {
        logger.info("mgr-reportReply-update-req={}", req);
        try {
            ParamUtil.check(req.getId(), "ID", req.getContent(), "content");
            reportReplyService.update(req);
            return new MyResp<>(CodeCons.SUCCESS, "修改成功");
        } catch (BizException e) {
            logger.error("mgr-reportReply-update-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("mgr-reportReply-update-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "更新失败");
    }

    @PostMapping("/mgr/getById")
    @OfficeSecure
    public MyResp<ReportReplyResp> mgrGetById(@RequestBody ReportReplyReq req) {
        logger.info("mgr-reportReply-getById-req={}", req);
        try {
            ParamUtil.check(req.getId(), "ID");
            ReportReplyResp rsp = reportReplyService.getById(req.getId());
            return new MyResp<>(CodeCons.SUCCESS, "", rsp);
        } catch (BizException e) {
            logger.error("mgr-reportReply-getById-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("mgr-reportReply-getById-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "查询失败");
    }

    @PostMapping("/mgr/pageList")
    @OfficeSecure
    public MyResp<PageInfo<ReportReplyResp>> mgrPageList(@RequestBody ReportReplyReq req) {
        logger.info("mgr-reportReply-pageList-req={}", req);
        try {
            ParamUtil.check(req.getPage(), "page", req.getPagesize(), "pageSize", req.getReportId(), "reportId");
            PageInfo<ReportReplyResp> page = reportReplyService.pageList(req);
            return new MyResp<>(CodeCons.SUCCESS, "", page);
        } catch (BizException e) {
            logger.error("mgr-reportReply-pageList-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("mgr-reportReply-pageList-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "查询失败");
    }
}