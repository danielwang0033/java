package com.coin.mapper.ext;

import com.coin.resp.guess.GuessReplyVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GuessReplyExtMapper {

    List<GuessReplyVo> replyList(@Param("guessId") Long guessId);
}
