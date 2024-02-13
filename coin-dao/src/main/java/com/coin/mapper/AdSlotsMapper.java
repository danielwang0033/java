package com.coin.mapper;

import com.coin.entity.AdSlots;
import com.coin.entity.AdSlotsExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdSlotsMapper {
    long countByExample(AdSlotsExample example);

    int deleteByExample(AdSlotsExample example);

    int deleteByPrimaryKey(Long id);

    int insert(AdSlots row);

    int insertSelective(AdSlots row);

    List<AdSlots> selectByExample(AdSlotsExample example);

    AdSlots selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") AdSlots row, @Param("example") AdSlotsExample example);

    int updateByExample(@Param("row") AdSlots row, @Param("example") AdSlotsExample example);

    int updateByPrimaryKeySelective(AdSlots row);

    int updateByPrimaryKey(AdSlots row);
}