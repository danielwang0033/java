package com.coin.mapper.ext;

import com.coin.req.ThreadReplyReq;
import com.coin.resp.ThreadReplyResp;
import com.coin.resp.thread.ThreadReplyVo;
import com.coin.resp.thread.ThreadsReplyCountVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ThreadReplyExtMapper {

    List<ThreadReplyResp> pageList(@Param("req") ThreadReplyReq req);

    List<ThreadsReplyCountVo> countByThreadIdList(@Param("threadIdList") List<Long> threadIdList);

    List<ThreadReplyVo> threadReplyByUserId(@Param("req") ThreadReplyReq req);

    Long countByThreadId(@Param("threadId") Long threadId);
}
