package com.coin.mapper.ext;

import com.coin.req.ReportReplyReq;
import com.coin.resp.ReportReplyResp;
import com.coin.resp.report.ReportReplyCountVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ReportReplyExtMapper {

    List<ReportReplyResp> pageList(@Param("req") ReportReplyReq req);

    List<ReportReplyCountVo> countByReportId(@Param("idList") List<Long> idList);
}
