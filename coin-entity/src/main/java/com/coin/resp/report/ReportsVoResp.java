package com.coin.resp.report;

import com.coin.resp.user.UserRelationLevelVo;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class ReportsVoResp {
    // 主键
    private Long id;

    // 用户id
    private Long userId;
    private String userName;
    private String avatar;

    // 被举报网站地址
    private String websiteUrl;

    // 被举报网站名称
    private String websiteName;

    // 举报原因id
    private Long reportReasonId;

    // 申诉金额
    private BigDecimal amount;

    // 举报内容
    private String reportContent;

    // 摘要
    private String desc;
    private Integer replyCount;
    private Integer readCount;

    // 标签(json,可以多个,关联标签表)
    private String tags;

    // 盘口名称标签id
    private Integer nameTagId;

    // 上传图片(多张,json存储)
    private String pics;
    private Integer needThumb;
    private List<String> thumbs;

    // 状态(0-未受理,1-已受理处理中,2-处理成功,3-处理失败)
    private Integer status;

    // 提交时间
    private Date submissionTime;

    // 受理时间
    private Date acceptanceTime;

    // 完成时间
    private Date completionTime;

    // 处理过程
    private String process;

    // 处理结果
    private String result;
    private Date deletedAt;
    private Date createdAt;
    private Date updatedAt;

    private ReasonsVo reason;

    private UserRelationLevelVo level;

    private Date postTime;

    private Integer showHandtag;
    private String handtag;
}
