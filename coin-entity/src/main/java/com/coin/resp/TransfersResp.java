package com.coin.resp;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class TransfersResp {

    private Long id;
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
    private Date createdAt;
    private Date updatedAt;
}
