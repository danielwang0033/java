package com.coin.cache;

import lombok.Data;

@Data
public class UserLevelCache {

    private Long id;

    private Integer level;

    private String name;

    private String badge;

    private Integer needExp;
}