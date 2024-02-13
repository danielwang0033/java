package com.coin.web.controller;

import com.coin.entity.ReportReasons;
import com.coin.req.ReportReasonsReq;
import com.coin.resp.MyResp;
import com.coin.service.ReportReasonsService;
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
@RequestMapping("/reportReasons")
public class ReportReasonsController {

    private static final Logger logger = LoggerFactory.getLogger(ReportReasonsController.class);

    @Resource
    private ReportReasonsService reportReasonsService;

    @PostMapping("/mgr/add")
    @OfficeSecure
    public MyResp<String> mgrAdd(@RequestBody ReportReasonsReq req) {
        logger.info("mgr-reportReasons-add-req={}", req);
        try {
            ParamUtil.check(req.getReason(), "reason", req.getColor(), "color");
            reportReasonsService.add(req);
            return new MyResp<>(CodeCons.SUCCESS, "添加成功");
        } catch (BizException e) {
            logger.error("mgr-reportReasons-add-be:", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("mgr-reportReasons-add-error:", e);
        }
        return new MyResp<>(CodeCons.ERROR, "添加失败");
    }

    @PostMapping("/mgr/del")
    @OfficeSecure
    public MyResp<String> mgrDel(@RequestBody ReportReasonsReq req) {
        logger.info("mgr-reportReasons-del-req={}", req);
        try {
            ParamUtil.check(req.getId(), "ID");
            reportReasonsService.delete(req);
            return new MyResp<>(CodeCons.SUCCESS, "删除成功");
        } catch (BizException e) {
            logger.error("mgr-reportReasons-del-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("mgr-reportReasons-del-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "删除失败");
    }

    @PostMapping("/mgr/update")
    @OfficeSecure
    public MyResp<String> mgrUpdate(@RequestBody ReportReasonsReq req) {
        logger.info("mgr-reportReasons-update-req={}", req);
        try {
            ParamUtil.check(req.getId(), "ID");
            reportReasonsService.update(req);
            return new MyResp<>(CodeCons.SUCCESS, "修改成功");
        } catch (BizException e) {
            logger.error("mgr-reportReasons-update-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("mgr-reportReasons-update-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "更新失败");
    }

    @PostMapping("/mgr/pageList")
    @OfficeSecure
    public MyResp<PageInfo<ReportReasons>> mgrPageList(@RequestBody ReportReasonsReq req) {
        logger.info("mgr-reportReasons-pageList-req={}", req);
        try {
            ParamUtil.check(req.getPage(), "page", req.getPagesize(), "pageSize");
            PageInfo<ReportReasons> page = reportReasonsService.pageList(req);
            return new MyResp<>(CodeCons.SUCCESS, "", page);
        } catch (BizException e) {
            logger.error("mgr-reportReasons-pageList-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("mgr-reportReasons-pageList-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "查询失败");
    }
}