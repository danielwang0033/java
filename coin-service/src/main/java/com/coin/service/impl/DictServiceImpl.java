package com.coin.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.coin.entity.TDict;
import com.coin.entity.TDictExample;
import com.coin.enums.CacheKeyEnum;
import com.coin.mapper.TDictMapper;
import com.coin.mapper.ext.TDictExtMapper;
import com.coin.req.DictReq;
import com.coin.req.dict.DictUpdateReq;
import com.coin.resp.dict.ModelSwitchVo;
import com.coin.resp.search.DictVo;
import com.coin.resp.search.SettingVo;
import com.coin.service.DictService;
import com.coin.service.constant.CodeCons;
import com.coin.service.exception.BizException;
import com.coin.service.util.ImageUtil;
import com.coin.service.util.LocalCache;
import com.coin.service.util.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

@Service
public class DictServiceImpl implements DictService {

    private static final Logger logger = LoggerFactory.getLogger(DictServiceImpl.class);

    @Resource
    private TDictMapper tDictMapper;

    @Resource
    private TDictExtMapper dictExtMapper;

    @Resource
    private RedisUtil redisUtil;

    @Override
    public List<TDict> getListByType(DictReq dictReq) {
        TDictExample example = new TDictExample();
        TDictExample.Criteria criteria = example.createCriteria();
        if (StrUtil.isNotBlank(dictReq.getDictType())) {
            criteria.andDictTypeEqualTo(dictReq.getDictType());
        }
        if (StrUtil.isNotBlank(dictReq.getDictCode())) {
            criteria.andDictCodeEqualTo(dictReq.getDictCode());
        }
        if (dictReq.getStatus() != null) {
            criteria.andStatusEqualTo(dictReq.getStatus());
        }
        if (dictReq.getOnlySortNum() != null && dictReq.getOnlySortNum() == 1) {
            example.setOrderByClause(" sort_num");
        } else {
            example.setOrderByClause(" dict_type, status desc, is_default desc, sort_num");
        }
        return tDictMapper.selectByExample(example);
    }

    @Override
    public TDict getById(Long id) {
        return tDictMapper.selectByPrimaryKey(id);
    }

