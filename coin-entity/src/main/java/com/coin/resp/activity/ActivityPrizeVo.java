package com.coin.resp.activity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ActivityPrizeVo {

    // 奖品类型, 1:博币 2:经验 3:抽奖次数 4:改名卡 5:实物 6:未中奖
    private Integer prizeType;

    // 奖品名称
    private String prizeName;

    // 奖品数量
    private Integer prizeQuantity;

    // 奖品图片
    private String prizeImage;

    // 中奖概率(仅抽奖有效)
    private BigDecimal probability;

    // 签到类型 0:连续签到 1:普通签到 (仅签到有效)
    private Integer checkInType;

    // 距离活动开始天数或连续签到天数 (仅签到有效)
    private Integer checkInDays;

    // 对应签到日期 (仅签到有效)
    private String checkInDate;
}
