package com.coin.mapper;

import com.coin.entity.MatchLeagues;
import com.coin.entity.MatchLeaguesExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MatchLeaguesMapper {
    long countByExample(MatchLeaguesExample example);

    int deleteByExample(MatchLeaguesExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MatchLeagues row);

    int insertSelective(MatchLeagues row);

    List<MatchLeagues> selectByExample(MatchLeaguesExample example);

    MatchLeagues selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") MatchLeagues row, @Param("example") MatchLeaguesExample example);

    int updateByExample(@Param("row") MatchLeagues row, @Param("example") MatchLeaguesExample example);

    int updateByPrimaryKeySelective(MatchLeagues row);

    int updateByPrimaryKey(MatchLeagues row);
}