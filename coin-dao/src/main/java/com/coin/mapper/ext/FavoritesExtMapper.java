package com.coin.mapper.ext;

import com.coin.entity.Favorites;
import org.apache.ibatis.annotations.Param;

public interface FavoritesExtMapper {

    Favorites findByType(@Param("userId") Long userId, @Param("favId") Long favId, @Param("type") String type);
}
