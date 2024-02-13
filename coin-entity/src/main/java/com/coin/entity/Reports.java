package com.coin.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class Reports implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long userId;
    private String reportedWebsiteUrl;
    private String reportedWebsiteName;
    private Long reportReasonId;
    private BigDecimal appealAmount;
    private String reportContent;
    private String desc;
    private Integer replyCount;
    private Integer readCount;
    private String tags;
    private Integer nameTagId;
    private String pics;
    private Integer needThumb;
    private String thumbs;
    private Integer status;
    private Date submissionTime;
    private Date acceptanceTime;
    private Date completionTime;
    private String process;
    private String result;
    private Date deletedAt;
    private Date createdAt;
    private Date updatedAt;
    private Integer showHandtag;
    private String handtag;

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                " [" +
                "Hash = " + hashCode() +
                ", id=" + id +
                ", userId=" + userId +
                ", reportedWebsiteUrl=" + reportedWebsiteUrl +
                ", reportedWebsiteName=" + reportedWebsiteName +
                ", reportReasonId=" + reportReasonId +
                ", appealAmount=" + appealAmount +
                ", reportContent=" + reportContent +
                ", desc=" + desc +
                ", replyCount=" + replyCount +
                ", readCount=" + readCount +
                ", tags=" + tags +
                ", nameTagId=" + nameTagId +
                ", pics=" + pics +
                ", needThumb=" + needThumb +
                ", thumbs=" + thumbs +
                ", status=" + status +
                ", submissionTime=" + submissionTime +
                ", acceptanceTime=" + acceptanceTime +
                ", completionTime=" + completionTime +
                ", process=" + process +
                ", result=" + result +
                ", deletedAt=" + deletedAt +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", showHandtag=" + showHandtag +
                ", handtag=" + handtag +
                ", serialVersionUID=" + serialVersionUID +
                "]";
    }
}