package com.coin.resp.guess;

import com.coin.cache.UserLevelCache;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * 查看详情
 */
@Data
public class GuessDetailVo {

    private Long id;

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

    // 管理员用户头像
    private String adminUserAvatar;

    private Integer adminUserExp;

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
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date beginTime;

    // 截止时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date endTime;

    // 状态, 0: 手动关闭 1: 未开始 2:进行中 3:已结束
    private Integer status;

    // 创建时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date createdAt;

    private List<GuessItemVo> guessItemList;

    // 总参与人数
    private Integer betUserCount;

    // 点赞状态
    private Integer likeStatus;

    // 收藏状态
    private Integer favStatus;

    // 背景图片
    private String backgroundImage;

    // 赛事开始时间
    private Date matchStartTime;

    // 主队名称
    private String homeTeamName;

    // 客队名称
    private String guestTeamName;

    private UserLevelCache userLevel;

    private UserLevelCache nextLevel;

    private Integer isFollow;
}
