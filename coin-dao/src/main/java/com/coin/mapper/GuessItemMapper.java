package com.coin.mapper;

import com.coin.entity.GuessItem;
import com.coin.entity.GuessItemExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GuessItemMapper {
    long countByExample(GuessItemExample example);

    int deleteByExample(GuessItemExample example);

    int deleteByPrimaryKey(Long id);

    int insert(GuessItem row);

    int insertSelective(GuessItem row);

    List<GuessItem> selectByExample(GuessItemExample example);

    GuessItem selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") GuessItem row, @Param("example") GuessItemExample example);

    int updateByExample(@Param("row") GuessItem row, @Param("example") GuessItemExample example);

    int updateByPrimaryKeySelective(GuessItem row);

    int updateByPrimaryKey(GuessItem row);
}