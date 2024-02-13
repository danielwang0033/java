package com.coin.service.impl;

import com.coin.entity.MatchVideos;
import com.coin.entity.MatchVideosExample;
import com.coin.mapper.MatchVideosMapper;
import com.coin.req.MatchVideosReq;
import com.coin.service.MatchVideosService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MatchVideosServiceImpl implements MatchVideosService {

    private static final Logger logger = LoggerFactory.getLogger(MatchVideosServiceImpl.class);

    @Resource
    private MatchVideosMapper matchVideosMapper;

    @Override
    public PageInfo<MatchVideos> pageList(MatchVideosReq req) {
        MatchVideosExample example = new MatchVideosExample();
        MatchVideosExample.Criteria criteria = example.createCriteria();
        if (req.getMatchId() != null) {
            criteria.andMatchidEqualTo(req.getMatchId());
        }
        PageHelper.startPage(req.getPage(), req.getPagesize());
        List<MatchVideos> datas = matchVideosMapper.selectByExample(example);
        return new PageInfo<>(datas);
    }
}
