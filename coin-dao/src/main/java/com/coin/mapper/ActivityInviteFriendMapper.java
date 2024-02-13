package com.coin.mapper;

import com.coin.entity.ActivityInviteFriend;
import com.coin.entity.ActivityInviteFriendExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ActivityInviteFriendMapper {
    long countByExample(ActivityInviteFriendExample example);

    int deleteByExample(ActivityInviteFriendExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ActivityInviteFriend row);

    int insertSelective(ActivityInviteFriend row);

    List<ActivityInviteFriend> selectByExample(ActivityInviteFriendExample example);

    ActivityInviteFriend selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") ActivityInviteFriend row, @Param("example") ActivityInviteFriendExample example);

    int updateByExample(@Param("row") ActivityInviteFriend row, @Param("example") ActivityInviteFriendExample example);

    int updateByPrimaryKeySelective(ActivityInviteFriend row);

    int updateByPrimaryKey(ActivityInviteFriend row);
}