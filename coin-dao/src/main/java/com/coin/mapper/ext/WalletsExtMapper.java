package com.coin.mapper.ext;

import com.coin.cache.UserBobiVo;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public interface WalletsExtMapper {

    int subtractBalance(@Param("id") Long id, @Param("amount") BigDecimal amount);

    int increaseBalance(@Param("id") Long id, @Param("amount") BigDecimal amount);

    List<UserBobiVo> selectByUserIdList(@Param("list") Set<Long> userIdList);

    UserBobiVo selectByUserId(@Param("userId") Long userId);

}
