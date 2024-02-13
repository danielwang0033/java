package com.coin.req;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ForumSubNavsReq extends CommonReq {

    // 所属板块
    private Long forumId;

    // 导航名称
    private String name;

    // 包含话题
    private String topics;

    // 状态
    private Integer status;

    // 排序
    private Integer sort;

    private String categoryAlias;
}
