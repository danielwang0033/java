package com.coin.web.controller;

import com.coin.entity.UserLevel;
import com.coin.req.UserLevelReq;
import com.coin.resp.MyResp;
import com.coin.service.UserLevelService;
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
@RequestMapping("/userLevel")
public class UserLevelController {

    private static final Logger logger = LoggerFactory.getLogger(UserLevelController.class);

    @Resource
    private UserLevelService userLevelService;

    @PostMapping("/mgr/pageList")
    @OfficeSecure
    public MyResp<PageInfo<UserLevel>> pageList(@RequestBody UserLevelReq req) {
        logger.info("userLevel-pageList-req={}", req);
        try {
            ParamUtil.check(req.getPage(), "page", req.getPagesize(), "pageSize");
            PageInfo<UserLevel> page = userLevelService.pageList(req);
            return new MyResp<>(CodeCons.SUCCESS, "", page);
        } catch (BizException e) {
            logger.error("userLevel-pageList-error", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("userLevel-pageList-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "查询失败");
    }

    @PostMapping("/mgr/add")
    @OfficeSecure
    public MyResp<String> add(@RequestBody UserLevelReq req) {
        logger.info("userLevel-add-req={}", req);
        try {
            ParamUtil.check(req.getLevel(), "level", req.getName(), "name", req.getBadge(),
                    "badge", req.getNeedExp(), "needExp");
            userLevelService.add(req);
            return new MyResp<>(CodeCons.SUCCESS, "添加成功");
        } catch (BizException e) {
            logger.error("userLevel-add-error", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("userLevel-add-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "添加失败");
    }

    @PostMapping("/mgr/update")
    @OfficeSecure
    public MyResp<String> update(@RequestBody UserLevelReq req) {
        logger.info("userLevel-update-req={}", req);
        try {
            ParamUtil.check(req.getId(), "id");
            userLevelService.update(req);
            return new MyResp<>(CodeCons.SUCCESS, "更新成功");
        } catch (BizException e) {
            logger.error("userLevel-update-error", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("userLevel-update-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "更新失败");
    }

    @PostMapping("/mgr/del")
    @OfficeSecure
    public MyResp<String> del(@RequestBody UserLevelReq req) {
        logger.info("userLevel-del-req={}", req);
        try {
            ParamUtil.check(req.getId(), "id");
            userLevelService.del(req);
            return new MyResp<>(CodeCons.SUCCESS, "删除成功");
        } catch (BizException e) {
            logger.error("userLevel-del-error", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("userLevel-del-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "删除失败");
    }
}