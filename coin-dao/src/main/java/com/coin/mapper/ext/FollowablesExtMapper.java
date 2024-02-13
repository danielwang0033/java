package com.coin.mapper.ext;

import com.coin.entity.Followables;
import com.coin.resp.user.UserFolloweeCountVo;
import com.coin.resp.user.UserFollowerCountVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

public interface FollowablesExtMapper {

    Followables checkFollow(@Param("aUserId") Long aUserId, @Param("bUserId") Long bUserId);

    List<Long> checkAFollowBList(@Param("aUserId") Long aUser, @Param("bUserList") Set<Long> bUserList);

    Long countFollowerByUserId(@Param("userId") Long userId);

    Long countFolloweeByUserId(@Param("userId") Long userId);

    List<UserFollowerCountVo> countFollowerByUserIdList(@Param("userIdList") Set<Long> userIdList);

    List<UserFolloweeCountVo> countFolloweeByUserIdList(@Param("userIdList") Set<Long> userIdList);
}
