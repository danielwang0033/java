package com.coin.service.impl;

import cn.hutool.core.util.BooleanUtil;
import com.coin.entity.*;
import com.coin.mapper.*;
import com.coin.mapper.ext.OrdersExtMapper;
import com.coin.req.OrdersReq;
import com.coin.resp.OrdersResp;
import com.coin.service.*;
import com.coin.service.constant.CodeCons;
import com.coin.service.exception.BizException;
import com.coin.service.util.ImageUtil;
import com.coin.service.util.PageUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrdersServiceImpl implements OrdersService {

    private static final Logger logger = LoggerFactory.getLogger(OrdersServiceImpl.class);

    @Resource
    private OrdersMapper ordersMapper;
    @Resource
    private UsersMapper usersMapper;
    @Resource
    private GoodsMapper goodsMapper;
    @Resource
    private OrdersExtMapper ordersExtMapper;
    @Resource
    private TransactionsMapper transactionsMapper;
    @Resource
    private WalletsService walletsService;
    @Resource
    private GoodsService goodsService;
    @Resource
    private TransactionsService transactionsService;
    @Resource
    private TransfersService transfersService;
    @Resource
    private UsersService usersService;
    @Resource
    private TransfersMapper transfersMapper;
    @Resource
    private WalletsMapper walletsMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void add(OrdersReq req) {
        Date now = new Date();
        Orders orders = new Orders();
        orders.setSn(req.getSn());
        orders.setUserId(req.getUserId());
        orders.setGoodId(req.getGoodId());
        orders.setStatus(req.getStatus());
        orders.setTransferId(req.getTransferId());
        orders.setCreatedAt(now);
        orders.setUpdatedAt(now);
        ordersMapper.insertSelective(orders);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void doApproval(OrdersReq req) {
        String errMsg = "审批失败";
        Orders oldOrder = ordersMapper.selectByPrimaryKey(req.getId());
        if (oldOrder.getStatus() != 1) {
            throw new BizException(CodeCons.ERROR, "当前订单已审批");
        }
        int status = req.getStatus();
        if (status != 2 && status != 3) {
            throw new BizException(CodeCons.ERROR, errMsg);
        }
        Date now = new Date();
        int count;

        if (status == 3) {
            //1.商品库存加回去
            count = goodsService.rollback(Long.parseLong(oldOrder.getGoodId() + ""), 1);
            if (count != 1) {
                throw new BizException(CodeCons.ERROR, errMsg);
            }
            Transfers transfers = transfersService.getById(Long.valueOf(oldOrder.getTransferId()));
            transfers.setStatus("refund");
            count = transfersMapper.updateByPrimaryKeySelective(transfers);
            if (count != 1) {
                throw new BizException(CodeCons.ERROR, errMsg);
            }

            //2.交易记录表增加2条回退记录
            Transactions transaction1 = transactionsService.getById(transfers.getWithdrawId());
            transaction1.setType("deposit");
            transaction1.setAmount(transaction1.getAmount().multiply(new BigDecimal("-1")));
            transaction1.setUuid(UUID.randomUUID().toString());
            transaction1.setUpdatedAt(now);
            transaction1.setCreatedAt(now);
            count = transactionsMapper.insertSelective(transaction1);
            if (count != 1) {
                throw new BizException(CodeCons.ERROR, errMsg);
            }
            //Transactions transaction2 = transactionsService.getByDayAndIsMall(minDate, maxDate, "App\\Models\\Goods", oldOrder.getGoodId().longValue());
            Transactions transaction2 = transactionsService.getById(transfers.getDepositId());
            transaction2.setType("withdraw");
            transaction2.setAmount(transaction2.getAmount().multiply(new BigDecimal("-1")));
            transaction2.setUuid(UUID.randomUUID().toString());
            transaction2.setUpdatedAt(now);
            transaction2.setCreatedAt(now);
            count = transactionsMapper.insertSelective(transaction2);
            if (count != 1) {
                throw new BizException(CodeCons.ERROR, errMsg);
            }
            //3.钱包扣除的博币回退
            //Wallets wallet1 = walletsService.getByUserId(oldOrder.getUserId().longValue());
            Wallets wallet1 = walletsMapper.selectByPrimaryKey(transfers.getFromId());
            count = walletsService.changeBalance(wallet1, transaction1.getAmount().abs());
            if (count != 1) {
                throw new BizException(CodeCons.ERROR, errMsg);
            }
            //4.商品冻结的博币扣除
            //Wallets wallet2 = walletsService.getByGoodId(oldOrder.getGoodId().longValue());
            Wallets wallet2 = walletsMapper.selectByPrimaryKey(transfers.getToId());
            count = walletsService.changeBalance(wallet2, transaction1.getAmount().abs().multiply(new BigDecimal("-1")));
            if (count != 1) {
                throw new BizException(CodeCons.ERROR, errMsg);
            }
            //5.转移表增加记录
            count = transfersService.addRollback(wallet2.getId(), wallet1.getId(), transaction1.getId(), transaction2.getId());
            if (count == 0) {
                throw new BizException(CodeCons.ERROR, errMsg);
            }
        }
        //0.改订单主表状态
        Orders orders = new Orders();
        orders.setUpdatedAt(now);
        orders.setId(req.getId());
        orders.setStatus(req.getStatus());
        count = ordersMapper.updateByPrimaryKeySelective(orders);
        if (count != 1) {
            throw new BizException(CodeCons.ERROR, errMsg);
        }
    }

    @Override
    public PageInfo<OrdersResp> pageList(OrdersReq req, boolean needDetail) {
        OrdersExample example = new OrdersExample();
        OrdersExample.Criteria criteria = example.createCriteria();
        if (req.getUserId() != null) {
            criteria.andUserIdEqualTo(req.getUserId());
        }
        if (req.getGoodId() != null) {
            criteria.andGoodIdEqualTo(req.getGoodId());
        }
        if (req.getStatus() != null) {
            criteria.andStatusEqualTo(req.getStatus());
        }
        if (req.getCreatedAtMin() != null) {
            criteria.andCreatedAtGreaterThanOrEqualTo(req.getCreatedAtMin());
        }
        if (req.getCreatedAtMax() != null) {
            criteria.andCreatedAtLessThanOrEqualTo(req.getCreatedAtMax());
        }
        example.setOrderByClause("updated_at desc");
        PageHelper.startPage(req.getPage(), req.getPagesize());
        List<Orders> datas = ordersMapper.selectByExample(example);
        PageInfo<Orders> page = new PageInfo<>(datas);
        List<OrdersResp> results = datas.stream().map(info -> this.convertRsp(info, needDetail)).collect(Collectors.toList());
        return PageUtil.pageInfo2PageRsp(page, results);
    }

    private OrdersResp convertRsp(Orders info, boolean needDetail) {
        OrdersResp resp = new OrdersResp();
        BeanUtils.copyProperties(info, resp);
        if (BooleanUtil.isTrue(needDetail)) {
            try {
                Users user = usersMapper.selectByPrimaryKey(resp.getUserId());
                resp.setUserName(user.getName());
                Goods good = goodsMapper.selectByPrimaryKey(resp.getGoodId());
                good.setPic(ImageUtil.completeImageUrl(good.getPic()));
                good.setH5pic(ImageUtil.completeImageUrl(good.getH5pic()));
                resp.setGoodName(good.getName());
                resp.setGood(good);
            } catch (Exception e) {
                logger.error("OrdersResp-convertRsp-e", e);
            }
        }
        return resp;
    }


    @Override
    public PageInfo<OrdersResp> myPageList(OrdersReq req) {
        OrdersExample example = new OrdersExample();
        Users user = usersService.selectByUserName(req.getOptLoginName());
        OrdersExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(user.getId());

        example.setOrderByClause("created_at desc");
        PageHelper.startPage(req.getPage(), req.getPagesize());
        List<Orders> datas = ordersMapper.selectByExample(example);
        PageInfo<Orders> page = new PageInfo<>(datas);

        List<OrdersResp> results = datas.stream().map(info -> this.convertRsp(info, true)).collect(Collectors.toList());
        return PageUtil.pageInfo2PageRsp(page, results);
        //return new PageInfo<>(datas);
    }

    @Override
    public Integer totalByStatus(Long goodId, Integer status) {
        OrdersExample example = new OrdersExample();
        OrdersExample.Criteria criteria = example.createCriteria();
        criteria.andGoodIdEqualTo(goodId);
        criteria.andStatusEqualTo(status);
        long count = ordersMapper.countByExample(example);
        return (int) count;
    }
}
