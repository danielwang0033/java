package com.coin.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.coin.entity.ArticleComments;
import com.coin.entity.ArticleCommentsExample;
import com.coin.entity.Articles;
import com.coin.enums.ApiExceptionEnum;
import com.coin.enums.NotificationType;
import com.coin.i18n.I18nUtil;
import com.coin.i18n.LongTextTranslate;
import com.coin.mapper.ArticleCommentsMapper;
import com.coin.mapper.ext.ArticleCommentsExtMapper;
import com.coin.req.ArticleCommentsReq;
import com.coin.req.NotificationsReq;
import com.coin.resp.article.ArticleReplyCountVo;
import com.coin.resp.dict.ExtraMsgVo;
import com.coin.resp.user.UserSimpleInfoVo;
import com.coin.service.*;
import com.coin.service.asyn.BobiAndExpService;
import com.coin.service.constant.CodeCons;
import com.coin.service.exception.BizException;
import com.coin.service.util.ImageUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ArticleCommentsServiceImpl implements ArticleCommentsService {

    private static final Logger logger = LoggerFactory.getLogger(ArticleCommentsServiceImpl.class);

    @Resource
    private ArticleCommentsMapper articleCommentsMapper;
    @Resource
    private ArticleCommentsExtMapper articleCommentsExtMapper;
    @Resource
    private ArticlesService articlesService;
    @Resource
    private UsersService usersService;
    @Resource
    private NotificationsService notificationsService;
    @Resource
    private BobiAndExpService bobiAndExpService;
    @Resource
    private DictService dictService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(ArticleCommentsReq req) {
        Long id = req.getId();
        articleCommentsMapper.deleteByPrimaryKey(id);
    }

    @Override
    public ArticleComments getById(Long id) {
        ArticleComments articleComments = articleCommentsMapper.selectByPrimaryKey(id);
        if (ObjectUtil.isNull(articleComments)) {
            throw new BizException(CodeCons.ERROR, "记录不存在");
        }
        return articleComments;
    }

    @Override
    public PageInfo<ArticleComments> pageList(ArticleCommentsReq req) {
        ArticleCommentsExample example = new ArticleCommentsExample();
        ArticleCommentsExample.Criteria criteria = example.createCriteria();
        if (req.getId() != null) {
            criteria.andIdEqualTo(req.getId());
        }
        if (ObjectUtil.isNotNull(req.getArticleId())) {
            criteria.andArticleIdEqualTo(req.getArticleId());
        }
        if (ObjectUtil.isNotNull(req.getUserId())) {
            criteria.andUserIdEqualTo(req.getUserId());
        }
        if (StrUtil.isNotBlank(req.getUserName())) {
            criteria.andUserNameLike("%" + req.getUserName().trim() + "%");
        }
        example.setOrderByClause(" id desc");
        PageHelper.startPage(req.getPage(), req.getPagesize());
        List<ArticleComments> datas = articleCommentsMapper.selectByExample(example);

        if (CollectionUtil.isNotEmpty(datas)) {
            List<Long> articleIdList = datas.stream().map(ArticleComments::getArticleId).collect(Collectors.toList());
            List<Articles> articlesList = articlesService.findList(articleIdList);
            if (CollectionUtil.isNotEmpty(articlesList)) {
                Map<Long, String> titleMap = articlesList.stream().collect(Collectors.toMap(Articles::getId, Articles::getTitle));
                datas.forEach(item -> item.setArticleTitle(titleMap.get(item.getArticleId())));
            }
        }
        return new PageInfo<>(datas);
    }

    @Override
    public List<ArticleReplyCountVo> countByArticleIdList(List<Long> articleIdList) {
        return articleCommentsExtMapper.countByArticleIdList(articleIdList);
    }

    @Override
    public ExtraMsgVo newComment(ArticleCommentsReq req) {
        Long loginUserId = req.getLoginUserId();
        UserSimpleInfoVo userSimpleInfoVo = usersService.selectUserSimpleInfoAndLevelById(loginUserId);
        if (ObjectUtil.isNull(userSimpleInfoVo)) {
            throw new BizException(CodeCons.ERROR, "用户不存在");
        }
        if (NumberUtil.equals(userSimpleInfoVo.getIsBanPost(), 1)) {
            throw new BizException(CodeCons.ERROR, ApiExceptionEnum.NO_PERMISSION_TO_POST_THREAD.getMessage());
        }
        Long articleId = req.getArticleId();
        Articles articles = articlesService.getById(articleId);

        Date now = new Date();
        ArticleComments articleComments = new ArticleComments();
        articleComments.setArticleId(articleId);
        articleComments.setUserId(loginUserId);
        articleComments.setUserName(userSimpleInfoVo.getName());
        articleComments.setContent(req.getContent());
        articleComments.setCreatedAt(now);
        articleCommentsMapper.insertSelective(articleComments);

        // 发消息
        String avatar = userSimpleInfoVo.getAvatar();
        if (StrUtil.isNotBlank(avatar)) {
            userSimpleInfoVo.setAvatar(ImageUtil.completeImageUrl(avatar));
        } else {
            String defaultUserAvatar = dictService.getDefaultUserAvatar();
            userSimpleInfoVo.setAvatar(ImageUtil.completeImageUrl(defaultUserAvatar));
        }
        NotificationsReq notificationsReq = new NotificationsReq();
        notificationsReq.setType("App\\Notifications\\ArticleBeReplied");
        notificationsReq.setNotifiableId(articles.getUserId());

        String str = (articles.getArtGroupId() == 1 ? I18nUtil.translateBiz("狗庄揭秘") : I18nUtil.translateBiz("行业资讯"));
        LongTextTranslate longTextTranslateBean = I18nUtil.getLongTextTranslateBean();
        String message = longTextTranslateBean.buildNotificationMessage(NotificationType.NEW_COMMENT, userSimpleInfoVo.getName(), str,  articles.getTitle());
        //notificationsReq.setMessage("<span style='color:#0390F6'>" + userSimpleInfoVo.getName() + "</span> 回复了您的" +  + "《<span style='color:#0390F6'>" + articles.getTitle() + "</span>》，快去看看吧！");
        notificationsReq.setMessage(message);
        Map<String, Object> objectMap = new HashMap<>();
        objectMap.put("replier", userSimpleInfoVo);
        objectMap.put("article", articles);
        notificationsReq.setObject(objectMap);
        notificationsService.buildNotification(notificationsReq);

        // 经验和博币
        return bobiAndExpService.replyOrComment(req.getLoginUserId(), articles.getUserId());
    }
}
