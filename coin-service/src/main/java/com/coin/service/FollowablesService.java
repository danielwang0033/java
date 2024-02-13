package com.coin.service;

import com.coin.entity.Followables;
import com.coin.req.FollowablesReq;
import com.coin.req.UsersReq;
import com.coin.resp.user.FollowResultVo;
import com.coin.resp.user.UserFolloweeCountVo;
import com.coin.resp.user.UserFollowerCountVo;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Set;

public interface FollowablesService {

    void add(FollowablesReq req);

    void delete(FollowablesReq req);

    List<Followables> getListByUserId(Long userId);

    List<Followables> getListByFTypeAndFId(String ftype, Long fid);

    Followables getById(Long id);

    PageInfo<Followables> pageList(FollowablesReq req);

    FollowResultVo follow(Long userId, Long followId);

    FollowResultVo unfollow(Long userId, Long followId);

    Boolean checkAFollowB(Long aUserId, Long bUserId);

    List<Long> checkAFollowBList(Long loginUserId, Set<Long> followableIdSet);

    PageInfo<Followables> findFollowersByUserId(UsersReq req);

    PageInfo<Followables> findFolloweesByUserId(UsersReq req);

    Long countFollowerByUserId(Long userId);

    List<UserFollowerCountVo> countFollowerByUserIdList(Set<Long> followableIdSet);

    Long countFolloweeByUserId(Long userId);

    List<UserFolloweeCountVo> countFolloweeByUserIdList(Set<Long> followableIdSet);
}
