package com.coin.mapper;

import com.coin.entity.Likes;
import com.coin.entity.LikesExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LikesMapper {
    long countByExample(LikesExample example);

    int deleteByExample(LikesExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Likes row);

    int insertSelective(Likes row);

    List<Likes> selectByExample(LikesExample example);

    Likes selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") Likes row, @Param("example") LikesExample example);

    int updateByExample(@Param("row") Likes row, @Param("example") LikesExample example);

    int updateByPrimaryKeySelective(Likes row);

    int updateByPrimaryKey(Likes row);
}