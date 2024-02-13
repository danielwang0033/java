package com.coin.mapper;

import com.coin.entity.ActivityPrize;
import com.coin.entity.ActivityPrizeExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ActivityPrizeMapper {
    long countByExample(ActivityPrizeExample example);

    int deleteByExample(ActivityPrizeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ActivityPrize row);

    int insertSelective(ActivityPrize row);

    List<ActivityPrize> selectByExample(ActivityPrizeExample example);

    ActivityPrize selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") ActivityPrize row, @Param("example") ActivityPrizeExample example);

    int updateByExample(@Param("row") ActivityPrize row, @Param("example") ActivityPrizeExample example);

    int updateByPrimaryKeySelective(ActivityPrize row);

    int updateByPrimaryKey(ActivityPrize row);
}