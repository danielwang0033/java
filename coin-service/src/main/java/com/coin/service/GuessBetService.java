package com.coin.service;

import com.coin.req.GuessBetReq;
import com.coin.resp.guess.BetResultVo;
import com.coin.resp.guess.BetUserVo;
import com.coin.resp.guess.MyBetListVo;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface GuessBetService {

    PageInfo<BetUserVo> betUserList(GuessBetReq req);

    PageInfo<BetResultVo> betResultList(GuessBetReq req);

    void bet(GuessBetReq req);

    List<BetUserVo> allBetList(Long guessId, Long guessItemId);

    PageInfo<MyBetListVo> myBetList(GuessBetReq req);
}
