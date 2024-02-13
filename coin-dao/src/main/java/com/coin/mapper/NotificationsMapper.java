package com.coin.mapper;

import com.coin.entity.Notifications;
import com.coin.entity.NotificationsExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NotificationsMapper {
    long countByExample(NotificationsExample example);

    int deleteByExample(NotificationsExample example);

    int deleteByPrimaryKey(String id);

    int insert(Notifications row);

    int insertSelective(Notifications row);

    List<Notifications> selectByExample(NotificationsExample example);

    Notifications selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("row") Notifications row, @Param("example") NotificationsExample example);

    int updateByExample(@Param("row") Notifications row, @Param("example") NotificationsExample example);

    int updateByPrimaryKeySelective(Notifications row);

    int updateByPrimaryKey(Notifications row);
}