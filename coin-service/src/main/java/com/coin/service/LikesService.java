package com.coin.service;

import com.coin.entity.Likes;
import com.coin.enums.LikeAndFavTypeEnum;

public interface LikesService {

    Likes findByType(Long userId, Long likeId, LikeAndFavTypeEnum likeAndFavTypeEnum);

    void addLike(Long userId, Long likeId, LikeAndFavTypeEnum likeAndFavTypeEnum);

    void cancelLike(Long id);

    Long countById(Long id, LikeAndFavTypeEnum likeAndFavTypeEnum);
}
