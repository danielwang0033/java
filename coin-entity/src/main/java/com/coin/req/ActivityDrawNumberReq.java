package com.coin.req;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ActivityDrawNumberReq extends CommonReq {

    // 所属活动id
    private Long activityId;

    // 变更类型,1:管理员加次数 2:抽奖使用 3:活动赠送
    private Integer changeType;

    // 用户id
    private Long userId;

    // 变更前数量
    private Integer beforeChange;

    // 变更后数量
    private Integer afterChange;

    // 变更数量,正数:加次数,负数:使用次数
    private Integer changeQuantity;

    // 备注
    private String remark;

    // 创建人
    private String createdBy;
}
