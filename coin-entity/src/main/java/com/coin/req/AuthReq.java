package com.coin.req;

import com.coin.entity.Activity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class AuthReq extends CommonReq {

    private String email;

    private String password;

    private String username;

    private String passwordConfirm;

    private String inviteCode;

    private Long activityId;

    private Activity activity;
}
