package com.coin.service;

import cn.hutool.json.JSONObject;
import com.coin.req.ChatInfoReq;
import com.coin.req.MatchsReq;
import com.coin.req.RoomsReq;

public interface ChatsService {

    String createRoom(MatchsReq req);

    void addOwner(RoomsReq req);

    void removeOwner(RoomsReq req);

    void deleteRoom(RoomsReq req);

    void kickRoom(RoomsReq req);

    void setAvatar(ChatInfoReq req);

    void sendMessage(RoomsReq req);

    JSONObject createUser(ChatInfoReq req);

    JSONObject login(ChatInfoReq req);

    void join(RoomsReq req);

    JSONObject historyMessage(RoomsReq req);

    JSONObject getChannelsMembers(RoomsReq req);

    JSONObject channelsOnline(RoomsReq req);
}
