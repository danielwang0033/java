package com.coin.mapper;

import com.coin.entity.ReplyExtend;
import com.coin.entity.ReplyExtendExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ReplyExtendMapper {
    long countByExample(ReplyExtendExample example);

    int deleteByExample(ReplyExtendExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ReplyExtend row);

    int insertSelective(ReplyExtend row);

    List<ReplyExtend> selectByExample(ReplyExtendExample example);

    ReplyExtend selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") ReplyExtend row, @Param("example") ReplyExtendExample example);

    int updateByExample(@Param("row") ReplyExtend row, @Param("example") ReplyExtendExample example);

    int updateByPrimaryKeySelective(ReplyExtend row);

    int updateByPrimaryKey(ReplyExtend row);
}