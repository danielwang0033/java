package com.coin.req;

import lombok.Data;

import java.io.Serializable;

/**
 * 公共请求对象
 */
@Data
public class CommonReq implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private Long loginUserId;
    private String optLoginName;
    private String loginUserEmail;
    private String optRocketUserId;
    private String optRocketUserLoginToken;
    private Integer page;
    private Integer pagesize;
    /**
     * token参数名
     */
    private String authorization;
    /**
     * 请求IP
     */
    private String reqIp;
    /**
     * 1-论坛后台 2-论坛前台
     */
    private Integer sourceSysType;
}
