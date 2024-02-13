package com.coin.req;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
public class TransactionsReq extends CommonReq {

    private String payableType;

    private Long payableId;

    private Long walletId;

    private String type;

    private BigDecimal amount;

    private Integer confirmed;

    private String meta;

    private String uuid;

    private Long userId;
}
