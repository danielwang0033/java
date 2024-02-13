package com.coin.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.coin.entity.ThreadReply;
import com.coin.entity.Threads;
import com.coin.enums.ApiExceptionEnum;
import com.coin.enums.NotificationType;
import com.coin.i18n.I18nUtil;
import com.coin.i18n.LongTextTranslate;
import com.coin.mapper.ThreadReplyMapper;
import com.coin.mapper.ThreadsMapper;
import com.coin.mapper.UsersMapper;
import com.coin.mapper.ext.ThreadReplyExtMapper;
import com.coin.req.NotificationsReq;
import com.coin.req.ThreadReplyReq;
import com.coin.resp.ThreadReplyResp;
import com.coin.resp.dict.ExtraMsgVo;
import com.coin.resp.thread.ThreadReplyVo;
import com.coin.resp.thread.ThreadUserSubjectVo;
import com.coin.resp.thread.ThreadsReplyCountVo;
import com.coin.resp.user.UserSimpleInfoVo;
import com.coin.service.*;
import com.coin.service.asyn.BobiAndExpService;
import com.coin.service.constant.CodeCons;
import com.coin.service.exception.BizException;
import com.coin.service.util.BizUtil;
import com.coin.service.util.ContentUtil;
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
public class ThreadReplyServiceImpl implements ThreadReplyService {

    private static final Logger logger = LoggerFactory.getLogger(ThreadReplyServiceImpl.class);

