package com.coin.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class GuessItem implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long guessId;
    private String itemName;
    private Integer itemStatus;
    private BigDecimal itemOdds;
    private Integer betUserCount;
    private Integer settleStatus;
    private Integer settlePattern;
    private Integer settleCount;
    private BigDecimal settleBetAmount;
    private BigDecimal settleAmount;
    private BigDecimal settleProfit;
    private Date settleTime;
    private String settleNote;
    private Date createdAt;
    private Date updatedAt;

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                " [" +
                "Hash = " + hashCode() +
                ", id=" + id +
                ", guessId=" + guessId +
                ", itemName=" + itemName +
                ", itemStatus=" + itemStatus +
                ", itemOdds=" + itemOdds +
                ", betUserCount=" + betUserCount +
                ", settleStatus=" + settleStatus +
                ", settlePattern=" + settlePattern +
                ", settleCount=" + settleCount +
                ", settleBetAmount=" + settleBetAmount +
                ", settleAmount=" + settleAmount +
                ", settleProfit=" + settleProfit +
                ", settleTime=" + settleTime +
                ", settleNote=" + settleNote +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", serialVersionUID=" + serialVersionUID +
                "]";
    }
}