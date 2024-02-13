package com.coin.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.coin.entity.MatchFbtechs;
import com.coin.entity.MatchFbtechsExample;
import com.coin.mapper.MatchFbtechsMapper;
import com.coin.service.MatchFbtechsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MatchFbtechsServiceImpl implements MatchFbtechsService {

    private static final Logger logger = LoggerFactory.getLogger(MatchFbtechsServiceImpl.class);

    @Resource
    private MatchFbtechsMapper matchFbtechsMapper;

    @Override
    public MatchFbtechs getByMatchId(Integer matchId) {
        MatchFbtechsExample example = new MatchFbtechsExample();
        MatchFbtechsExample.Criteria criteria = example.createCriteria();
        criteria.andMatchidEqualTo(matchId);
        List<MatchFbtechs> matchScores = matchFbtechsMapper.selectByExample(example);
        if (CollectionUtil.isNotEmpty(matchScores)) {
            return matchScores.get(0);
        }
        return null;
    }
}
