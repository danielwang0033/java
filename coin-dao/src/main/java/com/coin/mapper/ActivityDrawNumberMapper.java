package com.coin.mapper;

import com.coin.entity.ActivityDrawNumber;
import com.coin.entity.ActivityDrawNumberExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ActivityDrawNumberMapper {
    long countByExample(ActivityDrawNumberExample example);

    int deleteByExample(ActivityDrawNumberExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ActivityDrawNumber row);

    int insertSelective(ActivityDrawNumber row);

    List<ActivityDrawNumber> selectByExample(ActivityDrawNumberExample example);

    ActivityDrawNumber selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") ActivityDrawNumber row, @Param("example") ActivityDrawNumberExample example);

    int updateByExample(@Param("row") ActivityDrawNumber row, @Param("example") ActivityDrawNumberExample example);

    int updateByPrimaryKeySelective(ActivityDrawNumber row);

    int updateByPrimaryKey(ActivityDrawNumber row);
}