    @Override
    public TDict getByTypeAndCode(String dictType, String dictCode) {
        return dictExtMapper.getByTypeAndCode(dictType, dictCode);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(DictReq req) {
        TDict dict = this.getById(req.getId());
        TDict update = new TDict();
        update.setId(dict.getId());
        update.setDictName(req.getDictName());
        update.setDictVal(req.getDictVal());
        update.setDictValBig(req.getDictValBig());
        update.setStatus(req.getStatus());
        update.setSortNum(req.getSortNum());
        update.setUpdateUser(req.getOptLoginName());
        update.setUpdateDate(new Date());
        logger.info("dict-service-update, dict={}", update);
        tDictMapper.updateByPrimaryKeySelective(update);
        this.clearCache();
    }

    @Override
    public String getDefaultUserAvatar() {
        String cacheAvatar = redisUtil.get(CacheKeyEnum.USER_DEFAULT_AVATAR.getKeyName());
        if (StrUtil.isBlank(cacheAvatar)) {
            TDict byTypeAndCode = this.getByTypeAndCode("users", "default_avatar");
            if (ObjectUtil.isNotNull(byTypeAndCode)) {
                String dictVal = byTypeAndCode.getDictVal();
                if (StrUtil.isNotBlank(dictVal)) {
                    redisUtil.set(CacheKeyEnum.USER_DEFAULT_AVATAR.getKeyName(), ImageUtil.completeImageUrl(dictVal));
                    return dictVal;
                }
            }
            return "defaultAvatar.png";
        }
        return cacheAvatar;
    }

    @Override
    public SettingVo getSetting() {
        SettingVo cache = LocalCache.getCache(LocalCache.SETTING_CACHE);
        if (cache == null) {
            SettingVo settingVo = new SettingVo();
            TDictExample example = new TDictExample();
            TDictExample.Criteria criteria = example.createCriteria();
            List<String> dictTypeList = new ArrayList<>();
            dictTypeList.add("announcement");
            dictTypeList.add("contact");
            criteria.andDictTypeIn(dictTypeList);
            criteria.andStatusEqualTo(1);
            List<TDict> dictList = tDictMapper.selectByExample(example);

            List<DictVo> contact = new ArrayList<>();
            List<String> announcement = new ArrayList<>();
            for (TDict item : dictList) {
                if ("contact".equals(item.getDictType())) {
                    DictVo dictVo = new DictVo();
                    dictVo.setDictCode(item.getDictCode());
                    dictVo.setDictVal(item.getDictVal());
                    contact.add(dictVo);
                } else if ("announcement".equals(item.getDictType())) {
                    announcement.add(item.getDictVal());
                }
            }
            settingVo.setContact(contact);
            settingVo.setAnnouncement(announcement);
            LocalCache.putCache(LocalCache.SETTING_CACHE, settingVo);
            return settingVo;
        }
        return cache;
    }

    @Override
    public List<ModelSwitchVo> modelSwitchConfig(DictReq req) {
        List<ModelSwitchVo> cache = LocalCache.getCache(LocalCache.MODEL_SWITCH_CACHE);
        if (cache == null) {
            TDictExample example = new TDictExample();
            TDictExample.Criteria criteria = example.createCriteria();
            criteria.andDictTypeEqualTo("module_switch");
            criteria.andStatusEqualTo(1);
            example.setOrderByClause("sort_num asc");
            List<TDict> dictList = tDictMapper.selectByExample(example);
            List<ModelSwitchVo> result = new ArrayList<>();
            for (TDict item : dictList) {
                ModelSwitchVo vo = new ModelSwitchVo();
                vo.setId(Integer.parseInt(item.getDictCode()));
                vo.setTitle(item.getDictName());
                vo.setView(item.getDictVal());
                result.add(vo);
            }
            LocalCache.putCache(LocalCache.MODEL_SWITCH_CACHE, result);
            return result;
        }
        return cache;
    }

    @Override
    public void updateBatch(DictUpdateReq req) {
        List<TDict> updateList = req.getUpdateList();
        if (CollectionUtil.isEmpty(updateList)) {
            throw new BizException(CodeCons.ERROR, "更新数据为空");
        }
        for (TDict item : updateList) {
            if (ObjectUtil.isNull(item.getId())) {
                throw new BizException(CodeCons.ERROR, "主键为空");
            }
            TDict oldDict = this.getById(item.getId());
            if (ObjectUtil.isNull(oldDict)) {
                continue;
            }
            TDict update = new TDict();
            update.setId(oldDict.getId());
            update.setDictName(item.getDictName());
            update.setDictVal(item.getDictVal());
            // update.setDictValBig(item.getDictValBig());
            update.setStatus(item.getStatus());
            update.setSortNum(item.getSortNum());
            update.setUpdateUser(req.getOptLoginName());
            update.setUpdateDate(new Date());
            logger.info("dict-service-update, dict={}", update);
            tDictMapper.updateByPrimaryKeySelective(update);
        }
        this.clearCache();
    }

    private void clearCache() {
        // 清除默认头像缓存
        redisUtil.remove(CacheKeyEnum.USER_DEFAULT_AVATAR.getKeyName());
        // 清除首页弹窗缓存
        LocalCache.deleteCache(LocalCache.EVERY_DAY_FIRST_POP_UPS);
        // 清理缓存
        LocalCache.deleteCache(LocalCache.SETTING_CACHE);
        // 模块开关
        LocalCache.deleteCache(LocalCache.MODEL_SWITCH_CACHE);
    }

    @Override
    public void refreshXssConfig() {
        logger.info("开始加载XSS配置");
        LocalCache.deleteCache(LocalCache.XSS_RICH_TEXT_FIELD);
        LocalCache.deleteCache(LocalCache.XSS_IGNORE_FIELD);
        Set<String> xssRichTextSet = this.initXssSet("xss_rich_text_field");
        Set<String> xssIgnoreSet = this.initXssSet("xss_ignore_field");
        LocalCache.putCache(LocalCache.XSS_RICH_TEXT_FIELD, xssRichTextSet);
        LocalCache.putCache(LocalCache.XSS_IGNORE_FIELD, xssIgnoreSet);
        logger.info("已加载: {}条XSS_RICH_TEXT_FIELD配置, {}条XSS_IGNORE_FIELD配置", xssRichTextSet.size(), xssIgnoreSet.size());
    }

    private Set<String> initXssSet(String dictType) {
        DictReq dictReq = new DictReq();
        dictReq.setDictType(dictType);
        List<TDict> richTextList = this.getListByType(dictReq);
        Set<String> richTextSet = new HashSet<>();
        for (TDict item : richTextList) {
            richTextSet.add(item.getDictCode());
        }
        return richTextSet;
    }
}
