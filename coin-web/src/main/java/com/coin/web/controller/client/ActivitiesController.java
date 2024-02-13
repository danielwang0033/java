package com.coin.web.controller.client;

import com.coin.req.ActivityCheckInReq;
import com.coin.req.ActivityPrizeExchangeReq;
import com.coin.req.ActivityReq;
import com.coin.resp.MyResp;
import com.coin.resp.activity.*;
import com.coin.service.ActivityCheckInService;
import com.coin.service.ActivityPrizeExchangeService;
import com.coin.service.ActivityService;
import com.coin.service.constant.CodeCons;
import com.coin.service.exception.BizException;
import com.coin.service.util.ParamUtil;
import com.coin.web.annotation.CommonSecure;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/activities")
public class ActivitiesController {

    private static final Logger logger = LoggerFactory.getLogger(ActivitiesController.class);

    @Resource
    private ActivityService activityService;
    @Resource
    private ActivityPrizeExchangeService activityPrizeExchangeService;
    @Resource
    private ActivityCheckInService activityCheckInService;

    @PostMapping("/ongoingList")
    @CommonSecure(needLogin = false)
    public MyResp<List<ActivityOngoingVo>> ongoingList(@RequestBody ActivityReq req) {
        logger.info("activities-ongoingList-req={}", req);
        try {
            List<ActivityOngoingVo> list = activityService.ongoingActivities(req);
            return new MyResp<>(CodeCons.SUCCESS, "", list);
        } catch (BizException e) {
            logger.error("activities-ongoingList-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("activities-ongoingList-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "查询失败");
    }

    @PostMapping("/showDetail")
    @CommonSecure(needLogin = false)
    public MyResp<ActivityDetailVo> showDetail(@RequestBody ActivityReq req) {
        logger.info("activities-showDetail-req={}", req);
        try {
            ParamUtil.check(req.getId(), "id");
            ActivityDetailVo detail = activityService.showDetail(req);
            return new MyResp<>(CodeCons.SUCCESS, "", detail);
        } catch (BizException e) {
            logger.error("activities-showDetail-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("activities-showDetail-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "查询失败");
    }

    @PostMapping("/winningRecord")
    @CommonSecure
    public MyResp<PageInfo<WinningRecordVo>> winningRecord(@RequestBody ActivityPrizeExchangeReq req) {
        logger.info("activities-winningRecord-req={}", req);
        try {
            ParamUtil.check(req.getActivityId(), "activityId", req.getPage(), "page", req.getPagesize(), "pageSize");
            PageInfo<WinningRecordVo> page = activityPrizeExchangeService.winningRecord(req);
            return new MyResp<>(CodeCons.SUCCESS, "", page);
        } catch (BizException e) {
            logger.error("activities-winningRecord-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("activities-winningRecord-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "查询失败");
    }

    @PostMapping("/clickDraw")
    @CommonSecure
    public MyResp<ClickDrawVo> clickDraw(@RequestBody ActivityPrizeExchangeReq req) {
        logger.info("activities-clickDraw-req={}", req);
        try {
            ParamUtil.check(req.getActivityId(), "activityId");
            ClickDrawVo clickDrawVo = activityPrizeExchangeService.clickDraw(req);
            return new MyResp<>(CodeCons.SUCCESS, "", clickDrawVo);
        } catch (BizException e) {
            logger.error("activities-clickDraw-be", e);
            return new MyResp<>(CodeCons.DRAW_ERROR, e.getErrMsg());
        } catch (Exception e) {
            logger.error("activities-clickDraw-error", e);
        }
        return new MyResp<>(CodeCons.DRAW_ERROR, "抽奖失败");
    }

    @PostMapping("/clickCheckIn")
    @CommonSecure
    public MyResp<ClickCheckInVo> clickCheckIn(@RequestBody ActivityCheckInReq req) {
        logger.info("activities-clickCheckIn-req={}", req);
        try {
            ParamUtil.check(req.getActivityId(), "activityId");
            ClickCheckInVo clickCheckInVo = activityCheckInService.clickCheckIn(req);
            return new MyResp<>(CodeCons.SUCCESS, "", clickCheckInVo);
        } catch (BizException e) {
            logger.error("activities-clickCheckIn-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("activities-clickCheckIn-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "签到失败");
    }

    @PostMapping("/checkInHistory")
    @CommonSecure
    public MyResp<List<CheckInHistoryVo>> checkInHistory(@RequestBody ActivityCheckInReq req) {
        logger.info("activities-checkInHistory-req={}", req);
        try {
            ParamUtil.check(req.getActivityId(), "activityId");
            List<CheckInHistoryVo> history = activityCheckInService.checkInHistory(req);
            return new MyResp<>(CodeCons.SUCCESS, "", history);
        } catch (BizException e) {
            logger.error("activities-checkInHistory-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("activities-checkInHistory-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "查询失败");
    }
}