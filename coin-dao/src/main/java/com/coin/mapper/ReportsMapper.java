package com.coin.mapper;

import com.coin.entity.Reports;
import com.coin.entity.ReportsExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ReportsMapper {
    long countByExample(ReportsExample example);

    int deleteByExample(ReportsExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Reports row);

    int insertSelective(Reports row);

    List<Reports> selectByExample(ReportsExample example);

    Reports selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") Reports row, @Param("example") ReportsExample example);

    int updateByExample(@Param("row") Reports row, @Param("example") ReportsExample example);

    int updateByPrimaryKeySelective(Reports row);

    int updateByPrimaryKey(Reports row);
}