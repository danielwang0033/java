package com.coin.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ThreadReply implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long threadId;
    private Long replyId;
    private Long userId;
    private Integer floorNo;
    private String content;
    private String pics;
    private Integer needProcessPics;
    private Date lastModifyAt;
    private Integer lastModifyUserId;
    private Date createdAt;
    private Date updatedAt;
    private Date deletedAt;

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                " [" +
                "Hash = " + hashCode() +
                ", id=" + id +
                ", threadId=" + threadId +
                ", replyId=" + replyId +
                ", userId=" + userId +
                ", floorNo=" + floorNo +
                ", content=" + content +
                ", pics=" + pics +
                ", needProcessPics=" + needProcessPics +
                ", lastModifyAt=" + lastModifyAt +
                ", lastModifyUserId=" + lastModifyUserId +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", deletedAt=" + deletedAt +
                ", serialVersionUID=" + serialVersionUID +
                "]";
    }
}