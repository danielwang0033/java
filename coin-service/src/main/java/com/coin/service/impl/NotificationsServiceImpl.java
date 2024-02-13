package com.coin.service.impl;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONConfig;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.coin.entity.Notifications;
import com.coin.entity.NotificationsExample;
import com.coin.mapper.NotificationsMapper;
import com.coin.mapper.ext.NotificationsExtMapper;
import com.coin.req.NotificationsReq;
import com.coin.resp.notification.NotificationsVo;
import com.coin.service.DictService;
import com.coin.service.NotificationsService;
import com.coin.service.UserLevelService;
import com.coin.service.UsersService;
import com.coin.service.asyn.AsyncNotificationService;
import com.coin.service.util.PageUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class NotificationsServiceImpl implements NotificationsService {

    private static final Logger logger = LoggerFactory.getLogger(NotificationsServiceImpl.class);

    private static final Map<String, String> TYPE_MAP = new HashMap<>();

    static {
        // 管理员后台发送  仅阅读 无跳转ok
        TYPE_MAP.put("App\\Notifications\\AdminMessage", "System.AdminMessage");
        // 关注人回复了狗庄评论ok
        TYPE_MAP.put("App\\Notifications\\ArticleBeReplied", "System.ArticleBeReplied");
        // 被别的用户关注时   跳转到关注者个人主页ok
        TYPE_MAP.put("App\\Notifications\\BeFollowed", "System.BeFollowed");
        // 关注的人发了新申诉时触发  跳转该帖子详情ok
        TYPE_MAP.put("App\\Notifications\\FolloweePostedReport", "System.FolloweePostedReport");
        // 关注人发新帖时触发  跳转该帖子详情ok
        TYPE_MAP.put("App\\Notifications\\FolloweePostedThread", "System.FolloweePostedThread");
        // 注册成功时触发  仅阅读 无跳转ok
        TYPE_MAP.put("App\\Notifications\\Registered", "System.Registered");
        // 申诉被回复   跳转到申诉详情 ok
        TYPE_MAP.put("App\\Notifications\\ReportBeReplied", "System.ReportBeReplied");
        // 被回复时触发   跳转该帖子详情ok
        TYPE_MAP.put("App\\Notifications\\ThreadBeReplied", "System.ThreadBeReplied");
        // 回复的回复ok
        TYPE_MAP.put("replyExtend", "System.ReplyExtend");
    }

    @Resource
    private NotificationsMapper notificationsMapper;
    @Resource
    private NotificationsExtMapper notificationsExtMapper;
    @Resource
    private UsersService usersService;
    @Resource
    private UserLevelService userLevelService;
    @Resource
    private DictService dictService;
    @Resource
    private AsyncNotificationService asyncNotificationService;

    @Override
    public PageInfo<NotificationsVo> notificationPageList(NotificationsReq req) {
        PageHelper.startPage(req.getPage(), req.getPagesize());
        NotificationsExample example = new NotificationsExample();
        NotificationsExample.Criteria criteria = example.createCriteria();
        criteria.andNotifiableIdEqualTo(req.getLoginUserId());
        example.setOrderByClause(" created_at desc");
        List<Notifications> datas = notificationsMapper.selectByExample(example);
        List<NotificationsVo> notificationsVoList = new ArrayList<>();
        JSONConfig jsonConfig = new JSONConfig();
        jsonConfig.setIgnoreNullValue(true);

        datas.forEach(item -> {
            String data = item.getData();
            JSONObject jsonObject = JSONUtil.parseObj(data, jsonConfig);
            String message = jsonObject.getStr("message");

            Object dataData = jsonObject.getObj("data");
            NotificationsVo vo = new NotificationsVo();
            vo.setId(item.getId());
            vo.setMessageTime(item.getCreatedAt());
            if (ObjectUtil.isNotNull(item.getReadAt())) {
                vo.setRead(true);
            }
            vo.setMessage(message);
            vo.setType(TYPE_MAP.get(item.getType()));
            vo.setExtraData(dataData);
            notificationsVoList.add(vo);
        });
        return PageUtil.pageInfo2PageRsp(new PageInfo<>(datas), notificationsVoList);
    }

    @Override
    public void readOne(NotificationsReq req) {
        Notifications notifications = notificationsMapper.selectByPrimaryKey(req.getNotificationId());
        if (ObjectUtil.isNotNull(notifications)) {
            Long notifiableId = notifications.getNotifiableId();
            if (NumberUtil.equals(notifiableId, req.getLoginUserId()) && ObjectUtil.isNull(notifications.getReadAt())) {
                Notifications update = new Notifications();
                update.setId(notifications.getId());
                update.setReadAt(new Date());
                notificationsMapper.updateByPrimaryKeySelective(update);
            }
        }
    }

    @Override
    public void buildNotification(NotificationsReq req) {
        String message = req.getMessage();
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("type", TYPE_MAP.get(req.getType()));
        dataMap.put("message", message);
        dataMap.put("data", req.getObject());

        // 消息通知
        Notifications notifications = new Notifications();
        notifications.setId(UUID.randomUUID().toString());
        notifications.setType(req.getType());
        notifications.setNotifiableType("App\\Models\\User");
        notifications.setNotifiableId(req.getNotifiableId());
        notifications.setData(JSONUtil.toJsonStr(dataMap));
        notifications.setCreatedAt(new Date());
        asyncNotificationService.sendExtendReplyNotification(notifications);
    }

    @Override
    public void readAll(NotificationsReq req) {
        if (ObjectUtil.isNotNull(req.getLoginUserId())) {
            notificationsExtMapper.readAll(req.getLoginUserId());
        }
    }

    @Override
    public Long unreadNotifyCount(Long userid) {
        return notificationsExtMapper.unreadNotifyCount(userid);
    }
}
