package com.coin.web.controller.client;

import com.coin.entity.ReportNameTags;
import com.coin.entity.Reports;
import com.coin.req.ReportReplyReq;
import com.coin.req.ReportsReq;
import com.coin.resp.MyResp;
import com.coin.resp.report.*;
import com.coin.service.*;
import com.coin.service.constant.CodeCons;
import com.coin.service.exception.BizException;
import com.coin.service.util.ParamUtil;
import com.coin.web.annotation.CommonSecure;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 05_老哥帮手
@RestController
@RequestMapping("/report")
public class ReportController {

    private static final Logger logger = LoggerFactory.getLogger(ReportController.class);

    @Resource
    private ReportsService reportsService;
    @Resource
    private ReportTagsService reportTagsService;
    @Resource
    private ReportNameTagsService reportNameTagsService;
    @Resource
    private ReportReasonsService reportReasonsService;
    @Resource
    private ReportReplyService reportReplyService;

    // 01_维权申请
    @PostMapping("/newReport")
    @CommonSecure
    public MyResp<Map<String, Long>> newReport(@RequestBody ReportsReq req) {
        logger.info("reports-newReport-req={}", req);
        try {
            ParamUtil.check(req.getWebsiteName(), "websiteName", req.getWebsiteUrl(),
                    "websiteUrl", req.getReasonId(), "reasonId", req.getAmount(), "amount", req.getContent(), "content");
            Long id = reportsService.newReport(req);
            Map<String, Long> result = new HashMap<>();
            result.put("id", id);
            return new MyResp<>(CodeCons.SUCCESS, "投诉成功", result);
        } catch (BizException e) {
            logger.error("reports-newReport-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("reports-newReport-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "查询失败");
    }

    // 02_获取维权标签列表
    @PostMapping("/getTags")
    @CommonSecure(needLogin = false)
    public MyResp<List<TagsVo>> getTags(@RequestBody ReportsReq req) {
        logger.info("reports-getTags-req={}", req);
        try {
            List<TagsVo> list = reportTagsService.getTags();
            return new MyResp<>(CodeCons.SUCCESS, "", list);
        } catch (BizException e) {
            logger.error("reports-getTags-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("reports-getTags-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "查询失败");
    }

    // 03_获取盘口标签
    @PostMapping("/getNameTags")
    @CommonSecure(needLogin = false)
    public MyResp<List<ReportNameTags>> getNameTags(@RequestBody ReportsReq req) {
        logger.info("reports-getNameTags-req={}", req);
        try {
            List<ReportNameTags> list = reportNameTagsService.getNameTags();
            return new MyResp<>(CodeCons.SUCCESS, "", list);
        } catch (BizException e) {
            logger.error("reports-getNameTags-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("reports-getNameTags-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "查询失败");
    }

    // 04_获取维权原因
    @PostMapping("/getReasons")
    @CommonSecure(needLogin = false)
    public MyResp<List<ReasonsVo>> getReasons(@RequestBody ReportsReq req) {
        logger.info("reports-getReasons-req={}", req);
        try {
            List<ReasonsVo> list = reportReasonsService.getReasons();
            return new MyResp<>(CodeCons.SUCCESS, "", list);
        } catch (BizException e) {
            logger.error("reports-getReasons-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("reports-getReasons-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "查询失败");
    }

    // 05_获取维权列表
    @PostMapping("/lists")
    @CommonSecure(needLogin = false)
    public MyResp<PageInfo<ReportsVoResp>> lists(@RequestBody ReportsReq req) {
        logger.info("reports-lists-req={}", req);
        try {
            ParamUtil.check(req.getPage(), "page", req.getPagesize(), "pageSize");
            PageInfo<ReportsVoResp> page = reportsService.lists(req);
            return new MyResp<>(CodeCons.SUCCESS, "", page);
        } catch (BizException e) {
            logger.error("reports-lists-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("reports-lists-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "查询失败");
    }

    // 06_获取用户维权列表
    @PostMapping("/listsByUser")
    @CommonSecure(needLogin = false)
    public MyResp<PageInfo<ReportsVoResp>> listsByUser(@RequestBody ReportsReq req) {
        logger.info("reports-listsByUser-req={}", req);
        try {
            ParamUtil.check(req.getPage(), "page", req.getPagesize(), "pageSize", req.getUserId(), "userId");
            PageInfo<ReportsVoResp> page = reportsService.listsByUser(req);
            return new MyResp<>(CodeCons.SUCCESS, "", page);
        } catch (BizException e) {
            logger.error("reports-listsByUser-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("reports-listsByUser-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "查询失败");
    }

    // 07_获取当前登录用户维权列表
    @PostMapping("/listsBySelf")
    @CommonSecure
    public MyResp<PageInfo<ReportsVoResp>> listsBySelf(@RequestBody ReportsReq req) {
        logger.info("reports-listsBySelf-req={}", req);
        try {
            ParamUtil.check(req.getPage(), "page", req.getPagesize(), "pageSize");
            req.setUserId(req.getLoginUserId());
            PageInfo<ReportsVoResp> page = reportsService.listsByUser(req);
            return new MyResp<>(CodeCons.SUCCESS, "", page);
        } catch (BizException e) {
            logger.error("reports-listsBySelf-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("reports-listsBySelf-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "查询失败");
    }

    // 08_获取申诉详情
    @PostMapping("/{id}")
    @CommonSecure(needLogin = false)
    public MyResp<ReportsDetailResp> getReportDetail(@PathVariable("id") Long id, @RequestBody ReportsReq req) {
        logger.info("reports-getReportDetail-req={}", id);
        try {
            ParamUtil.check(id, "ID");
            req.setId(id);
            ReportsDetailResp rsp = reportsService.getReportDetail(req);
            return new MyResp<>(CodeCons.SUCCESS, "", rsp);
        } catch (BizException e) {
            logger.error("reports-getReportDetail-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("reports-getReportDetail-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "查询失败");
    }

    // 09_获取投诉类型统计
    @PostMapping("/getStatistics")
    @CommonSecure(needLogin = false)
    public MyResp<List<ReportsStatistics>> getStatistics(@RequestBody ReportsReq req) {
        logger.info("reports-getStatistics-req={}", req);
        try {
            List<ReportsStatistics> statistics = reportsService.getStatistics(req);
            return new MyResp<>(CodeCons.SUCCESS, "", statistics);
        } catch (BizException e) {
            logger.error("reports-getStatistics-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("reports-getStatistics-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "查询失败");
    }

    // 11_获取排行统计
    @PostMapping("/getLeaderBoard")
    @CommonSecure(needLogin = false)
    public MyResp<List<ReportsLeaderboard>> getLeaderBoard(@RequestBody ReportsReq req) {
        logger.info("reports-getLeaderBoard-req={}", req);
        try {
            ParamUtil.check(req.getIssucceed(), "issucceed");
            List<ReportsLeaderboard> datas = reportsService.getLeaderBoard(req);
            return new MyResp<>(CodeCons.SUCCESS, "", datas);
        } catch (BizException e) {
            logger.error("reports-getLeaderBoard-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("reports-getLeaderBoard-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "查询失败");
    }

    // 12_申诉发表回帖
    @PostMapping("/reply")
    @CommonSecure
    public MyResp<PageInfo<Reports>> reply(@RequestBody ReportsReq req) {
        logger.info("reports-reply-req={}", req);
        try {
            ParamUtil.check(req.getReportId(), "reportId", req.getContent(), "content");
            reportReplyService.reply(req);
            return new MyResp<>(CodeCons.SUCCESS, "回复成功");
        } catch (BizException e) {
            logger.error("reports-reply-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("reports-reply-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "回复失败");
    }

    // 14_删除申诉
    @PostMapping("/deleteReport/{id}")
    @CommonSecure
    public MyResp<String> deleteReport(@RequestBody ReportsReq req, @PathVariable("id") Long id) {
        logger.info("reports-deleteReport-req={}", id);
        try {
            ParamUtil.check(id, "ID");
            req.setReportId(id);
            reportsService.deleteById(req);
            return new MyResp<>(CodeCons.SUCCESS, "删除成功");
        } catch (BizException e) {
            logger.error("reports-deleteReport-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("reports-deleteReport-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "删除失败");
    }

    // 15_删除回帖
    @PostMapping("/deleteReportReply/{replyId}")
    @CommonSecure
    public MyResp<String> deleteReportReply(@RequestBody ReportReplyReq req, @PathVariable("replyId") Long replyId) {
        logger.info("reports-deleteReportReply-req={}", replyId);
        try {
            ParamUtil.check(replyId, "replyId");
            req.setId(replyId);
            reportReplyService.deleteById(req);
            return new MyResp<>(CodeCons.SUCCESS, "删除成功");
        } catch (BizException e) {
            logger.error("reports-deleteReportReply-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("reports-deleteReportReply-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "查询失败");
    }

    // 16_编辑回帖
    @PostMapping("/modifyReportReply/{id}")
    @CommonSecure
    public MyResp<String> modifyReportReply(@PathVariable("replyId") Long replyId, @RequestBody ReportReplyReq req) {
        logger.info("reports-modifyReportReply-req={}", req);
        try {
            ParamUtil.check(replyId, "replyId", req.getContent(), "content");
            req.setId(replyId);
            reportReplyService.modifyReportReply(req);
            return new MyResp<>(CodeCons.SUCCESS, "修改成功");
        } catch (BizException e) {
            logger.error("reports-modifyReportReply-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("reports-modifyReportReply-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "修改失败");
    }
}