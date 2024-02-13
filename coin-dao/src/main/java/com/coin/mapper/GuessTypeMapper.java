package com.coin.mapper;

import com.coin.entity.GuessType;
import com.coin.entity.GuessTypeExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GuessTypeMapper {
    long countByExample(GuessTypeExample example);

    int deleteByExample(GuessTypeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(GuessType row);

    int insertSelective(GuessType row);

    List<GuessType> selectByExample(GuessTypeExample example);

    GuessType selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") GuessType row, @Param("example") GuessTypeExample example);

    int updateByExample(@Param("row") GuessType row, @Param("example") GuessTypeExample example);

    int updateByPrimaryKeySelective(GuessType row);

    int updateByPrimaryKey(GuessType row);
}