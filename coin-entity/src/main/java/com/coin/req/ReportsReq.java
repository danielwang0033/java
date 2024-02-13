package com.coin.req;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class ReportsReq extends CommonReq {

    // 用户id
    private Long userId;

    // 被举报网站地址
    private String reportedWebsiteUrl;

    private String websiteUrl;

    // 被举报网站名称
    private String reportedWebsiteName;

    private String websiteName;

    // 举报原因id
    private Long reportReasonId;

    private Long reasonId;

    // 申诉金额
    private BigDecimal appealAmount;

    private BigDecimal amount;

    // 举报内容
    private String reportContent;

    private String content;

    // 摘要
    private String desc;

    private Integer replyCount;

    private Integer readCount;

    // 标签(json,可以多个,关联标签表)
    private String tags;

    private String tag;

    // 盘口名称标签id
    private Integer nameTagId;

    // 上传图片(多张,json存储)
    private String pics;

    private Integer needThumb;

    private String thumbs;

    // 状态(0-未受理,1-已受理处理中,2-处理成功,3-处理失败)
    private Integer status;

    // 处理过程
    private String process;

    // 处理结果
    private String result;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createdAtMin;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createdAtMax;

    // 0为查询维权榜单 1 为查询成功出款榜单 2为查询非成功申诉榜单 默认0
    private String issucceed;
    private Integer successType;

    private String userName;

    private String sort;
    private Integer showHandtag;
    private String handtag;

    private Long reportId;
}
