package com.coin.service.impl;

import com.coin.entity.UserExpLogs;
import com.coin.entity.UserExpLogsExample;
import com.coin.mapper.UserExpLogsMapper;
import com.coin.req.UserExpLogsReq;
import com.coin.service.UserExpLogsService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class UserExpLogsServiceImpl implements UserExpLogsService {

    private static final Logger logger = LoggerFactory.getLogger(UserExpLogsServiceImpl.class);

    @Resource
    private UserExpLogsMapper userExpLogsMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void add(UserExpLogsReq req) {
        Date now = new Date();
        UserExpLogs userExpLogs = new UserExpLogs();
        userExpLogs.setUserId(req.getUserId());
        userExpLogs.setExp(req.getExp());
        userExpLogs.setInfo(req.getInfo());
        userExpLogs.setCreatedAt(now);
        userExpLogs.setUpdatedAt(now);
        userExpLogsMapper.insertSelective(userExpLogs);
    }

    @Override
    public PageInfo<UserExpLogs> pageList(UserExpLogsReq req) {
        UserExpLogsExample example = new UserExpLogsExample();
        UserExpLogsExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(req.getUserId());
        example.setOrderByClause(" id desc");
        PageHelper.startPage(req.getPage(), req.getPagesize());
        List<UserExpLogs> datas = userExpLogsMapper.selectByExample(example);
        return new PageInfo<>(datas);
    }

    @Override
    public PageInfo<UserExpLogs> clientPageList(UserExpLogsReq req) {
        UserExpLogsExample example = new UserExpLogsExample();
        UserExpLogsExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(Math.toIntExact(req.getLoginUserId()));
        example.setOrderByClause(" id desc");
        PageHelper.startPage(req.getPage(), req.getPagesize());
        List<UserExpLogs> datas = userExpLogsMapper.selectByExample(example);
        return new PageInfo<>(datas);
    }
}
