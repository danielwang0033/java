package com.coin.web.controller;

import com.coin.entity.MatchFbtechs;
import com.coin.req.MatchFbtechsReq;
import com.coin.resp.MyResp;
import com.coin.service.MatchFbtechsService;
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
@RequestMapping("/matchFbtechs")
public class MatchFbtechsController {

    private static final Logger logger = LoggerFactory.getLogger(MatchFbtechsController.class);

    @Resource
    private MatchFbtechsService matchFbtechsService;

    @PostMapping("/mgr/getByMatchId")
    @OfficeSecure
    public MyResp<MatchFbtechs> getByMatchId(@RequestBody MatchFbtechsReq req) {
        logger.info("mgr-matchFbtechs-getById-req={}", req);
        try {
            ParamUtil.check(req.getMatchId(), "matchId");
            MatchFbtechs rsp = matchFbtechsService.getByMatchId(req.getMatchId());
            return new MyResp<>(CodeCons.SUCCESS, "", rsp);
        } catch (BizException e) {
            logger.error("mgr-matchFbtechs-getByMatchId-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("mgr-matchFbtechs-getByMatchId-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "查询失败");
    }
}