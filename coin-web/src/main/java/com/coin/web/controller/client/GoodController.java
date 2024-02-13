package com.coin.web.controller.client;

import com.coin.entity.Goods;
import com.coin.req.GoodsReq;
import com.coin.req.OrdersReq;
import com.coin.resp.GoodsResp;
import com.coin.resp.MyResp;
import com.coin.resp.OrdersResp;
import com.coin.service.GoodsService;
import com.coin.service.OrdersService;
import com.coin.service.constant.CodeCons;
import com.coin.service.exception.BizException;
import com.coin.service.util.ParamUtil;
import com.coin.web.annotation.CommonSecure;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

// 06_商城
@RestController
@RequestMapping("/good")
public class GoodController {

    private static final Logger logger = LoggerFactory.getLogger(GoodController.class);

    @Resource
    private GoodsService goodsService;
    @Resource
    private OrdersService ordersService;

    // 01_获取商品列表
    @PostMapping("/list")
    @CommonSecure(needLogin = false)
    public MyResp<PageInfo<GoodsResp>> list(@RequestBody GoodsReq req) {
        logger.info("goods-list-req={}", req);
        try {
            ParamUtil.check(req.getPage(), "page", req.getPagesize(), "pageSize");
            PageInfo<GoodsResp> page = goodsService.pageDatas(req);
            return new MyResp<>(CodeCons.SUCCESS, "", page);
        } catch (BizException e) {
            logger.error("goods-list-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("goods-list-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "查询失败");
    }

    // 02_商品兑换
    @PostMapping("/exchange")
    @CommonSecure
    public MyResp<PageInfo<Goods>> exchange(@RequestBody GoodsReq req) {
        logger.info("goods-exchange-req={}", req.getId());
        try {
            ParamUtil.check(req.getId(), "id");
            goodsService.exchange(req, req.getId());
            return new MyResp<>(CodeCons.SUCCESS, "抢购成功");
        } catch (BizException e) {
            logger.error("goods-exchange-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("goods-exchange-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "兑换失败");
    }

    // 03_最近兑换
    @PostMapping("/RecentlyRedeemed")
    @CommonSecure(needLogin = false)
    public MyResp<PageInfo<OrdersResp>> RecentlyRedeemed(@RequestBody OrdersReq req) {
        logger.info("goods-RecentlyRedeemed-req={}", req);
        try {
            ParamUtil.check(req.getPage(), "page", req.getPagesize(), "pageSize");
            PageInfo<OrdersResp> page = ordersService.pageList(req, req.getNeedDetail());
            return new MyResp<>(CodeCons.SUCCESS, "", page);
        } catch (BizException e) {
            logger.error("goods-RecentlyRedeemed-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("goods-RecentlyRedeemed-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "查询失败");
    }

    // 04_兑换排行榜
    @PostMapping("/ExchangeLeaderboard")
    @CommonSecure(needLogin = false)
    public MyResp<PageInfo<GoodsResp>> ExchangeLeaderboard(@RequestBody GoodsReq req) {
        logger.info("goods-ExchangeLeaderboard-req={}", req);
        try {
            ParamUtil.check(req.getPage(), "page", req.getPagesize(), "pageSize");
            PageInfo<GoodsResp> page = goodsService.exchangeLeaderboard(req);
            return new MyResp<>(CodeCons.SUCCESS, "", page);
        } catch (BizException e) {
            logger.error("goods-ExchangeLeaderboard-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("goods-ExchangeLeaderboard-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "查询失败");
    }

    // 05_获取用户兑换记录
    @PostMapping("/RecentlyRedeemedByUser")
    @CommonSecure
    public MyResp<PageInfo<OrdersResp>> RecentlyRedeemedByUser(@RequestBody OrdersReq req) {
        logger.info("goods-RecentlyRedeemedByUser-req={}", req);
        try {
            ParamUtil.check(req.getPage(), "page", req.getPagesize(), "pageSize");
            PageInfo<OrdersResp> page = ordersService.myPageList(req);
            return new MyResp<>(CodeCons.SUCCESS, "", page);
        } catch (BizException e) {
            logger.error("goods-RecentlyRedeemedByUser-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("goods-RecentlyRedeemedByUser-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "查询失败");
    }
}