package com.coin.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.coin.entity.GoodTags;
import com.coin.entity.GoodTagsExample;
import com.coin.mapper.GoodTagsMapper;
import com.coin.req.GoodTagsReq;
import com.coin.service.GoodTagsService;
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
public class GoodTagsServiceImpl implements GoodTagsService {

    private static final Logger logger = LoggerFactory.getLogger(GoodTagsServiceImpl.class);

    @Resource
    private GoodTagsMapper goodTagsMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void add(GoodTagsReq req) {
        Date now = new Date();
        GoodTags goodTags = new GoodTags();
        goodTags.setTagContent(req.getTagContent());
        goodTags.setTagImageUrl(req.getTagImageUrl());
        goodTags.setCreatedAt(now);
        goodTags.setUpdatedAt(now);
        goodTagsMapper.insertSelective(goodTags);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(GoodTagsReq req) {
        GoodTags oldContest = goodTagsMapper.selectByPrimaryKey(req.getId());
        if (ObjectUtil.isNull(oldContest)) {
            throw new BizException(CodeCons.ERROR, "记录不存在");
        }
        Date now = new Date();
        GoodTags updateGoodTags = new GoodTags();
        updateGoodTags.setId(req.getId());
        updateGoodTags.setTagContent(req.getTagContent());
        updateGoodTags.setTagImageUrl(req.getTagImageUrl());
        updateGoodTags.setUpdatedAt(now);
        goodTagsMapper.updateByPrimaryKeySelective(updateGoodTags);
    }

    @Override
    public List<GoodTags> getAllList(boolean needDel) {
        GoodTagsExample example = new GoodTagsExample();
        GoodTagsExample.Criteria criteria = example.createCriteria();
        if (!needDel) {
            criteria.andCreatedAtIsNull();
        }
        return goodTagsMapper.selectByExample(example);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(GoodTagsReq req) {
        Long id = req.getId();
        goodTagsMapper.deleteByPrimaryKey(id);
    }

    @Override
    public GoodTags getById(Long id) {
        GoodTags goodTags = goodTagsMapper.selectByPrimaryKey(id);
        if (ObjectUtil.isNull(goodTags)) {
            throw new BizException(CodeCons.ERROR, "记录不存在");
        }
        return goodTags;
    }

    @Override
    public PageInfo<GoodTags> pageList(GoodTagsReq req) {
        GoodTagsExample example = new GoodTagsExample();
        GoodTagsExample.Criteria criteria = example.createCriteria();
        example.setOrderByClause(" id");
        PageHelper.startPage(req.getPage(), req.getPagesize());
        List<GoodTags> datas = goodTagsMapper.selectByExample(example);
        return new PageInfo<>(datas);
    }
}
