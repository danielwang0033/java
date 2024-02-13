package com.coin.mapper;

import com.coin.entity.MatchScores;
import com.coin.entity.MatchScoresExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MatchScoresMapper {
    long countByExample(MatchScoresExample example);

    int deleteByExample(MatchScoresExample example);

    int insert(MatchScores row);

    int insertSelective(MatchScores row);

    List<MatchScores> selectByExample(MatchScoresExample example);

    int updateByExampleSelective(@Param("row") MatchScores row, @Param("example") MatchScoresExample example);

    int updateByExample(@Param("row") MatchScores row, @Param("example") MatchScoresExample example);
}