package com.coin.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class TDict implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String dictType;
    private String dictCode;
    private String dictName;
    private String dictVal;
    private String dictValBig;
    private String parentDictCode;
    private Integer isDefault;
    private Integer status;
    private Integer sortNum;
    private String createUser;
    private Date createDate;
    private String updateUser;
    private Date updateDate;

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                " [" +
                "Hash = " + hashCode() +
                ", id=" + id +
                ", dictType=" + dictType +
                ", dictCode=" + dictCode +
                ", dictName=" + dictName +
                ", dictVal=" + dictVal +
                ", dictValBig=" + dictValBig +
                ", parentDictCode=" + parentDictCode +
                ", isDefault=" + isDefault +
                ", status=" + status +
                ", sortNum=" + sortNum +
                ", createUser=" + createUser +
                ", createDate=" + createDate +
                ", updateUser=" + updateUser +
                ", updateDate=" + updateDate +
                ", serialVersionUID=" + serialVersionUID +
                "]";
    }
}