package com.coin.req;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class GuessReq extends CommonReq {

    // 标题
    private String title;

    // 竞猜主题
    private String guessSubject;

    // 阵营, 1:无阵营 1:双阵营
    private Integer campType;

    // 竞猜类型id
    private Long guessTypeId;

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

    // 管理员用户头像
    private String adminUserAvatar;

    // 开启时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date beginTime;

    // 截止时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date endTime;

    // 状态, 0: 手动关闭 1: 未开始 2:进行中 3:已结束
    private Integer status;

    private List<GuessItemReq> guessItemList;

    private String sortType;

    private String secondConfirm;

    // 赛事开始时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date matchStartTime;

    // 主队名称
    private String homeTeamName;

    // 客队名称
    private String guestTeamName;
}
