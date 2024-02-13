package com.coin.req;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

/**
 * 系统字典请求对象
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
public class DictReq extends CommonReq implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 字典类型
     */
    private String dictType;
    /**
     * 字典编码
     */
    private String dictCode;
    /**
     * 字典名称
     */
    private String dictName;
    /**
     * 字典值
     */
    private String dictVal;
    /**
     * 字典大值
     */
    private String dictValBig;
    /**
     * 上级字典编码：上级的类型一定是相同的
     */
    private String parentDictCode;
    /**
     * 是否默认
     */
    private Integer isDefault;
    /**
     * 状态：1启用 0禁用
     */
    private Integer status;
    /**
     * 序号
     */
    private Integer sortNum;
    /**
     * 是否只以sortNum排序
     */
    private Integer onlySortNum;

    private String updateUser;
}
