package com.coin.mapper;

import com.coin.entity.UserExpLogs;
import com.coin.entity.UserExpLogsExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserExpLogsMapper {
    long countByExample(UserExpLogsExample example);

    int deleteByExample(UserExpLogsExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserExpLogs row);

    int insertSelective(UserExpLogs row);

    List<UserExpLogs> selectByExample(UserExpLogsExample example);

    UserExpLogs selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") UserExpLogs row, @Param("example") UserExpLogsExample example);

    int updateByExample(@Param("row") UserExpLogs row, @Param("example") UserExpLogsExample example);

    int updateByPrimaryKeySelective(UserExpLogs row);

    int updateByPrimaryKey(UserExpLogs row);
}