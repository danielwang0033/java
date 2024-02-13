package com.coin.service;

import com.coin.entity.ReportNameTags;
import com.coin.req.ReportNameTagsReq;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ReportNameTagsService {

    void add(ReportNameTagsReq req);

    void delete(ReportNameTagsReq req);

    void update(ReportNameTagsReq req);

    PageInfo<ReportNameTags> pageList(ReportNameTagsReq req);

    List<ReportNameTags> getNameTags();
}
