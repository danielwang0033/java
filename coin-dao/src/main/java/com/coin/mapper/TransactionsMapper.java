package com.coin.mapper;

import com.coin.entity.Transactions;
import com.coin.entity.TransactionsExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TransactionsMapper {
    long countByExample(TransactionsExample example);

    int deleteByExample(TransactionsExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Transactions row);

    int insertSelective(Transactions row);

    List<Transactions> selectByExample(TransactionsExample example);

    Transactions selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") Transactions row, @Param("example") TransactionsExample example);

    int updateByExample(@Param("row") Transactions row, @Param("example") TransactionsExample example);

    int updateByPrimaryKeySelective(Transactions row);

    int updateByPrimaryKey(Transactions row);
}