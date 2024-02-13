package com.coin.service;

import com.coin.entity.Reports;
import com.coin.req.ReportsReq;
import com.coin.req.search.SearchReq;
import com.coin.resp.ReportsResp;
import com.coin.resp.report.ReportsDetailResp;
import com.coin.resp.report.ReportsLeaderboard;
import com.coin.resp.report.ReportsStatistics;
import com.coin.resp.report.ReportsVoResp;
import com.coin.resp.search.SearchReportVo;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ReportsService {

    void delete(ReportsReq req);

    void update(ReportsReq req);

    void updateReadCount(ReportsReq req);

    Reports getById(Long id);

    PageInfo<ReportsResp> pageList(ReportsReq req);

    PageInfo<ReportsVoResp> lists(ReportsReq req);

    PageInfo<ReportsVoResp> listsByUser(ReportsReq req);

    ReportsDetailResp getReportDetail(ReportsReq req);

    List<ReportsStatistics> getStatistics(ReportsReq req);

    List<ReportsLeaderboard> getLeaderBoard(ReportsReq req);

    Long countByUserId(Long userId);

    Long newReport(ReportsReq req);

    void deleteById(ReportsReq req);

    PageInfo<SearchReportVo> searchReport(SearchReq req);
}
