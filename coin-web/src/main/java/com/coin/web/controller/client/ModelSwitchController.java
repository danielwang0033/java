package com.coin.web.controller.client;

import com.coin.req.DictReq;
import com.coin.resp.MyResp;
import com.coin.resp.dict.ModelSwitchVo;
import com.coin.service.DictService;
import com.coin.service.constant.CodeCons;
import com.coin.service.exception.BizException;
import com.coin.web.annotation.CommonSecure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

// 16_模块开关
@RestController
@RequestMapping("/modelSwitch")
public class ModelSwitchController {

    private static final Logger logger = LoggerFactory.getLogger(ModelSwitchController.class);

    @Resource
    private DictService dictService;

    // 01_查询开关配置
    @PostMapping("/configList")
    @CommonSecure(needLogin = false)
    public MyResp<List<ModelSwitchVo>> configList(@RequestBody DictReq req) {
        logger.info("modelSwitch-configList-req={}", req);
        try {
            List<ModelSwitchVo> configList = dictService.modelSwitchConfig(req);
            return new MyResp<>(CodeCons.SUCCESS, "", configList);
        } catch (BizException e) {
            logger.error("modelSwitch-configList-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("modelSwitch-configList-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "查询失败");
    }
}