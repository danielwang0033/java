package com.coin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.coin.entity.*;
import com.coin.enums.ApiExceptionEnum;
import com.coin.enums.NotificationType;
import com.coin.i18n.I18nUtil;
import com.coin.i18n.LongTextTranslate;
import com.coin.mapper.*;
import com.coin.mapper.ext.ReportReplyExtMapper;
import com.coin.mapper.ext.ReportsExtMapper;
import com.coin.mapper.ext.UsersExtMapper;
import com.coin.req.NotificationsReq;
import com.coin.req.ReportsReq;
import com.coin.req.UsersReq;
import com.coin.req.search.SearchReq;
import com.coin.resp.ReportsResp;
import com.coin.resp.report.*;
import com.coin.resp.search.ReportReasonVo;
import com.coin.resp.search.SearchReportVo;
import com.coin.resp.search.UserShortVo;
import com.coin.resp.user.UserLevelVo;
import com.coin.resp.user.UserRelationLevelVo;
import com.coin.resp.user.UserSimpleInfoVo;
import com.coin.service.*;
import com.coin.service.asyn.AsyncHandleService;
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
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ReportsServiceImpl implements ReportsService {

    private static final Logger logger = LoggerFactory.getLogger(ReportsServiceImpl.class);

    @Resource
    private ReportsMapper reportsMapper;
    @Resource
    private UsersMapper usersMapper;
    @Resource
    private UsersExtMapper usersExtMapper;
    @Resource
    private ReportsExtMapper reportsExtMapper;
    @Resource
    private ReportReplyExtMapper reportReplyExtMapper;
    @Resource
    private ReportReasonsMapper reportReasonsMapper;
    @Resource
    private ReportNameTagsMapper reportNameTagsMapper;
    @Resource
    private ReportReasonsService reportReasonsService;
    @Resource
    private UsersService usersService;
    @Resource
    private UserLevelService userLevelService;
    @Resource
    private DictService dictService;
    @Resource
    private FollowablesMapper followablesMapper;
    @Resource
    private NotificationsService notificationsService;
    @Resource
    private FollowablesService followablesService;
    @Resource
    private AsyncHandleService asyncHandleService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(ReportsReq req) {
        Reports oldContest = reportsMapper.selectByPrimaryKey(req.getId());
        if (ObjectUtil.isNull(oldContest)) {
            throw new BizException(CodeCons.ERROR, "记录不存在");
        }

        // 状态(0-未受理,1-已受理处理中,2-处理成功,3-处理失败)
        Integer reqStatus = req.getStatus();
        Integer oldStatus = oldContest.getStatus();
        if (ObjectUtil.isNotNull(reqStatus) && !NumberUtil.equals(reqStatus, oldStatus)) {
            switch (reqStatus) {
                case 1:
                    if (!NumberUtil.equals(oldStatus, 0)) {
                        throw new BizException(CodeCons.ERROR, "不支持的状态变更");
                    }
                    break;
                case 2:
                case 3:
                    if (!NumberUtil.equals(oldStatus, 1)) {
                        throw new BizException(CodeCons.ERROR, "不支持的状态变更");
                    }
                    break;
                default:
                    throw new BizException(CodeCons.ERROR, "不支持的状态变更");
            }
        }
        Date now = new Date();
        Reports updateReports = new Reports();
        updateReports.setId(req.getId());
        updateReports.setReportedWebsiteUrl(req.getReportedWebsiteUrl());
        updateReports.setReportedWebsiteName(req.getReportedWebsiteName());
        updateReports.setReportReasonId(req.getReportReasonId());
        updateReports.setAppealAmount(req.getAppealAmount());
        updateReports.setTags(req.getTags());
        updateReports.setNameTagId(req.getNameTagId());
        updateReports.setStatus(req.getStatus());
        updateReports.setProcess(req.getProcess());
        updateReports.setResult(req.getResult());
        updateReports.setShowHandtag(req.getShowHandtag());
        updateReports.setHandtag(req.getHandtag());
        updateReports.setUpdatedAt(now);
        updateReports.setReportContent(req.getReportContent());
        ContentUtil.processReportsContent(updateReports);
        reportsMapper.updateByPrimaryKeySelective(updateReports);
    }

    @Override
    public void updateReadCount(ReportsReq req) {
        if (req.getReadCount() == 0) {
            return;
        }
        Reports reports = reportsMapper.selectByPrimaryKey(req.getId());
        if (reports.getReadCount() + req.getReadCount() < 0) {
            throw new BizException(CodeCons.ERROR, "更新后值为负");
        }
        Date now = new Date();
        Reports report = new Reports();
        report.setId(req.getId());
        report.setReadCount(reports.getReadCount() + req.getReadCount());
        report.setUpdatedAt(now);
        reportsMapper.updateByPrimaryKeySelective(report);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(ReportsReq req) {
        Date now = new Date();
        Reports reports = new Reports();
        reports.setDeletedAt(now);
        reports.setUpdatedAt(now);
        reports.setId(req.getId());
        reportsMapper.updateByPrimaryKeySelective(reports);
    }

    @Override
    public Reports getById(Long id) {
        Reports reports = reportsMapper.selectByPrimaryKey(id);
        if (ObjectUtil.isNull(reports)) {
            throw new BizException(CodeCons.ERROR, "记录不存在");
        }
        return reports;
    }

    @Override
    public PageInfo<ReportsResp> pageList(ReportsReq req) {
        PageHelper.startPage(req.getPage(), req.getPagesize());
        List<ReportsResp> datas = reportsExtMapper.selectByExample(req);
        // 统计评论
        if (CollectionUtil.isNotEmpty(datas)) {
            List<Long> idList = datas.stream().map(ReportsResp::getId).collect(Collectors.toList());
            List<ReportReplyCountVo> countVoList = reportReplyExtMapper.countByReportId(idList);
            if (CollectionUtil.isNotEmpty(countVoList)) {
                Map<Long, Long> countVoMap = countVoList.stream().collect(Collectors.toMap(ReportReplyCountVo::getReportId, ReportReplyCountVo::getCount));
                datas.forEach(item -> {
                    ContentUtil.processReportsContent(item);
                    Long id = item.getId();
                    Long count = countVoMap.get(id);
                    if (ObjectUtil.isNotNull(count)) {
                        item.setReplyCount(Integer.parseInt(count + ""));
                    }
                });
            }
        }
        return new PageInfo<>(datas);
    }

    @Override
    public PageInfo<ReportsVoResp> lists(ReportsReq req) {
        ReportsExample example = new ReportsExample();
        ReportsExample.Criteria criteria = example.createCriteria();
        if (ObjectUtil.isNotNull(req.getReportReasonId())) {
            criteria.andReportReasonIdEqualTo(req.getReportReasonId());
        }
        if (StrUtil.isNotBlank(req.getTag())) {
            criteria.andTagsLike("%" + req.getTag() + "%");
        }
        if (ObjectUtil.isNotNull(req.getNameTagId())) {
            criteria.andNameTagIdEqualTo(req.getNameTagId());
        }
        if (StrUtil.isNotBlank(req.getReportedWebsiteName())) {
            criteria.andReportedWebsiteNameEqualTo(req.getReportedWebsiteName());
        }
        if (ObjectUtil.isNotNull(req.getStatus())) {
            if (NumberUtil.equals(req.getStatus(), -2)) {
                criteria.andStatusNotEqualTo(2);
            } else if (req.getStatus() >= 0 && req.getStatus() <= 3) {
                criteria.andStatusEqualTo(req.getStatus());
            }
        }
        criteria.andDeletedAtIsNull();
        example.setOrderByClause(" created_at desc");
        PageHelper.startPage(req.getPage(), req.getPagesize());
        List<Reports> datas = reportsMapper.selectByExample(example);
        return getReportsVoRespPageInfo(datas);
    }

    @Override
    public PageInfo<ReportsVoResp> listsByUser(ReportsReq req) {
        ReportsExample example = new ReportsExample();
        ReportsExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(req.getUserId());
        example.setOrderByClause(" created_at desc");
        PageHelper.startPage(req.getPage(), req.getPagesize());
        List<Reports> datas = reportsMapper.selectByExample(example);
        return getReportsVoRespPageInfo(datas);
    }

    private PageInfo<ReportsVoResp> getReportsVoRespPageInfo(List<Reports> datas) {
        if (CollectionUtil.isNotEmpty(datas)) {
            Set<Long> reasonIdSet = datas.stream().map(Reports::getReportReasonId).collect(Collectors.toSet());
            List<ReportReasons> reportReasons = reportReasonsService.getList(reasonIdSet);
            Map<Long, ReportReasons> reasonsMap = reportReasons.stream().collect(Collectors.toMap(ReportReasons::getId, Function.identity()));

            Set<Long> userIdSet = datas.stream().map(Reports::getUserId).collect(Collectors.toSet());
            List<UserSimpleInfoVo> userSimpleInfoVos = usersService.selectUserSimpleInfoByIdList(userIdSet);
            Map<Long, UserSimpleInfoVo> userMap = userSimpleInfoVos.stream().collect(Collectors.toMap(UserSimpleInfoVo::getId, Function.identity()));

            PageInfo<Reports> page = new PageInfo<>(datas);
            String defaultUserAvatar = dictService.getDefaultUserAvatar();
            List<ReportsVoResp> rspList = datas.stream().map(item -> this.convertVo(item, reasonsMap, userMap, defaultUserAvatar)).collect(Collectors.toList());
            return PageUtil.pageInfo2PageRsp(page, rspList);
        }
        return new PageInfo<>();
    }

    private ReportsVoResp convertVo(Reports info, Map<Long, ReportReasons> reasonsMap, Map<Long, UserSimpleInfoVo> userMap, String defaultUserAvatar) {
        ContentUtil.processReportsContent(info);
        ReportsVoResp resp = new ReportsVoResp();
        BeanUtils.copyProperties(info, resp, "thumbs");
        if (ObjectUtil.isNotNull(info.getThumbs())) {
            resp.setThumbs(JSONUtil.toList(info.getThumbs(), String.class));
        } else {
            resp.setThumbs(new ArrayList<>());
        }

        ReportReasons reportReasons = reasonsMap.get(info.getReportReasonId());
        if (ObjectUtil.isNotNull(reportReasons)) {
            ReasonsVo reasonsVo = new ReasonsVo();
            reasonsVo.setId(reportReasons.getId());
            reasonsVo.setName(reportReasons.getReason());
            reasonsVo.setColor(reportReasons.getColor());
            resp.setReason(reasonsVo);
        }

        UserSimpleInfoVo userSimpleInfoVo = userMap.get(info.getUserId());
        if (ObjectUtil.isNotNull(userSimpleInfoVo)) {
            resp.setUserName(userSimpleInfoVo.getName());
            if (StrUtil.isNotBlank(userSimpleInfoVo.getAvatar())) {
                resp.setAvatar(ImageUtil.completeImageUrl(userSimpleInfoVo.getAvatar()));
            } else {
                resp.setAvatar(ImageUtil.completeImageUrl(defaultUserAvatar));
            }
            Integer exp = userSimpleInfoVo.getExp();
            UserLevelVo userLevelVo = userLevelService.matchLevelByExp(exp);
            UserRelationLevelVo userRelationLevelVo = BizUtil.buildLevelVo(userLevelVo);
            resp.setLevel(userRelationLevelVo);
        }

        resp.setAmount(info.getAppealAmount());
        resp.setWebsiteName(info.getReportedWebsiteName());
        resp.setWebsiteUrl(info.getReportedWebsiteUrl());
        resp.setPostTime(info.getCreatedAt());
        return resp;
    }

    @Override
    public ReportsDetailResp getReportDetail(ReportsReq req) {
        Reports reports = getById(req.getId());
        ContentUtil.processReportsContent(reports);
        ReportsDetailResp resp = BeanUtil.copyProperties(reports, ReportsDetailResp.class, "tags");
        resp.setContent(reports.getReportContent());
        resp.setWebsiteName(reports.getReportedWebsiteName());
        resp.setWebsiteUrl(reports.getReportedWebsiteUrl());
        ReportReasons reportReasons = reportReasonsService.getById(reports.getReportReasonId());
        if (ObjectUtil.isNotNull(reportReasons)) {
            ReasonsVo reasonsVo = new ReasonsVo();
            reasonsVo.setId(reportReasons.getId());
            reasonsVo.setName(reportReasons.getReason());
            reasonsVo.setColor(reportReasons.getColor());
            resp.setReason(reasonsVo);
        }
        Long userId = reports.getUserId();
        UserSimpleInfoVo userSimpleInfoVo = usersService.selectUserSimpleInfoById(userId);
        if (ObjectUtil.isNotNull(userSimpleInfoVo)) {
            resp.setUserName(userSimpleInfoVo.getName());
        }
        String tags = reports.getTags();
        if (StrUtil.isNotBlank(tags)) {
            resp.setTags(JSONUtil.toList(tags, String.class));
        } else {
            resp.setTags(new ArrayList<>());
        }
        // 累计访问数
        asyncHandleService.addReportVisitsAmount(resp.getId());
        return resp;
    }

    @Override
    public List<ReportsStatistics> getStatistics(ReportsReq req) {
        List<ReportsStatistics> statistics = reportsExtMapper.getStatistics();
        if (CollectionUtil.isEmpty(statistics)) {
            return new ArrayList<>();
        }
        long sum = statistics.stream().mapToLong(ReportsStatistics::getCount).sum();
        BigDecimal sumBd = new BigDecimal(sum);
        ReportReasonsExample example = new ReportReasonsExample();
        List<ReportReasons> reportReasons = reportReasonsMapper.selectByExample(example);
        Map<Long, ReportReasons> map = reportReasons.stream().collect(Collectors.toMap(ReportReasons::getId, Function.identity()));
        statistics.forEach(item -> {
            Long id = item.getId();
            ReportReasons report = map.get(id);
            if (ObjectUtil.isNotNull(report)) {
                item.setName(report.getReason());
                BigDecimal result = new BigDecimal(item.getCount()).divide(sumBd, 8, RoundingMode.DOWN)
                        .setScale(8, RoundingMode.DOWN);

                result = result.multiply(new BigDecimal(100)).setScale(2, RoundingMode.DOWN);
                item.setPercentage(result.toPlainString());
            }
        });
        return statistics;
    }

    @Override
    public List<ReportsLeaderboard> getLeaderBoard(ReportsReq req) {
        String isSucceed = req.getIssucceed();
        if (StrUtil.isBlank(isSucceed)) {
            req.setSuccessType(0);
        } else {
            req.setSuccessType(Integer.parseInt(isSucceed));
        }

        List<ReportsLeaderboard> leaderBoard = reportsExtMapper.getLeaderBoard(req);
        if (CollectionUtil.isEmpty(leaderBoard)) {
            return new ArrayList<>();
        }
        ReportNameTagsExample example = new ReportNameTagsExample();
        List<ReportNameTags> reportNameTags = reportNameTagsMapper.selectByExample(example);
        Map<Long, ReportNameTags> map = reportNameTags.stream().collect(Collectors.toMap(ReportNameTags::getId, Function.identity()));
        List<ReportsLeaderboard> result = new ArrayList<>();
        leaderBoard.forEach(item -> {
            Long id = item.getId();
            ReportNameTags nameTags = map.get(id);
            if (ObjectUtil.isNotNull(nameTags)) {
                item.setName(nameTags.getName());
                result.add(item);
            }
        });
        return result;
    }

    @Override
    public Long countByUserId(Long userId) {
        return reportsExtMapper.countByUserId(userId);
    }

    @Override
    public Long newReport(ReportsReq req) {
        Date now = new Date();
        Reports reports = new Reports();
        reports.setUserId(req.getLoginUserId());
        reports.setReportedWebsiteUrl(req.getWebsiteUrl());
        reports.setReportedWebsiteName(req.getWebsiteName());
        reports.setReportReasonId(req.getReasonId());
        reports.setAppealAmount(req.getAmount());
        reports.setReportContent(req.getContent());
        reports.setCreatedAt(now);
        ContentUtil.processReportsContent(reports);
        reportsMapper.insertSelective(reports);

        UsersReq usersReq = new UsersReq();
        usersReq.setPage(1);
        usersReq.setPagesize(1000);
        usersReq.setId(reports.getUserId());
        PageInfo<Followables> followersByUserId = followablesService.findFollowersByUserId(usersReq);
        if (ObjectUtil.isNotNull(followersByUserId) && CollectionUtil.isNotEmpty(followersByUserId.getList())) {
            UserSimpleInfoVo users = usersService.selectUserSimpleInfoAndLevelById(req.getLoginUserId());
            String avatar = users.getAvatar();
            if (StrUtil.isNotBlank(avatar)) {
                users.setAvatar(ImageUtil.completeImageUrl(avatar));
            } else {
                String defaultUserAvatar = dictService.getDefaultUserAvatar();
                users.setAvatar(ImageUtil.completeImageUrl(defaultUserAvatar));
            }
            List<Followables> datas = followersByUserId.getList();

            LongTextTranslate longTextTranslateBean = I18nUtil.getLongTextTranslateBean();
            datas.forEach(followables -> {
                NotificationsReq notificationsReq = new NotificationsReq();
                notificationsReq.setNotifiableId(followables.getUserId());
                notificationsReq.setType("App\\Notifications\\FolloweePostedReport");
                String message = longTextTranslateBean.buildNotificationMessage(NotificationType.NEW_REPORT, users.getName(), reports.getReportedWebsiteName());
                //notificationsReq.setMessage("您关注的用户 <span style='color:#0390F6'>" + users.getName() + "</span> 发表了申诉《<span style='color:#0390F6'>" + reports.getReportedWebsiteName() + "</span>》,快去围观吧！");
                notificationsReq.setMessage(message);
                Map<String, Object> objectMap = new HashMap<>();
                objectMap.put("poster", users);

                Map<String, Object> reportMap = new HashMap<>();
                reportMap.put("id", reports.getId());
                reportMap.put("desc", reports.getDesc());

                objectMap.put("report", reportMap);
                notificationsReq.setObject(objectMap);
                try {
                    notificationsService.buildNotification(notificationsReq);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
        }
        return reports.getId();
    }

    @Override
    public void deleteById(ReportsReq req) {
        Reports reports = getById(req.getReportId());
        UserSimpleInfoVo loginUser = usersService.selectUserSimpleInfoById(req.getLoginUserId());
        if (ObjectUtil.isNull(loginUser)) {
            throw new BizException(CodeCons.ERROR, "用户不存在");
        }
        if (NumberUtil.equals(loginUser.getIsBanPost(), 1)) {
            throw new BizException(CodeCons.ERROR, ApiExceptionEnum.NO_PERMISSION_TO_POST_THREAD.getMessage());
        }
        boolean allowDelete = BizUtil.checkAllowDelete(loginUser, reports.getUserId());
        if (!allowDelete) {
            throw new BizException(CodeCons.ERROR, ApiExceptionEnum.NO_PERMISSION_TO_DELETE.getMessage());
        }

        Date now = new Date();
        Reports report = new Reports();
        report.setId(reports.getId());
        report.setDeletedAt(now);
        report.setUpdatedAt(now);
        reportsMapper.updateByPrimaryKeySelective(report);
    }

    @Override
    public PageInfo<SearchReportVo> searchReport(SearchReq req) {
        if (StrUtil.isBlank(req.getKeyword().trim())) {
            return new PageInfo<>();
        }
        PageHelper.startPage(req.getPage(), req.getPagesize());
        List<SearchReportVo> datas = reportsExtMapper.searchReport(req.getKeyword());
        if (CollectionUtil.isNotEmpty(datas)) {
            Set<Long> userIdList = datas.stream().map(SearchReportVo::getUserId).collect(Collectors.toSet());
            List<UserSimpleInfoVo> userSimpleInfoVos = usersService.selectUserSimpleInfoByIdList(userIdList);
            Map<Long, UserSimpleInfoVo> userSimpleInfoVoMap = userSimpleInfoVos.stream().collect(Collectors.toMap(UserSimpleInfoVo::getId, Function.identity()));

            Set<Long> reasonIdList = datas.stream().map(SearchReportVo::getReportReasonId).collect(Collectors.toSet());
            List<ReportReasons> reportReasonsList = reportReasonsService.getList(reasonIdList);
            Map<Long, ReportReasons> forumsMap = reportReasonsList.stream().collect(Collectors.toMap(ReportReasons::getId, Function.identity()));
            String defaultUserAvatar = dictService.getDefaultUserAvatar();

            for (SearchReportVo item : datas) {
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
                Long reasonId = item.getReportReasonId();
                ReportReasons reportReasons = forumsMap.get(reasonId);
                if (ObjectUtil.isNotNull(reportReasons)) {
                    ReportReasonVo reportReasonVo = new ReportReasonVo();
                    reportReasonVo.setId(reportReasons.getId());
                    reportReasonVo.setColor(reportReasons.getColor());
                    reportReasonVo.setReason(reportReasons.getReason());
                    item.setReportReason(reportReasonVo);
                }
                item.setPostTime(item.getCreatedAt());

                // 处理关键字高亮
                String highlight1 = ContentUtil.processHighlight(item.getContent(), req.getKeyword());
                item.setContent(highlight1);
            }
        }
        return new PageInfo<>(datas);
    }
}
