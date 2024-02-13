package com.coin.web.controller.client;

import com.coin.req.search.SearchReq;
import com.coin.resp.MyResp;
import com.coin.resp.search.*;
import com.coin.service.*;
import com.coin.service.constant.CodeCons;
import com.coin.service.exception.BizException;
import com.coin.service.util.ParamUtil;
import com.coin.web.annotation.CommonSecure;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

// 10_搜索和setting
@RestController
@RequestMapping("/search")
public class SearchController {

    private static final Logger logger = LoggerFactory.getLogger(SearchController.class);

    @Resource
    private DictService dictService;

    @Resource
    private ThreadsService threadsService;

    @Resource
    private ReportsService reportsService;

    @Resource
    private ArticlesService articlesService;

    @Resource
    private UsersService usersService;

    // 01_setting
    @PostMapping("/setting")
    @CommonSecure(needLogin = false)
    public MyResp<SettingVo> setting(@RequestBody SearchReq req) {
        logger.info("search-setting-req={}", req);
        try {
            SettingVo settingVo = dictService.getSetting();
            return new MyResp<>(CodeCons.SUCCESS, "", settingVo);
        } catch (BizException e) {
            logger.error("search-setting-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("search-setting-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "查询失败");
    }

    // 02_搜索帖子
    @PostMapping("/forum")
    @CommonSecure(needLogin = false)
    public MyResp<PageInfo<SearchForumVo>> forumSearch(@RequestBody SearchReq req) {
        logger.info("search-forum-req={}", req);
        try {
            ParamUtil.check(req.getPage(), "page", req.getPagesize(), "pageSize", req.getKeyword(), "keyword");
            PageInfo<SearchForumVo> pageInfo = threadsService.searchForum(req);
            return new MyResp<>(CodeCons.SUCCESS, "", pageInfo);
        } catch (BizException e) {
            logger.error("search-forum-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("search-forum-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "查询失败");
    }

    // 03_搜索老哥帮手
    @PostMapping("/report")
    @CommonSecure(needLogin = false)
    public MyResp<PageInfo<SearchReportVo>> reportSearch(@RequestBody SearchReq req) {
        logger.info("search-report-req={}", req);
        try {
            ParamUtil.check(req.getPage(), "page", req.getPagesize(), "pageSize", req.getKeyword(), "keyword");
            PageInfo<SearchReportVo> pageInfo = reportsService.searchReport(req);
            return new MyResp<>(CodeCons.SUCCESS, "", pageInfo);
        } catch (BizException e) {
            logger.error("search-report-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("search-report-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "查询失败");
    }

    // 04_搜索狗庄揭秘
    @PostMapping("/article")
    @CommonSecure(needLogin = false)
    public MyResp<PageInfo<SearchArticleVo>> articleSearch(@RequestBody SearchReq req) {
        logger.info("search-article-req={}", req);
        try {
            ParamUtil.check(req.getPage(), "page", req.getPagesize(), "pageSize", req.getKeyword(), "keyword");
            PageInfo<SearchArticleVo> pageInfo = articlesService.searchArticle(req);
            return new MyResp<>(CodeCons.SUCCESS, "", pageInfo);
        } catch (BizException e) {
            logger.error("search-article-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("search-article-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "查询失败");
    }

    // 05_搜索用户
    @PostMapping("/user")
    @CommonSecure(needLogin = false)
    public MyResp<PageInfo<SearchUserVo>> userSearch(@RequestBody SearchReq req) {
        logger.info("search-user-req={}", req);
        try {
            ParamUtil.check(req.getPage(), "page", req.getPagesize(), "pageSize", req.getKeyword(), "keyword");
            PageInfo<SearchUserVo> pageInfo = usersService.searchUser(req);
            return new MyResp<>(CodeCons.SUCCESS, "", pageInfo);
        } catch (BizException e) {
            logger.error("search-user-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("search-user-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "查询失败");
    }
}