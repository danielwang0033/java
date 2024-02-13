package com.coin.web.controller;

import cn.hutool.core.collection.CollectionUtil;
import com.coin.req.DictReq;
import com.coin.resp.MyResp;
import com.coin.service.DictI18nService;
import com.coin.service.constant.CodeCons;
import com.coin.service.exception.BizException;
import com.coin.web.annotation.OfficeSecure;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/dictI18n")
public class DictI18nController {

    @Resource
    private DictI18nService dictService;

    @PostMapping("/addBatch")
    @OfficeSecure
    public MyResp<String> init(@RequestBody DictReq req) {
        try {
            dictService.addBatch(req);
            return new MyResp<>(CodeCons.SUCCESS, "OK");
        } catch (BizException e) {
            return new MyResp<>(e.getCode(), e.getErrMsg());
        }
    }

    @PostMapping("/check")
    @OfficeSecure
    public MyResp<String> check(@RequestBody DictReq req) {
        try {
            List<String> msg = dictService.check();
            return new MyResp<>(CodeCons.SUCCESS, CollectionUtil.join(msg, " "));
        } catch (BizException e) {
            return new MyResp<>(e.getCode(), e.getErrMsg());
        }
    }

    @PostMapping("/refresh")
    @OfficeSecure
    public MyResp<String> refresh(@RequestBody DictReq req) {
        try {
            dictService.refresh();
            return new MyResp<>(CodeCons.SUCCESS, "OK");
        } catch (BizException e) {
            return new MyResp<>(e.getCode(), e.getErrMsg());
        }
    }
}