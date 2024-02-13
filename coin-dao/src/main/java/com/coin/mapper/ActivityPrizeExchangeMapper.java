package com.coin.mapper;

import com.coin.entity.ActivityPrizeExchange;
import com.coin.entity.ActivityPrizeExchangeExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ActivityPrizeExchangeMapper {
    long countByExample(ActivityPrizeExchangeExample example);

    int deleteByExample(ActivityPrizeExchangeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ActivityPrizeExchange row);

    int insertSelective(ActivityPrizeExchange row);

    List<ActivityPrizeExchange> selectByExample(ActivityPrizeExchangeExample example);

    ActivityPrizeExchange selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") ActivityPrizeExchange row, @Param("example") ActivityPrizeExchangeExample example);

    int updateByExample(@Param("row") ActivityPrizeExchange row, @Param("example") ActivityPrizeExchangeExample example);

    int updateByPrimaryKeySelective(ActivityPrizeExchange row);

    int updateByPrimaryKey(ActivityPrizeExchange row);
}