package com.coin.mapper.ext;

import com.coin.req.AdminUsersReq;
import com.coin.resp.admin.AdminUsersVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminUsersExtMapper {

    List<AdminUsersVo> adminList(@Param("req") AdminUsersReq req);

    AdminUsersVo findById(@Param("id") Long id);
}
