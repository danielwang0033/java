package com.coin.req;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
public class GuessBetReq extends CommonReq {

    // 投注用户id
    private Long userId;

    // 投注用户名
    private String userName;

    // 竞猜id
    private Long guessId;

    // 投注项id
    private Long guessItemId;

    // 投注项名称
    private String guessItemName;

    // 下注金额
    private BigDecimal betAmount;

    // 下注赔率,与投注项赔率一致
    private BigDecimal betOdds;

    // 结算模式, 0:未设置 1:全赢 2:赢半 3:输半 4:全输 5:原路返还
    private Integer settlePattern;

    // 结算状态, 0:待结算 1:结算中 2:结算完成 3:结算异常
    private Integer settleStatus;

    // 中奖金额
    private BigDecimal awardAmount;

    private Integer type;

    private Integer adminFlag;
}
