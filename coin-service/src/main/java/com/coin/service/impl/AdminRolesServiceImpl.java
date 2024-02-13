package com.coin.service.impl;

import cn.hutool.core.util.StrUtil;
import com.coin.entity.AdminRoles;
import com.coin.entity.AdminRolesExample;
import com.coin.mapper.AdminRolesMapper;
import com.coin.req.AdminRolesReq;
import com.coin.service.AdminRolesService;
import com.coin.service.constant.CodeCons;
import com.coin.service.exception.BizException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class AdminRolesServiceImpl implements AdminRolesService {

    private static final Logger logger = LoggerFactory.getLogger(AdminRolesServiceImpl.class);

    @Resource
    private AdminRolesMapper adminRolesMapper;

    @Override
    public PageInfo<AdminRoles> pageList(AdminRolesReq req) {
        AdminRolesExample example = new AdminRolesExample();
        AdminRolesExample.Criteria criteria = example.createCriteria();
        if (StrUtil.isNotBlank(req.getName())) {
            criteria.andNameLike("%" + req.getName() + "%");
        }
        if (StrUtil.isNotBlank(req.getSlug())) {
            criteria.andSlugLike("%" + req.getSlug() + "%");
        }
        example.setOrderByClause(" id");
        PageHelper.startPage(req.getPage(), req.getPagesize());
        List<AdminRoles> datas = adminRolesMapper.selectByExample(example);
        return new PageInfo<>(datas);
    }

    @Override
    public void add(AdminRolesReq req) {
        AdminRoles old = this.getBySlug(req.getSlug());
        if (old != null) {
            throw new BizException(CodeCons.ERROR, "角色标识已存在");
        }
        Date now = new Date();
        AdminRoles newInfo = new AdminRoles();
        newInfo.setCreatedAt(now);
        newInfo.setUpdatedAt(now);
        newInfo.setName(req.getName());
        newInfo.setSlug(req.getSlug());
        adminRolesMapper.insertSelective(newInfo);
    }

    @Override
    public void update(AdminRolesReq req) {
        Date now = new Date();
        AdminRoles updateInfo = new AdminRoles();
        updateInfo.setId(req.getId());
        updateInfo.setName(req.getName());
        updateInfo.setUpdatedAt(now);
        adminRolesMapper.updateByPrimaryKeySelective(updateInfo);
    }

    @Override
    public AdminRoles getBySlug(String slug) {
        AdminRolesExample example = new AdminRolesExample();
        AdminRolesExample.Criteria criteria = example.createCriteria();
        criteria.andSlugEqualTo(slug.trim());
        List<AdminRoles> list = adminRolesMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        return list.get(0);
    }
}
