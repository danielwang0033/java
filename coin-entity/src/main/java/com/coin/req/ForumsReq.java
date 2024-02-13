package com.coin.req;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ForumsReq extends CommonReq {

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

    private String categoryAlias;
}
