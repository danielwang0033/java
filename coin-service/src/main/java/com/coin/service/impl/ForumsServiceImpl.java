package com.coin.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.coin.entity.Forums;
import com.coin.entity.ForumsExample;
import com.coin.mapper.ForumsMapper;
import com.coin.req.ForumsReq;
import com.coin.service.ForumsService;
import com.coin.service.constant.CodeCons;
import com.coin.service.exception.BizException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class ForumsServiceImpl implements ForumsService {

    private static final Logger logger = LoggerFactory.getLogger(ForumsServiceImpl.class);

    @Resource
    private ForumsMapper forumsMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void add(ForumsReq req) {
        Date now = new Date();
        Forums forums = new Forums();
        forums.setName(req.getName());
        forums.setAlias(req.getAlias());
        forums.setStatus(req.getStatus());
        forums.setLabelColor(req.getLabelColor());
//        forums.setSubjectCount(req.getSubjectCount());
        forums.setCreatedAt(now);
        forums.setUpdatedAt(now);
        forumsMapper.insertSelective(forums);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(ForumsReq req) {
        Forums oldContest = forumsMapper.selectByPrimaryKey(req.getId());
        if (ObjectUtil.isNull(oldContest)) {
            throw new BizException(CodeCons.ERROR, "记录不存在");
        }
        Date now = new Date();
        Forums updateForums = new Forums();
        updateForums.setId(req.getId());
        updateForums.setName(req.getName());
//        updateForums.setAlias(req.getAlias());
        updateForums.setStatus(req.getStatus());
        updateForums.setLabelColor(req.getLabelColor());
//        updateForums.setSubjectCount(req.getSubjectCount());
        updateForums.setUpdatedAt(now);
        forumsMapper.updateByPrimaryKeySelective(updateForums);
    }

    @Override
    public Forums getById(Long id) {
        Forums forums = forumsMapper.selectByPrimaryKey(id);
        if (ObjectUtil.isNull(forums)) {
            throw new BizException(CodeCons.ERROR, "记录不存在");
        }
        return forums;
    }

    @Override
    public PageInfo<Forums> pageList(ForumsReq req) {
        ForumsExample example = new ForumsExample();
        ForumsExample.Criteria criteria = example.createCriteria();
        if (req.getId() != null) {
            criteria.andIdEqualTo(req.getId());
        }
        example.setOrderByClause(" id");
        PageHelper.startPage(req.getPage(), req.getPagesize());
        List<Forums> datas = forumsMapper.selectByExample(example);
        return new PageInfo<>(datas);
    }

    @Override
    public List<Forums> category() {
        ForumsExample example = new ForumsExample();
        ForumsExample.Criteria criteria = example.createCriteria();
        criteria.andStatusEqualTo(1);
        return forumsMapper.selectByExample(example);
    }

    @Override
    public Forums selectByAlias(String categoryAlias) {
        ForumsExample example = new ForumsExample();
        ForumsExample.Criteria criteria = example.createCriteria();
        criteria.andAliasEqualTo(categoryAlias);
        List<Forums> list = forumsMapper.selectByExample(example);
        if (CollectionUtil.isNotEmpty(list)) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public List<Forums> getByIdList(List<Long> forumIdList) {
        ForumsExample example = new ForumsExample();
        ForumsExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(forumIdList);
        return forumsMapper.selectByExample(example);
    }
}
