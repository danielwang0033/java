package com.coin.mapper.ext;

import com.coin.resp.guess.GuessTypeVo;

import java.util.List;

public interface GuessTypeExtMapper {

    List<GuessTypeVo> getAvailableList();
}
