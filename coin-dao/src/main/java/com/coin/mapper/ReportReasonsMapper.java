package com.coin.mapper;

import com.coin.entity.ReportReasons;
import com.coin.entity.ReportReasonsExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ReportReasonsMapper {
    long countByExample(ReportReasonsExample example);

    int deleteByExample(ReportReasonsExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ReportReasons row);

    int insertSelective(ReportReasons row);

    List<ReportReasons> selectByExample(ReportReasonsExample example);

    ReportReasons selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") ReportReasons row, @Param("example") ReportReasonsExample example);

    int updateByExample(@Param("row") ReportReasons row, @Param("example") ReportReasonsExample example);

    int updateByPrimaryKeySelective(ReportReasons row);

    int updateByPrimaryKey(ReportReasons row);
}