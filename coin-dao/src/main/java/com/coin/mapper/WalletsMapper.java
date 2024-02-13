package com.coin.mapper;

import com.coin.entity.Wallets;
import com.coin.entity.WalletsExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WalletsMapper {
    long countByExample(WalletsExample example);

    int deleteByExample(WalletsExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Wallets row);

    int insertSelective(Wallets row);

    List<Wallets> selectByExample(WalletsExample example);

    Wallets selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") Wallets row, @Param("example") WalletsExample example);

    int updateByExample(@Param("row") Wallets row, @Param("example") WalletsExample example);

    int updateByPrimaryKeySelective(Wallets row);

    int updateByPrimaryKey(Wallets row);
}