package com.coin.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.coin.entity.AdSlots;
import com.coin.entity.AdSlotsExample;
import com.coin.mapper.AdSlotsMapper;
import com.coin.req.AdSlotsReq;
import com.coin.service.AdSlotsService;
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
public class AdSlotsServiceImpl implements AdSlotsService {

    private static final Logger logger = LoggerFactory.getLogger(AdSlotsServiceImpl.class);

    @Resource
    private AdSlotsMapper adSlotsMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void add(AdSlotsReq req) {
        Date now = new Date();
        AdSlots adSlots = new AdSlots();
        adSlots.setName(req.getName());
        adSlots.setAlias(req.getAlias());
        adSlots.setType(req.getType());
        adSlots.setPlatform(req.getPlatform());
        adSlots.setCreatedAt(now);
        adSlots.setUpdatedAt(now);
        adSlotsMapper.insertSelective(adSlots);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(AdSlotsReq req) {
        AdSlots oldContest = adSlotsMapper.selectByPrimaryKey(req.getId());
        if (ObjectUtil.isNull(oldContest)) {
            throw new BizException(CodeCons.ERROR, "记录不存在");
        }
        Date now = new Date();
        AdSlots updateAdSlots = new AdSlots();
        updateAdSlots.setId(req.getId());
        updateAdSlots.setName(req.getName());
        updateAdSlots.setAlias(req.getAlias());
        updateAdSlots.setType(req.getType());
        updateAdSlots.setPlatform(req.getPlatform());
        updateAdSlots.setUpdatedAt(now);
        adSlotsMapper.updateByPrimaryKeySelective(updateAdSlots);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(AdSlotsReq req) {
        Long id = req.getId();
        adSlotsMapper.deleteByPrimaryKey(id);
    }

    @Override
    public AdSlots getById(Long id) {
        AdSlots adSlots = adSlotsMapper.selectByPrimaryKey(id);
        if (ObjectUtil.isNull(adSlots)) {
            throw new BizException(CodeCons.ERROR, "记录不存在");
        }
        return adSlots;
    }

    @Override
    public List<AdSlots> getByPlatform(Integer platform) {
        AdSlotsExample example = new AdSlotsExample();
        AdSlotsExample.Criteria criteria = example.createCriteria();
        criteria.andPlatformEqualTo(platform);
        return adSlotsMapper.selectByExample(example);
    }

    @Override
    public PageInfo<AdSlots> pageList(AdSlotsReq req) {
        AdSlotsExample example = new AdSlotsExample();
        AdSlotsExample.Criteria criteria = example.createCriteria();
        if (req.getPlatform() != null) {
            criteria.andPlatformEqualTo(req.getPlatform());
        }
        example.setOrderByClause(" id");
        PageHelper.startPage(req.getPage(), req.getPagesize());
        List<AdSlots> datas = adSlotsMapper.selectByExample(example);
        return new PageInfo<>(datas);
    }
}
