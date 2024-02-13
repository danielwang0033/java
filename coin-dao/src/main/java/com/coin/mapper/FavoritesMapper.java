package com.coin.mapper;

import com.coin.entity.Favorites;
import com.coin.entity.FavoritesExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FavoritesMapper {
    long countByExample(FavoritesExample example);

    int deleteByExample(FavoritesExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Favorites row);

    int insertSelective(Favorites row);

    List<Favorites> selectByExample(FavoritesExample example);

    Favorites selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") Favorites row, @Param("example") FavoritesExample example);

    int updateByExample(@Param("row") Favorites row, @Param("example") FavoritesExample example);

    int updateByPrimaryKeySelective(Favorites row);

    int updateByPrimaryKey(Favorites row);
}