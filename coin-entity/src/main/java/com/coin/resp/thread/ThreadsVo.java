package com.coin.resp.thread;

import com.coin.entity.Forums;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ThreadsVo {
    // 帖子ID
    private Long id;

    // 版块ID
    private Long forumId;
    //版块名称
    private Forums forum;

    // 是否加粗
    private Long topicId;

    // 作者用户ID
    private Long userId;

    // 作者用户名称
    private String userName;

    private String userLevelBadge;

    private String avatar;

    // 主题
    private String subject;

    // 主题类型
    private Integer type;

    // 帖子标签
    private List<String> tags;

    // 内容中所包含的图片
    private String pics;
    private Integer needThumb;

    // 缩略图
    private List<String> thumbs;

    // 是否置顶
    private Integer isTop;

    // 阅读量
    private Integer readCount;

    // 回复量
    private Integer replyCount;

    // 帖子热度评分
    private Integer rankScore;

    // 最后编辑时间
    private Date lastModifyAt;

    // 最后编辑者用户id
    private Integer lastModifyUserId;

    // 是否允许删除
    private Integer notAllowedDelete;

    // 是否允许编辑
    private Integer notAllowedModify;

    // 最后回复时间
    private Date lastReplyTime;

    // 最后回复用户
    private Integer lastReplyUserId;

    // 内容
    private String content;

    // 摘要
    private String desc;
    private Date createdAt;
    private Date updatedAt;
    private Date deletedAt;

    // 是否加粗
    private Integer titleIsBold;

    // 标题颜色
    private String titleColor;

    // 标题是否倾斜
    private Integer titleIsItalicized;

    // 内容中所包含的视频
    private String vids;

    private Long threadReplyNum;
    private Integer incrLikes;

    private Date postTime;

    private ThreadTopicsVo topic;
}
