package com.coin.web.controller;

import com.coin.enums.DrawNumChangeTypeEnum;
import com.coin.req.UsersReq;
import com.coin.resp.MyResp;
import com.coin.resp.UsersResp;
import com.coin.resp.user.UserInviteVo;
import com.coin.service.UsersService;
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
@RequestMapping("/users")
public class UsersController {

    private static final Logger logger = LoggerFactory.getLogger(UsersController.class);

    @Resource
    private UsersService usersService;

    @PostMapping("/mgr/add")
    @OfficeSecure
    public MyResp<String> mgrAdd(@RequestBody UsersReq req) {
        logger.info("mgr-users-add-req={}", req);
        try {
            ParamUtil.check(req.getName(), "name", req.getEmail(), "email", req.getPassword(), "password");
            usersService.add(req);
            return new MyResp<>(CodeCons.SUCCESS, "添加成功");
        } catch (BizException e) {
            logger.error("mgr-users-add-be:", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("mgr-users-add-error:", e);
        }
        return new MyResp<>(CodeCons.ERROR, "添加失败");
    }

    @PostMapping("/mgr/update")
    @OfficeSecure
    public MyResp<String> mgrUpdate(@RequestBody UsersReq req) {
        logger.info("mgr-users-update-req={}", req);
        try {
            ParamUtil.check(req.getId(), "ID");
            usersService.update(req);
            return new MyResp<>(CodeCons.SUCCESS, "修改成功");
        } catch (BizException e) {
            logger.error("mgr-users-update-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("mgr-users-update-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "更新失败");
    }

    @PostMapping("/mgr/updateExp")
    @OfficeSecure
    public MyResp<String> updateExp(@RequestBody UsersReq req) {
        logger.info("mgr-users-update-req={}", req);
        try {
            ParamUtil.check(req.getId(), "ID");
            usersService.updateExp(req);
            return new MyResp<>(CodeCons.SUCCESS, "修改成功");
        } catch (BizException e) {
            logger.error("mgr-users-updateExp-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("mgr-users-updateExp-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "更新失败");
    }

    @PostMapping("/mgr/updateBobi")
    @OfficeSecure
    public MyResp<String> updateBobi(@RequestBody UsersReq req) {
        logger.info("mgr-users-update-req={}", req);
        try {
            ParamUtil.check(req.getId(), "ID");
            usersService.updateBobi(req);
            return new MyResp<>(CodeCons.SUCCESS, "修改成功");
        } catch (BizException e) {
            logger.error("mgr-users-update-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("mgr-users-update-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "更新失败");
    }

    @PostMapping("/mgr/getById")
    @OfficeSecure
    public MyResp<UsersResp> mgrGetById(@RequestBody UsersReq req) {
        logger.info("mgr-users-getById-req={}", req);
        try {
            ParamUtil.check(req.getId(), "ID");
            UsersResp rsp = usersService.getById(req.getId(), true);
            return new MyResp<>(CodeCons.SUCCESS, "", rsp);
        } catch (BizException e) {
            logger.error("mgr-users-getById-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("mgr-users-getById-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "查询失败");
    }

    @PostMapping("/mgr/pageList")
    @OfficeSecure
    public MyResp<PageInfo<UsersResp>> mgrPageList(@RequestBody UsersReq req) {
        logger.info("mgr-users-pageList-req={}", req);
        try {
            ParamUtil.check(req.getPage(), "page", req.getPagesize(), "pageSize");
            PageInfo<UsersResp> page = usersService.pageList(req);
            return new MyResp<>(CodeCons.SUCCESS, "", page);
        } catch (BizException e) {
            logger.error("mgr-users-pageList-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("mgr-users-pageList-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "查询失败");
    }

    @PostMapping("/mgr/addDrawNumber")
    @OfficeSecure
    public MyResp<String> addDrawNumber(@RequestBody UsersReq req) {
        logger.info("mgr-users-addDrawNumber-req={}", req);
        try {
            ParamUtil.check(req.getId(), "ID", req.getAddDrawNumber(), "addDrawNumber");

            usersService.addDrawNumber(req, DrawNumChangeTypeEnum.ADMIN_ADD);
            return new MyResp<>(CodeCons.SUCCESS, "操作成功");
        } catch (BizException e) {
            logger.error("mgr-users-addDrawNumber-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("mgr-users-addDrawNumber-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "更新失败");
    }

    @PostMapping("/mgr/inviteList")
    @OfficeSecure
    public MyResp<PageInfo<UserInviteVo>> inviteList(@RequestBody UsersReq req) {
        logger.info("mgr-users-inviteList-req={}", req);
        try {
            ParamUtil.check(req.getId(), "ID", req.getPage(), "page", req.getPagesize(), "pageSize");
            PageInfo<UserInviteVo> page = usersService.inviteList(req);
            return new MyResp<>(CodeCons.SUCCESS, "", page);
        } catch (BizException e) {
            logger.error("mgr-users-inviteList-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("mgr-users-inviteList-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "查询失败");
    }

    /*@PostMapping("/mgr/pwdTest")
    @OfficeSecure
    public MyResp<String> pwdTest(@RequestBody UsersReq req) {
        logger.info("mgr-users-pwdTest-req={}", req);
        try {
            ParamUtil.check(req.getId(), "ID");
            usersService.pwdTest(req);
            return new MyResp<>(CodeCons.SUCCESS, "OK");
        } catch (BizException e) {
            logger.error("mgr-users-pwdTest-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("mgr-users-pwdTest-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "更新失败");
    }*/
}