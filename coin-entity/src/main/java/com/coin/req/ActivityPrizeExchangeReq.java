package com.coin.req;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ActivityPrizeExchangeReq extends CommonReq {

    // 所属活动id
    private Long activityId;

    // 活动类型, 1:邀请好友 2:签到 3:抽奖
    private Integer activityType;

    // 中奖用户id
    private Long userId;

    // 奖品类型, 1:博币 2:经验 3:抽奖次数 4:改名卡 5:实物 6:未中奖
    private Integer prizeType;

    // 奖品名称
    private String prizeName;

    // 奖品数量
    private Integer prizeQuantity;

    // 奖品图片
    private String prizeImage;

    // 状态, -1:未中奖 1:等待兑换 2:已兑换
    private Integer status;

    // 备注
    private String remark;

    // 创建人
    private String createdBy;

    // 修改人
    private String updatedBy;
}
