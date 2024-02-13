package com.coin.resp.article;

import com.coin.resp.user.UserRelationLevelVo;
import lombok.Data;

@Data
public class ArticleUserVo {

    private Long id;
    private String name;
    private String avatar;
    private String bio;
    private Integer exp;
    private Integer bobi;
    private boolean isFollowedByMe;
    private Long followeeCount;
    private Long followerCount;
    private Long threadCount;
    private UserRelationLevelVo level;
}
