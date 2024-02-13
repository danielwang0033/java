package com.coin.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.coin.entity.Followables;
import com.coin.entity.FollowablesExample;
import com.coin.enums.NotificationType;
import com.coin.i18n.I18nUtil;
import com.coin.i18n.LongTextTranslate;
import com.coin.mapper.FollowablesMapper;
import com.coin.mapper.ext.FollowablesExtMapper;
import com.coin.req.FollowablesReq;
import com.coin.req.NotificationsReq;
import com.coin.req.UsersReq;
import com.coin.resp.user.FollowResultVo;
import com.coin.resp.user.UserFolloweeCountVo;
import com.coin.resp.user.UserFollowerCountVo;
import com.coin.resp.user.UserSimpleInfoVo;
import com.coin.service.DictService;
import com.coin.service.FollowablesService;
import com.coin.service.NotificationsService;
import com.coin.service.UsersService;
import com.coin.service.constant.CodeCons;
import com.coin.service.exception.BizException;
import com.coin.service.util.ImageUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

@Service
public class FollowablesServiceImpl implements FollowablesService {

    private static final Logger logger = LoggerFactory.getLogger(FollowablesServiceImpl.class);

    @Resource
    private FollowablesMapper followablesMapper;
    @Resource
    private FollowablesExtMapper followablesExtMapper;
    @Resource
    private NotificationsService notificationsService;
    @Resource
    private UsersService usersService;
    @Resource
    private DictService dictService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void add(FollowablesReq req) {
        Date now = new Date();
        Followables followables = new Followables();
        followables.setUserId(req.getUserId());
        followables.setFollowableType(req.getFollowableType());
        followables.setFollowableId(req.getFollowableId());
        followables.setAcceptedAt(req.getAcceptedAt());
        followables.setCreatedAt(now);
        followables.setUpdatedAt(now);
        followablesMapper.insertSelective(followables);
    }

    @Override
    public List<Followables> getListByUserId(Long userId) {
        FollowablesReq req = new FollowablesReq();
        req.setPage(1);
        req.setPagesize(1000000);
        req.setUserId(userId);
        PageInfo<Followables> page = this.pageList(req);
        return page.getList();
    }

