package com.coin.mapper;

import com.coin.entity.MatchVideos;
import com.coin.entity.MatchVideosExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MatchVideosMapper {
    long countByExample(MatchVideosExample example);

    int deleteByExample(MatchVideosExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MatchVideos row);

    int insertSelective(MatchVideos row);

    List<MatchVideos> selectByExampleWithBLOBs(MatchVideosExample example);

    List<MatchVideos> selectByExample(MatchVideosExample example);

    MatchVideos selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") MatchVideos row, @Param("example") MatchVideosExample example);

    int updateByExampleWithBLOBs(@Param("row") MatchVideos row, @Param("example") MatchVideosExample example);

    int updateByExample(@Param("row") MatchVideos row, @Param("example") MatchVideosExample example);

    int updateByPrimaryKeySelective(MatchVideos row);

    int updateByPrimaryKeyWithBLOBs(MatchVideos row);

    int updateByPrimaryKey(MatchVideos row);
}