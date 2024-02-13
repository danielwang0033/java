package com.coin.mapper;

import com.coin.entity.MatchFbtechs;
import com.coin.entity.MatchFbtechsExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MatchFbtechsMapper {
    long countByExample(MatchFbtechsExample example);

    int deleteByExample(MatchFbtechsExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MatchFbtechs row);

    int insertSelective(MatchFbtechs row);

    List<MatchFbtechs> selectByExample(MatchFbtechsExample example);

    MatchFbtechs selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") MatchFbtechs row, @Param("example") MatchFbtechsExample example);

    int updateByExample(@Param("row") MatchFbtechs row, @Param("example") MatchFbtechsExample example);

    int updateByPrimaryKeySelective(MatchFbtechs row);

    int updateByPrimaryKey(MatchFbtechs row);
}