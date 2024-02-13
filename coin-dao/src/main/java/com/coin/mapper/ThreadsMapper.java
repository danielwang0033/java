package com.coin.mapper;

import com.coin.entity.Threads;
import com.coin.entity.ThreadsExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ThreadsMapper {
    long countByExample(ThreadsExample example);

    int deleteByExample(ThreadsExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Threads row);

    int insertSelective(Threads row);

    List<Threads> selectByExample(ThreadsExample example);

    Threads selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") Threads row, @Param("example") ThreadsExample example);

    int updateByExample(@Param("row") Threads row, @Param("example") ThreadsExample example);

    int updateByPrimaryKeySelective(Threads row);

    int updateByPrimaryKey(Threads row);
}