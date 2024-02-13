package com.coin.req;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class TDictI18nReq extends CommonReq {

    // 翻译目标语言, vi: 越南语
    private String language;

    // 原文
    private String zhCode;

    // 译文
    private String translation;

    // 状态, 0:失效 1:有效
    private Integer status;
}
