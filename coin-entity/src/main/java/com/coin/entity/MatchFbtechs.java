package com.coin.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class MatchFbtechs implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Integer homeid;
    private Byte homecorner;
    private Byte homered;
    private Byte homeyellow;
    private String homeballrate;
    private Integer homeattack;
    private Integer homedangerattack;
    private Integer homeshoot;
    private Integer homeshooton;
    private Integer awayid;
    private Integer awaycorner;
    private Integer awayred;
    private Integer awayyellow;
    private String awayballrate;
    private Integer awayattack;
    private Integer awaydangerattack;
    private Integer awayshoot;
    private Integer awayshooton;
    private Integer matchid;
    private Date updatedAt;
    private Date createdAt;

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                " [" +
                "Hash = " + hashCode() +
                ", id=" + id +
                ", homeid=" + homeid +
                ", homecorner=" + homecorner +
                ", homered=" + homered +
                ", homeyellow=" + homeyellow +
                ", homeballrate=" + homeballrate +
                ", homeattack=" + homeattack +
                ", homedangerattack=" + homedangerattack +
                ", homeshoot=" + homeshoot +
                ", homeshooton=" + homeshooton +
                ", awayid=" + awayid +
                ", awaycorner=" + awaycorner +
                ", awayred=" + awayred +
                ", awayyellow=" + awayyellow +
                ", awayballrate=" + awayballrate +
                ", awayattack=" + awayattack +
                ", awaydangerattack=" + awaydangerattack +
                ", awayshoot=" + awayshoot +
                ", awayshooton=" + awayshooton +
                ", matchid=" + matchid +
                ", updatedAt=" + updatedAt +
                ", createdAt=" + createdAt +
                ", serialVersionUID=" + serialVersionUID +
                "]";
    }
}