package com.coin.resp;

import lombok.Data;

import java.util.Date;

@Data
public class ReportNameTagsResp {

    private Long id;
    private String name;
    private Date createdAt;
    private Date updatedAt;
}
