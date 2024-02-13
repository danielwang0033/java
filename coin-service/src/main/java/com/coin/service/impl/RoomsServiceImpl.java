package com.coin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.coin.entity.Rooms;
import com.coin.entity.RoomsExample;
import com.coin.entity.Users;
import com.coin.entity.UsersExample;
import com.coin.mapper.RoomsMapper;
import com.coin.mapper.UsersMapper;
import com.coin.mapper.ext.RoomsExtMapper;
import com.coin.mapper.ext.UsersExtMapper;
import com.coin.req.ChatInfoReq;
import com.coin.req.RoomsReq;
import com.coin.req.UsersReq;
import com.coin.resp.ChatInfoResp;
import com.coin.resp.MessagesResp;
import com.coin.resp.RoomsResp;
import com.coin.resp.UsersResp;
import com.coin.service.ChatsService;
import com.coin.service.DictService;
import com.coin.service.RoomsService;
import com.coin.service.constant.CodeCons;
import com.coin.service.exception.BizException;
import com.coin.service.util.DateUtil;
import com.coin.service.util.ImageUtil;
import com.coin.service.util.LocalShortCache;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class RoomsServiceImpl implements RoomsService {

    private static final Logger logger = LoggerFactory.getLogger(RoomsServiceImpl.class);

    @Resource
    private RoomsMapper roomsMapper;

    @Value("${chat.rcInstance}")
    private String rcInstance;
    @Value("${chat.rcRestUrl}")
    private String rcRestUrl;
    @Value("${chat.rcWsUrl}")
    private String rcWsUrl;
    @Value("${chat.rcRid}")
    private String rcRid;
    @Value("${chat.rcAdminId}")
    private String rcAdminId;
    @Value("${chat.rcAdminToken}")
    private String rcAdminToken;

    @Resource
    private RoomsExtMapper roomsExtMapper;
    @Resource
    private ChatsService chatsService;
    @Resource
    private UsersMapper usersMapper;
    @Resource
    private UsersExtMapper usersExtMapper;
    @Resource
    private DictService dictService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(RoomsReq req) {
        Rooms oldContest = roomsMapper.selectByPrimaryKey(req.getId());
        if (ObjectUtil.isNull(oldContest)) {
            throw new BizException(CodeCons.ERROR, "记录不存在");
        }
        Date now = new Date();
        Rooms updateRooms = new Rooms();
        updateRooms.setId(req.getId());
        if (ObjectUtil.isNotNull(req.getOwner())) {
            updateRooms.setOwner(req.getOwner());
            this.setOwner(req);
        }
        if (ObjectUtil.isNotNull(req.getMute())) {
            updateRooms.setMute(req.getMute());
        }
        updateRooms.setUpdatedAt(now);
        roomsMapper.updateByPrimaryKeySelective(updateRooms);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void kickRoom(RoomsReq req) {
        RoomsExample example = new RoomsExample();
        RoomsExample.Criteria criteria = example.createCriteria();
        if (req.getId() != null) {
            criteria.andIdEqualTo(req.getId());
        }
        if (StrUtil.isNotBlank(req.getRid())) {
            criteria.andRidEqualTo(req.getRid().trim());
        }
        if (StrUtil.isNotBlank(req.getRocketUserId())) {
            criteria.andRocketUserIdEqualTo(req.getRocketUserId());
        }
        List<Rooms> datas = roomsMapper.selectByExample(example);
        if (CollectionUtil.isEmpty(datas)) {
            throw new BizException(CodeCons.ERROR, "已被踢出");
        }
        Rooms oldContest = datas.get(0);

        req.setRid(oldContest.getRid());
        req.setRocketUserId(oldContest.getRocketUserId());
        chatsService.kickRoom(req);

        Long id = oldContest.getId();
        roomsMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Rooms getById(Long id) {
        Rooms rooms = roomsMapper.selectByPrimaryKey(id);
        if (ObjectUtil.isNull(rooms)) {
            throw new BizException(CodeCons.ERROR, "记录不存在");
        }
        return rooms;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public PageInfo<UsersResp> getChannelsMembers(UsersReq req) {
        PageHelper.startPage(req.getPage(), req.getPagesize());
        List<UsersResp> datas = usersExtMapper.selectJoinRoomByNameAndStatus(req.getName(), req.getRocketStatus(), req.getType());
        if (CollectionUtil.isEmpty(datas)) {
            return new PageInfo<>(datas);
        }
        for (UsersResp item : datas) {
            item.setAvatar(ImageUtil.completeImageUrl(item.getAvatar()));
        }
        return new PageInfo<>(datas);
    }

    @Override
    public ChatInfoResp getInfo(ChatInfoReq req) {
        Users user = usersMapper.selectByPrimaryKey(req.getLoginUserId());
        BeanUtil.copyProperties(user, req);
        req.setRocketUserName("f" + req.getId());
        req.setRid(rcRid);
        req.setOptRocketUserId(user.getRocketUserId());
        user.setAvatar(ImageUtil.completeImageUrl(user.getAvatar()));
        if (StrUtil.isEmpty(user.getRocketUserId())) {
            JSONObject jsonObject = chatsService.createUser(req);
            //保存，rocketUserId
            JSONObject userJson = jsonObject.getJSONObject("user");
            user.setRocketUserId(userJson.getStr("_id"));
            user.setRocketUsername(userJson.getStr("username"));
            req.setAvatar(user.getAvatar());
            chatsService.setAvatar(req);
        }
        if (StrUtil.isEmpty(user.getRocketUserLoginToken())) {
            JSONObject loginObject = chatsService.login(req);
            user.setRocketUserLoginToken(loginObject.getJSONObject("data").getStr("authToken"));
            usersMapper.updateByPrimaryKeySelective(user);
        }

        RoomsExample example = new RoomsExample();
        RoomsExample.Criteria criteria = example.createCriteria();
        criteria.andRidEqualTo(rcRid);
        criteria.andRocketUserIdEqualTo(user.getRocketUserId());
        List<Rooms> rooms = roomsMapper.selectByExample(example);
        Integer mute = 0;
        Integer owner = 0;
        if (CollectionUtil.isEmpty(rooms)) {
            Rooms room = new Rooms();
            room.setRid(req.getRid());
            room.setRocketUserId(req.getRocketUserId());
            roomsMapper.insertSelective(room);
            //加入
            RoomsReq roomsReq = new RoomsReq();
            BeanUtil.copyProperties(room, roomsReq);
            roomsReq.setOptRocketUserLoginToken(rcAdminToken);
            roomsReq.setOptRocketUserId(rcAdminId);
            chatsService.join(roomsReq);
        } else {
            mute = rooms.get(0).getMute();
            owner = rooms.get(0).getOwner();
        }
        return getChatInfoResp(user, mute, owner);
    }

    private ChatInfoResp getChatInfoResp(Users user, Integer mute, Integer owner) {
        String avatar = user.getAvatar();
        if (StrUtil.isNotBlank(avatar)) {
            avatar = ImageUtil.completeImageUrl(avatar);
        } else {
            String defaultUserAvatar = dictService.getDefaultUserAvatar();
            avatar = ImageUtil.completeImageUrl(defaultUserAvatar);
        }

        ChatInfoResp chatInfoResp = new ChatInfoResp();
        chatInfoResp.setWsUrl(rcWsUrl);
        chatInfoResp.setHttpUrl(rcInstance);
        chatInfoResp.setUserId(user.getRocketUserId());
        chatInfoResp.setUsername(user.getRocketUsername());
        chatInfoResp.setName(user.getName());
        chatInfoResp.setAvatar(avatar);
        chatInfoResp.setOwner(owner);
        chatInfoResp.setUserToken(user.getRocketUserLoginToken());
        chatInfoResp.setRoomId(rcRid);

        chatInfoResp.setMute(mute);
        return chatInfoResp;
    }

    @Override
    public PageInfo<MessagesResp> messageList(RoomsReq req) {
        String cacheKey = LocalShortCache.CHAT_MESSAGE;
        PageInfo<MessagesResp> cache = LocalShortCache.getCache(cacheKey);
        if (cache == null) {
            // 默认缓存8秒
            int cacheSecond = 8;
            JSONObject datas = chatsService.historyMessage(req);
            List<MessagesResp> messagesRespList = new ArrayList<>();
            if (ObjectUtil.isNull(datas) || ObjectUtil.isNull(datas.getJSONArray("messages"))) {
                PageInfo<MessagesResp> empty = new PageInfo<>(messagesRespList);
                LocalShortCache.putCache(cacheKey, empty, cacheSecond);
                return empty;
            }
            JSONArray messages = datas.getJSONArray("messages");
            for (int i = 0; i < messages.size(); i++) {
                JSONObject message = messages.getJSONObject(i);
                if (StrUtil.isNotEmpty(message.getStr("t"))) {
                    continue;
                }
                MessagesResp messagesResp = new MessagesResp();
                messagesResp.setMessageId(message.getStr("_id"));
                messagesResp.setMsg(message.getStr("msg"));

                String uname = message.getJSONObject("u").getStr("username");
                try {
                    messagesResp.setTs(DateUtil.getDateByStrTz(message.getStr("ts")).getTime());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                messagesResp.setUserId(message.getJSONObject("u").getStr("_id"));
                messagesResp.setUsername(uname);
                messagesResp.setName(message.getJSONObject("u").getStr("name"));
                messagesResp.setAvatar(rcInstance + "avatar/" + uname);
                messagesRespList.add(messagesResp);
            }
            PageInfo<MessagesResp> messagesRespPageInfo = new PageInfo<>(messagesRespList);
            LocalShortCache.putCache(cacheKey, messagesRespPageInfo, cacheSecond);
            return messagesRespPageInfo;
        }
        return cache;
    }

    @Override
    public void channelsOnline(RoomsReq req) {
        JSONArray datas = chatsService.channelsOnline(req).getJSONArray("online");
        if (CollectionUtil.isEmpty(datas)) {
            logger.info("无在线用户");
            return;
        }
        //上线更新成下线
        UsersExample example = new UsersExample();
        UsersExample.Criteria criteria = example.createCriteria();
        criteria.andRocketUserIdIsNotNull();
        criteria.andRocketStatusEqualTo("online");
        Users users = new Users();
        users.setRocketStatus("offline");
        usersMapper.updateByExampleSelective(users, example);

        //更新上线
        List<String> userIds = new ArrayList<>();
        for (int i = 0; i < datas.size(); i++) {
            userIds.add(datas.getJSONObject(i).getStr("_id"));
        }
        UsersExample example2 = new UsersExample();
        UsersExample.Criteria criteria2 = example2.createCriteria();
        criteria2.andRocketUserIdIn(userIds);
        Users users2 = new Users();
        users2.setRocketStatus("online");
        usersMapper.updateByExampleSelective(users2, example2);
    }

    @Override
    public PageInfo<RoomsResp> selectList(RoomsReq req) {
        PageHelper.startPage(req.getPage(), req.getPagesize());
        List<RoomsResp> datas = roomsExtMapper.selectList(req);
        return new PageInfo<>(datas);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void joinRoom(RoomsReq req) {
        chatsService.join(req);
        //添加记录
        Rooms rooms = new Rooms();
        rooms.setRocketUserId(req.getRocketUserId());
        rooms.setRid(req.getRid());
        roomsMapper.insertSelective(rooms);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void mute(RoomsReq req) {
        //判断用户是否是管理员
        RoomsExample example = new RoomsExample();
        RoomsExample.Criteria criteria = example.createCriteria();
        criteria.andRidEqualTo(rcRid);
        criteria.andRocketUserIdEqualTo(req.getRocketUserId());
        List<Rooms> rooms = roomsMapper.selectByExample(example);
        if (CollectionUtil.isEmpty(rooms)) {
            throw new BizException(CodeCons.ERROR, "用户不在此房间");
        }
        Rooms room = rooms.get(0);
        room.setMute(req.getMute());
        roomsMapper.updateByPrimaryKey(room);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void postMessage(RoomsReq req) {
        req.setRid(rcRid);
        RoomsExample example = new RoomsExample();
        RoomsExample.Criteria criteria = example.createCriteria();
        criteria.andRidEqualTo(req.getRid());
        criteria.andRocketUserIdEqualTo(req.getOptRocketUserId());
        List<Rooms> rooms = roomsMapper.selectByExample(example);
        if (CollectionUtil.isEmpty(rooms)) {
            throw new BizException(CodeCons.ERROR, "用户不在此房间");
        }
        if (rooms.get(0).getMute() != null && rooms.get(0).getMute() == 1) {
            throw new BizException(CodeCons.ERROR, "用户被禁言");
        }
        chatsService.sendMessage(req);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void setOwner(RoomsReq req) {
        Rooms oldContest = roomsMapper.selectByPrimaryKey(req.getId());
        if (ObjectUtil.isNull(oldContest)) {
            throw new BizException(CodeCons.ERROR, "用户不在此房间");
        }
        req.setRid(oldContest.getRid());
        req.setRocketUserId(oldContest.getRocketUserId());
        if (req.getOwner() == 1) {
            chatsService.addOwner(req);
        } else {
            chatsService.removeOwner(req);
        }

        Date now = new Date();
        Rooms updateRooms = new Rooms();
        updateRooms.setId(req.getId());
        //设置管理员
        updateRooms.setOwner(req.getOwner());
        updateRooms.setUpdatedAt(now);
        roomsMapper.updateByPrimaryKeySelective(updateRooms);
    }
}
