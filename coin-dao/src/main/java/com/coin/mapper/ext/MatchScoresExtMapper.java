package com.coin.mapper.ext;

import com.coin.entity.MatchScores;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MatchScoresExtMapper {

    void insertBatch(@Param("list") List<MatchScores> matchScoresAddList);

    List<MatchScores> getTeamNameByMatchIdBatch(@Param("matchIdList") List<String> matchIdList);
}
