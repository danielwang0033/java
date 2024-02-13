package com.coin.web.controller;

import com.coin.entity.ArticleGroups;
import com.coin.req.ArticleGroupsReq;
import com.coin.resp.MyResp;
import com.coin.service.ArticleGroupsService;
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
import java.util.List;

@RestController
@RequestMapping("/articleGroups")
public class ArticleGroupsController {

    private static final Logger logger = LoggerFactory.getLogger(ArticleGroupsController.class);

    @Resource
    private ArticleGroupsService articleGroupsService;

    @PostMapping("/mgr/getList")
    @OfficeSecure
    public MyResp<List<ArticleGroups>> getList(@RequestBody ArticleGroupsReq req) {
        logger.info("articleGroups-getList-req={}", req);
        try {
            List<ArticleGroups> list = articleGroupsService.getList(req);
            return new MyResp<>(CodeCons.SUCCESS, "", list);
        } catch (BizException e) {
            logger.error("articleGroups-getList-error", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("articleGroups-getList-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "查询失败");
    }

    @PostMapping("/mgr/pageList")
    @OfficeSecure
    public MyResp<PageInfo<ArticleGroups>> pageList(@RequestBody ArticleGroupsReq req) {
        logger.info("articleGroups-pageList-req={}", req);
        try {
            PageInfo<ArticleGroups> list = articleGroupsService.pageList(req);
            return new MyResp<>(CodeCons.SUCCESS, "", list);
        } catch (BizException e) {
            logger.error("articleGroups-pageList-error", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("articleGroups-pageList-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "查询失败");
    }

    @PostMapping("/mgr/add")
    @OfficeSecure
    public MyResp<String> add(@RequestBody ArticleGroupsReq req) {
        logger.info("articleGroups-add-req={}", req);
        try {
            ParamUtil.check(req.getName(), "name", req.getAlias(), "alias", req.getDescription(), "description");
            articleGroupsService.add(req);
            return new MyResp<>(CodeCons.SUCCESS, "添加成功");
        } catch (BizException e) {
            logger.error("articleGroups-add-error", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("articleGroups-add-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "添加失败");
    }

    @PostMapping("/mgr/update")
    @OfficeSecure
    public MyResp<String> update(@RequestBody ArticleGroupsReq req) {
        logger.info("articleGroups-update-req={}", req);
        try {
            ParamUtil.check(req.getId(), "id");
            articleGroupsService.update(req);
            return new MyResp<>(CodeCons.SUCCESS, "更新成功");
        } catch (BizException e) {
            logger.error("articleGroups-update-error", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("articleGroups-update-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "更新失败");
    }
}