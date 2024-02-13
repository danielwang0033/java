package com.coin.service;

import com.coin.cache.UserBobiVo;
import com.coin.entity.Goods;
import com.coin.entity.Users;
import com.coin.entity.Wallets;
import com.coin.req.WalletsReq;
import com.github.pagehelper.PageInfo;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public interface WalletsService {

    int changeBalance(Wallets wallet, BigDecimal val);

    Wallets getByUserId(Long userId);

    Wallets getByGoodId(Long goodId);

    PageInfo<Wallets> pageList(WalletsReq req);

    void initWallet(Users users);

    void initWallet(Goods goods);

    List<UserBobiVo> selectByUserIdList(Set<Long> userIdList);

    UserBobiVo selectByUserId(Long userId);

    void addBobiByUserId(Long userId, Integer addAmount, String message);
}
