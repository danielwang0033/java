package com.coin.mapper;

import com.coin.entity.AdminRoles;
import com.coin.entity.AdminRolesExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminRolesMapper {
    long countByExample(AdminRolesExample example);

    int deleteByExample(AdminRolesExample example);

    int deleteByPrimaryKey(Long id);

    int insert(AdminRoles row);

    int insertSelective(AdminRoles row);

    List<AdminRoles> selectByExample(AdminRolesExample example);

    AdminRoles selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") AdminRoles row, @Param("example") AdminRolesExample example);

    int updateByExample(@Param("row") AdminRoles row, @Param("example") AdminRolesExample example);

    int updateByPrimaryKeySelective(AdminRoles row);

    int updateByPrimaryKey(AdminRoles row);
}