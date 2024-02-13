package com.coin.service;

import com.coin.entity.Activity;
import com.coin.entity.ActivityPrize;
import com.coin.resp.activity.ActivityPrizeVo;

import java.util.List;

public interface ActivityPrizeService {

    void addBatch(Activity activity, List<ActivityPrizeVo> prizeList);

    void deleteByActivityId(Long activityId);

    List<ActivityPrize> selectByActivity(Long activityId);

    ActivityPrize selectByActivityAndDate(Activity activity, String dateStr);

    List<ActivityPrize> selectByActivityAndDateList(Long activityId, Integer activityType, List<String> dateStrList);

    ActivityPrize selectByActivityAndSerialCount(Activity activity, int serialDayCount);
}
