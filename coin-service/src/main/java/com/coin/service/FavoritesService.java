package com.coin.service;

import com.coin.entity.Favorites;
import com.coin.enums.LikeAndFavTypeEnum;
import com.coin.req.FavoritesReq;
import com.github.pagehelper.PageInfo;

public interface FavoritesService {

    Favorites findByType(Long userId, Long favId, LikeAndFavTypeEnum likeAndFavTypeEnum);

    void cancelFav(Long id);

    void addFav(Long userId, Long favId, LikeAndFavTypeEnum likeAndFavTypeEnum);

    PageInfo<Favorites> favoriteThreads(FavoritesReq req);

    void deleteArticle(Long articleId);
}
