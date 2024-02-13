package com.coin.service;

import com.coin.entity.MatchScores;

import java.util.List;

public interface MatchScoresService {

    List<MatchScores> getByMatchId(String matchId);

    List<MatchScores> getByMatchIdBatch(List<String> matchIdList);
}
