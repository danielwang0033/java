package com.coin.resp.activity;

import lombok.Data;

@Data
public class CheckInRecordVo {

    private String dateStr;

    // 周几
    private String dayOfWeek;

    // 几号
    private String dayOfMonth;

    // 是否是今日
    private boolean isToday;

    // 签到状态: 1:已签到 2:未签到 3:过期未签到
    private int signStatusType;

    // 签到状态
    private String signStatus;

    // 奖品
    private ActivityDetailPrizeVo prize;
}
