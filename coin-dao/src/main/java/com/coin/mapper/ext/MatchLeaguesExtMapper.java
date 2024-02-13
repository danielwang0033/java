package com.coin.mapper.ext;

import com.coin.entity.MatchLeagues;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MatchLeaguesExtMapper {

    void insertBatch(@Param("list") List<MatchLeagues> matchLeaguesAddList);
}
