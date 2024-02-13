package com.coin.mapper.ext;

import com.coin.req.ReportsReq;
import com.coin.resp.ReportsResp;
import com.coin.resp.report.ReportsLeaderboard;
import com.coin.resp.report.ReportsStatistics;
import com.coin.resp.search.SearchReportVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ReportsExtMapper {

    List<ReportsResp> selectByExample(@Param("req") ReportsReq req);

    List<ReportsStatistics> getStatistics();

    List<ReportsLeaderboard> getLeaderBoard(@Param("req") ReportsReq req);

    Long countByUserId(@Param("userId") Long userId);

    List<SearchReportVo> searchReport(@Param("keyword") String keyword);

    int addVisitsAmount(@Param("reportId") Long reportId, @Param("addNum") int addNum);
}
