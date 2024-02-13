package com.coin.service;

import com.coin.entity.Guess;
import com.coin.req.GuessReq;
import com.coin.resp.guess.GuessDetailVo;
import com.coin.resp.guess.GuessListVo;
import com.github.pagehelper.PageInfo;

public interface GuessService {

    void add(GuessReq req);

    void delete(GuessReq req);

    void update(GuessReq req);

    Guess getById(Long id);

    PageInfo<Guess> pageList(GuessReq req);

    void syncGuessStatus(Guess guess);

    Long countByGuessType(Long guessTypeId);

    GuessDetailVo detail(GuessReq req);

    void syncGuessStatusTask();

    PageInfo<GuessListVo> guessList(GuessReq req);

    void updateExtra(GuessReq req);

    String like(GuessReq req);

    String favorite(GuessReq req);
}
