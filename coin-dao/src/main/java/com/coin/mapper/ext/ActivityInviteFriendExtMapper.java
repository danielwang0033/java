package com.coin.mapper.ext;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ActivityInviteFriendExtMapper {

    List<Long> checkByInviteCode(@Param("codeList") List<Long> hasInviteCodeList);
}
