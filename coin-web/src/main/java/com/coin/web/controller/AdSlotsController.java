package com.coin.web.controller;

import com.coin.entity.AdSlots;
import com.coin.req.AdSlotsReq;
import com.coin.resp.MyResp;
import com.coin.service.AdSlotsService;
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

/**
 * 广告位
 */
@RestController
@RequestMapping("/adSlots")
public class AdSlotsController {

    private static final Logger logger = LoggerFactory.getLogger(AdSlotsController.class);

    @Resource
    private AdSlotsService adSlotsService;

    @PostMapping("/mgr/add")
    @OfficeSecure
    public MyResp<String> mgrAdd(@RequestBody AdSlotsReq req) {
        logger.info("mgr-adSlots-add-req={}", req);
        try {
            ParamUtil.check(req.getName(), "name", req.getAlias(), "alias", req.getType(), "type", req.getPlatform(),
                    "platform");
            adSlotsService.add(req);
            return new MyResp<>(CodeCons.SUCCESS, "添加成功");
        } catch (BizException e) {
            logger.error("mgr-adSlots-add-be:", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("mgr-adSlots-add-error:", e);
        }
        return new MyResp<>(CodeCons.ERROR, "添加失败");
    }

    @PostMapping("/mgr/del")
    @OfficeSecure
    public MyResp<String> mgrDel(@RequestBody AdSlotsReq req) {
        logger.info("mgr-adSlots-del-req={}", req);
        try {
            ParamUtil.check(req.getId(), "ID");
            adSlotsService.delete(req);
            return new MyResp<>(CodeCons.SUCCESS, "删除成功");
        } catch (BizException e) {
            logger.error("mgr-adSlots-del-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("mgr-adSlots-del-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "删除失败");
    }

    @PostMapping("/mgr/update")
    @OfficeSecure
    public MyResp<String> mgrUpdate(@RequestBody AdSlotsReq req) {
        logger.info("mgr-adSlots-update-req={}", req);
        try {
            ParamUtil.check(req.getId(), "ID");
            adSlotsService.update(req);
            return new MyResp<>(CodeCons.SUCCESS, "修改成功");
        } catch (BizException e) {
            logger.error("mgr-adSlots-update-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("mgr-adSlots-update-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "更新失败");
    }

    @PostMapping("/mgr/pageList")
    @OfficeSecure
    public MyResp<PageInfo<AdSlots>> mgrPageList(@RequestBody AdSlotsReq req) {
        logger.info("mgr-adSlots-pageList-req={}", req);
        try {
            ParamUtil.check(req.getPage(), "page", req.getPagesize(), "pageSize");
            PageInfo<AdSlots> page = adSlotsService.pageList(req);
            return new MyResp<>(CodeCons.SUCCESS, "", page);
        } catch (BizException e) {
            logger.error("mgr-adSlots-pageList-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("mgr-adSlots-pageList-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "查询失败");
    }
}