package com.coin.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.coin.entity.*;
import com.coin.i18n.I18nUtil;
import com.coin.mapper.GoodsMapper;
import com.coin.mapper.OrdersMapper;
import com.coin.mapper.TransactionsMapper;
import com.coin.mapper.ext.GoodsExtMapper;
import com.coin.mapper.ext.OrdersExtMapper;
import com.coin.req.GoodsReq;
import com.coin.req.OrdersReq;
import com.coin.req.TransfersReq;
import com.coin.resp.GoodsResp;
import com.coin.resp.OrdersResp;
import com.coin.service.*;
import com.coin.service.constant.CodeCons;
import com.coin.service.exception.BizException;
import com.coin.service.util.ContentUtil;
import com.coin.service.util.ImageUtil;
import com.coin.service.util.PageUtil;
import com.coin.service.util.RedisUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class GoodsServiceImpl implements GoodsService {

    private static final Logger logger = LoggerFactory.getLogger(GoodsServiceImpl.class);

    @Resource
    private GoodsMapper goodsMapper;
    @Resource
    private GoodsExtMapper goodsExtMapper;
    @Resource
    private OrdersService ordersService;
    @Resource
    private OrdersMapper ordersMapper;
    @Resource
    private OrdersExtMapper ordersExtMapper;
    @Resource
    private GoodsService goodsService;
    @Resource
    private GoodTagsService goodTagsService;
    @Resource
    private UsersService usersService;
    @Resource
    private WalletsService walletsService;
    @Resource
    private TransactionsMapper transactionsMapper;
    @Resource
    private TransactionsService transactionsService;
    @Resource
    private TransfersService transfersService;
    @Resource
    private RedisUtil redisUtil;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void add(GoodsReq req) {
        Date now = new Date();
        Goods goods = new Goods();
        goods.setName(req.getName());
        goods.setPic(req.getPic());
        goods.setPics("[]");
        goods.setTags(StrUtil.isBlank(req.getTags()) ? "[]" : req.getTags());
        goods.setH5pic(req.getH5pic());
        goods.setStock(req.getStock());
        goods.setContent(req.getContent());
        goods.setPrice(req.getPrice());
        goods.setSort(req.getSort());
        goods.setCreatedAt(now);
        goods.setUpdatedAt(now);
        ContentUtil.processGoodsContent(goods);
        goodsMapper.insertSelective(goods);
        //创建钱包
        walletsService.initWallet(goods);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(GoodsReq req) {
        Goods oldContest = goodsMapper.selectByPrimaryKey(req.getId());
        if (ObjectUtil.isNull(oldContest)) {
            throw new BizException(CodeCons.ERROR, "记录不存在");
        }
        Date now = new Date();
        Goods updateGoods = new Goods();
        updateGoods.setId(req.getId());
        updateGoods.setName(req.getName());
        updateGoods.setPic(req.getPic());
        updateGoods.setH5pic(req.getH5pic());
        updateGoods.setPics(req.getPics());
        updateGoods.setTags(req.getTags());
        updateGoods.setStock(req.getStock());
        updateGoods.setContent(req.getContent());
        updateGoods.setPrice(req.getPrice());
        updateGoods.setSort(req.getSort());
        updateGoods.setStatus(req.getStatus());
        updateGoods.setUpdatedAt(now);
        ContentUtil.processGoodsContent(updateGoods);
        goodsMapper.updateByPrimaryKeySelective(updateGoods);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int rollback(Long id, int num) {
        Goods oldGood = goodsMapper.selectByPrimaryKey(id);
        Goods updateGoods = new Goods();
        updateGoods.setId(id);
        updateGoods.setUpdatedAt(new Date());
        updateGoods.setStock(oldGood.getStock());
        return goodsMapper.updateByPrimaryKeySelective(updateGoods);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(GoodsReq req) {
        Goods updateGoods = new Goods();
        updateGoods.setId(req.getId());
        updateGoods.setStatus(0);
        updateGoods.setDeletedAt(new Date());
        goodsMapper.updateByPrimaryKeySelective(updateGoods);
    }

    @Override
    public Goods getById(Long id) {
        Goods goods = goodsMapper.selectByPrimaryKey(id);
        if (ObjectUtil.isNull(goods)) {
            throw new BizException(CodeCons.ERROR, "记录不存在");
        }
        return goods;
    }

    @Override
    public PageInfo<Goods> pageList(GoodsReq req) {
        GoodsExample example = new GoodsExample();
        GoodsExample.Criteria criteria = example.createCriteria();
        criteria.andDeletedAtIsNull();
        if (req.getStatus() != null) {
            criteria.andStatusEqualTo(req.getStatus());
        }
        if (StrUtil.isNotBlank(req.getName())) {
            criteria.andNameLike("%" + req.getName() + "%");
        }
        criteria.andDeletedAtIsNull();
        example.setOrderByClause(" sort desc, id desc");
        PageHelper.startPage(req.getPage(), req.getPagesize());
        List<Goods> datas = goodsMapper.selectByExample(example);
        datas.forEach(item -> {
            item.setH5pic(ImageUtil.completeImageUrl(item.getH5pic()));
            item.setPic(ImageUtil.completeImageUrl(item.getPic()));
        });
        return new PageInfo<>(datas);
    }

    @Override
    public PageInfo<GoodsResp> pageDatas(GoodsReq req) {
        PageInfo<Goods> page = this.pageList(req);
        List<Goods> datas = page.getList();
        List<GoodsResp> rspList = datas.stream().map(this::convertRsp).collect(Collectors.toList());
        return PageUtil.pageInfo2PageRsp(page, rspList);
    }

    private GoodsResp convertRsp(Goods good) {
        ContentUtil.processGoodsContent(good);
        GoodsResp resp = new GoodsResp();
        BeanUtils.copyProperties(good, resp);
        try {
            int total = ordersService.totalByStatus(good.getId(), 2);
            resp.setTotalExchange(total);

            OrdersExample example = new OrdersExample();
            OrdersExample.Criteria criteria = example.createCriteria();
            criteria.andGoodIdEqualTo(good.getId());
            criteria.andStatusLessThan(3);
            long count = ordersMapper.countByExample(example);
            resp.setRedeemed(Math.toIntExact(count));
            List<String> list = new ArrayList<>();
            if (StrUtil.isNotBlank(resp.getTags())) {
                JSONArray array = JSONUtil.parseArray(resp.getTags());
                List<GoodTags> goodTags = goodTagsService.getAllList(true);
                for (int i = 0; i < array.size(); i++) {
                    String tagName = this.getNameById(goodTags, array.getLong(i));
                    list.add(tagName);
                }
            }
            resp.setTagStrs(list);
        } catch (Exception e) {
            logger.error("GoodsResp-convertRsp-error", e);
        }
        return resp;
    }

    private String getNameById(List<GoodTags> goodTags, long id) {
        for (GoodTags gt : goodTags) {
            if (NumberUtil.equals(gt.getId(), id)) {
                return gt.getTagContent();
            }
        }
        return "";
    }

    @Override
    public PageInfo<GoodsResp> exchangeLeaderboard(GoodsReq req) {
        GoodsExample example = new GoodsExample();
        //ordersExtMapper.selectByLeaderboard();
        // 时间参数 day 当天,week 本周,month 本月,6month 最近六月,year 今年， 默认本月
        String startTime = getStartTime(req.getTime());
        PageHelper.startPage(req.getPage(), req.getPagesize());
        List<OrdersResp> orders = ordersExtMapper.selectByLeaderboard(startTime);
        //没有直接返回
        if (CollectionUtils.isEmpty(orders)) {
            return null;
        }
        List<Long> ids = new ArrayList<>();
        for (OrdersResp item : orders) {
            ids.add(item.getGoodId());
        }

        GoodsExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(ids);
        example.setOrderByClause(" FIELD(id," + CollectionUtil.join(ids, ",") + ")");
        List<Goods> datas = goodsMapper.selectByExample(example);
        PageInfo<Goods> page = new PageInfo<>(datas);
        List<GoodsResp> rspList = datas.stream().map(info -> this.convertRedeemedRsp(info, startTime)).collect(Collectors.toList());
        return PageUtil.pageInfo2PageRsp(page, rspList);
    }

    private String getStartTime(String time) {
        String startTime = null;
        switch (time) {
            case "day":
                startTime = String.valueOf(DateUtil.beginOfDay(new Date()));
                break;
            case "week":
                startTime = String.valueOf(DateUtil.beginOfWeek(new Date()));
                break;
            case "month":
                startTime = String.valueOf(DateUtil.beginOfMonth(new Date()));
                break;
            case "6month":
                startTime = String.valueOf(DateUtil.offsetMonth(new Date(), -6));
                break;
            case "year":
                startTime = String.valueOf(DateUtil.beginOfYear(new Date()));
                break;
        }
        return startTime;
    }

    private GoodsResp convertRedeemedRsp(Goods info, String startTime) {
        GoodsResp resp = new GoodsResp();
        BeanUtils.copyProperties(info, resp);
        try {
            OrdersExample example = new OrdersExample();
            OrdersExample.Criteria criteria = example.createCriteria();
            String timeRange = getStartTime(startTime); // 假设这个方法可以正确计算时间范围
            int redeemed;
            if (timeRange != null) {
                criteria.andStatusLessThan(3);
                //timeRange
                criteria.andCreatedAtGreaterThanOrEqualTo(DateUtil.parse(startTime));
            } else {
                criteria.andStatusLessThan(3);
            }
            criteria.andGoodIdEqualTo(info.getId());
            redeemed = (int) ordersMapper.countByExample(example);
            resp.setRedeemed(redeemed);
            resp.setPic(ImageUtil.completeImageUrl(resp.getPic()));
            resp.setH5pic(ImageUtil.completeImageUrl(resp.getH5pic()));
        } catch (Exception e) {
            logger.error(CodeCons.ERROR, "GoodsResp-convertRsp-error", e);
        }
        return resp;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void exchange(GoodsReq req, Long id) {
        req.setId(id);
        Goods goods = goodsMapper.selectByPrimaryKey(req.getId());
        if (ObjectUtil.isNull(goods)) {
            throw new BizException(CodeCons.ERROR, "记录不存在");
        }

        Integer stock = goods.getStock();
        if (stock <= 0) {
            throw new BizException(CodeCons.GOOD_NO_STOCK, "库存不足");
        }
        Users user = usersService.selectByUserName(req.getOptLoginName());
        //今天有没兑换过
        Date now = new Date();
        DateTime todayStart = DateUtil.beginOfDay(now);
        DateTime todayEnd = DateUtil.endOfDay(now);

        OrdersExample example = new OrdersExample();
        OrdersExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(user.getId());
        criteria.andStatusLessThan(3);
        criteria.andCreatedAtBetween(todayStart, todayEnd);
        List<Orders> orders = ordersMapper.selectByExample(example);
        if (!CollectionUtils.isEmpty(orders)) {
            throw new BizException(CodeCons.GOOD_ALREADY_EXCHANGE, "今天已兑换过");
        }
        //更新库存
        int row = goodsExtMapper.decrementByExample(req.getId());
        if (row != 1) {
            throw new BizException(CodeCons.ERROR, "库存更新失败");
        }
        String errorMsg = "兑换失败";
        Wallets wallet1 = walletsService.getByUserId(user.getId());
        Wallets wallet2 = walletsService.getByGoodId(goods.getId());
        int count;
        Map<String, String> json = new HashMap<>();
        json.put("info", I18nUtil.translateBiz("商城兑换") + ":[" + goods.getName() + "]");
        json.put("type", "Mall");
        //添加用户交易记录
        Transactions transaction1 = new Transactions();
        transaction1.setType("withdraw");
        transaction1.setPayableId(user.getId());
        transaction1.setPayableType("App\\Models\\User");
        transaction1.setAmount(goods.getPrice().multiply(new BigDecimal("-1")));
        transaction1.setUuid(UUID.randomUUID().toString());
        transaction1.setUpdatedAt(now);
        transaction1.setCreatedAt(now);
        transaction1.setMeta(JSONUtil.toJsonStr(json));
        transaction1.setWalletId(wallet1.getId());
        transaction1.setConfirmed(true);
        count = transactionsMapper.insertSelective(transaction1);
        if (count != 1) {
            throw new BizException(CodeCons.ERROR, errorMsg);
        }
        //添加物品交易记录
        Transactions transaction2 = new Transactions();
        transaction2.setType("deposit");
        transaction2.setPayableId(goods.getId());
        transaction2.setPayableType("App\\Models\\Good");
        transaction2.setAmount(goods.getPrice());
        transaction2.setUuid(UUID.randomUUID().toString());
        transaction2.setUpdatedAt(now);
        transaction2.setCreatedAt(now);
        transaction2.setMeta(json.toString());
        transaction2.setWalletId(wallet2.getId());
        transaction2.setConfirmed(true);
        count = transactionsMapper.insertSelective(transaction2);
        if (count != 1) {
            throw new BizException(CodeCons.ERROR, errorMsg);
        }
        //钱包博币扣减
        count = walletsService.changeBalance(wallet1, transaction1.getAmount().abs().multiply(new BigDecimal("-1")));
        if (count != 1) {
            throw new BizException(CodeCons.ERROR, errorMsg);
        }
        //4.商品冻结的博币扣除
        count = walletsService.changeBalance(wallet2, transaction1.getAmount().abs());
        if (count != 1) {
            throw new BizException(CodeCons.ERROR, errorMsg);
        }

        //5.转移表增加记录
        TransfersReq transfersReq = new TransfersReq();
        transfersReq.setFromType("Bavix\\Wallet\\Models\\Wallet");
        transfersReq.setFromId(wallet1.getId());
        transfersReq.setToType("Bavix\\Wallet\\Models\\Wallet");
        transfersReq.setToId(wallet2.getId());
        transfersReq.setDepositId(transaction2.getId());
        transfersReq.setWithdrawId(transaction1.getId());
        transfersReq.setStatus("paid");
        int transferId = transfersService.add(transfersReq);
        if (transferId == 0) {
            throw new BizException(CodeCons.ERROR, errorMsg);
        }
        //添加订单记录
        OrdersReq reqOrder = new OrdersReq();
        reqOrder.setUserId(user.getId());
        reqOrder.setGoodId(req.getId());
        reqOrder.setStatus(1);
        reqOrder.setTransferId(transferId);

        reqOrder.setSn(String.valueOf(now.getTime()));
        ordersService.add(reqOrder);
    }
}
