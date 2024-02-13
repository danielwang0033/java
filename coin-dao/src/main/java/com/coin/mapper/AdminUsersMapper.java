package com.coin.mapper;

import com.coin.entity.AdminUsers;
import com.coin.entity.AdminUsersExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminUsersMapper {
    long countByExample(AdminUsersExample example);

    int deleteByExample(AdminUsersExample example);

    int deleteByPrimaryKey(Long id);

    int insert(AdminUsers row);

    int insertSelective(AdminUsers row);

    List<AdminUsers> selectByExample(AdminUsersExample example);

    AdminUsers selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") AdminUsers row, @Param("example") AdminUsersExample example);

    int updateByExample(@Param("row") AdminUsers row, @Param("example") AdminUsersExample example);

    int updateByPrimaryKeySelective(AdminUsers row);

    int updateByPrimaryKey(AdminUsers row);
}