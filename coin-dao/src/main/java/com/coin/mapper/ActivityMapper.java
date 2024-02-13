package com.coin.mapper;

import com.coin.entity.Activity;
import com.coin.entity.ActivityExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ActivityMapper {
    long countByExample(ActivityExample example);

    int deleteByExample(ActivityExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Activity row);

    int insertSelective(Activity row);

    List<Activity> selectByExample(ActivityExample example);

    Activity selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") Activity row, @Param("example") ActivityExample example);

    int updateByExample(@Param("row") Activity row, @Param("example") ActivityExample example);

    int updateByPrimaryKeySelective(Activity row);

    int updateByPrimaryKey(Activity row);
}