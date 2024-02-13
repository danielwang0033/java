package com.coin.mapper.ext;

import com.coin.req.GuessBetReq;
import com.coin.resp.guess.BetResultVo;
import com.coin.resp.guess.BetUserVo;
import com.coin.resp.guess.MyBetListVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GuessBetExtMapper {

    List<BetUserVo> betUserList(@Param("req") GuessBetReq req);

    List<BetResultVo> betResultList(@Param("req") GuessBetReq req);

    Long historyBet(@Param("guessId") Long guessId, @Param("guessItemId") Long guessItemId, @Param("userId") Long userId);

    List<MyBetListVo> myBetList(@Param("req") GuessBetReq req);
}
