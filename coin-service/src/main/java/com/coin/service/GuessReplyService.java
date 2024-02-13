package com.coin.service;

import com.coin.entity.GuessReply;
import com.coin.req.GuessReplyReq;
import com.coin.resp.guess.GuessReplyVo;
import com.github.pagehelper.PageInfo;

public interface GuessReplyService {

    void add(GuessReplyReq req);

    void delete(GuessReplyReq req);

    void update(GuessReplyReq req);

    GuessReply getById(Long id);

    PageInfo<GuessReply> pageList(GuessReplyReq req);

    void reply(GuessReplyReq req);

    PageInfo<GuessReplyVo> replyList(GuessReplyReq req);
}
