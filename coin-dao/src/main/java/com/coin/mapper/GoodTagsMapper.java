package com.coin.mapper;

import com.coin.entity.GoodTags;
import com.coin.entity.GoodTagsExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodTagsMapper {
    long countByExample(GoodTagsExample example);

    int deleteByExample(GoodTagsExample example);

    int deleteByPrimaryKey(Long id);

    int insert(GoodTags row);

    int insertSelective(GoodTags row);

    List<GoodTags> selectByExample(GoodTagsExample example);

    GoodTags selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") GoodTags row, @Param("example") GoodTagsExample example);

    int updateByExample(@Param("row") GoodTags row, @Param("example") GoodTagsExample example);

    int updateByPrimaryKeySelective(GoodTags row);

    int updateByPrimaryKey(GoodTags row);
}