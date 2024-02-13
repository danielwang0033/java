package com.coin.resp.guess;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 竞猜用户
 */
@Data
public class MyBetListVo {

    private Long id;

    private Long userId;

    // 竞猜标题
    private String guessTitle;

    // 投注用户名
    private String userName;

    // 投注项名称
    private String guessItemName;

    // 下注金额
    private BigDecimal betAmount;

    // 结算状态, 0:待结算 1:结算中 2:结算完成 3:结算异常
    private Integer settleStatus;

    // 结算模式, 0:未设置 1:全赢 2:赢半 3:输半 4:全输 5:原路返还
    private Integer settlePattern;

    // 创建时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date createdAt;

    // 下注赔率,与投注项赔率一致
    private BigDecimal betOdds;

    // 结算金额, 根据结算模式计算
    private BigDecimal settleAmount;

    // 中奖金额
    private BigDecimal awardAmount;
}
