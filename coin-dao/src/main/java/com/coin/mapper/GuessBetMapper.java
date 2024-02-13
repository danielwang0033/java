package com.coin.mapper;

import com.coin.entity.GuessBet;
import com.coin.entity.GuessBetExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GuessBetMapper {
    long countByExample(GuessBetExample example);

    int deleteByExample(GuessBetExample example);

    int deleteByPrimaryKey(Long id);

    int insert(GuessBet row);

    int insertSelective(GuessBet row);

    List<GuessBet> selectByExample(GuessBetExample example);

    GuessBet selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") GuessBet row, @Param("example") GuessBetExample example);

    int updateByExample(@Param("row") GuessBet row, @Param("example") GuessBetExample example);

    int updateByPrimaryKeySelective(GuessBet row);

    int updateByPrimaryKey(GuessBet row);
}