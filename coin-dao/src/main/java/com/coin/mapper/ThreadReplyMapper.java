package com.coin.mapper;

import com.coin.entity.ThreadReply;
import com.coin.entity.ThreadReplyExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ThreadReplyMapper {
    long countByExample(ThreadReplyExample example);

    int deleteByExample(ThreadReplyExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ThreadReply row);

    int insertSelective(ThreadReply row);

    List<ThreadReply> selectByExample(ThreadReplyExample example);

    ThreadReply selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") ThreadReply row, @Param("example") ThreadReplyExample example);

    int updateByExample(@Param("row") ThreadReply row, @Param("example") ThreadReplyExample example);

    int updateByPrimaryKeySelective(ThreadReply row);

    int updateByPrimaryKey(ThreadReply row);
}