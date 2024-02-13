package com.coin.web.controller;

import com.coin.entity.ReportNameTags;
import com.coin.req.ReportNameTagsReq;
import com.coin.resp.MyResp;
import com.coin.service.ReportNameTagsService;
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
@RequestMapping("/reportNameTags")
public class ReportNameTagsController {

    private static final Logger logger = LoggerFactory.getLogger(ReportNameTagsController.class);

    @Resource
    private ReportNameTagsService reportNameTagsService;

    @PostMapping("/mgr/add")
    @OfficeSecure
    public MyResp<String> mgrAdd(@RequestBody ReportNameTagsReq req) {
        logger.info("mgr-reportNameTags-add-req={}", req);
        try {
            ParamUtil.check(req.getName(), "name");
            reportNameTagsService.add(req);
            return new MyResp<>(CodeCons.SUCCESS, "添加成功");
        } catch (BizException e) {
            logger.error("mgr-reportNameTags-add-be:", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("mgr-reportNameTags-add-error:", e);
        }
        return new MyResp<>(CodeCons.ERROR, "添加失败");
    }

    @PostMapping("/mgr/del")
    @OfficeSecure
    public MyResp<String> mgrDel(@RequestBody ReportNameTagsReq req) {
        logger.info("mgr-reportNameTags-del-req={}", req);
        try {
            ParamUtil.check(req.getId(), "ID");
            reportNameTagsService.delete(req);
            return new MyResp<>(CodeCons.SUCCESS, "删除成功");
        } catch (BizException e) {
            logger.error("mgr-reportNameTags-del-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("mgr-reportNameTags-del-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "删除失败");
    }

    @PostMapping("/mgr/update")
    @OfficeSecure
    public MyResp<String> mgrUpdate(@RequestBody ReportNameTagsReq req) {
        logger.info("mgr-reportNameTags-update-req={}", req);
        try {
            ParamUtil.check(req.getId(), "ID");
            reportNameTagsService.update(req);
            return new MyResp<>(CodeCons.SUCCESS, "修改成功");
        } catch (BizException e) {
            logger.error("mgr-reportNameTags-update-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("mgr-reportNameTags-update-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "更新失败");
    }

    @PostMapping("/mgr/pageList")
    @OfficeSecure
    public MyResp<PageInfo<ReportNameTags>> mgrPageList(@RequestBody ReportNameTagsReq req) {
        logger.info("mgr-reportNameTags-pageList-req={}", req);
        try {
            ParamUtil.check(req.getPage(), "page", req.getPagesize(), "pageSize");
            PageInfo<ReportNameTags> page = reportNameTagsService.pageList(req);
            return new MyResp<>(CodeCons.SUCCESS, "", page);
        } catch (BizException e) {
            logger.error("mgr-reportNameTags-pageList-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("mgr-reportNameTags-pageList-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "查询失败");
    }
}