package com.coin.mapper.ext;

import com.coin.req.RoomsReq;
import com.coin.resp.RoomsResp;

import java.util.List;

public interface RoomsExtMapper {

    List<RoomsResp> selectList(RoomsReq req);
}