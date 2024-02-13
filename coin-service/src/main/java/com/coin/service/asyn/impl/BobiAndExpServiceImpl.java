package com.coin.service.asyn.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.coin.entity.TDict;
import com.coin.enums.CacheKeyEnum;
import com.coin.enums.NotificationType;
import com.coin.i18n.I18nUtil;
import com.coin.i18n.LongTextTranslate;
import com.coin.req.DictReq;
import com.coin.req.NotificationsReq;
import com.coin.resp.dict.*;
import com.coin.resp.user.AddBobiAndExpTypeVo;
import com.coin.resp.user.UserLevelVo;
import com.coin.resp.user.UserSimpleInfoVo;
import com.coin.service.*;
import com.coin.service.asyn.BobiAndExpService;
import com.coin.service.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class BobiAndExpServiceImpl implements BobiAndExpService {

    @Resource
    private RedisUtil redisUtil;
    @Resource
    private DictService dictService;
    @Resource
    private WalletsService walletsService;
    @Resource
    private UsersService usersService;
    @Resource
    private UserLevelService userLevelService;
    @Resource
    private NotificationsService notificationsService;

    /**
     * 配置转List
     */
    private static List<Integer> getIntegerList(TDict item) {
        String dictVal = item.getDictVal();
        List<Integer> result = new ArrayList<>();
        if (StrUtil.isNotBlank(dictVal)) {
            String[] split = dictVal.split(",");
            for (String str : split) {
                result.add(Integer.parseInt(str));
            }
        }
        return result;
    }

    /**
     * 获取当天剩余毫秒数
     */
    private static long getTodayRemainMilliseconds() {
        Date now = new Date();
        DateTime endOfDay = DateUtil.endOfDay(now);
        return DateUtil.betweenMs(now, endOfDay);
    }

    @Override
    public DictBobiConfig dictBobiConfig() {
        ValueOperations<String, Object> redisTemplateForObject = redisUtil.getRedisTemplateForObject();
        Object obj = redisTemplateForObject.get(CacheKeyEnum.BOBI_CONFIG.getKeyName());
        if (ObjectUtil.isNull(obj)) {
            DictReq req = new DictReq();
            req.setDictType("bobi");
            req.setStatus(1);
            List<TDict> listByType = dictService.getListByType(req);
            DictBobiConfig config = new DictBobiConfig();
            for (TDict item : listByType) {
                String dictCode = item.getDictCode();
                switch (dictCode) {
                    case "active":
                        config.setActive(Integer.parseInt(item.getDictVal()));
                        break;
                    case "avatar":
                        config.setAvatar(Integer.parseInt(item.getDictVal()));
                        break;
                    case "be_comment":
                        config.setBeComment(getIntegerList(item));
                        break;
                    case "daily_be_replied_bobi":
                        config.setDailyBeRepliedBobi(Integer.parseInt(item.getDictVal()));
                        break;
                    case "daily_reply_bobi":
                        config.setDailyReplyBobi(Integer.parseInt(item.getDictVal()));
                        break;
                    case "daily_thread":
                        config.setDailyThread(getIntegerList(item));
                        break;
                    case "register":
                        config.setRegister(Integer.parseInt(item.getDictVal()));
                        break;
                    case "reply_thread":
                        config.setReplyThread(getIntegerList(item));
                        break;
                    case "thread_best":
                        config.setThreadBest(Integer.parseInt(item.getDictVal()));
                        break;
                    case "thread_hot":
                        config.setThreadHot(Integer.parseInt(item.getDictVal()));
                        break;
                    case "verified_email":
                        config.setVerifiedEmail(Integer.parseInt(item.getDictVal()));
                        break;
                }
            }
            redisTemplateForObject.set(CacheKeyEnum.BOBI_CONFIG.getKeyName(), config);
            return config;
        }
        return (DictBobiConfig) obj;
    }

    @Override
    public DictLevelConfig dictLevelConfig() {
        ValueOperations<String, Object> redisTemplateForObject = redisUtil.getRedisTemplateForObject();
        Object obj = redisTemplateForObject.get(CacheKeyEnum.LEVEL_CONFIG.getKeyName());
        if (ObjectUtil.isNull(obj)) {
            DictReq req = new DictReq();
            req.setDictType("level");
            req.setStatus(1);
            List<TDict> listByType = dictService.getListByType(req);
            DictLevelConfig config = new DictLevelConfig();
            for (TDict item : listByType) {
                String dictCode = item.getDictCode();
                switch (dictCode) {
                    case "active":
                        config.setActive(Integer.parseInt(item.getDictVal()));
                        break;
                    case "avatar":
                        config.setAvatar(Integer.parseInt(item.getDictVal()));
                        break;
                    case "be_comment":
                        config.setBeComment(Integer.parseInt(item.getDictVal()));
                        break;
                    case "daily_be_replied_times":
                        config.setDailyBeRepliedTimes(Integer.parseInt(item.getDictVal()));
                        break;
                    case "daily_reply_times":
                        config.setDailyReplyTimes(Integer.parseInt(item.getDictVal()));
                        break;
                    case "daily_thread":
                        config.setDailyThread(Integer.parseInt(item.getDictVal()));
                        break;
                    case "register":
                        config.setRegister(Integer.parseInt(item.getDictVal()));
                        break;
                    case "reply_thread":
                        config.setReplyThread(Integer.parseInt(item.getDictVal()));
                        break;
                    case "thread_best":
                        config.setThreadBest(Integer.parseInt(item.getDictVal()));
                        break;
                    case "thread_hot":
                        config.setThreadHot(Integer.parseInt(item.getDictVal()));
                        break;
                    case "verified_email":
                        config.setVerifiedEmail(Integer.parseInt(item.getDictVal()));
                        break;
                }
            }
            redisTemplateForObject.set(CacheKeyEnum.LEVEL_CONFIG.getKeyName(), config);
            return config;
        }
        return (DictLevelConfig) obj;
    }

    @Override
    public ExtraMsgVo activeEveryDay(Long loginUserId) {
        // 判断是否已近加过积分
        String key = String.format(CacheKeyEnum.ADD_BOBI_EXP_ACTIVE_EVERY_DAY.getKeyName(), loginUserId);
        Object obj = redisUtil.getRedisTemplateForObject().get(key);
        if (ObjectUtil.isNull(obj)) {
            // 封装响应对象
            ExtraMsgVo extraMsgVo = new ExtraMsgVo();
            String info = I18nUtil.translateBiz("每日活跃奖励");
            String type = "active";
            AddBobiAndExpTypeVo typeVo = buildAddBobiAndExpTypeVo(info, type);
            Integer bobi = this.dictBobiConfig().getActive();
            if (ObjectUtil.isNotNull(bobi) && bobi > 0) {
                walletsService.addBobiByUserId(loginUserId, bobi, JSONUtil.toJsonStr(typeVo));
                extraMsgVo.setBobi(new ExtraBobiVo(bobi + "", info));
            }
            Integer exp = this.dictLevelConfig().getActive();
            if (ObjectUtil.isNotNull(exp) && exp > 0) {
                usersService.addExpByUserId(loginUserId, exp, info);
                extraMsgVo.setExp(new ExtraExpVo(exp + "", info));
            }
            // 设置key有效期
            long betweenMs = getTodayRemainMilliseconds();
            redisUtil.getRedisTemplateForObject().set(key, "1", betweenMs, TimeUnit.MILLISECONDS);
            return extraMsgVo;
        }
        return null;
    }

    @Override
    public ExtraMsgVo firstUploadUserAvatar(Long loginUserId) {
        // 封装响应对象
        ExtraMsgVo extraMsgVo = new ExtraMsgVo();
        String info = I18nUtil.translateBiz("首次上传头像奖励");
        String type = "avatar";
        AddBobiAndExpTypeVo typeVo = buildAddBobiAndExpTypeVo(info, type);
        Integer bobi = this.dictBobiConfig().getAvatar();
        if (ObjectUtil.isNotNull(bobi) && bobi > 0) {
            walletsService.addBobiByUserId(loginUserId, bobi, JSONUtil.toJsonStr(typeVo));
            extraMsgVo.setBobi(new ExtraBobiVo(bobi + "", info));
        }
        Integer exp = this.dictLevelConfig().getAvatar();
        if (ObjectUtil.isNotNull(exp) && exp > 0) {
            usersService.addExpByUserId(loginUserId, exp, info);
            extraMsgVo.setExp(new ExtraExpVo(exp + "", info));
        }
        return extraMsgVo;
    }

    @Override
    public ExtraMsgVo dailyThread(Long userId) {
        // 判断是否已近加过积分
        String key = String.format(CacheKeyEnum.ADD_BOBI_EXP_DAILY_THREAD.getKeyName(), userId);
        Object obj = redisUtil.getRedisTemplateForObject().get(key);
        if (ObjectUtil.isNull(obj)) {
            // 封装响应对象
            ExtraMsgVo extraMsgVo = new ExtraMsgVo();
            String info = I18nUtil.translateBiz("每日首帖奖励");
            String type = "daily_thread";
            AddBobiAndExpTypeVo typeVo = buildAddBobiAndExpTypeVo(info, type);
            List<Integer> bobiList = this.dictBobiConfig().getDailyThread();
            Integer bobi = matchBobiByUserLevel(bobiList, userId);
            if (ObjectUtil.isNotNull(bobi) && bobi > 0) {
                walletsService.addBobiByUserId(userId, bobi, JSONUtil.toJsonStr(typeVo));
                extraMsgVo.setBobi(new ExtraBobiVo(bobi + "", info));
            }
            Integer exp = this.dictLevelConfig().getDailyThread();
            if (ObjectUtil.isNotNull(exp) && exp > 0) {
                usersService.addExpByUserId(userId, exp, info);
                extraMsgVo.setExp(new ExtraExpVo(exp + "", info));
            }
            // 设置key有效期
            long betweenMs = getTodayRemainMilliseconds();
            redisUtil.getRedisTemplateForObject().set(key, "1", betweenMs, TimeUnit.MILLISECONDS);
            return extraMsgVo;
        }
        return null;
    }

    @Override
    public ExtraMsgVo register(Long userId) {
        // 封装响应对象
        ExtraMsgVo extraMsgVo = new ExtraMsgVo();
        String info = I18nUtil.translateBiz("注册赠送博币");
        String type = "register";
        AddBobiAndExpTypeVo typeVo = buildAddBobiAndExpTypeVo(info, type);
        Integer bobi = this.dictBobiConfig().getRegister();
        if (ObjectUtil.isNotNull(bobi) && bobi > 0) {
            walletsService.addBobiByUserId(userId, bobi, JSONUtil.toJsonStr(typeVo));
            extraMsgVo.setBobi(new ExtraBobiVo(bobi + "", info));
        }
        Integer exp = this.dictLevelConfig().getRegister();
        if (ObjectUtil.isNotNull(exp) && exp > 0) {
            usersService.addExpByUserId(userId, exp, info);
            extraMsgVo.setExp(new ExtraExpVo(exp + "", info));
        }
        return extraMsgVo;
    }

    @Override
    public ExtraMsgVo login(Long userId) {
        String key = String.format(CacheKeyEnum.ADD_BOBI_EXP_LOGIN.getKeyName(), userId);
        Object obj = redisUtil.getRedisTemplateForObject().get(key);
        if (ObjectUtil.isNull(obj)) {
            // 封装响应对象
            ExtraMsgVo extraMsgVo = new ExtraMsgVo();
            String info = I18nUtil.translateBiz("登录赠送博币");
            String type = "login";
            AddBobiAndExpTypeVo typeVo = buildAddBobiAndExpTypeVo(info, type);
            Integer bobi = this.dictBobiConfig().getRegister();
            if (ObjectUtil.isNotNull(bobi) && bobi > 0) {
                walletsService.addBobiByUserId(userId, bobi, JSONUtil.toJsonStr(typeVo));
                extraMsgVo.setBobi(new ExtraBobiVo(bobi + "", info));
            }
            Integer exp = this.dictLevelConfig().getRegister();
            if (ObjectUtil.isNotNull(exp) && exp > 0) {
                usersService.addExpByUserId(userId, exp, info);
                extraMsgVo.setExp(new ExtraExpVo(exp + "", info));
            }
            // 设置key有效期
            long betweenMs = getTodayRemainMilliseconds();
            redisUtil.getRedisTemplateForObject().set(key, 1, betweenMs, TimeUnit.MILLISECONDS);
            return extraMsgVo;
        }
        return null;
    }

    @Override
    public void threadHot(Long threadUserid) {
        String info = I18nUtil.translateBiz("帖子上热门");
        String type = "thread_be_hot";
        AddBobiAndExpTypeVo typeVo = buildAddBobiAndExpTypeVo(info, type);
        Integer bobi = this.dictBobiConfig().getThreadHot();
        if (ObjectUtil.isNotNull(bobi) && bobi > 0) {
            walletsService.addBobiByUserId(threadUserid, bobi, JSONUtil.toJsonStr(typeVo));
        }
        Integer exp = this.dictLevelConfig().getThreadHot();
        if (ObjectUtil.isNotNull(exp) && exp > 0) {
            usersService.addExpByUserId(threadUserid, exp, info);
        }
    }

    @Override
    public void threadBest(Long threadUserid) {
        String info = I18nUtil.translateBiz("帖子加精华");
        String type = "thread_be_best";
        AddBobiAndExpTypeVo typeVo = buildAddBobiAndExpTypeVo(info, type);
        Integer bobi = this.dictBobiConfig().getThreadBest();
        if (ObjectUtil.isNotNull(bobi) && bobi > 0) {
            walletsService.addBobiByUserId(threadUserid, bobi, JSONUtil.toJsonStr(typeVo));
        }
        Integer exp = this.dictLevelConfig().getThreadBest();
        if (ObjectUtil.isNotNull(exp) && exp > 0) {
            usersService.addExpByUserId(threadUserid, exp, info);
        }
    }

    @Override
    public ExtraMsgVo verifiedEmail(Long userId, String email) {
        // 封装响应对象
        ExtraMsgVo extraMsgVo = new ExtraMsgVo();
        String info = I18nUtil.translateBiz("验证邮箱奖励");
        String type = "verified_email";
        AddBobiAndExpTypeVo typeVo = buildAddBobiAndExpTypeVo(info, type);
        Integer bobi = this.dictBobiConfig().getVerifiedEmail();
        if (ObjectUtil.isNotNull(bobi) && bobi > 0) {
            walletsService.addBobiByUserId(userId, bobi, JSONUtil.toJsonStr(typeVo));
            extraMsgVo.setBobi(new ExtraBobiVo(bobi + "", info));
        }
        Integer exp = this.dictLevelConfig().getVerifiedEmail();
        if (ObjectUtil.isNotNull(exp) && exp > 0) {
            usersService.addExpByUserId(userId, exp, info);
            extraMsgVo.setExp(new ExtraExpVo(exp + "", info));
        }
        // 发系统消息
        LongTextTranslate longTextTranslateBean = I18nUtil.getLongTextTranslateBean();
        NotificationsReq notifications = new NotificationsReq();
        notifications.setType("App\\Notifications\\Registered");
        String message = longTextTranslateBean.buildNotificationMessage(NotificationType.VERIFIED_EMAIL, email);
        notifications.setMessage(message);
        notifications.setNotifiableId(userId);
        notificationsService.buildNotification(notifications);
        return extraMsgVo;
    }

    @Override
    public void activityAddBobi(Long userId, Integer bobi, String info) {
        String type = "activity_add_bobi";
        AddBobiAndExpTypeVo typeVo = buildAddBobiAndExpTypeVo(info, type);
        walletsService.addBobiByUserId(userId, bobi, JSONUtil.toJsonStr(typeVo));
    }

    @Override
    public void activityAddExp(Long userId, Integer exp, String note) {
        usersService.addExpByUserId(userId, exp, note);
    }

    @Override
    public ExtraMsgVo replyOrComment(Long userId, Long targetUserId) {
        // 先处理被回复人博币和经验
        this.beReplyOrComment(targetUserId);

        String key = String.format(CacheKeyEnum.ADD_BOBI_EXP_REPLY_OR_COMMENT.getKeyName(), userId);
        Object obj = redisUtil.getRedisTemplateForObject().get(key);

        String info = I18nUtil.translateBiz("发表回帖/回复获得博币");
        String type = "daily_reply";
        AddBobiAndExpTypeVo typeVo = buildAddBobiAndExpTypeVo(info, type);

        // 封装响应对象
        ExtraMsgVo extraMsgVo = new ExtraMsgVo();
        List<Integer> bobiList = this.dictBobiConfig().getReplyThread();
        Integer bobi = matchBobiByUserLevel(bobiList, userId);
        Integer exp = this.dictLevelConfig().getReplyThread();
        if (ObjectUtil.isNull(obj)) {
            // 今日首次回复
            if (ObjectUtil.isNotNull(bobi) && bobi > 0) {
                walletsService.addBobiByUserId(userId, bobi, JSONUtil.toJsonStr(typeVo));
                extraMsgVo.setBobi(new ExtraBobiVo(bobi + "", info));
            }
            if (ObjectUtil.isNotNull(exp) && exp > 0) {
                usersService.addExpByUserId(userId, exp, info);
                extraMsgVo.setExp(new ExtraExpVo(exp + "", info));
            }

            // 初始化对象
            ReplyCountVo replyCountVo = new ReplyCountVo();
            replyCountVo.setCount(1);
            replyCountVo.setTotalBobi(bobi);
            // 设置key有效期
            long betweenMs = getTodayRemainMilliseconds();
            redisUtil.getRedisTemplateForObject().set(key, replyCountVo, betweenMs, TimeUnit.MILLISECONDS);
            return extraMsgVo;
        } else {
            // 今日非首次回复
            ReplyCountVo redisObj = (ReplyCountVo) obj;
            Integer maxBobi = this.dictBobiConfig().getDailyReplyBobi();

            boolean flag = false;
            Integer count = redisObj.getCount();
            Integer totalBobi = redisObj.getTotalBobi();

            if (redisObj.getTotalBobi() < maxBobi) {
                if (ObjectUtil.isNotNull(bobi) && bobi > 0) {
                    walletsService.addBobiByUserId(userId, bobi, JSONUtil.toJsonStr(typeVo));
                    extraMsgVo.setBobi(new ExtraBobiVo(bobi + "", info));
                    totalBobi = totalBobi + bobi;
                    flag = true;
                }
            }
            Integer dailyReplyTimes = this.dictLevelConfig().getDailyReplyTimes();
            if (redisObj.getCount() < dailyReplyTimes) {
                if (ObjectUtil.isNotNull(exp) && exp > 0) {
                    usersService.addExpByUserId(userId, exp, info);
                    extraMsgVo.setExp(new ExtraExpVo(exp + "", info));
                    count = count + 1;
                    flag = true;
                }
            }
            if (flag) {
                // 重新赋值对象
                ReplyCountVo replyCountVo = new ReplyCountVo();
                replyCountVo.setCount(count);
                replyCountVo.setTotalBobi(totalBobi);
                // 设置key有效期
                long betweenMs = getTodayRemainMilliseconds();
                redisUtil.getRedisTemplateForObject().set(key, replyCountVo, betweenMs, TimeUnit.MILLISECONDS);
                return extraMsgVo;
            }
            return null;
        }
    }

    private void beReplyOrComment(Long targetUserId) {
        // 判断是否已近加过积分
        String key = String.format(CacheKeyEnum.ADD_BOBI_EXP_BE_REPLY_OR_COMMENT.getKeyName(), targetUserId);
        Object obj = redisUtil.getRedisTemplateForObject().get(key);

        String info = I18nUtil.translateBiz("帖子被回复获得博币");
        String type = "daily_be_replied";
        AddBobiAndExpTypeVo typeVo = buildAddBobiAndExpTypeVo(info, type);

        List<Integer> bobiList = this.dictBobiConfig().getBeComment();
        Integer bobi = matchBobiByUserLevel(bobiList, targetUserId);
        Integer exp = this.dictLevelConfig().getBeComment();
        if (ObjectUtil.isNull(obj)) {
            // 今日首次被回复
            if (ObjectUtil.isNotNull(bobi) && bobi > 0) {
                walletsService.addBobiByUserId(targetUserId, bobi, JSONUtil.toJsonStr(typeVo));
            }
            if (ObjectUtil.isNotNull(exp) && exp > 0) {
                usersService.addExpByUserId(targetUserId, exp, info);
            }

            // 初始化对象
            ReplyCountVo replyCountVo = new ReplyCountVo();
            replyCountVo.setCount(1);
            replyCountVo.setTotalBobi(bobi);
            // 设置key有效期
            long betweenMs = getTodayRemainMilliseconds();
            redisUtil.getRedisTemplateForObject().set(key, replyCountVo, betweenMs, TimeUnit.MILLISECONDS);
        } else {
            // 今日非首次被回复
            ReplyCountVo redisObj = (ReplyCountVo) obj;
            Integer maxBobi = this.dictBobiConfig().getDailyBeRepliedBobi();
            boolean flag = false;
            Integer count = redisObj.getCount();
            Integer totalBobi = redisObj.getTotalBobi();

            if (redisObj.getTotalBobi() < maxBobi) {
                if (ObjectUtil.isNotNull(bobi) && bobi > 0) {
                    walletsService.addBobiByUserId(targetUserId, bobi, JSONUtil.toJsonStr(typeVo));
                    totalBobi = totalBobi + bobi;
                    flag = true;
                }
            }
            Integer dailyReplyTimes = this.dictLevelConfig().getDailyBeRepliedTimes();
            if (redisObj.getCount() < dailyReplyTimes) {
                if (ObjectUtil.isNotNull(exp) && exp > 0) {
                    usersService.addExpByUserId(targetUserId, exp, info);
                    count = count + 1;
                    flag = true;
                }
            }
            if (flag) {
                // 重新赋值对象
                ReplyCountVo replyCountVo = new ReplyCountVo();
                replyCountVo.setCount(count);
                replyCountVo.setTotalBobi(totalBobi);
                // 设置key有效期
                long betweenMs = getTodayRemainMilliseconds();
                redisUtil.getRedisTemplateForObject().set(key, replyCountVo, betweenMs, TimeUnit.MILLISECONDS);
            }
        }
    }

    /**
     * 匹配博币值
     */
    Integer matchBobiByUserLevel(List<Integer> bobiList, Long userId) {
        if (CollectionUtil.isEmpty(bobiList) || ObjectUtil.isNull(userId)) {
            return 0;
        }
        try {
            UserSimpleInfoVo userSimpleInfoVo = usersService.selectUserSimpleInfoById(userId);
            if (ObjectUtil.isNotNull(userSimpleInfoVo)) {
                UserLevelVo userLevelVo = userLevelService.matchLevelByExp(userSimpleInfoVo.getExp());
                Integer level = userLevelVo.getUserLevel().getLevel();
                int size = bobiList.size();
                if (level > size || level <= 0) {
                    return 0;
                }
                return bobiList.get(level - 1);
            }
        } catch (Exception e) {
            log.error("matchBobiByUserLevel异常", e);
            return 0;
        }
        return 0;
    }

    private AddBobiAndExpTypeVo buildAddBobiAndExpTypeVo(String info, String type) {
        AddBobiAndExpTypeVo typeVo = new AddBobiAndExpTypeVo();
        typeVo.setType(type);
        typeVo.setInfo(info);
        return typeVo;
    }
}
