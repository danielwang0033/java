package com.coin.resp;

import lombok.Data;

import java.util.Date;

@Data
public class GoodTagsResp {

    private Long id;
    private String tagContent;
    private String tagImageUrl;
    private Date createdAt;
    private Date updatedAt;
}
