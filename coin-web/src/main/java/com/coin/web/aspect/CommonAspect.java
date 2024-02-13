package com.coin.web.aspect;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.coin.entity.Rooms;
import com.coin.entity.RoomsExample;
import com.coin.entity.Users;
import com.coin.mapper.RoomsMapper;
import com.coin.mapper.UsersMapper;
import com.coin.req.CommonReq;
import com.coin.resp.MyResp;
import com.coin.service.constant.BizCons;
import com.coin.service.constant.CodeCons;
import com.coin.service.exception.BizException;
import com.coin.service.util.RedisUtil;
import com.coin.web.annotation.CommonSecure;
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
import java.util.List;

@Aspect
@Component
@Order(2)
public class CommonAspect {

    private static final Logger logger = LoggerFactory.getLogger(CommonAspect.class);
    @Resource
    private RedisUtil redisUtil;
    @Resource
    private IpUtils ipUtils;
    @Resource
    private UsersMapper usersMapper;
    @Resource
    private RoomsMapper roomsMapper;
    @Value("${chat.rcRid}")
    private String rcRid;

    @Around(value = "within(com.coin.web.controller.client.*Controller) && @annotation(commonSecure)")
    public Object commonSecure(ProceedingJoinPoint pj, CommonSecure commonSecure) {
        try {
            CommonReq req = this.getReq(pj);
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request;
            if (attributes != null) {
                request = attributes.getRequest();
            } else {
                return new MyResp<>(CodeCons.LOGIN_OUT, "ServletRequestAttributes异常");
            }
            String method = request.getServletPath();
            String token = request.getHeader("token");
            if (StrUtil.isBlank(token)){
                token = "";
            }

            String optLoginNameObj = redisUtil.get(token);
            if (StrUtil.isBlank(optLoginNameObj) && commonSecure.needLogin()) {
                return new MyResp<>(CodeCons.LOGIN_OUT, "登录已过期");
            }
            JSONObject jsonObject = JSONUtil.parseObj(optLoginNameObj);
            Long loginUserId = jsonObject.getLong("id");
            String loginUserName = jsonObject.getStr("name");
            String loginUserEmail = jsonObject.getStr("email");
            req.setOptLoginName(loginUserName);
            req.setLoginUserId(loginUserId);
            req.setLoginUserEmail(loginUserEmail);
            if (!commonSecure.needLogin()) {
                loginUserEmail = "sys-api";
            }
            String apiKey = RedisUtil.getApiKey(loginUserEmail + method);
            String ip = ipUtils.getIp(request);
            req.setReqIp(ip);
            //聊天登录
            if (commonSecure.needLogin() && commonSecure.needChatLogin()) {
                Users users = usersMapper.selectByPrimaryKey(loginUserId);
                if (CharSequenceUtil.isEmpty(users.getRocketUserId())) {
                    logger.error("commonSecure-1:optLoginName={}, request-ip={}, apiKey={}", loginUserEmail, ip, apiKey);
                    return new MyResp<>(CodeCons.REQ_TOO_FAST, "非聊天室用户");
                }
                req.setOptRocketUserId(users.getRocketUserId());
                req.setOptRocketUserLoginToken(users.getRocketUserLoginToken());
                if (commonSecure.needChatManage()) {
                    RoomsExample example = new RoomsExample();
                    RoomsExample.Criteria criteria = example.createCriteria();
                    criteria.andRidEqualTo(rcRid);
                    criteria.andRocketUserIdEqualTo(users.getRocketUserId());
                    List<Rooms> roomsMapperList = roomsMapper.selectByExample(example);
                    if (CollUtil.isEmpty(roomsMapperList)) {
                        return new MyResp<>(CodeCons.REQ_TOO_FAST, "未加入房间");
                    }
                    Rooms rooms = roomsMapperList.get(0);
                    if (rooms.getOwner() != 1) {
                        return new MyResp<>(CodeCons.REQ_TOO_FAST, "不是房间管理员");
                    }
                }
            }
            String tokenKey = RedisUtil.getApiKey(loginUserEmail + ":token");
            req.setSourceSysType(2);
            logger.info("commonSecure-2:optLoginName={}, request-ip={}", loginUserEmail, ip);
            redisUtil.setExpire(token, BizCons.SESSION_OUT_TIME_CLIENT);
            redisUtil.setExpire(tokenKey, BizCons.SESSION_OUT_TIME_CLIENT);
            return pj.proceed();
        } catch (BizException e) {
            logger.error("commonSecure-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("commonSecure-ex", e);
        } catch (Throwable t) {
            logger.error("commonSecure-tb", t);
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
