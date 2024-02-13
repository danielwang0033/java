package com.coin.web.controller.noauth;

import com.coin.service.UsersService;
import com.coin.service.exception.BizException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/auth/verifyEmail")
public class NoAuthController {

    private static final Logger logger = LoggerFactory.getLogger(NoAuthController.class);

    @Resource
    private UsersService usersService;

    // 01_验证邮箱
    @GetMapping("/{uuid}")
    public String verifyEmail(@PathVariable("uuid") String uuid) {
        logger.info("users-logout-req");
        try {
            return usersService.verifyEmail(uuid);
        } catch (BizException e) {
            logger.error("users-logout-be", e);
            return "error";
        } catch (Exception e) {
            logger.error("users-logout-error", e);
        }
        return "error";
    }
}