package com.coin.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.coin.entity.Transfers;
import com.coin.entity.TransfersExample;
import com.coin.mapper.TransfersMapper;
import com.coin.req.TransfersReq;
import com.coin.service.TransfersService;
import com.coin.service.constant.CodeCons;
import com.coin.service.exception.BizException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class TransfersServiceImpl implements TransfersService {

    private static final Logger logger = LoggerFactory.getLogger(TransfersServiceImpl.class);

    @Resource
    private TransfersMapper transfersMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int add(TransfersReq req) {
        Date now = new Date();
        Transfers transfers = new Transfers();
        transfers.setFromType(req.getFromType());
        transfers.setFromId(req.getFromId());
        transfers.setToType(req.getToType());
        transfers.setToId(req.getToId());
        transfers.setStatus(req.getStatus());
        transfers.setStatusLast(req.getStatusLast());
        transfers.setDepositId(req.getDepositId());
        transfers.setWithdrawId(req.getWithdrawId());
        transfers.setDiscount(req.getDiscount());
        transfers.setFee(req.getFee());
        transfers.setUuid(UUID.randomUUID().toString());
        transfers.setCreatedAt(now);
        transfers.setUpdatedAt(now);
        transfersMapper.insertSelective(transfers);
        return Math.toIntExact(transfers.getId());
    }

    @Override
    public int addRollback(long fromId, long toId, long depositId, long withdrawId) {
        TransfersReq req = new TransfersReq();
        req.setFromType("Bavix\\Wallet\\Models\\Wallet");
        req.setFromId(fromId);
        req.setToType("Bavix\\Wallet\\Models\\Wallet");
        req.setToId(toId);
        req.setStatus("transfer");
        req.setDepositId(depositId);
        req.setWithdrawId(withdrawId);
        return this.add(req);
    }

    @Override
    public Transfers getById(Long id) {
        Transfers transfers = transfersMapper.selectByPrimaryKey(id);
        if (ObjectUtil.isNull(transfers)) {
            throw new BizException(CodeCons.ERROR, "记录不存在");
        }
        return transfers;
    }

    @Override
    public PageInfo<Transfers> pageList(TransfersReq req) {
        TransfersExample example = new TransfersExample();
        TransfersExample.Criteria criteria = example.createCriteria();
        example.setOrderByClause(" id");
        PageHelper.startPage(req.getPage(), req.getPagesize());
        List<Transfers> datas = transfersMapper.selectByExample(example);
        return new PageInfo<>(datas);
    }
}
