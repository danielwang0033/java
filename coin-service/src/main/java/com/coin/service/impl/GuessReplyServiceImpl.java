package com.coin.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.coin.entity.GuessReply;
import com.coin.entity.GuessReplyExample;
import com.coin.entity.Users;
import com.coin.mapper.GuessReplyMapper;
import com.coin.mapper.ext.GuessReplyExtMapper;
import com.coin.req.GuessReplyReq;
import com.coin.resp.guess.GuessReplyVo;
import com.coin.service.DictService;
import com.coin.service.GuessReplyService;
import com.coin.service.GuessService;
import com.coin.service.UsersService;
import com.coin.service.asyn.AsyncHandleService;
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
import java.util.List;

@Service
public class GuessReplyServiceImpl implements GuessReplyService {

    private static final Logger logger = LoggerFactory.getLogger(GuessReplyServiceImpl.class);

    @Resource
    private DictService dictService;

    @Resource
    private GuessReplyMapper guessReplyMapper;

    @Resource
    private GuessReplyExtMapper guessReplyExtMapper;

    @Resource
    private GuessService guessService;


    @Resource
    private AsyncHandleService asyncHandleService;

    @Resource
    private UsersService usersService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void add(GuessReplyReq req) {
        Date now = new Date();
        GuessReply guessReply = new GuessReply();
        guessReply.setGuessId(req.getGuessId());
        guessReply.setUserId(req.getUserId());
        guessReply.setUserName(req.getUserName());
        guessReply.setContent(req.getContent());
        guessReply.setStatus(req.getStatus());
        guessReply.setCreatedAt(now);
        guessReply.setUpdatedAt(now);
        guessReplyMapper.insertSelective(guessReply);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(GuessReplyReq req) {
        GuessReply oldContest = guessReplyMapper.selectByPrimaryKey(req.getId());
        if (ObjectUtil.isNull(oldContest)) {
            throw new BizException(CodeCons.ERROR, "记录不存在");
        }
        Date now = new Date();
        GuessReply updateGuessReply = new GuessReply();
        updateGuessReply.setId(req.getId());
        updateGuessReply.setGuessId(req.getGuessId());
        updateGuessReply.setUserId(req.getUserId());
        updateGuessReply.setUserName(req.getUserName());
        updateGuessReply.setContent(req.getContent());
        updateGuessReply.setStatus(req.getStatus());
        updateGuessReply.setUpdatedAt(now);
        guessReplyMapper.updateByPrimaryKeySelective(updateGuessReply);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(GuessReplyReq req) {
        GuessReply guessReply = getById(req.getId());
        Long guessId = guessReply.getGuessId();
        GuessReply update = new GuessReply();
        update.setId(guessReply.getId());
        update.setStatus(0);
        update.setUpdatedAt(new Date());
        guessReplyMapper.updateByPrimaryKeySelective(update);
        // 评论删除成功, 评论数加-1
        asyncHandleService.subtractGuessCommentAmount(guessId);
    }

    @Override
    public GuessReply getById(Long id) {
        GuessReply guessReply = guessReplyMapper.selectByPrimaryKey(id);
        if (ObjectUtil.isNull(guessReply)) {
            throw new BizException(CodeCons.ERROR, "记录不存在");
        }
        return guessReply;
    }

    @Override
    public PageInfo<GuessReply> pageList(GuessReplyReq req) {
        GuessReplyExample example = new GuessReplyExample();
        GuessReplyExample.Criteria criteria = example.createCriteria();
        if (ObjectUtil.isNotNull(req.getGuessId())) {
            criteria.andGuessIdEqualTo(req.getGuessId());
        }
        if (ObjectUtil.isNotNull(req.getUserId())) {
            criteria.andUserIdEqualTo(req.getUserId());
        }
        if (StrUtil.isNotBlank(req.getUserName())) {
            criteria.andUserNameLike("%" + req.getUserName() + "%");
        }
        criteria.andStatusEqualTo(1);
        example.setOrderByClause(" id desc");
        PageHelper.startPage(req.getPage(), req.getPagesize());
        List<GuessReply> datas = guessReplyMapper.selectByExample(example);
        return new PageInfo<>(datas);
    }

    @Override
    public void reply(GuessReplyReq req) {
        Long guessId = req.getGuessId();
        guessService.getById(guessId);
        Users user = usersService.selectById(req.getLoginUserId());
        Date now = new Date();
        GuessReply guessReply = new GuessReply();
        guessReply.setGuessId(guessId);
        guessReply.setUserId(user.getId());
        guessReply.setUserName(user.getName());
        guessReply.setContent(req.getContent());
        guessReply.setStatus(1);
        guessReply.setCreatedAt(now);
        guessReplyMapper.insertSelective(guessReply);
        // 评论成功, 评论数加1
        asyncHandleService.addGuessCommentAmount(guessId);
    }

    @Override
    public PageInfo<GuessReplyVo> replyList(GuessReplyReq req) {
        PageHelper.startPage(req.getPage(), req.getPagesize());
        List<GuessReplyVo> datas = guessReplyExtMapper.replyList(req.getId());
        String defaultUserAvatar = dictService.getDefaultUserAvatar();
        datas.forEach(item -> {
            if (StrUtil.isBlank(item.getUserAvatar())) {
                item.setUserAvatar(ImageUtil.completeImageUrl(defaultUserAvatar));
            } else {
                item.setUserAvatar(ImageUtil.completeImageUrl(item.getUserAvatar()));
            }
        });
        return new PageInfo<>(datas);
    }
}
