package com.coin.web.controller;

import com.coin.entity.MatchLeagues;
import com.coin.req.MatchLeaguesReq;
import com.coin.resp.MyResp;
import com.coin.service.MatchLeaguesService;
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
@RequestMapping("/matchLeagues")
public class MatchLeaguesController {

    private static final Logger logger = LoggerFactory.getLogger(MatchLeaguesController.class);

    @Resource
    private MatchLeaguesService matchLeaguesService;

    @PostMapping("/mgr/del")
    @OfficeSecure
    public MyResp<String> mgrDel(@RequestBody MatchLeaguesReq req) {
        logger.info("mgr-matchLeagues-del-req={}", req);
        try {
            ParamUtil.check(req.getId(), "ID");
            matchLeaguesService.delete(req);
            return new MyResp<>(CodeCons.SUCCESS, "删除成功");
        } catch (BizException e) {
            logger.error("mgr-matchLeagues-del-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("mgr-matchLeagues-del-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "删除失败");
    }

    @PostMapping("/mgr/update")
    @OfficeSecure
    public MyResp<String> mgrUpdate(@RequestBody MatchLeaguesReq req) {
        logger.info("mgr-matchLeagues-update-req={}", req);
        try {
            ParamUtil.check(req.getId(), "ID");
            matchLeaguesService.update(req);
            return new MyResp<>(CodeCons.SUCCESS, "修改成功");
        } catch (BizException e) {
            logger.error("mgr-matchLeagues-update-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("mgr-matchLeagues-update-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "更新失败");
    }

    @PostMapping("/mgr/pageList")
    @OfficeSecure
    public MyResp<PageInfo<MatchLeagues>> mgrPageList(@RequestBody MatchLeaguesReq req) {
        logger.info("mgr-matchLeagues-pageList-req={}", req);
        try {
            ParamUtil.check(req.getPage(), "page", req.getPagesize(), "pageSize");
            PageInfo<MatchLeagues> page = matchLeaguesService.pageList(req);
            return new MyResp<>(CodeCons.SUCCESS, "", page);
        } catch (BizException e) {
            logger.error("mgr-matchLeagues-pageList-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("mgr-matchLeagues-pageList-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "查询失败");
    }
}