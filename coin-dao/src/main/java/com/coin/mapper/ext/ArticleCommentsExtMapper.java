package com.coin.mapper.ext;

import com.coin.resp.article.ArticleReplyCountVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArticleCommentsExtMapper {

    List<ArticleReplyCountVo> countByArticleIdList(@Param("articleIdList") List<Long> articleIdList);
}
