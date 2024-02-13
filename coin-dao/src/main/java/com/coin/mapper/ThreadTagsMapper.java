package com.coin.mapper;

import com.coin.entity.ThreadTags;
import com.coin.entity.ThreadTagsExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ThreadTagsMapper {
    long countByExample(ThreadTagsExample example);

    int deleteByExample(ThreadTagsExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ThreadTags row);

    int insertSelective(ThreadTags row);

    List<ThreadTags> selectByExample(ThreadTagsExample example);

    ThreadTags selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") ThreadTags row, @Param("example") ThreadTagsExample example);

    int updateByExample(@Param("row") ThreadTags row, @Param("example") ThreadTagsExample example);

    int updateByPrimaryKeySelective(ThreadTags row);

    int updateByPrimaryKey(ThreadTags row);
}