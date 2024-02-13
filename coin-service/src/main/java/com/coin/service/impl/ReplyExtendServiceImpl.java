package com.coin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.coin.cache.UserBobiVo;
import com.coin.cache.UserExtInfoCache;
import com.coin.cache.UserThreadCountVo;
import com.coin.entity.*;
import com.coin.enums.ApiExceptionEnum;
import com.coin.enums.CacheKeyEnum;
import com.coin.enums.NotificationType;
import com.coin.i18n.I18nUtil;
import com.coin.i18n.LongTextTranslate;
import com.coin.mapper.ReplyExtendMapper;
import com.coin.mapper.ext.ReplyExtendExtMapper;
import com.coin.req.ReplyExtendReq;
import com.coin.resp.ReportReplyResp;
import com.coin.resp.reply.ReplyExtendMgrVo;
import com.coin.resp.reply.ReplyExtendVo;
import com.coin.resp.reply.ReplyVo;
import com.coin.resp.user.UserLevelVo;
import com.coin.resp.user.UserSimpleInfoVo;
import com.coin.service.*;
import com.coin.service.asyn.AsyncNotificationService;
import com.coin.service.constant.CodeCons;
import com.coin.service.exception.BizException;
import com.coin.service.util.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ReplyExtendServiceImpl implements ReplyExtendService {

    private static final Logger logger = LoggerFactory.getLogger(ReplyExtendServiceImpl.class);

    @Resource
    private ReplyExtendMapper replyExtendMapper;

    @Resource
    private ReplyExtendExtMapper replyExtendExtMapper;

    @Resource
    private ThreadsService threadsService;

    @Resource
    private ThreadReplyService threadReplyService;

    @Resource
    private ReportsService reportsService;

    @Resource
    private ReportReplyService reportReplyService;

    @Resource
    private ArticlesService articlesService;

    @Resource
    private ArticleCommentsService articleCommentsService;

    @Resource
    private GuessService guessService;

    @Resource
    private GuessReplyService guessReplyService;

    @Resource
    private UsersService usersService;

    @Resource
    private UserLevelService userLevelService;

    @Resource
    private RedisUtil redisUtil;

    @Resource
    private WalletsService walletsService;

    @Resource
    private AsyncNotificationService asyncNotificationService;

    @Resource
    private DictService dictService;

    @Override
    public void reply(ReplyExtendReq req) {
        UserSimpleInfoVo loginUser = usersService.selectUserSimpleInfoById(req.getLoginUserId());
        if (ObjectUtil.isNull(loginUser)) {
            throw new BizException(CodeCons.ERROR, "用户不存在");
        }
        if (NumberUtil.equals(loginUser.getIsBanPost(), 1)) {
            throw new BizException(CodeCons.ERROR, ApiExceptionEnum.NO_PERMISSION_TO_POST_THREAD.getMessage());
        }

        // 回复类型: 1:楼层回复  2:对话回复
        Integer replyType = req.getReplyType();
        switch (replyType) {
            case 1:
                // 回复楼层时parentId默认-1
                req.setParentId(-1L);
                req.setParentUserId(null);
                break;
            case 2:
                Long parentId = req.getParentId();
                if (ObjectUtil.isNull(parentId)) {
                    throw new BizException(CodeCons.ERROR, "对话回复必须指定父级");
                }
                ReplyExtend replyExtend = this.getById(req.getParentId());
                req.setParentUserId(replyExtend.getUserId());
                req.setParentReplyContent(replyExtend.getContent());
                break;
            default:
                throw new BizException(CodeCons.ERROR, "不支持的回复类型");
        }

        // 限制最多评论200个字
        String content = req.getContent();
        if (StrUtil.length(content) > 200) {
            String text = RichTextUtil.getText(content);
            if (StrUtil.length(text) > 200) {
                throw new BizException(CodeCons.ERROR, "评论内容超出限制");
            }
        }

        // 类型: 1:帖子 2:老哥帮手 3:行业资讯 4:竞猜
        Integer towerType = req.getTowerType();
        switch (towerType) {
            case 1:
                replyThread(req);
                break;
            case 2:
                replyReport(req);
                break;
            case 3:
                replyArticle(req);
                break;
            case 4:
                replyGuess(req);
                break;
            default:
                throw new BizException(CodeCons.ERROR, "不支持此类型");
        }
    }

    @Override
    public PageInfo<ReplyVo> replyPageList(ReplyExtendReq req) {
        // 类型: 1:帖子 2:老哥帮手 3:行业资讯 4:竞猜
        Integer towerType = req.getTowerType();
        PageInfo<ReplyVo> result;
        List<ReplyVo> datas;
        PageHelper.startPage(req.getPage(), req.getPagesize());
        switch (towerType) {
            case 1:
                datas = replyExtendExtMapper.selectThreadReplyList(req);
                result = buildPageListWithDetail(req, datas);
                break;
            case 2:
                datas = replyExtendExtMapper.selectReportReplyList(req);
                result = buildPageList(req, datas);
                break;
            case 3:
                datas = replyExtendExtMapper.selectAritcleReplyList(req);
                result = buildPageList(req, datas);
                break;
            case 4:
                datas = replyExtendExtMapper.selectGuessReplyList(req);
                result = buildPageList(req, datas);
                break;
            default:
                throw new BizException(CodeCons.ERROR, "不支持此类型");
        }
        return result;
    }

    private PageInfo<ReplyVo> buildPageListWithDetail(ReplyExtendReq req, List<ReplyVo> datas) {
        try {
            if (CollectionUtil.isNotEmpty(datas)) {
                // 封装一级回复对应用户等级
                Set<Long> userIdList = new HashSet<>();
                Set<String> userIdListStr = new HashSet<>();
                for (ReplyVo data : datas) {
                    Integer exp = data.getExp();
                    UserLevelVo userLevelVo = userLevelService.matchLevelByExp(exp);
                    data.setUserLevel(userLevelVo);
                    userIdList.add(data.getUserId());
                    userIdListStr.add(data.getUserId() + "");
                }

                // 批量查询博币和发帖数
                HashOperations<String, String, UserExtInfoCache> redisTemplateForUserExtInfoCache = redisUtil.getRedisTemplateForUserExtInfoCache();
                List<UserExtInfoCache> userExtInfoCaches = redisTemplateForUserExtInfoCache.multiGet(CacheKeyEnum.USER_EXT_INFO.getKeyName(), userIdListStr);
                // 移除null元素
                CollUtil.removeNull(userExtInfoCaches);

                // 缓存未命中的数据
                if (CollectionUtil.isNotEmpty(userExtInfoCaches)) {
                    // 移除过期值
                    long current = DateUtil.currentSeconds();
                    List<UserExtInfoCache> expireList = new ArrayList<>();
                    List<String> expireIdList = new ArrayList<>();
                    for (UserExtInfoCache item : userExtInfoCaches) {
                        Long cacheTime = item.getCreateAt();
                        // 默认缓存10分钟
                        if (current - cacheTime > 600) {
                            expireList.add(item);
                            expireIdList.add(item.getUserId() + "");
                        }
                    }
                    if (CollectionUtil.isNotEmpty(expireList)) {
                        for (String id : expireIdList) {
                            redisTemplateForUserExtInfoCache.delete(CacheKeyEnum.USER_EXT_INFO.getKeyName(), id);
                        }
                        userExtInfoCaches.removeAll(expireList);
                    }

                    List<Long> collectIdList = userExtInfoCaches.stream().map(UserExtInfoCache::getUserId).collect(Collectors.toList());
                    collectIdList.forEach(userIdList::remove);
                }
                if (CollectionUtil.isNotEmpty(userIdList)) {
                    // 开始查询
                    List<UserBobiVo> userBobiVos = walletsService.selectByUserIdList(userIdList);
                    Map<Long, UserBobiVo> userBobiVoMap = userBobiVos.stream().collect(Collectors.toMap(UserBobiVo::getUserId, Function.identity()));
                    List<UserThreadCountVo> userThreadCountVos = threadsService.countByUserIdList(userIdList);
                    Map<Long, UserThreadCountVo> userThreadCountVoMap = userThreadCountVos.stream().collect(Collectors.toMap(UserThreadCountVo::getUserId, Function.identity()));

                    // 查询结果转换为list
                    List<UserExtInfoCache> userExtInfoCacheList = new ArrayList<>();
                    for (Long item : userIdList) {
                        UserExtInfoCache cache = new UserExtInfoCache();
                        cache.setCreateAt(DateUtil.currentSeconds());
                        cache.setUserId(item);
                        UserBobiVo userBobiVo = userBobiVoMap.get(item);
                        if (ObjectUtil.isNotNull(userBobiVo)) {
                            BigDecimal bobi = userBobiVo.getBobi();
                            if (ObjectUtil.isNotNull(bobi)) {
                                cache.setUserBobi(bobi.intValue());
                            } else {
                                cache.setUserBobi(0);
                            }
                        }
                        UserThreadCountVo userThreadCountVo = userThreadCountVoMap.get(item);
                        if (ObjectUtil.isNotNull(userThreadCountVo)) {
                            cache.setThreadCount(userThreadCountVo.getThreadCount());
                        }
                        userExtInfoCacheList.add(cache);
                    }

                    // 新增缓存
                    if (CollectionUtil.isNotEmpty(userExtInfoCacheList)) {
                        Map<String, UserExtInfoCache> map = new HashMap<>();
                        for (UserExtInfoCache cache : userExtInfoCacheList) {
                            map.put(cache.getUserId() + "", cache);
                        }
                        redisTemplateForUserExtInfoCache.putAll(CacheKeyEnum.USER_EXT_INFO.getKeyName(), map);
                        userExtInfoCaches.addAll(userExtInfoCacheList);
                    }
                }

                // 封装博币和发帖数
                Map<Long, UserExtInfoCache> extendIdMap = userExtInfoCaches.stream().collect(Collectors.toMap(UserExtInfoCache::getUserId, Function.identity()));
                for (ReplyVo data : datas) {
                    Long userId = data.getUserId();
                    UserExtInfoCache cache = extendIdMap.get(userId);
                    if (ObjectUtil.isNotNull(cache)) {
                        Long threadCount = cache.getThreadCount();
                        if (ObjectUtil.isNotNull(threadCount) && threadCount >= 0) {
                            data.setThreadCount(threadCount);
                        }
                        Integer userBobi = cache.getUserBobi();
                        if (ObjectUtil.isNotNull(userBobi) && userBobi >= 0) {
                            data.setBobi(userBobi);
                        }
                    }

                    if (ObjectUtil.isNull(data.getThreadCount())) {
                        data.setThreadCount(0L);
                    }
                    if (ObjectUtil.isNull(data.getBobi())) {
                        data.setBobi(0);
                    }
                }
            }
        } catch (Exception e) {
            logger.error("获取用户详情异常", e);
        }
        return this.buildPageList(req, datas);
    }

    private PageInfo<ReplyVo> buildPageList(ReplyExtendReq req, List<ReplyVo> datas) {
        // 登录用户信息
        UserSimpleInfoVo loginUser;
        if (ObjectUtil.isNotNull(req.getLoginUserId())) {
            loginUser = usersService.selectUserSimpleInfoById(req.getLoginUserId());
        } else {
            loginUser = null;
        }

        // 查询towerId所有扩展回复
        if (CollectionUtil.isNotEmpty(datas)) {
            // 获取指定towerId和floorId集合的扩展回复
            List<Long> floorIdList = datas.stream().map(ReplyVo::getId).collect(Collectors.toList());
            req.setFloorIdList(floorIdList);
            List<ReplyExtendVo> replyExtendVoList = replyExtendExtMapper.selectReplyExtendList(req);

            // 按id映射
            Map<Long, ReplyExtendVo> extendIdMap = replyExtendVoList.stream().collect(Collectors.toMap(ReplyExtendVo::getId, Function.identity()));
            // 根据floorId分组
            Map<Long, List<ReplyExtendVo>> replyExtendMap = replyExtendVoList.stream()
                    .collect(Collectors.groupingBy(ReplyExtendVo::getFloorId, Collectors.toList()));
            String defaultUserAvatar = dictService.getDefaultUserAvatar();
            for (ReplyVo data : datas) {
                // 处理头像
                if (StrUtil.isBlank(data.getUserAvatar())) {
                    data.setUserAvatar(ImageUtil.completeImageUrl(defaultUserAvatar));
                } else {
                    data.setUserAvatar(ImageUtil.completeImageUrl(data.getUserAvatar()));
                }
                // 处理帖子内容
                if (NumberUtil.equals(req.getTowerType(), 1)) {
                    String content = data.getContent();
                    String pics = data.getPics();
                    String newContent = RichTextUtil.processHistoryContent(content, pics);
                    data.setContent(newContent);
                }

                List<ReplyExtendVo> extendList = replyExtendMap.get(data.getId());
                if (CollectionUtil.isNotEmpty(extendList)) {
                    extendList.forEach(item -> {
                        Long parentId = item.getParentId();
                        if (ObjectUtil.isNotNull(parentId) && NumberUtil.equals(parentId, -1L)) {
                            item.setParentUserName("");
                        } else {
                            ReplyExtendVo parentVo = extendIdMap.get(parentId);
                            if (ObjectUtil.isNotNull(parentVo)) {
                                item.setParentUserName(parentVo.getUserName());
                            }
                        }
                        if (NumberUtil.equals(req.getTowerType(), 1)) {
                            String userAvatar = item.getUserAvatar();
                            if (StrUtil.isBlank(userAvatar)) {
                                item.setUserAvatar(ImageUtil.completeImageUrl(defaultUserAvatar));
                            } else {
                                item.setUserAvatar(ImageUtil.completeImageUrl(userAvatar));
                            }
                        }
                        // 判断非一级回复allowEdit,allowDelete
                        boolean allowEdit = BizUtil.checkAllowEdit(loginUser, item.getUserId());
                        boolean allowDelete = BizUtil.checkAllowDelete(loginUser, item.getUserId());
                        item.setAllowEdit(allowEdit);
                        item.setAllowDelete(allowDelete);
                    });
                    data.setReplyExtendList(extendList);
                }

                // 判断一级回复allowEdit,allowDelete
                boolean allowEdit = BizUtil.checkAllowEdit(loginUser, data.getUserId());
                boolean allowDelete = BizUtil.checkAllowDelete(loginUser, data.getUserId());
                data.setAllowEdit(allowEdit);
                data.setAllowDelete(allowDelete);
            }
        }
        return new PageInfo<>(datas);
    }

    private void replyThread(ReplyExtendReq req) {
        Long floorId = req.getFloorId();
        Long loginUserId = req.getLoginUserId();
        int towerOwnerFlag = 0;
        int floorOwnerFlag = 0;

        ThreadReply threadReply = threadReplyService.getById(floorId);
        Threads threads = threadsService.getById(threadReply.getThreadId());
        if (NumberUtil.equals(loginUserId, threads.getUserId())) {
            towerOwnerFlag = 1;
        }
        if (NumberUtil.equals(loginUserId, threadReply.getUserId())) {
            floorOwnerFlag = 1;
        }

        req.setTowerId(threads.getId());
        req.setUserId(loginUserId);
        req.setTowerUserId(threads.getUserId());
        req.setFloorUserId(threadReply.getUserId());
        req.setTowerOwnerFlag(towerOwnerFlag);
        req.setFloorOwnerFlag(floorOwnerFlag);

        // 提醒
        req.setFromContent(threads.getSubject());
        req.setFromContentReply(threadReply.getContent());
        this.add(req);
    }

    private void replyReport(ReplyExtendReq req) {
        Long floorId = req.getFloorId();
        Long loginUserId = req.getLoginUserId();
        int towerOwnerFlag = 0;
        int floorOwnerFlag = 0;

        ReportReplyResp reportReply = reportReplyService.getById(floorId);
        Reports reports = reportsService.getById(reportReply.getReportId());
        if (NumberUtil.equals(loginUserId, reports.getUserId())) {
            towerOwnerFlag = 1;
        }
        if (NumberUtil.equals(loginUserId, reports.getUserId())) {
            floorOwnerFlag = 1;
        }

        req.setTowerId(reports.getId());
        req.setUserId(loginUserId);
        req.setTowerUserId(reports.getUserId());
        req.setFloorUserId(reportReply.getUserId());
        req.setTowerOwnerFlag(towerOwnerFlag);
        req.setFloorOwnerFlag(floorOwnerFlag);

        // 提醒
        req.setFromContent(reports.getDesc());
        req.setFromContentReply(reportReply.getContent());
        this.add(req);
    }

    private void replyArticle(ReplyExtendReq req) {
        Long floorId = req.getFloorId();
        Long loginUserId = req.getLoginUserId();
        int towerOwnerFlag = 0;
        int floorOwnerFlag = 0;

        ArticleComments articleComments = articleCommentsService.getById(floorId);
        Articles articles = articlesService.getById(articleComments.getArticleId());
        if (NumberUtil.equals(loginUserId, articles.getUserId())) {
            towerOwnerFlag = 1;
        }
        if (NumberUtil.equals(loginUserId, articles.getUserId())) {
            floorOwnerFlag = 1;
        }

        req.setTowerId(articles.getId());
        req.setUserId(loginUserId);
        req.setTowerUserId(articles.getUserId());
        req.setFloorUserId(articleComments.getUserId());
        req.setTowerOwnerFlag(towerOwnerFlag);
        req.setFloorOwnerFlag(floorOwnerFlag);
        // 设置articleGroup类型 1:狗庄揭秘 2:行业资讯
        req.setArtGroupId(articles.getArtGroupId());

        // 提醒
        req.setFromContent(articles.getTitle());
        req.setFromContentReply(articleComments.getContent());
        this.add(req);
    }

    private void replyGuess(ReplyExtendReq req) {
        Long floorId = req.getFloorId();
        Long loginUserId = req.getLoginUserId();
        int towerOwnerFlag = 0;
        int floorOwnerFlag = 0;

        GuessReply guessReply = guessReplyService.getById(floorId);
        Guess guess = guessService.getById(guessReply.getGuessId());
        if (NumberUtil.equals(loginUserId, guess.getAdminUserId())) {
            towerOwnerFlag = 1;
        }
        if (NumberUtil.equals(loginUserId, guess.getAdminUserId())) {
            floorOwnerFlag = 1;
        }

        req.setTowerId(guess.getId());
        req.setUserId(loginUserId);
        req.setTowerUserId(guess.getAdminUserId());
        req.setFloorUserId(guessReply.getUserId());
        req.setTowerOwnerFlag(towerOwnerFlag);
        req.setFloorOwnerFlag(floorOwnerFlag);

        // 提醒
        req.setFromContent(guess.getGuessSubject());
        req.setFromContentReply(guessReply.getContent());
        this.add(req);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void add(ReplyExtendReq req) {
        Date now = new Date();
        ReplyExtend replyExtend = new ReplyExtend();
        replyExtend.setTowerType(req.getTowerType());
        replyExtend.setTowerId(req.getTowerId());
        replyExtend.setTowerUserId(req.getTowerUserId());
        replyExtend.setFloorId(req.getFloorId());
        replyExtend.setFloorUserId(req.getFloorUserId());
        replyExtend.setParentId(req.getParentId());
        replyExtend.setParentUserId(req.getParentUserId());
        replyExtend.setUserId(req.getUserId());
        replyExtend.setTowerOwnerFlag(req.getTowerOwnerFlag());
        replyExtend.setFloorOwnerFlag(req.getFloorOwnerFlag());
        replyExtend.setContent(req.getContent());
        replyExtend.setCreatedAt(now);
        replyExtend.setUpdatedAt(null);
        replyExtend.setDeletedAt(null);
        replyExtendMapper.insertSelective(replyExtend);
        buildNotification(req);
    }

    private boolean buildNotification(ReplyExtendReq req) {
        // 默认通知楼层用户, 如果是回复的回复,则通知回复对应人
        Long notifiableId = req.getFloorUserId();
        Long parentUserId = req.getParentUserId();
        if (ObjectUtil.isNotNull(parentUserId)) {
            notifiableId = parentUserId;
        }
        Long userId = req.getUserId();
        if (NumberUtil.equals(notifiableId, userId)) {
            return false;
        }
        if (StrUtil.isBlank(req.getParentReplyContent())) {
            req.setParentReplyContent(req.getFromContentReply());
        }

        Map<String, Object> dataData = new HashMap<>();
        UserSimpleInfoVo usersResp = usersService.selectUserSimpleInfoAndLevelById(userId);
        UserLevelVo userLevelVo = userLevelService.matchLevelByExp(usersResp.getExp());
        JSONObject replyInfo = new JSONObject();
        replyInfo.set("id", req.getTowerId());
        replyInfo.set("towerType", req.getTowerType());
        replyInfo.set("replyContent", req.getContent());
        // 单独处理狗庄揭秘和行业资讯
        if (ObjectUtil.isNotNull(req.getArtGroupId())) {
            replyInfo.set("artGroupId", req.getArtGroupId());
        }

        String avatar = usersResp.getAvatar();
        if (StrUtil.isBlank(avatar)) {
            avatar = dictService.getDefaultUserAvatar();
        }

        JSONObject jumpObj = new JSONObject();
        jumpObj.set("id", usersResp.getId());
        jumpObj.set("avatar", ImageUtil.completeImageUrl(avatar));
        jumpObj.set("badge", ImageUtil.completeImageUrl(userLevelVo.getUserLevel().getBadge()));
        jumpObj.set("fromContent", req.getFromContent());

        LongTextTranslate longTextTranslateBean = I18nUtil.getLongTextTranslateBean();
        String message = longTextTranslateBean.buildNotificationMessage(NotificationType.REPLY_EXT, usersResp.getName(), req.getParentReplyContent());
        // String message = "<span style='color:#0390F6'>" + usersResp.getName() + "</span> 回复了您的评论<span style='color:#0390F6'>" + req.getParentReplyContent() + "</span>";
        dataData.put("jumpObj", jumpObj);
        dataData.put("replyInfo", replyInfo);

        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("message", message);
        dataMap.put("data", dataData);

        // 消息通知
        Notifications notifications = new Notifications();
        notifications.setId(UUID.randomUUID().toString());
        notifications.setType("replyExtend");
        notifications.setNotifiableType("App\\Models\\User");
        notifications.setNotifiableId(notifiableId);
        notifications.setData(JSONUtil.toJsonStr(dataMap));
        notifications.setCreatedAt(new Date());
        asyncNotificationService.sendExtendReplyNotification(notifications);
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(ReplyExtendReq req) {
        ReplyExtend oldContest = replyExtendMapper.selectByPrimaryKey(req.getId());
        if (ObjectUtil.isNull(oldContest)) {
            throw new BizException(CodeCons.ERROR, "记录不存在");
        }
        Date now = new Date();
        ReplyExtend updateReplyExtend = new ReplyExtend();
        updateReplyExtend.setId(req.getId());
        updateReplyExtend.setTowerType(req.getTowerType());
        updateReplyExtend.setTowerId(req.getTowerId());
        updateReplyExtend.setTowerUserId(req.getTowerUserId());
        updateReplyExtend.setFloorId(req.getFloorId());
        updateReplyExtend.setFloorUserId(req.getFloorUserId());
        updateReplyExtend.setParentId(req.getParentId());
        updateReplyExtend.setParentUserId(req.getParentUserId());
        updateReplyExtend.setUserId(req.getUserId());
        updateReplyExtend.setTowerOwnerFlag(req.getTowerOwnerFlag());
        updateReplyExtend.setFloorOwnerFlag(req.getFloorOwnerFlag());
        updateReplyExtend.setContent(req.getContent());
        updateReplyExtend.setUpdatedAt(now);
        updateReplyExtend.setDeletedAt(null);
        replyExtendMapper.updateByPrimaryKeySelective(updateReplyExtend);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(ReplyExtendReq req) {
        Long id = req.getId();
        replyExtendMapper.deleteByPrimaryKey(id);
    }

    @Override
    public ReplyExtend getById(Long id) {
        ReplyExtend replyExtend = replyExtendMapper.selectByPrimaryKey(id);
        if (ObjectUtil.isNull(replyExtend)) {
            throw new BizException(CodeCons.ERROR, "回复记录不存在");
        }
        return replyExtend;
    }

    @Override
    public PageInfo<ReplyExtendMgrVo> pageList(ReplyExtendReq req) {
        PageHelper.startPage(req.getPage(), req.getPagesize());
        ReplyExtendExample example = new ReplyExtendExample();
        ReplyExtendExample.Criteria criteria = example.createCriteria();
        criteria.andTowerTypeEqualTo(req.getTowerType());
        criteria.andTowerIdEqualTo(req.getTowerId());
        criteria.andFloorIdEqualTo(req.getFloorId());
        criteria.andDeletedAtIsNull();
        example.setOrderByClause(" id asc");
        List<ReplyExtend> datas = replyExtendMapper.selectByExample(example);
        List<ReplyExtendMgrVo> mgrVoList = new ArrayList<>();
        if (CollectionUtil.isNotEmpty(datas)) {
            Set<Long> userIdList = new HashSet<>();
            for (ReplyExtend item : datas) {
                userIdList.add(item.getUserId());
                userIdList.add(item.getFloorUserId());
                userIdList.add(item.getParentUserId());
                userIdList.add(item.getTowerUserId());
            }
            mgrVoList = BeanUtil.copyToList(datas, ReplyExtendMgrVo.class);
            List<UserSimpleInfoVo> userSimpleInfoVoList = usersService.selectUserSimpleInfoByIdList(userIdList);
            Map<Long, UserSimpleInfoVo> nameMap = userSimpleInfoVoList.stream().collect(Collectors.toMap(UserSimpleInfoVo::getId, Function.identity()));
            mgrVoList.forEach(item -> {
                item.setUserName(findName(nameMap, item.getUserId()));
                item.setFloorUserName(findName(nameMap, item.getFloorUserId()));
                item.setParentUserName(findName(nameMap, item.getParentUserId()));
                item.setTowerUserName(findName(nameMap, item.getTowerUserId()));
            });
        }
        return PageUtil.pageInfo2PageRsp(new PageInfo<>(datas), mgrVoList);
    }

    private String findName(Map<Long, UserSimpleInfoVo> nameMap, Long userId) {
        UserSimpleInfoVo userSimpleInfoVo = nameMap.get(userId);
        if (ObjectUtil.isNotNull(userSimpleInfoVo)) {
            return userSimpleInfoVo.getName();
        }
        return "";
    }

    @Override
    public void modifyReplyById(ReplyExtendReq req) {
        UserSimpleInfoVo loginUser = usersService.selectUserSimpleInfoById(req.getLoginUserId());
        if (ObjectUtil.isNull(loginUser)) {
            throw new BizException(CodeCons.ERROR, "用户不存在");
        }
        if (NumberUtil.equals(loginUser.getIsBanPost(), 1)) {
            throw new BizException(CodeCons.ERROR, ApiExceptionEnum.NO_PERMISSION_TO_POST_THREAD.getMessage());
        }
        ReplyExtend replyExtend = replyExtendMapper.selectByPrimaryKey(req.getId());
        boolean allowEdit = BizUtil.checkAllowEdit(loginUser, replyExtend.getUserId());
        if (!allowEdit) {
            throw new BizException(CodeCons.ERROR, ApiExceptionEnum.NO_PERMISSION_TO_EDIT.getMessage());
        }
        Date now = new Date();
        ReplyExtend update = new ReplyExtend();
        update.setId(replyExtend.getId());
        update.setContent(req.getContent());
        update.setUpdatedAt(now);
        replyExtendMapper.updateByPrimaryKeySelective(update);
    }

    @Override
    public void deleteReplyById(ReplyExtendReq req) {
        UserSimpleInfoVo loginUser = usersService.selectUserSimpleInfoById(req.getLoginUserId());
        if (ObjectUtil.isNull(loginUser)) {
            throw new BizException(CodeCons.ERROR, "用户不存在");
        }
        if (NumberUtil.equals(loginUser.getIsBanPost(), 1)) {
            throw new BizException(CodeCons.ERROR, ApiExceptionEnum.NO_PERMISSION_TO_POST_THREAD.getMessage());
        }
        ReplyExtend replyExtend = replyExtendMapper.selectByPrimaryKey(req.getId());
        boolean allowDelete = BizUtil.checkAllowDelete(loginUser, replyExtend.getUserId());
        if (!allowDelete) {
            throw new BizException(CodeCons.ERROR, ApiExceptionEnum.NO_PERMISSION_TO_EDIT.getMessage());
        }
        Date now = new Date();
        ReplyExtend update = new ReplyExtend();
        update.setId(replyExtend.getId());
        update.setDeletedAt(now);
        replyExtendMapper.updateByPrimaryKeySelective(update);
    }
}
