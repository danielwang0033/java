package com.coin.service;

import com.coin.entity.Activity;
import com.coin.entity.ActivityPrize;
import com.coin.entity.ActivityPrizeExchange;
import com.coin.req.ActivityPrizeExchangeReq;
import com.coin.resp.activity.ClickDrawVo;
import com.coin.resp.activity.WinningRecordVo;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ActivityPrizeExchangeService {

    PageInfo<WinningRecordVo> winningRecord(ActivityPrizeExchangeReq req);

    ClickDrawVo clickDraw(ActivityPrizeExchangeReq req);

    ActivityPrizeExchange saveExchangeLog(Long userId, String userName, Activity activity, ActivityPrize item, String remark);

    List<ActivityPrizeExchange> findByIdList(List<Long> exchangeIdList, Long userId);
}
