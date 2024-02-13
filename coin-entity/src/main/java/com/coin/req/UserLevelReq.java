package com.coin.req;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

/**
 * 会员等级请求对象
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
public class UserLevelReq extends CommonReq implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer level;

    private String name;

    private String badge;

    private Integer needExp;

    private String privilege;
}

