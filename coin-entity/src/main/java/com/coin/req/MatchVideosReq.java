package com.coin.req;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class MatchVideosReq extends CommonReq {

    // 比赛ID
    private String matchId;

    // 线路
    private String line;

    private Integer streamId;

    private Integer pushing;

    private Integer streamType;

    private String streamName;

    private String m3u8;
}
