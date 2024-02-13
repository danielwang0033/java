package com.coin.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONUtil;
import com.coin.entity.ForumSubNavs;
import com.coin.entity.ForumSubNavsExample;
import com.coin.entity.Forums;
import com.coin.entity.ThreadTopics;
import com.coin.mapper.ForumSubNavsMapper;
import com.coin.mapper.ForumsMapper;
import com.coin.req.ForumSubNavsReq;
import com.coin.req.ThreadTopicsReq;
import com.coin.resp.thread.ForumSubNavsVo;
import com.coin.service.ForumSubNavsService;
import com.coin.service.ForumsService;
import com.coin.service.ThreadTopicsService;
import com.coin.service.constant.CodeCons;
import com.coin.service.exception.BizException;
import com.coin.service.util.PageUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ForumSubNavsServiceImpl implements ForumSubNavsService {

    private static final Logger logger = LoggerFactory.getLogger(ForumSubNavsServiceImpl.class);

    @Resource
    private ForumSubNavsMapper forumSubNavsMapper;
    @Resource
    private ForumsMapper forumsMapper;
    @Resource
    private ThreadTopicsService threadTopicsService;
    @Resource
    private ForumsService forumsService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void add(ForumSubNavsReq req) {
        Date now = new Date();
        ForumSubNavs forumSubNavs = new ForumSubNavs();
        forumSubNavs.setForumId(req.getForumId());
        forumSubNavs.setName(req.getName());
        forumSubNavs.setTopics(req.getTopics());
        forumSubNavs.setStatus(req.getStatus());
        forumSubNavs.setSort(req.getSort());
        forumSubNavs.setCreatedAt(now);
        forumSubNavs.setUpdatedAt(now);
        forumSubNavsMapper.insertSelective(forumSubNavs);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(ForumSubNavsReq req) {
        ForumSubNavs oldContest = forumSubNavsMapper.selectByPrimaryKey(req.getId());
        if (ObjectUtil.isNull(oldContest)) {
            throw new BizException(CodeCons.ERROR, "记录不存在");
        }
        Date now = new Date();
        ForumSubNavs updateForumSubNavs = new ForumSubNavs();
        updateForumSubNavs.setId(req.getId());
        updateForumSubNavs.setForumId(req.getForumId());
        updateForumSubNavs.setName(req.getName());
        updateForumSubNavs.setTopics(req.getTopics());
        updateForumSubNavs.setStatus(req.getStatus());
        updateForumSubNavs.setSort(req.getSort());
        updateForumSubNavs.setUpdatedAt(now);
        forumSubNavsMapper.updateByPrimaryKeySelective(updateForumSubNavs);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(ForumSubNavsReq req) {
        Long id = req.getId();
        forumSubNavsMapper.deleteByPrimaryKey(id);
    }

    @Override
    public ForumSubNavs getById(Long id) {
        ForumSubNavs forumSubNavs = forumSubNavsMapper.selectByPrimaryKey(id);
        if (ObjectUtil.isNull(forumSubNavs)) {
            throw new BizException(CodeCons.ERROR, "记录不存在");
        }
        return forumSubNavs;
    }

    @Override
    public PageInfo<ForumSubNavsVo> pageList(ForumSubNavsReq req) {
        ForumSubNavsExample example = new ForumSubNavsExample();
        ForumSubNavsExample.Criteria criteria = example.createCriteria();
        if (req.getForumId() != null) {
            criteria.andForumIdEqualTo(req.getForumId());
        }
        example.setOrderByClause(" id");
        PageHelper.startPage(req.getPage(), req.getPagesize());
        List<ForumSubNavs> datas = forumSubNavsMapper.selectByExample(example);
        PageInfo<ForumSubNavs> page = new PageInfo<>(datas);
        if (CollectionUtil.isNotEmpty(datas)) {
            List<ForumSubNavsVo> result = getForumSubNavsVos(datas);
            if (CollectionUtil.isNotEmpty(result)) {
                return PageUtil.pageInfo2PageRsp(page, result);
            }
        }
        return new PageInfo<>();
    }

    @Override
    public List<ForumSubNavsVo> threadSubNavs(ForumSubNavsReq req) {
        Forums forums = forumsService.selectByAlias(req.getCategoryAlias());
        if (ObjectUtil.isNull(forums)) {
            return new ArrayList<>();
        }
        ForumSubNavsExample example = new ForumSubNavsExample();
        ForumSubNavsExample.Criteria criteria = example.createCriteria();
        criteria.andForumIdEqualTo(forums.getId());
        criteria.andStatusEqualTo(1);
        example.setOrderByClause(" sort asc, created_at desc");
        List<ForumSubNavs> datas = forumSubNavsMapper.selectByExample(example);
        if (CollectionUtil.isNotEmpty(datas)) {
            List<ForumSubNavsVo> result = getForumSubNavsVos(datas);
            if (CollectionUtil.isNotEmpty(result)) {
                return result;
            }
        }
        return new ArrayList<>();
    }

    private List<ForumSubNavsVo> getForumSubNavsVos(List<ForumSubNavs> datas) {
        // forums
        Set<Long> forumIdList = datas.stream().map(ForumSubNavs::getForumId).collect(Collectors.toSet());
        List<Forums> forumsList = forumsService.getByIdList(ListUtil.toList(forumIdList));
        Map<Long, String> forumsMap = forumsList.stream().collect(Collectors.toMap(Forums::getId, Forums::getName));

        // topic
        Set<String> topicList = datas.stream().map(ForumSubNavs::getTopics).collect(Collectors.toSet());
        Set<Long> topicIdSet = new HashSet<>();
        for (String item : topicList) {
            List<String> topicIdArr = JSONUtil.toList(item, String.class);
            for (String topicId : topicIdArr) {
                topicIdSet.add(Long.parseLong(topicId));
            }
        }
        ThreadTopicsReq threadTopicsReq = new ThreadTopicsReq();
        ArrayList<Long> list = ListUtil.toList(topicIdSet);
        threadTopicsReq.setIds(list);
        List<ThreadTopics> threadTopics = threadTopicsService.getList(threadTopicsReq);
        if (CollectionUtil.isNotEmpty(threadTopics)) {
            Map<Long, String> threadTopicMap = threadTopics.stream().collect(Collectors.toMap(ThreadTopics::getId, ThreadTopics::getName));
            return datas.stream().map(item -> this.convertRspVo(item, threadTopicMap, forumsMap)).collect(Collectors.toList());
        }
        return null;
    }

    private ForumSubNavsVo convertRspVo(ForumSubNavs info, Map<Long, String> threadTopicMap, Map<Long, String> forumsMap) {
        ForumSubNavsVo resp = new ForumSubNavsVo();
        BeanUtils.copyProperties(info, resp);
        resp.setForumName(forumsMap.get(resp.getForumId()));

        List<String> topicNames = new ArrayList<>();
        String topicListStr = resp.getTopics();
        List<String> list = JSONUtil.toList(topicListStr, String.class);
        for (String topicId : list) {
            String topicName = threadTopicMap.get(Long.parseLong(topicId));
            topicNames.add(topicName);
        }
        resp.setTopicNames(topicNames);
        return resp;
    }
}
