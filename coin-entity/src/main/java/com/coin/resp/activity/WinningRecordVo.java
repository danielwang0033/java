package com.coin.resp.activity;

import lombok.Data;

import java.util.Date;

@Data
public class WinningRecordVo {

    private Long id;

    // 奖品名称
    private String prizeName;

    private Date createdAt;
}
