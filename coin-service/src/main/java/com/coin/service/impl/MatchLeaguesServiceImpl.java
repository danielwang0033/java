package com.coin.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.coin.entity.MatchLeagues;
import com.coin.entity.MatchLeaguesExample;
import com.coin.mapper.MatchLeaguesMapper;
import com.coin.req.MatchLeaguesReq;
import com.coin.service.MatchLeaguesService;
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
public class MatchLeaguesServiceImpl implements MatchLeaguesService {

    private static final Logger logger = LoggerFactory.getLogger(MatchLeaguesServiceImpl.class);

    @Resource
    private MatchLeaguesMapper matchLeaguesMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void add(MatchLeaguesReq req) {
        Date now = new Date();
        MatchLeagues matchLeagues = new MatchLeagues();
        matchLeagues.setLeagueId(req.getLeagueId());
        matchLeagues.setLeagueName(req.getLeagueName());
        matchLeagues.setLeagueLogo(req.getLeagueLogo());
        matchLeagues.setCreatedAt(now);
        matchLeagues.setUpdatedAt(now);
        matchLeagues.setType(req.getType());
        matchLeaguesMapper.insertSelective(matchLeagues);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(MatchLeaguesReq req) {
        MatchLeagues oldContest = matchLeaguesMapper.selectByPrimaryKey(req.getId());
        if (ObjectUtil.isNull(oldContest)) {
            throw new BizException(CodeCons.ERROR, "记录不存在");
        }
        Date now = new Date();
        MatchLeagues updateMatchLeagues = new MatchLeagues();
        updateMatchLeagues.setId(req.getId());
        updateMatchLeagues.setUpdatedAt(now);
        updateMatchLeagues.setSort(req.getSort());
        matchLeaguesMapper.updateByPrimaryKeySelective(updateMatchLeagues);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(MatchLeaguesReq req) {
        Long id = req.getId();
        matchLeaguesMapper.deleteByPrimaryKey(id);
    }

    @Override
    public MatchLeagues getById(Long id) {
        MatchLeagues matchLeagues = matchLeaguesMapper.selectByPrimaryKey(id);
        if (ObjectUtil.isNull(matchLeagues)) {
            throw new BizException(CodeCons.ERROR, "记录不存在");
        }
        return matchLeagues;
    }

    @Override
    public PageInfo<MatchLeagues> pageList(MatchLeaguesReq req) {
        MatchLeaguesExample example = new MatchLeaguesExample();
        MatchLeaguesExample.Criteria criteria = example.createCriteria();
        if (ObjectUtil.isNotNull(req.getLeagueName())) {
            criteria.andLeagueNameLike("%" + req.getLeagueName() + "%");
        }
        example.setOrderByClause("sort asc");
        PageHelper.startPage(req.getPage(), req.getPagesize());
        List<MatchLeagues> datas = matchLeaguesMapper.selectByExample(example);
        return new PageInfo<>(datas);
    }
}
