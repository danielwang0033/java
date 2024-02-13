package com.coin.req;

import com.coin.resp.activity.ActivityPrizeVo;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class ActivityReq extends CommonReq {

    // 活动标题
    private String activityTitle;

    // 活动类型, 1:邀请好友 2:签到 3:抽奖
    private Integer activityType;

    // 排序,数值越小越靠前
    private Integer priority;

    // 开始展示时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date showTimeStart;

    // 截止展示时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date showTimeEnd;

    // 活动开始时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date activityTimeStart;

    // 活动截止时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date activityTimeEnd;

    // 悬浮按钮图
    private String floatButtonImage;

    // 悬浮按钮图_H5
    private String floatButtonImageH5;

    // 背景图
    private String backgroundImage;

    // 背景图_H5
    private String backgroundImageH5;

    // 头部图
    private String headImage;

    // 头部图_H5
    private String headImageH5;

    // 规则图
    private String ruleImage;

    // 规则图_H5
    private String ruleImageH5;

    // 状态, -1:删除 0: 停用 1:启用
    private Integer status;

    // 奖品集合
    private List<ActivityPrizeVo> prizeList;

    // 确认是否是H5端
    private boolean h5Flag;

    // 开始展示时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date showTimeFrom;

    // 截止展示时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date showTimeTo;
}
