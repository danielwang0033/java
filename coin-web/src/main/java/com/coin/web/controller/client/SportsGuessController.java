package com.coin.web.controller.client;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.coin.req.*;
import com.coin.resp.MyResp;
import com.coin.resp.guess.*;
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
import java.math.BigDecimal;
import java.util.List;

// 07_体育竞猜
@RestController
@RequestMapping("/sportsGuess")
public class SportsGuessController {

    private static final Logger logger = LoggerFactory.getLogger(SportsGuessController.class);

    @Resource
    private GuessService guessService;

    @Resource
    private GuessTypeService guessTypeService;

    @Resource
    private GuessItemService guessItemService;

    @Resource
    private GuessBetService guessBetService;

    @Resource
    private GuessReplyService guessReplyService;

    // 01_竞猜类型列表
    @PostMapping("/guessTypeList")
    @CommonSecure(needLogin = false)
    public MyResp<List<GuessTypeVo>> guessTypeList(GuessTypeReq req) {
        logger.info("guess-guessTypeList-req");
        try {
            List<GuessTypeVo> rsp = guessTypeService.getAvailableList();
            return new MyResp<>(CodeCons.SUCCESS, "", rsp);
        } catch (BizException e) {
            logger.error("guess-guessTypeList-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("guess-guessTypeList-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "查询失败");
    }

    // 02_竞猜列表
    @PostMapping("/guessList")
    @CommonSecure(needLogin = false)
    public MyResp<PageInfo<GuessListVo>> guessList(@RequestBody GuessReq req) {
        logger.info("guess-guessList-req={}", req);
        try {
            ParamUtil.check(req.getPage(), "page", req.getPagesize(), "pageSize");
            if (StrUtil.isBlank(req.getSortType())) {
                req.setSortType("default");
            }
            ParamUtil.limitCheck(req.getSortType(), new String[]{"new", "hot", "default"});
            PageInfo<GuessListVo> page = guessService.guessList(req);
            return new MyResp<>(CodeCons.SUCCESS, "", page);
        } catch (BizException e) {
            logger.error("guess-guessList-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("guess-guessList-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "查询失败");
    }

    // 04_查看详情
    @PostMapping("/detail")
    @CommonSecure(needLogin = false)
    public MyResp<GuessDetailVo> detail(@RequestBody GuessReq req) {
        logger.info("guess-detail-req={}", req);
        try {
            ParamUtil.check(req.getId(), "id");
            GuessDetailVo guessDetail = guessService.detail(req);
            return new MyResp<>(CodeCons.SUCCESS, "", guessDetail);
        } catch (BizException e) {
            logger.error("guess-detail-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("guess-detail-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "查询失败");
    }

    // 05_竞猜用户
    @PostMapping("/betUserList")
    @CommonSecure(needLogin = false)
    public MyResp<PageInfo<BetUserVo>> betUserList(@RequestBody GuessBetReq req) {
        logger.info("guess-betUserList-req={}", req);
        try {
            ParamUtil.check(req.getGuessId(), "guessId", req.getPage(), "page", req.getPagesize(), "pageSize", req.getType(), "type");
            // 类型: 1: 所有投注 2:本人投注
            ParamUtil.limitCheck(req.getType() + "", new String[]{"1", "2"});
            if (req.getType() == 2) {
                if (ObjectUtil.isNull(req.getLoginUserId())) {
                    return new MyResp<>(CodeCons.ERROR, "请先登录");
                }
            }
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
    @CommonSecure(needLogin = false)
    public MyResp<PageInfo<BetResultVo>> betResultList(@RequestBody GuessBetReq req) {
        logger.info("guess-betResultList-req={}", req);
        try {
            ParamUtil.check(req.getGuessId(), "guessId", req.getPage(), "page", req.getPagesize(), "pageSize", req.getType(), "type");
            // 类型: 1:中奖用户 2:未中奖用户 3:我的中奖
            ParamUtil.limitCheck(req.getType() + "", new String[]{"1", "2", "3"});
            if (req.getType() == 3) {
                if (ObjectUtil.isNull(req.getLoginUserId())) {
                    return new MyResp<>(CodeCons.ERROR, "请先登录");
                }
            }
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

    // 07_投注项列表
    @PostMapping("/guessItemList")
    @CommonSecure(needLogin = false)
    public MyResp<List<GuessItemVo>> guessItemList(@RequestBody GuessItemReq req) {
        logger.info("guess-guessItemList-req={}", req);
        try {
            ParamUtil.check(req.getGuessId(), "guessId");
            List<GuessItemVo> itemList = guessItemService.guessItemAvailableList(req.getGuessId());
            return new MyResp<>(CodeCons.SUCCESS, "", itemList);
        } catch (BizException e) {
            logger.error("guess-guessItemList-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("guess-guessItemList-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "查询失败");
    }

    // 08_投注
    @PostMapping("/bet")
    @CommonSecure
    public MyResp<String> bet(@RequestBody GuessBetReq req) {
        logger.info("guess-bet-req={}", req);
        try {
            ParamUtil.check(req.getGuessId(), "guessId", req.getGuessItemId(), "guessItemId", req.getBetAmount(), "betAmount");
            if (BigDecimal.ZERO.compareTo(req.getBetAmount()) >= 0) {
                return new MyResp<>(CodeCons.ERROR, "金额必须为正数");
            }
            guessBetService.bet(req);
            return new MyResp<>(CodeCons.SUCCESS, "投注成功");
        } catch (BizException e) {
            logger.error("guess-bet-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("guess-bet-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "投注失败");
    }

    // 09_评论
    @PostMapping("/reply")
    @CommonSecure
    public MyResp<String> reply(@RequestBody GuessReplyReq req) {
        logger.info("guess-reply-req={}", req);
        try {
            ParamUtil.check(req.getGuessId(), "guessId", req.getContent(), "content");
            guessReplyService.reply(req);
            return new MyResp<>(CodeCons.SUCCESS, "评论成功");
        } catch (BizException e) {
            logger.error("guess-reply-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("guess-reply-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "评论失败");
    }

    // 10_评论列表
    @PostMapping("/replyList")
    @CommonSecure(needLogin = false)
    public MyResp<PageInfo<GuessReplyVo>> replyList(@RequestBody GuessReplyReq req) {
        logger.info("guess-replyList-req={}", req);
        try {
            ParamUtil.check(req.getId(), "id", req.getPage(), "page", req.getPagesize(), "pageSize");
            PageInfo<GuessReplyVo> page = guessReplyService.replyList(req);
            return new MyResp<>(CodeCons.SUCCESS, "", page);
        } catch (BizException e) {
            logger.error("guess-replyList-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("guess-replyList-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "查询失败");
    }

    // 11_点赞_取消点赞
    @PostMapping("/like")
    @CommonSecure
    public MyResp<String> like(@RequestBody GuessReq req) {
        logger.info("guess-like-req={}", req);
        try {
            ParamUtil.check(req.getId(), "id");
            return new MyResp<>(CodeCons.SUCCESS, guessService.like(req));
        } catch (BizException e) {
            logger.error("guess-like-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("guess-like-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "操作失败");
    }

    // 12_收藏_取消收藏
    @PostMapping("/favorite")
    @CommonSecure
    public MyResp<String> favorite(@RequestBody GuessReq req) {
        logger.info("guess-favorite-req={}", req);
        try {
            ParamUtil.check(req.getId(), "id");
            return new MyResp<>(CodeCons.SUCCESS, guessService.favorite(req));
        } catch (BizException e) {
            logger.error("guess-favorite-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("guess-favorite-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "操作失败");
    }

    // 13_我的投注列表
    @PostMapping("/myBetList")
    @CommonSecure
    public MyResp<PageInfo<MyBetListVo>> myBetList(@RequestBody GuessBetReq req) {
        logger.info("guess-myBetList-req={}", req);
        try {
            ParamUtil.check("page", req.getPagesize(), "pageSize");
            PageInfo<MyBetListVo> page = guessBetService.myBetList(req);
            return new MyResp<>(CodeCons.SUCCESS, "", page);
        } catch (BizException e) {
            logger.error("guess-myBetList-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("guess-myBetList-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "查询失败");
    }
}