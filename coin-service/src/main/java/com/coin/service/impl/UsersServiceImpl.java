package com.coin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.coin.cache.UserBobiVo;
import com.coin.cache.UserThreadCountVo;
import com.coin.entity.*;
import com.coin.enums.ActivityTypeEnum;
import com.coin.enums.DrawNumChangeTypeEnum;
import com.coin.enums.NameCardChangeTypeEnum;
import com.coin.enums.NotificationType;
import com.coin.i18n.I18nUtil;
import com.coin.i18n.LongTextTranslate;
import com.coin.mapper.TransactionsMapper;
import com.coin.mapper.UserExpLogsMapper;
import com.coin.mapper.UsersMapper;
import com.coin.mapper.WalletsMapper;
import com.coin.mapper.ext.UsersExtMapper;
import com.coin.mapper.ext.WalletsExtMapper;
import com.coin.req.*;
import com.coin.req.search.SearchReq;
import com.coin.resp.ArticlesResp;
import com.coin.resp.ReportsResp;
import com.coin.resp.UsersResp;
import com.coin.resp.activity.ActivityDrawNumberVo;
import com.coin.resp.activity.NameCardNumberVo;
import com.coin.resp.article.ArticleUserVo;
import com.coin.resp.search.SearchUserVo;
import com.coin.resp.user.*;
import com.coin.service.*;
import com.coin.service.asyn.AsyncNotificationService;
import com.coin.service.asyn.BobiAndExpService;
import com.coin.service.config.MailServerConfig;
import com.coin.service.constant.BizCons;
import com.coin.service.constant.CodeCons;
import com.coin.service.exception.BizException;
import com.coin.service.util.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class UsersServiceImpl implements UsersService {

    private static final Logger logger = LoggerFactory.getLogger(UsersServiceImpl.class);

    @Resource
    private UsersMapper usersMapper;
    @Resource
    private UsersExtMapper usersExtMapper;
    @Resource
    private UserExpLogsMapper userExpLogsMapper;
    @Resource
    private WalletsMapper walletsMapper;
    @Resource
    private WalletsExtMapper walletsExtMapper;
    @Resource
    private TransactionsMapper transactionsMapper;
    @Resource
    private UserLevelService userLevelService;
    @Resource
    private ArticlesService articlesService;
    @Resource
    private ReportsService reportsService;
    @Resource
    private WalletsService walletsService;
    @Resource
    private FollowablesService followablesService;
    @Resource
    private RedisUtil redisUtil;
    @Resource
    private DictService dictService;
    @Resource
    private ThreadsService threadsService;
    @Resource
    private NotificationsService notificationsService;
    @Resource
    private UserExpLogsService userExpLogsService;
    @Resource
    private BobiAndExpService bobiAndExpService;
    @Resource
    private AsyncNotificationService asyncNotificationService;
    @Resource
    private ActivityDrawNumberService activityDrawNumberService;
    @Resource
    private ActivityNameCardService activityNameCardService;
    @Resource
    private ActivityService activityService;
    @Resource
    private ActivityInviteFriendService activityInviteFriendService;
    @Resource
    private MailServerConfig mailServerConfig;
    @Value("${spring.profiles.active}")
    private String env;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Long add(UsersReq req) {
        Users checkName = this.selectByUserName(req.getName());
        if (ObjectUtil.isNotNull(checkName)) {
            throw new BizException(CodeCons.ERROR, "用户名已被注册");
        }
        Users checkEmail = this.selectByEmail(req.getEmail());
        if (ObjectUtil.isNotNull(checkEmail)) {
            throw new BizException(CodeCons.ERROR, "邮箱地址已被注册");
        }
        Date now = new Date();
        Users users = new Users();
        users.setName(req.getName());//用户名
        users.setEmail(req.getEmail());//邮箱
        users.setEmailVerifiedAt(req.getEmailVerifiedAt());//邮箱验证时间
        users.setOnlineUntil(req.getOnlineUntil());//在线时间
        users.setIsBaned(req.getIsBaned());//是否禁用
        users.setIsBanPost(req.getIsBanPost());//是否禁止发帖
        users.setIsBanForum(req.getIsBanForum());//是否禁止聊天
        users.setIsSuperAdmin(req.getIsSuperAdmin());//是否超级管理员
        users.setIsForumAdmin(req.getIsForumAdmin());//是否论坛管理员
        users.setBio(req.getBio());//签名
        users.setAvatar(req.getAvatar());//头像
        if (StrUtil.isNotBlank(req.getPassword())) {
            users.setPassword(MD5Util.MD5(req.getPassword()));//密码
        }
        // 来自邀请链接
        if (ObjectUtil.isNotNull(req.getInvitedByUser())) {
            users.setInvitedByUserId(req.getInvitedByUser().getId());
        }
        users.setCreatedAt(now);
        users.setUpdatedAt(now);
        int i = usersMapper.insertSelective(users);
        if (i == 1) {
            // 创建钱包
            walletsService.initWallet(users);

            // 记录邀请好友活动日志
            if (ObjectUtil.isNotNull(users.getInvitedByUserId())) {
                activityInviteFriendService.saveInvitedLog(users, req.getActivity(), req.getInvitedByUser());
            }
        } else {
            throw new BizException(CodeCons.ERROR, "新增失败");
        }
        return users.getId();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(UsersReq req) {
        Users oldContest = usersMapper.selectByPrimaryKey(req.getId());
        if (ObjectUtil.isNull(oldContest)) {
            throw new BizException(CodeCons.ERROR, "记录不存在");
        }
        Date now = new Date();
        Users updateUsers = new Users();
        updateUsers.setId(req.getId());
        updateUsers.setName(req.getName());
        updateUsers.setEmail(req.getEmail());
        updateUsers.setEmailVerifiedAt(req.getEmailVerifiedAt());
        updateUsers.setOnlineUntil(req.getOnlineUntil());
        updateUsers.setIsBaned(req.getIsBaned());
        updateUsers.setIsBanPost(req.getIsBanPost());
        updateUsers.setIsBanForum(req.getIsBanForum());
        updateUsers.setIsSuperAdmin(req.getIsSuperAdmin());
        updateUsers.setIsForumAdmin(req.getIsForumAdmin());
        updateUsers.setAvatar(req.getAvatar());
        updateUsers.setUpdatedAt(now);
        if (StrUtil.isNotBlank(req.getPassword())) {
            updateUsers.setPassword(MD5Util.MD5(req.getPassword()));
        }
        usersMapper.updateByPrimaryKeySelective(updateUsers);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateExp(UsersReq req) {
        if (req.getExp() == 0) {
            return;
        }
        Users oldContest = usersMapper.selectByPrimaryKey(req.getId());
        if (oldContest.getExp() + req.getExp() < 0) {
            throw new BizException(CodeCons.ERROR, "更新后值为负");
        }
        Date now = new Date();
        Users user = new Users();
        user.setExp(oldContest.getExp() + req.getExp());
        user.setId(oldContest.getId());
        user.setUpdatedAt(now);
        int count = usersMapper.updateByPrimaryKeySelective(user);
        if (count == 1) {
            UserExpLogs expLog = new UserExpLogs();
            expLog.setCreatedAt(now);
            expLog.setUpdatedAt(now);
            expLog.setExp(req.getExp());
            expLog.setUserId(req.getId().intValue());
            expLog.setInfo(I18nUtil.translateBiz("管理员扣减经验"));
            if (req.getExp() > 0) {
                expLog.setInfo(I18nUtil.translateBiz("管理员增加经验"));
            }
            userExpLogsMapper.insertSelective(expLog);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateBobi(UsersReq req) {
        if (req.getBobi() == 0) {
            return;
        }
        Wallets wallets = walletsService.getByUserId(req.getId());
        if (wallets.getBalance().add(new BigDecimal(req.getBobi())).compareTo(BigDecimal.ZERO) < 0) {
            throw new BizException(CodeCons.ERROR, "更新后值为负");
        }
        Date now = new Date();
        Wallets updateWallets = new Wallets();
        updateWallets.setBalance(wallets.getBalance().add(new BigDecimal(req.getBobi())));
        updateWallets.setId(wallets.getId());
        updateWallets.setUpdatedAt(now);
        int count = walletsMapper.updateByPrimaryKeySelective(updateWallets);
        if (count == 1) {
            Map<String, String> json = new HashMap<>();
            json.put("type", "Admin");
            json.put("info", I18nUtil.translateBiz("管理员扣减博币"));
            Transactions transactions = new Transactions();
            transactions.setCreatedAt(now);
            transactions.setUpdatedAt(now);
            transactions.setPayableType("App\\Models\\User");
            transactions.setPayableId(req.getId());
            transactions.setWalletId(wallets.getId());
            transactions.setType("withdraw");
            if (req.getBobi() > 0) {
                transactions.setType("deposit");
                json.put("info", I18nUtil.translateBiz("管理员增加博币"));
            }
            transactions.setAmount(new BigDecimal(req.getBobi()));
            transactions.setConfirmed(true);
            transactions.setMeta(JSONUtil.toJsonStr(json));
            transactions.setUuid(UUID.fastUUID().toString(true));
            transactionsMapper.insertSelective(transactions);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deductBobiForBet(UsersReq user) {
        BigDecimal betAmount = user.getBetAmount();
        if (betAmount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new BizException(CodeCons.ERROR, "下注金额异常");
        }
        Wallets wallets = walletsService.getByUserId(user.getId());
        if (ObjectUtil.isNull(wallets)) {
            throw new BizException(CodeCons.ERROR, "用户钱包不存在");
        }
        BigDecimal balance = wallets.getBalance();
        if (balance.compareTo(BigDecimal.ZERO) < 0) {
            throw new BizException(CodeCons.ERROR, "余额不足");
        }
        int count = walletsExtMapper.subtractBalance(wallets.getId(), betAmount);
        if (count == 1) {
            Date now = new Date();
            Map<String, String> json = new HashMap<>();
            json.put("type", "bet");
            json.put("info", user.getBetNote());
            Transactions transactions = new Transactions();
            transactions.setCreatedAt(now);
            transactions.setUpdatedAt(now);
            transactions.setPayableType("App\\Models\\User");
            transactions.setPayableId(user.getId());
            transactions.setWalletId(wallets.getId());
            transactions.setType("withdraw");
            transactions.setAmount(betAmount.multiply(new BigDecimal("-1")));
            transactions.setConfirmed(true);
            transactions.setMeta(JSONUtil.toJsonStr(json));
            transactions.setUuid(UUID.fastUUID().toString(true));
            transactionsMapper.insertSelective(transactions);
        } else {
            throw new BizException(CodeCons.ERROR, "支付失败");
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void increaseBobiForBet(UsersReq user) {
        BigDecimal settleAmount = user.getSettleAmount();
        if (settleAmount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new BizException(CodeCons.ERROR, "结算金额必须为正数");
        }
        Wallets wallets = walletsService.getByUserId(user.getId());
        if (ObjectUtil.isNull(wallets)) {
            throw new BizException(CodeCons.ERROR, "用户钱包异常");
        }
        BigDecimal balance = wallets.getBalance();
        if (balance.compareTo(BigDecimal.ZERO) < 0) {
            throw new BizException(CodeCons.ERROR, "余额不足");
        }
        int count = walletsExtMapper.increaseBalance(wallets.getId(), settleAmount);
        if (count == 1) {
            Date now = new Date();
            Map<String, String> json = new HashMap<>();
            json.put("type", "bet");
            json.put("info", user.getSettleNote());
            Transactions transactions = new Transactions();
            transactions.setCreatedAt(now);
            transactions.setUpdatedAt(now);
            transactions.setPayableType("App\\Models\\User");
            transactions.setPayableId(user.getId());
            transactions.setWalletId(wallets.getId());
            transactions.setType("deposit");
            transactions.setAmount(settleAmount);
            transactions.setConfirmed(true);
            transactions.setMeta(JSONUtil.toJsonStr(json));
            transactions.setUuid(UUID.fastUUID().toString(true));
            transactionsMapper.insertSelective(transactions);
        } else {
            throw new BizException(CodeCons.ERROR, "博币结算失败");
        }
    }

    @Override
    public UsersResp getById(Long id, Boolean needDetail) {
        Users users = usersMapper.selectByPrimaryKey(id);
        if (ObjectUtil.isNull(users)) {
            throw new BizException(CodeCons.ERROR, "记录不存在");
        }
        UsersResp usersResp = new UsersResp();
        BeanUtil.copyProperties(users, usersResp);
        UserLevelVo userLevelVo = userLevelService.matchLevelByExp(users.getExp());
        usersResp.setUserLevel(userLevelVo.getUserLevel());
        usersResp.setNextLevel(userLevelVo.getNextLevel());
        usersResp.setAvatar(ImageUtil.completeImageUrl(usersResp.getAvatar()));
        if (needDetail) {
            this.addDetail(usersResp);
        }
        return usersResp;
    }

    @Override
    public Users selectById(Long id) {
        Users users = usersMapper.selectByPrimaryKey(id);
        if (ObjectUtil.isNull(users)) {
            throw new BizException(CodeCons.ERROR, "用户不存在");
        }
        return users;
    }

    @Override
    public PageInfo<UsersResp> pageList(UsersReq req) {
        UsersExample example = new UsersExample();
        UsersExample.Criteria criteria = example.createCriteria();
        if (req.getId() != null) {
            criteria.andIdEqualTo(req.getId());
        }
        if (StrUtil.isNotBlank(req.getName())) {
            criteria.andNameLike("%" + req.getName() + "%");
        }
        example.setOrderByClause(" id desc");
        PageHelper.startPage(req.getPage(), req.getPagesize());
        List<Users> datas = usersMapper.selectByExample(example);
        PageInfo<Users> pageInfo = new PageInfo<>(datas);
        String defaultUserAvatar = dictService.getDefaultUserAvatar();
        List<Long> hasInviteCodeList = new ArrayList<>();
        List<UsersResp> rspList = datas.stream().map(info -> this.convertRsp(info, defaultUserAvatar, hasInviteCodeList)).collect(Collectors.toList());
        if (CollectionUtil.isNotEmpty(hasInviteCodeList)) {
            List<Long> hasInviteList = activityInviteFriendService.checkByInviteCode(hasInviteCodeList);
            if (CollectionUtil.isNotEmpty(hasInviteList)) {
                for (UsersResp item : rspList) {
                    // 设置状态
                    item.setInviteListFlag(hasInviteList.contains(item.getId()));
                }
            }
        }
        return PageUtil.pageInfo2PageRsp(pageInfo, rspList);
    }

    @Override
    public Users selectByUserName(String username) {
        UsersExample example = new UsersExample();
        UsersExample.Criteria criteria = example.createCriteria();
        criteria.andNameEqualTo(username);
        List<Users> users = usersMapper.selectByExample(example);
        if (CollectionUtil.isEmpty(users)) {
            return null;
        }
        if (users.size() > 1) {
            throw new BizException(CodeCons.ERROR, "存在多条记录");
        }
        return users.get(0);
    }

    @Override
    public Users selectByEmail(String email) {
        UsersExample example = new UsersExample();
        UsersExample.Criteria criteria = example.createCriteria();
        criteria.andEmailEqualTo(email);
        List<Users> users = usersMapper.selectByExample(example);
        if (CollectionUtil.isEmpty(users)) {
            return null;
        }
        if (users.size() > 1) {
            throw new BizException(CodeCons.ERROR, "存在多条记录");
        }
        return users.get(0);
    }

    private void addDetail(UsersResp resp) {
        //发帖数和总数
        Date now = new Date();
        ArticlesReq req = new ArticlesReq();
        req.setPage(1);
        req.setPagesize(100000);
        req.setUserId(resp.getId());
        List<ArticlesResp> list = articlesService.pageList(req).getList();
        //帖子总数
        resp.setTotalArticleNum(list.size());
        req.setCreatedAtMin(cn.hutool.core.date.DateUtil.beginOfDay(now));
        list = articlesService.pageList(req).getList();
        //今日帖子数
        resp.setTodayArticleNum(list.size());

        ReportsReq rreq = new ReportsReq();
        rreq.setPage(1);
        rreq.setPagesize(100000);
        rreq.setUserId(resp.getId());
        List<ReportsResp> reportsList = reportsService.pageList(rreq).getList();
        //投诉总数
        resp.setTotalReportNum(reportsList.size());
        rreq.setCreatedAtMin(now);
        reportsList = reportsService.pageList(rreq).getList();
        //今日投诉
        resp.setTodayReportNum(reportsList.size());

        //关注数
        List<Followables> followables = followablesService.getListByUserId(resp.getId());
        resp.setTotalFollowableNum(followables.size());
        //粉丝数
        followables = followablesService.getListByFTypeAndFId("App\\Models\\User", resp.getId());
        resp.setTotalFans(followables.size());
        //博币:目前只查看余额
        Wallets wallets = walletsService.getByUserId(resp.getId());
        resp.setBobi(wallets.getBalance());
        //是否在线
        resp.setIsOnline(0);
        String tokenKey = RedisUtil.getApiKey(resp.getEmail() + ":token");
        if (redisUtil.get(tokenKey) != null) {
            resp.setIsOnline(1);
        }
    }

    private UsersResp convertRsp(Users users, String defaultAvatar, List<Long> hasInviteCodeList) {
        UsersResp resp = new UsersResp();
        BeanUtils.copyProperties(users, resp);
        resp.setIsOnline(0);
        String tokenKey = RedisUtil.getApiKey(users.getEmail() + ":token");
        if (redisUtil.get(tokenKey) != null) {
            resp.setIsOnline(1);
        }
        if (StrUtil.isBlank(users.getAvatar())) {
            resp.setAvatar(defaultAvatar);
        }
        if (StrUtil.isNotBlank(users.getInviteCode())) {
            hasInviteCodeList.add(users.getId());
        }
        return resp;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void doClientRegister(UsersReq usersReq, HttpServletRequest request) {
        Long userId = this.add(usersReq);
        String ip = usersReq.getReqIp();
        String ipKey = "email_limit_ip_" + ip;
        String ipVal = redisUtil.get(ipKey);
        int ipCount = ipVal != null ? Integer.parseInt(ipVal) : 0;
        if (ipCount >= 20) {
            return;
        }
        Date now = new Date();
        redisUtil.set(ipKey, "" + (ipCount + 1), cn.hutool.core.date.DateUtil.offsetDay(now, 1).getTime());
        String emailKey = "email_limit_email_" + usersReq.getEmail();
        String lastEmailTime = redisUtil.get(emailKey);
        try {
            if (lastEmailTime != null && now.before(cn.hutool.core.date.DateUtil.offsetMinute(DateUtil.getDateByStr(lastEmailTime), 1))) {
                return;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        redisUtil.set(emailKey, DateUtil.getStrByDate(now), cn.hutool.core.date.DateUtil.offsetDay(now, 1).getTime());
        String uuid = UUID.fastUUID().toString(true);
        String key = buildVerifyEmailKey(uuid);
        redisUtil.set(key, usersReq.getEmail(), cn.hutool.core.date.DateUtil.offsetMinute(now, 10).getTime());

        String verificationUrl = domain(request) + "/auth/verifyEmail/" + uuid;
        String username = usersReq.getName();
        LongTextTranslate longTextTranslateBean = I18nUtil.getLongTextTranslateBean();
        String html = longTextTranslateBean.registerEmailHtml(username, verificationUrl);
        EmailsReq emailsReq = new EmailsReq();
        emailsReq.setEmail(usersReq.getEmail());
        emailsReq.setHtml(html);
        this.sendMailApi(emailsReq);
        bobiAndExpService.register(userId);
        NotificationsReq notifications = new NotificationsReq();
        notifications.setType("App\\Notifications\\Registered");
        String message = longTextTranslateBean.buildNotificationMessage(NotificationType.REGISTER, "10");
        notifications.setMessage(message);
        // notifications.setMessage("恭喜您,注册成功！一封邮箱验证邮件已经发送给您，邮件10分钟内有效，请尽快验证。");
        notifications.setNotifiableId(userId);
        notificationsService.buildNotification(notifications);
    }

    @Override
    public String doClientLogin(UsersReq req) {
        Users dbUser = usersExtMapper.selectByName(req.getName());
        if (ObjectUtil.isNull(dbUser)) {
            throw new BizException(CodeCons.ERROR, "用户不存在");
        }
        Integer isBaned = dbUser.getIsBaned();
        if (ObjectUtil.isNotNull(isBaned) && NumberUtil.equals(isBaned, 1)) {
            throw new BizException(CodeCons.AUTH_FAILED, "账号已被禁用");
        }
        // 如果当前用户password字段值为-1, 则使用当前req中password 2次md5作为当前用户密码
        Long dbUserId = dbUser.getId();
        String dbUserPassword = dbUser.getPassword();
        String dbUserEmail = dbUser.getEmail();
        if (StrUtil.isBlank(dbUserPassword)) {
            throw new BizException(CodeCons.ERROR, "用户未设置密码");
        }
        if (StrUtil.isBlank(dbUserEmail)) {
            throw new BizException(CodeCons.ERROR, "用户未设置邮箱");
        }
        // 兼容PHP老用户密码
        if (StrUtil.equals(dbUserPassword, "-1")) {
            String reqPassword = req.getPassword();
            logger.info("用户id:{}为PHP老用户,密码已重置为当前输入密码:{}", dbUserId, reqPassword);
            Users update = new Users();
            update.setId(dbUserId);
            update.setPassword(MD5Util.MD5(reqPassword));
            usersMapper.updateByPrimaryKeySelective(update);
        } else {
            if (!MD5Util.MD5(req.getPassword()).equals(dbUserPassword)) {
                throw new BizException(CodeCons.ERROR, "密码输入错误");
            }
        }
        String tokenKey = RedisUtil.getApiKey(dbUserEmail + ":token");
        String oldToken = redisUtil.get(tokenKey);
        if (StrUtil.isNotBlank(oldToken)) {
            redisUtil.remove(oldToken);
            redisUtil.remove(tokenKey);
        }
        Map<String, Object> sessionData = new HashMap<>();
        sessionData.put("id", dbUserId);
        sessionData.put("name", dbUser.getName());
        sessionData.put("email", dbUserEmail);
        sessionData.put("rocketUserId", dbUser.getRocketUserId());
        sessionData.put("rocketUserLoginToken", dbUser.getRocketUserLoginToken());
        String token = MD5Util.MD5(dbUser.getName() + System.currentTimeMillis() + BizUtil.getStringRandom(5, 1));
        redisUtil.set(token, JSONUtil.toJsonStr(sessionData), BizCons.SESSION_OUT_TIME_CLIENT);
        redisUtil.set(tokenKey, token, BizCons.SESSION_OUT_TIME_CLIENT);

        // 登录成功送博币
        bobiAndExpService.login(dbUserId);
        return token;
    }

    @Override
    public void doClientLogout(UsersReq req) {
        String email = req.getLoginUserEmail();
        String tokenKey = RedisUtil.getApiKey(email + ":token");
        String oldToken = redisUtil.get(tokenKey);
        if (StrUtil.isNotBlank(oldToken)) {
            redisUtil.remove(oldToken);
            redisUtil.remove(tokenKey);
        }
    }

    @Override
    public void updateClientUser(UsersReq req) {
        Users oldUser = usersMapper.selectByPrimaryKey(req.getLoginUserId());
        if (ObjectUtil.isNull(oldUser)) {
            throw new BizException(CodeCons.ERROR, "用户不存在");
        }
        Integer isBaned = oldUser.getIsBaned();
        if (ObjectUtil.isNotNull(isBaned) && NumberUtil.equals(isBaned, 1)) {
            throw new BizException(CodeCons.AUTH_FAILED, "账号已被禁用");
        }

        Date now = new Date();
        Users update = new Users();
        update.setId(oldUser.getId());
        if (StrUtil.isNotBlank(req.getAvatar())) {
            update.setAvatar(req.getAvatar());
            if (StrUtil.isBlank(oldUser.getAvatar())) {
                // 首次上传头像加博币经验
                bobiAndExpService.firstUploadUserAvatar(req.getLoginUserId());
            }
        }
        if (StrUtil.isNotBlank(req.getBio())) {
            update.setBio(req.getBio());
        }
        if (StrUtil.isNotBlank(req.getQq())) {
            update.setQq(req.getQq());
        }
        if (StrUtil.isNotBlank(req.getPhone())) {
            update.setPhone(req.getPhone());
        }
        if (StrUtil.isNotBlank(req.getWechat())) {
            update.setWechat(req.getWechat());
        }
        update.setUpdatedAt(now);
        usersMapper.updateByPrimaryKeySelective(update);
    }

    @Override
    public String verifyEmail(String uuid) {
        Date now = new Date();
        String key = buildVerifyEmailKey(uuid);
        String email = redisUtil.get(key);
        // 获取完后直接清除key
        redisUtil.remove(key);
        if (StrUtil.isNotEmpty(email)) {
            Users users = usersExtMapper.selectByEmail(email);
            if (ObjectUtil.isNotEmpty(users)) {
                Users updateUser = new Users();
                updateUser.setId(users.getId());
                updateUser.setEmailVerifiedAt(now);
                usersMapper.updateByPrimaryKeySelective(updateUser);
                bobiAndExpService.verifiedEmail(users.getId(), this.hiddenEmail(email));
                return "<script>alert(\"Verification successful!\");window.close();</script>";
            } else {
                return "<script>alert(\"Verification failed, please try again!\");window.close();</script>";
            }
        } else {
            return "<script>alert(\"Verification failed, link invalid or expired!\");window.close();</script>";
        }
    }

    @Override
    public void verifyResetPwdEmailCode(EmailsReq req) {
        Date now = new Date();
        String cachekey = "resetEmail:" + req.getTraceId();
        String ss = redisUtil.get(cachekey);
        if (StrUtil.isNotEmpty(ss)) {
            throw new BizException("100011", "验证码不存在或者已过期");
        }
        JSONObject resetPwdArr = JSONUtil.parseObj(ss);
        if (!Objects.equals(resetPwdArr.getStr("code"), req.getCode())) {//验证码错误
            throw new BizException("100012", "验证码错误");
        }
        if (resetPwdArr.getBool("verified") != null) {//验证码错误
            throw new BizException("100013", "验证码错误");
        }
        resetPwdArr.set("verified", true);
        String str = JSONUtil.toJsonStr(resetPwdArr);
        redisUtil.set(cachekey, str, cn.hutool.core.date.DateUtil.offsetMinute(now, 10).getTime());
    }

    @Override
    public EmailsResp sendResetPasswordMail(UsersReq req) {
        Users users = usersExtMapper.selectByName(req.getUsername());
        if (ObjectUtil.isNull(users)) {
            throw new BizException("100007", "用户名或者邮箱不存在");
        }
        String ip = req.getReqIp();
        String ipKey = "reset_pwd_email_limit_ip_" + ip;
        String ipVal = redisUtil.get(ipKey);
        if (ipVal == null) {
            ipVal = "0";
        }
        int ipCount = Integer.parseInt(ipVal);
        if (ipCount >= 100) {
            throw new BizException("100008", "今日已发送太多邮件");
        }
        Date now = new Date();
        if (ipCount == 0) {
            redisUtil.set(ipKey, "1", cn.hutool.core.date.DateUtil.endOfDay(now).getTime());
        } else {
            redisUtil.set(ipKey, "" + (Integer.parseInt(redisUtil.get(ipKey)) + 1));
        }
        String emailKey = "reset_pwd_email_limit_email_" + users.getEmail();
        String lastEmailTime = redisUtil.get(emailKey);
        try {
            if (lastEmailTime != null && now.before(cn.hutool.core.date.DateUtil.offsetMinute(DateUtil.getDateByStr(lastEmailTime), 1))) {
                throw new BizException("100010", "邮件发送时间间隔过短");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        redisUtil.set(emailKey, DateUtil.getStrByDate(now), cn.hutool.core.date.DateUtil.offsetDay(now, 1).getTime());
        String traceId = UUID.fastUUID().toString(true);
        String key = "resetEmail:" + traceId;
        Random random = new Random();
        int randomNumber = random.nextInt(999999);
        String token = String.format("%06d", randomNumber);
        Map<String, String> obj = new HashMap<>();
        obj.put("code", token);
        obj.put("email", users.getEmail());
        redisUtil.set(key, JSONUtil.toJsonStr(obj), cn.hutool.core.date.DateUtil.offsetMinute(now, 10).getTime());

        EmailsReq emailsReq = new EmailsReq();
        LongTextTranslate longTextTranslate = I18nUtil.getLongTextTranslateBean();
        String html = longTextTranslate.resetPasswordMailHtml(token);

        emailsReq.setEmail(users.getEmail());
        emailsReq.setHtml(html);
        //发送 code 邮件
        sendMailApi(emailsReq);

        EmailsResp emailsResp = new EmailsResp();
        emailsResp.setTraceId(traceId);
        return emailsResp;
    }

    @Override
    public void resetPassword(EmailsReq req) {
        if (!req.getPassword().equals(req.getPasswordConfirm())) {
            throw new BizException("100014", "两次输入密码不一致");
        }
        String cacheKey = "resetEmail:" + req.getTraceId();
        String cacheVal = redisUtil.get(cacheKey);
        if (StrUtil.isEmpty(cacheVal)) {
            throw new BizException("100014", "重置密码失败");
        }
        JSONObject resetPwdArr = JSONUtil.parseObj(cacheKey);
        if (!resetPwdArr.getStr("code").equals(req.getCode())) {
            throw new BizException("100012", "验证码错误");
        }
        Users users = usersExtMapper.selectByEmail(resetPwdArr.getStr("email"));
        Users users1 = new Users();
        users1.setId(users.getId());
        users1.setPassword(MD5Util.MD5(req.getPassword()));
        usersMapper.updateByPrimaryKeySelective(users1);
        redisUtil.remove(cacheKey);
    }

    @Override
    public void sendVerifyEmail(UsersReq req, HttpServletRequest request) {
        Users users = usersMapper.selectByPrimaryKey(req.getLoginUserId());
        if (ObjectUtil.isNotNull(users.getEmailVerifiedAt())) {
            throw new BizException("100037", "您的邮箱已完成验证");
        }
        String ip = req.getReqIp();
        String ipKey = "verify_email_limit_ip_" + ip;
        String s = redisUtil.get(ipKey);
        int ipCount = 0;
        if (StrUtil.isNotBlank(s)) {
            ipCount = Integer.parseInt(s);
        }
        if (ipCount >= 100) {
            throw new BizException("100008", "发送次数过多");
        }
        Date now = new Date();
        String emailDailyKey = "verify_email_limit_daily_" + users.getEmail();
        String emailCache = redisUtil.get(emailDailyKey);
        if (StrUtil.isNotBlank(emailCache)) {
            int emailCount = Integer.parseInt(emailCache);
            if (emailCount >= 20) {
                throw new BizException("100009", "发送次数过多");
            }
            redisUtil.set(emailDailyKey, emailCount + 1);
        } else {
            redisUtil.set(emailDailyKey, 1, cn.hutool.core.date.DateUtil.endOfDay(now).getTime());
        }
        // 检查同一邮箱发送邮件数量和间隔
        String emailKey = "verify_email_limit_email_" + users.getEmail();
        String lastEmailTime = redisUtil.get(emailKey);
        if (lastEmailTime != null) {
            throw new BizException("100010", "邮件发送时间间隔过短");
        }
        String uuid = UUID.fastUUID().toString(true);
        String key = buildVerifyEmailKey(uuid);
        redisUtil.set(key, users.getEmail(), cn.hutool.core.date.DateUtil.offsetMinute(now, 10).getTime());
        String verificationUrl = domain(request) + "/auth/verifyEmail/" + uuid;
        LongTextTranslate longTextTranslate = I18nUtil.getLongTextTranslateBean();
        String html = longTextTranslate.verifyEmailHtml(users.getName(), verificationUrl);
        EmailsReq emailsReq = new EmailsReq();
        emailsReq.setEmail(users.getEmail());
        emailsReq.setHtml(html);
        sendMailApi(emailsReq);
        if (ipCount == 0) {
            redisUtil.set(ipKey, 1, cn.hutool.core.date.DateUtil.endOfDay(now).getTime());
        } else {
            redisUtil.set(ipKey, redisUtil.get(ipKey) + 1);
        }
        redisUtil.set(emailKey, 1, 60);
    }

    @Override
    public void updateClientUserPwd(UsersReq req) {
        Users users = usersMapper.selectByPrimaryKey(req.getLoginUserId());
        if (!MD5Util.MD5(req.getCurrentPassword()).equals(users.getPassword())) {
            throw new BizException(CodeCons.ERROR, "密码输入错误");
        }
        Users update = new Users();
        update.setId(users.getId());
        update.setPassword(MD5Util.MD5(req.getNewPassword()));
        update.setUpdatedAt(new Date());
        usersMapper.updateByPrimaryKeySelective(update);
    }

    @Override
    public List<UserSimpleInfoVo> selectUserSimpleInfoByIdList(Set<Long> userIdList) {
        Set<Long> set = CollUtil.removeNull(userIdList);
        if (CollectionUtil.isNotEmpty(set)) {
            return usersExtMapper.selectUserSimpleInfoByIdList(userIdList);
        }
        return new ArrayList<>();
    }

    @Override
    public UserSimpleInfoVo selectUserSimpleInfoById(Long id) {
        if (ObjectUtil.isNull(id)) {
            return null;
        }
        return usersExtMapper.selectUserSimpleInfoById(id);
    }

    @Override
    public UserSimpleInfoVo selectUserSimpleInfoAndLevelById(Long id) {
        if (ObjectUtil.isNull(id)) {
            return null;
        }
        UserSimpleInfoVo userSimpleInfoVo = usersExtMapper.selectUserSimpleInfoById(id);
        if (ObjectUtil.isNull(userSimpleInfoVo)) {
            return null;
        }
        UserLevelVo userLevelVo = userLevelService.matchLevelByExp(userSimpleInfoVo.getExp());
        userSimpleInfoVo.setLevel(userLevelVo.getUserLevel());
        return userSimpleInfoVo;
    }

    @Override
    public PageInfo<UserRelationVo> selectFollowers(UsersReq req) {
        PageInfo<Followables> pageInfo = followablesService.findFollowersByUserId(req);
        List<Followables> followables = pageInfo.getList();
        List<UserRelationVo> resultList = new ArrayList<>();
        extracted(req, followables, resultList, true);
        return PageUtil.pageInfo2PageRsp(pageInfo, resultList);
    }

    @Override
    public PageInfo<UserRelationVo> selectFollowees(UsersReq req) {
        PageInfo<Followables> pageInfo = followablesService.findFolloweesByUserId(req);
        List<Followables> followables = pageInfo.getList();
        List<UserRelationVo> resultList = new ArrayList<>();
        extracted(req, followables, resultList, false);
        return PageUtil.pageInfo2PageRsp(pageInfo, resultList);
    }

    private void extracted(UsersReq req, List<Followables> followables, List<UserRelationVo> resultList, boolean isFollower) {
        if (CollectionUtil.isNotEmpty(followables)) {
            Set<Long> userIdList;
            if (isFollower) {
                userIdList = followables.stream().map(Followables::getUserId).collect(Collectors.toSet());
            } else {
                userIdList = followables.stream().map(Followables::getFollowableId).collect(Collectors.toSet());
            }
            List<UserSimpleInfoVo> userSimpleInfoVos = this.selectUserSimpleInfoByIdList(userIdList);
            Map<Long, UserSimpleInfoVo> userSimpleInfoVoMap = userSimpleInfoVos.stream().collect(Collectors.toMap(UserSimpleInfoVo::getId, Function.identity()));

            // 统计数量
            List<UserFolloweeCountVo> followeeCountList = followablesService.countFolloweeByUserIdList(userIdList);
            Map<Long, Long> followeeCountMap = followeeCountList.stream().collect(Collectors.toMap(UserFolloweeCountVo::getUserId, UserFolloweeCountVo::getCount));
            List<UserFollowerCountVo> followerCountList = followablesService.countFollowerByUserIdList(userIdList);
            Map<Long, Long> followerCountMap = followerCountList.stream().collect(Collectors.toMap(UserFollowerCountVo::getUserId, UserFollowerCountVo::getCount));

            // 帖子按用户统计
            List<UserThreadCountVo> threadCountList = threadsService.countByUserIdList(userIdList);
            Map<Long, Long> threadCountMap = threadCountList.stream().collect(Collectors.toMap(UserThreadCountVo::getUserId, UserThreadCountVo::getThreadCount));

            // 是否被我关注
            Set<Long> followSet = new HashSet<>();
            if (ObjectUtil.isNotNull(req.getLoginUserId())) {
                List<Long> followList = followablesService.checkAFollowBList(req.getLoginUserId(), userIdList);
                followSet.addAll(followList);
            }

            String defaultUserAvatar = dictService.getDefaultUserAvatar();
            followables.forEach(item -> {
                UserRelationVo vo = new UserRelationVo();
                UserSimpleInfoVo simpleInfoVo;
                if (isFollower) {
                    simpleInfoVo = userSimpleInfoVoMap.get(item.getUserId());
                } else {
                    simpleInfoVo = userSimpleInfoVoMap.get(item.getFollowableId());
                }

                if (ObjectUtil.isNotNull(simpleInfoVo)) {
                    BeanUtil.copyProperties(simpleInfoVo, vo);
                    Integer exp = vo.getExp();
                    String avatar = vo.getAvatar();
                    if (StrUtil.isBlank(avatar)) {
                        vo.setAvatar(ImageUtil.completeImageUrl(defaultUserAvatar));
                    } else {
                        vo.setAvatar(ImageUtil.completeImageUrl(avatar));
                    }

                    UserLevelVo userLevelVo = userLevelService.matchLevelByExp(exp);
                    UserRelationLevelVo userRelationLevelVo = BizUtil.buildLevelVo(userLevelVo);
                    vo.setLevel(userRelationLevelVo);

                    Long followerCount = followerCountMap.get(simpleInfoVo.getId());
                    Long followeeCount = followeeCountMap.get(simpleInfoVo.getId());
                    Long threadCount = threadCountMap.get(simpleInfoVo.getId());

                    if (ObjectUtil.isNull(followerCount)) {
                        followerCount = 0L;
                    }
                    if (ObjectUtil.isNull(followeeCount)) {
                        followeeCount = 0L;
                    }
                    if (ObjectUtil.isNull(threadCount)) {
                        threadCount = 0L;
                    }
                    vo.setFollowerCount(followerCount);
                    vo.setFolloweeCount(followeeCount);
                    vo.setThreadCount(threadCount);

                    if (ObjectUtil.isNotNull(req.getLoginUserId())) {
                        vo.setFollowedByMe(followSet.contains(simpleInfoVo.getId()));
                    }
                    resultList.add(vo);
                }
            });
        }
    }

    @Override
    public UserVo selectUserVo(UsersReq req) {
        Long id = req.getId();
        Users simpleInfoVo = usersMapper.selectByPrimaryKey(id);
        String avatar = simpleInfoVo.getAvatar();
        if (cn.hutool.core.util.StrUtil.isBlank(avatar)) {
            avatar = dictService.getDefaultUserAvatar();
        }
        UserLevelVo userLevelVo = userLevelService.matchLevelByExp(simpleInfoVo.getExp());
        UserRelationLevelVo level = BizUtil.buildLevelVo(userLevelVo);
        UserBobiVo userBobiVo = walletsService.selectByUserId(simpleInfoVo.getId());

        Long threadCount = threadsService.countByUserId(simpleInfoVo.getId());
        Long reportCount = reportsService.countByUserId(simpleInfoVo.getId());

        String email = simpleInfoVo.getEmail();
        email = hiddenEmail(email);

        UserVo vo = new UserVo();
        vo.setId(simpleInfoVo.getId());
        vo.setName(simpleInfoVo.getName());
        vo.setEmail(email);
        vo.setAvatar(ImageUtil.completeImageUrl(avatar));
        vo.setBio(simpleInfoVo.getBio());
        vo.setExp(simpleInfoVo.getExp());
        vo.setLevel(level);
        if (ObjectUtil.isNotNull(userBobiVo) && ObjectUtil.isNotNull(userBobiVo.getBobi())) {
            vo.setBobi(userBobiVo.getBobi().intValue());
        } else {
            vo.setBobi(0);
        }
        boolean followedByMe = false;
        if (ObjectUtil.isNotNull(req.getLoginUserId())) {
            followedByMe = followablesService.checkAFollowB(req.getLoginUserId(), simpleInfoVo.getId());
        }
        Long followeeCount = followablesService.countFolloweeByUserId(simpleInfoVo.getId());
        Long followerCount = followablesService.countFollowerByUserId(simpleInfoVo.getId());
        Long unreadNotifyCount = notificationsService.unreadNotifyCount(simpleInfoVo.getId());

        vo.setFollowedByMe(followedByMe);
        vo.setFolloweeCount(followeeCount);
        vo.setFollowerCount(followerCount);
        vo.setThreadCount(threadCount);
        vo.setReportCount(reportCount);
        vo.setRegisteredAt(simpleInfoVo.getCreatedAt());
        vo.setUnreadNotifyCount(unreadNotifyCount);

        vo.setQq(simpleInfoVo.getQq());
        vo.setWechat(simpleInfoVo.getWechat());
        vo.setPhone(simpleInfoVo.getPhone());
        if (ObjectUtil.isNotNull(simpleInfoVo.getEmailVerifiedAt())) {
            vo.setEmailVerified(true);
        }
        if (ObjectUtil.isNotNull(simpleInfoVo.getEmailModifiedAt())) {
            vo.setEmailModified(true);
        }
        return vo;
    }

    @Override
    public ArticleUserVo selectArticleUser(Long userId, Long loginUserid) {
        UserSimpleInfoVo simpleInfoVo = selectUserSimpleInfoById(userId);
        String avatar = simpleInfoVo.getAvatar();
        if (cn.hutool.core.util.StrUtil.isBlank(avatar)) {
            avatar = dictService.getDefaultUserAvatar();
        }
        UserLevelVo userLevelVo = userLevelService.matchLevelByExp(simpleInfoVo.getExp());
        UserRelationLevelVo level = BizUtil.buildLevelVo(userLevelVo);
        UserBobiVo userBobiVo = walletsService.selectByUserId(simpleInfoVo.getId());

        Long threadCount = threadsService.countByUserId(simpleInfoVo.getId());
        int bobi = 0;
        if (ObjectUtil.isNotNull(userBobiVo) && ObjectUtil.isNotNull(userBobiVo.getBobi())) {
            bobi = userBobiVo.getBobi().intValue();
        }
        boolean followedByMe = false;
        if (ObjectUtil.isNotNull(loginUserid)) {
            followedByMe = followablesService.checkAFollowB(loginUserid, simpleInfoVo.getId());
        }
        Long followeeCount = followablesService.countFolloweeByUserId(simpleInfoVo.getId());
        Long followerCount = followablesService.countFollowerByUserId(simpleInfoVo.getId());

        ArticleUserVo userVo = new ArticleUserVo();
        userVo.setId(userId);
        userVo.setName(simpleInfoVo.getName());
        userVo.setAvatar(ImageUtil.completeImageUrl(avatar));
        userVo.setBio(simpleInfoVo.getBio());
        userVo.setExp(simpleInfoVo.getExp());
        userVo.setBobi(bobi);
        userVo.setFollowedByMe(followedByMe);
        userVo.setFolloweeCount(followeeCount);
        userVo.setFollowerCount(followerCount);
        userVo.setThreadCount(threadCount);
        userVo.setLevel(level);
        return userVo;
    }

    @Override
    public void sendMailApi(EmailsReq req) {
        String url = mailServerConfig.getUrl();
        String username = mailServerConfig.getUsername();
        String password = mailServerConfig.getPassword();
        String from = "support@bominwang.com";
        String to = req.getEmail();
        String subject = I18nUtil.translateBiz("博民网");
        String html = req.getHtml();
        try {
            Map<String, Object> formData = new HashMap<>();
            formData.put("from", from);
            formData.put("to", to);
            formData.put("cc", "");
            formData.put("bcc", "");
            formData.put("subject", subject);
            formData.put("html", html);
            Map<String, String> headers = new HashMap<>();
            String ret = HttpRequest.
                    post(url)
                    .addHeaders(headers)
                    .basicAuth(username, password)
                    .form(formData)
                    .timeout(20000)
                    .execute()
                    .body();
            logger.info("邮件发送成功:{}->{},{},{}", from, to, html, ret);
        } catch (Exception e) {
            logger.error("邮件发送失败:{}->{},{}", from, to, html, e);
        }
    }

    @Override
    public void addExpByUserId(Long userId, Integer addAmount, String info) {
        if (ObjectUtil.isNull(userId)) {
            throw new BizException(CodeCons.ERROR, "用户编号为空");
        }
        if (ObjectUtil.isNull(addAmount) || addAmount <= 0) {
            throw new BizException(CodeCons.ERROR, "数值异常");
        }
        if (StrUtil.isBlank(info)) {
            throw new BizException(CodeCons.ERROR, "消息异常");
        }
        int count = usersExtMapper.addExpByUserId(userId, addAmount);
        if (count == 1) {
            UserExpLogsReq userExpLogsReq = new UserExpLogsReq();
            userExpLogsReq.setUserId(new Integer(userId + ""));
            userExpLogsReq.setExp(addAmount);
            userExpLogsReq.setInfo(info);
            userExpLogsService.add(userExpLogsReq);
        }
    }

    @Override
    public PageInfo<SearchUserVo> searchUser(SearchReq req) {
        if (StrUtil.isBlank(req.getKeyword().trim())) {
            return new PageInfo<>();
        }
        PageHelper.startPage(req.getPage(), req.getPagesize());
        List<SearchUserVo> datas = usersExtMapper.searchUser(req.getKeyword());
        if (CollectionUtil.isNotEmpty(datas)) {
            Set<Long> userIdList = datas.stream().map(SearchUserVo::getId).collect(Collectors.toSet());
            String defaultUserAvatar = dictService.getDefaultUserAvatar();
            // 统计数量
            List<UserFolloweeCountVo> followeeCountList = followablesService.countFolloweeByUserIdList(userIdList);
            Map<Long, Long> followeeCountMap = followeeCountList.stream().collect(Collectors.toMap(UserFolloweeCountVo::getUserId, UserFolloweeCountVo::getCount));
            List<UserFollowerCountVo> followerCountList = followablesService.countFollowerByUserIdList(userIdList);
            Map<Long, Long> followerCountMap = followerCountList.stream().collect(Collectors.toMap(UserFollowerCountVo::getUserId, UserFollowerCountVo::getCount));
            // 帖子按用户统计
            List<UserThreadCountVo> threadCountList = threadsService.countByUserIdList(userIdList);
            Map<Long, Long> threadCountMap = threadCountList.stream().collect(Collectors.toMap(UserThreadCountVo::getUserId, UserThreadCountVo::getThreadCount));
            // 是否被我关注
            Set<Long> followSet = new HashSet<>();
            if (ObjectUtil.isNotNull(req.getLoginUserId())) {
                List<Long> followList = followablesService.checkAFollowBList(req.getLoginUserId(), userIdList);
                followSet.addAll(followList);
            }
            for (SearchUserVo item : datas) {
                if (StrUtil.isNotBlank(item.getAvatar())) {
                    item.setAvatar(ImageUtil.completeImageUrl(item.getAvatar()));
                } else {
                    item.setAvatar(ImageUtil.completeImageUrl(defaultUserAvatar));
                }
                Long followerCount = followerCountMap.get(item.getId());
                Long followeeCount = followeeCountMap.get(item.getId());
                Long threadCount = threadCountMap.get(item.getId());
                if (ObjectUtil.isNull(followerCount)) {
                    followerCount = 0L;
                }
                if (ObjectUtil.isNull(followeeCount)) {
                    followeeCount = 0L;
                }
                if (ObjectUtil.isNull(threadCount)) {
                    threadCount = 0L;
                }
                item.setFollowerCount(followerCount);
                item.setFolloweeCount(followeeCount);
                item.setThreadCount(threadCount);
                if (ObjectUtil.isNotNull(req.getLoginUserId())) {
                    item.setFollowedByMe(followSet.contains(item.getId()));
                }
                // 用户等级
                Integer exp = item.getExp();
                UserLevelVo userLevelVo = userLevelService.matchLevelByExp(exp);
                if (ObjectUtil.isNotNull(userLevelVo)) {
                    item.setUserLevelBadge(ImageUtil.completeImageUrl(userLevelVo.getUserLevel().getBadge()));
                }
                // 处理关键字高亮
                String highlight = ContentUtil.processHighlight(item.getName(), req.getKeyword());
                item.setName(highlight);
            }
        }
        return new PageInfo<>(datas);
    }

    @Override
    public String generateInviteCode(Long userId) {
        Users users = usersMapper.selectByPrimaryKey(userId);
        String inviteCode = users.getInviteCode();
        if (StrUtil.isBlank(inviteCode)) {
            Date now = new Date();
            String baseStr = users.getId() + users.getEmail() + users.getName() + now.getTime();
            inviteCode = MD5Util.MD5(baseStr);
            Users update = new Users();
            update.setId(users.getId());
            update.setInviteCode(inviteCode);
            usersMapper.updateByPrimaryKeySelective(update);
        }
        return inviteCode;
    }

    @Override
    public Integer userDrawNumber(Long userId) {
        return usersExtMapper.userDrawNumber(userId);
    }

    @Override
    public String userInviteCode(Long userId) {
        String inviteCode = usersExtMapper.userInviteCode(userId);
        if (StrUtil.isBlank(inviteCode)) {
            inviteCode = generateInviteCode(userId);
        }
        return inviteCode;
    }

    @Override
    public void deductionDrawNumber(Long userId) {
        int i = usersExtMapper.deductionDrawNumber(userId);
        if (i != 1) {
            throw new BizException(CodeCons.ERROR, "扣减抽奖次数失败");
        }
    }

    @Override
    public Users checkInviteCode(AuthReq req) {
        Long activityId = req.getActivityId();
        String inviteCode = req.getInviteCode();
        // 校验活动是否有效
        Activity activity = activityService.verifyActivity(activityId);
        Date now = new Date();
        if (cn.hutool.core.date.DateUtil.compare(now, activity.getActivityTimeStart()) < 0) {
            throw new BizException(CodeCons.ERROR, "活动未开始");
        }
        if (cn.hutool.core.date.DateUtil.compare(now, activity.getActivityTimeEnd()) > 0) {
            throw new BizException(CodeCons.ERROR, "活动已过期");
        }
        ActivityTypeEnum activityTypeEnum = ActivityTypeEnum.checkAndGet(activity.getActivityType());
        if (ActivityTypeEnum.INVITE != activityTypeEnum) {
            throw new BizException(CodeCons.ERROR, "活动类型异常");
        }
        req.setActivity(activity);
        UsersExample example = new UsersExample();
        UsersExample.Criteria criteria = example.createCriteria();
        criteria.andInviteCodeEqualTo(inviteCode);
        List<Users> usersList = usersMapper.selectByExample(example);
        if (CollectionUtil.isEmpty(usersList)) {
            throw new BizException(CodeCons.ERROR, "邀请码不存在");
        }
        return usersList.get(0);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void addDrawNumber(UsersReq req, DrawNumChangeTypeEnum changeTypeEnum) {
        Users users = usersMapper.selectByPrimaryKey(req.getId());
        if (ObjectUtil.isNull(users)) {
            throw new BizException(CodeCons.ERROR, "用户不存在");
        }
        if (req.getAddDrawNumber() <= 0) {
            return;
        }
        Integer before = users.getDrawNumberBalance();
        if (ObjectUtil.isNull(before)) {
            before = 0;
        }
        if (before < 0) {
            throw new BizException(CodeCons.ERROR, "抽奖次数异常");
        }
        int after = before + req.getAddDrawNumber();
        Users update = new Users();
        update.setId(users.getId());
        update.setDrawNumberBalance(after);
        update.setUpdatedAt(new Date());
        usersMapper.updateByPrimaryKeySelective(update);
        // 写日志
        ActivityDrawNumberVo log = new ActivityDrawNumberVo();
        // 如果活动存在, 则关联活动
        if (ObjectUtil.isNotNull(req.getActivity())) {
            log.setActivityId(req.getActivity().getId());
        }
        log.setChangeType(changeTypeEnum);
        log.setUserId(users.getId());
        log.setBeforeChange(before);
        log.setAfterChange(after);
        log.setChangeQuantity(req.getAddDrawNumber());
        log.setRemark("管理员:" + req.getOptLoginName());
        log.setCreatedBy(req.getOptLoginName());
        activityDrawNumberService.addLog(log);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void addNameCard(UsersReq req, NameCardChangeTypeEnum cardChangeTypeEnum) {
        Users users = usersMapper.selectByPrimaryKey(req.getId());
        if (ObjectUtil.isNull(users)) {
            throw new BizException(CodeCons.ERROR, "用户不存在");
        }
        if (req.getNameCardNumber() <= 0) {
            return;
        }
        Integer before = users.getNameCardBalance();
        if (ObjectUtil.isNull(before)) {
            before = 0;
        }
        if (before < 0) {
            throw new BizException(CodeCons.ERROR, "改名卡次数异常");
        }
        int after = before + req.getNameCardNumber();
        Users update = new Users();
        update.setId(users.getId());
        update.setNameCardBalance(after);
        update.setUpdatedAt(new Date());
        usersMapper.updateByPrimaryKeySelective(update);
        // 写日志
        NameCardNumberVo log = new NameCardNumberVo();
        // 如果活动存在, 则关联活动
        if (ObjectUtil.isNotNull(req.getActivity())) {
            log.setActivityId(req.getActivity().getId());
        }
        log.setChangeType(cardChangeTypeEnum);
        log.setUserId(users.getId());
        log.setBeforeChange(before);
        log.setAfterChange(after);
        log.setChangeQuantity(req.getNameCardNumber());
        log.setRemark(req.getRemark());
        log.setCreatedBy(req.getOptLoginName());
        activityNameCardService.addLog(log);
    }

    @Override
    public PageInfo<UserInviteVo> inviteList(UsersReq req) {
        Users users = usersMapper.selectByPrimaryKey(req.getId());
        if (StrUtil.isNotBlank(users.getInviteCode())) {
            UsersExample example = new UsersExample();
            UsersExample.Criteria criteria = example.createCriteria();
            criteria.andInvitedByUserIdEqualTo(users.getId());
            example.setOrderByClause(" id desc");
            PageHelper.startPage(req.getPage(), req.getPagesize());
            List<Users> datas = usersMapper.selectByExample(example);
            PageInfo<Users> pageInfo = new PageInfo<>(datas);
            List<UserInviteVo> rspList = datas.stream().map(this::convertInviteRsp).collect(Collectors.toList());
            return PageUtil.pageInfo2PageRsp(pageInfo, rspList);
        }
        return new PageInfo<>();
    }

    @Override
    public void modifyEmail(UsersReq req) {
        Users oldUser = usersMapper.selectByPrimaryKey(req.getLoginUserId());
        if (ObjectUtil.isNull(oldUser)) {
            throw new BizException(CodeCons.ERROR, "用户不存在");
        }
        Integer isBaned = oldUser.getIsBaned();
        if (ObjectUtil.isNotNull(isBaned) && NumberUtil.equals(isBaned, 1)) {
            throw new BizException(CodeCons.AUTH_FAILED, "账号已被禁用");
        }
        if (ObjectUtil.isNotNull(oldUser.getEmailVerifiedAt())) {
            throw new BizException(CodeCons.ERROR, "您的邮箱已完成验证");
        }
        // 校验邮箱唯一
        Users selectByEmail = this.selectByEmail(req.getEmail());
        if (ObjectUtil.isNotNull(selectByEmail)) {
            if (oldUser.getId().equals(selectByEmail.getId())) {
                throw new BizException(CodeCons.ERROR, "请输入与当前不同的邮箱地址");
            }
            throw new BizException(CodeCons.ERROR, "邮箱已被占用");
        }
        // 更新邮箱地址和邮箱验证时间
        int i = usersExtMapper.modifyEmail(oldUser.getId(), oldUser.getEmail(), req.getEmail());
        if (i != 1) {
            throw new BizException(CodeCons.ERROR, "邮箱更新失败");
        }
    }

    private UserInviteVo convertInviteRsp(Users users) {
        Date emailVerifiedAt = users.getEmailVerifiedAt();
        UserInviteVo resp = new UserInviteVo();
        resp.setId(users.getId());
        resp.setName(users.getName());
        if (ObjectUtil.isNull(emailVerifiedAt)) {
            resp.setEmail("/");
        } else {
            resp.setEmail(users.getEmail());
        }
        resp.setCreateAt(users.getCreatedAt());
        return resp;
    }

   /* @Override
    public void pwdTest(UsersReq req) {
        List<UserPwdVo> usersList = usersExtMapper.pwdTest(req);
        logger.info("usersList:{}", usersList.size());
        for (UserPwdVo item : usersList) {
            Long id = item.getId();
            String pwd = item.getPwd();
            String password = item.getPassword();
            Users update = new Users();
            update.setId(id);
            if (StrUtil.isBlank(pwd)) {
                if (!"-1".equals(password)) {
                    logger.info("正在更新id:{},{},{}", id, pwd, password);
                    update.setPassword("-1");
                    usersMapper.updateByPrimaryKeySelective(update);
                }
            } else {
                logger.info("正在更新id:{},{},{}", id, pwd, password);
                update.setPassword(MD5Util.MD5(MD5Util.MD5(pwd)));
                usersMapper.updateByPrimaryKeySelective(update);
            }
        }
    }*/

    /**
     * 邮箱隐私保护
     *
     * @param email
     * @return
     */
    @Override
    public String hiddenEmail(String email) {
        if (StrUtil.isNotBlank(email)) {
            String[] split = email.split("@", 2);
            if (ObjectUtil.isNotNull(split) && split.length == 2) {
                String s1 = split[0];
                if (s1.length() >= 4) {
                    int length = s1.length();
                    int len = length - 4;
                    String substring = s1.substring(0, len);
                    email = substring + "****" + "@" + split[1];
                } else {
                    email = "****" + "@" + split[1];
                }
            }
        }
        return email;
    }

    /**
     * 验证邮箱key
     *
     * @param uuid
     * @return
     */
    private String buildVerifyEmailKey(String uuid) {
        return "VERIFY_EMAIL:" + uuid;
    }

    /**
     * 根据环境获取接口域名
     *
     * @param request
     * @return
     */
    private String domain(HttpServletRequest request) {
        String apiFlag = "";
        if (env.endsWith("dev")) {
            apiFlag = "";
        } else if (env.endsWith("fat")) {
            apiFlag = "/_lucky_";
        } else if (env.endsWith("online")) {
            apiFlag = "/_lucky_";
        }
        return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + apiFlag;
    }
}
