package com.coin.web.controller;

import com.coin.entity.MatchVideos;
import com.coin.req.MatchVideosReq;
import com.coin.resp.MyResp;
import com.coin.service.MatchVideosService;
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
@RequestMapping("/matchVideos")
public class MatchVideosController {

    private static final Logger logger = LoggerFactory.getLogger(MatchVideosController.class);

    @Resource
    private MatchVideosService matchVideosService;

    @PostMapping("/mgr/pageList")
    @OfficeSecure
    public MyResp<PageInfo<MatchVideos>> mgrPageList(@RequestBody MatchVideosReq req) {
        logger.info("mgr-matchVideos-pageList-req={}", req);
        try {
            ParamUtil.check(req.getPage(), "page", req.getPagesize(), "pageSize");
            PageInfo<MatchVideos> page = matchVideosService.pageList(req);
            return new MyResp<>(CodeCons.SUCCESS, "", page);
        } catch (BizException e) {
            logger.error("mgr-matchVideos-pageList-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("mgr-matchVideos-pageList-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "查询失败");
    }
}