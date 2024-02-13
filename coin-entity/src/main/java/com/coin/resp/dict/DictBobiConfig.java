package com.coin.resp.dict;

import lombok.Data;

import java.util.List;

@Data
public class DictBobiConfig {

    // 每日活跃
    private Integer active;
    // 上传头像
    private Integer avatar;
    // 帖子被评论
    private List<Integer> beComment;
    // 每日被回帖获取博币上限
    private Integer dailyBeRepliedBobi;
    // 每日回帖获取博币上限
    private Integer dailyReplyBobi;
    // 每日首贴
    private List<Integer> dailyThread;
    // 注册
    private Integer register;
    // 回帖/评论
    private List<Integer> replyThread;
    // 精华帖子
    private Integer threadBest;
    // 热门帖子
    private Integer threadHot;
    // 验证邮箱
    private Integer verifiedEmail;
}
