package com.coin.service.impl;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.coin.cache.UserBobiVo;
import com.coin.entity.Goods;
import com.coin.entity.Users;
import com.coin.entity.Wallets;
import com.coin.entity.WalletsExample;
import com.coin.mapper.WalletsMapper;
import com.coin.mapper.ext.WalletsExtMapper;
import com.coin.req.TransactionsReq;
import com.coin.req.WalletsReq;
import com.coin.service.TransactionsService;
import com.coin.service.WalletsService;
import com.coin.service.constant.CodeCons;
import com.coin.service.exception.BizException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
public class WalletsServiceImpl implements WalletsService {

    private static final Logger logger = LoggerFactory.getLogger(WalletsServiceImpl.class);

    @Resource
    private WalletsMapper walletsMapper;

    @Resource
    private WalletsExtMapper walletsExtMapper;

    @Resource
    private TransactionsService transactionsService;

    @Override
    public void initWallet(Users users) {
        Date now = new Date();
        Wallets wallets = new Wallets();
        wallets.setHolderType("App\\Models\\User");
        wallets.setHolderId(users.getId());
        wallets.setName("point");
        wallets.setSlug("default");
        wallets.setUuid(UUID.randomUUID().toString());
        wallets.setDescription("");
        wallets.setMeta("[]");
        wallets.setBalance(BigDecimal.ZERO);
        wallets.setDecimalPlaces(new Short("2"));
        wallets.setCreatedAt(now);
        wallets.setUpdatedAt(now);
        walletsMapper.insertSelective(wallets);
    }

    @Override
    public void initWallet(Goods goods) {
        Date now = new Date();
        Wallets wallets = new Wallets();
        wallets.setHolderType("App\\Models\\Goods");
        wallets.setHolderId(goods.getId());
        wallets.setName("point");
        wallets.setSlug("default");
        wallets.setUuid(UUID.randomUUID().toString());
        wallets.setDescription("");
        wallets.setMeta("[]");
        wallets.setBalance(BigDecimal.ZERO);
        wallets.setDecimalPlaces(new Short("2"));
        wallets.setCreatedAt(now);
        wallets.setUpdatedAt(now);
        walletsMapper.insertSelective(wallets);
    }

    @Override
    public int changeBalance(Wallets wallet, BigDecimal val) {
        Wallets updateInfo = new Wallets();
        updateInfo.setId(wallet.getId());
        updateInfo.setBalance(wallet.getBalance().add(val));
        updateInfo.setUpdatedAt(new Date());
        return walletsMapper.updateByPrimaryKeySelective(updateInfo);
    }

    @Override
    public Wallets getByUserId(Long userId) {
        WalletsExample example = new WalletsExample();
        WalletsExample.Criteria criteria = example.createCriteria();
        criteria.andHolderTypeEqualTo("App\\Models\\User");
        criteria.andHolderIdEqualTo(userId);
        criteria.andSlugEqualTo("default");
        List<Wallets> datas = walletsMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(datas)) {
            return null;
        }
        return datas.get(0);
    }

    @Override
    public Wallets getByGoodId(Long goodId) {
        WalletsExample example = new WalletsExample();
        WalletsExample.Criteria criteria = example.createCriteria();
        criteria.andHolderTypeEqualTo("App\\Models\\Goods");
        criteria.andHolderIdEqualTo(goodId);
        criteria.andSlugEqualTo("default");
        List<Wallets> datas = walletsMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(datas)) {
            return null;
        }
        return datas.get(0);
    }

    @Override
    public PageInfo<Wallets> pageList(WalletsReq req) {
        WalletsExample example = new WalletsExample();
        WalletsExample.Criteria criteria = example.createCriteria();
        example.setOrderByClause(" id");
        PageHelper.startPage(req.getPage(), req.getPagesize());
        List<Wallets> datas = walletsMapper.selectByExample(example);
        return new PageInfo<>(datas);
    }

    @Override
    public List<UserBobiVo> selectByUserIdList(Set<Long> userIdList) {
        return walletsExtMapper.selectByUserIdList(userIdList);
    }

    @Override
    public UserBobiVo selectByUserId(Long userId) {
        return walletsExtMapper.selectByUserId(userId);
    }

    @Override
    public void addBobiByUserId(Long userId, Integer addAmount, String jsonMessage) {
        if (ObjectUtil.isNull(userId)) {
            throw new BizException(CodeCons.ERROR, "用户编号为空");
        }
        if (ObjectUtil.isNull(addAmount) || addAmount <= 0) {
            throw new BizException(CodeCons.ERROR, "金额异常");
        }
        if (StrUtil.isBlank(jsonMessage)) {
            throw new BizException(CodeCons.ERROR, "消息异常");
        }
        Wallets wallets = this.getByUserId(userId);
        if (ObjectUtil.isNull(wallets)) {
            throw new BizException(CodeCons.ERROR, "钱包不存在");
        }
        Long walletsId = wallets.getId();
        int count = walletsExtMapper.increaseBalance(walletsId, new BigDecimal(addAmount));
        if (count == 1) {
            TransactionsReq transactionsReq = new TransactionsReq();
            transactionsReq.setType("deposit");
            transactionsReq.setPayableId(userId);
            transactionsReq.setPayableType("App\\Models\\User");
            transactionsReq.setAmount(new BigDecimal(addAmount));
            transactionsReq.setUuid(UUID.fastUUID().toString());
            transactionsReq.setMeta(jsonMessage);
            transactionsReq.setWalletId(walletsId);
            transactionsService.add(transactionsReq);
        }
    }
}
