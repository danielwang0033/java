package com.coin.web.controller;

import com.coin.entity.ArticleComments;
import com.coin.req.ArticleCommentsReq;
import com.coin.resp.MyResp;
import com.coin.service.ArticleCommentsService;
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
 * 文章评论
 */
@RestController
@RequestMapping("/articleComments")
public class ArticleCommentsController {

    private static final Logger logger = LoggerFactory.getLogger(ArticleCommentsController.class);

    @Resource
    private ArticleCommentsService articleCommentsService;

    @PostMapping("/mgr/del")
    @OfficeSecure
    public MyResp<String> mgrDel(@RequestBody ArticleCommentsReq req) {
        logger.info("mgr-articleComments-del-req={}", req);
        try {
            ParamUtil.check(req.getId(), "ID");
            articleCommentsService.delete(req);
            return new MyResp<>(CodeCons.SUCCESS, "删除成功");
        } catch (BizException e) {
            logger.error("mgr-articleComments-del-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("mgr-articleComments-del-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "删除失败");
    }

    @PostMapping("/mgr/pageList")
    @OfficeSecure
    public MyResp<PageInfo<ArticleComments>> mgrPageList(@RequestBody ArticleCommentsReq req) {
        logger.info("mgr-articleComments-pageList-req={}", req);
        try {
            ParamUtil.check(req.getPage(), "page", req.getPagesize(), "pageSize");
            PageInfo<ArticleComments> page = articleCommentsService.pageList(req);
            return new MyResp<>(CodeCons.SUCCESS, "", page);
        } catch (BizException e) {
            logger.error("mgr-articleComments-pageList-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("mgr-articleComments-pageList-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "查询失败");
    }
}