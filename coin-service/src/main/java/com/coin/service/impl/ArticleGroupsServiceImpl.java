package com.coin.service.impl;

import cn.hutool.core.collection.ListUtil;
import com.coin.entity.ArticleGroups;
import com.coin.entity.ArticleGroupsExample;
import com.coin.mapper.ArticleGroupsMapper;
import com.coin.req.ArticleGroupsReq;
import com.coin.service.ArticleGroupsService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
public class ArticleGroupsServiceImpl implements ArticleGroupsService {

    private static final Logger logger = LoggerFactory.getLogger(ArticleGroupsServiceImpl.class);

    @Resource
    private ArticleGroupsMapper articleGroupsMapper;

    @Override
    public List<ArticleGroups> getList(ArticleGroupsReq req) {
        ArticleGroupsExample example = new ArticleGroupsExample();
        ArticleGroupsExample.Criteria criteria = example.createCriteria();
        example.setOrderByClause(" id");
        return articleGroupsMapper.selectByExample(example);
    }

    @Override
    public PageInfo<ArticleGroups> pageList(ArticleGroupsReq req) {
        ArticleGroupsExample example = new ArticleGroupsExample();
        ArticleGroupsExample.Criteria criteria = example.createCriteria();
        example.setOrderByClause(" id");
        PageHelper.startPage(req.getPage(), req.getPagesize());
        List<ArticleGroups> datas = articleGroupsMapper.selectByExample(example);
        return new PageInfo<>(datas);
    }

    @Override
    public ArticleGroups getById(Long id) {
        return articleGroupsMapper.selectByPrimaryKey(id);
    }

    @Override
    public void add(ArticleGroupsReq req) {
        Date now = new Date();
        ArticleGroups articleGroup = new ArticleGroups();
        articleGroup.setName(req.getName());
        articleGroup.setAlias(req.getAlias());
        articleGroup.setDescription(req.getDescription());
        articleGroup.setCreatedAt(now);
        articleGroup.setUpdatedAt(now);
        articleGroupsMapper.insertSelective(articleGroup);
    }

    @Override
    public void update(ArticleGroupsReq req) {
        Date now = new Date();
        ArticleGroups articleGroup = new ArticleGroups();
        articleGroup.setId(req.getId());
        articleGroup.setName(req.getName());
        articleGroup.setDescription(req.getDescription());
        articleGroup.setUpdatedAt(now);
        articleGroupsMapper.updateByPrimaryKeySelective(articleGroup);
    }

    @Override
    public List<ArticleGroups> findByIdList(Set<Long> artGroupIdSet) {
        ArticleGroupsExample example = new ArticleGroupsExample();
        ArticleGroupsExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(ListUtil.toList(artGroupIdSet));
        return articleGroupsMapper.selectByExample(example);
    }
}
