package com.coin.web.controller;

import com.coin.req.GuessBetReq;
import com.coin.resp.MyResp;
import com.coin.resp.guess.BetResultVo;
import com.coin.resp.guess.BetUserVo;
import com.coin.service.GuessBetService;
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

@RestController
@RequestMapping("/guessBet")
public class GuessBetController {

    private static final Logger logger = LoggerFactory.getLogger(GuessBetController.class);

    @Resource
    private GuessBetService guessBetService;

    // 05_竞猜用户
    @PostMapping("/betUserList")
    @CommonSecure
    public MyResp<PageInfo<BetUserVo>> betUserList(@RequestBody GuessBetReq req) {
        logger.info("guess-betUserList-req={}", req);
        try {
            ParamUtil.check(req.getGuessId(), "guessId", req.getPage(), "page", req.getPagesize(), "pageSize");
            // 类型: 1: 所有投注 2:本人投注
            // 后台默认查询所有投注
            req.setType(1);
            PageInfo<BetUserVo> page = guessBetService.betUserList(req);
            return new MyResp<>(CodeCons.SUCCESS, "", page);
        } catch (BizException e) {
            logger.error("guess-betUserList-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("guess-betUserList-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "查询失败");
    }

    // 06_竞猜结果
    @PostMapping("/betResultList")
    @CommonSecure
    public MyResp<PageInfo<BetResultVo>> betResultList(@RequestBody GuessBetReq req) {
        logger.info("guess-betResultList-req={}", req);
        try {
            ParamUtil.check(req.getGuessId(), "guessId", req.getPage(), "page", req.getPagesize(), "pageSize");
            // 类型: 1:中奖用户 2:未中奖用户 3:我的中奖 4:所有
            req.setAdminFlag(1);
            req.setType(4);
            PageInfo<BetResultVo> page = guessBetService.betResultList(req);
            return new MyResp<>(CodeCons.SUCCESS, "", page);
        } catch (BizException e) {
            logger.error("guess-betResultList-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("guess-betResultList-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "查询失败");
    }
}