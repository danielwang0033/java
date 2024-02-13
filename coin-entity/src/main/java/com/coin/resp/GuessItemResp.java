package com.coin.resp;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class GuessItemResp {
    // ID
    private Long id;

    // 竞猜id
    private Long guessId;

    // 投注项名称
    private String itemName;

    // 投注项状态, 0:已关闭 1:正常
    private Integer itemStatus;

    // 赔率
    private BigDecimal itemOdds;

    // 投注人数
    private Integer betUserCount;

    // 结算状态, 0:待结算 1:结算中 2:结算完成 3:结算异常
    private Integer settleStatus;

    // 结算模式, 0:未设置 1:全赢 2:赢半 3:输半 4:全输 5:原路返还
    private Integer settlePattern;

    // 结算笔数
    private Integer settleCount;

    // 结算投注总额
    private BigDecimal settleBetAmount;

    // 结算总额
    private BigDecimal settleAmount;

    // 结算利润,结算投注总额-结算总额,可以为负数
    private BigDecimal settleProfit;

    // 结算时间
    private Date settleTime;

    // 结算备注
    private String settleNote;

    // 创建时间
    private Date createdAt;

    // 修改时间
    private Date updatedAt;
}
