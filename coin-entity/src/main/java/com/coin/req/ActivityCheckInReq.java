package com.coin.req;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ActivityCheckInReq extends CommonReq {

    // 所属活动id
    private Long activityId;

    // 签到用户id
    private Long userId;

    // 创建人
    private String createdBy;
}
