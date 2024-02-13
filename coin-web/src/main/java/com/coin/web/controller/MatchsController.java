package com.coin.web.controller;

import com.coin.entity.Matchs;
import com.coin.req.MatchsReq;
import com.coin.resp.MatchsResp;
import com.coin.resp.MyResp;
import com.coin.service.MatchsService;
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
@RequestMapping("/matchs")
public class MatchsController {

    private static final Logger logger = LoggerFactory.getLogger(MatchsController.class);

    @Resource
    private MatchsService matchsService;

    @PostMapping("/mgr/del")
    @OfficeSecure
    public MyResp<String> mgrDel(@RequestBody MatchsReq req) {
        logger.info("mgr-matchs-del-req={}", req);
        try {
            ParamUtil.check(req.getId(), "ID");
            matchsService.delete(req);
            return new MyResp<>(CodeCons.SUCCESS, "删除成功");
        } catch (BizException e) {
            logger.error("mgr-matchs-del-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("mgr-matchs-del-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "删除失败");
    }

    @PostMapping("/mgr/update")
    @OfficeSecure
    public MyResp<String> mgrUpdate(@RequestBody MatchsReq req) {
        logger.info("mgr-matchs-update-req={}", req);
        try {
            ParamUtil.check(req.getMatchId(), "matchId");
            matchsService.update(req);
            return new MyResp<>(CodeCons.SUCCESS, "修改成功");
        } catch (BizException e) {
            logger.error("mgr-matchs-update-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("mgr-matchs-update-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "更新失败");
    }

    @PostMapping("/mgr/getById")
    @OfficeSecure
    public MyResp<Matchs> mgrGetById(@RequestBody MatchsReq req) {
        logger.info("mgr-matchs-getById-req={}", req);
        try {
            ParamUtil.check(req.getId(), "ID");
            Matchs rsp = matchsService.getById(req.getId());
            return new MyResp<>(CodeCons.SUCCESS, "", rsp);
        } catch (BizException e) {
            logger.error("mgr-matchs-getById-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("mgr-matchs-getById-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "查询失败");
    }

    @PostMapping("/mgr/pageList")
    @OfficeSecure
    public MyResp<PageInfo<MatchsResp>> mgrPageList(@RequestBody MatchsReq req) {
        logger.info("mgr-matchs-pageList-req={}", req);
        try {
            ParamUtil.check(req.getPage(), "page", req.getPagesize(), "pageSize");
            PageInfo<MatchsResp> page = matchsService.pageList(req);
            return new MyResp<>(CodeCons.SUCCESS, "", page);
        } catch (BizException e) {
            logger.error("mgr-matchs-pageList-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("mgr-matchs-pageList-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "查询失败");
    }
}