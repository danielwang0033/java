package com.coin.service.asyn;

import com.coin.resp.dict.DictBobiConfig;
import com.coin.resp.dict.DictLevelConfig;
import com.coin.resp.dict.ExtraMsgVo;

/**
 * 博币和经验处理服务
 */
public interface BobiAndExpService {

    DictBobiConfig dictBobiConfig();

    DictLevelConfig dictLevelConfig();

    /**
     * 每日活跃 加博币和经验
     */
    ExtraMsgVo activeEveryDay(Long loginUserId);

    /**
     * 首次上传头像 加博币和经验
     */
    ExtraMsgVo firstUploadUserAvatar(Long loginUserId);

    /**
     * 每日首贴 发帖用户加博币和经验
     */
    ExtraMsgVo dailyThread(Long userId);

    /**
     * 用户注册成功 注册用户加博币和经验
     */
    ExtraMsgVo register(Long userId);

    /**
     * 用户登录成功 登录用户加博币和经验
     */
    ExtraMsgVo login(Long userId);

    /**
     * 帖子被设置为热门帖 发帖用户加博币和经验
     */
    void threadHot(Long threadUserid);

    /**
     * 帖子被设置为精华帖 发帖用户加博币和经验
     */
    void threadBest(Long threadUserid);

    /**
     * 验证邮箱 被验证用户加博币和经验
     */
    ExtraMsgVo verifiedEmail(Long userId, String email);

    /**
     * 评论或回复 加博币和经验
     *
     * @param userId       写评论或回复的用户id
     * @param targetUserId 被评论对象的用户id
     */
    ExtraMsgVo replyOrComment(Long userId, Long targetUserId);

    /**
     * 活动加博币
     */
    void activityAddBobi(Long userId, Integer bobi, String note);

    /**
     * 活动加经验
     */
    void activityAddExp(Long userId, Integer exp, String note);
}
