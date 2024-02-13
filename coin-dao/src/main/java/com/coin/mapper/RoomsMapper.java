package com.coin.mapper;

import com.coin.entity.Rooms;
import com.coin.entity.RoomsExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoomsMapper {
    long countByExample(RoomsExample example);

    int deleteByExample(RoomsExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Rooms row);

    int insertSelective(Rooms row);

    List<Rooms> selectByExample(RoomsExample example);

    Rooms selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") Rooms row, @Param("example") RoomsExample example);

    int updateByExample(@Param("row") Rooms row, @Param("example") RoomsExample example);

    int updateByPrimaryKeySelective(Rooms row);

    int updateByPrimaryKey(Rooms row);
}