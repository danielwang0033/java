package com.coin.mapper;

import com.coin.entity.ArticleGroups;
import com.coin.entity.ArticleGroupsExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArticleGroupsMapper {
    long countByExample(ArticleGroupsExample example);

    int deleteByExample(ArticleGroupsExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ArticleGroups row);

    int insertSelective(ArticleGroups row);

    List<ArticleGroups> selectByExample(ArticleGroupsExample example);

    ArticleGroups selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") ArticleGroups row, @Param("example") ArticleGroupsExample example);

    int updateByExample(@Param("row") ArticleGroups row, @Param("example") ArticleGroupsExample example);

    int updateByPrimaryKeySelective(ArticleGroups row);

    int updateByPrimaryKey(ArticleGroups row);
}