package com.coin.resp.guess;

import com.coin.entity.UserLevel;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 竞猜列表
 */
@Data
public class GuessListVo {

    // ID
    private Long id;

    // 标题
    private String title;

    // 竞猜主题
    private String guessSubject;

    // 竞猜类型名称
    private String guessTypeName;

    // 访问量
    private Integer visits;

    // 评论数
    private Integer comments;

    // 管理员用户名
    private String adminUserName;

    // 管理员用户头像
    private String adminUserAvatar;

    // 管理员用户经验
    private Integer adminUserExp;

    // 内容摘要
    private String contentSnippet;

    // 内容图片,前3张
    private String contentImages;

    // 状态, 0: 手动关闭 1: 未开始 2:进行中 3:已结束
    private Integer status;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date beginTime;

    // 截止时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date endTime;

    // 赛事开始时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date matchStartTime;

    private UserLevel userLevel;
}
