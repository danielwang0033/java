package com.coin.resp.thread;

import com.coin.resp.user.UserRelationLevelVo;
import lombok.Data;

@Data
public class ThreadUserVo {

    private Long id;

    private String name;

    private String avatar;

    private Integer exp;

    private UserRelationLevelVo level;

    private Integer bobi;
}
