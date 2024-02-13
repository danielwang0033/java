package com.coin.web.controller;

import com.coin.req.ThreadsReq;
import com.coin.resp.MyResp;
import com.coin.resp.thread.ThreadsVo;
import com.coin.service.ThreadsService;
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
@RequestMapping("/threads")
public class ThreadsController {

    private static final Logger logger = LoggerFactory.getLogger(ThreadsController.class);

    @Resource
    private ThreadsService threadsService;

    @PostMapping("/mgr/del")
    @OfficeSecure
    public MyResp<String> mgrDel(@RequestBody ThreadsReq req) {
        logger.info("mgr-threads-del-req={}", req);
        try {
            ParamUtil.check(req.getId(), "ID");
            threadsService.delete(req);
            return new MyResp<>(CodeCons.SUCCESS, "删除成功");
        } catch (BizException e) {
            logger.error("mgr-threads-del-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("mgr-threads-del-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "删除失败");
    }

    @PostMapping("/mgr/update")
    @OfficeSecure
    public MyResp<String> mgrUpdate(@RequestBody ThreadsReq req) {
        logger.info("mgr-threads-update-req={}", req);
        try {
            ParamUtil.check(req.getId(), "ID");
            threadsService.update(req);
            return new MyResp<>(CodeCons.SUCCESS, "修改成功");
        } catch (BizException e) {
            logger.error("mgr-threads-update-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("mgr-threads-update-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "更新失败");
    }

    @PostMapping("/mgr/addReadCount")
    @OfficeSecure
    public MyResp<String> addReadCount(@RequestBody ThreadsReq req) {
        logger.info("mgr-threads-addReadCount-req={}", req);
        try {
            ParamUtil.check(req.getId(), "ID", req.getReadCount(), "readCount");
            threadsService.addReadCount(req);
            return new MyResp<>(CodeCons.SUCCESS, "更新成功");
        } catch (BizException e) {
            logger.error("mgr-threads-addReadCount-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("mgr-threads-addReadCount-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "更新失败");
    }

    @PostMapping("/mgr/addLikeCount")
    @OfficeSecure
    public MyResp<String> addLikeCount(@RequestBody ThreadsReq req) {
        logger.info("mgr-threads-addLikeCount-req={}", req);
        try {
            ParamUtil.check(req.getId(), "ID", req.getIncrLikes(), "likeCount");
            threadsService.addLikeCount(req);
            return new MyResp<>(CodeCons.SUCCESS, "更新成功");
        } catch (BizException e) {
            logger.error("mgr-threads-addLikeCount-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("mgr-threads-addLikeCount-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "更新失败");
    }

    @PostMapping("/mgr/pageList")
    @OfficeSecure
    public MyResp<PageInfo<ThreadsVo>> mgrPageList(@RequestBody ThreadsReq req) {
        logger.info("mgr-threads-pageList-req={}", req);
        try {
            ParamUtil.check(req.getPage(), "page", req.getPagesize(), "pageSize");
            PageInfo<ThreadsVo> page = threadsService.pageList(req);
            return new MyResp<>(CodeCons.SUCCESS, "", page);
        } catch (BizException e) {
            logger.error("mgr-threads-pageList-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("mgr-threads-pageList-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "查询失败");
    }
}