package com.coin.mapper;

import com.coin.entity.ActivityCheckIn;
import com.coin.entity.ActivityCheckInExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ActivityCheckInMapper {
    long countByExample(ActivityCheckInExample example);

    int deleteByExample(ActivityCheckInExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ActivityCheckIn row);

    int insertSelective(ActivityCheckIn row);

    List<ActivityCheckIn> selectByExample(ActivityCheckInExample example);

    ActivityCheckIn selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") ActivityCheckIn row, @Param("example") ActivityCheckInExample example);

    int updateByExample(@Param("row") ActivityCheckIn row, @Param("example") ActivityCheckInExample example);

    int updateByPrimaryKeySelective(ActivityCheckIn row);

    int updateByPrimaryKey(ActivityCheckIn row);
}