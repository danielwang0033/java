package com.coin.req;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class EmailsReq extends CommonReq {

    private String traceId;
    private String code;
    private String email;
    private String html;
    private String password;
    private String passwordConfirm;
}
