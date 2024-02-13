package com.coin.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class GuessReply implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long guessId;
    private Long userId;
    private String userName;
    private String content;
    private Integer status;
    private Date createdAt;
    private Date updatedAt;

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                " [" +
                "Hash = " + hashCode() +
                ", id=" + id +
                ", guessId=" + guessId +
                ", userId=" + userId +
                ", userName=" + userName +
                ", content=" + content +
                ", status=" + status +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", serialVersionUID=" + serialVersionUID +
                "]";
    }
}