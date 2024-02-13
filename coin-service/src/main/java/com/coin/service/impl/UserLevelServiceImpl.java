package com.coin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.coin.cache.UserLevelCache;
import com.coin.entity.UserLevel;
import com.coin.entity.UserLevelExample;
import com.coin.enums.CacheKeyEnum;
import com.coin.mapper.UserLevelMapper;
import com.coin.req.UserLevelReq;
import com.coin.resp.user.UserLevelVo;
import com.coin.service.UserLevelService;
import com.coin.service.util.ImageUtil;
import com.coin.service.util.RedisUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class UserLevelServiceImpl implements UserLevelService {

    private static final Logger logger = LoggerFactory.getLogger(UserLevelServiceImpl.class);
    private static final String USER_LEVEL_LIST_KEY = CacheKeyEnum.USER_LEVEL_LIST.getKeyName();
    @Resource
    private UserLevelMapper userLevelMapper;
    @Resource
    private RedisUtil redisUtil;

    private static UserLevelVo getUserLevelVo(Integer exp, List<UserLevelCache> userLevels) {
        UserLevelVo userLevelVo = new UserLevelVo();
        int len = userLevels.size();
        int idx1 = 0;
        int idx2 = 1;
        boolean flag = false;
        for (int i = 0; i < len; i++) {
            UserLevelCache item = userLevels.get(i);
            Integer needExp = item.getNeedExp();
            if (needExp > exp) {
                if (i >= len - 1) {
                    idx1 = len - 1;
                    idx2 = len - 1;
                } else {
                    idx1 = i - 1;
                    idx2 = i;
                }
                flag = true;
                break;
            }
        }
        if (flag) {
            if (idx1 < 0) {
                idx1 = 0;
            }
            userLevelVo.setUserLevel(userLevels.get(idx1));
            userLevelVo.setNextLevel(userLevels.get(idx2));
        } else {
            userLevelVo.setUserLevel(userLevels.get(len - 1));
            userLevelVo.setNextLevel(userLevels.get(len - 1));
        }
        return userLevelVo;
    }

    @Override
    public PageInfo<UserLevel> pageList(UserLevelReq req) {
        UserLevelExample example = new UserLevelExample();
        UserLevelExample.Criteria criteria = example.createCriteria();
        if (req.getLevel() != null) {
            criteria.andLevelEqualTo(req.getLevel());
        }
        example.setOrderByClause(" level");
        PageHelper.startPage(req.getPage(), req.getPagesize());
        List<UserLevel> datas = userLevelMapper.selectByExample(example);
        return new PageInfo<>(datas);
    }

    @Override
    public void add(UserLevelReq req) {
        Date now = new Date();
        UserLevel userLevel = new UserLevel();
        userLevel.setLevel(req.getLevel());
        userLevel.setName(req.getName());
        userLevel.setBadge(req.getBadge());
        userLevel.setNeedExp(req.getNeedExp());
        userLevel.setPrivilege(req.getPrivilege());
        userLevel.setCreatedAt(now);
        userLevel.setUpdatedAt(now);
        userLevelMapper.insertSelective(userLevel);
        clearCache();
    }

    @Override
    public void update(UserLevelReq req) {
        Date now = new Date();
        UserLevel userLevel = new UserLevel();
        userLevel.setId(req.getId());
        userLevel.setLevel(req.getLevel());
        userLevel.setName(req.getName());
        userLevel.setBadge(req.getBadge());
        userLevel.setNeedExp(req.getNeedExp());
        userLevel.setPrivilege(req.getPrivilege());
        userLevel.setUpdatedAt(now);
        userLevelMapper.updateByPrimaryKeySelective(userLevel);
        clearCache();
    }

    @Override
    public void del(UserLevelReq req) {
        userLevelMapper.deleteByPrimaryKey(req.getId());
        clearCache();
    }

    @Override
    public UserLevelVo matchLevelByExp(Integer exp) {
        String userLevelsStr = redisUtil.get(USER_LEVEL_LIST_KEY);
        List<UserLevelCache> userLevelCacheList = JSONUtil.toList(userLevelsStr, UserLevelCache.class);
        if (StrUtil.isBlank(userLevelsStr) || CollectionUtil.isEmpty(userLevelCacheList)) {
            UserLevelExample example = new UserLevelExample();
            example.setOrderByClause(" need_exp asc");
            List<UserLevel> userLevels = userLevelMapper.selectByExample(example);
            userLevels.forEach(item -> {
                // 等级图片
                item.setBadge(ImageUtil.completeImageUrl(item.getBadge()));
            });

            List<UserLevelCache> initList = BeanUtil.copyToList(userLevels, UserLevelCache.class);
            redisUtil.set(USER_LEVEL_LIST_KEY, JSONUtil.toJsonStr(initList));
            userLevelCacheList = initList;
        }
        return getUserLevelVo(exp, userLevelCacheList);
    }

    private void clearCache() {
        redisUtil.remove(USER_LEVEL_LIST_KEY);
    }
}
