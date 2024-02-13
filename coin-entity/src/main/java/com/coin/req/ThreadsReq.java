package com.coin.req;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class ThreadsReq extends CommonReq {

    // 版块ID
    private Long forumId;

    // 是否加粗
    private Long topicId;

    private Long topic;

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

    // 最后回复时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date lastReplyTime;

    // 最后回复用户
    private Integer lastReplyUserId;

    // 内容
    private String content;

    // 摘要
    private String desc;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createdAtMin;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createdAtMax;

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

    // 处理tags  {"0": "hot", "1": "newer", "2": "best", "3": "break"}
    private Integer tagsHot;
    private Integer tagsNewer;
    private Integer tagsBest;
    private Integer tagsBreak;

    private String title;
}
