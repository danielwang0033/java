package com.coin.resp;

import lombok.Data;

import java.util.Date;

@Data
public class ReportReplyResp {
    // 回帖Id
    private Long id;

    // 申诉id
    private Long reportId;

    // 作者用户id
    private Long userId;

    // 内容
    private String content;

    // 最后编辑时间
    private Date lastModifyAt;

    // 最后编辑者用户id
    private Integer lastModifyUserId;
    private Date createdAt;
    private Date updatedAt;
    private Date deletedAt;

    private String reportName;

    private String userName;
}
