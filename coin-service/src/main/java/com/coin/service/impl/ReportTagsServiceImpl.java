package com.coin.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.coin.entity.ReportTags;
import com.coin.entity.ReportTagsExample;
import com.coin.mapper.ReportTagsMapper;
import com.coin.req.ReportTagsReq;
import com.coin.resp.report.TagsVo;
import com.coin.service.ReportTagsService;
import com.coin.service.constant.CodeCons;
import com.coin.service.exception.BizException;
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

@Service
public class ReportTagsServiceImpl implements ReportTagsService {

    private static final Logger logger = LoggerFactory.getLogger(ReportTagsServiceImpl.class);

    @Resource
    private ReportTagsMapper reportTagsMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void add(ReportTagsReq req) {
        Date now = new Date();
        ReportTags reportTags = new ReportTags();
        reportTags.setTagContent(req.getTagContent());
        reportTags.setTagImageUrl(req.getTagImageUrl());
        reportTags.setCreatedAt(now);
        reportTags.setUpdatedAt(now);
        reportTagsMapper.insertSelective(reportTags);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(ReportTagsReq req) {
        ReportTags oldContest = reportTagsMapper.selectByPrimaryKey(req.getId());
        if (ObjectUtil.isNull(oldContest)) {
            throw new BizException(CodeCons.ERROR, "记录不存在");
        }
        Date now = new Date();
        ReportTags updateReportTags = new ReportTags();
        updateReportTags.setId(req.getId());
        updateReportTags.setTagContent(req.getTagContent());
        updateReportTags.setTagImageUrl(req.getTagImageUrl());
        updateReportTags.setUpdatedAt(now);
        reportTagsMapper.updateByPrimaryKeySelective(updateReportTags);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(ReportTagsReq req) {
        Long id = req.getId();
        reportTagsMapper.deleteByPrimaryKey(id);
    }

    @Override
    public ReportTags getById(Long id) {
        ReportTags reportTags = reportTagsMapper.selectByPrimaryKey(id);
        if (ObjectUtil.isNull(reportTags)) {
            throw new BizException(CodeCons.ERROR, "记录不存在");
        }
        return reportTags;
    }

    @Override
    public PageInfo<ReportTags> pageList(ReportTagsReq req) {
        ReportTagsExample example = new ReportTagsExample();
        ReportTagsExample.Criteria criteria = example.createCriteria();
        example.setOrderByClause(" id");
        PageHelper.startPage(req.getPage(), req.getPagesize());
        List<ReportTags> datas = reportTagsMapper.selectByExample(example);
        return new PageInfo<>(datas);
    }

    @Override
    public List<TagsVo> getTags() {
        ReportTagsExample example = new ReportTagsExample();
        List<ReportTags> reportTags = reportTagsMapper.selectByExample(example);
        List<TagsVo> result = new ArrayList<>();
        reportTags.forEach(item -> {
            TagsVo vo = new TagsVo();
            vo.setId(item.getId());
            vo.setName(item.getTagContent());
            result.add(vo);
        });
        return result;
    }
}
