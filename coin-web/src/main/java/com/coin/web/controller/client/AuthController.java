package com.coin.web.controller.client;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.coin.entity.Users;
import com.coin.req.AuthReq;
import com.coin.req.EmailsReq;
import com.coin.req.UsersReq;
import com.coin.resp.MyResp;
import com.coin.resp.user.EmailsResp;
import com.coin.service.UsersService;
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
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

// 01_登录认证
@RestController
@RequestMapping("/auth")
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Resource
    private UsersService usersService;

    // 01_用户注册
    @PostMapping("/register")
    @CommonSecure(needLogin = false)
    public MyResp<Map<String, String>> register(@RequestBody AuthReq req, HttpServletRequest request) {
        logger.info("users-register-req={}", req);
        try {
            ParamUtil.check(req.getUsername(), "username", req.getEmail(), "email", req.getPassword(),
                    "password", req.getPasswordConfirm(), "passwordConfirm");
            if (!StrUtil.equals(req.getPassword(), req.getPasswordConfirm())) {
                return new MyResp<>(CodeCons.ERROR, "两次输入密码不同");
            }
            // 校验用户名称唯一
            Users users = usersService.selectByUserName(req.getUsername());
            if (ObjectUtil.isNotNull(users)) {
                return new MyResp<>(CodeCons.ERROR, "用户名已被占用");
            }
            // 校验邮箱唯一
            Users users2 = usersService.selectByEmail(req.getEmail());
            if (ObjectUtil.isNotNull(users2)) {
                return new MyResp<>(CodeCons.ERROR, "邮箱已被占用");
            }

            // 开始注册
            UsersReq usersReq = new UsersReq();
            // 如果存在推荐码, 则校验推荐码
            if (StrUtil.isNotBlank(req.getInviteCode())){
                Users inviteCodeUser = usersService.checkInviteCode(req);
                usersReq.setInvitedByUser(inviteCodeUser);
                usersReq.setActivity(req.getActivity());
            }
            usersReq.setName(req.getUsername());
            usersReq.setPassword(req.getPassword());
            usersReq.setEmail(req.getEmail());
            usersReq.setReqIp(req.getReqIp());
            usersService.doClientRegister(usersReq, request);

            String token = usersService.doClientLogin(usersReq);
            Map<String, String> map = new HashMap<>();
            map.put("token", token);
            return new MyResp<>(CodeCons.SUCCESS, "注册成功,系统将要发送一封验证邮件到您的邮箱。未通过邮件验证用户将无法使用找回密码功能！", map);
        } catch (BizException e) {
            logger.error("users-register-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("users-register-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "注册失败");
    }

    // 02_用户登录
    @PostMapping("/login")
    @CommonSecure(needLogin = false)
    public MyResp<String> login(@RequestBody AuthReq req) {
        logger.info("users-login-req={}", req);
        try {
            ParamUtil.check(req.getUsername(), "username", req.getPassword(), "password");
            UsersReq usersReq = new UsersReq();
            usersReq.setName(req.getUsername());
            usersReq.setEmail(req.getEmail());
            usersReq.setPassword(req.getPassword());
            String token = usersService.doClientLogin(usersReq);
            return new MyResp<>(CodeCons.SUCCESS, "登录成功", token);
        } catch (BizException e) {
            logger.error("users-login-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("users-login-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "登录失败");
    }

    // 03_找回密码-发送邮件
    @PostMapping("/sendResetPasswordMail")
    @CommonSecure(needLogin = false)
    public MyResp<EmailsResp> sendResetPasswordMail(@RequestBody UsersReq req) {
        logger.info("users-sendResetPasswordMail-req={}", req);
        try {
            EmailsResp traceId = usersService.sendResetPasswordMail(req);
            return new MyResp<>(CodeCons.SUCCESS, "邮件即将送达", traceId);
        } catch (BizException e) {
            logger.error("users-sendResetPasswordMail-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("users-sendResetPasswordMail-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "发送失败");
    }

    // 04_找回密码-重设密码
    @PostMapping("/resetPassword")
    @CommonSecure(needLogin = false)
    public MyResp<String> resetPassword(@RequestBody EmailsReq req) {
        logger.info("users-resetPassword-req={}", req);
        try {
            ParamUtil.check(req.getTraceId(), "traceId", req.getPassword(), "password",
                    req.getCode(), "code", req.getPasswordConfirm(), "passwordConfirm");
            usersService.resetPassword(req);
            return new MyResp<>(CodeCons.SUCCESS, "重置密码成功");
        } catch (BizException e) {
            logger.error("users-resetPassword-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("users-resetPassword-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "查询失败");
    }

    // 05_找回密码-校验验证码
    @PostMapping("/verifyResetPwdEmailCode")
    @CommonSecure
    public MyResp<String> verifyResetPwdEmailCode(@RequestBody EmailsReq req) {
        logger.info("users-verifyResetPwdEmailCode-req={}", req);
        try {
            usersService.verifyResetPwdEmailCode(req);
            return new MyResp<>(CodeCons.SUCCESS, "验证成功");
        } catch (BizException e) {
            logger.error("users-verifyResetPwdEmailCode-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("users-verifyResetPwdEmailCode-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "验证失败");
    }

    // 06_退出登录
    @PostMapping("/logout")
    @CommonSecure
    public MyResp<String> logout(@RequestBody UsersReq req) {
        logger.info("users-logout-req");
        try {
            usersService.doClientLogout(req);
            return new MyResp<>(CodeCons.SUCCESS, "退出成功");
        } catch (BizException e) {
            logger.error("users-logout-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("users-logout-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "退出失败");
    }
}