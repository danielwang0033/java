package com.coin.service;

import com.coin.entity.Rooms;
import com.coin.req.ChatInfoReq;
import com.coin.req.RoomsReq;
import com.coin.req.UsersReq;
import com.coin.resp.ChatInfoResp;
import com.coin.resp.MessagesResp;
import com.coin.resp.RoomsResp;
import com.coin.resp.UsersResp;
import com.github.pagehelper.PageInfo;

public interface RoomsService {

    void update(RoomsReq req);

    Rooms getById(Long id);

    PageInfo<MessagesResp> messageList(RoomsReq req);

    ChatInfoResp getInfo(ChatInfoReq req);

    PageInfo<RoomsResp> selectList(RoomsReq req);

    PageInfo<UsersResp> getChannelsMembers(UsersReq req);

    void channelsOnline(RoomsReq req);

    void setOwner(RoomsReq req);

    void kickRoom(RoomsReq req);

    void mute(RoomsReq req);

    void postMessage(RoomsReq req);

    void joinRoom(RoomsReq req);

}
