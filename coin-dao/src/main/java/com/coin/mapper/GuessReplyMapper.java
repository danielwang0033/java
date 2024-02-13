package com.coin.mapper;

import com.coin.entity.GuessReply;
import com.coin.entity.GuessReplyExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GuessReplyMapper {
    long countByExample(GuessReplyExample example);

    int deleteByExample(GuessReplyExample example);

    int deleteByPrimaryKey(Long id);

    int insert(GuessReply row);

    int insertSelective(GuessReply row);

    List<GuessReply> selectByExample(GuessReplyExample example);

    GuessReply selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") GuessReply row, @Param("example") GuessReplyExample example);

    int updateByExample(@Param("row") GuessReply row, @Param("example") GuessReplyExample example);

    int updateByPrimaryKeySelective(GuessReply row);

    int updateByPrimaryKey(GuessReply row);
}