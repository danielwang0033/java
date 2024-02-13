package com.coin.service;

import com.coin.entity.ReportReasons;
import com.coin.req.ReportReasonsReq;
import com.coin.resp.report.ReasonsVo;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Set;

public interface ReportReasonsService {

    void add(ReportReasonsReq req);

    void delete(ReportReasonsReq req);

    void update(ReportReasonsReq req);

    ReportReasons getById(Long id);

    PageInfo<ReportReasons> pageList(ReportReasonsReq req);

    List<ReasonsVo> getReasons();

    List<ReportReasons> getList(Set<Long> reasonIdSet);
}
