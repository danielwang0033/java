package com.coin.resp.user;

import lombok.Data;

@Data
public class UserRelationLevelVo {

    private Integer level;
    private String name;
    private String badge;
    private Integer needExp;
    private Integer nextLevel;
    private Integer nextLevelNeedExp;
    private String nextLevelName;
    private String nextLevelBadge;
}
