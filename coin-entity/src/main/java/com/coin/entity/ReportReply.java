package com.coin.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ReportReply implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long reportId;
    private Long userId;
    private String content;
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
                ", reportId=" + reportId +
                ", userId=" + userId +
                ", content=" + content +
                ", lastModifyAt=" + lastModifyAt +
                ", lastModifyUserId=" + lastModifyUserId +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", deletedAt=" + deletedAt +
                ", serialVersionUID=" + serialVersionUID +
                "]";
    }
}