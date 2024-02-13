package com.coin.resp;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class TransactionsResp {

    private Long id;
    private String payableType;
    private Long payableId;
    private Long walletId;
    private String type;
    private BigDecimal amount;
    private Integer confirmed;
    private String meta;
    private String uuid;
    private Date createdAt;
    private Date updatedAt;
    private String info;
}