    @Override
    public List<Followables> getListByFTypeAndFId(String ftype, Long fid) {
        FollowablesReq req = new FollowablesReq();
        req.setPage(1);
        req.setPagesize(1000000);
        req.setFollowableType(ftype);
        req.setFollowableId(fid);
        PageInfo<Followables> page = this.pageList(req);
        return page.getList();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(FollowablesReq req) {
        Long id = req.getId();
        followablesMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Followables getById(Long id) {
        Followables followables = followablesMapper.selectByPrimaryKey(id);
        if (ObjectUtil.isNull(followables)) {
            throw new BizException(CodeCons.ERROR, "记录不存在");
        }
        return followables;
    }

    @Override
    public PageInfo<Followables> pageList(FollowablesReq req) {
        FollowablesExample example = new FollowablesExample();
        FollowablesExample.Criteria criteria = example.createCriteria();
        if (req.getUserId() != null) {
            criteria.andUserIdEqualTo(req.getUserId());
        }
        if (req.getFollowableId() != null) {
            criteria.andFollowableIdEqualTo(req.getFollowableId());
        }
        if (StrUtil.isNotBlank(req.getFollowableType())) {
            criteria.andFollowableTypeEqualTo(req.getFollowableType());
        }
        example.setOrderByClause(" id");
        PageHelper.startPage(req.getPage(), req.getPagesize());
        List<Followables> datas = followablesMapper.selectByExample(example);
        return new PageInfo<>(datas);
    }

    @Override
    public FollowResultVo follow(Long userId, Long followId) {
        String type = "App\\Models\\User";
        FollowablesExample example = new FollowablesExample();
        FollowablesExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userId);
        criteria.andFollowableIdEqualTo(followId);
        criteria.andFollowableTypeEqualTo(type);
        List<Followables> datas = followablesMapper.selectByExample(example);
        if (CollectionUtil.isEmpty(datas)) {
            FollowablesReq req = new FollowablesReq();
            req.setUserId(userId);
            req.setFollowableId(followId);
            req.setFollowableType(type);
            req.setAcceptedAt(new Date());
            add(req);
        }
        Long followerCount = this.countFollowerByUserId(followId);
        FollowResultVo resultVo = new FollowResultVo();
        resultVo.setFollowedByMe(true);
        resultVo.setFollowerCount(followerCount);

        UserSimpleInfoVo follower = usersService.selectUserSimpleInfoAndLevelById(userId);
        if (StrUtil.isNotBlank(follower.getAvatar())) {
            follower.setAvatar(ImageUtil.completeImageUrl(follower.getAvatar()));
        } else {
            String defaultUserAvatar = dictService.getDefaultUserAvatar();
            follower.setAvatar(ImageUtil.completeImageUrl(defaultUserAvatar));
        }

        NotificationsReq notificationsReq = new NotificationsReq();
        notificationsReq.setNotifiableId(followId);
        notificationsReq.setType("App\\Notifications\\BeFollowed");

        LongTextTranslate longTextTranslateBean = I18nUtil.getLongTextTranslateBean();
        String message = longTextTranslateBean.buildNotificationMessage(NotificationType.FOLLOW, follower.getName());
        // notificationsReq.setMessage("<span style='color:#0390F6'>" + follower.getName() + "</span> 关注了您！");
        notificationsReq.setMessage(message);
        Map<String, Object> objectMap = new HashMap<>();
        objectMap.put("follower", follower);

        notificationsReq.setObject(objectMap);
        notificationsService.buildNotification(notificationsReq);
        return resultVo;
    }

    @Override
    public FollowResultVo unfollow(Long userId, Long followId) {
        String type = "App\\Models\\User";
        FollowablesExample example = new FollowablesExample();
        FollowablesExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userId);
        criteria.andFollowableIdEqualTo(followId);
        criteria.andFollowableTypeEqualTo(type);
        List<Followables> datas = followablesMapper.selectByExample(example);
        if (CollectionUtil.isNotEmpty(datas)) {
            Followables followables = datas.get(0);
            FollowablesReq req = new FollowablesReq();
            req.setId(followables.getId());
            delete(req);
        }

        Long followerCount = this.countFollowerByUserId(followId);
        FollowResultVo resultVo = new FollowResultVo();
        resultVo.setFollowedByMe(false);
        resultVo.setFollowerCount(followerCount);
        return resultVo;
    }

    @Override
    public Boolean checkAFollowB(Long aUserId, Long bUserid) {
        return ObjectUtil.isNotNull(followablesExtMapper.checkFollow(aUserId, bUserid));
    }

    @Override
    public List<Long> checkAFollowBList(Long aUser, Set<Long> bUserList) {
        return followablesExtMapper.checkAFollowBList(aUser, bUserList);
    }

    @Override
    public PageInfo<Followables> findFollowersByUserId(UsersReq req) {
        String type = "App\\Models\\User";
        FollowablesExample example = new FollowablesExample();
        FollowablesExample.Criteria criteria = example.createCriteria();
        criteria.andFollowableIdEqualTo(req.getId());
        criteria.andFollowableTypeEqualTo(type);
        PageHelper.startPage(req.getPage(), req.getPagesize());
        List<Followables> datas = followablesMapper.selectByExample(example);
        return new PageInfo<>(datas);
    }

    @Override
    public PageInfo<Followables> findFolloweesByUserId(UsersReq req) {
        String type = "App\\Models\\User";
        FollowablesExample example = new FollowablesExample();
        FollowablesExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(req.getId());
        criteria.andFollowableTypeEqualTo(type);
        PageHelper.startPage(req.getPage(), req.getPagesize());
        List<Followables> datas = followablesMapper.selectByExample(example);
        return new PageInfo<>(datas);
    }

    @Override
    public Long countFollowerByUserId(Long userId) {
        return followablesExtMapper.countFollowerByUserId(userId);
    }

    @Override
    public List<UserFollowerCountVo> countFollowerByUserIdList(Set<Long> followableIdSet) {
        return followablesExtMapper.countFollowerByUserIdList(followableIdSet);
    }

    @Override
    public Long countFolloweeByUserId(Long userId) {
        return followablesExtMapper.countFolloweeByUserId(userId);
    }

    @Override
    public List<UserFolloweeCountVo> countFolloweeByUserIdList(Set<Long> followableIdSet) {
        return followablesExtMapper.countFolloweeByUserIdList(followableIdSet);
    }
}
