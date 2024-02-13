package com.coin.resp.activity;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ActivityVo {

    private Long id;

    // 活动标题
    private String activityTitle;

    // 活动类型, 1:邀请好友 2:签到 3:抽奖
    private Integer activityType;

    // 排序,数值越小越靠前
    private Integer priority;

    // 开始展示时间
    private Date showTimeStart;

    // 截止展示时间
    private Date showTimeEnd;

    // 活动开始时间
    private Date activityTimeStart;

    // 活动截止时间
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

    // 创建人
    private String createdBy;

    // 创建时间
    private Date createdAt;

    // 修改人
    private String updatedBy;

    // 修改时间
    private Date updatedAt;

    // 删除人
    private String deletedBy;

    // 删除时间
    private Date deletedAt;

    // 奖品集合
    private List<ActivityPrizeVo> prizeList;
}
