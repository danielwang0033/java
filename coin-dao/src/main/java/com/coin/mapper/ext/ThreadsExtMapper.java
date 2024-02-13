package com.coin.mapper.ext;

import com.coin.cache.UserThreadCountVo;
import com.coin.resp.search.SearchForumVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

public interface ThreadsExtMapper {

    List<UserThreadCountVo> countByUserIdList(@Param("list") Set<Long> userIdList);

    Long countByUserId(@Param("userId") Long userId);

    List<SearchForumVo> searchForum(@Param("keyword") String keyword);

    int addVisitsAmount(@Param("threadId") Long threadId, @Param("addNum") int addNum);
}
