package com.coin.service;

import com.coin.req.ReportReplyReq;
import com.coin.req.ReportsReq;
import com.coin.resp.ReportReplyResp;
import com.github.pagehelper.PageInfo;

public interface ReportReplyService {

    void add(ReportReplyReq req);

    void delete(ReportReplyReq req);

    void update(ReportReplyReq req);

    ReportReplyResp getById(Long id);

    PageInfo<ReportReplyResp> pageList(ReportReplyReq req);

    void reply(ReportsReq req);

    void deleteById(ReportReplyReq req);

    void modifyReportReply(ReportReplyReq req);
}
