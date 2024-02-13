package com.coin.req;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
public class WalletsReq extends CommonReq {

    private String holderType;

    private Long holderId;

    private String name;

    private String slug;

    private String uuid;

    private String description;

    private String meta;

    private BigDecimal balance;

    private Integer decimalPlaces;
}