    @Resource
    private ThreadReplyMapper threadReplyMapper;
    @Resource
    private ThreadsMapper threadsMapper;
    @Resource
    private UsersMapper usersMapper;
    @Resource
    private UsersService usersService;
    @Resource
    private ForumsService forumsService;
    @Resource
    private ThreadReplyExtMapper threadReplyExtMapper;
    @Resource
    private ThreadsService threadsService;
    @Resource
    private NotificationsService notificationsService;
    @Resource
    private BobiAndExpService bobiAndExpService;
    @Resource
    private DictService dictService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(ThreadReplyReq req) {
        ThreadReply oldContest = threadReplyMapper.selectByPrimaryKey(req.getId());
        if (ObjectUtil.isNull(oldContest)) {
            throw new BizException(CodeCons.ERROR, "记录不存在");
        }
        Date now = new Date();
        ThreadReply updateThreadReply = new ThreadReply();
        updateThreadReply.setId(req.getId());
        updateThreadReply.setContent(req.getContent());
        updateThreadReply.setUpdatedAt(now);
        ContentUtil.processThreadReplyContent(updateThreadReply);
        threadReplyMapper.updateByPrimaryKeySelective(updateThreadReply);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(ThreadReplyReq req) {
        ThreadReply threadReply = new ThreadReply();
        threadReply.setId(req.getId());
        threadReply.setDeletedAt(new Date());
        threadReplyMapper.updateByPrimaryKeySelective(threadReply);
    }

    @Override
    public ThreadReply getById(Long id) {
        ThreadReply threadReply = threadReplyMapper.selectByPrimaryKey(id);
        if (ObjectUtil.isNull(threadReply)) {
            throw new BizException(CodeCons.ERROR, "记录不存在");
        }
        return threadReply;
    }

    @Override
    public PageInfo<ThreadReplyResp> pageList(ThreadReplyReq req) {
        PageHelper.startPage(req.getPage(), req.getPagesize());
        List<ThreadReplyResp> datas = threadReplyExtMapper.pageList(req);
        return new PageInfo<>(datas);
    }

    @Override
    public List<ThreadsReplyCountVo> countByThreadIdList(List<Long> threadIdList) {
        return threadReplyExtMapper.countByThreadIdList(threadIdList);
    }

    @Override
    public Integer countByThreadId(Long threadId) {
        Long count = threadReplyExtMapper.countByThreadId(threadId);
        if (ObjectUtil.isNull(count) || count < 0) {
            return 0;
        }
        return Integer.parseInt(count + "");
    }

    @Override
    public PageInfo<ThreadReplyVo> threadReplyByUserId(ThreadReplyReq req) {
        PageHelper.startPage(req.getPage(), req.getPagesize());
        List<ThreadReplyVo> datas = threadReplyExtMapper.threadReplyByUserId(req);
        if (CollectionUtil.isNotEmpty(datas)) {
            datas.forEach(item -> {
                ThreadUserSubjectVo subjectVo = new ThreadUserSubjectVo();
                subjectVo.setName(item.getThreadUserName());
                subjectVo.setAvatar(ImageUtil.completeImageUrl(item.getThreadUserAvatar()));
                subjectVo.setSubject(item.getThreadName());
                item.setThread(subjectVo);

                item.setAvatar(ImageUtil.completeImageUrl(item.getAvatar()));
                item.setThreadUserAvatar(ImageUtil.completeImageUrl(item.getThreadUserAvatar()));
            });
        }
        return new PageInfo<>(datas);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ExtraMsgVo reply(ThreadReplyReq req) {
        Threads threads = threadsService.getById(req.getThreadId());
        Date now = new Date();
        ThreadReply threadReply = new ThreadReply();
        threadReply.setThreadId(threads.getId());
        threadReply.setUserId(req.getLoginUserId());
        threadReply.setContent(req.getContent());
        threadReply.setCreatedAt(now);
        threadReplyMapper.insertSelective(threadReply);

        // 更新评论数
        Integer replyCount = threadsService.addReplyCount(threads.getId());
        if (replyCount >= 15) {
            // 自动加精华
            threadsService.addBestTag(threads);
        }
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
        notificationsReq.setNotifiableId(threads.getUserId());
        notificationsReq.setType("App\\Notifications\\ThreadBeReplied");

        LongTextTranslate longTextTranslateBean = I18nUtil.getLongTextTranslateBean();
        String message = longTextTranslateBean.buildNotificationMessage(NotificationType.THREAD_REPLY, replier.getName(), threads.getSubject());
        notificationsReq.setMessage(message);
        // notificationsReq.setMessage("<span style='color:#0390F6'>" + replier.getName() + "</span> 回复了您的帖子《<span style='color:#0390F6'>" + threads.getSubject() + "</span>》，快去看看吧！");
        Map<String, Object> objectMap = new HashMap<>();
        objectMap.put("replier", replier);
        objectMap.put("thread", threads);
        notificationsReq.setObject(objectMap);
        notificationsService.buildNotification(notificationsReq);

        //加bobi，经验
        return bobiAndExpService.replyOrComment(req.getLoginUserId(), threads.getUserId());
    }

    @Override
    public void modifyThreadReplyById(ThreadReplyReq req) {
        UserSimpleInfoVo loginUser = usersService.selectUserSimpleInfoById(req.getLoginUserId());
        if (ObjectUtil.isNull(loginUser)) {
            throw new BizException(CodeCons.ERROR, "用户不存在");
        }
        if (NumberUtil.equals(loginUser.getIsBanPost(), 1)) {
            throw new BizException(CodeCons.ERROR, ApiExceptionEnum.NO_PERMISSION_TO_POST_THREAD.getMessage());
        }
        ThreadReply threadReply = threadReplyMapper.selectByPrimaryKey(req.getId());
        boolean allowEdit = BizUtil.checkAllowEdit(loginUser, threadReply.getUserId());
        if (!allowEdit) {
            throw new BizException(CodeCons.ERROR, ApiExceptionEnum.NO_PERMISSION_TO_EDIT.getMessage());
        }

        Date now = new Date();
        ThreadReply update = new ThreadReply();
        update.setId(threadReply.getId());
        update.setContent(req.getContent());
        update.setUpdatedAt(now);
        update.setLastModifyAt(now);
        update.setLastModifyUserId(Integer.parseInt(loginUser.getId() + ""));
        threadReplyMapper.updateByPrimaryKeySelective(update);
    }

    @Override
    public void deleteThreadReply(ThreadReplyReq req) {
        UserSimpleInfoVo loginUser = usersService.selectUserSimpleInfoById(req.getLoginUserId());
        if (ObjectUtil.isNull(loginUser)) {
            throw new BizException(CodeCons.ERROR, "用户不存在");
        }
        if (NumberUtil.equals(loginUser.getIsBanPost(), 1)) {
            throw new BizException(CodeCons.ERROR, ApiExceptionEnum.NO_PERMISSION_TO_POST_THREAD.getMessage());
        }
        ThreadReply threadReply = threadReplyMapper.selectByPrimaryKey(req.getId());
        if (ObjectUtil.isNull(threadReply)) {
            throw new BizException(CodeCons.ERROR, "回复记录不存在");
        }
        boolean allowDelete = BizUtil.checkAllowDelete(loginUser, threadReply.getUserId());
        if (!allowDelete) {
            throw new BizException(CodeCons.ERROR, ApiExceptionEnum.NO_PERMISSION_TO_EDIT.getMessage());
        }
        Date now = new Date();
        ThreadReply update = new ThreadReply();
        update.setId(threadReply.getId());
        update.setDeletedAt(now);
        threadReplyMapper.updateByPrimaryKeySelective(update);
    }
}
