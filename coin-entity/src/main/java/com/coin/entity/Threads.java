package com.coin.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Threads implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long forumId;
    private Long topicId;
    private Long userId;
    private String subject;
    private Integer type;
    private String tags;
    private String pics;
    private Integer needThumb;
    private String thumbs;
    private Integer isTop;
    private Integer readCount;
    private Integer replyCount;
    private Integer rankScore;
    private Date lastModifyAt;
    private Integer lastModifyUserId;
    private Integer notAllowedDelete;
    private Integer notAllowedModify;
    private Date lastReplyTime;
    private Integer lastReplyUserId;
    private String content;
    private String desc;
    private Date createdAt;
    private Date updatedAt;
    private Integer incrLikes;
    private Date deletedAt;
    private Integer titleIsBold;
    private String titleColor;
    private Integer titleIsItalicized;
    private String vids;

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                " [" +
                "Hash = " + hashCode() +
                ", id=" + id +
                ", forumId=" + forumId +
                ", topicId=" + topicId +
                ", userId=" + userId +
                ", subject=" + subject +
                ", type=" + type +
                ", tags=" + tags +
                ", pics=" + pics +
                ", needThumb=" + needThumb +
                ", thumbs=" + thumbs +
                ", isTop=" + isTop +
                ", readCount=" + readCount +
                ", replyCount=" + replyCount +
                ", rankScore=" + rankScore +
                ", lastModifyAt=" + lastModifyAt +
                ", lastModifyUserId=" + lastModifyUserId +
                ", notAllowedDelete=" + notAllowedDelete +
                ", notAllowedModify=" + notAllowedModify +
                ", lastReplyTime=" + lastReplyTime +
                ", lastReplyUserId=" + lastReplyUserId +
                ", content=" + content +
                ", desc=" + desc +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", incrLikes=" + incrLikes +
                ", deletedAt=" + deletedAt +
                ", titleIsBold=" + titleIsBold +
                ", titleColor=" + titleColor +
                ", titleIsItalicized=" + titleIsItalicized +
                ", vids=" + vids +
                ", serialVersionUID=" + serialVersionUID +
                "]";
    }
}