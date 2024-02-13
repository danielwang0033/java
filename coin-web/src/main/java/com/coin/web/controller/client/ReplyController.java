package com.coin.web.controller.client;

import com.coin.req.ReplyExtendReq;
import com.coin.resp.MyResp;
import com.coin.resp.reply.ReplyVo;
import com.coin.service.ReplyExtendService;
import com.coin.service.constant.CodeCons;
import com.coin.service.exception.BizException;
import com.coin.service.util.ParamUtil;
import com.coin.web.annotation.CommonSecure;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

// 08_评论扩展
@RestController
@RequestMapping("/replyExtend")
public class ReplyController {

    private static final Logger logger = LoggerFactory.getLogger(ReplyController.class);

    @Resource
    private ReplyExtendService replyExtendService;

    // 01_新增扩展评论
    @PostMapping("/reply")
    @CommonSecure
    public MyResp<String> reply(@RequestBody ReplyExtendReq req) {
        logger.info("replyExtend-reply-req={}", req);
        try {
            ParamUtil.check(req.getReplyType(), "replyType", req.getTowerType(), "towerType",
                    req.getFloorId(), "floorId", req.getContent(), "content");
            replyExtendService.reply(req);
            return new MyResp<>(CodeCons.SUCCESS, "评论成功");
        } catch (BizException e) {
            logger.error("replyExtend-reply-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("replyExtend-reply-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "评论失败");
    }

    // 02_查询评论列表
    @PostMapping("/replyPageList")
    @CommonSecure(needLogin = false)
    public MyResp<PageInfo<ReplyVo>> replyPageList(@RequestBody ReplyExtendReq req) {
        logger.info("replyExtend-replyPageList-req={}", req);
        try {
            ParamUtil.check(req.getPage(), "page", req.getPagesize(), "pageSize",
                    req.getTowerType(), "towerType", req.getTowerId(), "towerId");
            PageInfo<ReplyVo> page = replyExtendService.replyPageList(req);
            return new MyResp<>(CodeCons.SUCCESS, "", page);
        } catch (BizException e) {
            logger.error("replyExtend-replyPageList-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("replyExtend-replyPageList-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "查询失败");
    }

    // 03_编辑回复
    @PostMapping("/modifyById/{id}")
    @CommonSecure
    public MyResp<String> modifyById(@PathVariable("id") Long id, @RequestBody ReplyExtendReq req) {
        logger.info("replyExtend-modifyById-req={}", req);
        try {
            ParamUtil.check(id, "id", req.getContent(), "content");
            req.setId(id);
            replyExtendService.modifyReplyById(req);
            return new MyResp<>(CodeCons.SUCCESS, "修改成功");
        } catch (BizException e) {
            logger.error("replyExtend-modifyById-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("replyExtend-modifyById-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "修改失败");
    }

    // 04_删除回复
    @PostMapping("/deleteById/{id}")
    @CommonSecure
    public MyResp<String> deleteById(@PathVariable("id") Long id, @RequestBody ReplyExtendReq req) {
        logger.info("replyExtend-deleteById-req={}", req);
        try {
            ParamUtil.check(id, "id");
            req.setId(id);
            replyExtendService.deleteReplyById(req);
            return new MyResp<>(CodeCons.SUCCESS, "删除成功");
        } catch (BizException e) {
            logger.error("replyExtend-deleteById-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("replyExtend-deleteById-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "删除失败");
    }
}