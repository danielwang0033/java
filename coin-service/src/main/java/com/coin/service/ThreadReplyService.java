package com.coin.service;

import com.coin.entity.ThreadReply;
import com.coin.req.ThreadReplyReq;
import com.coin.resp.ThreadReplyResp;
import com.coin.resp.dict.ExtraMsgVo;
import com.coin.resp.thread.ThreadReplyVo;
import com.coin.resp.thread.ThreadsReplyCountVo;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ThreadReplyService {

    void delete(ThreadReplyReq req);

    void update(ThreadReplyReq req);

    ThreadReply getById(Long id);

    PageInfo<ThreadReplyResp> pageList(ThreadReplyReq req);

    List<ThreadsReplyCountVo> countByThreadIdList(List<Long> threadIdList);

    PageInfo<ThreadReplyVo> threadReplyByUserId(ThreadReplyReq req);

    ExtraMsgVo reply(ThreadReplyReq req);

    void modifyThreadReplyById(ThreadReplyReq req);

    void deleteThreadReply(ThreadReplyReq req);

    Integer countByThreadId(Long threadId);
}
