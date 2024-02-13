package com.coin.web.controller.client;

import com.coin.req.ChatInfoReq;
import com.coin.req.RoomsReq;
import com.coin.req.UsersReq;
import com.coin.resp.ChatInfoResp;
import com.coin.resp.MessagesResp;
import com.coin.resp.MyResp;
import com.coin.resp.UsersResp;
import com.coin.service.RoomsService;
import com.coin.service.constant.CodeCons;
import com.coin.service.exception.BizException;
import com.coin.service.util.ParamUtil;
import com.coin.web.annotation.CommonSecure;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

// 04_赛事直播
@RestController
@RequestMapping("/chat")
public class RoomController {

    private static final Logger logger = LoggerFactory.getLogger(RoomController.class);

    @Resource
    private RoomsService roomsService;

    // 01_房间消息
    @PostMapping("/channels.messages")
    @CommonSecure(needLogin = false)
    public MyResp<PageInfo<MessagesResp>> pageList(@RequestBody RoomsReq req) {
        logger.info("rooms-lists-req={}", req);
        try {
            ParamUtil.check(req.getPage(), "page", req.getPagesize(), "pageSize");
            PageInfo<MessagesResp> page = roomsService.messageList(req);
            return new MyResp<>(CodeCons.SUCCESS, "", page);
        } catch (BizException e) {
            logger.error("rooms-lists-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("rooms-lists-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "查询失败");
    }

    // 02_初始化
    @PostMapping("/getInfo")
    @CommonSecure
    public MyResp<ChatInfoResp> getInfo(@RequestBody ChatInfoReq req) {
        logger.info("rooms-lists-req={}", req);
        try {
            ChatInfoResp page = roomsService.getInfo(req);
            return new MyResp<>(CodeCons.SUCCESS, "", page);
        } catch (BizException e) {
            logger.error("rooms-lists-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("rooms-lists-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "查询失败");
    }

    // 01_会员列表
    @PostMapping("/channels.members")
    @CommonSecure(needChatLogin = true)
    public MyResp<PageInfo<UsersResp>> getChannelsMembers(@RequestBody UsersReq req) {
        logger.info("rooms-lists-req={}", req);
        try {
            ParamUtil.check(req.getPage(), "page", req.getPagesize(), "pageSize");
            PageInfo<UsersResp> page = roomsService.getChannelsMembers(req);
            return new MyResp<>(CodeCons.SUCCESS, "", page);
        } catch (BizException e) {
            logger.error("rooms-lists-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("rooms-lists-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "查询失败");
    }

    // 01_提出
    @PostMapping("/channels.kick")
    @CommonSecure(needChatLogin = true, needChatManage = true)
    public MyResp<String> kickRoom(@RequestBody RoomsReq req) {
        logger.info("rooms-lists-req={}", req);
        try {
            roomsService.kickRoom(req);
            return new MyResp<>(CodeCons.SUCCESS, "操作成功");
        } catch (BizException e) {
            logger.error("rooms-lists-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("rooms-lists-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "操作失败");
    }

    // 01_加入
    @PostMapping("/channels.invite")
    @CommonSecure(needChatLogin = true, needChatManage = true)
    public MyResp<String> joinRoom(@RequestBody RoomsReq req) {
        logger.info("rooms-lists-req={}", req);
        try {
            roomsService.joinRoom(req);
            return new MyResp<>(CodeCons.SUCCESS, "");
        } catch (BizException e) {
            logger.error("rooms-lists-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("rooms-lists-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "操作失败");
    }

    // 01_禁言
    @PostMapping("/channels.mute")
    @CommonSecure(needChatLogin = true, needChatManage = true)
    public MyResp<String> mute(@RequestBody RoomsReq req) {
        logger.info("rooms-lists-req={}", req);
        try {
            roomsService.mute(req);
            return new MyResp<>(CodeCons.SUCCESS, "");
        } catch (BizException e) {
            logger.error("rooms-lists-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("rooms-lists-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "操作失败");
    }

    // 01_发送消息
    @PostMapping("/chat.postMessage")
    @CommonSecure(needChatLogin = true)
    public MyResp<String> postMessage(@RequestBody RoomsReq req) {
        logger.info("rooms-postMessage-req={}", req);
        try {
            roomsService.postMessage(req);
            return new MyResp<>(CodeCons.SUCCESS, "");
        } catch (BizException e) {
            logger.error("rooms-postMessage-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("rooms-postMessage-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "发送失败");
    }
}