package com.coin.resp;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class GoodsRsp implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private Integer goodsType;

    private String goodsName;

    private Integer price;

    private String goodsDesc;

    private String goodsPic;

    private Integer isSignGoods;

    private String signPic;

    private Integer goodsNum;

    private Integer sortNum;

    private Integer changeType;

    private Integer isSale;

    private Integer isCheck;

    private Integer isWelfareShow;

    private Integer isEventShow;

    private Integer status;

    private String createUser;

    private Date createDate;

    private String updateUser;

    private Date updateDate;

}