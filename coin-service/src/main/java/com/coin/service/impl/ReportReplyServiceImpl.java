package com.coin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.coin.entity.ReportReply;
import com.coin.entity.Reports;
import com.coin.entity.Users;
import com.coin.enums.ApiExceptionEnum;
import com.coin.enums.NotificationType;
import com.coin.i18n.I18nUtil;
import com.coin.i18n.LongTextTranslate;
import com.coin.mapper.ReportReplyMapper;
import com.coin.mapper.ext.ReportReplyExtMapper;
import com.coin.req.NotificationsReq;
import com.coin.req.ReportReplyReq;
import com.coin.req.ReportsReq;
import com.coin.resp.ReportReplyResp;
import com.coin.resp.user.UserSimpleInfoVo;
import com.coin.service.*;
import com.coin.service.constant.CodeCons;
import com.coin.service.exception.BizException;
import com.coin.service.util.BizUtil;
import com.coin.service.util.ImageUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportReplyServiceImpl implements ReportReplyService {

    private static final Logger logger = LoggerFactory.getLogger(ReportReplyServiceImpl.class);

    @Resource
    private ReportReplyMapper reportReplyMapper;
    @Resource
    private ReportReplyExtMapper reportReplyExtMapper;
    @Resource
    private UsersService usersService;
    @Resource
    private DictService dictService;
    @Resource
    private ReportsService reportsService;
    @Resource
    private NotificationsService notificationsService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void add(ReportReplyReq req) {
        usersService.selectUserSimpleInfoById(req.getUserId());
        Reports reports = reportsService.getById(req.getReportId());

        Date now = new Date();
        ReportReply reportReply = new ReportReply();
        reportReply.setReportId(req.getReportId());
        reportReply.setUserId(req.getUserId());
        reportReply.setContent(req.getContent());
        reportReply.setCreatedAt(now);
        reportReplyMapper.insertSelective(reportReply);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(ReportReplyReq req) {
        ReportReply oldContest = reportReplyMapper.selectByPrimaryKey(req.getId());
        if (ObjectUtil.isNull(oldContest)) {
            throw new BizException(CodeCons.ERROR, "记录不存在");
        }
        Date now = new Date();
        ReportReply updateReportReply = new ReportReply();
        updateReportReply.setId(req.getId());
        updateReportReply.setContent(req.getContent());
        updateReportReply.setLastModifyAt(now);
        updateReportReply.setUpdatedAt(now);
        reportReplyMapper.updateByPrimaryKeySelective(updateReportReply);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(ReportReplyReq req) {
        Date now = new Date();
        ReportReply updateReportReply = new ReportReply();
        updateReportReply.setId(req.getId());
        updateReportReply.setDeletedAt(now);
        reportReplyMapper.updateByPrimaryKeySelective(updateReportReply);
    }

    @Override
    public ReportReplyResp getById(Long id) {
        ReportReply reportReply = reportReplyMapper.selectByPrimaryKey(id);
        if (ObjectUtil.isNull(reportReply)) {
            throw new BizException(CodeCons.ERROR, "记录不存在");
        }
        Users users = usersService.selectById(reportReply.getUserId());
        Reports reports = reportsService.getById(reportReply.getReportId());
        ReportReplyResp resp = new ReportReplyResp();
        BeanUtil.copyProperties(reportReply, resp);
        resp.setUserName(users.getName());
        resp.setReportName(reports.getReportedWebsiteName());
        return resp;
    }

    @Override
    public PageInfo<ReportReplyResp> pageList(ReportReplyReq req) {
        PageHelper.startPage(req.getPage(), req.getPagesize());
        List<ReportReplyResp> datas = reportReplyExtMapper.pageList(req);
        return new PageInfo<>(datas);
    }

    @Override
    public void reply(ReportsReq req) {
        UserSimpleInfoVo userSimpleInfoVo = usersService.selectUserSimpleInfoById(req.getLoginUserId());
        if (ObjectUtil.isNull(userSimpleInfoVo)) {
            throw new BizException(CodeCons.ERROR, "用户不存在");
        }
        if (NumberUtil.equals(userSimpleInfoVo.getIsBanPost(), 1)) {
            throw new BizException(CodeCons.ERROR, ApiExceptionEnum.NO_PERMISSION_TO_POST_THREAD.getMessage());
        }
        Reports reports = reportsService.getById(req.getReportId());

        Date now = new Date();
        ReportReply reportReply = new ReportReply();
        reportReply.setReportId(req.getReportId());
        reportReply.setUserId(req.getLoginUserId());
        reportReply.setContent(req.getContent());
        reportReply.setCreatedAt(now);
        reportReplyMapper.insertSelective(reportReply);

        // 发消息
        UserSimpleInfoVo replier = usersService.selectUserSimpleInfoAndLevelById(req.getLoginUserId());
        String avatar = replier.getAvatar();
        if (StrUtil.isNotBlank(avatar)) {
            replier.setAvatar(ImageUtil.completeImageUrl(avatar));
        } else {
            String defaultUserAvatar = dictService.getDefaultUserAvatar();
            replier.setAvatar(ImageUtil.completeImageUrl(defaultUserAvatar));
        }

        NotificationsReq notificationsReq = new NotificationsReq();
        notificationsReq.setNotifiableId(reports.getUserId());
        notificationsReq.setType("App\\Notifications\\ReportBeReplied");

        LongTextTranslate longTextTranslateBean = I18nUtil.getLongTextTranslateBean();
        String message = longTextTranslateBean.buildNotificationMessage(NotificationType.REPORT_REPLY, replier.getName(), reports.getReportedWebsiteName());
        // notificationsReq.setMessage("<span style='color:#0390F6'>" + replier.getName() + "</span> 回复了您的申诉《<span style='color:#0390F6'>" + reports.getReportedWebsiteName() + "</span>》，快去看看吧！");
        notificationsReq.setMessage(message);
        Map<String, Object> objectMap = new HashMap<>();
        objectMap.put("replier", replier);

        Map<String, Object> reportsMap = new HashMap<>();
        reportsMap.put("id", reports.getId());
        reportsMap.put("desc", reports.getDesc());
        objectMap.put("report", reportsMap);

        Map<String, Object> reportReplyMap = new HashMap<>();
        reportReplyMap.put("id", reportReply.getId());
        reportReplyMap.put("content", reportReply.getContent());
        objectMap.put("reportReply", reportReplyMap);

        notificationsReq.setObject(objectMap);
        notificationsService.buildNotification(notificationsReq);
    }

    @Override
    public void deleteById(ReportReplyReq req) {
        ReportReply reportReply = reportReplyMapper.selectByPrimaryKey(req.getId());
        UserSimpleInfoVo loginUser = usersService.selectUserSimpleInfoById(req.getLoginUserId());
        if (ObjectUtil.isNull(loginUser)) {
            throw new BizException(CodeCons.ERROR, "用户不存在");
        }
        if (NumberUtil.equals(loginUser.getIsBanPost(), 1)) {
            throw new BizException(CodeCons.ERROR, ApiExceptionEnum.NO_PERMISSION_TO_POST_THREAD.getMessage());
        }
        boolean allowDelete = BizUtil.checkAllowDelete(loginUser, reportReply.getUserId());
        if (!allowDelete) {
            throw new BizException(CodeCons.ERROR, ApiExceptionEnum.NO_PERMISSION_TO_DELETE.getMessage());
        }

        Date now = new Date();
        ReportReply updateReply = new ReportReply();
        updateReply.setId(reportReply.getId());
        updateReply.setDeletedAt(now);
        updateReply.setUpdatedAt(now);
        reportReplyMapper.updateByPrimaryKeySelective(updateReply);
    }

    @Override
    public void modifyReportReply(ReportReplyReq req) {
        ReportReply reportReply = reportReplyMapper.selectByPrimaryKey(req.getId());
        UserSimpleInfoVo loginUser = usersService.selectUserSimpleInfoById(req.getLoginUserId());
        if (ObjectUtil.isNull(loginUser)) {
            throw new BizException(CodeCons.ERROR, "用户不存在");
        }
        if (NumberUtil.equals(loginUser.getIsBanPost(), 1)) {
            throw new BizException(CodeCons.ERROR, ApiExceptionEnum.NO_PERMISSION_TO_POST_THREAD.getMessage());
        }
        boolean allowEdit = BizUtil.checkAllowEdit(loginUser, reportReply.getUserId());
        if (!allowEdit) {
            throw new BizException(CodeCons.ERROR, ApiExceptionEnum.NO_PERMISSION_TO_EDIT.getMessage());
        }

        Date now = new Date();
        ReportReply updateReply = new ReportReply();
        updateReply.setId(reportReply.getId());
        updateReply.setContent(req.getContent());
        updateReply.setDeletedAt(now);
        reportReplyMapper.updateByPrimaryKeySelective(updateReply);
    }
}
