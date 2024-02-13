package com.coin.web.controller.client;

import com.coin.entity.Forums;
import com.coin.entity.ThreadTags;
import com.coin.entity.ThreadTopics;
import com.coin.req.*;
import com.coin.req.thread.ThreadsVoReq;
import com.coin.resp.MyResp;
import com.coin.resp.dict.ExtraMsgVo;
import com.coin.resp.fav.FavVo;
import com.coin.resp.like.LikeVo;
import com.coin.resp.thread.ForumSubNavsVo;
import com.coin.resp.thread.ThreadsDetailVo;
import com.coin.resp.thread.ThreadsVo;
import com.coin.service.*;
import com.coin.service.constant.CodeCons;
import com.coin.service.exception.BizException;
import com.coin.service.util.ParamUtil;
import com.coin.web.annotation.CommonSecure;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

// 03_论坛
@RestController
@RequestMapping("/forum")
public class ForumController {

    private static final Logger logger = LoggerFactory.getLogger(ForumController.class);

    @Resource
    private ForumsService forumsService;
    @Resource
    private ForumSubNavsService forumSubNavsService;
    @Resource
    private ThreadsService threadsService;
    @Resource
    private ThreadTagsService threadTagsService;
    @Resource
    private ThreadTopicsService threadTopicsService;
    @Resource
    private ThreadReplyService threadReplyService;

