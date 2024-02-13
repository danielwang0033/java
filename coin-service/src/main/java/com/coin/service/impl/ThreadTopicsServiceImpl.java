package com.coin.service.impl;

import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.coin.entity.Forums;
import com.coin.entity.ThreadTopics;
import com.coin.entity.ThreadTopicsExample;
import com.coin.mapper.ThreadTopicsMapper;
import com.coin.mapper.ext.ThreadTopicsExtMapper;
import com.coin.req.ThreadTopicsReq;
import com.coin.service.ForumsService;
import com.coin.service.ThreadTopicsService;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
public class ThreadTopicsServiceImpl implements ThreadTopicsService {

    private static final Logger logger = LoggerFactory.getLogger(ThreadTopicsServiceImpl.class);

    @Resource
    private ThreadTopicsMapper threadTopicsMapper;
    @Resource
    private ThreadTopicsExtMapper threadTopicsExtMapper;
    @Resource
    private ForumsService forumsService;
    @Resource
    private UsersService usersService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void add(ThreadTopicsReq req) {
        Date now = new Date();
        ThreadTopics threadTopics = new ThreadTopics();
        threadTopics.setForumId(req.getForumId());
        threadTopics.setName(req.getName());
        threadTopics.setPic(req.getPic());
        threadTopics.setColor(req.getColor());
        threadTopics.setDesc(req.getDesc());
        threadTopics.setStatus(req.getStatus());
//        threadTopics.setThreadCount(req.getThreadCount());
        threadTopics.setOnlyAdmin(req.getOnlyAdmin());
//        threadTopics.setReadCount(req.getReadCount());
        threadTopics.setIsHot(req.getIsHot());
        threadTopics.setSort(req.getSort());
        threadTopics.setCreatedAt(now);
        threadTopics.setUpdatedAt(now);
        threadTopicsMapper.insertSelective(threadTopics);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(ThreadTopicsReq req) {
        ThreadTopics oldContest = threadTopicsMapper.selectByPrimaryKey(req.getId());
        if (ObjectUtil.isNull(oldContest)) {
            throw new BizException(CodeCons.ERROR, "记录不存在");
        }
        Date now = new Date();
        ThreadTopics updateThreadTopics = new ThreadTopics();
        updateThreadTopics.setId(req.getId());
        updateThreadTopics.setForumId(req.getForumId());
        updateThreadTopics.setName(req.getName());
        updateThreadTopics.setPic(req.getPic());
        updateThreadTopics.setColor(req.getColor());
        updateThreadTopics.setDesc(req.getDesc());
        updateThreadTopics.setStatus(req.getStatus());
//        updateThreadTopics.setThreadCount(req.getThreadCount());
        updateThreadTopics.setOnlyAdmin(req.getOnlyAdmin());
//        updateThreadTopics.setReadCount(req.getReadCount());
        updateThreadTopics.setIsHot(req.getIsHot());
        //  updateThreadTopics.setSort(req.getSort());
        updateThreadTopics.setUpdatedAt(now);
        threadTopicsMapper.updateByPrimaryKeySelective(updateThreadTopics);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateBatchReadCount() {
        threadTopicsExtMapper.updateBatchReadCount();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(ThreadTopicsReq req) {
        Long id = req.getId();
        threadTopicsMapper.deleteByPrimaryKey(id);
    }

    @Override
    public ThreadTopics getById(Long id) {
        ThreadTopics threadTopics = threadTopicsMapper.selectByPrimaryKey(id);
        if (ObjectUtil.isNull(threadTopics)) {
            throw new BizException(CodeCons.ERROR, "记录不存在");
        }
        return threadTopics;
    }

    @Override
    public List<ThreadTopics> getList(ThreadTopicsReq req) {
        ThreadTopicsExample example = new ThreadTopicsExample();
        ThreadTopicsExample.Criteria criteria = example.createCriteria();
        if (req.getIds() != null) {
            criteria.andIdIn(req.getIds());
        }
        example.setOrderByClause(" id");
        return threadTopicsMapper.selectByExample(example);
    }

    @Override
    public List<ThreadTopics> getByIdSet(Set<Long> topicIdSet) {
        ThreadTopicsExample example = new ThreadTopicsExample();
        ThreadTopicsExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(ListUtil.toList(topicIdSet));
        return threadTopicsMapper.selectByExample(example);
    }

    @Override
    public PageInfo<ThreadTopics> pageList(ThreadTopicsReq req) {
        ThreadTopicsExample example = new ThreadTopicsExample();
        ThreadTopicsExample.Criteria criteria = example.createCriteria();
        if (req.getForumId() != null) {
            criteria.andForumIdEqualTo(req.getForumId());
        }
        example.setOrderByClause(" id");
        PageHelper.startPage(req.getPage(), req.getPagesize());
        List<ThreadTopics> datas = threadTopicsMapper.selectByExample(example);
        return new PageInfo<>(datas);
    }

    @Override
    public List<ThreadTopics> threadTopics(ThreadTopicsReq req) {
        Forums forums = forumsService.selectByAlias(req.getCategoryAlias());
        if (ObjectUtil.isNull(forums)) {
            return new ArrayList<>();
        }
        // 判断是否是管理员
        boolean isAdmin = false;
        ThreadTopicsExample example = new ThreadTopicsExample();
        ThreadTopicsExample.Criteria criteria = example.createCriteria();
        criteria.andForumIdEqualTo(forums.getId());
        criteria.andStatusEqualTo(1);
        if (!isAdmin) {
            criteria.andOnlyAdminEqualTo(0);
        }
        String sort = req.getSortStr();
        if (StrUtil.isNotBlank(sort)) {
            switch (sort) {
                case "read":
                    example.setOrderByClause(" read_count desc, sort asc");
                    break;
                case "sort":
                default:
                    example.setOrderByClause(" sort asc");
                    break;
            }
        } else {
            example.setOrderByClause(" created_at desc");
        }
        List<ThreadTopics> threadTopics = threadTopicsMapper.selectByExample(example);
        threadTopics.forEach(item -> item.setPic(ImageUtil.completeImageUrl(item.getPic())));
        return threadTopics;
    }

    @Override
    public List<ThreadTopics> allTopics(ThreadTopicsReq req) {
        ThreadTopicsExample example = new ThreadTopicsExample();
        ThreadTopicsExample.Criteria criteria = example.createCriteria();
        criteria.andStatusEqualTo(1);
        example.setOrderByClause(" read_count desc, sort asc, created_at desc");
        List<ThreadTopics> threadTopics = threadTopicsMapper.selectByExample(example);
        threadTopics.forEach(item -> item.setPic(ImageUtil.completeImageUrl(item.getPic())));
        return threadTopics;
    }
}
