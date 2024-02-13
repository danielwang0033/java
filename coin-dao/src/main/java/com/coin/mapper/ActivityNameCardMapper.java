package com.coin.mapper;

import com.coin.entity.ActivityNameCard;
import com.coin.entity.ActivityNameCardExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ActivityNameCardMapper {
    long countByExample(ActivityNameCardExample example);

    int deleteByExample(ActivityNameCardExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ActivityNameCard row);

    int insertSelective(ActivityNameCard row);

    List<ActivityNameCard> selectByExample(ActivityNameCardExample example);

    ActivityNameCard selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") ActivityNameCard row, @Param("example") ActivityNameCardExample example);

    int updateByExample(@Param("row") ActivityNameCard row, @Param("example") ActivityNameCardExample example);

    int updateByPrimaryKeySelective(ActivityNameCard row);

    int updateByPrimaryKey(ActivityNameCard row);
}