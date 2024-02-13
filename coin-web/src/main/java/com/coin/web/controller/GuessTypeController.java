package com.coin.web.controller;

import com.coin.entity.GuessType;
import com.coin.req.GuessTypeReq;
import com.coin.resp.MyResp;
import com.coin.service.GuessTypeService;
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
@RequestMapping("/guessType")
public class GuessTypeController {

    private static final Logger logger = LoggerFactory.getLogger(GuessTypeController.class);

    @Resource
    private GuessTypeService guessTypeService;

    @PostMapping("/mgr/add")
    @OfficeSecure
    public MyResp<String> mgrAdd(@RequestBody GuessTypeReq req) {
        logger.info("mgr-guessType-add-req={}", req);
        try {
            ParamUtil.check(req.getName(), "name", req.getAlias(), "alias",
                    req.getColor(), "color"/*, req.getImage(), "image"*/);
            guessTypeService.add(req);
            return new MyResp<>(CodeCons.SUCCESS, "添加成功");
        } catch (BizException e) {
            logger.error("mgr-guessType-add-be:", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("mgr-guessType-add-error:", e);
        }
        return new MyResp<>(CodeCons.ERROR, "添加失败");
    }

    @PostMapping("/mgr/del")
    @OfficeSecure
    public MyResp<String> mgrDel(@RequestBody GuessTypeReq req) {
        logger.info("mgr-guessType-del-req={}", req);
        try {
            ParamUtil.check(req.getId(), "ID");
            guessTypeService.delete(req);
            return new MyResp<>(CodeCons.SUCCESS, "删除成功");
        } catch (BizException e) {
            logger.error("mgr-guessType-del-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("mgr-guessType-del-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "删除失败");
    }

    @PostMapping("/mgr/update")
    @OfficeSecure
    public MyResp<String> mgrUpdate(@RequestBody GuessTypeReq req) {
        logger.info("mgr-guessType-update-req={}", req);
        try {
            ParamUtil.check(req.getId(), "ID");
            guessTypeService.update(req);
            return new MyResp<>(CodeCons.SUCCESS, "修改成功");
        } catch (BizException e) {
            logger.error("mgr-guessType-update-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("mgr-guessType-update-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "更新失败");
    }

    @PostMapping("/mgr/getById")
    @OfficeSecure
    public MyResp<GuessType> mgrGetById(@RequestBody GuessTypeReq req) {
        logger.info("mgr-guessType-getById-req={}", req);
        try {
            ParamUtil.check(req.getId(), "ID");
            GuessType rsp = guessTypeService.getById(req.getId());
            return new MyResp<>(CodeCons.SUCCESS, "", rsp);
        } catch (BizException e) {
            logger.error("mgr-guessType-getById-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("mgr-guessType-getById-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "查询失败");
    }

    @PostMapping("/mgr/typeList")
    @OfficeSecure
    public MyResp<PageInfo<GuessType>> typeList(@RequestBody GuessTypeReq req) {
        logger.info("mgr-guessType-typeList-req={}", req);
        try {
            ParamUtil.check(req.getPage(), "page", req.getPagesize(), "pageSize");
            PageInfo<GuessType> typeList = guessTypeService.typeList(req);
            return new MyResp<>(CodeCons.SUCCESS, "", typeList);
        } catch (BizException e) {
            logger.error("mgr-guessType-typeList-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("mgr-guessType-typeList-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "查询失败");
    }
}