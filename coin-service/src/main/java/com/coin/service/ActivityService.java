package com.coin.service;

import com.coin.entity.Activity;
import com.coin.req.ActivityReq;
import com.coin.resp.activity.ActivityDetailVo;
import com.coin.resp.activity.ActivityOngoingVo;
import com.coin.resp.activity.ActivityVo;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ActivityService {

    void add(ActivityReq req);

    void delete(ActivityReq req);

    void update(ActivityReq req);

    Activity getById(Long id);

    PageInfo<Activity> pageList(ActivityReq req);

    ActivityVo getDetailById(Long id);

    void updateActivityStatus(ActivityReq req);

    List<ActivityOngoingVo> ongoingActivities(ActivityReq req);

    ActivityDetailVo showDetail(ActivityReq req);

    Activity verifyActivity(Long activityId);
}
