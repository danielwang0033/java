package com.coin.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.coin.entity.*;
import com.coin.i18n.I18nUtil;
import com.coin.mapper.AdSlotsMapper;
import com.coin.mapper.AdsMapper;
import com.coin.req.AdsReq;
import com.coin.resp.AdsResp;
import com.coin.resp.ad.AdSlotsRespVo;
import com.coin.resp.ad.AdsRespVo;
import com.coin.service.AdSlotsService;
import com.coin.service.AdsService;
import com.coin.service.DictService;
import com.coin.service.constant.CodeCons;
import com.coin.service.exception.BizException;
import com.coin.service.util.ImageUtil;
import com.coin.service.util.LocalCache;
import com.coin.service.util.PageUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AdsServiceImpl implements AdsService {

    private static final Logger logger = LoggerFactory.getLogger(AdsServiceImpl.class);

    @Resource
    private AdsMapper adsMapper;
    @Resource
    private AdSlotsService adSlotsService;
    @Resource
    private AdSlotsMapper adSlotsMapper;
    @Resource
    private DictService dictService;

    /**
     * 获取当天剩余毫秒数
     */
    private static long getTodayRemainSeconds() {
        Date now = new Date();
        DateTime endOfDay = DateUtil.endOfDay(now);
        return DateUtil.between(now, endOfDay, DateUnit.SECOND);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void add(AdsReq req) {
        Date now = new Date();
        Ads ads = new Ads();
        ads.setUuid(UUID.randomUUID().toString());
        ads.setAdSlotId(req.getAdSlotId());
        ads.setFile(req.getFile());
        ads.setLink(req.getLink());
        ads.setStart(req.getStart());
        ads.setEnd(req.getEnd());
        ads.setStatus(req.getStatus());
        ads.setCreatedAt(now);
        ads.setUpdatedAt(now);
        ads.setSort(req.getSort());
        adsMapper.insertSelective(ads);
        // 清空缓存
        clearCache();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(AdsReq req) {
        Ads oldContest = adsMapper.selectByPrimaryKey(req.getId());
        if (ObjectUtil.isNull(oldContest)) {
            throw new BizException(CodeCons.ERROR, "记录不存在");
        }
        Date now = new Date();
        Ads updateAds = new Ads();
        updateAds.setId(req.getId());
        updateAds.setUuid(req.getUuid());
        updateAds.setAdSlotId(req.getAdSlotId());
        updateAds.setFile(req.getFile());
        updateAds.setLink(req.getLink());
        updateAds.setStart(req.getStart());
        updateAds.setEnd(req.getEnd());
        updateAds.setStatus(req.getStatus());
        updateAds.setUpdatedAt(now);
        updateAds.setSort(req.getSort());
        adsMapper.updateByPrimaryKeySelective(updateAds);
        // 清空缓存
        clearCache();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(AdsReq req) {
        Long id = req.getId();
        adsMapper.deleteByPrimaryKey(id);
        // 清空缓存
        clearCache();
    }

    @Override
    public Ads getById(Long id) {
        Ads ads = adsMapper.selectByPrimaryKey(id);
        if (ObjectUtil.isNull(ads)) {
            throw new BizException(CodeCons.ERROR, "记录不存在");
        }
        return ads;
    }

    @Override
    public Map<String, AdSlotsRespVo> clientPageList(AdsReq req) {
        Map<String, AdSlotsRespVo> cache = LocalCache.getCache(String.format(LocalCache.AD_CACHE, req.getPlatform()));
        if (cache == null) {
            Map<String, AdSlotsRespVo> result = new HashMap<>();
            AdSlotsExample adSlotsExample = new AdSlotsExample();
            AdSlotsExample.Criteria criteria = adSlotsExample.createCriteria();
            criteria.andPlatformEqualTo(req.getPlatform());
            List<AdSlots> adSlots = adSlotsMapper.selectByExample(adSlotsExample);
            if (CollectionUtil.isNotEmpty(adSlots)) {
                List<Integer> slotIdList = adSlots.stream().map(i -> i.getId().intValue()).collect(Collectors.toList());
                Map<Integer, List<Ads>> adsMap = findAdBySlotList(slotIdList);
                if (CollectionUtil.isNotEmpty(adsMap)) {
                    for (AdSlots item : adSlots) {
                        List<Ads> adList = adsMap.get(Integer.parseInt(item.getId() + ""));
                        if (CollectionUtil.isEmpty(adList)) {
                            continue;
                        }
                        List<AdsRespVo> adsRespList = new ArrayList<>();
                        for (Ads ad : adList) {
                            AdsRespVo adsResp = new AdsRespVo();
                            adsResp.setFile(ImageUtil.completeImageUrl(ad.getFile()));
                            adsResp.setId(ad.getId());
                            adsResp.setLink(ad.getLink());
                            adsRespList.add(adsResp);
                        }
                        AdSlotsRespVo adSlotsResp = new AdSlotsRespVo();
                        adSlotsResp.setList(adsRespList);
                        result.put(item.getAlias(), adSlotsResp);
                    }
                }
            }
            // 缓存1小时
            LocalCache.putCache(String.format(LocalCache.AD_CACHE, req.getPlatform()), result, 3600);
            return result;
        }
        return cache;
    }

    private Map<Integer, List<Ads>> findAdBySlotList(List<Integer> slotIdList) {
        Date now = new Date();
        AdsExample adsExample = new AdsExample();
        AdsExample.Criteria criteria = adsExample.createCriteria();
        criteria.andStatusEqualTo(1);
        criteria.andAdSlotIdIn(slotIdList);
        // star<=now() and end>=now()
        criteria.andStartLessThanOrEqualTo(now);
        criteria.andEndGreaterThanOrEqualTo(now);

        adsExample.setOrderByClause("sort asc");
        List<Ads> adsList = adsMapper.selectByExample(adsExample);
        if (CollectionUtil.isNotEmpty(adsList)) {
            return adsList.stream().collect(Collectors.groupingBy(Ads::getAdSlotId, Collectors.toList()));
        }
        return null;
    }

    @Override
    public PageInfo<AdsResp> pageList(AdsReq req) {
        AdsExample example = new AdsExample();
        AdsExample.Criteria criteria = example.createCriteria();
        if (req.getPlatform() != null) {
            List<AdSlots> list = adSlotsService.getByPlatform(req.getPlatform());
            if (!CollectionUtils.isEmpty(list)) {
                List<Integer> slotIds = new ArrayList<>();
                for (AdSlots adSlots : list) {
                    slotIds.add(adSlots.getId().intValue());
                }
                criteria.andAdSlotIdIn(slotIds);
            }
        }
        if (req.getAdSlotId() != null) {
            criteria.andAdSlotIdEqualTo(req.getAdSlotId());
        }
        if (req.getStatus() != null) {
            Date now = new Date();
            if (req.getStatus() == 1) {
                criteria.andStatusEqualTo(0);
            } /*else if (req.getStatus() == 2) {
                criteria.andStartLessThanOrEqualTo(now);
                criteria.andEndGreaterThanOrEqualTo(now);
            } */ else if (req.getStatus() == 3) {
                criteria.andStatusEqualTo(1);
                criteria.andStartLessThanOrEqualTo(now);
                criteria.andEndGreaterThanOrEqualTo(now);
            }
        }
        example.setOrderByClause(" id");
        PageHelper.startPage(req.getPage(), req.getPagesize());
        List<Ads> datas = adsMapper.selectByExample(example);
        PageInfo<Ads> page = new PageInfo<>(datas);
        List<AdsResp> rspList = datas.stream().map(this::convertRsp).collect(Collectors.toList());
        return PageUtil.pageInfo2PageRsp(page, rspList);
    }

    private AdsResp convertRsp(Ads ads) {
        AdsResp resp = new AdsResp();
        BeanUtils.copyProperties(ads, resp);
        Date now = new Date();
        resp.setStatusStr("投放中");
        if (ads.getStatus() == 0) {
            resp.setStatusStr("广告未启用");
        } else if (ads.getStart().after(now) || ads.getEnd().before(now)) {
            resp.setStatusStr("不在投放时间范围");
        }
        AdSlots adSlots = adSlotsService.getById(Long.parseLong(ads.getAdSlotId().toString()));
        if (ObjectUtil.isNotNull(adSlots)) {
            resp.setAdSlotName(adSlots.getName());
            resp.setPlatform(adSlots.getPlatform());
        }
        // 国际化
        resp.setStatusStr(I18nUtil.translateBiz(resp.getStatusStr()));
        return resp;
    }

    @Override
    public List<String> popUps(AdsReq req) {
        String cacheKey = LocalCache.EVERY_DAY_FIRST_POP_UPS;
        String dictCode = "home_page_pop_ups";
        if (req.isH5Flag()) {
            cacheKey = cacheKey + "_h5";
            dictCode = dictCode + "_h5";
        }
        // 如果登录,则判断是否已读
        Long loginUserId = req.getLoginUserId();
        if (ObjectUtil.isNotNull(loginUserId)) {
            Object cache = LocalCache.getCache(cacheKey + loginUserId);
            // 如果缓存存在数据, 表示已经调用关闭弹窗接口
            if (ObjectUtil.isNotNull(cache)) {
                return null;
            }
        }
        List<String> cache = LocalCache.getCache(cacheKey);
        if (cache == null) {
            TDict dict = dictService.getByTypeAndCode("ad", dictCode);
            List<String> popUpsList = new ArrayList<>();
            if (ObjectUtil.isNotNull(dict)) {
                Integer status = dict.getStatus();
                if (ObjectUtil.isNotNull(status) && NumberUtil.equals(status, 1)) {
                    String popUpsStr = dict.getDictVal();
                    if (StrUtil.isNotBlank(popUpsStr)) {
                        String[] split = popUpsStr.split(",");
                        for (String item : split) {
                            popUpsList.add(ImageUtil.completeImageUrl(item));
                        }
                    }
                }
            }
            LocalCache.putCache(cacheKey, popUpsList);
            return popUpsList;
        }
        return cache;
    }

    @Override
    public void readPopUps(AdsReq req) {
        String cacheKey = LocalCache.EVERY_DAY_FIRST_POP_UPS;
        if (req.isH5Flag()) {
            cacheKey = cacheKey + "_h5";
        }
        Long loginUserId = req.getLoginUserId();
        if (ObjectUtil.isNotNull(loginUserId)) {
            // 计算当前时间距离当天结束多少毫秒
            LocalCache.putCache(cacheKey + loginUserId, loginUserId, getTodayRemainSeconds());
        }
    }

    // 清空缓存
    private void clearCache() {
        LocalCache.deleteCache(String.format(LocalCache.AD_CACHE, 1));
        LocalCache.deleteCache(String.format(LocalCache.AD_CACHE, 2));
    }
}
