package com.coin.mapper.ext;

import com.coin.resp.OrdersResp;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrdersExtMapper {
    List<OrdersResp> selectByLeaderboard(@Param("created_at_ge") String created_at_ge);
}