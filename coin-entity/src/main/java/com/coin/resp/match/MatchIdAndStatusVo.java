package com.coin.resp.match;

import lombok.Data;

@Data
public class MatchIdAndStatusVo {

    private String matchId;

    private Integer statusId;

    /* 需要保留原值的字段 */

    // 排序 999999
    private Integer sort;

    // url
    private String videoUrl;

    // 流名称
    private String streamName;

    // 房间号
    private String rid;

    // 0未发布，1发布
    private Integer publish;

    private Integer playing;
}
