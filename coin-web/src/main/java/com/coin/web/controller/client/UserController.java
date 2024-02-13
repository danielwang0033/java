package com.coin.web.controller.client;

import cn.hutool.core.util.StrUtil;
import com.coin.entity.UserExpLogs;
import com.coin.req.*;
import com.coin.resp.MyResp;
import com.coin.resp.TransactionsResp;
import com.coin.resp.UsersResp;
import com.coin.resp.article.ArticlesVoResp;
import com.coin.resp.thread.ThreadReplyVo;
import com.coin.resp.thread.ThreadsVo;
import com.coin.resp.user.FollowResultVo;
import com.coin.resp.user.UserRelationVo;
import com.coin.resp.user.UserVo;
import com.coin.service.*;
import com.coin.service.asyn.BobiAndExpService;
import com.coin.service.constant.CodeCons;
import com.coin.service.exception.BizException;
import com.coin.service.util.ParamUtil;
import com.coin.web.annotation.CommonSecure;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

// 02_用户
@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Resource
    private UsersService usersService;
    @Resource
    private ThreadsService threadsService;
    @Resource
    private ThreadReplyService threadReplyService;
    @Resource
    private ArticlesService articlesService;
    @Resource
    private FollowablesService followablesService;
    @Resource
    private TransactionsService transactionsService;
    @Resource
    private UserExpLogsService userExpLogsService;
    @Resource
    private BobiAndExpService bobiAndExpService;

    // 01_获取用户信息
    @PostMapping("/{id}")
    @CommonSecure(needLogin = false)
    public MyResp<UserVo> getById(@PathVariable("id") Long id, @RequestBody UsersReq req) {
        logger.info("users-getById-req={}", req);
        try {
            ParamUtil.check(id, "ID");
            req.setId(id);
            UserVo rsp = usersService.selectUserVo(req);
            return new MyResp<>(CodeCons.SUCCESS, "", rsp);
        } catch (BizException e) {
            logger.error("users-getById-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("users-getById-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "查询失败");
    }

    // 02_获取当前用户信息
    @PostMapping
    @CommonSecure
    public MyResp<UserVo> user(@RequestBody UsersReq req) {
        logger.info("users-user-req");
        try {
            req.setId(req.getLoginUserId());
            UserVo rsp = usersService.selectUserVo(req);
            // 每日活跃
            bobiAndExpService.activeEveryDay(req.getLoginUserId());
            return new MyResp<>(CodeCons.SUCCESS, "", rsp);
        } catch (BizException e) {
            logger.error("users-user-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("users-user-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "查询失败");
    }

    // 03_修改用户信息
    @PostMapping("/updateUser")
    @CommonSecure
    public MyResp<String> updateUser(@RequestBody UsersReq req) {
        logger.info("users-updateUser-req={}", req);
        try {
            usersService.updateClientUser(req);
            return new MyResp<>(CodeCons.SUCCESS, "修改成功");
        } catch (BizException e) {
            logger.error("users-updateUser-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("users-updateUser-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "修改失败");
    }

    // 04_修改用户密码
    @PostMapping("/updatePassword")
    @CommonSecure
    public MyResp<String> updatePassword(@RequestBody UsersReq req) {
        logger.info("users-updatePassword-req={}", req);
        try {
            ParamUtil.check(req.getCurrentPassword(), "CurrentPassword",
                    req.getNewPassword(), "NewPassword", req.getNewPasswordConfirmation(), "NewPasswordConfirmation");
            if (!StrUtil.equals(req.getNewPassword(), req.getNewPasswordConfirmation())) {
                return new MyResp<>(CodeCons.ERROR, req.getNewPassword() + "两次输入的密码不一致" + req.getNewPasswordConfirmation());
            }
            usersService.updateClientUserPwd(req);
            return new MyResp<>(CodeCons.SUCCESS, "修改密码成功");
        } catch (BizException e) {
            logger.error("users-updatePassword-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("users-updatePassword-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "修改密码失败");
    }

    // 05_验证电子信箱
    @PostMapping("/sendVerifyEmail")
    @CommonSecure
    public MyResp<String> sendVerifyEmail(@RequestBody UsersReq req, HttpServletRequest request) {
        logger.info("users-sendVerifyEmail-req={}", req);
        try {
            usersService.sendVerifyEmail(req, request);
            return new MyResp<>(CodeCons.SUCCESS, "邮件即将送达");
        } catch (BizException e) {
            logger.error("users-sendVerifyEmail-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("users-sendVerifyEmail-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "发送失败");
    }

    // 06_关注
    @PostMapping("/follow/{id}")
    @CommonSecure
    public MyResp<FollowResultVo> follow(@PathVariable("id") Long followId, @RequestBody UsersReq req) {
        logger.info("users-follow-req={}", followId);
        try {
            ParamUtil.check(followId, "followId");
            FollowResultVo resultVo = followablesService.follow(req.getLoginUserId(), followId);
            return new MyResp<>(CodeCons.SUCCESS, "关注成功", resultVo);
        } catch (BizException e) {
            logger.error("users-follow-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("users-follow-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "关注失败");
    }

    // 07_取消关注
    @PostMapping("/unfollow/{id}")
    @CommonSecure
    public MyResp<FollowResultVo> unfollow(@PathVariable("id") Long followId, @RequestBody UsersReq req) {
        logger.info("users-unfollow-req={}", followId);
        try {
            ParamUtil.check(followId, "followId");
            FollowResultVo resultVo = followablesService.unfollow(req.getLoginUserId(), followId);
            return new MyResp<>(CodeCons.SUCCESS, "取消关注成功", resultVo);
        } catch (BizException e) {
            logger.error("users-unfollow-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("users-unfollow-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "取消关注失败");
    }

    // 08_我的粉丝列表
    @PostMapping("/myFollowers")
    @CommonSecure
    public MyResp<PageInfo<UserRelationVo>> myFollowers(@RequestBody UsersReq req) {
        logger.info("users-myFollowers-req={}", req);
        try {
            ParamUtil.check(req.getPage(), "page", req.getPagesize(), "pageSize");
            req.setId(req.getLoginUserId());
            PageInfo<UserRelationVo> list = usersService.selectFollowers(req);
            return new MyResp<>(CodeCons.SUCCESS, "", list);
        } catch (BizException e) {
            logger.error("users-myFollowers-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("users-myFollowers-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "查询失败");
    }

    // 09_获取用户粉丝列表
    @PostMapping("/followers/{id}")
    @CommonSecure(needLogin = false)
    public MyResp<PageInfo<UserRelationVo>> followers(@PathVariable("id") Long id, @RequestBody UsersReq req) {
        logger.info("users-pageList-req={}", req);
        try {
            ParamUtil.check(id, "id", req.getPage(), "page", req.getPagesize(), "pageSize");
            req.setId(id);
            PageInfo<UserRelationVo> list = usersService.selectFollowers(req);
            return new MyResp<>(CodeCons.SUCCESS, "", list);
        } catch (BizException e) {
            logger.error("users-followers-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("users-followers-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "查询失败");
    }

    // 10_我的关注列表
    @PostMapping("/myFollowees")
    @CommonSecure
    public MyResp<PageInfo<UserRelationVo>> myFollowees(@RequestBody UsersReq req) {
        logger.info("users-myFollowees-req={}", req);
        try {
            ParamUtil.check(req.getPage(), "page", req.getPagesize(), "pageSize");
            req.setId(req.getLoginUserId());
            PageInfo<UserRelationVo> list = usersService.selectFollowees(req);
            return new MyResp<>(CodeCons.SUCCESS, "", list);
        } catch (BizException e) {
            logger.error("users-myFollowees-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("users-myFollowees-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "查询失败");
    }

    // 11_获取用户关注列表
    @PostMapping("/followees/{id}")
    @CommonSecure(needLogin = false)
    public MyResp<PageInfo<UserRelationVo>> followees(@PathVariable("id") Long id, @RequestBody UsersReq req) {
        logger.info("users-pageList-req={}", req);
        try {
            ParamUtil.check(id, "id", req.getPage(), "page", req.getPagesize(), "pageSize");
            req.setId(id);
            PageInfo<UserRelationVo> list = usersService.selectFollowees(req);
            return new MyResp<>(CodeCons.SUCCESS, "", list);
        } catch (BizException e) {
            logger.error("users-followees-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("users-followees-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "查询失败");
    }

    // 12_我收藏的帖子
    @PostMapping("/favorite/threads")
    @CommonSecure
    public MyResp<PageInfo<ThreadsVo>> favoriteThreads(@RequestBody FavoritesReq req) {
        logger.info("users-favorite_threads-req={}", req);
        try {
            ParamUtil.check(req.getPage(), "page", req.getPagesize(), "pageSize");
            PageInfo<ThreadsVo> page = threadsService.favoriteThreads(req);
            return new MyResp<>(CodeCons.SUCCESS, "", page);
        } catch (BizException e) {
            logger.error("users-favoriteThreads-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("users-favoriteThreads-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "查询失败");
    }

    // 13_我收藏的资讯
    @PostMapping("/favorite/articles")
    @CommonSecure
    public MyResp<PageInfo<ArticlesVoResp>> favoriteArticles(@RequestBody FavoritesReq req) {
        logger.info("users-favoriteArticles-req={}", req);
        try {
            ParamUtil.check(req.getPage(), "page", req.getPagesize(), "pageSize");
            PageInfo<ArticlesVoResp> page = articlesService.favoriteArticles(req);
            return new MyResp<>(CodeCons.SUCCESS, "", page);
        } catch (BizException e) {
            logger.error("users-favoriteArticles-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("users-favoriteArticles-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "查询失败");
    }

    // 14_我发表的帖子
    @PostMapping("/threads")
    @CommonSecure
    public MyResp<PageInfo<ThreadsVo>> threads(@RequestBody FavoritesReq req) {
        logger.info("users-threads-req={}", req);
        try {
            ParamUtil.check(req.getPage(), "page", req.getPagesize(), "pageSize");
            PageInfo<ThreadsVo> page = threadsService.myThreads(req);
            return new MyResp<>(CodeCons.SUCCESS, "", page);
        } catch (BizException e) {
            logger.error("users-threads-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("users-threads-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "查询失败");
    }

    // 15_获取用户发表的帖子
    @PostMapping("/threads/{userId}")
    @CommonSecure(needLogin = false)
    public MyResp<PageInfo<ThreadsVo>> threadsByUserId(@PathVariable("userId") Long userId, @RequestBody FavoritesReq req) {
        logger.info("users-threadsByUserId-req={}", req);
        try {
            ParamUtil.check(req.getPage(), "page", req.getPagesize(), "pageSize", userId, "userId");
            req.setUserId(userId);
            PageInfo<ThreadsVo> page = threadsService.threadsByUserId(req);
            return new MyResp<>(CodeCons.SUCCESS, "", page);
        } catch (BizException e) {
            logger.error("users-threadsByUserId-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("users-threadsById-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "查询失败");
    }

    // 16_获取用户发表的回帖
    @PostMapping("/threadReply/{userId}")
    @CommonSecure(needLogin = false)
    public MyResp<PageInfo<ThreadReplyVo>> threadReplyByUserId(@PathVariable("userId") Long userId, @RequestBody ThreadReplyReq req) {
        logger.info("users-threadReplyByUserId-req={}", req);
        try {
            ParamUtil.check(req.getPage(), "page", req.getPagesize(), "pageSize", userId, "userId");
            req.setUserId(userId);
            PageInfo<ThreadReplyVo> page = threadReplyService.threadReplyByUserId(req);
            return new MyResp<>(CodeCons.SUCCESS, "", page);
        } catch (BizException e) {
            logger.error("users-threadReplyByUserId-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("users-threadReplyByUserId-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "查询失败");
    }

    // 17_我发表的回帖
    @PostMapping("/threadReply")
    @CommonSecure
    public MyResp<PageInfo<ThreadReplyVo>> threadReply(@RequestBody ThreadReplyReq req) {
        logger.info("users-threadReply-req={}", req);
        try {
            ParamUtil.check(req.getPage(), "page", req.getPagesize(), "pageSize");
            req.setUserId(req.getLoginUserId());
            PageInfo<ThreadReplyVo> page = threadReplyService.threadReplyByUserId(req);
            return new MyResp<>(CodeCons.SUCCESS, "", page);
        } catch (BizException e) {
            logger.error("users-threadReply-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("users-threadReply-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "查询失败");
    }

    // 19_获取通知详情
    @PostMapping("/user/notifications/{id}")
    @CommonSecure
    public MyResp<PageInfo<UsersResp>> notificationsById(@PathVariable("id") Long id, @RequestBody UsersReq req) {
        logger.info("users-notificationsById-req={}", req);
        try {
            ParamUtil.check(req.getPage(), "page", req.getPagesize(), "pageSize");
            PageInfo<UsersResp> page = usersService.pageList(req);
            return new MyResp<>(CodeCons.SUCCESS, "", page);
        } catch (BizException e) {
            logger.error("users-notificationsById-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("users-notificationsById-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "查询失败");
    }

    // 20_删除通知
    @PostMapping("/user/notifications/delete/{id}")
    @CommonSecure
    public MyResp<PageInfo<UsersResp>> notificationsDeleteById(@PathVariable("id") Long id, @RequestBody UsersReq req) {
        logger.info("users-notificationsDeleteById-req={}", req);
        try {
            ParamUtil.check(req.getPage(), "page", req.getPagesize(), "pageSize");
            PageInfo<UsersResp> page = usersService.pageList(req);
            return new MyResp<>(CodeCons.SUCCESS, "", page);
        } catch (BizException e) {
            logger.error("users-notificationsDeleteById-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("users-notificationsDeleteById-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "查询失败");
    }

    // 21_批量删除通知
    @PostMapping("/notifications/branchDelete")
    @CommonSecure
    public MyResp<PageInfo<UsersResp>> notificationsBranchDelete(@RequestBody UsersReq req) {
        logger.info("users-notificationsBranchDelete-req={}", req);
        try {
            ParamUtil.check(req.getPage(), "page", req.getPagesize(), "pageSize");
            PageInfo<UsersResp> page = usersService.pageList(req);
            return new MyResp<>(CodeCons.SUCCESS, "", page);
        } catch (BizException e) {
            logger.error("users-notificationsBranchDelete-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("users-notificationsBranchDelete-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "查询失败");
    }

    // 24_修改邮箱
    @PostMapping("/modifyEmail")
    @CommonSecure
    public MyResp<String> modifyEmail(@RequestBody UsersReq req) {
        logger.info("users-modifyEmail-req={}", req);
        try {
            ParamUtil.check(req.getEmail(), "email");
            usersService.modifyEmail(req);
            return new MyResp<>(CodeCons.SUCCESS, "修改成功");
        } catch (BizException e) {
            logger.error("users-modifyEmail-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("users-modifyEmail-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "修改失败");
    }

    // 25_我的博币记录
    @PostMapping("/myBobiRecords")
    @CommonSecure
    public MyResp<PageInfo<TransactionsResp>> myBobiRecords(@RequestBody UsersReq req) {
        logger.info("users-myBobiRecords-req={}", req);
        try {
            ParamUtil.check(req.getPage(), "page", req.getPagesize(), "pageSize");
            PageInfo<TransactionsResp> page = transactionsService.getBobiLogList(req);
            return new MyResp<>(CodeCons.SUCCESS, "", page);
        } catch (BizException e) {
            logger.error("users-myBobiRecords-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("users-myBobiRecords-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "查询失败");
    }

    // 26_我的经验记录
    @PostMapping("/myExpRecords")
    @CommonSecure
    public MyResp<PageInfo<UserExpLogs>> myExpRecords(@RequestBody UserExpLogsReq req) {
        logger.info("users-myExpRecords-req={}", req);
        try {
            ParamUtil.check(req.getPage(), "page", req.getPagesize(), "pageSize");
            PageInfo<UserExpLogs> page = userExpLogsService.clientPageList(req);
            return new MyResp<>(CodeCons.SUCCESS, "", page);
        } catch (BizException e) {
            logger.error("users-myExpRecords-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("users-myExpRecords-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "查询失败");
    }

    // 27_发邮件接口(仅测试)
    @PostMapping("/sendMailApi")
    @CommonSecure
    public MyResp<String> sendMailApi(@RequestBody EmailsReq req) {
        logger.info("users-sendMail-req={}", req);
        try {
            ParamUtil.check(req.getPage(), "page", req.getPagesize(), "pageSize");
            usersService.sendMailApi(req);
            return new MyResp<>(CodeCons.SUCCESS, "", "");
        } catch (BizException e) {
            logger.error("users-sendMail-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("users-sendMail-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "发送失败");
    }
}