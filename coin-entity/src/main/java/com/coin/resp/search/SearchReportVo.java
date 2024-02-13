package com.coin.resp.search;

import lombok.Data;

import java.util.Date;

@Data
public class SearchReportVo {

    private Long id;

    private Long userId;

    private UserShortVo user;

    private Long reportReasonId;

    private ReportReasonVo reportReason;

    private String content;

    private Integer replyCount;

    private Integer readCount;

    private Integer status;

    private Date createdAt;

    private Date postTime;

    // 被举报网站地址
    private String websiteUrl;
    // 被举报网站名称
    private String websiteName;
    // 被举报网站地址
    private String reportedWebsiteUrl;
    // 被举报网站名称
    private String reportedWebsiteName;

    public String getWebsiteUrl() {
        return reportedWebsiteUrl;
    }

    public String getWebsiteName() {
        return reportedWebsiteName;
    }
}