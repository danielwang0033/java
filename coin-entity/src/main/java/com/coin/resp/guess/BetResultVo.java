package com.coin.resp.guess;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 竞猜结果
 */
@Data
public class BetResultVo {

    private Long id;

    private Long userId;

    // 投注用户名
    private String userName;

    // 投注项名称
    private String guessItemName;

    // 下注金额
    private BigDecimal betAmount;

    // 下注赔率,与投注项赔率一致
    private BigDecimal betOdds;

    // 中奖金额
    private BigDecimal awardAmount;

    // 结算状态, 0:待结算 1:结算中 2:结算完成 3:结算异常
    private Integer settleStatus;

    // 结算模式, 0:未设置 1:全赢 2:赢半 3:输半 4:全输 5:原路返还
    private Integer settlePattern;

    // 创建时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date createdAt;
}
