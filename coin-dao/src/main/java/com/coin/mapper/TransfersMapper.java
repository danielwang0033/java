package com.coin.mapper;

import com.coin.entity.Transfers;
import com.coin.entity.TransfersExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TransfersMapper {
    long countByExample(TransfersExample example);

    int deleteByExample(TransfersExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Transfers row);

    int insertSelective(Transfers row);

    List<Transfers> selectByExample(TransfersExample example);

    Transfers selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") Transfers row, @Param("example") TransfersExample example);

    int updateByExample(@Param("row") Transfers row, @Param("example") TransfersExample example);

    int updateByPrimaryKeySelective(Transfers row);

    int updateByPrimaryKey(Transfers row);
}