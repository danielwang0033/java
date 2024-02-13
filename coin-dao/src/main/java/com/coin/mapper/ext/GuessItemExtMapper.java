package com.coin.mapper.ext;

import com.coin.resp.guess.GuessItemVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GuessItemExtMapper {

    List<GuessItemVo> guessItemList(@Param("guessId") Long guessId, @Param("status") Integer status);

    void addBetUserCount(@Param("guessItemId") Long guessItemId);
}
