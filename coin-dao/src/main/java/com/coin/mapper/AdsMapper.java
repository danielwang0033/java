package com.coin.mapper;

import com.coin.entity.Ads;
import com.coin.entity.AdsExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdsMapper {
    long countByExample(AdsExample example);

    int deleteByExample(AdsExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Ads row);

    int insertSelective(Ads row);

    List<Ads> selectByExample(AdsExample example);

    Ads selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") Ads row, @Param("example") AdsExample example);

    int updateByExample(@Param("row") Ads row, @Param("example") AdsExample example);

    int updateByPrimaryKeySelective(Ads row);

    int updateByPrimaryKey(Ads row);
}