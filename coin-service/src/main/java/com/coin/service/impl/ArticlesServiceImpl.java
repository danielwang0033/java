package com.coin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.coin.entity.*;
import com.coin.enums.LikeAndFavTypeEnum;
import com.coin.mapper.ArticleCommentsMapper;
import com.coin.mapper.ArticlesMapper;
import com.coin.mapper.ext.ArticlesExtMapper;
import com.coin.req.ArticlesReq;
import com.coin.req.FavoritesReq;
import com.coin.req.article.ArticlesVoReq;
import com.coin.req.search.SearchReq;
import com.coin.resp.ArticlesResp;
import com.coin.resp.article.ArticleReplyCountVo;
import com.coin.resp.article.ArticleUserVo;
import com.coin.resp.article.ArticlesVoResp;
import com.coin.resp.fav.FavVo;
import com.coin.resp.like.LikeVo;
import com.coin.resp.search.SearchArticleVo;
import com.coin.resp.search.UserShortVo;
import com.coin.resp.user.UserSimpleInfoVo;
import com.coin.service.*;
import com.coin.service.asyn.AsyncHandleService;
import com.coin.service.constant.CodeCons;
import com.coin.service.exception.BizException;
import com.coin.service.util.ContentUtil;
import com.coin.service.util.ImageUtil;
import com.coin.service.util.PageUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ArticlesServiceImpl implements ArticlesService {

    private static final Logger logger = LoggerFactory.getLogger(ArticlesServiceImpl.class);

    @Resource
    private ArticlesMapper articlesMapper;
    @Resource
    private ArticleGroupsService articleGroupsService;
    @Resource
    private LikesService likesService;
    @Resource
    private FavoritesService favoritesService;
    @Resource
    private UsersService usersService;
    @Resource
    private ArticleCommentsService articleCommentsService;
    @Resource
    private ArticleCommentsMapper articleCommentsMapper;
    @Resource
    private ArticlesExtMapper articlesExtMapper;
    @Resource
    private DictService dictService;
    @Resource
    private AsyncHandleService asyncHandleService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void add(ArticlesReq req) {
        Date now = new Date();
        Articles articles = new Articles();
        articles.setTitle(req.getTitle());
        articles.setDescription(StrUtil.isBlank(req.getDescription()) ? "" : req.getDescription());
        articles.setContent(req.getContent());
        articles.setUserId(req.getUserId());
        articles.setUserName(req.getUserName());
        articles.setArtGroupId(req.getArtGroupId());
        articles.setCreatedAt(now);
        articles.setUpdatedAt(now);
        ContentUtil.processArticlesContent(articles);
        articlesMapper.insertSelective(articles);
    }

    @Override
    public void addVisits(ArticlesReq req) {
        if (req.getVisits() == 0) {
            return;
        }
        Articles articles = articlesMapper.selectByPrimaryKey(req.getId());
        if (articles.getVisits() + req.getVisits() < 0) {
            throw new BizException(CodeCons.ERROR, "更新后值为负");
        }
        Date now = new Date();
        Articles newInfo = new Articles();
        newInfo.setId(req.getId());
        newInfo.setVisits(articles.getVisits() + req.getVisits());
        newInfo.setUpdatedAt(now);
        articlesMapper.updateByPrimaryKeySelective(newInfo);
    }

    @Override
    public void addLikeCount(ArticlesReq req) {
        if (req.getIncrLikes() == 0) {
            return;
        }
        Articles oldArticle = articlesMapper.selectByPrimaryKey(req.getId());
        if (oldArticle.getIncrLikes() + req.getIncrLikes() < 0) {
            throw new BizException(CodeCons.ERROR, "更新后值为负");
        }
        Date now = new Date();
        Articles articles = new Articles();
        articles.setId(req.getId());
        articles.setIncrLikes(oldArticle.getIncrLikes() + req.getIncrLikes());
        articles.setUpdatedAt(now);
        articlesMapper.updateByPrimaryKeySelective(articles);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(ArticlesReq req) {
        Articles oldContest = articlesMapper.selectByPrimaryKey(req.getId());
        if (ObjectUtil.isNull(oldContest)) {
            throw new BizException(CodeCons.ERROR, "记录不存在");
        }
        Date now = new Date();
        Articles updateArticles = new Articles();
        updateArticles.setId(req.getId());
        updateArticles.setTitle(req.getTitle());
        updateArticles.setContent(req.getContent());
        updateArticles.setUserId(req.getUserId());
        updateArticles.setUserName(req.getUserName());
        updateArticles.setVisits(req.getVisits());
        updateArticles.setArtGroupId(req.getArtGroupId());
        updateArticles.setUpdatedAt(now);
        ContentUtil.processArticlesContent(updateArticles);
        articlesMapper.updateByPrimaryKeySelective(updateArticles);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(ArticlesReq req) {
        Long id = req.getId();
        Articles articles = articlesMapper.selectByPrimaryKey(id);
        if (ObjectUtil.isNotNull(articles)) {
            articlesMapper.deleteByPrimaryKey(id);
            // 删除所有已收藏的记录
            favoritesService.deleteArticle(id);
        }
    }

    @Override
    public Articles getById(Long id) {
        Articles articles = articlesMapper.selectByPrimaryKey(id);
        if (ObjectUtil.isNull(articles)) {
            throw new BizException(CodeCons.ERROR, "记录不存在");
        }
        return articles;
    }

    @Override
    public PageInfo<ArticlesResp> pageList(ArticlesReq req) {
        ArticlesExample example = new ArticlesExample();
        ArticlesExample.Criteria criteria = example.createCriteria();
        if (req.getId() != null) {
            criteria.andIdEqualTo(req.getId());
        }
        if (req.getUserId() != null) {
            criteria.andUserIdEqualTo(req.getUserId());
        }
        if (req.getArtGroupId() != null) {
            criteria.andArtGroupIdEqualTo(req.getArtGroupId());
        }
        if (req.getCreatedAtMin() != null) {
            criteria.andCreatedAtGreaterThanOrEqualTo(req.getCreatedAtMin());
        }
        if (req.getCreatedAtMax() != null) {
            criteria.andCreatedAtLessThanOrEqualTo(req.getCreatedAtMax());
        }
        if (StrUtil.isNotBlank(req.getUserName())) {
            criteria.andUserNameLike("%" + req.getUserName() + "%");
        }
        example.setOrderByClause(" id desc");
        PageHelper.startPage(req.getPage(), req.getPagesize());
        List<Articles> datas = articlesMapper.selectByExample(example);
        PageInfo<Articles> page = new PageInfo<>(datas);
        List<ArticlesResp> rspList = datas.stream().map(this::convertRsp).collect(Collectors.toList());
        return PageUtil.pageInfo2PageRsp(page, rspList);
    }

    private ArticlesResp convertRsp(Articles info) {
        ContentUtil.processArticlesContent(info);
        ArticlesResp resp = new ArticlesResp();
        BeanUtils.copyProperties(info, resp);
        try {
            ArticleGroups articleGroups = articleGroupsService.getById(info.getArtGroupId());
            resp.setArtGroupName(articleGroups.getName());

            ArticleCommentsExample example = new ArticleCommentsExample();
            ArticleCommentsExample.Criteria criteria = example.createCriteria();
            criteria.andArticleIdEqualTo(info.getId());
            long comments = articleCommentsMapper.countByExample(example);
            resp.setComments(Math.toIntExact(comments));
        } catch (Exception e) {
            logger.error(CodeCons.ERROR, "ArticlesResp-convertRsp-error", e);
        }
        return resp;
    }

    @Override
    public PageInfo<ArticlesVoResp> lists(ArticlesVoReq req) {
        ArticlesExample example = new ArticlesExample();
        ArticlesExample.Criteria criteria = example.createCriteria();
        criteria.andStatusEqualTo(1);

        if (ObjectUtil.isNotNull(req.getGroupId())) {
            Long groupId = Long.parseLong(req.getGroupId());
            criteria.andArtGroupIdEqualTo(groupId);
        }
        String type = req.getType();
        if (ObjectUtil.isNotNull(type)) {
            switch (type) {
                case "2":
                    example.setOrderByClause(" visits desc, created_at desc");
                    break;
                case "3":
                    Long userId = req.getUserId();
                    if (ObjectUtil.isNull(userId)) {
                        throw new BizException(CodeCons.ERROR, "用户编号为空");
                    }
                    criteria.andUserIdEqualTo(userId);
                    example.setOrderByClause(" visits desc, created_at desc");
                    break;
                case "1":
                default:
                    example.setOrderByClause(" created_at desc, visits desc");
            }
        } else {
            example.setOrderByClause(" created_at desc, visits desc");
        }
        PageHelper.startPage(req.getPage(), req.getPagesize());
        List<Articles> datas = articlesMapper.selectByExample(example);
        PageInfo<Articles> page = new PageInfo<>(datas);
        List<ArticlesVoResp> rspList = getArticlesVoResps(datas);
        return PageUtil.pageInfo2PageRsp(page, rspList);
    }

    @Override
    public ArticlesVoResp getDetailById(ArticlesReq req) {
        Articles articles = getById(req.getId());
        ContentUtil.processArticlesContent(articles);
        ArticlesVoResp articlesVoResp = BeanUtil.copyProperties(articles, ArticlesVoResp.class);
        Long loginUserId = req.getLoginUserId();
        if (ObjectUtil.isNotNull(loginUserId)) {
            Likes likes = likesService.findByType(req.getLoginUserId(), articles.getId(), LikeAndFavTypeEnum.ARTICLE);
            if (ObjectUtil.isNotNull(likes)) {
                articlesVoResp.setCurrentUserLiked(1);
            }
            Favorites favorites = favoritesService.findByType(req.getLoginUserId(), articles.getId(), LikeAndFavTypeEnum.ARTICLE);
            if (ObjectUtil.isNotNull(favorites)) {
                articlesVoResp.setCurrentUserFavorited(1);
            }
        }
        // 统计点赞数
        Long likeCount = likesService.countById(articles.getId(), LikeAndFavTypeEnum.ARTICLE);
        articlesVoResp.setLikeCount(likeCount + articles.getIncrLikes());
        // 用户信息
        ArticleUserVo userVo = usersService.selectArticleUser(articles.getUserId(), req.getLoginUserId());
        articlesVoResp.setUser(userVo);
        // 点赞收藏状态
        if (ObjectUtil.isNotNull(req.getLoginUserId())) {
            Likes likes = likesService.findByType(req.getLoginUserId(), articles.getId(), LikeAndFavTypeEnum.ARTICLE);
            if (ObjectUtil.isNotNull(likes)) {
                articlesVoResp.setLikeStatus(true);
            }
            Favorites favorites = favoritesService.findByType(req.getLoginUserId(), articles.getId(), LikeAndFavTypeEnum.ARTICLE);
            if (ObjectUtil.isNotNull(favorites)) {
                articlesVoResp.setFavStatus(true);
            }
        }
        // 累计访问数
        asyncHandleService.addArticleVisitsAmount(articlesVoResp.getId());
        return articlesVoResp;
    }

    @Override
    public PageInfo<ArticlesVoResp> favoriteArticles(FavoritesReq req) {
        req.setFavoriteableType("App\\Models\\Article");
        PageInfo<Favorites> page = favoritesService.favoriteThreads(req);
        List<Favorites> list = page.getList();
        List<ArticlesVoResp> rspList = new ArrayList<>();
        if (CollectionUtil.isNotEmpty(list)) {
            List<Long> favIdList = list.stream().map(Favorites::getFavoriteableId).collect(Collectors.toList());
            rspList = findByIdList(favIdList);
        }
        return PageUtil.pageInfo2PageRsp(page, rspList);
    }

    @Override
    public List<Articles> findList(List<Long> idList) {
        ArticlesExample example = new ArticlesExample();
        ArticlesExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(ListUtil.toList(idList));
        return articlesMapper.selectByExample(example);
    }

    @Override
    public List<ArticlesVoResp> findByIdList(List<Long> idList) {
        ArticlesExample example = new ArticlesExample();
        ArticlesExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(ListUtil.toList(idList));
        List<Articles> datas = articlesMapper.selectByExample(example);
        return getArticlesVoResps(datas);
    }

    private List<ArticlesVoResp> getArticlesVoResps(List<Articles> datas) {
        if (CollectionUtil.isNotEmpty(datas)) {
            List<Long> idList = datas.stream().map(Articles::getId).collect(Collectors.toList());

            Set<Long> artGroupIdSet = datas.stream().map(Articles::getArtGroupId).collect(Collectors.toSet());
            List<ArticleGroups> articleGroupsList = articleGroupsService.findByIdList(artGroupIdSet);
            Map<Long, ArticleGroups> groupMap = articleGroupsList.stream().collect(Collectors.toMap(ArticleGroups::getId, Function.identity()));

            Set<Long> userIdSet = datas.stream().map(Articles::getUserId).collect(Collectors.toSet());
            List<UserSimpleInfoVo> userSimpleInfoVos = usersService.selectUserSimpleInfoByIdList(userIdSet);
            Map<Long, UserSimpleInfoVo> userNameMap = userSimpleInfoVos.stream().collect(Collectors.toMap(UserSimpleInfoVo::getId, Function.identity()));

            // 统计回复数
            List<ArticleReplyCountVo> countByArticleIdList = articleCommentsService.countByArticleIdList(idList);
            Map<Long, Long> countMap = countByArticleIdList.stream().collect(Collectors.toMap(ArticleReplyCountVo::getArticleId, ArticleReplyCountVo::getCount));
            return datas.stream().map(info -> this.convertVo(info, groupMap, countMap, userNameMap)).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    private ArticlesVoResp convertVo(Articles info, Map<Long, ArticleGroups> groupsMap, Map<Long, Long> countMap, Map<Long, UserSimpleInfoVo> userNameMap) {
        ContentUtil.processArticlesContent(info);
        ArticlesVoResp resp = new ArticlesVoResp();
        BeanUtils.copyProperties(info, resp, "thumbs");
        if (ObjectUtil.isNotNull(info.getThumbs())) {
            resp.setThumbs(JSONUtil.toList(info.getThumbs(), String.class));
        } else {
            resp.setThumbs(new ArrayList<>());
        }
        Long artGroupId = resp.getArtGroupId();
        resp.setGroup(groupsMap.get(artGroupId));

        UserSimpleInfoVo userSimpleInfoVo = userNameMap.get(resp.getUserId());
        if (ObjectUtil.isNotNull(userSimpleInfoVo)) {
            resp.setUserName(userSimpleInfoVo.getName());
            resp.setAvatar(ImageUtil.completeImageUrl(userSimpleInfoVo.getAvatar()));
        }

        Long count = countMap.get(resp.getId());
        if (ObjectUtil.isNotNull(count)) {
            resp.setComments(count);
        } else {
            resp.setComments(0L);
        }
        return resp;
    }

    @Override
    public LikeVo like(ArticlesVoReq req) {
        LikeVo result = new LikeVo();
        Articles byId = this.getById(req.getId());
        Likes byType = likesService.findByType(req.getLoginUserId(), byId.getId(), LikeAndFavTypeEnum.ARTICLE);
        if (ObjectUtil.isNull(byType)) {
            likesService.addLike(req.getLoginUserId(), byId.getId(), LikeAndFavTypeEnum.ARTICLE);
            result.setLikeStatus(true);
        } else {
            likesService.cancelLike(byType.getId());
            result.setLikeStatus(false);
        }
        // 统计点赞数
        Long count = likesService.countById(byId.getId(), LikeAndFavTypeEnum.ARTICLE);
        result.setLikeCount(count + byId.getIncrLikes());
        return result;
    }

    @Override
    public FavVo favorite(ArticlesVoReq req) {
        FavVo result = new FavVo();
        Articles byId = this.getById(req.getId());
        Favorites byType = favoritesService.findByType(req.getLoginUserId(), byId.getId(), LikeAndFavTypeEnum.ARTICLE);
        if (ObjectUtil.isNull(byType)) {
            favoritesService.addFav(req.getLoginUserId(), byId.getId(), LikeAndFavTypeEnum.ARTICLE);
            result.setFavStatus(true);
        } else {
            favoritesService.cancelFav(byType.getId());
            result.setFavStatus(false);
        }
        return result;
    }

    @Override
    public PageInfo<SearchArticleVo> searchArticle(SearchReq req) {
        if (StrUtil.isBlank(req.getKeyword().trim())) {
            return new PageInfo<>();
        }
        PageHelper.startPage(req.getPage(), req.getPagesize());
        List<SearchArticleVo> datas = articlesExtMapper.searchArticle(req.getKeyword());
        if (CollectionUtil.isNotEmpty(datas)) {
            Set<Long> userIdList = datas.stream().map(SearchArticleVo::getUserId).collect(Collectors.toSet());
            List<UserSimpleInfoVo> userSimpleInfoVos = usersService.selectUserSimpleInfoByIdList(userIdList);
            Map<Long, UserSimpleInfoVo> userSimpleInfoVoMap = userSimpleInfoVos.stream().collect(Collectors.toMap(UserSimpleInfoVo::getId, Function.identity()));
            String defaultUserAvatar = dictService.getDefaultUserAvatar();
            for (SearchArticleVo item : datas) {
                Long userId = item.getUserId();
                UserSimpleInfoVo userSimpleInfoVo = userSimpleInfoVoMap.get(userId);
                if (ObjectUtil.isNotNull(userSimpleInfoVo)) {
                    UserShortVo userSimpleVo = new UserShortVo();
                    userSimpleVo.setId(userSimpleInfoVo.getId());
                    userSimpleVo.setName(userSimpleInfoVo.getName());
                    if (StrUtil.isNotBlank(userSimpleInfoVo.getAvatar())) {
                        userSimpleVo.setAvatar(ImageUtil.completeImageUrl(userSimpleInfoVo.getAvatar()));
                    } else {
                        userSimpleVo.setAvatar(ImageUtil.completeImageUrl(defaultUserAvatar));
                    }
                    item.setUser(userSimpleVo);
                }
                item.setPostTime(item.getCreatedAt());

                // 处理关键字高亮
                String highlight1 = ContentUtil.processHighlight(item.getTitle(), req.getKeyword());
                item.setTitle(highlight1);

                String highlight2 = ContentUtil.processHighlight(item.getContent(), req.getKeyword());
                item.setContent(highlight2);
            }
        }
        return new PageInfo<>(datas);
    }
}
