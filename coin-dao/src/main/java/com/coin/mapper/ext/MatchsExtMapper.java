package com.coin.mapper.ext;

import com.coin.entity.Matchs;
import com.coin.req.MatchsReq;
import com.coin.resp.LeaguesResp;
import com.coin.resp.match.MatchIdAndStatusVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MatchsExtMapper {

    void insertBatch(@Param("list") List<Matchs> matchsAddList);

    void updateBatch(@Param("list") List<Matchs> matchsUpdateList);

    List<MatchIdAndStatusVo> selectMatchIdAndStatusId();

    List<LeaguesResp> selectLeague();

    void deleteNoFinished();

    List<Matchs> selectMatchJoinLeague(@Param("req") MatchsReq matchsReq);
}
