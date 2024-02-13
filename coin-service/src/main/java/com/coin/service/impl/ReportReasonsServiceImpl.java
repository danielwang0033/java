package com.coin.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.util.ObjectUtil;
import com.coin.entity.ReportReasons;
import com.coin.entity.ReportReasonsExample;
import com.coin.mapper.ReportReasonsMapper;
import com.coin.req.ReportReasonsReq;
import com.coin.resp.report.ReasonsVo;
import com.coin.service.ReportReasonsService;
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
import java.util.Set;

@Service
public class ReportReasonsServiceImpl implements ReportReasonsService {

    private static final Logger logger = LoggerFactory.getLogger(ReportReasonsServiceImpl.class);

    @Resource
    private ReportReasonsMapper reportReasonsMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void add(ReportReasonsReq req) {
        Date now = new Date();
        ReportReasons reportReasons = new ReportReasons();
        reportReasons.setReason(req.getReason());
        reportReasons.setColor(req.getColor());
        reportReasons.setCreatedAt(now);
        reportReasons.setUpdatedAt(now);
        reportReasonsMapper.insertSelective(reportReasons);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(ReportReasonsReq req) {
        ReportReasons oldContest = reportReasonsMapper.selectByPrimaryKey(req.getId());
        if (ObjectUtil.isNull(oldContest)) {
            throw new BizException(CodeCons.ERROR, "记录不存在");
        }
        Date now = new Date();
        ReportReasons updateReportReasons = new ReportReasons();
        updateReportReasons.setId(req.getId());
        updateReportReasons.setReason(req.getReason());
        updateReportReasons.setColor(req.getColor());
        updateReportReasons.setUpdatedAt(now);
        reportReasonsMapper.updateByPrimaryKeySelective(updateReportReasons);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(ReportReasonsReq req) {
        Long id = req.getId();
        reportReasonsMapper.deleteByPrimaryKey(id);
    }

    @Override
    public ReportReasons getById(Long id) {
        ReportReasons reportReasons = reportReasonsMapper.selectByPrimaryKey(id);
        if (ObjectUtil.isNull(reportReasons)) {
            throw new BizException(CodeCons.ERROR, "记录不存在");
        }
        return reportReasons;
    }

    @Override
    public PageInfo<ReportReasons> pageList(ReportReasonsReq req) {
        ReportReasonsExample example = new ReportReasonsExample();
        ReportReasonsExample.Criteria criteria = example.createCriteria();
        example.setOrderByClause(" id");
        PageHelper.startPage(req.getPage(), req.getPagesize());
        List<ReportReasons> datas = reportReasonsMapper.selectByExample(example);
        return new PageInfo<>(datas);
    }

    @Override
    public List<ReasonsVo> getReasons() {
        ReportReasonsExample example = new ReportReasonsExample();
        List<ReportReasons> reportReasons = reportReasonsMapper.selectByExample(example);
        List<ReasonsVo> result = new ArrayList<>();
        if (CollectionUtil.isNotEmpty(reportReasons)) {
            reportReasons.forEach(item -> {
                ReasonsVo vo = new ReasonsVo();
                vo.setId(item.getId());
                vo.setName(item.getReason());
                vo.setColor(item.getColor());
                result.add(vo);
            });
        }
        return result;
    }

    @Override
    public List<ReportReasons> getList(Set<Long> reasonIdSet) {
        ReportReasonsExample example = new ReportReasonsExample();
        ReportReasonsExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(ListUtil.toList(reasonIdSet));
        return reportReasonsMapper.selectByExample(example);
    }
}
