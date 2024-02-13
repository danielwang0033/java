package com.coin.mapper.ext;

import com.coin.req.ReplyExtendReq;
import com.coin.resp.reply.ReplyExtendVo;
import com.coin.resp.reply.ReplyVo;

import java.util.List;

public interface ReplyExtendExtMapper {

    List<ReplyVo> selectThreadReplyList(ReplyExtendReq req);

    List<ReplyVo> selectReportReplyList(ReplyExtendReq req);

    List<ReplyVo> selectAritcleReplyList(ReplyExtendReq req);

    List<ReplyVo> selectGuessReplyList(ReplyExtendReq req);

    List<ReplyExtendVo> selectReplyExtendList(ReplyExtendReq req);
}
