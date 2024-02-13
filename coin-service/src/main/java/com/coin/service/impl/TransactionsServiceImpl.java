package com.coin.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.coin.entity.Transactions;
import com.coin.entity.TransactionsExample;
import com.coin.entity.Wallets;
import com.coin.mapper.TransactionsMapper;
import com.coin.req.TransactionsReq;
import com.coin.req.UsersReq;
import com.coin.resp.TransactionsResp;
import com.coin.service.TransactionsService;
import com.coin.service.WalletsService;
import com.coin.service.constant.CodeCons;
import com.coin.service.exception.BizException;
import com.coin.service.util.PageUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionsServiceImpl implements TransactionsService {

    private static final Logger logger = LoggerFactory.getLogger(TransactionsServiceImpl.class);

    @Resource
    private TransactionsMapper transactionsMapper;
    @Resource
    private WalletsService walletsService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void add(TransactionsReq req) {
        Date now = new Date();
        Transactions transactions = new Transactions();
        transactions.setPayableType(req.getPayableType());
        transactions.setPayableId(req.getPayableId());
        transactions.setWalletId(req.getWalletId());
        transactions.setType(req.getType());
        transactions.setAmount(req.getAmount());
        transactions.setConfirmed(true);
        transactions.setMeta(req.getMeta());
        transactions.setUuid(req.getUuid());
        transactions.setCreatedAt(now);
        transactionsMapper.insertSelective(transactions);
    }

    @Override
    public Transactions getById(Long id) {
        Transactions transactions = transactionsMapper.selectByPrimaryKey(id);
        if (ObjectUtil.isNull(transactions)) {
            throw new BizException(CodeCons.ERROR, "记录不存在");
        }
        return transactions;
    }

    @Override
    public PageInfo<Transactions> pageList(TransactionsReq req) {
        TransactionsExample example = new TransactionsExample();
        TransactionsExample.Criteria criteria = example.createCriteria();
        Wallets wallet = walletsService.getByUserId(req.getUserId());
        criteria.andWalletIdEqualTo(wallet.getId());
        example.setOrderByClause(" id desc");
        PageHelper.startPage(req.getPage(), req.getPagesize());
        List<Transactions> datas = transactionsMapper.selectByExample(example);
        return new PageInfo<>(datas);
    }

    @Override
    public PageInfo<TransactionsResp> getBobiLogList(UsersReq req) {
        Wallets wallets = walletsService.getByUserId(req.getLoginUserId());
        TransactionsExample example = new TransactionsExample();
        TransactionsExample.Criteria criteria = example.createCriteria();
        criteria.andPayableTypeEqualTo("App\\Models\\User");
        criteria.andPayableIdEqualTo(req.getLoginUserId());
        criteria.andWalletIdEqualTo(wallets.getId());
        example.setOrderByClause(" id desc");
        PageHelper.startPage(req.getPage(), req.getPagesize());
        List<Transactions> datas = transactionsMapper.selectByExample(example);
        List<TransactionsResp> rspList = datas.stream().map(this::convertRsp).collect(Collectors.toList());
        PageInfo<Transactions> page = new PageInfo<>(datas);
        return PageUtil.pageInfo2PageRsp(page, rspList);
    }

    public TransactionsResp convertRsp(Transactions transactions) {
        TransactionsResp resp = new TransactionsResp();
        BeanUtils.copyProperties(transactions, resp);
        JSONObject meta = JSONUtil.parseObj(transactions.getMeta());
        String info = meta.getStr("info");
        String type = meta.getStr("type");
        resp.setInfo(info);
        resp.setType(type);
        return resp;
    }
}
