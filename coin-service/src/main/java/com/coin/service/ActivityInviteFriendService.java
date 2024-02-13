package com.coin.service;

import com.coin.entity.Activity;
import com.coin.entity.Users;

import java.util.List;

public interface ActivityInviteFriendService {

    void saveInvitedLog(Users users, Activity activity, Users invitedByUser);

    List<Long> checkByInviteCode(List<Long> hasInviteCodeList);
}
