package com.coin.service;

import com.coin.entity.ActivityCheckIn;
import com.coin.req.ActivityCheckInReq;
import com.coin.resp.activity.CheckInHistoryVo;
import com.coin.resp.activity.ClickCheckInVo;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface ActivityCheckInService {

    List<ActivityCheckIn> findUserCheckInRecord(Long activityId, Long loginUserId);

    ClickCheckInVo clickCheckIn(ActivityCheckInReq req);

    Integer calcSerialDays(Date now, Map<String, ActivityCheckIn> checkInMap, Date activityTimeStart, Date activityTimeEnd);

    List<CheckInHistoryVo> checkInHistory(ActivityCheckInReq req);
}
