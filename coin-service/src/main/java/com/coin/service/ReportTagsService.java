package com.coin.service;

import com.coin.entity.ReportTags;
import com.coin.req.ReportTagsReq;
import com.coin.resp.report.TagsVo;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ReportTagsService {

    void add(ReportTagsReq req);

    void delete(ReportTagsReq req);

    void update(ReportTagsReq req);

    ReportTags getById(Long id);

    PageInfo<ReportTags> pageList(ReportTagsReq req);

    List<TagsVo> getTags();
}
