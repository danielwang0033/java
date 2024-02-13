package com.coin.service;

import com.coin.entity.AdminRoles;
import com.coin.req.AdminRolesReq;
import com.github.pagehelper.PageInfo;

public interface AdminRolesService {

    PageInfo<AdminRoles> pageList(AdminRolesReq req);

    void add(AdminRolesReq req);

    void update(AdminRolesReq req);

    AdminRoles getBySlug(String slug);
}
