package com.coin.mapper.ext;

import org.apache.ibatis.annotations.Param;

public interface NotificationsExtMapper {

    int readAll(@Param("notifiableId") Long notifiableId);

    Long unreadNotifyCount(@Param("userid") Long userid);
}
