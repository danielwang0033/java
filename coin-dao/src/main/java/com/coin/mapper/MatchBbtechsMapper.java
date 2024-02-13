package com.coin.mapper;

import com.coin.entity.MatchBbtechs;
import com.coin.entity.MatchBbtechsExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MatchBbtechsMapper {
    long countByExample(MatchBbtechsExample example);

    int deleteByExample(MatchBbtechsExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MatchBbtechs row);

    int insertSelective(MatchBbtechs row);

    List<MatchBbtechs> selectByExample(MatchBbtechsExample example);

    MatchBbtechs selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") MatchBbtechs row, @Param("example") MatchBbtechsExample example);

    int updateByExample(@Param("row") MatchBbtechs row, @Param("example") MatchBbtechsExample example);

    int updateByPrimaryKeySelective(MatchBbtechs row);

    int updateByPrimaryKey(MatchBbtechs row);
}