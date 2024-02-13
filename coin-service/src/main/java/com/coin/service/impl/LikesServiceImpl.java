package com.coin.service.impl;

import com.coin.entity.Likes;
import com.coin.enums.LikeAndFavTypeEnum;
import com.coin.mapper.LikesMapper;
import com.coin.mapper.ext.LikesExtMapper;
import com.coin.service.LikesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class LikesServiceImpl implements LikesService {

    private static final Logger logger = LoggerFactory.getLogger(LikesServiceImpl.class);

    @Resource
    private LikesMapper likesMapper;

    @Resource
    private LikesExtMapper likesExtMapper;

    @Override
    public Likes findByType(Long userId, Long likeId, LikeAndFavTypeEnum likeAndFavTypeEnum) {
        return likesExtMapper.findByType(userId, likeId, likeAndFavTypeEnum.getType());
    }

    @Override
    public void addLike(Long userId, Long likeId, LikeAndFavTypeEnum likeAndFavTypeEnum) {
        Date now = new Date();
        Likes likes = new Likes();
        likes.setUserId(userId);
        likes.setLikeableType(likeAndFavTypeEnum.getType());
        likes.setLikeableId(likeId);
        likes.setCreatedAt(now);
        likes.setUpdatedAt(now);
        likesMapper.insertSelective(likes);
    }

    @Override
    public void cancelLike(Long id) {
        likesMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Long countById(Long id, LikeAndFavTypeEnum typeEnum) {
        return likesExtMapper.countById(id, typeEnum.getType());
    }
}
