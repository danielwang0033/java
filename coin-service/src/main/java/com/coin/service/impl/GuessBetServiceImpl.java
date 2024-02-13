package com.coin.service.impl;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ObjectUtil;
import com.coin.entity.*;
import com.coin.i18n.I18nUtil;
import com.coin.i18n.LongTextTranslate;
import com.coin.mapper.GuessBetMapper;
import com.coin.mapper.ext.GuessBetExtMapper;
import com.coin.req.GuessBetReq;
import com.coin.req.UsersReq;
import com.coin.resp.guess.BetResultVo;
import com.coin.resp.guess.BetUserVo;
import com.coin.resp.guess.MyBetListVo;
import com.coin.service.*;
import com.coin.service.constant.CodeCons;
import com.coin.service.exception.BizException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class GuessBetServiceImpl implements GuessBetService {

    private static final Logger logger = LoggerFactory.getLogger(GuessBetServiceImpl.class);

    @Resource
    private GuessBetMapper guessBetMapper;

    @Resource
    private GuessBetExtMapper guessBetExtMapper;

    @Resource
    private GuessService guessService;

    @Resource
    private UsersService usersService;

    @Resource
    private WalletsService walletsService;

    @Resource
    private GuessItemService guessItemService;

    @Resource
    private GuessTypeService guessTypeService;

    @Override
    public PageInfo<BetUserVo> betUserList(GuessBetReq req) {
        PageHelper.startPage(req.getPage(), req.getPagesize());
        List<BetUserVo> datas = guessBetExtMapper.betUserList(req);
        return new PageInfo<>(datas);
    }

    @Override
    public PageInfo<MyBetListVo> myBetList(GuessBetReq req) {
        PageHelper.startPage(req.getPage(), req.getPagesize());
        List<MyBetListVo> datas = guessBetExtMapper.myBetList(req);
        return new PageInfo<>(datas);
    }

    @Override
    public PageInfo<BetResultVo> betResultList(GuessBetReq req) {
        PageHelper.startPage(req.getPage(), req.getPagesize());
        List<BetResultVo> datas = guessBetExtMapper.betResultList(req);
        return new PageInfo<>(datas);
    }

    @Override
    public List<BetUserVo> allBetList(Long guessId, Long guessItemId) {
        GuessBetReq req = new GuessBetReq();
        req.setGuessId(guessId);
        req.setGuessItemId(guessItemId);
        return guessBetExtMapper.betUserList(req);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void bet(GuessBetReq req) {
        Guess guess = guessService.getById(req.getGuessId());
        // 状态, 0: 手动关闭 1: 未开始 2:进行中 3:已结束
        Integer status = guess.getStatus();
        if (NumberUtil.equals(status, 0)) {
            throw new BizException(CodeCons.ERROR, "竞猜已关闭");
        }
        if (NumberUtil.equals(status, 1)) {
            throw new BizException(CodeCons.ERROR, "竞猜未开始");
        }
        if (NumberUtil.equals(status, 3)) {
            throw new BizException(CodeCons.ERROR, "竞猜已结束");
        }
        GuessType guessType = guessTypeService.getById(guess.getGuessTypeId());
        if (ObjectUtil.isNull(guessType)) {
            throw new BizException(CodeCons.ERROR, "竞猜类型不存在");
        }
        if (!NumberUtil.equals(guessType.getStatus(), 1)) {
            throw new BizException(CodeCons.ERROR, "竞猜类型未启用");
        }
        GuessItem guessItem = guessItemService.getById(req.getGuessItemId());
        if (!NumberUtil.equals(guessItem.getGuessId(), guess.getId())) {
            throw new BizException(CodeCons.ERROR, "此投注项不属于当前竞猜");
        }
        if (NumberUtil.equals(guessItem.getItemStatus(), 0)) {
            throw new BizException(CodeCons.ERROR, "投注项已关闭");
        }
        if (!NumberUtil.equals(guessItem.getSettleStatus(), 0)) {
            throw new BizException(CodeCons.ERROR, "投注项结算状态异常");
        }
        if (!NumberUtil.equals(guessItem.getSettlePattern(), 0)) {
            throw new BizException(CodeCons.ERROR, "投注项结算模式异常");
        }
        if (guessItem.getItemOdds().compareTo(BigDecimal.ONE) < 0) {
            throw new BizException(CodeCons.ERROR, "投注项赔率必须大于1");
        }

        Users user = usersService.selectById(req.getLoginUserId());
        // 扣减博币
        BigDecimal betAmount = req.getBetAmount();
        UsersReq usersReq = new UsersReq();
        usersReq.setId(user.getId());
        usersReq.setBetAmount(betAmount);

        LongTextTranslate longTextTranslateBean = I18nUtil.getLongTextTranslateBean();
        String betNote = longTextTranslateBean.buildBetNote(guess.getGuessSubject(), guess.getTitle(),
                guessItem.getItemName(), betAmount.intValue(), guessItem.getItemOdds());
//        String betNote = String.format("竞猜投注:[%s]%s,投注:%s,支付:%s(博币),赔率:%s",
//                guess.getGuessSubject(), guess.getTitle(),
//                guessItem.getItemName(), betAmount.intValue(),
//                guessItem.getItemOdds());
        usersReq.setBetNote(betNote);
        usersService.deductBobiForBet(usersReq);

        // 新增投注记录
        Date now = new Date();
        GuessBet guessBet = new GuessBet();
        guessBet.setUserId(user.getId());
        guessBet.setUserName(user.getName());
        guessBet.setGuessId(guess.getId());
        guessBet.setGuessItemId(guessItem.getId());
        guessBet.setGuessItemName(guessItem.getItemName());
        guessBet.setBetAmount(betAmount);
        guessBet.setBetOdds(guessItem.getItemOdds());
        guessBet.setSettlePattern(0);
        guessBet.setSettleStatus(0);
        guessBet.setAwardAmount(BigDecimal.ZERO);
        guessBet.setCreatedAt(now);
        guessBet.setUpdatedAt(now);
        // 增加投注计数
        boolean flag = guessItemService.addBetUserCount(guessBet.getGuessId(), guessBet.getGuessItemId(), guessBet.getUserId());
        if (flag) {
            guessBetMapper.insertSelective(guessBet);
        }
    }
}