    // 01_获取论坛分类
    @GetMapping("/category")
    @CommonSecure(needLogin = false)
    public MyResp<List<Forums>> category(@RequestBody ForumsReq req) {
        logger.info("forums-category-req={}", req);
        try {
            List<Forums> list = forumsService.category();
            return new MyResp<>(CodeCons.SUCCESS, "", list);
        } catch (BizException e) {
            logger.error("forums-category-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("forums-category-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "查询失败");
    }

    // 02_发帖
    @PostMapping("/{category_alias}/newThread")
    @CommonSecure
    public MyResp<ExtraMsgVo> bbsNewThread(@RequestBody ThreadsReq req, @PathVariable("category_alias") String categoryAlias) {
        logger.info("forums-bbsNewThread-req={}", req);
        try {
            ParamUtil.check(req.getTitle(), "title", req.getContent(), "content", categoryAlias, "categoryAlias");
            req.setCategoryAlias(categoryAlias);
            ExtraMsgVo extraMsgVo = threadsService.bbsNewThread(req);
            return new MyResp<>(CodeCons.SUCCESS, "发帖成功", extraMsgVo);
        } catch (BizException e) {
            logger.error("forums-bbsNewThread-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("forums-bbsNewThread-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "发帖失败");
    }

    // 03_获取帖子标签列表
    @PostMapping("/tags")
    @CommonSecure(needLogin = false)
    public MyResp<List<ThreadTags>> tags(@RequestBody ForumsReq req) {
        logger.info("forums-tags-req={}", req);
        try {
            List<ThreadTags> list = threadTagsService.tags(req);
            return new MyResp<>(CodeCons.SUCCESS, "", list);
        } catch (BizException e) {
            logger.error("forums-tags-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("forums-tags-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "查询失败");
    }

    // 04_获取帖子列表
    @PostMapping("/{category_alias}/lists")
    @CommonSecure(needLogin = false)
    public MyResp<PageInfo<ThreadsVo>> threadLists(@RequestBody ThreadsVoReq req, @PathVariable("category_alias") String categoryAlias) {
        logger.info("forums-threadLists-req={}", req);
        try {
            ParamUtil.check(req.getPage(), "page", req.getPagesize(), "pageSize", categoryAlias, "categoryAlias");
            req.setCategoryAlias(categoryAlias);
            PageInfo<ThreadsVo> page = threadsService.threadLists(req);
            return new MyResp<>(CodeCons.SUCCESS, "", page);
        } catch (BizException e) {
            logger.error("forums-threadLists-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("forums-threadLists-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "查询失败");
    }

    // 05_获取论坛子导航列表
    @PostMapping("/{category_alias}/subNavs")
    @CommonSecure(needLogin = false)
    public MyResp<List<ForumSubNavsVo>> threadSubNavs(@RequestBody ForumSubNavsReq req, @PathVariable("category_alias") String categoryAlias) {
        logger.info("forums-threadSubNavs-req={}", req);
        try {
            ParamUtil.check(categoryAlias, "categoryAlias");
            req.setCategoryAlias(categoryAlias);
            List<ForumSubNavsVo> list = forumSubNavsService.threadSubNavs(req);
            return new MyResp<>(CodeCons.SUCCESS, "", list);
        } catch (BizException e) {
            logger.error("forums-threadSubNavs-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("forums-threadSubNavs-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "查询失败");
    }

    // 06_获取帖子话题列表
    @PostMapping("/{category_alias}/topics")
    @CommonSecure(needLogin = false)
    public MyResp<List<ThreadTopics>> threadTopics(@RequestBody ThreadTopicsReq req, @PathVariable("category_alias") String categoryAlias) {
        logger.info("forums-threadTopics-req={}", req);
        try {
            ParamUtil.check(categoryAlias, "categoryAlias");
            req.setCategoryAlias(categoryAlias);
            List<ThreadTopics> page = threadTopicsService.threadTopics(req);
            return new MyResp<>(CodeCons.SUCCESS, "", page);
        } catch (BizException e) {
            logger.error("forums-threadTopics-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("forums-threadTopics-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "查询失败");
    }

    // 07_获取全部帖子话题按阅读量排序
    @PostMapping("/allTopics")
    @CommonSecure(needLogin = false)
    public MyResp<List<ThreadTopics>> allTopics(@RequestBody ThreadTopicsReq req) {
        logger.info("forums-allTopics-req={}", req);
        try {
            List<ThreadTopics> page = threadTopicsService.allTopics(req);
            return new MyResp<>(CodeCons.SUCCESS, "", page);
        } catch (BizException e) {
            logger.error("forums-allTopics-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("forums-allTopics-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "查询失败");
    }

    // 08_获取帖子详情
    @PostMapping("/thread/{id}")
    @CommonSecure(needLogin = false)
    public MyResp<ThreadsDetailVo> threadById(@PathVariable("id") Long id, @RequestBody ThreadsReq req) {
        logger.info("forums-threadById-req={}", req);
        try {
            ParamUtil.check(id, "id");
            req.setId(id);
            ThreadsDetailVo vo = threadsService.threadById(req);
            return new MyResp<>(CodeCons.SUCCESS, "", vo);
        } catch (BizException e) {
            logger.error("forums-threadById-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("forums-threadById-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "查询失败");
    }

    // 09_发表回帖
    @PostMapping("/reply")
    @CommonSecure
    public MyResp<ExtraMsgVo> reply(@RequestBody ThreadReplyReq req) {
        logger.info("forums-reply-req={}", req);
        try {
            ParamUtil.check(req.getThreadId(), "threadId", req.getContent(), "content");
            ExtraMsgVo extraMsgVo = threadReplyService.reply(req);
            return new MyResp<>(CodeCons.SUCCESS, "回帖成功", extraMsgVo);
        } catch (BizException e) {
            logger.error("forums-reply-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("forums-reply-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "回帖失败");
    }

    // 11_编辑帖子
    @PostMapping("/modifyThread/{id}")
    @CommonSecure
    public MyResp<String> modifyThreadById(@PathVariable("id") Long id, @RequestBody ThreadsReq req) {
        logger.info("forums-modifyThreadById-req={}", req);
        try {
            ParamUtil.check(req.getContent(), "content", id, "id");
            req.setId(id);
            threadsService.modifyThreadById(req);
            return new MyResp<>(CodeCons.SUCCESS, "修改成功");
        } catch (BizException e) {
            logger.error("forums-modifyThreadById-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("forums-modifyThreadById-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "修改失败");
    }

    // 12_编辑回复
    @PostMapping("/modifyThreadReplyById/{id}")
    @CommonSecure
    public MyResp<String> modifyThreadReplyById(@PathVariable("id") Long id, @RequestBody ThreadReplyReq req) {
        logger.info("forums-modifyThreadReplyById-req={}", req);
        try {
            ParamUtil.check(id, "id", req.getContent(), "content");
            req.setId(id);
            threadReplyService.modifyThreadReplyById(req);
            return new MyResp<>(CodeCons.SUCCESS, "修改成功");
        } catch (BizException e) {
            logger.error("forums-modifyThreadReplyById-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("forums-modifyThreadReplyById-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "修改失败");
    }

    // 13_删除帖子
    @PostMapping("/deleteThread/{id}")
    @CommonSecure
    public MyResp<String> deleteThread(@PathVariable("id") Long id, @RequestBody ThreadsReq req) {
        logger.info("forums-deleteThread-req={}", req);
        try {
            ParamUtil.check(id, "id");
            threadsService.deleteThread(req, id);
            return new MyResp<>(CodeCons.SUCCESS, "删除成功");
        } catch (BizException e) {
            logger.error("forums-deleteThread-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("forums-deleteThread-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "删除失败");
    }

    // 14_删除回复
    @PostMapping("/deleteThreadReply/{id}")
    @CommonSecure
    public MyResp<String> deleteThreadReply(@PathVariable("id") Long id, @RequestBody ThreadReplyReq req) {
        logger.info("forums-deleteThreadReply-req={}", req);
        try {
            ParamUtil.check(id, "id");
            req.setId(id);
            threadReplyService.deleteThreadReply(req);
            return new MyResp<>(CodeCons.SUCCESS, "删除成功");
        } catch (BizException e) {
            logger.error("forums-deleteThreadReply-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("forums-deleteThreadReply-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "删除失败");
    }

    // 15_帖子点赞/取消点赞
    @PostMapping("/thread/toggleLike/{id}")
    @CommonSecure
    public MyResp<LikeVo> threadToggleLike(@PathVariable("id") Long id, @RequestBody ThreadsReq req) {
        logger.info("forums-threadToggleLike-req={}", req);
        try {
            ParamUtil.check(id, "id");
            req.setId(id);
            LikeVo like = threadsService.like(req);
            return new MyResp<>(CodeCons.SUCCESS, "操作成功", like);
        } catch (BizException e) {
            logger.error("forums-threadToggleLike-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("forums-threadToggleLike-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "操作失败");
    }

    // 17_帖子收藏
    @PostMapping("/thread/favorite/{id}")
    @CommonSecure
    public MyResp<FavVo> threadFavorite(@PathVariable("id") Long id, @RequestBody ThreadsReq req) {
        logger.info("forums-threadFavorite-req={}", req);
        try {
            ParamUtil.check(id, "id");
            req.setId(id);
            FavVo favorite = threadsService.favorite(req);
            return new MyResp<>(CodeCons.SUCCESS, "操作成功", favorite);
        } catch (BizException e) {
            logger.error("forums-threadFavorite-be", e);
            return new MyResp<>(e.getCode(), e.getErrMsg());
        } catch (Exception e) {
            logger.error("forums-threadFavorite-error", e);
        }
        return new MyResp<>(CodeCons.ERROR, "操作失败");
    }
}