package com.coin.req;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ReportNameTagsReq extends CommonReq {

    private String name;
}
