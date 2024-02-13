package com.coin.mapper;

import com.coin.entity.ReportNameTags;
import com.coin.entity.ReportNameTagsExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ReportNameTagsMapper {
    long countByExample(ReportNameTagsExample example);

    int deleteByExample(ReportNameTagsExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ReportNameTags row);

    int insertSelective(ReportNameTags row);

    List<ReportNameTags> selectByExample(ReportNameTagsExample example);

    ReportNameTags selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") ReportNameTags row, @Param("example") ReportNameTagsExample example);

    int updateByExample(@Param("row") ReportNameTags row, @Param("example") ReportNameTagsExample example);

    int updateByPrimaryKeySelective(ReportNameTags row);

    int updateByPrimaryKey(ReportNameTags row);
}