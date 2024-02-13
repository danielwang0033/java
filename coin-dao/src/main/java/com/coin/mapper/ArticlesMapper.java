package com.coin.mapper;

import com.coin.entity.Articles;
import com.coin.entity.ArticlesExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArticlesMapper {
    long countByExample(ArticlesExample example);

    int deleteByExample(ArticlesExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Articles row);

    int insertSelective(Articles row);

    List<Articles> selectByExample(ArticlesExample example);

    Articles selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") Articles row, @Param("example") ArticlesExample example);

    int updateByExample(@Param("row") Articles row, @Param("example") ArticlesExample example);

    int updateByPrimaryKeySelective(Articles row);

    int updateByPrimaryKey(Articles row);
}