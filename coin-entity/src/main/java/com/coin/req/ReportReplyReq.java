package com.coin.req;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ReportReplyReq extends CommonReq {

    // 申诉id
    private Long reportId;

    // 作者用户id
    private Long userId;

    // 内容
    private String content;

    // 最后编辑者用户id
    private Integer lastModifyUserId;
}
