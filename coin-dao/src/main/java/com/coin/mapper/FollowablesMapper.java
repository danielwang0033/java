package com.coin.mapper;

import com.coin.entity.Followables;
import com.coin.entity.FollowablesExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FollowablesMapper {
    long countByExample(FollowablesExample example);

    int deleteByExample(FollowablesExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Followables row);

    int insertSelective(Followables row);

    List<Followables> selectByExample(FollowablesExample example);

    Followables selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") Followables row, @Param("example") FollowablesExample example);

    int updateByExample(@Param("row") Followables row, @Param("example") FollowablesExample example);

    int updateByPrimaryKeySelective(Followables row);

    int updateByPrimaryKey(Followables row);
}