package com.coin.mapper;

import com.coin.entity.UserLevel;
import com.coin.entity.UserLevelExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserLevelMapper {
    long countByExample(UserLevelExample example);

    int deleteByExample(UserLevelExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserLevel row);

    int insertSelective(UserLevel row);

    List<UserLevel> selectByExample(UserLevelExample example);

    UserLevel selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") UserLevel row, @Param("example") UserLevelExample example);

    int updateByExample(@Param("row") UserLevel row, @Param("example") UserLevelExample example);

    int updateByPrimaryKeySelective(UserLevel row);

    int updateByPrimaryKey(UserLevel row);
}