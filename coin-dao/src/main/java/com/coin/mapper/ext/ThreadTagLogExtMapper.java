package com.coin.mapper.ext;

import com.coin.entity.ThreadTagLog;
import org.apache.ibatis.annotations.Param;

public interface ThreadTagLogExtMapper {

    ThreadTagLog findByTag(@Param("threadId") Long threadId, @Param("tag") String tag, @Param("action") int action);
}
