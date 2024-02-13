package com.coin.resp.dict;

import lombok.Data;

@Data
public class DictLevelConfig {

    // 每日活跃经验
    private Integer active;
    // 上传头像经验
    private Integer avatar;
    // 帖子被评论
    private Integer beComment;
    // 每日帖子被回复获得经验次数
    private Integer dailyBeRepliedTimes;
    // 每日回复获得经验次数
    private Integer dailyReplyTimes;
    // 每日首贴经验
    private Integer dailyThread;
    // 注册获得经验
    private Integer register;
    // 回帖/评论经验
    private Integer replyThread;
    // 精华帖子经验
    private Integer threadBest;
    // 热门帖子经验
    private Integer threadHot;
    // 验证邮箱经验
    private Integer verifiedEmail;
}
