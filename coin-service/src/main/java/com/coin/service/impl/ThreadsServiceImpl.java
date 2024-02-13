package com.coin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.coin.cache.UserThreadCountVo;
import com.coin.entity.*;
import com.coin.enums.ApiExceptionEnum;
import com.coin.enums.LikeAndFavTypeEnum;
import com.coin.enums.NotificationType;
import com.coin.i18n.I18nUtil;
import com.coin.i18n.LongTextTranslate;
import com.coin.mapper.*;
import com.coin.mapper.ext.ThreadsExtMapper;
import com.coin.mapper.ext.UsersExtMapper;
import com.coin.req.FavoritesReq;
import com.coin.req.NotificationsReq;
import com.coin.req.ThreadsReq;
import com.coin.req.UsersReq;
import com.coin.req.search.SearchReq;
import com.coin.req.thread.ThreadsVoReq;
import com.coin.resp.dict.ExtraMsgVo;
import com.coin.resp.fav.FavVo;
import com.coin.resp.like.LikeVo;
import com.coin.resp.search.ForumsVo;
import com.coin.resp.search.SearchForumVo;
import com.coin.resp.search.UserShortVo;
import com.coin.resp.thread.ThreadTopicsVo;
import com.coin.resp.thread.ThreadsDetailVo;
import com.coin.resp.thread.ThreadsReplyCountVo;
import com.coin.resp.thread.ThreadsVo;
import com.coin.resp.user.UserLevelVo;
import com.coin.resp.user.UserSimpleInfoVo;
import com.coin.resp.user.UserVo;
import com.coin.service.*;
import com.coin.service.asyn.AsyncHandleService;
import com.coin.service.asyn.BobiAndExpService;
import com.coin.service.constant.CodeCons;
import com.coin.service.exception.BizException;
import com.coin.service.util.BizUtil;
import com.coin.service.util.ContentUtil;
import com.coin.service.util.ImageUtil;
import com.coin.service.util.PageUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ThreadsServiceImpl implements ThreadsService {

    private static final Logger logger = LoggerFactory.getLogger(ThreadsServiceImpl.class);

    @Resource
    private ThreadsMapper threadsMapper;
    @Resource
    private ThreadsExtMapper threadsExtMapper;
    @Resource
    private UsersMapper usersMapper;
    @Resource
    private ForumsMapper forumsMapper;
    @Resource
    private ThreadReplyService threadReplyService;
    @Resource
    private UsersExtMapper usersExtMapper;
    @Resource
    private ForumsService forumsService;
    @Resource
    private UsersService usersService;
    @Resource
    private UserLevelService userLevelService;
    @Resource
    private WalletsService walletsService;
    @Resource
    private FavoritesService favoritesService;
    @Resource
    private ThreadTopicsService threadTopicsService;
    @Resource
    private ForumSubNavsService forumSubNavsService;
    @Resource
    private LikesService likesService;
    @Resource
    private DictService dictService;
    @Resource
    private NotificationsService notificationsService;
    @Resource
    private FollowablesService followablesService;
    @Resource
    private LikesMapper likesMapper;
    @Resource
    private FavoritesMapper favoritesMapper;
    @Resource
    private BobiAndExpService bobiAndExpService;
    @Resource
    private ThreadTagLogService threadTagLogService;
    @Resource
    private AsyncHandleService asyncHandleService;

    private static Threads getUpdate(ThreadsReq req, boolean allowEdit, Threads threads) {
        if (!allowEdit) {
            throw new BizException(CodeCons.ERROR, ApiExceptionEnum.NO_PERMISSION_TO_EDIT.getMessage());
        }
        Integer notAllowedModify = threads.getNotAllowedModify();
        if (NumberUtil.equals(notAllowedModify, 1)) {
            throw new BizException(CodeCons.ERROR, "不允许编辑");
        }
        Date now = new Date();
        Threads update = new Threads();
        update.setId(threads.getId());
        update.setSubject(req.getSubject());
        update.setContent(req.getContent());
        update.setUpdatedAt(now);
        update.setLastModifyAt(now);
        return update;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(ThreadsReq req) {
        Threads threadDb = threadsMapper.selectByPrimaryKey(req.getId());
        if (ObjectUtil.isNull(threadDb)) {
            throw new BizException(CodeCons.ERROR, "记录不存在");
        }
        Date now = new Date();
        Threads updateThreads = new Threads();
        updateThreads.setId(req.getId());
        updateThreads.setForumId(req.getForumId());
        updateThreads.setTopicId(req.getTopicId());
        updateThreads.setSubject(req.getSubject());
        updateThreads.setType(req.getType());
        updateThreads.setIsTop(req.getIsTop());
        updateThreads.setContent(req.getContent());
        updateThreads.setUpdatedAt(now);
        updateThreads.setTitleIsBold(req.getTitleIsBold());
        updateThreads.setTitleColor(req.getTitleColor());
        updateThreads.setTitleIsItalicized(req.getTitleIsItalicized());

        // 处理tags
        List<String> tagList = new ArrayList<>();
        boolean hotFlag = false;
        boolean bestFlag = false;
        if (NumberUtil.equals(req.getTagsHot(), 1)) {
            tagList.add("hot");
            hotFlag = true;
        }
        if (NumberUtil.equals(req.getTagsNewer(), 1)) {
            tagList.add("newer");
        }
        if (NumberUtil.equals(req.getTagsBest(), 1)) {
            tagList.add("best");
            bestFlag = true;
        }
        if (NumberUtil.equals(req.getTagsBreak(), 1)) {
            tagList.add("break");
        }
        updateThreads.setTags(JSONUtil.toJsonStr(tagList));

        ContentUtil.processThreadsContent(updateThreads);
        threadsMapper.updateByPrimaryKeySelective(updateThreads);

        // 加热门
        if (hotFlag) {
            this.addHotTag(threadDb);
        }
        // 加精华
        if (bestFlag) {
            this.addBestTag(threadDb);
        }
    }

    @Override
    public void addReadCount(ThreadsReq req) {
        if (req.getReadCount() == 0) {
            return;
        }
        Threads oldThread = threadsMapper.selectByPrimaryKey(req.getId());
        if (oldThread.getReadCount() + req.getReadCount() < 0) {
            throw new BizException(CodeCons.ERROR, "更新后值为负");
        }
        Date now = new Date();
        Threads thread = new Threads();
        thread.setId(req.getId());
        thread.setReadCount(oldThread.getReadCount() + req.getReadCount());
        thread.setUpdatedAt(now);
        threadsMapper.updateByPrimaryKeySelective(thread);
    }

    @Override
    public Integer addReplyCount(Long id) {
        // 更新帖子评论数
        Integer replyCount = threadReplyService.countByThreadId(id);
        int replyCountNow;
        if (ObjectUtil.isNotNull(replyCount)) {
            replyCountNow = replyCount + 1;
        } else {
            replyCountNow = 1;
        }
        Threads updateThread = new Threads();
        updateThread.setId(id);
        updateThread.setReplyCount(replyCountNow);
        threadsMapper.updateByPrimaryKeySelective(updateThread);
        return replyCountNow;
    }

    @Override
    public void addBestTag(Threads threads) {
        String tag = "best";
        List<String> tagList = processThreadTags(threads.getTags());
        if (CollectionUtil.isNotEmpty(tagList)) {
            if (!tagList.contains(tag)) {
                tagList.add(tag);
                // 如果是首次加精华, 则加博币
                ThreadTagLog threadTagLog = threadTagLogService.findByTag(threads.getId(), tag);
                if (ObjectUtil.isNull(threadTagLog)) {
                    bobiAndExpService.threadBest(threads.getUserId());
                }
                updateTags(threads.getId(), tagList);
            }
        } else {
            tagList = new ArrayList<>();
            tagList.add(tag);
            updateTags(threads.getId(), tagList);
        }
    }

    @Override
    public void addHotTag(Threads threads) {
        String tag = "hot";
        List<String> tagList = processThreadTags(threads.getTags());
        if (CollectionUtil.isNotEmpty(tagList)) {
            if (!tagList.contains(tag)) {
                tagList.add(tag);
                // 如果是首次加热门, 则加博币
                ThreadTagLog threadTagLog = threadTagLogService.findByTag(threads.getId(), tag);
                if (ObjectUtil.isNull(threadTagLog)) {
                    bobiAndExpService.threadHot(threads.getUserId());
                }
                updateTags(threads.getId(), tagList);
            }
        } else {
            tagList = new ArrayList<>();
            tagList.add(tag);
            updateTags(threads.getId(), tagList);
        }
    }

    private void updateTags(Long threadId, List<String> tagList) {
        Threads updateThread = new Threads();
        updateThread.setId(threadId);
        updateThread.setTags(JSONUtil.toJsonStr(tagList));
        threadsMapper.updateByPrimaryKeySelective(updateThread);
    }

    @Override
    public void addLikeCount(ThreadsReq req) {
        if (req.getIncrLikes() == 0) {
            return;
        }
        Threads oldThread = threadsMapper.selectByPrimaryKey(req.getId());
        if (oldThread.getIncrLikes() + req.getIncrLikes() < 0) {
            throw new BizException(CodeCons.ERROR, "更新后值为负");
        }
        Date now = new Date();
        Threads thread = new Threads();
        thread.setId(req.getId());
        thread.setIncrLikes(oldThread.getIncrLikes() + req.getIncrLikes());
        thread.setUpdatedAt(now);
        threadsMapper.updateByPrimaryKeySelective(thread);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(ThreadsReq req) {
        Date now = new Date();
        Threads updateThreads = new Threads();
        updateThreads.setId(req.getId());
        updateThreads.setDeletedAt(now);
        threadsMapper.updateByPrimaryKeySelective(updateThreads);
    }

    @Override
    public Threads getById(Long id) {
        Threads threads = threadsMapper.selectByPrimaryKey(id);
        if (ObjectUtil.isNull(threads)) {
            throw new BizException(CodeCons.ERROR, "记录不存在");
        }
        return threads;
    }

    @Override
    public PageInfo<ThreadsVo> pageList(ThreadsReq req) {
        if (req.getUserName() != null) {
            Users users = usersExtMapper.selectByName(req.getUserName());
            if (ObjectUtil.isNull(users)) {
                throw new BizException(CodeCons.ERROR, "记录不存在");
            }
            req.setUserId(users.getId());
        }

        ThreadsExample example = new ThreadsExample();
        ThreadsExample.Criteria criteria = example.createCriteria();
        criteria.andDeletedAtIsNull();
        if (req.getId() != null) {
            criteria.andIdEqualTo(req.getId());
        }
        if (req.getUserId() != null) {
            criteria.andUserIdEqualTo(req.getUserId());
        }
        if (StrUtil.isNotBlank(req.getSubject())) {
            criteria.andSubjectLike("%" + req.getSubject().trim() + "%");
        }
        if (req.getCreatedAtMin() != null) {
            criteria.andCreatedAtGreaterThanOrEqualTo(req.getCreatedAtMin());
        }
        if (req.getCreatedAtMax() != null) {
            criteria.andCreatedAtLessThanOrEqualTo(req.getCreatedAtMax());
        }
        if (req.getForumId() != null) {
            criteria.andForumIdEqualTo(req.getForumId());
        }
        if (req.getTopicId() != null) {
            criteria.andTopicIdEqualTo(req.getTopicId());
        }
        if (req.getIsTop() != null) {
            criteria.andIsTopEqualTo(req.getIsTop());
        }
        example.setOrderByClause(" id desc");
        PageHelper.startPage(req.getPage(), req.getPagesize());
        List<Threads> datas = threadsMapper.selectByExample(example);
        PageInfo<Threads> page = new PageInfo<>(datas);
        List<ThreadsVo> rspList = getThreadsVos(datas);
        return PageUtil.pageInfo2PageRsp(page, rspList);
    }

    @Override
    public List<UserThreadCountVo> countByUserIdList(Set<Long> userIdList) {
        return threadsExtMapper.countByUserIdList(userIdList);
    }

    @Override
    public Long countByUserId(Long userId) {
        return threadsExtMapper.countByUserId(userId);
    }

    @Override
    public ExtraMsgVo bbsNewThread(ThreadsReq req) {
        Forums forums = forumsService.selectByAlias(req.getCategoryAlias());
        if (ObjectUtil.isNull(forums)) {
            throw new BizException(CodeCons.ERROR, "别名不存在");
        }
        if (NumberUtil.equals(forums.getStatus(), 0)) {
            throw new BizException(CodeCons.ERROR, "论坛已关闭");
        }
        Long topicId = null;
        UserSimpleInfoVo userSimpleInfoVo = usersService.selectUserSimpleInfoAndLevelById(req.getLoginUserId());
        if (ObjectUtil.isNotNull(req.getTopic())) {
            ThreadTopics topics = threadTopicsService.getById(req.getTopic());
            if (ObjectUtil.isNotNull(topics)) {
                if (NumberUtil.equals(topics.getStatus(), 0)) {
                    throw new BizException(CodeCons.ERROR, "话题已关闭");
                }
                if (NumberUtil.equals(topics.getOnlyAdmin(), 1)
                        && !(NumberUtil.equals(userSimpleInfoVo.getIsSuperAdmin(), 1))
                        || NumberUtil.equals(userSimpleInfoVo.getIsForumAdmin(), 1)) {
                    throw new BizException(CodeCons.ERROR, "此话题仅管理员可编辑");
                }
                topicId = topics.getId();
            }
        }
        if (NumberUtil.equals(userSimpleInfoVo.getIsBanPost(), 1)) {
            throw new BizException(CodeCons.ERROR, "暂无权限");
        }
        Date now = new Date();
        Threads threads = new Threads();
        threads.setForumId(forums.getId());
        threads.setTopicId(topicId);
        threads.setUserId(req.getLoginUserId());
        threads.setSubject(req.getTitle());
        threads.setType(1);
        threads.setContent(req.getContent());
        threads.setCreatedAt(now);
        ContentUtil.processThreadsContent(threads);
        threadsMapper.insertSelective(threads);

        UsersReq usersReq = new UsersReq();
        usersReq.setPage(1);
        usersReq.setPagesize(1000);
        usersReq.setId(threads.getUserId());
        PageInfo<Followables> followersByUserId = followablesService.findFollowersByUserId(usersReq);
        if (ObjectUtil.isNotNull(followersByUserId) && CollectionUtil.isNotEmpty(followersByUserId.getList())) {
            String avatar = userSimpleInfoVo.getAvatar();
            if (StrUtil.isNotBlank(avatar)) {
                userSimpleInfoVo.setAvatar(ImageUtil.completeImageUrl(avatar));
            } else {
                String defaultUserAvatar = dictService.getDefaultUserAvatar();
                userSimpleInfoVo.setAvatar(ImageUtil.completeImageUrl(defaultUserAvatar));
            }
            LongTextTranslate longTextTranslateBean = I18nUtil.getLongTextTranslateBean();
            List<Followables> datas = followersByUserId.getList();
            datas.forEach(followables -> {
                NotificationsReq notificationsReq = new NotificationsReq();
                notificationsReq.setNotifiableId(followables.getUserId());
                notificationsReq.setType("App\\Notifications\\FolloweePostedThread");

                String message = longTextTranslateBean.buildNotificationMessage(NotificationType.NEW_THREAD, userSimpleInfoVo.getName(), threads.getSubject());
                notificationsReq.setMessage(message);
                // notificationsReq.setMessage("您关注的用户 " + "<span style='color:#0390F6'>" + userSimpleInfoVo.getName() + "</span> 发表了新帖子《" + "<span style='color:#0390F6'>" + threads.getSubject() + "</span>》,快去围观吧！");
                Map<String, Object> objectMap = new HashMap<>();
                objectMap.put("poster", userSimpleInfoVo);
                objectMap.put("thread", threads);
                notificationsReq.setObject(objectMap);
                try {
                    notificationsService.buildNotification(notificationsReq);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
        }

        ExtraMsgVo extraMsgVo = bobiAndExpService.dailyThread(req.getLoginUserId());
        extraMsgVo.setId(threads.getId());
        return extraMsgVo;
    }

    @Override
    public PageInfo<ThreadsVo> threadLists(ThreadsVoReq req) {
        Forums forums = forumsService.selectByAlias(req.getCategoryAlias());
        if (ObjectUtil.isNull(forums)) {
            throw new BizException(CodeCons.ERROR, "分类不存在");
        }
        ThreadsExample example = new ThreadsExample();
        ThreadsExample.Criteria criteria = example.createCriteria();
        criteria.andDeletedAtIsNull();
        criteria.andForumIdEqualTo(forums.getId());
        if (ObjectUtil.isNotNull(req.getDays()) && req.getDays() > 0) {
            DateTime offsetDay = DateUtil.offsetDay(new Date(), req.getDays() * -1);
            criteria.andCreatedAtGreaterThanOrEqualTo(DateUtil.beginOfDay(offsetDay));
        }
        if (ObjectUtil.isNotNull(req.getTopic())) {
            criteria.andTopicIdEqualTo(req.getTopic());
        }
        if (ObjectUtil.isNotNull(req.getSubnav()) && !NumberUtil.equals(req.getSubnav(), 0)) {
            ForumSubNavs forumSubNavs = forumSubNavsService.getById(req.getSubnav());
            if (ObjectUtil.isNotNull(forumSubNavs)) {
                String topics = forumSubNavs.getTopics();
                List<Long> topicIdList = JSONUtil.toList(topics, Long.class);
                criteria.andTopicIdIn(topicIdList);
            }
        }
        if (ObjectUtil.isNotNull(req.getNotop()) && NumberUtil.equals(req.getNotop(), 1)) {
            criteria.andIsTopEqualTo(0);
        }
        if (StrUtil.isNotBlank(req.getTag())) {
            criteria.andTagsLike("%" + req.getTag() + "%");
        }
        String sort = req.getSort();
        if (StrUtil.isNotBlank(sort)) {
            switch (sort) {
                case "read_count":
                    example.setOrderByClause(" is_top desc, read_count desc, created_at desc");
                    break;
                case "reply_count":
                    example.setOrderByClause(" is_top desc, reply_count desc, created_at desc");
                    break;
                case "reply_time":
                    example.setOrderByClause(" is_top desc, last_reply_time desc, created_at desc");
                    break;
                case "post_time":
                default:
                    example.setOrderByClause(" is_top desc, created_at desc");
            }
        } else {
            example.setOrderByClause(" is_top desc, created_at desc");
        }
        PageHelper.startPage(req.getPage(), req.getPagesize());
        List<Threads> datas = threadsMapper.selectByExample(example);
        PageInfo<Threads> page = new PageInfo<>(datas);
        List<ThreadsVo> rspList = getThreadsVos(datas);
        return PageUtil.pageInfo2PageRsp(page, rspList);
    }

    @Override
    public List<ThreadsVo> findByIdList(List<Long> idList) {
        ThreadsExample example = new ThreadsExample();
        ThreadsExample.Criteria criteria = example.createCriteria();
        criteria.andDeletedAtIsNull();
        criteria.andIdIn(ListUtil.toList(idList));
        List<Threads> datas = threadsMapper.selectByExample(example);
        return getThreadsVos(datas);
    }

    @Override
    public ThreadsDetailVo threadById(ThreadsReq req) {
        Threads byId = getById(req.getId());
        if (ObjectUtil.isNotNull(byId.getDeletedAt())) {
            throw new BizException(CodeCons.ERROR, "此帖已删除");
        }
        ContentUtil.processThreadsContent(byId);
        ThreadsDetailVo vo = BeanUtil.copyProperties(byId, ThreadsDetailVo.class);
        UsersReq usersReq = new UsersReq();
        usersReq.setId(byId.getUserId());
        usersReq.setLoginUserId(req.getLoginUserId());
        UserVo userVo = usersService.selectUserVo(usersReq);
        vo.setUser(userVo);

        if (ObjectUtil.isNotEmpty(req.getLoginUserId())) {
            //favStatus  当前收藏状态
            Favorites favorites = favoritesService.findByType(req.getLoginUserId(), byId.getId(), LikeAndFavTypeEnum.THREAD);
            vo.setFavStatus(favorites != null);

            //点赞状态。likeStatus
            Likes byType = likesService.findByType(req.getLoginUserId(), byId.getId(), LikeAndFavTypeEnum.THREAD);
            vo.setLikeStatus(byType != null);
        }
        //点赞数
        LikesExample likesExample = new LikesExample();
        LikesExample.Criteria criteria = likesExample.createCriteria();
        criteria.andLikeableTypeEqualTo("App\\Models\\Thread");
        criteria.andLikeableIdEqualTo(byId.getId());
        long likeCount = likesMapper.countByExample(likesExample);
        vo.setLikeCount(likeCount + byId.getIncrLikes());

        //收藏数
        FavoritesExample favoritesExample = new FavoritesExample();
        FavoritesExample.Criteria favoritesExampleCriteria = favoritesExample.createCriteria();
        favoritesExampleCriteria.andFavoriteableTypeEqualTo("App\\Models\\Thread");
        favoritesExampleCriteria.andFavoriteableIdEqualTo(byId.getId());
        long favoriteCount = favoritesMapper.countByExample(favoritesExample);
        vo.setFavoritorCount(favoriteCount);

        // 回复数
        Integer replyCount = threadReplyService.countByThreadId(byId.getId());
        vo.setReplyCount(replyCount);

        // 编辑删除标记
        UserSimpleInfoVo loginUser;
        if (ObjectUtil.isNotNull(req.getLoginUserId())) {
            loginUser = usersService.selectUserSimpleInfoById(req.getLoginUserId());
        } else {
            loginUser = null;
        }
        vo.setAllowEdit(BizUtil.checkAllowEdit(loginUser, vo.getId()));
        vo.setAllowDelete(BizUtil.checkAllowDelete(loginUser, vo.getId()));

        //增加阅读数
        asyncHandleService.addThreadVisitsAmount(vo.getId());
        return vo;
    }

    @Override
    public PageInfo<ThreadsVo> favoriteThreads(FavoritesReq req) {
        req.setFavoriteableType("App\\Models\\Thread");
        PageInfo<Favorites> page = favoritesService.favoriteThreads(req);
        List<Favorites> list = page.getList();
        List<ThreadsVo> rspList = new ArrayList<>();
        if (CollectionUtil.isNotEmpty(list)) {
            List<Long> favIdList = list.stream().map(Favorites::getFavoriteableId).collect(Collectors.toList());
            rspList = findByIdList(favIdList);
        }
        return PageUtil.pageInfo2PageRsp(page, rspList);
    }

    @Override
    public PageInfo<ThreadsVo> myThreads(FavoritesReq req) {
        ThreadsExample example = new ThreadsExample();
        ThreadsExample.Criteria criteria = example.createCriteria();
        criteria.andDeletedAtIsNull();
        criteria.andUserIdEqualTo(req.getLoginUserId());
        example.setOrderByClause(" id desc");

        PageHelper.startPage(req.getPage(), req.getPagesize());
        List<Threads> datas = threadsMapper.selectByExample(example);
        PageInfo<Threads> page = new PageInfo<>(datas);
        List<ThreadsVo> rspList = getThreadsVos(datas);
        return PageUtil.pageInfo2PageRsp(page, rspList);
    }

    @Override
    public PageInfo<ThreadsVo> threadsByUserId(FavoritesReq req) {
        ThreadsExample example = new ThreadsExample();
        ThreadsExample.Criteria criteria = example.createCriteria();
        criteria.andDeletedAtIsNull();
        criteria.andUserIdEqualTo(req.getUserId());
        example.setOrderByClause(" id desc");

        PageHelper.startPage(req.getPage(), req.getPagesize());
        List<Threads> datas = threadsMapper.selectByExample(example);
        PageInfo<Threads> page = new PageInfo<>(datas);
        List<ThreadsVo> rspList = getThreadsVos(datas);
        return PageUtil.pageInfo2PageRsp(page, rspList);
    }

    private List<ThreadsVo> getThreadsVos(List<Threads> datas) {
        List<ThreadsVo> rspList = new ArrayList<>();
        if (CollectionUtil.isNotEmpty(datas)) {
            List<Long> idList = datas.stream().map(Threads::getId).collect(Collectors.toList());

            Set<Long> userIdSet = datas.stream().map(Threads::getUserId).collect(Collectors.toSet());
            List<UserSimpleInfoVo> userSimpleInfoVos = usersService.selectUserSimpleInfoByIdList(userIdSet);
            Map<Long, UserSimpleInfoVo> userNameMap = userSimpleInfoVos.stream().collect(Collectors.toMap(UserSimpleInfoVo::getId, Function.identity()));

            Set<Long> forumIdSet = datas.stream().map(Threads::getForumId).collect(Collectors.toSet());
            List<Forums> forumsList = forumsService.getByIdList(ListUtil.toList(forumIdSet));
            Map<Long, Forums> forumMap = forumsList.stream().collect(Collectors.toMap(Forums::getId, Function.identity()));
            // 统计回复数
            List<ThreadsReplyCountVo> threadsReplyCountList = threadReplyService.countByThreadIdList(idList);
            Map<Long, Long> countMap = threadsReplyCountList.stream().collect(Collectors.toMap(ThreadsReplyCountVo::getThreadId, ThreadsReplyCountVo::getCount));

            Set<Long> topicIdSet = datas.stream().map(Threads::getTopicId).collect(Collectors.toSet());
            List<ThreadTopics> topicSet = threadTopicsService.getByIdSet(topicIdSet);
            Map<Long, ThreadTopics> topicMap = topicSet.stream().collect(Collectors.toMap(ThreadTopics::getId, Function.identity()));

            String defaultUserAvatar = dictService.getDefaultUserAvatar();
            rspList = datas.stream().map(info -> this.convertVo(info, forumMap, countMap, userNameMap, topicMap, defaultUserAvatar)).collect(Collectors.toList());
        }
        return rspList;
    }

    private ThreadsVo convertVo(Threads info, Map<Long, Forums> forumMap, Map<Long, Long> countMap, Map<Long, UserSimpleInfoVo> userNameMap, Map<Long, ThreadTopics> topicMap, String defaultUserAvatar) {
        ContentUtil.processThreadsContent(info);
        ThreadsVo resp = new ThreadsVo();
        BeanUtils.copyProperties(info, resp, "thumbs", "tags");
        if (StrUtil.isNotBlank(info.getThumbs())) {
            resp.setThumbs(JSONUtil.toList(info.getThumbs(), String.class));
        } else {
            resp.setThumbs(new ArrayList<>());
        }
        resp.setTags(processThreadTags(info.getTags()));

        UserSimpleInfoVo userSimpleInfoVo = userNameMap.get(resp.getUserId());
        if (ObjectUtil.isNotNull(userSimpleInfoVo)) {
            resp.setUserName(userSimpleInfoVo.getName());
            String avatar = userSimpleInfoVo.getAvatar();
            if (StrUtil.isBlank(avatar)) {
                resp.setAvatar(ImageUtil.completeImageUrl(defaultUserAvatar));
            } else {
                resp.setAvatar(ImageUtil.completeImageUrl(userSimpleInfoVo.getAvatar()));
            }
            Integer exp = userSimpleInfoVo.getExp();
            UserLevelVo userLevelVo = userLevelService.matchLevelByExp(exp);
            if (ObjectUtil.isNotNull(userLevelVo)) {
                resp.setUserLevelBadge(userLevelVo.getUserLevel().getBadge());
            }
        }
        Long count = countMap.get(info.getId());
        if (ObjectUtil.isNotNull(count)) {
            resp.setThreadReplyNum(count);
            resp.setReplyCount(Integer.parseInt(count + ""));
        } else {
            resp.setThreadReplyNum(0L);
            resp.setReplyCount(0);
        }
        resp.setForum(forumMap.get(resp.getForumId()));
        resp.setPostTime(info.getCreatedAt());

        Long topicId = info.getTopicId();
        if (ObjectUtil.isNotNull(topicId) && !NumberUtil.equals(topicId, 0)) {
            ThreadTopics threadTopics = topicMap.get(topicId);
            ThreadTopicsVo threadTopicsVo = BeanUtil.copyProperties(threadTopics, ThreadTopicsVo.class);
            Long forumId = threadTopicsVo.getForumId();
            if (ObjectUtil.isNotNull(forumId)) {
                Forums forums = forumMap.get(forumId);
                threadTopicsVo.setForum(forums);
            }
            if (StrUtil.isNotBlank(threadTopicsVo.getPic())) {
                threadTopicsVo.setPic(ImageUtil.completeImageUrl(threadTopicsVo.getPic()));
            }
            resp.setTopic(threadTopicsVo);
        }
        return resp;
    }

    private List<String> processThreadTags(String tags) {
        List<String> tagList = null;
        if (StrUtil.isNotBlank(tags)) {
            try {
                if (!("[]".equals(tags) || "{}".equals(tags))) {
                    if (tags.startsWith("[")) {
                        tagList = JSONUtil.toList(tags, String.class);
                    } else if (tags.startsWith("{")) {
                        tagList = new ArrayList<>(JSONUtil.toBean(tags, Map.class).values());
                    }
                }
            } catch (Exception e) {
                logger.error("转化错误:" + tags, e);
            }
        }
        return tagList;
    }

    @Override
    public void modifyThreadById(ThreadsReq req) {
        UserSimpleInfoVo loginUser = usersService.selectUserSimpleInfoById(req.getLoginUserId());
        if (ObjectUtil.isNull(loginUser)) {
            throw new BizException(CodeCons.ERROR, "用户不存在");
        }
        if (NumberUtil.equals(loginUser.getIsBanPost(), 1)) {
            throw new BizException(CodeCons.ERROR, ApiExceptionEnum.NO_PERMISSION_TO_POST_THREAD.getMessage());
        }
        Threads threads = threadsMapper.selectByPrimaryKey(req.getId());
        boolean allowEdit = BizUtil.checkAllowEdit(loginUser, threads.getUserId());
        Threads update = getUpdate(req, allowEdit, threads);
        threadsMapper.updateByPrimaryKeySelective(update);
    }

    @Override
    public void deleteThread(ThreadsReq req, Long id) {
        UserSimpleInfoVo loginUser = usersService.selectUserSimpleInfoById(req.getLoginUserId());
        if (ObjectUtil.isNull(loginUser)) {
            throw new BizException(CodeCons.ERROR, "用户不存在");
        }
        if (NumberUtil.equals(loginUser.getIsBanPost(), 1)) {
            throw new BizException(CodeCons.ERROR, ApiExceptionEnum.NO_PERMISSION_TO_POST_THREAD.getMessage());
        }
        Threads threads = threadsMapper.selectByPrimaryKey(id);
        boolean allowDelete = BizUtil.checkAllowDelete(loginUser, threads.getUserId());
        if (!allowDelete) {
            throw new BizException(CodeCons.ERROR, ApiExceptionEnum.NO_PERMISSION_TO_EDIT.getMessage());
        }
        Integer notAllowedDelete = threads.getNotAllowedDelete();
        if (NumberUtil.equals(notAllowedDelete, 1)) {
            throw new BizException(CodeCons.ERROR, "不允许删除");
        }
        Date now = new Date();
        Threads update = new Threads();
        update.setId(threads.getId());
        update.setDeletedAt(now);
        threadsMapper.updateByPrimaryKeySelective(update);
    }

    @Override
    public LikeVo like(ThreadsReq req) {
        LikeVo result = new LikeVo();
        Threads byId = this.getById(req.getId());
        Likes byType = likesService.findByType(req.getLoginUserId(), byId.getId(), LikeAndFavTypeEnum.THREAD);
        if (ObjectUtil.isNull(byType)) {
            likesService.addLike(req.getLoginUserId(), byId.getId(), LikeAndFavTypeEnum.THREAD);
            result.setLikeStatus(true);
        } else {
            likesService.cancelLike(byType.getId());
            result.setLikeStatus(false);
        }
        // 统计点赞数
        Long count = likesService.countById(byId.getId(), LikeAndFavTypeEnum.THREAD);
        result.setLikeCount(count + byId.getIncrLikes());
        return result;
    }

    @Override
    public FavVo favorite(ThreadsReq req) {
        FavVo result = new FavVo();
        Threads byId = this.getById(req.getId());
        Favorites byType = favoritesService.findByType(req.getLoginUserId(), byId.getId(), LikeAndFavTypeEnum.THREAD);
        if (ObjectUtil.isNull(byType)) {
            favoritesService.addFav(req.getLoginUserId(), byId.getId(), LikeAndFavTypeEnum.THREAD);
            result.setFavStatus(true);
        } else {
            favoritesService.cancelFav(byType.getId());
            result.setFavStatus(false);
        }
        return result;
    }

    @Override
    public PageInfo<SearchForumVo> searchForum(SearchReq req) {
        if (StrUtil.isBlank(req.getKeyword().trim())) {
            return new PageInfo<>();
        }
        PageHelper.startPage(req.getPage(), req.getPagesize());
        List<SearchForumVo> datas = threadsExtMapper.searchForum(req.getKeyword());
        if (CollectionUtil.isNotEmpty(datas)) {
            Set<Long> userIdList = datas.stream().map(SearchForumVo::getUserId).collect(Collectors.toSet());
            List<UserSimpleInfoVo> userSimpleInfoVos = usersService.selectUserSimpleInfoByIdList(userIdList);
            Map<Long, UserSimpleInfoVo> userSimpleInfoVoMap = userSimpleInfoVos.stream().collect(Collectors.toMap(UserSimpleInfoVo::getId, Function.identity()));

            Set<Long> forumIdList = datas.stream().map(SearchForumVo::getForumId).collect(Collectors.toSet());
            List<Forums> forumsList = forumsService.getByIdList(ListUtil.toList(forumIdList));
            Map<Long, Forums> forumsMap = forumsList.stream().collect(Collectors.toMap(Forums::getId, Function.identity()));
            String defaultUserAvatar = dictService.getDefaultUserAvatar();
            for (SearchForumVo item : datas) {
                Long userId = item.getUserId();
                UserSimpleInfoVo userSimpleInfoVo = userSimpleInfoVoMap.get(userId);
                if (ObjectUtil.isNotNull(userSimpleInfoVo)) {
                    UserShortVo userSimpleVo = new UserShortVo();
                    userSimpleVo.setId(userSimpleInfoVo.getId());
                    userSimpleVo.setName(userSimpleInfoVo.getName());
                    if (StrUtil.isNotBlank(userSimpleInfoVo.getAvatar())) {
                        userSimpleVo.setAvatar(ImageUtil.completeImageUrl(userSimpleInfoVo.getAvatar()));
                    } else {
                        userSimpleVo.setAvatar(ImageUtil.completeImageUrl(defaultUserAvatar));
                    }
                    item.setUser(userSimpleVo);
                }
                Long forumId = item.getForumId();
                Forums forums = forumsMap.get(forumId);
                if (ObjectUtil.isNotNull(forums)) {
                    ForumsVo forumsVo = new ForumsVo();
                    forumsVo.setId(forums.getId());
                    forumsVo.setName(forums.getName());
                    forumsVo.setAlias(forums.getAlias());
                    forumsVo.setLabelColor(forums.getLabelColor());
                    item.setForum(forumsVo);
                }
                item.setPostTime(item.getCreatedAt());

                // 处理关键字高亮
                String highlight1 = ContentUtil.processHighlight(item.getSubject(), req.getKeyword());
                item.setSubject(highlight1);

                String highlight2 = ContentUtil.processHighlight(item.getDesc(), req.getKeyword());
                item.setDesc(highlight2);
            }
        }
        return new PageInfo<>(datas);
    }
}
