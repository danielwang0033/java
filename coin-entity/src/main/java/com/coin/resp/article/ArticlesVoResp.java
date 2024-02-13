package com.coin.resp.article;

import com.coin.entity.ArticleGroups;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ArticlesVoResp {

    private Long id;

    // 文章标题
    private String title;

    // 缩略图
    private List<String> thumbs;

    // 文章描述
    private String description;

    // 文章内容
    private String content;

    // 评论数
    private Long comments;

    // 作者id
    private Long userId;

    // 作者
    private String userName;

    private String avatar;

    // 访问量
    private Integer visits;

    // 分类
    private Long artGroupId;

    private Date createdAt;

    private Integer currentUserLiked;
    private Integer currentUserFavorited;

    private ArticleGroups group;

    private Long likeCount;

    private ArticleUserVo user;

    // 点赞状态
    private boolean likeStatus;

    // 收藏状态
    private boolean favStatus;
}
