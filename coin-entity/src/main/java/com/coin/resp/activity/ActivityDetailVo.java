package com.coin.resp.activity;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ActivityDetailVo {

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

    private String backgroundImage;

    private String headImage;

    private String ruleImage;

    // 抽奖活动奖品(仅抽奖活动有效)
    private List<ActivityDetailPrizeVo> prizeList;

    // 剩余抽奖次数(仅抽奖活动有效)
    private Integer drawNumber;

    // 邀请码(仅邀请好友有效)
    private String inviteCode;

    // 签到记录(仅签到有效)
    private List<CheckInRecordVo> checkInList;

    // 连续签到天数(仅签到有效)
    private Integer serialDays;
}
