package com.coin.web.controller;

import com.coin.req.ArticlesReq;
import com.coin.resp.ArticlesResp;
import com.coin.resp.MyResp;
import com.coin.service.ArticlesService;
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
@RequestMapping("/articles")
public class ArticlesController {

    private static final Logger logger = LoggerFactory.getLogger(ArticlesController.class);

    @Resource
    private ArticlesService articlesService;

    @PostMapping("/mgr/add")
    @OfficeSecure
    public MyResp<String> mgrAdd(@RequestBody ArticlesReq req) {
        logger.info("mgr-articles-add-req={}", req);
        try {
            ParamUtil.check(req.getTitle(), "title", req.getContent(), "content", req.getArtGroupId(),
                    "artGroupId", req.getUserId(), "userId", req.getUserName(), "userName");
            articlesService.add(req);
            return new MyResp<>(CodeCons.SUCCESS, "添加成功");
        } catch (BizException e) {
            logger.error("mgr-articles-add-be:", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("mgr-articles-add-error:", e);
        }
        return new MyResp<>(CodeCons.ERROR, "添加失败");
    }

    @PostMapping("/mgr/addVisits")
    @OfficeSecure
    public MyResp<String> addVisits(@RequestBody ArticlesReq req) {
        logger.info("mgr-articles-addVisits-req={}", req);
        try {
            ParamUtil.check(req.getVisits(), "visits");
            articlesService.addVisits(req);
            return new MyResp<>(CodeCons.SUCCESS, "更新成功");
        } catch (BizException e) {
            logger.error("mgr-articles-addVisits-be:", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("mgr-articles-addVisits-error:", e);
        }
        return new MyResp<>(CodeCons.ERROR, "更新失败");
    }

    @PostMapping("/mgr/addLikes")
    @OfficeSecure
    public MyResp<String> addLikes(@RequestBody ArticlesReq req) {
        logger.info("mgr-articles-addLikes-req={}", req);
        try {
            ParamUtil.check(req.getIncrLikes(), "likes");
            articlesService.addLikeCount(req);
            return new MyResp<>(CodeCons.SUCCESS, "更新成功");
        } catch (BizException e) {
            logger.error("mgr-articles-addLikes-be:", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("mgr-articles-addLikes-error:", e);
        }
        return new MyResp<>(CodeCons.ERROR, "更新失败");
    }

    @PostMapping("/mgr/del")
    @OfficeSecure
    public MyResp<String> mgrDel(@RequestBody ArticlesReq req) {
        logger.info("mgr-articles-del-req={}", req);
        try {
            ParamUtil.check(req.getId(), "ID");
            articlesService.delete(req);
            return new MyResp<>(CodeCons.SUCCESS, "删除成功");
        } catch (BizException e) {
            logger.error("mgr-articles-del-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("mgr-articles-del-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "删除失败");
    }

    @PostMapping("/mgr/update")
    @OfficeSecure
    public MyResp<String> mgrUpdate(@RequestBody ArticlesReq req) {
        logger.info("mgr-articles-update-req={}", req);
        try {
            ParamUtil.check(req.getId(), "ID");
            articlesService.update(req);
            return new MyResp<>(CodeCons.SUCCESS, "修改成功");
        } catch (BizException e) {
            logger.error("mgr-articles-update-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("mgr-articles-update-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "更新失败");
    }

    @PostMapping("/mgr/pageList")
    @OfficeSecure
    public MyResp<PageInfo<ArticlesResp>> mgrPageList(@RequestBody ArticlesReq req) {
        logger.info("mgr-articles-pageList-req={}", req);
        try {
            ParamUtil.check(req.getPage(), "page", req.getPagesize(), "pageSize");
            PageInfo<ArticlesResp> page = articlesService.pageList(req);
            return new MyResp<>(CodeCons.SUCCESS, "", page);
        } catch (BizException e) {
            logger.error("mgr-articles-pageList-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("mgr-articles-pageList-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "查询失败");
    }
}