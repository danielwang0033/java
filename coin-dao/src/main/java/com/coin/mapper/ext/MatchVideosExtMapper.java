package com.coin.mapper.ext;

import com.coin.entity.MatchVideos;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MatchVideosExtMapper {

    void insertBatch(List<MatchVideos> matchVideosAddList);

    void truncateVideos();

    List<String> selectStreamList(@Param("matchId") String matchId);
}
