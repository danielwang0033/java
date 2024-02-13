package com.coin.web.controller;

import cn.hutool.core.util.StrUtil;
import com.coin.entity.AdminUsers;
import com.coin.req.AdminUsersReq;
import com.coin.req.NotificationsReq;
import com.coin.resp.MyResp;
import com.coin.resp.admin.AdminUsersVo;
import com.coin.service.AdminUsersService;
import com.coin.service.constant.BizCons;
import com.coin.service.constant.CodeCons;
import com.coin.service.exception.BizException;
import com.coin.service.util.BizUtil;
import com.coin.service.util.MD5Util;
import com.coin.service.util.ParamUtil;
import com.coin.service.util.RedisUtil;
import com.coin.web.annotation.OfficeSecure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/adminUsers")
public class AdminUsersController {

    private static final Logger logger = LoggerFactory.getLogger(AdminUsersController.class);

    @Resource
    private AdminUsersService adminUsersService;
    @Resource
    private RedisUtil redisUtil;

    @PostMapping("/mgr/login")
    @OfficeSecure(needLogin = false)
    public MyResp<AdminUsers> login(@RequestBody AdminUsersReq req) {
        logger.info("adminUsers-login-req={}", req);
        try {
            ParamUtil.check(req.getUsername(), "username", req.getPassword(), "password");
            AdminUsers oldUser = adminUsersService.getByUsername(req.getUsername());
            if (oldUser == null) {
                return new MyResp<>(CodeCons.ERROR, "用户不存在");
            }

            if (!MD5Util.MD5(req.getPassword()).equals(oldUser.getPassword())) {
                return new MyResp<>(CodeCons.ERROR, "密码输入错误");
            }
            String tokenKey = RedisUtil.getOfficeKey(oldUser.getUsername() + ":token");
            String oldToken = redisUtil.get(tokenKey);
            if (StrUtil.isNotBlank(oldToken)) {
                redisUtil.remove(oldToken);
                redisUtil.remove(tokenKey);
            }
            oldUser.setPassword(null);
            String token = MD5Util.MD5(oldUser.getUsername() + System.currentTimeMillis() + BizUtil.getStringRandom(5, 1));
            redisUtil.set(token, oldUser.getUsername(), BizCons.SESSION_OUT_TIME);
            redisUtil.set(tokenKey, token, BizCons.SESSION_OUT_TIME);
            oldUser.setRememberToken(token);
            return new MyResp<>(CodeCons.SUCCESS, token, oldUser);
        } catch (BizException e) {
            logger.error("adminUsers-login-error", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("adminUsers-login-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "请求失败");
    }

    @PostMapping("/mgr/sendAdminMsg")
    @OfficeSecure
    public MyResp<String> sendAdminMsg(@RequestBody NotificationsReq req) {
        logger.info("adminUsers-sendAdminMsg-req={}", req);
        try {
            ParamUtil.check(req.getData(), "data", req.getNotifiableId(), "notifiableId");
            adminUsersService.sendAdminMsg(req);
            return new MyResp<>(CodeCons.SUCCESS, "发送成功");
        } catch (BizException e) {
            logger.error("adminUsers-sendAdminMsg-error", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("adminUsers-sendAdminMsg-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "发送失败");
    }

    @PostMapping("/mgr/logout")
    @OfficeSecure
    public MyResp<String> logout(@RequestBody AdminUsersReq req) {
        logger.info("adminUsers-logout-req={}", req);
        try {
            if (req.getOptLoginName() == null) {
                return new MyResp<>(CodeCons.SUCCESS, "退出成功");
            }
            AdminUsers user = adminUsersService.getByUsername(req.getOptLoginName());
            String tokenKey = RedisUtil.getOfficeKey(user.getUsername() + ":token");
            if (redisUtil.get(tokenKey) != null) {
                String oldToken = redisUtil.get(redisUtil.get(tokenKey));
                if (oldToken != null) {
                    redisUtil.remove(redisUtil.get(tokenKey));
                }
                redisUtil.remove(tokenKey);
            }
            return new MyResp<>(CodeCons.SUCCESS, "退出成功");
        } catch (BizException e) {
            logger.error("adminUsers-logout-error", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("adminUsers-logout-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "请求失败");
    }

    @PostMapping("/mgr/update")
    @OfficeSecure
    public MyResp<String> update(@RequestBody AdminUsersReq req) {
        logger.info("adminUsers-updatePass-req={}", req);
        try {
            adminUsersService.update(req);
            MyResp<String> logout = this.logout(req);
            if (logout.getCode().equals(100000)) {
                return new MyResp<>(CodeCons.SUCCESS, "修改成功");
            }
        } catch (BizException e) {
            logger.error("adminUsers-updatePass-error", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("adminUsers-updatePass-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "修改失败");
    }

    @PostMapping("/mgr/adminList")
    @OfficeSecure
    public MyResp<List<AdminUsersVo>> adminList(@RequestBody AdminUsersReq req) {
        logger.info("mgr-adminUsers-adminList-req={}", req);
        try {
            List<AdminUsersVo> page = adminUsersService.adminList(req);
            return new MyResp<>(CodeCons.SUCCESS, "", page);
        } catch (BizException e) {
            logger.error("mgr-adminUsers-adminList-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("mgr-adminUsers-adminList-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "查询失败");
    }
}