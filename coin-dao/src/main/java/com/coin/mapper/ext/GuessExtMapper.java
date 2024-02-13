package com.coin.mapper.ext;

import com.coin.entity.Guess;
import com.coin.req.GuessReq;
import com.coin.resp.guess.GuessListVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GuessExtMapper {

    Long countByGuessType(@Param("guessTypeId") Long guessTypeId);

    int addCommentCount(@Param("guessId") Long guessId);

    int subtractCommentAmount(@Param("guessId") Long guessId);

    List<Guess> selectWaitSync(@Param("nowBefore") String nowBefore, @Param("nowAfter") String nowAfter);

    List<GuessListVo> guessList(@Param("req") GuessReq req);

    int addBetUserCount(@Param("guessId") Long guessId);

    int addVisitsAmount(@Param("guessId") Long guessId, @Param("addNum") Integer addNum);
}