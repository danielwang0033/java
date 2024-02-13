package com.coin.mapper;

import com.coin.entity.Forums;
import com.coin.entity.ForumsExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ForumsMapper {
    long countByExample(ForumsExample example);

    int deleteByExample(ForumsExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Forums row);

    int insertSelective(Forums row);

    List<Forums> selectByExample(ForumsExample example);

    Forums selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") Forums row, @Param("example") ForumsExample example);

    int updateByExample(@Param("row") Forums row, @Param("example") ForumsExample example);

    int updateByPrimaryKeySelective(Forums row);

    int updateByPrimaryKey(Forums row);
}