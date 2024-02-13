package com.coin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.coin.entity.*;
import com.coin.enums.LikeAndFavTypeEnum;
import com.coin.i18n.I18nUtil;
import com.coin.mapper.GuessMapper;
import com.coin.mapper.ext.GuessExtMapper;
import com.coin.req.GuessItemReq;
import com.coin.req.GuessReq;
import com.coin.resp.guess.GuessDetailVo;
import com.coin.resp.guess.GuessItemVo;
import com.coin.resp.guess.GuessListVo;
import com.coin.resp.user.UserLevelVo;
import com.coin.service.*;
import com.coin.service.asyn.AsyncHandleService;
import com.coin.service.constant.CodeCons;
import com.coin.service.exception.BizException;
import com.coin.service.util.ContentUtil;
import com.coin.service.util.ImageUtil;
import com.coin.service.util.MD5Util;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class GuessServiceImpl implements GuessService {

    private static final Logger logger = LoggerFactory.getLogger(GuessServiceImpl.class);

    private static final int[] CAMP_TYPE_ALLOW = new int[]{1, 2};

    @Resource
    private GuessMapper guessMapper;

    @Resource
    private GuessExtMapper guessExtMapper;

    @Resource
    private GuessItemService guessItemService;

    @Resource
    private GuessTypeService guessTypeService;

    @Resource
    private AsyncHandleService asyncHandleService;

    @Resource
    private LikesService likesService;

    @Resource
    private FavoritesService favoritesService;

    @Resource
    private UsersService usersService;

    @Resource
    private UserLevelService userLevelService;

    @Resource
    private DictService dictService;

    @Resource
    private FollowablesService followablesService;

    private static Guess buildNew(GuessReq req) {
        Date now = new Date();
        Guess guess = new Guess();
        guess.setTitle(req.getTitle());
        guess.setGuessSubject(req.getGuessSubject());
        guess.setCampType(req.getCampType());
        guess.setGuessTypeId(req.getGuessTypeId());
        guess.setVisits(0);
        guess.setComments(0);
        guess.setContent(req.getContent());
        guess.setAdminUserId(req.getAdminUserId());
        guess.setAdminUserName(req.getAdminUserName());
        guess.setBeginTime(req.getBeginTime());
        guess.setEndTime(req.getEndTime());
        guess.setStatus(1);
        guess.setSecondConfirm(MD5Util.MD5(req.getSecondConfirm()));
        guess.setUpdatedAt(now);
        guess.setCreatedAt(now);
        guess.setMatchStartTime(req.getMatchStartTime());
        guess.setHomeTeamName(req.getHomeTeamName());
        guess.setGuestTeamName(req.getGuestTeamName());
        return guess;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void add(GuessReq req) {
        checkGuess(req);
        List<GuessItemReq> guessItemList = req.getGuessItemList();
        List<GuessItem> guessItems = new ArrayList<>();
        Set<String> guessItemNameSet = new HashSet<>();
        for (GuessItemReq item : guessItemList) {
            GuessItem add = new GuessItem();
            if (StrUtil.isBlank(item.getItemName())) {
                throw new BizException(CodeCons.ERROR, "投注项名称不能未空");
            }
            if (ObjectUtil.isNull(item.getItemOdds()) || item.getItemOdds().doubleValue() <= 0) {
                throw new BizException(CodeCons.ERROR, "投注项赔率倍数必须大于零");
            }
            if (guessItemNameSet.contains(item.getItemName())) {
                throw new BizException(CodeCons.ERROR, "投注项名称重复");
            }
            guessItemNameSet.add(item.getItemName());
            add.setItemName(item.getItemName());
            add.setItemOdds(item.getItemOdds());
            guessItems.add(add);
        }

        Guess guess = buildNew(req);
        processAdminUser(guess);
        ContentUtil.processGuessContent(guess);
        int i = guessMapper.insertSelective(guess);
        if (i == 1) {
            // 添加投注项
            Long guessId = guess.getId();
            for (GuessItem guessItem : guessItems) {
                GuessItemReq itemReq = new GuessItemReq();
                itemReq.setGuessId(guessId);
                itemReq.setItemName(guessItem.getItemName());
                itemReq.setItemOdds(guessItem.getItemOdds());
                guessItemService.add(itemReq);
            }
        } else {
            throw new BizException(CodeCons.ERROR, "新增失败");
        }
    }

    private void processAdminUser(Guess guess) {
        Users users = usersService.selectById(guess.getAdminUserId());
        guess.setAdminUserName(users.getName());
    }

    private void checkGuess(GuessReq req) {
        Date now = new Date();
        if (!ArrayUtil.contains(CAMP_TYPE_ALLOW, req.getCampType())) {
            throw new BizException(CodeCons.ERROR, "阵营选择只允许1或2");
        }
        if (NumberUtil.equals(req.getCampType(), 2)) {
            if (StrUtil.isBlank(req.getHomeTeamName())) {
                throw new BizException(CodeCons.ERROR, "选择双阵营时主队名称必填");
            }
            if (StrUtil.isBlank(req.getGuestTeamName())) {
                throw new BizException(CodeCons.ERROR, "选择双阵营时客队名称必填");
            }
        }
        if (DateUtil.compare(req.getMatchStartTime(), now) < 0) {
            throw new BizException(CodeCons.ERROR, "开赛时间必须大于当前时间");
        }

        GuessType guessType = guessTypeService.getById(req.getGuessTypeId());
        if (ObjectUtil.isNull(guessType)) {
            throw new BizException(CodeCons.ERROR, "竞猜类型不存在");
        }

        if (DateUtil.compare(req.getBeginTime(), now) < 0) {
            throw new BizException(CodeCons.ERROR, "竞猜开始时间必须大于当前时间");
        }
        if (DateUtil.compare(req.getEndTime(), now) < 0) {
            throw new BizException(CodeCons.ERROR, "竞猜结束时间必须大于当前时间");
        }
        if (DateUtil.compare(req.getEndTime(), req.getEndTime()) > 0) {
            throw new BizException(CodeCons.ERROR, "竞猜开始时间必须小于结束时间");
        }
        req.setGuessTypeName(guessType.getName());
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(GuessReq req) {
        Guess guess = this.getById(req.getId());
        Integer status = guess.getStatus();
        if (NumberUtil.equals(status, 0)) {
            throw new BizException(CodeCons.ERROR, "竞猜已关闭");
        }
        if (NumberUtil.equals(status, 2)) {
            throw new BizException(CodeCons.ERROR, "竞猜进行中");
        }
        if (NumberUtil.equals(status, 3)) {
            throw new BizException(CodeCons.ERROR, "竞猜已结束");
        }
        Date now = new Date();
        if (NumberUtil.equals(status, 1)) {
            // 未开始状态修改
            checkGuess(req);
            Guess updateGuess = new Guess();
            updateGuess.setId(req.getId());
            updateGuess.setTitle(req.getTitle());
            updateGuess.setGuessSubject(req.getGuessSubject());
            updateGuess.setCampType(req.getCampType());
            updateGuess.setGuessTypeId(req.getGuessTypeId());
            updateGuess.setContent(req.getContent());
            updateGuess.setAdminUserId(req.getAdminUserId());
            updateGuess.setAdminUserName(req.getAdminUserName());
            updateGuess.setBeginTime(req.getBeginTime());
            updateGuess.setEndTime(req.getEndTime());
            updateGuess.setUpdatedAt(now);
            updateGuess.setMatchStartTime(req.getMatchStartTime());
            updateGuess.setHomeTeamName(req.getHomeTeamName());
            updateGuess.setGuestTeamName(req.getGuestTeamName());
            if (StrUtil.isNotBlank(req.getSecondConfirm())) {
                updateGuess.setSecondConfirm(MD5Util.MD5(req.getSecondConfirm()));
            }
            processAdminUser(guess);
            ContentUtil.processGuessContent(updateGuess);
            guessMapper.updateByPrimaryKeySelective(updateGuess);
            Set<Long> updateItemIdSet = new HashSet<>();
            // 修改竞猜item
            List<GuessItemReq> guessItemList = req.getGuessItemList();
            for (GuessItemReq item : guessItemList) {
                Long id = item.getId();
                if (ObjectUtil.isNull(id)) {
                    GuessItemReq addReq = new GuessItemReq();
                    addReq.setGuessId(updateGuess.getId());
                    addReq.setItemName(item.getItemName());
                    addReq.setItemOdds(item.getItemOdds());
                    Long addId = guessItemService.add(addReq);
                    updateItemIdSet.add(addId);
                } else {
                    GuessItemReq updateReq = new GuessItemReq();
                    updateReq.setId(item.getId());
                    updateReq.setGuessId(updateGuess.getId());
                    updateReq.setItemName(item.getItemName());
                    updateReq.setItemOdds(item.getItemOdds());
                    guessItemService.update(updateReq);
                    updateItemIdSet.add(item.getId());
                }
            }
            List<GuessItemVo> guessItemDbList = guessItemService.guessItemAll(guess.getId());
            for (GuessItemVo item : guessItemDbList) {
                Long id = item.getId();
                if (!updateItemIdSet.contains(id)) {
                    // 未包含,代表需要删除
                    guessItemService.deleteDirect(id);
                }
            }
        }
    }

    @Override
    public void updateExtra(GuessReq req) {
        Guess guess = getById(req.getId());
        int visits = guess.getVisits();
        int reqInt = req.getVisits();
        int updateInt = (visits + reqInt);
        if (updateInt < 0) {
            updateInt = 0;
        }
        logger.info("正在修改竞猜记录id:{} 访问数, 修改前:{} 修改后:{}", guess.getId(), visits, updateInt);
        Guess update = new Guess();
        update.setId(guess.getId());
        update.setVisits(updateInt);
        update.setUpdatedAt(new Date());
        guessMapper.updateByPrimaryKeySelective(update);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(GuessReq req) {
        Long id = req.getId();
        Guess guess = this.getById(id);
        Integer status = guess.getStatus();
        if (NumberUtil.equals(status, 0)) {
            throw new BizException(CodeCons.ERROR, "竞猜已关闭");
        }
        if (NumberUtil.equals(status, 3)) {
            throw new BizException(CodeCons.ERROR, "竞猜已结束");
        }
        if (NumberUtil.equals(status, 2)) {
            throw new BizException(CodeCons.ERROR, "竞猜进行中");
        }
        List<GuessItemVo> itemList = guessItemService.guessItemAvailableList(id);
        for (GuessItemVo guessItemVo : itemList) {
            guessItemService.deleteDirect(guessItemVo.getId());
        }
        guessMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Guess getById(Long id) {
        Guess guess = guessMapper.selectByPrimaryKey(id);
        if (ObjectUtil.isNull(guess)) {
            throw new BizException(CodeCons.ERROR, "记录不存在");
        }
        // 绕过原事务机制
        GuessServiceImpl bean = SpringUtil.getBean(GuessServiceImpl.class);
        bean.syncGuessStatus(guess);
        return guess;
    }

    @Override
    public PageInfo<Guess> pageList(GuessReq req) {
        GuessExample example = new GuessExample();
        GuessExample.Criteria criteria = example.createCriteria();

        if (ObjectUtil.isNotNull(req.getId())) {
            criteria.andIdEqualTo(req.getId());
        }

        if (StrUtil.isNotBlank(req.getTitle())) {
            criteria.andTitleLike("%" + req.getTitle() + "%");
        }
        if (StrUtil.isNotBlank(req.getGuessSubject())) {
            criteria.andGuessSubjectLike("%" + req.getGuessSubject() + "%");
        }

        if (ObjectUtil.isNotNull(req.getGuessTypeId())) {
            criteria.andGuessTypeIdEqualTo(req.getGuessTypeId());
        }
        if (ObjectUtil.isNotNull(req.getStatus())) {
            criteria.andStatusEqualTo(req.getStatus());
        }

        if (ObjectUtil.isNotNull(req.getAdminUserId())) {
            criteria.andAdminUserIdEqualTo(req.getAdminUserId());
        }
        if (StrUtil.isNotBlank(req.getAdminUserName())) {
            criteria.andAdminUserNameLike("%" + req.getAdminUserName() + "%");
        }

        example.setOrderByClause(" id desc");
        PageHelper.startPage(req.getPage(), req.getPagesize());
        List<Guess> datas = guessMapper.selectByExample(example);
        List<Long> idList = datas.stream().map(Guess::getId).collect(Collectors.toList());

        Map<Long, GuessType> typeMap = new HashMap<>();
        Set<Long> typeIdList = datas.stream().map(Guess::getGuessTypeId).collect(Collectors.toSet());
        if (CollectionUtil.isNotEmpty(typeIdList)) {
            List<GuessType> guessTypeList = guessTypeService.selectByIdList(typeIdList);
            typeMap = guessTypeList.stream().collect(Collectors.toMap(GuessType::getId, Function.identity()));
        }

        if (CollectionUtil.isNotEmpty(idList)) {
            List<GuessItem> guessItems = guessItemService.selectByGuessIdList(idList);
            Map<Long, List<GuessItem>> itemMap = guessItems.stream().collect(Collectors.groupingBy(GuessItem::getGuessId, Collectors.toList()));
            for (Guess data : datas) {
                data.setGuessItemList(itemMap.get(data.getId()));
                data.setGuessTypeName(typeMap.get(data.getGuessTypeId()).getName());
            }
        }
        return new PageInfo<>(datas);
    }

    @Override
    public PageInfo<GuessListVo> guessList(GuessReq req) {
        PageHelper.startPage(req.getPage(), req.getPagesize());
        List<GuessListVo> datas = guessExtMapper.guessList(req);
        String defaultUserAvatar = dictService.getDefaultUserAvatar();
        datas.forEach(item -> {
                    // 默认头像
                    String adminUserAvatar = item.getAdminUserAvatar();
                    if (StrUtil.isBlank(adminUserAvatar)) {
                        adminUserAvatar = defaultUserAvatar;
                    }
                    item.setAdminUserAvatar(ImageUtil.completeImageUrl(adminUserAvatar));
                }
        );
        return new PageInfo<>(datas);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void syncGuessStatus(Guess guess) {
        if (ObjectUtil.isNull(guess)) {
            throw new BizException(CodeCons.ERROR, "竞猜记录不存在");
        }
        // 状态, 0: 手动关闭 1: 未开始 2:进行中 3:已结束
        // 无须处理 0: 手动关闭 3:已结束
        Integer status = guess.getStatus();
        if (ObjectUtil.isNull(status)) {
            throw new BizException(CodeCons.ERROR, "竞猜记录状态异常");
        }
        Date now = new Date();
        if (NumberUtil.equals(status, 1)) {
            // 根据开始时间和结束时间同步
            if (DateUtil.compare(now, guess.getBeginTime()) >= 0 && DateUtil.compare(now, guess.getEndTime()) <= 0) {
                Guess update = new Guess();
                update.setId(guess.getId());
                update.setStatus(2);
                guessMapper.updateByPrimaryKeySelective(update);
                guess.setStatus(2);
            } else if (DateUtil.compare(now, guess.getEndTime()) > 0) {
                Guess update = new Guess();
                update.setId(guess.getId());
                update.setStatus(3);
                guessMapper.updateByPrimaryKeySelective(update);
                guess.setStatus(3);
            }
        } else if (NumberUtil.equals(status, 2)) {
            if (DateUtil.compare(now, guess.getEndTime()) > 0) {
                Guess update = new Guess();
                update.setId(guess.getId());
                update.setStatus(3);
                guessMapper.updateByPrimaryKeySelective(update);
                guess.setStatus(3);
            }
        }
    }

    @Override
    public Long countByGuessType(Long guessTypeId) {
        return guessExtMapper.countByGuessType(guessTypeId);
    }

    @Override
    public GuessDetailVo detail(GuessReq req) {
        Guess guess = getById(req.getId());
        GuessType guessType = guessTypeService.getById(guess.getGuessTypeId());
        if (ObjectUtil.isNull(guessType) || !NumberUtil.equals(guessType.getStatus(), 1)) {
            throw new BizException(CodeCons.ERROR, "竞猜类型未启用");
        }
        GuessDetailVo vo = new GuessDetailVo();
        BeanUtil.copyProperties(guess, vo);
        List<GuessItemVo> itemList = guessItemService.guessItemAvailableList(vo.getId());
        vo.setGuessItemList(itemList);
        vo.setLikeStatus(0);
        vo.setFavStatus(0);
        Likes likes = likesService.findByType(req.getLoginUserId(), guess.getId(), LikeAndFavTypeEnum.GUESS);
        if (ObjectUtil.isNotNull(likes)) {
            vo.setLikeStatus(1);
        }
        Favorites favorites = favoritesService.findByType(req.getLoginUserId(), guess.getId(), LikeAndFavTypeEnum.GUESS);
        if (ObjectUtil.isNotNull(favorites)) {
            vo.setFavStatus(1);
        }
        // 背景图
        String guessTypeImage = guessType.getImage();
        if (StrUtil.isBlank(guessTypeImage)) {
            vo.setBackgroundImage(ImageUtil.completeImageUrl("2023/10/202310262058330037aR.png"));
        } else {
            vo.setBackgroundImage(ImageUtil.completeImageUrl(guessTypeImage));
        }

        // 用户等级信息
        Users users = usersService.selectById(guess.getAdminUserId());
        UserLevelVo userLevelVo = userLevelService.matchLevelByExp(users.getExp());
        vo.setUserLevel(userLevelVo.getUserLevel());
        vo.setNextLevel(userLevelVo.getNextLevel());

        // 默认头像
        String adminUserAvatar = users.getAvatar();
        if (StrUtil.isBlank(adminUserAvatar)) {
            adminUserAvatar = dictService.getDefaultUserAvatar();
        }
        vo.setAdminUserName(users.getName());
        vo.setAdminUserAvatar(ImageUtil.completeImageUrl(adminUserAvatar));
        vo.setAdminUserExp(users.getExp());

        // 关注状态
        int isFollow = 0;
        if (ObjectUtil.isNotNull(req.getLoginUserId())) {
            Boolean flag = followablesService.checkAFollowB(req.getLoginUserId(), users.getId());
            if (flag) {
                isFollow = 1;
            }
        }
        vo.setIsFollow(isFollow);

        // 增加访问数量
        asyncHandleService.addGuessVisitsAmount(guess.getId());
        return vo;
    }

    @Override
    public void syncGuessStatusTask() {
        // 获取待同步记录
        Date now = new Date();
        String nowBefore = DateUtil.format(DateUtil.offsetMinute(now, -30), "yyyy-MM-dd HH:mm:ss");
        String nowAfter = DateUtil.format(DateUtil.offsetMinute(now, 30), "yyyy-MM-dd HH:mm:ss");
        List<Guess> waitList = guessExtMapper.selectWaitSync(nowBefore, nowAfter);
        if (CollectionUtil.isEmpty(waitList)) {
            logger.info("时间段:{} 至 {} 无竞猜记录同步", nowBefore, nowAfter);
        } else {
            logger.info("时间段:{} 至 {} 有{}条竞猜记录待同步", nowBefore, nowAfter, waitList.size());
            GuessService bean = SpringUtil.getBean(GuessServiceImpl.class);
            for (Guess guess : waitList) {
                bean.syncGuessStatus(guess);
            }
        }
    }

    @Override
    public String like(GuessReq req) {
        String result;
        Guess guess = this.getById(req.getId());
        Likes byType = likesService.findByType(req.getLoginUserId(), guess.getId(), LikeAndFavTypeEnum.GUESS);
        if (ObjectUtil.isNull(byType)) {
            likesService.addLike(req.getLoginUserId(), guess.getId(), LikeAndFavTypeEnum.GUESS);
            result = "点赞成功";
        } else {
            likesService.cancelLike(byType.getId());
            result = "已取消点赞";
        }
        // 国际化
        return I18nUtil.translateBiz(result);
    }

    @Override
    public String favorite(GuessReq req) {
        String result;
        Guess guess = this.getById(req.getId());
        Favorites byType = favoritesService.findByType(req.getLoginUserId(), guess.getId(), LikeAndFavTypeEnum.GUESS);
        if (ObjectUtil.isNull(byType)) {
            favoritesService.addFav(req.getLoginUserId(), guess.getId(), LikeAndFavTypeEnum.GUESS);
            result = "收藏成功";
        } else {
            favoritesService.cancelFav(byType.getId());
            result = "已取消收藏";
        }
        // 国际化
        return I18nUtil.translateBiz(result);
    }
}
