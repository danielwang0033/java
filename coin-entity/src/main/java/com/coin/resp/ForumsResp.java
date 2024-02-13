package com.coin.resp;

import lombok.Data;

import java.util.Date;

@Data
public class ForumsResp {
    // 版块ID
    private Long id;

    // 版块名称
    private String name;

    // 板块别名
    private String alias;

    // 板块状态1开0关
    private Integer status;

    // 标签颜色
    private String labelColor;

    // 主题数
    private Integer subjectCount;
    private Date createdAt;
    private Date updatedAt;
}
