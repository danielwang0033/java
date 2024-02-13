package com.coin.service;

import com.coin.entity.GuessItem;
import com.coin.req.GuessItemReq;
import com.coin.resp.guess.GuessItemVo;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface GuessItemService {

    Long add(GuessItemReq req);

    void delete(GuessItemReq req);

    void deleteDirect(Long id);

    void update(GuessItemReq req);

    GuessItem getById(Long id);

    PageInfo<GuessItem> itemList(GuessItemReq req);

    List<GuessItemVo> guessItemAvailableList(Long guessId);

    List<GuessItemVo> guessItemAll(Long guessId);

    boolean addBetUserCount(Long guessId, Long guessItemId, Long userId);

    void calcAward(GuessItemReq req);

    void calcAwardRevoke(GuessItemReq req);

    void settle(GuessItemReq req);

    void settleFast(GuessItemReq req);

    List<GuessItem> selectByGuessIdList(List<Long> guessIdList);
}
