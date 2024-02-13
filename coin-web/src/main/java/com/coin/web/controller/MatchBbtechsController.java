package com.coin.web.controller;

import com.coin.entity.MatchBbtechs;
import com.coin.req.MatchBbtechsReq;
import com.coin.resp.MyResp;
import com.coin.service.MatchBbtechsService;
import com.coin.service.constant.CodeCons;
import com.coin.service.exception.BizException;
import com.coin.service.util.ParamUtil;
import com.coin.web.annotation.OfficeSecure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/matchBbtechs")
public class MatchBbtechsController {

    private static final Logger logger = LoggerFactory.getLogger(MatchBbtechsController.class);

    @Resource
    private MatchBbtechsService matchBbtechsService;

    @PostMapping("/mgr/getByMatchId")
    @OfficeSecure
    public MyResp<MatchBbtechs> getByMatchId(@RequestBody MatchBbtechsReq req) {
        logger.info("mgr-getByMatchId-getById-req={}", req);
        try {
            ParamUtil.check(req.getMatchId(), "matchId");
            MatchBbtechs rsp = matchBbtechsService.getByMatchId(req.getMatchId());
            return new MyResp<>(CodeCons.SUCCESS, "", rsp);
        } catch (BizException e) {
            logger.error("mgr-matchBbtechs-getByMatchId-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("mgr-matchBbtechs-getByMatchId-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "查询失败");
    }
}