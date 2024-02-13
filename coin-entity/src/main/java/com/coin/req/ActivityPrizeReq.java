package com.coin.req;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
public class ActivityPrizeReq extends CommonReq {

    // 所属活动id
    private Long activityId;

    // 活动类型, 1:邀请好友 2:签到 3:抽奖
    private Integer activityType;

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

    // 状态, -1:删除 1:正常
    private Integer status;

    // 创建人
    private String createdBy;

    // 修改人
    private String updatedBy;

    // 删除人
    private String deletedBy;
}
