package com.coin.mapper;

import com.coin.entity.ForumSubNavs;
import com.coin.entity.ForumSubNavsExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ForumSubNavsMapper {
    long countByExample(ForumSubNavsExample example);

    int deleteByExample(ForumSubNavsExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ForumSubNavs row);

    int insertSelective(ForumSubNavs row);

    List<ForumSubNavs> selectByExample(ForumSubNavsExample example);

    ForumSubNavs selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") ForumSubNavs row, @Param("example") ForumSubNavsExample example);

    int updateByExample(@Param("row") ForumSubNavs row, @Param("example") ForumSubNavsExample example);

    int updateByPrimaryKeySelective(ForumSubNavs row);

    int updateByPrimaryKey(ForumSubNavs row);
}