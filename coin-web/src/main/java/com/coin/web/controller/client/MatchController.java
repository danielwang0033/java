package com.coin.web.controller.client;

import com.coin.req.MatchsReq;
import com.coin.resp.LeaguesResp;
import com.coin.resp.MatchsResp;
import com.coin.resp.MyResp;
import com.coin.service.MatchsService;
import com.coin.service.constant.CodeCons;
import com.coin.service.exception.BizException;
import com.coin.service.util.ParamUtil;
import com.coin.web.annotation.CommonSecure;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

// 04_赛事直播
@RestController
@RequestMapping("/match")
public class MatchController {

    private static final Logger logger = LoggerFactory.getLogger(MatchController.class);

    @Resource
    private MatchsService matchsService;

    // 01_比赛列表
    @PostMapping("/list")
    @CommonSecure(needLogin = false)
    public MyResp<PageInfo<MatchsResp>> pageList(@RequestBody MatchsReq req) {
        logger.info("match-lists-req={}", req);
        try {
            ParamUtil.check(req.getPage(), "page", req.getPagesize(), "pageSize");
            PageInfo<MatchsResp> page = matchsService.matchPageList(req);
            return new MyResp<>(CodeCons.SUCCESS, "", page);
        } catch (BizException e) {
            logger.error("match-lists-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("match-lists-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "查询失败");
    }

    // 02_赛事列表
    @PostMapping("/leagueList")
    @CommonSecure(needLogin = false)
    public MyResp<List<LeaguesResp>> leagueList(@RequestBody MatchsReq req) {
        logger.info("match-lists-req={}", req);
        try {
            List<LeaguesResp> page = matchsService.LeaguePageList(req);
            return new MyResp<>(CodeCons.SUCCESS, "", page);
        } catch (BizException e) {
            logger.error("match-lists-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("match-lists-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "查询失败");
    }

    // 03_比赛详情
    @PostMapping("/{id}")
    @CommonSecure(needLogin = false)
    public MyResp<MatchsResp> getById(@RequestBody MatchsReq req, @PathVariable("id") String id) {
        logger.info("match-getById-req={}", id);
        try {
            ParamUtil.check(id, "ID");
            MatchsResp rsp = matchsService.getDetailByMatchId(id);
            return new MyResp<>(CodeCons.SUCCESS, "", rsp);
        } catch (BizException e) {
            logger.error("match-getById-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("match-getById-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "查询失败");
    }
}