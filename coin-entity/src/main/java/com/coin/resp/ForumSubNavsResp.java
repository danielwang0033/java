package com.coin.resp;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ForumSubNavsResp {

    private Long id;

    // 所属板块
    private Long forumId;

    // 所属板块名称
    private String forumName;

    // 导航名称
    private String name;

    // 包含话题
    private String topics;
    // 包含话题名称
    private List<String> topicNames;

    // 状态
    private Integer status;

    // 排序
    private Integer sort;
    private Date createdAt;
    private Date updatedAt;
}
