package com.coin.resp;

import lombok.Data;

import java.util.Date;

@Data
public class GuessResp {
    // ID
    private Long id;

    // 标题
    private String title;

    // 竞猜主题
    private String guessSubject;

    // 阵营, 1:无阵营 1:双阵营
    private Integer campType;

    // 竞猜类型id
    private Integer guessTypeId;

    // 竞猜类型名称
    private String guessTypeName;

    // 访问量
    private Integer visits;

    // 评论数
    private Integer comments;

    // 内容
    private String content;

    // 管理员用户id
    private Long adminUserId;

    // 管理员用户名
    private String adminUserName;

    // 开启时间
    private Date beginTime;

    // 截止时间
    private Date endTime;

    // 状态, 0: 手动关闭 1: 未开始 2:进行中 3:已结束
    private Integer status;

    // 修改时间
    private Date updatedAt;

    // 创建时间
    private Date createdAt;
}
