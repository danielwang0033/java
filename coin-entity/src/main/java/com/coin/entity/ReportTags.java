package com.coin.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ReportTags implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String tagContent;
    private String tagImageUrl;
    private Date createdAt;
    private Date updatedAt;

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                " [" +
                "Hash = " + hashCode() +
                ", id=" + id +
                ", tagContent=" + tagContent +
                ", tagImageUrl=" + tagImageUrl +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", serialVersionUID=" + serialVersionUID +
                "]";
    }
}