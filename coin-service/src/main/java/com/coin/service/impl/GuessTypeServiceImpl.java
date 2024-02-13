package com.coin.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.coin.entity.GuessType;
import com.coin.entity.GuessTypeExample;
import com.coin.mapper.GuessTypeMapper;
import com.coin.mapper.ext.GuessTypeExtMapper;
import com.coin.req.GuessTypeReq;
import com.coin.resp.guess.GuessTypeVo;
import com.coin.service.GuessService;
import com.coin.service.GuessTypeService;
import com.coin.service.asyn.AsyncHandleService;
import com.coin.service.constant.CodeCons;
import com.coin.service.exception.BizException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
public class GuessTypeServiceImpl implements GuessTypeService {

    private static final Logger logger = LoggerFactory.getLogger(GuessTypeServiceImpl.class);

    private static final int[] STATUS_ALLOW = new int[]{1, 0};

    @Resource
    private GuessTypeMapper guessTypeMapper;

    @Resource
    private GuessTypeExtMapper guessTypeExtMapper;

    @Resource
    private GuessService guessService;

    @Resource
    private AsyncHandleService asyncHandleService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void add(GuessTypeReq req) {
        // 校验名称和别名
        if (CollectionUtil.isNotEmpty(checkType(req.getName(), null))) {
            throw new BizException(CodeCons.ERROR, "名称已存在");
        }
        if (CollectionUtil.isNotEmpty(checkType(null, req.getAlias()))) {
            throw new BizException(CodeCons.ERROR, "别名已存在");
        }
        Date now = new Date();
        GuessType guessType = new GuessType();
        guessType.setName(req.getName());
        guessType.setAlias(req.getAlias());
        guessType.setColor(req.getColor());
        guessType.setStatus(1);
        guessType.setImage(req.getImage());
        guessType.setCreatedAt(now);
        guessType.setUpdatedAt(now);
        guessTypeMapper.insertSelective(guessType);
    }

    private List<GuessType> checkType(String name, String alias) {
        GuessTypeExample example = new GuessTypeExample();
        GuessTypeExample.Criteria criteria = example.createCriteria();
        if (StrUtil.isNotBlank(name)) {
            criteria.andNameEqualTo(name);
        }
        if (StrUtil.isNotBlank(alias)) {
            criteria.andAliasEqualTo(alias);
        }
        return guessTypeMapper.selectByExample(example);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(GuessTypeReq req) {
        GuessType oldContest = guessTypeMapper.selectByPrimaryKey(req.getId());
        if (ObjectUtil.isNull(oldContest)) {
            throw new BizException(CodeCons.ERROR, "记录不存在");
        }
        String name = req.getName();
        String alias = req.getAlias();
        Integer status = req.getStatus();
        if (ObjectUtil.isNotNull(status) && !ArrayUtil.contains(STATUS_ALLOW, status)) {
            throw new BizException(CodeCons.ERROR, "竞猜状态异常");
        }
        if (StrUtil.isNotBlank(name) && !StrUtil.equals(oldContest.getName(), name)) {
            if (CollectionUtil.isNotEmpty(checkType(req.getName(), null))) {
                throw new BizException(CodeCons.ERROR, "名称已存在");
            }
        }
        if (StrUtil.isNotBlank(alias) && !StrUtil.equals(oldContest.getAlias(), alias)) {
            if (CollectionUtil.isNotEmpty(checkType(null, req.getAlias()))) {
                throw new BizException(CodeCons.ERROR, "别名已存在");
            }
        }
        Date now = new Date();
        GuessType updateGuessType = new GuessType();
        updateGuessType.setId(req.getId());
        updateGuessType.setName(req.getName());
        updateGuessType.setAlias(req.getAlias());
        updateGuessType.setColor(req.getColor());
        updateGuessType.setStatus(req.getStatus());
        updateGuessType.setImage(req.getImage());
        updateGuessType.setUpdatedAt(now);
        guessTypeMapper.updateByPrimaryKeySelective(updateGuessType);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(GuessTypeReq req) {
        Long id = req.getId();
        GuessType guessType = guessTypeMapper.selectByPrimaryKey(id);
        if (ObjectUtil.isNull(guessType)) {
            throw new BizException(CodeCons.ERROR, "记录不存在");
        }
        // 校验是否已近关联竞猜
        Long count = guessService.countByGuessType(id);
        if (count > 0) {
            throw new BizException(CodeCons.ERROR, "已关联竞猜");
        }
        guessTypeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public GuessType getById(Long id) {
        GuessType guessType = guessTypeMapper.selectByPrimaryKey(id);
        if (ObjectUtil.isNull(guessType)) {
            throw new BizException(CodeCons.ERROR, "记录不存在");
        }
        return guessType;
    }

    @Override
    public PageInfo<GuessType> typeList(GuessTypeReq req) {
        GuessTypeExample example = new GuessTypeExample();
        PageHelper.startPage(req.getPage(), req.getPagesize());
        List<GuessType> datas = guessTypeMapper.selectByExample(example);
        return new PageInfo<>(datas);
    }

    @Override
    public List<GuessTypeVo> getAvailableList() {
        return guessTypeExtMapper.getAvailableList();
    }

    @Override
    public List<GuessType> selectByIdList(Set<Long> typeIdList) {
        GuessTypeExample example = new GuessTypeExample();
        GuessTypeExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(CollectionUtil.newArrayList(typeIdList));
        return guessTypeMapper.selectByExample(example);
    }
}
