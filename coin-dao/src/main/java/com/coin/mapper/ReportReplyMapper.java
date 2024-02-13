package com.coin.mapper;

import com.coin.entity.ReportReply;
import com.coin.entity.ReportReplyExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ReportReplyMapper {
    long countByExample(ReportReplyExample example);

    int deleteByExample(ReportReplyExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ReportReply row);

    int insertSelective(ReportReply row);

    List<ReportReply> selectByExample(ReportReplyExample example);

    ReportReply selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") ReportReply row, @Param("example") ReportReplyExample example);

    int updateByExample(@Param("row") ReportReply row, @Param("example") ReportReplyExample example);

    int updateByPrimaryKeySelective(ReportReply row);

    int updateByPrimaryKey(ReportReply row);
}