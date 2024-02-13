package com.coin.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.coin.entity.MatchScores;
import com.coin.entity.MatchScoresExample;
import com.coin.mapper.MatchScoresMapper;
import com.coin.mapper.ext.MatchScoresExtMapper;
import com.coin.service.MatchScoresService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MatchScoresServiceImpl implements MatchScoresService {

    private static final Logger logger = LoggerFactory.getLogger(MatchScoresServiceImpl.class);

    @Resource
    private MatchScoresMapper matchScoresMapper;

    @Resource
    private MatchScoresExtMapper matchScoresExtMapper;

    @Override
    public List<MatchScores> getByMatchId(String matchId) {
        MatchScoresExample example = new MatchScoresExample();
        MatchScoresExample.Criteria criteria = example.createCriteria();
        criteria.andMatchidEqualTo(matchId);
        return matchScoresMapper.selectByExample(example);
    }

    @Override
    public List<MatchScores> getByMatchIdBatch(List<String> matchIdList) {
        if (CollectionUtil.isEmpty(matchIdList)) {
            return null;
        }
        return matchScoresExtMapper.getTeamNameByMatchIdBatch(matchIdList);
    }
}
