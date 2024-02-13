package com.coin.mapper.ext;

import com.coin.resp.search.SearchArticleVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArticlesExtMapper {

    List<SearchArticleVo> searchArticle(@Param("keyword") String keyword);

    int addVisitsAmount(@Param("articleId") Long articleId, @Param("addNum") int addNum);
}
