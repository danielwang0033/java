package com.coin.service;

import com.coin.entity.AdminUsers;
import com.coin.req.AdminUsersReq;
import com.coin.req.NotificationsReq;
import com.coin.resp.admin.AdminUsersVo;

import java.util.List;

public interface AdminUsersService {

    void update(AdminUsersReq req);

    void sendAdminMsg(NotificationsReq req);

    AdminUsers getById(Long id);

    AdminUsers getByUsername(String username);

    List<AdminUsersVo> adminList(AdminUsersReq req);
}
