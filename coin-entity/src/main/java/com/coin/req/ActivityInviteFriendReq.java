package com.coin.req;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ActivityInviteFriendReq extends CommonReq {

    // 所属活动id
    private Long activityId;

    // 用户id
    private Long userId;

    // 用户邀请码
    private String inviteCode;

    // 受邀用户id
    private Long inviteeUserId;

    // 创建人
    private String createdBy;
}
