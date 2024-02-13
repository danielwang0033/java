package com.coin.mapper;

import com.coin.entity.ThreadTagLog;
import com.coin.entity.ThreadTagLogExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ThreadTagLogMapper {
    long countByExample(ThreadTagLogExample example);

    int deleteByExample(ThreadTagLogExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ThreadTagLog row);

    int insertSelective(ThreadTagLog row);

    List<ThreadTagLog> selectByExample(ThreadTagLogExample example);

    ThreadTagLog selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") ThreadTagLog row, @Param("example") ThreadTagLogExample example);

    int updateByExample(@Param("row") ThreadTagLog row, @Param("example") ThreadTagLogExample example);

    int updateByPrimaryKeySelective(ThreadTagLog row);

    int updateByPrimaryKey(ThreadTagLog row);
}