package com.coin.service;

import com.coin.entity.ReplyExtend;
import com.coin.req.ReplyExtendReq;
import com.coin.resp.reply.ReplyExtendMgrVo;
import com.coin.resp.reply.ReplyVo;
import com.github.pagehelper.PageInfo;

public interface ReplyExtendService {

    void add(ReplyExtendReq req);

    void delete(ReplyExtendReq req);

    void update(ReplyExtendReq req);

    ReplyExtend getById(Long id);

    PageInfo<ReplyExtendMgrVo> pageList(ReplyExtendReq req);

    void reply(ReplyExtendReq req);

    PageInfo<ReplyVo> replyPageList(ReplyExtendReq req);

    void modifyReplyById(ReplyExtendReq req);

    void deleteReplyById(ReplyExtendReq req);
}
