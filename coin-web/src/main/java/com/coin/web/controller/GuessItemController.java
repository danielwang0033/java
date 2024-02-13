package com.coin.web.controller;

import com.coin.entity.GuessItem;
import com.coin.req.GuessItemReq;
import com.coin.resp.MyResp;
import com.coin.service.GuessItemService;
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
@RequestMapping("/guessItem")
public class GuessItemController {

    private static final Logger logger = LoggerFactory.getLogger(GuessItemController.class);

    @Resource
    private GuessItemService guessItemService;

    @PostMapping("/mgr/add")
    @OfficeSecure
    public MyResp<String> mgrAdd(@RequestBody GuessItemReq req) {
        logger.info("mgr-guessItem-add-req={}", req);
        try {
            ParamUtil.check(req.getGuessId(), "guessId", req.getItemName(), "itemName",
                    req.getItemOdds(), "itemOdds");
            guessItemService.add(req);
            return new MyResp<>(CodeCons.SUCCESS, "添加成功");
        } catch (BizException e) {
            logger.error("mgr-guessItem-add-be:", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("mgr-guessItem-add-error:", e);
        }
        return new MyResp<>(CodeCons.ERROR, "添加失败");
    }

    @PostMapping("/mgr/del")
    @OfficeSecure
    public MyResp<String> mgrDel(@RequestBody GuessItemReq req) {
        logger.info("mgr-guessItem-del-req={}", req);
        try {
            ParamUtil.check(req.getId(), "ID");
            guessItemService.delete(req);
            return new MyResp<>(CodeCons.SUCCESS, "删除成功");
        } catch (BizException e) {
            logger.error("mgr-guessItem-del-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("mgr-guessItem-del-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "删除失败");
    }

    @PostMapping("/mgr/update")
    @OfficeSecure
    public MyResp<String> mgrUpdate(@RequestBody GuessItemReq req) {
        logger.info("mgr-guessItem-update-req={}", req);
        try {
            ParamUtil.check(req.getId(), "ID");
            guessItemService.update(req);
            return new MyResp<>(CodeCons.SUCCESS, "修改成功");
        } catch (BizException e) {
            logger.error("mgr-guessItem-update-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("mgr-guessItem-update-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "更新失败");
    }

    @PostMapping("/mgr/getById")
    @OfficeSecure
    public MyResp<GuessItem> mgrGetById(@RequestBody GuessItemReq req) {
        logger.info("mgr-guessItem-getById-req={}", req);
        try {
            ParamUtil.check(req.getId(), "ID");
            GuessItem rsp = guessItemService.getById(req.getId());
            return new MyResp<>(CodeCons.SUCCESS, "", rsp);
        } catch (BizException e) {
            logger.error("mgr-guessItem-getById-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("mgr-guessItem-getById-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "查询失败");
    }

    @PostMapping("/mgr/itemList")
    @OfficeSecure
    public MyResp<PageInfo<GuessItem>> mgrItemList(@RequestBody GuessItemReq req) {
        logger.info("mgr-guessItem-mgrItemList-req={}", req);
        try {
            ParamUtil.check(req.getPage(), "page", req.getPagesize(), "pageSize", req.getGuessId(), "guessId");
            PageInfo<GuessItem> page = guessItemService.itemList(req);
            return new MyResp<>(CodeCons.SUCCESS, "", page);
        } catch (BizException e) {
            logger.error("mgr-guessItem-mgrItemList-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("mgr-guessItem-mgrItemList-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "查询失败");
    }

    // 计算中奖金额
    @PostMapping("/mgr/calcAward")
    @OfficeSecure
    public MyResp<String> calcAward(@RequestBody GuessItemReq req) {
        logger.info("guess-calcAward-req={}", req);
        try {
            ParamUtil.check(req.getId(), "id", req.getGuessId(), "guessId", req.getSettlePattern(), "settlePattern");
            ParamUtil.limitCheck(req.getSettlePattern() + "", new String[]{"1", "2", "3", "4", "5"});
            guessItemService.calcAward(req);
            return new MyResp<>(CodeCons.SUCCESS, "中奖金额计算完成");
        } catch (BizException e) {
            logger.error("guess-calcAward-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("guess-calcAward-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "中奖金额计算失败");
    }

    // 撤销金额计算
    @PostMapping("/mgr/calcAwardRevoke")
    @OfficeSecure
    public MyResp<String> calcAwardRevoke(@RequestBody GuessItemReq req) {
        logger.info("guess-calcAwardRevoke-req={}", req);
        try {
            ParamUtil.check(req.getId(), "id", req.getGuessId(), "guessId");
            guessItemService.calcAwardRevoke(req);
            return new MyResp<>(CodeCons.SUCCESS, "中奖金额计算撤销完成");
        } catch (BizException e) {
            logger.error("guess-calcAwardRevoke-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("guess-calcAwardRevoke-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "中奖金额计算撤销失败");
    }

    // 投注结算
    @PostMapping("/mgr/settle")
    @OfficeSecure
    public MyResp<String> settle(@RequestBody GuessItemReq req) {
        logger.info("guess-settle-req={}", req);
        try {
            ParamUtil.check(req.getId(), "id", req.getGuessId(), "guessId", req.getSecondConfirm(), "secondConfirm");
            guessItemService.settle(req);
            return new MyResp<>(CodeCons.SUCCESS, "结算完成");
        } catch (BizException e) {
            logger.error("guess-settle-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("guess-settle-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "结算失败");
    }

    // 一键结算
    @PostMapping("/mgr/settleFast")
    @OfficeSecure
    public MyResp<String> settleFast(@RequestBody GuessItemReq req) {
        logger.info("guess-settle-req={}", req);
        try {
            ParamUtil.check(req.getId(), "id", req.getGuessId(), "guessId", req.getSecondConfirm(), "secondConfirm", req.getSettlePattern());
            guessItemService.settleFast(req);
            return new MyResp<>(CodeCons.SUCCESS, "结算完成");
        } catch (BizException e) {
            logger.error("guess-settleFast-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("guess-settleFast-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "结算失败");
    }
}