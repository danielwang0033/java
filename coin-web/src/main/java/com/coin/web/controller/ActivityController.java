package com.coin.web.controller;

import cn.hutool.core.util.NumberUtil;
import com.coin.entity.Activity;
import com.coin.req.ActivityReq;
import com.coin.resp.MyResp;
import com.coin.resp.activity.ActivityVo;
import com.coin.service.ActivityService;
import com.coin.service.constant.CodeCons;
import com.coin.service.exception.BizException;
import com.coin.service.util.ParamUtil;
import com.coin.web.annotation.OfficeSecure;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/activity")
public class ActivityController {

    private static final Logger logger = LoggerFactory.getLogger(ActivityController.class);

    @Resource
    private ActivityService activityService;

    @PostMapping("/add")
    @OfficeSecure
    public MyResp<String> mgrAdd(@RequestBody ActivityReq req) {
        logger.info("mgr-activity-add-req={}", req);
        try {
            ParamUtil.check(req.getActivityType(), "activityType",
                    req.getActivityTitle(), "activityTitle",
                    req.getShowTimeStart(), "showTimeStart",
                    req.getShowTimeEnd(), "showTimeEnd",
                    req.getActivityTimeStart(), "activityTimeStart",
                    req.getActivityTimeEnd(), "activityTimeEnd",
                    req.getStatus(), "status");
            if (NumberUtil.equals(req.getStatus(), 1)) {
                // 如果是点击发布
                ParamUtil.check(req.getPriority(), "priority",
                        req.getFloatButtonImage(), "floatButtonImage",
                        req.getFloatButtonImageH5(), "floatButtonImageH5",
                        req.getHeadImage(), "headImage",
                        req.getHeadImageH5(), "headImageH5",
                        req.getBackgroundImage(), "backgroundImage",
                        req.getBackgroundImageH5(), "backgroundImageH5",
                        req.getRuleImage(), "ruleImage",
                        req.getRuleImageH5(), "ruleImageH5");
            }
            activityService.add(req);
            return new MyResp<>(CodeCons.SUCCESS, "添加成功");
        } catch (BizException e) {
            logger.error("mgr-activity-add-be:", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("mgr-activity-add-error:", e);
        }
        return new MyResp<>(CodeCons.ERROR, "添加失败");
    }

    @PostMapping("/del")
    @OfficeSecure
    public MyResp<String> mgrDel(@RequestBody ActivityReq req) {
        logger.info("mgr-activity-del-req={}", req);
        try {
            ParamUtil.check(req.getId(), "ID");
            activityService.delete(req);
            return new MyResp<>(CodeCons.SUCCESS, "删除成功");
        } catch (BizException e) {
            logger.error("mgr-activity-del-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("mgr-activity-del-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "删除失败");
    }

    @PostMapping("/update")
    @OfficeSecure
    public MyResp<String> mgrUpdate(@RequestBody ActivityReq req) {
        logger.info("mgr-activity-update-req={}", req);
        try {
            ParamUtil.check(req.getId(), "id",
                    req.getActivityType(), "activityType",
                    req.getActivityTitle(),
                    "activityTitle",
                    req.getShowTimeStart(), "showTimeStart",
                    req.getShowTimeEnd(), "showTimeEnd",
                    req.getActivityTimeStart(), "activityTimeStart",
                    req.getActivityTimeEnd(), "activityTimeEnd",
                    req.getShowTimeStart(), "showTimeStart",
                    req.getShowTimeEnd(), "showTimeEnd",
                    req.getActivityTimeStart(), "activityTimeStart",
                    req.getActivityTimeEnd(), "activityTimeEnd",
                    req.getStatus(), "status");
            if (NumberUtil.equals(req.getStatus(), 1)) {
                // 如果是点击发布
                ParamUtil.check(req.getPriority(), "priority",
                        req.getFloatButtonImage(), "floatButtonImage",
                        req.getFloatButtonImageH5(), "floatButtonImageH5",
                        req.getHeadImage(), "headImage",
                        req.getHeadImageH5(), "headImageH5",
                        req.getBackgroundImage(), "backgroundImage",
                        req.getBackgroundImageH5(), "backgroundImageH5",
                        req.getRuleImage(), "ruleImage",
                        req.getRuleImageH5(), "ruleImageH5");
            }
            activityService.update(req);
            return new MyResp<>(CodeCons.SUCCESS, "修改成功");
        } catch (BizException e) {
            logger.error("mgr-activity-update-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("mgr-activity-update-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "更新失败");
    }

    @PostMapping("/detail")
    @OfficeSecure
    public MyResp<ActivityVo> detail(@RequestBody ActivityReq req) {
        logger.info("mgr-activity-detail-req={}", req);
        try {
            ParamUtil.check(req.getId(), "ID");
            ActivityVo rsp = activityService.getDetailById(req.getId());
            return new MyResp<>(CodeCons.SUCCESS, "", rsp);
        } catch (BizException e) {
            logger.error("mgr-activity-detail-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("mgr-activity-detail-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "查询失败");
    }

    @PostMapping("/updateActivityStatus")
    @OfficeSecure
    public MyResp<ActivityVo> updateActivityStatus(@RequestBody ActivityReq req) {
        logger.info("mgr-activity-updateActivityStatus-req={}", req);
        try {
            ParamUtil.check(req.getId(), "ID", req.getStatus(), "status");
            activityService.updateActivityStatus(req);
            return new MyResp<>(CodeCons.SUCCESS, "操作成功");
        } catch (BizException e) {
            logger.error("mgr-activity-updateActivityStatus-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("mgr-activity-updateActivityStatus-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "更新失败");
    }

    @PostMapping("/pageList")
    @OfficeSecure
    public MyResp<PageInfo<Activity>> mgrPageList(@RequestBody ActivityReq req) {
        logger.info("mgr-activity-pageList-req={}", req);
        try {
            ParamUtil.check(req.getPage(), "page", req.getPagesize(), "pageSize");
            PageInfo<Activity> page = activityService.pageList(req);
            return new MyResp<>(CodeCons.SUCCESS, "", page);
        } catch (BizException e) {
            logger.error("mgr-activity-pageList-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("mgr-activity-pageList-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "查询失败");
    }
}