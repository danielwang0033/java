package com.coin.resp.activity;

import lombok.Data;

@Data
public class CheckInHistoryVo {

    // 签到日期
    private String date;

    // 签到时间
    private String time;

    // 签到类型 0:连续签到 1:普通签到
    private Integer checkInType;

    // 奖品类型, 1:博币 2:经验 3:抽奖次数 4:改名卡 5:实物 6:未中奖
    private Integer prizeType;

    // 奖品数量
    private Integer prizeAmount;

    // 连续签到天数
    private Integer serialDays;
}