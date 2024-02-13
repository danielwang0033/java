package com.coin.mapper.ext;

import com.coin.entity.Users;
import com.coin.req.UsersReq;
import com.coin.resp.UsersResp;
import com.coin.resp.search.SearchUserVo;
import com.coin.resp.user.UserPwdVo;
import com.coin.resp.user.UserSimpleInfoVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

public interface UsersExtMapper {

    Users selectByName(@Param("name") String name);

    List<UsersResp> selectJoinRoomByNameAndStatus(@Param("name") String name, @Param("status") String status, @Param("type") Integer type);

    List<UserSimpleInfoVo> selectUserSimpleInfoByIdList(@Param("idList") Set<Long> userIdList);

    UserSimpleInfoVo selectUserSimpleInfoById(@Param("id") Long id);

    int addUsedNum(@Param("id") Long userId, @Param("RouletteUsedTime") Integer RouletteUsedTime);

    Users selectByEmail(@Param("email") String email);

    int addExpByUserId(@Param("userId") Long userId, @Param("addExp") Integer addExp);

    List<SearchUserVo> searchUser(@Param("keyword") String keyword);

    Integer userDrawNumber(@Param("userId") Long userId);

    String userInviteCode(@Param("userId") Long userId);

    int deductionDrawNumber(@Param("userId") Long userId);

    int modifyEmail(@Param("userId") Long userId, @Param("oldEmail") String oldEmail, @Param("newEmail") String newEmail);

    List<UserPwdVo> pwdTest(UsersReq req);
}