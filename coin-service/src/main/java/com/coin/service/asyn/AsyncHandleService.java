package com.coin.service.asyn;

public interface AsyncHandleService {

    /**
     * 增加竞猜评论数
     */
    void addGuessCommentAmount(Long guessId);

    /**
     * 减去竞猜评论数
     */
    void subtractGuessCommentAmount(Long guessId);

    /**
     * 增加竞猜访问数
     */
    void addGuessVisitsAmount(Long guessId);

    /**
     * 增加文章访问数
     */
    void addArticleVisitsAmount(Long articleId);

    /**
     * 增加帖子访问数
     */
    void addThreadVisitsAmount(Long threadId);

    /**
     * 增加老哥帮手访问数
     */
    void addReportVisitsAmount(Long reportId);
}
