package com.coin.resp;

import com.coin.entity.Goods;
import com.coin.entity.Users;
import lombok.Data;

import java.util.Date;

@Data
public class OrdersResp {

    private Long id;
    private String sn;
    private Long userId;
    private String userName;
    private Users user;
    private Long goodId;
    //
    private Integer orderCount;
    private String goodName;
    private Goods good;
    private Integer status;
    private Date createdAt;
    private Date updatedAt;

    private Boolean canBuy = true;
}
