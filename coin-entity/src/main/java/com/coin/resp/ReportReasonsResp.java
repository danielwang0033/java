package com.coin.resp;

import lombok.Data;

import java.util.Date;

@Data
public class ReportReasonsResp {
    // 主键
    private Long id;

    // 原因
    private String reason;

    // 颜色
    private String color;
    private Date createdAt;
    private Date updatedAt;
}
