package com.coin.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.coin.entity.Guess;
import com.coin.entity.GuessBet;
import com.coin.entity.GuessItem;
import com.coin.entity.GuessItemExample;
import com.coin.i18n.I18nUtil;
import com.coin.i18n.LongTextTranslate;
import com.coin.mapper.GuessBetMapper;
import com.coin.mapper.GuessItemMapper;
import com.coin.mapper.ext.GuessBetExtMapper;
import com.coin.mapper.ext.GuessExtMapper;
import com.coin.mapper.ext.GuessItemExtMapper;
import com.coin.req.GuessItemReq;
import com.coin.req.UsersReq;
import com.coin.resp.guess.BetUserVo;
import com.coin.resp.guess.GuessItemVo;
import com.coin.service.GuessBetService;
import com.coin.service.GuessItemService;
import com.coin.service.GuessService;
import com.coin.service.UsersService;
import com.coin.service.constant.CodeCons;
import com.coin.service.exception.BizException;
import com.coin.service.util.MD5Util;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

@Service
public class GuessItemServiceImpl implements GuessItemService {

    private static final Logger logger = LoggerFactory.getLogger(GuessItemServiceImpl.class);

    @Resource
    private GuessItemMapper guessItemMapper;

    @Resource
    private GuessItemExtMapper guessItemExtMapper;

    @Resource
    private GuessExtMapper guessExtMapper;

    @Resource
    private GuessService guessService;

    @Resource
    private GuessBetService guessBetService;

    @Resource
    private GuessBetMapper guessBetMapper;

    @Resource
    private GuessBetExtMapper guessBetExtMapper;

    @Resource
    private UsersService usersService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Long add(GuessItemReq req) {
        Guess guess = guessService.getById(req.getGuessId());
        Integer status = guess.getStatus();
        if (NumberUtil.equals(status, 0)) {
            throw new BizException(CodeCons.ERROR, "竞猜已关闭");
        }
        if (NumberUtil.equals(status, 3)) {
            throw new BizException(CodeCons.ERROR, "竞猜已结束");
        }
        checkItem(req);
        Date now = new Date();
        GuessItem guessItem = new GuessItem();
        guessItem.setGuessId(req.getGuessId());
        guessItem.setItemName(req.getItemName());
        guessItem.setItemStatus(1);
        guessItem.setItemOdds(req.getItemOdds());
        guessItem.setBetUserCount(0);
        guessItem.setSettleStatus(0);
        guessItem.setSettlePattern(0);
        guessItem.setSettleCount(0);
        guessItem.setSettleBetAmount(BigDecimal.ZERO);
        guessItem.setSettleAmount(BigDecimal.ZERO);
        guessItem.setSettleProfit(BigDecimal.ZERO);
        guessItem.setSettleTime(null);
        guessItem.setSettleNote("");
        guessItem.setCreatedAt(now);
        guessItem.setUpdatedAt(now);
        guessItemMapper.insertSelective(guessItem);
        return guessItem.getId();
    }

