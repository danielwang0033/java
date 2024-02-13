package com.coin.mapper;

import com.coin.entity.Goods;
import com.coin.entity.GoodsExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodsMapper {
    long countByExample(GoodsExample example);

    int deleteByExample(GoodsExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Goods row);

    int insertSelective(Goods row);

    List<Goods> selectByExample(GoodsExample example);

    Goods selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") Goods row, @Param("example") GoodsExample example);

    int updateByExample(@Param("row") Goods row, @Param("example") GoodsExample example);

    int updateByPrimaryKeySelective(Goods row);

    int updateByPrimaryKey(Goods row);
}