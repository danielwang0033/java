package com.coin.req;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ReportReasonsReq extends CommonReq {

    // 原因
    private String reason;

    // 颜色
    private String color;
}
