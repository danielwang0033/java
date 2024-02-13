package com.coin.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class Guess implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String title;
    private String guessSubject;
    private Integer campType;
    private Long guessTypeId;
    private Integer visits;
    private Integer comments;
    private String content;
    private Integer betUserCount;
    private String contentSnippet;
    private String contentImages;
    private Long adminUserId;
    private String adminUserName;
    private Date beginTime;
    private Date endTime;
    private String secondConfirm;
    private String flagImage;
    private Integer sort;
    private Integer status;
    private Date updatedAt;
    private Date createdAt;
    private List<GuessItem> guessItemList;
    private String guessTypeName;
    private Date matchStartTime;
    private String homeTeamName;
    private String guestTeamName;

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                " [" +
                "Hash = " + hashCode() +
                ", id=" + id +
                ", title=" + title +
                ", guessSubject=" + guessSubject +
                ", campType=" + campType +
                ", guessTypeId=" + guessTypeId +
                ", visits=" + visits +
                ", comments=" + comments +
                ", content=" + content +
                ", betUserCount=" + betUserCount +
                ", contentSnippet=" + contentSnippet +
                ", contentImages=" + contentImages +
                ", adminUserId=" + adminUserId +
                ", adminUserName=" + adminUserName +
                ", beginTime=" + beginTime +
                ", endTime=" + endTime +
                ", secondConfirm=" + secondConfirm +
                ", flagImage=" + flagImage +
                ", sort=" + sort +
                ", status=" + status +
                ", updatedAt=" + updatedAt +
                ", createdAt=" + createdAt +
                ", matchStartTime=" + matchStartTime +
                ", homeTeamName=" + homeTeamName +
                ", guestTeamName=" + guestTeamName +
                ", serialVersionUID=" + serialVersionUID +
                "]";
    }
}