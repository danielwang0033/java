package com.coin.web.controller;

import com.coin.entity.AdminRoles;
import com.coin.req.AdminRolesReq;
import com.coin.resp.MyResp;
import com.coin.service.AdminRolesService;
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
@RequestMapping("/adminRoles")
public class AdminRolesController {

    private static final Logger logger = LoggerFactory.getLogger(AdminRolesController.class);

    @Resource
    private AdminRolesService adminRolesService;

    @PostMapping("/mgr/pageList")
    @OfficeSecure
    public MyResp<PageInfo<AdminRoles>> pageList(@RequestBody AdminRolesReq req) {
        logger.info("adminRoles-pageList-req={}", req);
        try {
            ParamUtil.check(req.getPage(), "page", req.getPagesize(), "pageSize");
            PageInfo<AdminRoles> page = adminRolesService.pageList(req);
            return new MyResp<>(CodeCons.SUCCESS, "", page);
        } catch (BizException e) {
            logger.error("adminRoles-pageList-error", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("adminRoles-pageList-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "查询失败");
    }

    @PostMapping("/mgr/add")
    @OfficeSecure
    public MyResp<String> add(@RequestBody AdminRolesReq req) {
        logger.info("adminRoles-add-req={}", req);
        try {
            ParamUtil.check(req.getName(), "name", req.getSlug(), "slug");
            adminRolesService.add(req);
            return new MyResp<>(CodeCons.SUCCESS, "添加成功");
        } catch (BizException e) {
            logger.error("adminRoles-add-error", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("adminRoles-add-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "添加失败");
    }

    @PostMapping("/mgr/update")
    @OfficeSecure
    public MyResp<String> update(@RequestBody AdminRolesReq req) {
        logger.info("adminRoles-update-req={}", req);
        try {
            ParamUtil.check(req.getId(), "id");
            adminRolesService.update(req);
            return new MyResp<>(CodeCons.SUCCESS, "更新成功");
        } catch (BizException e) {
            logger.error("adminRoles-update-error", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("adminRoles-update-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "更新失败");
    }
}