    private void checkItem(GuessItemReq req) {
        BigDecimal itemOdds = req.getItemOdds();
        if (itemOdds.compareTo(BigDecimal.ONE) < 0) {
            throw new BizException(CodeCons.ERROR, "赔率必须大于等于壹");
        }
        GuessItemExample example = new GuessItemExample();
        GuessItemExample.Criteria criteria = example.createCriteria();
        criteria.andGuessIdEqualTo(req.getGuessId());
        criteria.andItemNameEqualTo(req.getItemName());
        if (ObjectUtil.isNotNull(req.getId())) {
            criteria.andIdNotEqualTo(req.getId());
        }
        List<GuessItem> guessItems = guessItemMapper.selectByExample(example);
        if (CollectionUtil.isNotEmpty(guessItems)) {
            throw new BizException(CodeCons.ERROR, "竞猜项名称重复");
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(GuessItemReq req) {
        GuessItem guessItem = guessItemMapper.selectByPrimaryKey(req.getId());
        if (ObjectUtil.isNull(guessItem)) {
            throw new BizException(CodeCons.ERROR, "记录不存在");
        }
        Guess guess = guessService.getById(guessItem.getGuessId());
        Integer status = guess.getStatus();
        if (NumberUtil.equals(status, 0)) {
            throw new BizException(CodeCons.ERROR, "竞猜已关闭");
        }
        if (NumberUtil.equals(status, 2)) {
            throw new BizException(CodeCons.ERROR, "竞猜进行中");
        }
        if (NumberUtil.equals(status, 3)) {
            throw new BizException(CodeCons.ERROR, "竞猜已结束");
        }
        // 未开始
        if (NumberUtil.equals(status, 1)) {
            checkItem(req);
            Date now = new Date();
            GuessItem updateGuessItem = new GuessItem();
            updateGuessItem.setId(req.getId());
            updateGuessItem.setGuessId(req.getGuessId());
            updateGuessItem.setItemName(req.getItemName());
            updateGuessItem.setItemOdds(req.getItemOdds());
            updateGuessItem.setUpdatedAt(now);
            guessItemMapper.updateByPrimaryKeySelective(updateGuessItem);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(GuessItemReq req) {
        GuessItem guessItem = this.getById(req.getId());
        Guess guess = guessService.getById(guessItem.getGuessId());
        Integer status = guess.getStatus();
        if (NumberUtil.equals(status, 0)) {
            throw new BizException(CodeCons.ERROR, "竞猜已关闭");
        }
        if (NumberUtil.equals(status, 2)) {
            throw new BizException(CodeCons.ERROR, "竞猜进行中");
        }
        if (NumberUtil.equals(status, 3)) {
            throw new BizException(CodeCons.ERROR, "竞猜已结束");
        }
        Long id = req.getId();
        guessItemMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void deleteDirect(Long id) {
        guessItemMapper.deleteByPrimaryKey(id);
    }

    @Override
    public GuessItem getById(Long id) {
        GuessItem guessItem = guessItemMapper.selectByPrimaryKey(id);
        if (ObjectUtil.isNull(guessItem)) {
            throw new BizException(CodeCons.ERROR, "记录不存在");
        }
        return guessItem;
    }

    @Override
    public PageInfo<GuessItem> itemList(GuessItemReq req) {
        GuessItemExample example = new GuessItemExample();
        GuessItemExample.Criteria criteria = example.createCriteria();
        criteria.andGuessIdEqualTo(req.getGuessId());
        example.setOrderByClause(" id asc");
        PageHelper.startPage(req.getPage(), req.getPagesize());
        List<GuessItem> datas = guessItemMapper.selectByExample(example);
        return new PageInfo<>(datas);
    }

    @Override
    public List<GuessItemVo> guessItemAvailableList(Long guessId) {
        return guessItemExtMapper.guessItemList(guessId, 1);
    }

    @Override
    public List<GuessItemVo> guessItemAll(Long guessId) {
        return guessItemExtMapper.guessItemList(guessId, null);
    }

    @Override
    public boolean addBetUserCount(Long guessId, Long guessItemId, Long userId) {
        Long itemHistory = guessBetExtMapper.historyBet(guessId, guessItemId, userId);
        if (ObjectUtil.isNull(itemHistory)) {
            // 此投注项该投注人没有历史记录, 投数人数+1
            guessItemExtMapper.addBetUserCount(guessItemId);
            Long guessHistory = guessBetExtMapper.historyBet(guessId, null, userId);
            if (ObjectUtil.isNull(guessHistory)) {
                guessExtMapper.addBetUserCount(guessId);
            }
            return true;
        }
        return false;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void calcAward(GuessItemReq req) {
        GuessItem guessItem = this.getById(req.getId());
        Guess guess = guessService.getById(guessItem.getGuessId());
        Integer reqSettlePattern = req.getSettlePattern();
        // 结算状态, 0:待结算 1:结算中 2:结算完成 3:结算异常
        Integer status = guess.getStatus();
        if (NumberUtil.equals(status, 0)) {
            throw new BizException(CodeCons.ERROR, "竞猜已关闭");
        }
        if (NumberUtil.equals(status, 1)) {
            throw new BizException(CodeCons.ERROR, "竞猜未开始");
        }
        if (NumberUtil.equals(status, 2)) {
            throw new BizException(CodeCons.ERROR, "竞猜进行中");
        }
        // 竞猜结束, 计算投注项中奖金额
        if (NumberUtil.equals(status, 3)) {
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
                throw new BizException(CodeCons.ERROR, "投注项赔率不能小于一");
            }
            // 投注人数
            Set<Long> updateBetUserCount = new HashSet<>();
            // 结算笔数
            int updateSettleCount = 0;
            // 结算投注总额
            BigDecimal updateSettleBetAmount = BigDecimal.ZERO;
            // 结算总额
            BigDecimal updateSettleAmount = BigDecimal.ZERO;
            // 结算利润
            BigDecimal updateSettleProfit;
            // 查询当前投注项所有投注记录
            List<BetUserVo> betUserVos = guessBetService.allBetList(guess.getId(), guessItem.getId());
            for (BetUserVo betItem : betUserVos) {
                Long betItemId = betItem.getId();
                BigDecimal betOdds = betItem.getBetOdds();
                Integer betSettleStatus = betItem.getSettleStatus();
                Integer betSettlePattern = betItem.getSettlePattern();
                BigDecimal betAmount = betItem.getBetAmount();

                List<String> errorList = new ArrayList<>();
                if (!NumberUtil.equals(betSettlePattern, 0)) {
                    errorList.add("结算模式为:" + betSettlePattern);
                }
                if (!NumberUtil.equals(betSettleStatus, 0)) {
                    errorList.add("结算状态为:" + betSettlePattern);
                }
                // 校验投注赔率
                if (betOdds.compareTo(BigDecimal.ONE) < 0) {
                    errorList.add("投注项赔率为:" + betOdds + ",小于1");
                }
                if (betOdds.compareTo(guessItem.getItemOdds()) != 0) {
                    errorList.add("投注项赔率为:" + betOdds + ",与投注项不一致");
                }
                if (CollectionUtil.isNotEmpty(errorList)) {
                    updateExceptionRecord(betItem, errorList);
                    continue;
                }

                // 更新投注状态
                GuessBet updateBet = new GuessBet();
                updateBet.setId(betItemId);
                updateBet.setSettlePattern(reqSettlePattern);
                // 结算中
                updateBet.setSettleStatus(1);
                // 计算结算金额, 根据结算模式计算
                BigDecimal settleAmount = calcSettleAmountWithPattern(reqSettlePattern, betOdds, betAmount);
                // 计算本次投注投注人收益, 结算金额-下注金额 ,可以为负数
                BigDecimal settleProfit = settleAmount.subtract(betAmount);
                // 计算奖金, 如果本次投注投注人收益为负数,则中奖奖金为0, 否则奖金等于本次投注投注人收益
                BigDecimal awardAmount;
                if (settleProfit.compareTo(BigDecimal.ZERO) <= 0) {
                    awardAmount = BigDecimal.ZERO;
                } else {
                    awardAmount = settleProfit;
                }
                updateBet.setSettleAmount(settleAmount);
                updateBet.setSettleProfit(settleProfit);
                updateBet.setAwardAmount(awardAmount);
                updateBet.setUpdatedAt(new Date());
                guessBetMapper.updateByPrimaryKeySelective(updateBet);
                // 统计
                updateBetUserCount.add(betItem.getUserId());
                updateSettleCount = updateSettleCount + 1;
                updateSettleBetAmount = updateSettleBetAmount.add(betAmount);
                updateSettleAmount = updateSettleAmount.add(settleAmount);
            }
            // 结算利润,结算投注总额-结算总额,可以为负数
            updateSettleProfit = updateSettleBetAmount.subtract(updateSettleAmount);
            // 更新竞猜项状态
            GuessItem updateItem = new GuessItem();
            updateItem.setId(guessItem.getId());
            updateItem.setSettleStatus(1);
            updateItem.setSettlePattern(reqSettlePattern);
            updateItem.setBetUserCount(updateBetUserCount.size());
            updateItem.setSettleCount(updateSettleCount);
            updateItem.setSettleBetAmount(updateSettleBetAmount);
            updateItem.setSettleAmount(updateSettleAmount);
            updateItem.setSettleProfit(updateSettleProfit);
            updateItem.setSettleNote("结算金额计算成功");
            guessItemMapper.updateByPrimaryKeySelective(updateItem);
        } else {
            throw new BizException(CodeCons.ERROR, "投注项状态异常");
        }
    }

    private void updateExceptionRecord(BetUserVo betItem, List<String> errorList) {
        String errNote = "计算结算金额检测出异常!" + CollectionUtil.join(errorList, "|") + "数据可能被篡改";
        Long betItemId = betItem.getId();
        // 更新投注状态
        GuessBet updateBet = new GuessBet();
        updateBet.setId(betItemId);
        // 结算异常
        updateBet.setSettleStatus(3);
        updateBet.setSettleNote(errNote);
        updateBet.setUpdatedAt(new Date());
        guessBetMapper.updateByPrimaryKeySelective(updateBet);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void calcAwardRevoke(GuessItemReq req) {
        GuessItem guessItem = this.getById(req.getId());
        Guess guess = guessService.getById(guessItem.getGuessId());
        // 结算状态, 0:待结算 1:结算中 2:结算完成 3:结算异常
        Integer status = guess.getStatus();
        if (NumberUtil.equals(status, 0)) {
            throw new BizException(CodeCons.ERROR, "竞猜已关闭");
        }
        if (NumberUtil.equals(status, 1)) {
            throw new BizException(CodeCons.ERROR, "竞猜未开始");
        }
        if (NumberUtil.equals(status, 2)) {
            throw new BizException(CodeCons.ERROR, "竞猜进行中");
        }
        // 竞猜结束, 计算投注项中奖金额
        if (NumberUtil.equals(status, 3)) {
            if (!NumberUtil.equals(guessItem.getGuessId(), guess.getId())) {
                throw new BizException(CodeCons.ERROR, "此投注项不属于当前竞猜");
            }
            if (NumberUtil.equals(guessItem.getItemStatus(), 0)) {
                throw new BizException(CodeCons.ERROR, "投注项已关闭");
            }
            if (!NumberUtil.equals(guessItem.getSettleStatus(), 1)) {
                throw new BizException(CodeCons.ERROR, "只有结算中状态支持撤销操作");
            }
            // 查询当前投注项所有投注记录
            List<BetUserVo> betUserVos = guessBetService.allBetList(guess.getId(), guessItem.getId());
            for (BetUserVo betItem : betUserVos) {
                Long betItemId = betItem.getId();
                Integer betSettleStatus = betItem.getSettleStatus();
                if (!NumberUtil.equals(betSettleStatus, 1)) {
                    // 只处理投注记录结算状态为处理中的记录
                    continue;
                }
                // 回退投注状态
                GuessBet updateBet = new GuessBet();
                updateBet.setId(betItemId);
                updateBet.setSettlePattern(0);
                updateBet.setSettleStatus(0);
                updateBet.setSettleAmount(BigDecimal.ZERO);
                updateBet.setSettleProfit(BigDecimal.ZERO);
                updateBet.setAwardAmount(BigDecimal.ZERO);
                updateBet.setUpdatedAt(new Date());
                guessBetMapper.updateByPrimaryKeySelective(updateBet);
            }
            // 还原竞猜项状态
            GuessItem updateItem = new GuessItem();
            updateItem.setId(guessItem.getId());
            updateItem.setSettleStatus(0);
            updateItem.setSettlePattern(0);
            updateItem.setSettleCount(0);
            updateItem.setSettleBetAmount(BigDecimal.ZERO);
            updateItem.setSettleAmount(BigDecimal.ZERO);
            updateItem.setSettleProfit(BigDecimal.ZERO);
            updateItem.setSettleNote("结算回退成功");
            guessItemMapper.updateByPrimaryKeySelective(updateItem);
        } else {
            throw new BizException(CodeCons.ERROR, "投注项状态异常");
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void settle(GuessItemReq req) {
        GuessItem guessItem = this.getById(req.getId());
        Guess guess = guessService.getById(guessItem.getGuessId());
        String secondConfirm = req.getSecondConfirm();
        if (!MD5Util.MD5(secondConfirm).equals(guess.getSecondConfirm())) {
            throw new BizException(CodeCons.ERROR, "结算密码不正确");
        }
        // 结算状态, 0:待结算 1:结算中 2:结算完成 3:结算异常
        Integer status = guess.getStatus();
        if (NumberUtil.equals(status, 0)) {
            throw new BizException(CodeCons.ERROR, "竞猜已关闭");
        }
        if (NumberUtil.equals(status, 1)) {
            throw new BizException(CodeCons.ERROR, "竞猜未开始");
        }
        if (NumberUtil.equals(status, 2)) {
            throw new BizException(CodeCons.ERROR, "竞猜进行中");
        }
        // 竞猜结束, 计算投注项中奖金额
        if (NumberUtil.equals(status, 3)) {
            if (!NumberUtil.equals(guessItem.getGuessId(), guess.getId())) {
                throw new BizException(CodeCons.ERROR, "此投注项不属于当前竞猜");
            }
            if (NumberUtil.equals(guessItem.getItemStatus(), 0)) {
                throw new BizException(CodeCons.ERROR, "投注项已关闭");
            }
            if (!NumberUtil.equals(guessItem.getSettleStatus(), 1)) {
                throw new BizException(CodeCons.ERROR, "只有结算中状态支持结算操作");
            }

            // 查询当前投注项所有投注记录
            List<BetUserVo> betUserVos = guessBetService.allBetList(guess.getId(), guessItem.getId());
            for (BetUserVo betItem : betUserVos) {
                Long betItemId = betItem.getId();
                Integer betSettleStatus = betItem.getSettleStatus();
                if (!NumberUtil.equals(betSettleStatus, 1)) {
                    // 只处理投注记录结算状态为处理中的记录
                    continue;
                }
                // 开始结算,修改投注项状态,扣减钱包账户博币
                GuessBet updateBet = new GuessBet();
                updateBet.setId(betItemId);
                updateBet.setSettleStatus(2);
                updateBet.setUpdatedAt(new Date());
                guessBetMapper.updateByPrimaryKeySelective(updateBet);

                // 新增博币(结算金额大于0则新增)
                if (betItem.getSettleAmount().compareTo(BigDecimal.ZERO) > 0) {
                    // 小数点后的值截断处理
                    BigDecimal settleAmountDown = betItem.getSettleAmount().setScale(0, RoundingMode.DOWN);
                    if (settleAmountDown.compareTo(BigDecimal.ZERO) > 0) {
                        // 如果截断后的值还大于0
                        String settleNote = buildSettleNote(guess, betItem, settleAmountDown);
                        UsersReq usersReq = new UsersReq();
                        usersReq.setId(betItem.getUserId());
                        usersReq.setSettleAmount(settleAmountDown);
                        usersReq.setSettleNote(settleNote);
                        usersService.increaseBobiForBet(usersReq);
                    }
                }
            }
            // 更新竞猜项状态为结算成功
            Date now = new Date();
            GuessItem updateItem = new GuessItem();
            updateItem.setId(guessItem.getId());
            updateItem.setSettleStatus(2);
            updateItem.setSettleTime(now);
            updateItem.setUpdatedAt(now);
            updateItem.setSettleNote("结算成功! 结算操作员:" + req.getOptLoginName());
            guessItemMapper.updateByPrimaryKeySelective(updateItem);
        } else {
            throw new BizException(CodeCons.ERROR, "投注项状态异常");
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void settleFast(GuessItemReq req) {
        this.calcAward(req);
        this.settle(req);
    }

    @Override
    public List<GuessItem> selectByGuessIdList(List<Long> guessIdList) {
        GuessItemExample example = new GuessItemExample();
        GuessItemExample.Criteria criteria = example.createCriteria();
        criteria.andGuessIdIn(guessIdList);
        return guessItemMapper.selectByExample(example);
    }

    // 结算模式, 0:未设置 1:全赢 2:赢半 3:输半 4:全输 5:原路返还
    private String buildSettleNote(Guess guess, BetUserVo betItem, BigDecimal settleAmountDown) {
        LongTextTranslate longTextTranslateBean = I18nUtil.getLongTextTranslateBean();
        String note = longTextTranslateBean.buildSettleNote(guess, betItem, settleAmountDown);
        if (StrUtil.isBlank(note)) {
            throw new BizException(CodeCons.ERROR, "生成结算备注异常");
        }
        return note;
    }

    private BigDecimal calcSettleAmountWithPattern(Integer settlePattern, BigDecimal betOdds, BigDecimal betAmount) {
        // 结算模式, 0:未设置 1:全赢 2:赢半 3:输半 4:全输 5:原路返还
        // 全赢：本金*赔率
        //-赢半：本金*赔率/2
        //-输半：本金/2
        //-全输：0
        BigDecimal tow = new BigDecimal(2);
        BigDecimal result = null;
        switch (settlePattern) {
            case 1:
                result = betAmount.multiply(betOdds);
                break;
            case 2:
                result = calcWinHalf(betAmount, betOdds, tow);
                break;
            case 3:
                result = betAmount.divide(tow, 2, RoundingMode.DOWN);
                break;
            case 4:
                result = BigDecimal.ZERO;
                break;
            case 5:
                result = betAmount;
                break;
        }
        return result;
    }

    private BigDecimal calcWinHalf(BigDecimal betAmount, BigDecimal betOdds, BigDecimal tow) {
        // 赢半 = 本金 + 本金*((赔率-1)/2)
        BigDecimal subtract = betOdds.subtract(BigDecimal.ONE);
        BigDecimal divide = subtract.divide(tow, 4, RoundingMode.DOWN);
        BigDecimal multiply = betAmount.multiply(divide);
        BigDecimal result = betAmount.add(multiply);
        return result.setScale(2, RoundingMode.DOWN);
    }
}
