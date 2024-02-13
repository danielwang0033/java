package com.coin.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.coin.entity.AdminUsers;
import com.coin.entity.AdminUsersExample;
import com.coin.entity.Notifications;
import com.coin.mapper.AdminUsersMapper;
import com.coin.mapper.NotificationsMapper;
import com.coin.mapper.ext.AdminUsersExtMapper;
import com.coin.req.AdminUsersReq;
import com.coin.req.NotificationsReq;
import com.coin.resp.admin.AdminUsersVo;
import com.coin.service.AdminUsersService;
import com.coin.service.constant.CodeCons;
import com.coin.service.exception.BizException;
import com.coin.service.util.MD5Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class AdminUsersServiceImpl implements AdminUsersService {

    private static final Logger logger = LoggerFactory.getLogger(AdminUsersServiceImpl.class);

    @Resource
    private AdminUsersMapper adminUsersMapper;
    @Resource
    private AdminUsersExtMapper adminUsersExtMapper;
    @Resource
    private NotificationsMapper notificationsMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(AdminUsersReq req) {
        AdminUsers oldContest = this.getByUsername(req.getOptLoginName());
        if (ObjectUtil.isNull(oldContest)) {
            throw new BizException(CodeCons.ERROR, "记录不存在");
        }
        Date now = new Date();
        AdminUsers updateAdminUsers = new AdminUsers();
        updateAdminUsers.setId(oldContest.getId());
        updateAdminUsers.setAvatar(req.getAvatar());
        updateAdminUsers.setName(req.getName());
        updateAdminUsers.setUpdatedAt(now);
        if (StrUtil.isNotBlank(req.getPassword()) && StrUtil.isNotBlank(req.getNewPassword())) {
            if (!MD5Util.MD5(req.getPassword()).equals(oldContest.getPassword())) {
                throw new BizException(CodeCons.ERROR, "旧密码不正确");
            }
            updateAdminUsers.setPassword(MD5Util.MD5(req.getNewPassword()));
        }
        adminUsersMapper.updateByPrimaryKeySelective(updateAdminUsers);
    }

    @Override
    public void sendAdminMsg(NotificationsReq req) {
        Date now = new Date();
        Notifications notifications = new Notifications();
        notifications.setId(String.valueOf(UUID.randomUUID()));
        notifications.setType("App\\Notifications\\AdminMessage");
        notifications.setNotifiableType("App\\Models\\User");
        notifications.setNotifiableId(req.getNotifiableId());

        JSONObject data = JSONUtil.parseObj(req.getData());
        notifications.setData(JSONUtil.toJsonStr(data));
        notifications.setCreatedAt(now);
        notifications.setUpdatedAt(now);
        notificationsMapper.insertSelective(notifications);
    }

    @Override
    public AdminUsers getById(Long id) {
        AdminUsers adminUsers = adminUsersMapper.selectByPrimaryKey(id);
        if (ObjectUtil.isNull(adminUsers)) {
            throw new BizException(CodeCons.ERROR, "记录不存在");
        }
        return adminUsers;
    }

    @Override
    public AdminUsers getByUsername(String username) {
        AdminUsersExample example = new AdminUsersExample();
        AdminUsersExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(username);
        List<AdminUsers> users = adminUsersMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(users)) {
            return null;
        }
        return users.get(0);
    }

    @Override
    public List<AdminUsersVo> adminList(AdminUsersReq req) {
        return adminUsersExtMapper.adminList(req);
    }
}
