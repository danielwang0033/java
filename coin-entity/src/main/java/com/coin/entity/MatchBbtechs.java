package com.coin.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class MatchBbtechs implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Integer homeid;
    private Integer homebackboard;
    private Integer homefoul;
    private Integer homethreescore;
    private Integer hometwoscore;
    private Integer homepenalty;
    private String homepenaltyrate;
    private Integer homebackboardf;
    private Integer homebackboardb;
    private Integer homeassists;
    private Integer homesnatch;
    private Integer homeblock;
    private Integer homefault;
    private Integer awayid;
    private Integer awaybackboard;
    private Integer awayfoul;
    private Integer awaythreescore;
    private Integer awaytwoscore;
    private Integer awaypenalty;
    private String awaypenaltyrate;
    private Integer awaybackboardf;
    private Integer awaybackboardb;
    private Integer awayassists;
    private Integer awaysnatch;
    private Integer awayblock;
    private Integer awayfault;
    private Integer matchid;
    private Date createdAt;
    private Date updatedAt;

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                " [" +
                "Hash = " + hashCode() +
                ", id=" + id +
                ", homeid=" + homeid +
                ", homebackboard=" + homebackboard +
                ", homefoul=" + homefoul +
                ", homethreescore=" + homethreescore +
                ", hometwoscore=" + hometwoscore +
                ", homepenalty=" + homepenalty +
                ", homepenaltyrate=" + homepenaltyrate +
                ", homebackboardf=" + homebackboardf +
                ", homebackboardb=" + homebackboardb +
                ", homeassists=" + homeassists +
                ", homesnatch=" + homesnatch +
                ", homeblock=" + homeblock +
                ", homefault=" + homefault +
                ", awayid=" + awayid +
                ", awaybackboard=" + awaybackboard +
                ", awayfoul=" + awayfoul +
                ", awaythreescore=" + awaythreescore +
                ", awaytwoscore=" + awaytwoscore +
                ", awaypenalty=" + awaypenalty +
                ", awaypenaltyrate=" + awaypenaltyrate +
                ", awaybackboardf=" + awaybackboardf +
                ", awaybackboardb=" + awaybackboardb +
                ", awayassists=" + awayassists +
                ", awaysnatch=" + awaysnatch +
                ", awayblock=" + awayblock +
                ", awayfault=" + awayfault +
                ", matchid=" + matchid +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", serialVersionUID=" + serialVersionUID +
                "]";
    }
}