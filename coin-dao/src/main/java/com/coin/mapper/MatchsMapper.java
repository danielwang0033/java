package com.coin.mapper;

import com.coin.entity.Matchs;
import com.coin.entity.MatchsExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MatchsMapper {
    long countByExample(MatchsExample example);

    int deleteByExample(MatchsExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Matchs row);

    int insertSelective(Matchs row);

    List<Matchs> selectByExample(MatchsExample example);

    Matchs selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") Matchs row, @Param("example") MatchsExample example);

    int updateByExample(@Param("row") Matchs row, @Param("example") MatchsExample example);

    int updateByPrimaryKeySelective(Matchs row);

    int updateByPrimaryKey(Matchs row);
}