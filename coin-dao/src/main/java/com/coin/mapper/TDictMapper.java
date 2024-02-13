package com.coin.mapper;

import com.coin.entity.TDict;
import com.coin.entity.TDictExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TDictMapper {
    long countByExample(TDictExample example);

    int deleteByExample(TDictExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TDict row);

    int insertSelective(TDict row);

    List<TDict> selectByExample(TDictExample example);

    TDict selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") TDict row, @Param("example") TDictExample example);

    int updateByExample(@Param("row") TDict row, @Param("example") TDictExample example);

    int updateByPrimaryKeySelective(TDict row);

    int updateByPrimaryKey(TDict row);
}