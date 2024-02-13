package com.coin.resp.guess;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 投注项
 */
@Data
public class GuessItemVo {

    private Long id;

    // 竞猜id
    private Long guessId;

    // 投注项名称
    private String itemName;

    // 赔率
    private BigDecimal itemOdds;

    // 投注人数
    private Integer betUserCount;

    // 结算模式
    private Integer settlePattern;

    // 结算状态
    private Integer settleStatus;
}
