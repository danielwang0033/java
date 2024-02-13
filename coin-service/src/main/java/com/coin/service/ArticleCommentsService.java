package com.coin.service;

import com.coin.entity.ArticleComments;
import com.coin.req.ArticleCommentsReq;
import com.coin.resp.article.ArticleReplyCountVo;
import com.coin.resp.dict.ExtraMsgVo;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ArticleCommentsService {

    void delete(ArticleCommentsReq req);

    ArticleComments getById(Long id);

    PageInfo<ArticleComments> pageList(ArticleCommentsReq req);

    List<ArticleReplyCountVo> countByArticleIdList(List<Long> articleIdList);

    ExtraMsgVo newComment(ArticleCommentsReq req);
}
