package com.coin.mapper.ext;

import com.coin.entity.Likes;
import org.apache.ibatis.annotations.Param;

public interface LikesExtMapper {

    Likes findByType(@Param("userId") Long userId, @Param("likeId") Long likeId, @Param("type") String type);

    Long countById(@Param("likeId") Long likeId, @Param("type") String type);
}
