package com.coin.web.controller;

import com.coin.req.ForumSubNavsReq;
import com.coin.resp.MyResp;
import com.coin.resp.thread.ForumSubNavsVo;
import com.coin.service.ForumSubNavsService;
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

/**
 * 论坛二级导航
 */
@RestController
@RequestMapping("/forumSubNavs")
public class ForumSubNavsController {

    private static final Logger logger = LoggerFactory.getLogger(ForumSubNavsController.class);

    @Resource
    private ForumSubNavsService forumSubNavsService;

    @PostMapping("/mgr/add")
    @OfficeSecure
    public MyResp<String> mgrAdd(@RequestBody ForumSubNavsReq req) {
        logger.info("mgr-forumSubNavs-add-req={}", req);
        try {
            ParamUtil.check(req.getName(), "name", req.getForumId(), "forumId", req.getTopics(), "topics",
                    req.getStatus(), "status");
            forumSubNavsService.add(req);
            return new MyResp<>(CodeCons.SUCCESS, "添加成功");
        } catch (BizException e) {
            logger.error("mgr-forumSubNavs-add-be:", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("mgr-forumSubNavs-add-error:", e);
        }
        return new MyResp<>(CodeCons.ERROR, "添加失败");
    }

    @PostMapping("/mgr/del")
    @OfficeSecure
    public MyResp<String> mgrDel(@RequestBody ForumSubNavsReq req) {
        logger.info("mgr-forumSubNavs-del-req={}", req);
        try {
            ParamUtil.check(req.getId(), "ID");
            forumSubNavsService.delete(req);
            return new MyResp<>(CodeCons.SUCCESS, "删除成功");
        } catch (BizException e) {
            logger.error("mgr-forumSubNavs-del-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("mgr-forumSubNavs-del-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "删除失败");
    }

    @PostMapping("/mgr/update")
    @OfficeSecure
    public MyResp<String> mgrUpdate(@RequestBody ForumSubNavsReq req) {
        logger.info("mgr-forumSubNavs-update-req={}", req);
        try {
            ParamUtil.check(req.getId(), "ID");
            forumSubNavsService.update(req);
            return new MyResp<>(CodeCons.SUCCESS, "修改成功");
        } catch (BizException e) {
            logger.error("mgr-forumSubNavs-update-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("mgr-forumSubNavs-update-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "更新失败");
    }

    @PostMapping("/mgr/pageList")
    @OfficeSecure
    public MyResp<PageInfo<ForumSubNavsVo>> mgrPageList(@RequestBody ForumSubNavsReq req) {
        logger.info("mgr-forumSubNavs-pageList-req={}", req);
        try {
            ParamUtil.check(req.getPage(), "page", req.getPagesize(), "pageSize");
            PageInfo<ForumSubNavsVo> page = forumSubNavsService.pageList(req);
            return new MyResp<>(CodeCons.SUCCESS, "", page);
        } catch (BizException e) {
            logger.error("mgr-forumSubNavs-pageList-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("mgr-forumSubNavs-pageList-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "查询失败");
    }
}