package com.coin.mapper;

import com.coin.entity.ThreadTopics;
import com.coin.entity.ThreadTopicsExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ThreadTopicsMapper {
    long countByExample(ThreadTopicsExample example);

    int deleteByExample(ThreadTopicsExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ThreadTopics row);

    int insertSelective(ThreadTopics row);

    List<ThreadTopics> selectByExample(ThreadTopicsExample example);

    ThreadTopics selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") ThreadTopics row, @Param("example") ThreadTopicsExample example);

    int updateByExample(@Param("row") ThreadTopics row, @Param("example") ThreadTopicsExample example);

    int updateByPrimaryKeySelective(ThreadTopics row);

    int updateByPrimaryKey(ThreadTopics row);
}