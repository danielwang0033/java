package com.coin.service;

import com.coin.entity.Users;
import com.coin.enums.DrawNumChangeTypeEnum;
import com.coin.enums.NameCardChangeTypeEnum;
import com.coin.req.AuthReq;
import com.coin.req.EmailsReq;
import com.coin.req.UsersReq;
import com.coin.req.search.SearchReq;
import com.coin.resp.UsersResp;
import com.coin.resp.article.ArticleUserVo;
import com.coin.resp.search.SearchUserVo;
import com.coin.resp.user.*;
import com.github.pagehelper.PageInfo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;

public interface UsersService {

    Long add(UsersReq req);

    void update(UsersReq req);

    void updateExp(UsersReq req);

    void updateBobi(UsersReq req);

    void deductBobiForBet(UsersReq req);

    void increaseBobiForBet(UsersReq req);

    UsersResp getById(Long id, Boolean needDetail);

    PageInfo<UsersResp> pageList(UsersReq req);

    Users selectByUserName(String username);

    Users selectByEmail(String email);

    Users selectById(Long id);

    void doClientRegister(UsersReq usersReq, HttpServletRequest request);

    String doClientLogin(UsersReq usersReq);

    void doClientLogout(UsersReq req);

    void updateClientUser(UsersReq req);

    void updateClientUserPwd(UsersReq req);

    List<UserSimpleInfoVo> selectUserSimpleInfoByIdList(Set<Long> userIdList);

    UserSimpleInfoVo selectUserSimpleInfoById(Long id);

    UserSimpleInfoVo selectUserSimpleInfoAndLevelById(Long id);

    PageInfo<UserRelationVo> selectFollowers(UsersReq req);

    PageInfo<UserRelationVo> selectFollowees(UsersReq req);

    UserVo selectUserVo(UsersReq req);

    ArticleUserVo selectArticleUser(Long userId, Long loginUserid);

    void sendMailApi(EmailsReq req);

    void sendVerifyEmail(UsersReq req, HttpServletRequest request);

    String verifyEmail(String uuid);

    void verifyResetPwdEmailCode(EmailsReq req);

    EmailsResp sendResetPasswordMail(UsersReq req);

    void resetPassword(EmailsReq req);

    void addExpByUserId(Long userId, Integer addExp, String info);

    PageInfo<SearchUserVo> searchUser(SearchReq req);

    void addDrawNumber(UsersReq req, DrawNumChangeTypeEnum changeTypeEnum);

    void addNameCard(UsersReq req, NameCardChangeTypeEnum cardChangeTypeEnum);

    String generateInviteCode(Long userId);

    Integer userDrawNumber(Long userId);

    String userInviteCode(Long userId);

    void deductionDrawNumber(Long userId);

    Users checkInviteCode(AuthReq req);

    PageInfo<UserInviteVo> inviteList(UsersReq req);

    void modifyEmail(UsersReq req);

    // void pwdTest(UsersReq req);

    String hiddenEmail(String email);
}
