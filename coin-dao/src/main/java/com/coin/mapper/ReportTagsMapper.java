package com.coin.mapper;

import com.coin.entity.ReportTags;
import com.coin.entity.ReportTagsExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ReportTagsMapper {
    long countByExample(ReportTagsExample example);

    int deleteByExample(ReportTagsExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ReportTags row);

    int insertSelective(ReportTags row);

    List<ReportTags> selectByExample(ReportTagsExample example);

    ReportTags selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") ReportTags row, @Param("example") ReportTagsExample example);

    int updateByExample(@Param("row") ReportTags row, @Param("example") ReportTagsExample example);

    int updateByPrimaryKeySelective(ReportTags row);

    int updateByPrimaryKey(ReportTags row);
}