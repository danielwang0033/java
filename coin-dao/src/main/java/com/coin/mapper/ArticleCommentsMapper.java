package com.coin.mapper;

import com.coin.entity.ArticleComments;
import com.coin.entity.ArticleCommentsExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArticleCommentsMapper {
    long countByExample(ArticleCommentsExample example);

    int deleteByExample(ArticleCommentsExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ArticleComments row);

    int insertSelective(ArticleComments row);

    List<ArticleComments> selectByExample(ArticleCommentsExample example);

    ArticleComments selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") ArticleComments row, @Param("example") ArticleCommentsExample example);

    int updateByExample(@Param("row") ArticleComments row, @Param("example") ArticleCommentsExample example);

    int updateByPrimaryKeySelective(ArticleComments row);

    int updateByPrimaryKey(ArticleComments row);
}