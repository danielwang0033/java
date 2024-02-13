package com.coin.service;

import com.coin.entity.GuessType;
import com.coin.req.GuessTypeReq;
import com.coin.resp.guess.GuessTypeVo;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Set;

public interface GuessTypeService {

    void add(GuessTypeReq req);

    void delete(GuessTypeReq req);

    void update(GuessTypeReq req);

    GuessType getById(Long id);

    PageInfo<GuessType> typeList(GuessTypeReq req);

    List<GuessTypeVo> getAvailableList();

    List<GuessType> selectByIdList(Set<Long> typeIdList);
}
