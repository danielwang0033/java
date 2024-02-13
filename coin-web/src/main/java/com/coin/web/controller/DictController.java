package com.coin.web.controller;

import cn.hutool.json.JSONObject;
import com.coin.entity.TDict;
import com.coin.i18n.I18nUtil;
import com.coin.req.DictReq;
import com.coin.req.dict.DictUpdateReq;
import com.coin.resp.MyResp;
import com.coin.service.DictService;
import com.coin.service.constant.CodeCons;
import com.coin.service.exception.BizException;
import com.coin.service.util.ParamUtil;
import com.coin.web.annotation.OfficeSecure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/dict")
public class DictController {

    private static final Logger logger = LoggerFactory.getLogger(DictController.class);

    @Resource
    private DictService dictService;

    @Value("${spring.profiles.active}")
    private String env;

    @PostMapping("/mgr/getList")
    @OfficeSecure
    public MyResp<List<TDict>> pageList(@RequestBody DictReq req) {
        logger.info("dict-getList-req={}", req);
        try {
            ParamUtil.check(req.getDictType(), "dictType");
            List<TDict> list = dictService.getListByType(req);
            return new MyResp<>(CodeCons.SUCCESS, "", list);
        } catch (BizException e) {
            logger.error("mgr-dict-getList-be:", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("mgr-dict-getList-error:", e);
        }
        return new MyResp<>(CodeCons.ERROR, "请求失败");
    }

    @PostMapping("/mgr/moduleSwitch")
    @OfficeSecure
    public MyResp<List<TDict>> moduleSwitch(@RequestBody DictReq req) {
        logger.info("dict-moduleSwitch-req={}", req);
        try {
            req.setOnlySortNum(1);
            req.setDictType("module_switch");
            List<TDict> list = dictService.getListByType(req);
            return new MyResp<>(CodeCons.SUCCESS, "", list);
        } catch (BizException e) {
            logger.error("mgr-dict-moduleSwitch-be:", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("mgr-dict-moduleSwitch-error:", e);
        }
        return new MyResp<>(CodeCons.ERROR, "请求失败");
    }

    @PostMapping("/mgr/update")
    @OfficeSecure
    public MyResp<String> update(@RequestBody DictReq req) {
        logger.info("dict-update-req={}", req);
        try {
            ParamUtil.check(req.getId(), "id");
            dictService.update(req);
            return new MyResp<>(CodeCons.SUCCESS, "保存成功");
        } catch (BizException e) {
            logger.error("dict-update-e:", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("dict-update-error:", e);
        }
        return new MyResp<>(CodeCons.ERROR, "请求失败");
    }

    @PostMapping("/mgr/updateBatch")
    @OfficeSecure
    public MyResp<String> updateBatch(@RequestBody DictUpdateReq req) {
        logger.info("dict-update-update={}", req);
        try {
            dictService.updateBatch(req);
            return new MyResp<>(CodeCons.SUCCESS, "更新成功");
        } catch (BizException e) {
            logger.error("dict-update-e", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("dict-update-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "请求失败");
    }

    @PostMapping("/refreshXssConfig")
    @OfficeSecure
    public MyResp<String> refresh(@RequestBody DictReq req) {
        try {
            dictService.refreshXssConfig();
            return new MyResp<>(CodeCons.SUCCESS, "OK");
        } catch (BizException e) {
            return new MyResp<>(e.getCode(), e.getErrMsg());
        }
    }

    @PostMapping("/getI18nConfig")
    @OfficeSecure
    public MyResp<JSONObject> getI18nConfig(@RequestBody DictReq req) {
        try {
            JSONObject result = new JSONObject();
            result.set("env", env);
            result.set("i18n.enable", I18nUtil.ENABLE);
            result.set("i18n.language", I18nUtil.LANGUAGE);
            return new MyResp<>(CodeCons.SUCCESS, "OK", result);
        } catch (BizException e) {
            return new MyResp<>(e.getCode(), e.getErrMsg());
        }
    }
}
