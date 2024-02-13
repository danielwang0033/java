package com.coin.mapper.ext;

import org.apache.ibatis.annotations.Param;

public interface GoodsExtMapper {
    int decrementByExample(@Param("id") Long id);
}