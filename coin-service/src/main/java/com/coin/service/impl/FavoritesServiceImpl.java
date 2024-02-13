package com.coin.service.impl;

import com.coin.entity.Favorites;
import com.coin.entity.FavoritesExample;
import com.coin.enums.LikeAndFavTypeEnum;
import com.coin.mapper.FavoritesMapper;
import com.coin.mapper.ext.FavoritesExtMapper;
import com.coin.req.FavoritesReq;
import com.coin.service.FavoritesService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class FavoritesServiceImpl implements FavoritesService {

    private static final Logger logger = LoggerFactory.getLogger(FavoritesServiceImpl.class);

    @Resource
    private FavoritesMapper favoritesMapper;

    @Resource
    private FavoritesExtMapper favoritesExtMapper;

    @Override
    public Favorites findByType(Long userId, Long favId, LikeAndFavTypeEnum likeAndFavTypeEnum) {
        return favoritesExtMapper.findByType(userId, favId, likeAndFavTypeEnum.getType());
    }

    @Override
    public void addFav(Long userId, Long favId, LikeAndFavTypeEnum likeAndFavTypeEnum) {
        Date now = new Date();
        Favorites favorites = new Favorites();
        favorites.setUserId(userId);
        favorites.setFavoriteableType(likeAndFavTypeEnum.getType());
        favorites.setFavoriteableId(favId);
        favorites.setCreatedAt(now);
        favorites.setUpdatedAt(now);
        favoritesMapper.insertSelective(favorites);
    }

    @Override
    public void cancelFav(Long id) {
        favoritesMapper.deleteByPrimaryKey(id);
    }

    @Override
    public PageInfo<Favorites> favoriteThreads(FavoritesReq req) {
        FavoritesExample example = new FavoritesExample();
        FavoritesExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(req.getLoginUserId());
        criteria.andFavoriteableTypeEqualTo(req.getFavoriteableType());
        example.setOrderByClause(" id desc");
        PageHelper.startPage(req.getPage(), req.getPagesize());
        List<Favorites> datas = favoritesMapper.selectByExample(example);
        return new PageInfo<>(datas);
    }

    @Override
    public void deleteArticle(Long articleId) {
        FavoritesExample example = new FavoritesExample();
        FavoritesExample.Criteria criteria = example.createCriteria();
        criteria.andFavoriteableTypeEqualTo("App\\Models\\Article");
        criteria.andFavoriteableIdEqualTo(articleId);
        favoritesMapper.deleteByExample(example);
    }
}
