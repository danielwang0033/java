package com.coin.req.search;

import com.coin.req.CommonReq;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class SearchReq extends CommonReq {

    private String keyword;
}
