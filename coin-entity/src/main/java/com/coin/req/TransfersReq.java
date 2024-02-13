package com.coin.req;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
public class TransfersReq extends CommonReq {

    private String fromType;

    private Long fromId;

    private String toType;

    private Long toId;

    private String status;

    private String statusLast;

    private Long depositId;

    private Long withdrawId;

    private BigDecimal discount;

    private BigDecimal fee;

    private String uuid;
}
