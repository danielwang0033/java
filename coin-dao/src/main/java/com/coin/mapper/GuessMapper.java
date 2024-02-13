package com.coin.mapper;

import com.coin.entity.Guess;
import com.coin.entity.GuessExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GuessMapper {
    long countByExample(GuessExample example);

    int deleteByExample(GuessExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Guess row);

    int insertSelective(Guess row);

    List<Guess> selectByExample(GuessExample example);

    Guess selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") Guess row, @Param("example") GuessExample example);

    int updateByExample(@Param("row") Guess row, @Param("example") GuessExample example);

    int updateByPrimaryKeySelective(Guess row);

    int updateByPrimaryKey(Guess row);
}