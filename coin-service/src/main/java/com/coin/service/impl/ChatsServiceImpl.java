package com.coin.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.coin.mapper.MatchsMapper;
import com.coin.req.ChatInfoReq;
import com.coin.req.MatchsReq;
import com.coin.req.RoomsReq;
import com.coin.service.ChatsService;
import com.coin.service.constant.CodeCons;
import com.coin.service.exception.BizException;
import com.coin.service.util.MD5Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class ChatsServiceImpl implements ChatsService {

    private static final Logger logger = LoggerFactory.getLogger(ChatsServiceImpl.class);

    @Resource
    private MatchsMapper matchsMapper;

    @Value("${sport.sportDomain}")
    private String sportDomain;
    @Value("${sport.sportKey}")
    private String sportKey;
    @Value("${sport.sportTkey}")
    private String sportTkey;

    @Value("${chat.rcInstance}")
    private String rcInstance;
    @Value("${chat.rcRestUrl}")
    private String rcRestUrl;
    @Value("${chat.rcWsUrl}")
    private String rcWsUrl;
    @Value("${chat.rcAdminId}")
    private String rcAdminId;
    @Value("${chat.rcAdminToken}")
    private String rcAdminToken;
    @Value("${chat.rcRid}")
    private String rcRid;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String createRoom(MatchsReq req) {
        //创建聊天室
        Map<String, String> headers = new HashMap<>();
        headers.put("X-Auth-Token", rcAdminToken);
        headers.put("X-User-Id", rcAdminId);
        headers.put("Content-type", "application/json");

        Map<String, String> param = new HashMap<>();
        param.put("name", "m" + req.getMatchId());

        //发送post请求并接收响应数据
        String http1 = HttpUtil.createGet(rcRestUrl + "channels.info?roomName=" + "m" + req.getMatchId()).addHeaders(headers).execute().body();
        JSONObject httpJson1 = JSONUtil.parseObj(http1);
        if (httpJson1.getBool("success")) {
            JSONObject channel = httpJson1.getJSONObject("channel");
            return channel.getStr("_id");
        }

        //发送post请求并接收响应数据
        String body = JSONUtil.toJsonStr(param);
        String http = HttpUtil.createPost(rcRestUrl + "channels.create").addHeaders(headers).body(body).execute().body();

        JSONObject httpJson = JSONUtil.parseObj(http);
        if (!httpJson.getBool("success")) {
            throw new BizException(CodeCons.ERROR, "ERROR");
        }
        JSONObject channel = httpJson.getJSONObject("channel");
        return channel.getStr("_id");
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteRoom(RoomsReq req) {
        //创建聊天室
        Map<String, String> headers = new HashMap<>();
        headers.put("X-Auth-Token", rcAdminToken);
        headers.put("X-User-Id", rcAdminId);
        headers.put("Content-type", "application/json");

        Map<String, String> param = new HashMap<>();
        param.put("roomId", req.getRid());

        //发送post请求并接收响应数据
        String body = JSONUtil.toJsonStr(param);
        String http = HttpUtil.createPost(rcRestUrl + "channels.delete").addHeaders(headers).body(body).execute().body();
        JSONObject httpJson = JSONUtil.parseObj(http);
        if (!httpJson.getBool("success")) {
            throw new BizException(CodeCons.ERROR, "ERROR");
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void kickRoom(RoomsReq req) {
        //创建聊天室
        Map<String, String> headers = new HashMap<>();
        headers.put("X-Auth-Token", req.getOptRocketUserLoginToken());
        headers.put("X-User-Id", req.getOptRocketUserId());
        headers.put("Content-type", "application/json");

        Map<String, String> param = new HashMap<>();
        param.put("roomId", req.getRid());
        param.put("userId", req.getRocketUserId());

        //发送post请求并接收响应数据
        String body = JSONUtil.toJsonStr(param);
        String http = HttpUtil.createPost(rcRestUrl + "channels.kick").addHeaders(headers).body(body).execute().body();
        JSONObject httpJson = JSONUtil.parseObj(http);
        if (!httpJson.getBool("success")) {
            throw new BizException(CodeCons.ERROR, "ERROR");
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public JSONObject login(ChatInfoReq req) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-type", "application/json");
        //发送post请求并接收响应数据
        Map<String, String> param = new HashMap<>();
        param.put("username", req.getRocketUserName());
        param.put("password", MD5Util.MD5(String.valueOf(req.getLoginUserId())));

        String body = JSONUtil.toJsonStr(param);
        String http = HttpUtil.createPost(rcRestUrl + "login").addHeaders(headers).body(body).execute().body();

        JSONObject httpJson = JSONUtil.parseObj(http);
        if (!httpJson.getStr("status").equals("success")) {
            throw new BizException(CodeCons.ERROR, "ERROR");
        }
        return httpJson;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void join(RoomsReq req) {
        Map<String, String> headers = new HashMap<>();
        headers.put("X-Auth-Token", req.getOptRocketUserLoginToken());
        headers.put("X-User-Id", req.getOptRocketUserId());
        headers.put("Content-type", "application/json");

        //发送post请求并接收响应数据
        Map<String, String> param = new HashMap<>();
        param.put("roomId", req.getRid());
        param.put("userId", req.getRocketUserId());

        String body = JSONUtil.toJsonStr(param);
        HttpUtil.createGet(rcRestUrl + "channels.invite").addHeaders(headers).body(body).execute().body();
        /*JSONObject httpJson = JSONUtil.parseObj(http);
        if (ObjectUtil.isNotNull(httpJson)) {
            if (!httpJson.getBool("success")) {
                throw new BizException(CodeCons.ERROR, "ERROR");
            }
        }
        return httpJson;*/
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public JSONObject historyMessage(RoomsReq req) {
        Map<String, String> headers = new HashMap<>();
        headers.put("X-Auth-Token", rcAdminToken);
        headers.put("X-User-Id", rcAdminId);
        headers.put("Content-type", "application/json");
        req.setRid(rcRid);
        //发送get请求并接收响应数据
        String http = HttpUtil.createGet(rcRestUrl + "channels.messages?roomId=" + req.getRid() + "&count=" + req.getPagesize() + "&offset=0").addHeaders(headers).execute().body();
        JSONObject httpJson = JSONUtil.parseObj(http);
        if (httpJson.getBool("success")) {
            return httpJson;
        }
        return null;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public JSONObject channelsOnline(RoomsReq req) {
        Map<String, String> headers = new HashMap<>();
        headers.put("X-Auth-Token", rcAdminToken);
        headers.put("X-User-Id", rcAdminId);
        headers.put("Content-type", "application/json");

        //发送get请求并接收响应数据
        String http = HttpUtil.createGet(rcRestUrl + "channels.online?query={\"_id\": \"" + req.getRid() + "\"}").addHeaders(headers).execute().body();
        JSONObject httpJson = JSONUtil.parseObj(http);
        if (!httpJson.getBool("success")) {
            throw new BizException(CodeCons.ERROR, "ERROR");
        }
        return httpJson;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public JSONObject getChannelsMembers(RoomsReq req) {
        Map<String, String> headers = new HashMap<>();
        headers.put("X-Auth-Token", rcAdminToken);
        headers.put("X-User-Id", rcAdminId);
        headers.put("Content-type", "application/json");

        //发送get请求并接收响应数据
        String http = HttpUtil.createGet(rcRestUrl + "channels.messages?roomId=" + req.getRid() + "&count=" + req.getCount() + "&offset=0").addHeaders(headers).execute().body();
        JSONObject httpJson = JSONUtil.parseObj(http);
        if (!httpJson.getBool("success")) {
            throw new BizException(CodeCons.ERROR, "ERROR");
        }
        return httpJson;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void addOwner(RoomsReq req) {
        //创建聊天室
        Map<String, String> headers = new HashMap<>();
        headers.put("X-Auth-Token", rcAdminToken);
        headers.put("X-User-Id", rcAdminId);
        headers.put("Content-type", "application/json");

        Map<String, String> param = new HashMap<>();
        param.put("roomId", req.getRid());
        param.put("userId", req.getRocketUserId());

        //发送post请求并接收响应数据
        String body = JSONUtil.toJsonStr(param);
        String http = HttpUtil.createPost(rcRestUrl + "channels.addOwner").addHeaders(headers).body(body).execute().body();
        JSONObject httpJson = JSONUtil.parseObj(http);
        if (!httpJson.getBool("success") && !Objects.equals(httpJson.getStr("errorType"), "error-user-already-owner")) {
            throw new BizException(CodeCons.ERROR, "ERROR");
        }
    }

    public void removeOwner(RoomsReq req) {
        //创建聊天室
        Map<String, String> headers = new HashMap<>();
        headers.put("X-Auth-Token", rcAdminToken);
        headers.put("X-User-Id", rcAdminId);
        headers.put("Content-type", "application/json");

        Map<String, String> param = new HashMap<>();
        param.put("roomId", req.getRid());
        param.put("userId", req.getRocketUserId());

        //发送post请求并接收响应数据
        String body = JSONUtil.toJsonStr(param);
        String http = HttpUtil.createPost(rcRestUrl + "channels.removeOwner").addHeaders(headers).body(body).execute().body();
        JSONObject httpJson = JSONUtil.parseObj(http);
        if (!httpJson.getBool("success") && !Objects.equals(httpJson.getStr("errorType"), "error-user-not-owner")) {
            throw new BizException(CodeCons.ERROR, "ERROR");
        }
    }

    public JSONObject createUser(ChatInfoReq req) {
        //创建聊天室
        Map<String, String> headers = new HashMap<>();
        headers.put("X-Auth-Token", rcAdminToken);
        headers.put("X-User-Id", rcAdminId);
        headers.put("Content-type", "application/json");

        Map<String, String> param = new HashMap<>();
        param.put("name", req.getName());
        param.put("email", req.getEmail());
        param.put("username", req.getRocketUserName());
        param.put("password", MD5Util.MD5(String.valueOf(req.getId())));

        //发送post请求并接收响应数据
        String body = JSONUtil.toJsonStr(param);
        String http = HttpUtil.createPost(rcRestUrl + "users.create").addHeaders(headers).body(body).execute().body();
        JSONObject httpJson = JSONUtil.parseObj(http);
        if (!httpJson.getBool("success")) {
            throw new BizException(CodeCons.ERROR, "ERROR");
        }
        return httpJson;
    }

    public void setAvatar(ChatInfoReq req) {
        if (StrUtil.isBlank(req.getAvatar())) {
            return;
        }
        //创建聊天室
        Map<String, String> headers = new HashMap<>();
        headers.put("X-Auth-Token", rcAdminToken);
        headers.put("X-User-Id", rcAdminId);
        headers.put("Content-type", "application/json");

        Map<String, String> param = new HashMap<>();
        param.put("avatarUrl", req.getAvatar());
        //发送post请求并接收响应数据
        String body = JSONUtil.toJsonStr(param);
        String http = HttpUtil.createPost(rcRestUrl + "users.setAvatar").addHeaders(headers).body(body).execute().body();
        JSONObject httpJson = JSONUtil.parseObj(http);
        if (!httpJson.getBool("success")) {
            throw new BizException(CodeCons.ERROR, "ERROR");
        }
    }

    public void sendMessage(RoomsReq req) {
        if (StrUtil.isBlank(req.getRid()) || StrUtil.isBlank(req.getText())) {
            return;
        }
        //创建聊天室
        Map<String, String> headers = new HashMap<>();
        headers.put("X-Auth-Token", req.getOptRocketUserLoginToken());
        headers.put("X-User-Id", req.getOptRocketUserId());
        headers.put("Content-type", "application/json");
        Map<String, Map<String, String>> param = new HashMap<>();
        Map<String, String> message = new HashMap<>();
        message.put("rid", req.getRid());
        message.put("msg", req.getText());
        param.put("message", message);

        //发送post请求并接收响应数据
        String body = JSONUtil.toJsonStr(param);
        String http = HttpUtil.createPost(rcRestUrl + "chat.sendMessage").addHeaders(headers).body(body).execute().body();
        JSONObject httpJson = JSONUtil.parseObj(http);
        if (!httpJson.getBool("success")) {
            throw new BizException(CodeCons.ERROR, "ERROR");
        }
    }
}
