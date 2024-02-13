package com.coin.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.coin.entity.MatchBbtechs;
import com.coin.entity.MatchBbtechsExample;
import com.coin.mapper.MatchBbtechsMapper;
import com.coin.service.MatchBbtechsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MatchBbtechsServiceImpl implements MatchBbtechsService {

    private static final Logger logger = LoggerFactory.getLogger(MatchBbtechsServiceImpl.class);

    @Resource
    private MatchBbtechsMapper matchBbtechsMapper;

    @Override
    public MatchBbtechs getByMatchId(Integer matchId) {
        MatchBbtechsExample example = new MatchBbtechsExample();
        MatchBbtechsExample.Criteria criteria = example.createCriteria();
        criteria.andMatchidEqualTo(matchId);
        List<MatchBbtechs> matchScores = matchBbtechsMapper.selectByExample(example);
        if (CollectionUtil.isNotEmpty(matchScores)) {
            return matchScores.get(0);
        }
        return null;
    }
}
