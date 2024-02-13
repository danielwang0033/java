package com.coin.req;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class JobsReq extends CommonReq {

    private String queue;

    private String payload;

    private Integer attempts;

    private Integer reservedAt;

    private Integer availableAt;

    private Integer createdAt;

}
