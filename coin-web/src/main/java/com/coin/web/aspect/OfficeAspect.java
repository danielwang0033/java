package com.coin.web.aspect;

import cn.hutool.core.util.StrUtil;
import com.coin.req.CommonReq;
import com.coin.resp.MyResp;
import com.coin.service.constant.BizCons;
import com.coin.service.constant.CodeCons;
import com.coin.service.exception.BizException;
import com.coin.service.util.RedisUtil;
import com.coin.web.annotation.OfficeSecure;
import com.coin.web.utils.IpUtils;
import com.coin.web.utils.ParamUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
@Order(1)
public class OfficeAspect {

    private static final Logger logger = LoggerFactory.getLogger(OfficeAspect.class);
    @Resource
    private RedisUtil redisUtil;
    @Resource
    private IpUtils ipUtils;
    @Value("${chat.rcAdminId}")
    private String rcAdminId;
    @Value("${chat.rcAdminToken}")
    private String rcAdminToken;

    @Around(value = "within(com.coin.web.controller.*Controller) && @annotation(officeSecure)")
    public Object officeSecure(ProceedingJoinPoint pj, OfficeSecure officeSecure) {
        try {
            CommonReq req = this.getReq(pj);
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request;
            if (attributes != null) {
                request = attributes.getRequest();
            } else {
                return new MyResp<>(CodeCons.LOGIN_OUT, "ServletRequestAttributes异常");
            }
            String authorization = request.getHeader("authorization");
            if (StrUtil.isBlank(authorization)) {
                authorization = "";
            }
            if (officeSecure.doDownLoad()) {
                authorization = req.getAuthorization();
            }
            String optLoginName = redisUtil.get(authorization);
            if (StrUtil.isBlank(optLoginName) && officeSecure.needLogin()) {
                return new MyResp<>(CodeCons.LOGIN_OUT, "登录已过期");
            }
            if (!officeSecure.needLogin()) {
                optLoginName = req.getOptLoginName();
            }
            String ip = ipUtils.getIp(request);
            req.setReqIp(ip);
            String authorizationKey = RedisUtil.getOfficeKey(optLoginName + ":token");
            req.setSourceSysType(1);
            logger.info("office-optLoginName={}, request-ip={}", optLoginName, ip);
            redisUtil.setExpire(authorization, BizCons.SESSION_OUT_TIME);
            redisUtil.setExpire(authorizationKey, BizCons.SESSION_OUT_TIME);
            req.setOptLoginName(optLoginName);
            req.setOptRocketUserId(rcAdminId);
            req.setOptRocketUserLoginToken(rcAdminToken);
            return pj.proceed();
        } catch (BizException e) {
            logger.error("officeSecure-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("officeSecure-ex", e);
        } catch (Throwable t) {
            logger.error("officeSecure-tb", t);
        }
        return null;
    }

    private CommonReq getReq(ProceedingJoinPoint pj) {
        CommonReq req = null;
        Object object = null;
        for (Object obj : pj.getArgs()) {
            if (obj instanceof CommonReq) {
                req = (CommonReq) obj;
                object = obj;
                break;
            }
        }
        if (req == null) {
            throw new BizException(CodeCons.ERROR, "请求参数格式错误");
        }
        ParamUtil.xssCheck(object);
        return req;
    }
}
