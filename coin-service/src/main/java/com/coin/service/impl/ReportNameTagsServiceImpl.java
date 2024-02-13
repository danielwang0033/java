package com.coin.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.coin.entity.ReportNameTags;
import com.coin.entity.ReportNameTagsExample;
import com.coin.mapper.ReportNameTagsMapper;
import com.coin.req.ReportNameTagsReq;
import com.coin.service.ReportNameTagsService;
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
public class ReportNameTagsServiceImpl implements ReportNameTagsService {

    private static final Logger logger = LoggerFactory.getLogger(ReportNameTagsServiceImpl.class);

    @Resource
    private ReportNameTagsMapper reportNameTagsMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void add(ReportNameTagsReq req) {
        Date now = new Date();
        ReportNameTags reportNameTags = new ReportNameTags();
        reportNameTags.setName(req.getName());
        reportNameTags.setCreatedAt(now);
        reportNameTags.setUpdatedAt(now);
        reportNameTagsMapper.insertSelective(reportNameTags);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(ReportNameTagsReq req) {
        ReportNameTags oldContest = reportNameTagsMapper.selectByPrimaryKey(req.getId());
        if (ObjectUtil.isNull(oldContest)) {
            throw new BizException(CodeCons.ERROR, "记录不存在");
        }
        Date now = new Date();
        ReportNameTags updateReportNameTags = new ReportNameTags();
        updateReportNameTags.setId(req.getId());
        updateReportNameTags.setName(req.getName());
        updateReportNameTags.setUpdatedAt(now);
        reportNameTagsMapper.updateByPrimaryKeySelective(updateReportNameTags);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(ReportNameTagsReq req) {
        Long id = req.getId();
        reportNameTagsMapper.deleteByPrimaryKey(id);
    }

    @Override
    public PageInfo<ReportNameTags> pageList(ReportNameTagsReq req) {
        ReportNameTagsExample example = new ReportNameTagsExample();
        ReportNameTagsExample.Criteria criteria = example.createCriteria();
        example.setOrderByClause(" id");
        PageHelper.startPage(req.getPage(), req.getPagesize());
        List<ReportNameTags> datas = reportNameTagsMapper.selectByExample(example);
        return new PageInfo<>(datas);
    }

    @Override
    public List<ReportNameTags> getNameTags() {
        ReportNameTagsExample example = new ReportNameTagsExample();
        return reportNameTagsMapper.selectByExample(example);
    }
}
