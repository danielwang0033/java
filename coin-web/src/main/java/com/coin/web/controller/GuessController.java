package com.coin.web.controller;

import cn.hutool.core.collection.CollectionUtil;
import com.coin.entity.Guess;
import com.coin.req.GuessReq;
import com.coin.resp.MyResp;
import com.coin.service.GuessService;
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
@RequestMapping("/guess")
public class GuessController {

    private static final Logger logger = LoggerFactory.getLogger(GuessController.class);

    @Resource
    private GuessService guessService;

    @PostMapping("/mgr/add")
    @OfficeSecure
    public MyResp<String> mgrAdd(@RequestBody GuessReq req) {
        logger.info("mgr-guess-add-req={}", req);
        try {
            ParamUtil.check(req.getTitle(), "title", req.getGuessSubject(), "guessSubject",
                    req.getCampType(), "campType", req.getGuessTypeId(), "guessTypeId", req.getContent(), "content",
                    req.getAdminUserId(), "adminUserId", req.getAdminUserName(), "adminUserName",
                    req.getBeginTime(), "beginTime", req.getEndTime(), "endTime", req.getSecondConfirm(), "secondConfirm"
                    , req.getMatchStartTime(), "matchStartTime");
            if (CollectionUtil.isEmpty(req.getGuessItemList())) {
                return new MyResp<>(CodeCons.ERROR, "请至少添加一个投注项");
            }
            guessService.add(req);
            return new MyResp<>(CodeCons.SUCCESS, "添加成功");
        } catch (BizException e) {
            logger.error("mgr-guess-add-be:", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("mgr-guess-add-error:", e);
        }
        return new MyResp<>(CodeCons.ERROR, "添加失败");
    }

    @PostMapping("/mgr/del")
    @OfficeSecure
    public MyResp<String> mgrDel(@RequestBody GuessReq req) {
        logger.info("mgr-guess-del-req={}", req);
        try {
            ParamUtil.check(req.getId(), "ID");
            guessService.delete(req);
            return new MyResp<>(CodeCons.SUCCESS, "删除成功");
        } catch (BizException e) {
            logger.error("mgr-guess-del-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("mgr-guess-del-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "删除失败");
    }

    @PostMapping("/mgr/update")
    @OfficeSecure
    public MyResp<String> mgrUpdate(@RequestBody GuessReq req) {
        logger.info("mgr-guess-update-req={}", req);
        try {
            ParamUtil.check(req.getId(), "id", req.getTitle(), "title", req.getGuessSubject(), "guessSubject",
                    req.getCampType(), "campType", req.getGuessTypeId(), "guessTypeId", req.getContent(), "content",
                    req.getAdminUserId(), "adminUserId", req.getAdminUserName(), "adminUserName",
                    req.getBeginTime(), "beginTime", req.getEndTime(), "endTime", req.getMatchStartTime(), "matchStartTime");
            if (CollectionUtil.isEmpty(req.getGuessItemList())) {
                return new MyResp<>(CodeCons.ERROR, "请至少添加一个投注项");
            }
            guessService.update(req);
            return new MyResp<>(CodeCons.SUCCESS, "修改成功");
        } catch (BizException e) {
            logger.error("mgr-guess-update-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("mgr-guess-update-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "更新失败");
    }

    @PostMapping("/mgr/updateExtra")
    @OfficeSecure
    public MyResp<String> updateExtra(@RequestBody GuessReq req) {
        logger.info("mgr-guess-updateExtra-req={}", req);
        try {
            ParamUtil.check(req.getId(), "id", req.getVisits(), "visits");
            guessService.updateExtra(req);
            return new MyResp<>(CodeCons.SUCCESS, "修改成功");
        } catch (BizException e) {
            logger.error("mgr-guess-updateExtra-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("mgr-guess-updateExtra-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "更新失败");
    }

    @PostMapping("/mgr/getById")
    @OfficeSecure
    public MyResp<Guess> mgrGetById(@RequestBody GuessReq req) {
        logger.info("mgr-guess-getById-req={}", req);
        try {
            ParamUtil.check(req.getId(), "ID");
            Guess rsp = guessService.getById(req.getId());
            return new MyResp<>(CodeCons.SUCCESS, "", rsp);
        } catch (BizException e) {
            logger.error("mgr-guess-getById-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("mgr-guess-getById-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "查询失败");
    }

    @PostMapping("/mgr/pageList")
    @OfficeSecure
    public MyResp<PageInfo<Guess>> mgrPageList(@RequestBody GuessReq req) {
        logger.info("mgr-guess-pageList-req={}", req);
        try {
            ParamUtil.check(req.getPage(), "page", req.getPagesize(), "pageSize");
            PageInfo<Guess> page = guessService.pageList(req);
            return new MyResp<>(CodeCons.SUCCESS, "", page);
        } catch (BizException e) {
            logger.error("mgr-guess-pageList-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("mgr-guess-pageList-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "查询失败");
    }
}