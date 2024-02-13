package com.coin.mapper.ext;

import com.coin.entity.TDict;
import org.apache.ibatis.annotations.Param;

public interface TDictExtMapper {

    TDict getByTypeAndCode(@Param("type") String dictType, @Param("code") String dictCode);
}
