package com.coin.req;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

/**
 * 管理后台角色请求对象
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
public class AdminRolesReq extends CommonReq implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;

    private String slug;
}

