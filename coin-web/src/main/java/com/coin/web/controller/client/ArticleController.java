package com.coin.web.controller.client;

import com.coin.req.ArticleCommentsReq;
import com.coin.req.ArticlesReq;
import com.coin.req.article.ArticlesVoReq;
import com.coin.resp.MyResp;
import com.coin.resp.article.ArticlesVoResp;
import com.coin.resp.dict.ExtraMsgVo;
import com.coin.resp.fav.FavVo;
import com.coin.resp.like.LikeVo;
import com.coin.service.ArticleCommentsService;
import com.coin.service.ArticlesService;
import com.coin.service.LikesService;
import com.coin.service.constant.CodeCons;
import com.coin.service.exception.BizException;
import com.coin.service.util.ParamUtil;
import com.coin.web.annotation.CommonSecure;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

// 04_狗庄揭秘
@RestController
@RequestMapping("/article")
public class ArticleController {

    private static final Logger logger = LoggerFactory.getLogger(ArticleController.class);

    @Resource
    private ArticlesService articlesService;

    @Resource
    private ArticleCommentsService articleCommentsService;

    @Resource
    private LikesService likesService;

    // 01_文章列表
    @PostMapping("/lists")
    @CommonSecure(needLogin = false)
    public MyResp<PageInfo<ArticlesVoResp>> pageList(@RequestBody ArticlesVoReq req) {
        logger.info("articles-lists-req={}", req);
        try {
            ParamUtil.check(req.getPage(), "page", req.getPagesize(), "pageSize");
            PageInfo<ArticlesVoResp> page = articlesService.lists(req);
            return new MyResp<>(CodeCons.SUCCESS, "", page);
        } catch (BizException e) {
            logger.error("articles-lists-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("articles-lists-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "查询失败");
    }

    // 03_点赞/取消点赞
    @PostMapping("/like/{id}")
    @CommonSecure
    public MyResp<LikeVo> like(@RequestBody ArticlesVoReq req, @PathVariable("id") Long id) {
        logger.info("articles-like-req={}", id);
        try {
            ParamUtil.check(id, "id");
            req.setId(id);
            return new MyResp<>(CodeCons.SUCCESS, "操作成功", articlesService.like(req));
        } catch (BizException e) {
            logger.error("articles-like-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("articles-like-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "操作失败");
    }

    // 04_收藏/取消收藏
    @PostMapping("/favorite/{id}")
    @CommonSecure
    public MyResp<FavVo> favorite(@RequestBody ArticlesVoReq req, @PathVariable("id") Long id) {
        logger.info("articles-favorite-req={}", id);
        try {
            ParamUtil.check(id, "id");
            req.setId(id);
            return new MyResp<>(CodeCons.SUCCESS, "操作成功", articlesService.favorite(req));
        } catch (BizException e) {
            logger.error("articles-favorite-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("articles-favorite-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "操作失败");
    }

    // 06_文章详情
    @PostMapping("/{id}")
    @CommonSecure(needLogin = false)
    public MyResp<ArticlesVoResp> getById(@PathVariable("id") Long id, @RequestBody ArticlesReq req) {
        logger.info("articles-getById-req={}", id);
        try {
            ParamUtil.check(id, "ID");
            req.setId(id);
            ArticlesVoResp rsp = articlesService.getDetailById(req);
            return new MyResp<>(CodeCons.SUCCESS, "", rsp);
        } catch (BizException e) {
            logger.error("articles-getById-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("articles-getById-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "查询失败");
    }

    // 07_添加评论
    @PostMapping("/newComment/{id}")
    @CommonSecure
    public MyResp<ExtraMsgVo> newComment(@PathVariable("id") Long id, @RequestBody ArticleCommentsReq req) {
        logger.info("articles-newComment-req={}", req);
        try {
            ParamUtil.check(id, "ID", req.getContent(), "content");
            req.setArticleId(id);
            ExtraMsgVo extraMsgVo = articleCommentsService.newComment(req);
            return new MyResp<>(CodeCons.SUCCESS, "评论成功", extraMsgVo);
        } catch (BizException e) {
            logger.error("articles-newComment-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("articles-newComment-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "操作失败");
    }
}