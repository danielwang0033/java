package com.coin.web.controller;

import com.coin.entity.MatchScores;
import com.coin.req.MatchScoresReq;
import com.coin.resp.MyResp;
import com.coin.service.MatchScoresService;
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
import java.util.List;

@RestController
@RequestMapping("/matchScores")
public class MatchScoresController {

    private static final Logger logger = LoggerFactory.getLogger(MatchScoresController.class);

    @Resource
    private MatchScoresService matchScoresService;

    @PostMapping("/mgr/getByMatchId")
    @OfficeSecure
    public MyResp<List<MatchScores>> getByMatchId(@RequestBody MatchScoresReq req) {
        logger.info("mgr-getByMatchId-getById-req={}", req);
        try {
            ParamUtil.check(req.getMatchId(), "matchId");
            List<MatchScores> rsp = matchScoresService.getByMatchId(req.getMatchId());
            return new MyResp<>(CodeCons.SUCCESS, "", rsp);
        } catch (BizException e) {
            logger.error("mgr-matchScores-getByMatchId-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("mgr-matchScores-getByMatchId-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "查询失败");
    }
}