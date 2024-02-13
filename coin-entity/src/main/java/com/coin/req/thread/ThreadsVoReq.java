package com.coin.req.thread;

import com.coin.req.CommonReq;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ThreadsVoReq extends CommonReq {

    // 版块ID
    private Long forumId;

    // 是否加粗
    private Long topicId;

    // 作者用户ID
    private Long userId;

    // 主题
    private String subject;

    // 主题类型
    private Integer type;

    // 帖子标签
    private String tags;

    // 内容中所包含的图片
    private String pics;

    private Integer needThumb;

    // 缩略图
    private String thumbs;

    // 是否置顶
    private Integer isTop;

    // 阅读量
    private Integer readCount;

    // 回复量
    private Integer replyCount;

    // 帖子热度评分
    private Integer rankScore;

    // 最后编辑者用户id
    private Integer lastModifyUserId;

    // 是否允许删除
    private Integer notAllowedDelete;

    // 是否允许编辑
    private Integer notAllowedModify;

    // 最后回复用户
    private Integer lastReplyUserId;

    // 内容
    private String content;

    // 摘要
    private String desc;

    // 是否加粗
    private Integer titleIsBold;

    // 标题颜色
    private String titleColor;

    // 标题是否倾斜
    private Integer titleIsItalicized;

    // 内容中所包含的视频
    private String vids;

    // 内容中所包含的视频
    private String userName;
    private Integer incrLikes;

    private String categoryAlias;

    private Integer days;
    private String tag;
    private String sort;
    private Integer notop;
    private Long topic;
    private Long subnav;
    private Integer p;
}
