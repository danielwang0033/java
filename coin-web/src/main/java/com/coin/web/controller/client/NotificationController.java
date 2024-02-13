package com.coin.web.controller.client;

import com.coin.req.NotificationsReq;
import com.coin.resp.MyResp;
import com.coin.resp.notification.NotificationsVo;
import com.coin.service.NotificationsService;
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

// 09_消息提醒
@RestController
@RequestMapping("/notifications")
public class NotificationController {

    private static final Logger logger = LoggerFactory.getLogger(NotificationController.class);

    @Resource
    private NotificationsService notificationsService;

    // 01_消息提醒分页查询
    @PostMapping("/notificationPageList")
    @CommonSecure
    public MyResp<PageInfo<NotificationsVo>> notificationPageList(@RequestBody NotificationsReq req) {
        logger.info("notifications-notificationPageList-req={}", req);
        try {
            ParamUtil.check(req.getPage(), "page", req.getPagesize(), "pageSize");
            PageInfo<NotificationsVo> page = notificationsService.notificationPageList(req);
            return new MyResp<>(CodeCons.SUCCESS, "", page);
        } catch (BizException e) {
            logger.error("notifications-notificationPageList-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("notifications-notificationPageList-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "查询失败");
    }

    // 02_已读
    @PostMapping("/readOne")
    @CommonSecure
    public MyResp<String> readOne(@RequestBody NotificationsReq req) {
        logger.info("notifications-readOne-req={}", req);
        try {
            ParamUtil.check(req.getNotificationId(), "notificationId");
            notificationsService.readOne(req);
            return new MyResp<>(CodeCons.SUCCESS, "操作成功");
        } catch (BizException e) {
            logger.error("notifications-readOne-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("notifications-readOne-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "操作失败");
    }

    // 03_全部已读
    @PostMapping("/readAll")
    @CommonSecure
    public MyResp<String> readAll(@RequestBody NotificationsReq req) {
        logger.info("notifications-readAll-req={}", req);
        try {
            notificationsService.readAll(req);
            return new MyResp<>(CodeCons.SUCCESS, "操作成功");
        } catch (BizException e) {
            logger.error("notifications-readAll-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("notifications-readAll-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "操作失败");
    }
}