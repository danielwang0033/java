package com.coin.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class GuessBet implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long userId;
    private String userName;
    private Long guessId;
    private Long guessItemId;
    private String guessItemName;
    private BigDecimal betAmount;
    private BigDecimal betOdds;
    private Integer settlePattern;
    private Integer settleStatus;
    private BigDecimal awardAmount;
    private BigDecimal settleAmount;
    private BigDecimal settleProfit;
    private Date createdAt;
    private Date updatedAt;
    private String settleNote;

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                " [" +
                "Hash = " + hashCode() +
                ", id=" + id +
                ", userId=" + userId +
                ", userName=" + userName +
                ", guessId=" + guessId +
                ", guessItemId=" + guessItemId +
                ", guessItemName=" + guessItemName +
                ", betAmount=" + betAmount +
                ", betOdds=" + betOdds +
                ", settlePattern=" + settlePattern +
                ", settleStatus=" + settleStatus +
                ", awardAmount=" + awardAmount +
                ", settleAmount=" + settleAmount +
                ", settleProfit=" + settleProfit +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", settleNote=" + settleNote +
                ", serialVersionUID=" + serialVersionUID +
                "]";
    }
}