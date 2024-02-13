package com.coin.web.controller.client;

import com.coin.req.AdsReq;
import com.coin.resp.MyResp;
import com.coin.resp.ad.AdSlotsRespVo;
import com.coin.service.AdsService;
import com.coin.service.constant.CodeCons;
import com.coin.service.exception.BizException;
import com.coin.service.util.ParamUtil;
import com.coin.web.annotation.CommonSecure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

// 15_广告
@RestController
@RequestMapping("/ads")
public class AdController {

    private static final Logger logger = LoggerFactory.getLogger(AdController.class);

    @Resource
    private AdsService adsService;

    // 01_广告列表
    @PostMapping("/pageList")
    @CommonSecure(needLogin = false)
    public MyResp<Map<String, AdSlotsRespVo>> pageList(@RequestBody AdsReq req) {
        logger.info("ads-pageList-req={}", req);
        try {
            ParamUtil.check(req.getPlatform(), "platform");
            Map<String, AdSlotsRespVo> page = adsService.clientPageList(req);
            return new MyResp<>(CodeCons.SUCCESS, "", page);
        } catch (BizException e) {
            logger.error("ads-pageList-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("ads-pageList-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "查询失败");
    }

    // 02_首页弹窗
    @PostMapping("/popUps")
    @CommonSecure(needLogin = false)
    public MyResp<List<String>> popUps(@RequestBody AdsReq req) {
        logger.info("ads-popUps-req={}", req);
        try {
            List<String> popUps = adsService.popUps(req);
            return new MyResp<>(CodeCons.SUCCESS, "", popUps);
        } catch (BizException e) {
            logger.error("ads-popUps-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("ads-popUps-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "查询失败");
    }

    // 03_关闭首页弹窗
    @PostMapping("/readPopUps")
    @CommonSecure
    public MyResp<String> readPopUps(@RequestBody AdsReq req) {
        logger.info("ads-readPopUps-req={}", req);
        try {
            adsService.readPopUps(req);
            return new MyResp<>(CodeCons.SUCCESS, "更新成功");
        } catch (BizException e) {
            logger.error("ads-readPopUps-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("ads-readPopUps-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "更新失败");
    }
}