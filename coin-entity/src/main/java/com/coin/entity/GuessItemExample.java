package com.coin.entity;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
public class GuessItemExample {
    @Setter
    protected String orderByClause;

    @Setter
    protected boolean distinct;

    protected final List<Criteria> oredCriteria;

    public GuessItemExample() {
        oredCriteria = new ArrayList<>();
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.isEmpty()) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        return new Criteria();
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    @Getter
    protected abstract static class GeneratedCriteria {
        protected final List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<>();
        }

        public boolean isValid() {
            return !criteria.isEmpty();
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andGuessIdIsNull() {
            addCriterion("guess_id is null");
            return (Criteria) this;
        }

        public Criteria andGuessIdIsNotNull() {
            addCriterion("guess_id is not null");
            return (Criteria) this;
        }

        public Criteria andGuessIdEqualTo(Long value) {
            addCriterion("guess_id =", value, "guessId");
            return (Criteria) this;
        }

        public Criteria andGuessIdNotEqualTo(Long value) {
            addCriterion("guess_id <>", value, "guessId");
            return (Criteria) this;
        }

        public Criteria andGuessIdGreaterThan(Long value) {
            addCriterion("guess_id >", value, "guessId");
            return (Criteria) this;
        }

        public Criteria andGuessIdGreaterThanOrEqualTo(Long value) {
            addCriterion("guess_id >=", value, "guessId");
            return (Criteria) this;
        }

        public Criteria andGuessIdLessThan(Long value) {
            addCriterion("guess_id <", value, "guessId");
            return (Criteria) this;
        }

        public Criteria andGuessIdLessThanOrEqualTo(Long value) {
            addCriterion("guess_id <=", value, "guessId");
            return (Criteria) this;
        }

        public Criteria andGuessIdIn(List<Long> values) {
            addCriterion("guess_id in", values, "guessId");
            return (Criteria) this;
        }

        public Criteria andGuessIdNotIn(List<Long> values) {
            addCriterion("guess_id not in", values, "guessId");
            return (Criteria) this;
        }

        public Criteria andGuessIdBetween(Long value1, Long value2) {
            addCriterion("guess_id between", value1, value2, "guessId");
            return (Criteria) this;
        }

        public Criteria andGuessIdNotBetween(Long value1, Long value2) {
            addCriterion("guess_id not between", value1, value2, "guessId");
            return (Criteria) this;
        }

        public Criteria andItemNameIsNull() {
            addCriterion("item_name is null");
            return (Criteria) this;
        }

        public Criteria andItemNameIsNotNull() {
            addCriterion("item_name is not null");
            return (Criteria) this;
        }

        public Criteria andItemNameEqualTo(String value) {
            addCriterion("item_name =", value, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameNotEqualTo(String value) {
            addCriterion("item_name <>", value, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameGreaterThan(String value) {
            addCriterion("item_name >", value, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameGreaterThanOrEqualTo(String value) {
            addCriterion("item_name >=", value, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameLessThan(String value) {
            addCriterion("item_name <", value, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameLessThanOrEqualTo(String value) {
            addCriterion("item_name <=", value, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameLike(String value) {
            addCriterion("item_name like", value, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameNotLike(String value) {
            addCriterion("item_name not like", value, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameIn(List<String> values) {
            addCriterion("item_name in", values, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameNotIn(List<String> values) {
            addCriterion("item_name not in", values, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameBetween(String value1, String value2) {
            addCriterion("item_name between", value1, value2, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameNotBetween(String value1, String value2) {
            addCriterion("item_name not between", value1, value2, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemStatusIsNull() {
            addCriterion("item_status is null");
            return (Criteria) this;
        }

        public Criteria andItemStatusIsNotNull() {
            addCriterion("item_status is not null");
            return (Criteria) this;
        }

        public Criteria andItemStatusEqualTo(Integer value) {
            addCriterion("item_status =", value, "itemStatus");
            return (Criteria) this;
        }

        public Criteria andItemStatusNotEqualTo(Integer value) {
            addCriterion("item_status <>", value, "itemStatus");
            return (Criteria) this;
        }

        public Criteria andItemStatusGreaterThan(Integer value) {
            addCriterion("item_status >", value, "itemStatus");
            return (Criteria) this;
        }

        public Criteria andItemStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("item_status >=", value, "itemStatus");
            return (Criteria) this;
        }

        public Criteria andItemStatusLessThan(Integer value) {
            addCriterion("item_status <", value, "itemStatus");
            return (Criteria) this;
        }

        public Criteria andItemStatusLessThanOrEqualTo(Integer value) {
            addCriterion("item_status <=", value, "itemStatus");
            return (Criteria) this;
        }

        public Criteria andItemStatusIn(List<Integer> values) {
            addCriterion("item_status in", values, "itemStatus");
            return (Criteria) this;
        }

        public Criteria andItemStatusNotIn(List<Integer> values) {
            addCriterion("item_status not in", values, "itemStatus");
            return (Criteria) this;
        }

        public Criteria andItemStatusBetween(Integer value1, Integer value2) {
            addCriterion("item_status between", value1, value2, "itemStatus");
            return (Criteria) this;
        }

        public Criteria andItemStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("item_status not between", value1, value2, "itemStatus");
            return (Criteria) this;
        }

        public Criteria andItemOddsIsNull() {
            addCriterion("item_odds is null");
            return (Criteria) this;
        }

        public Criteria andItemOddsIsNotNull() {
            addCriterion("item_odds is not null");
            return (Criteria) this;
        }

        public Criteria andItemOddsEqualTo(BigDecimal value) {
            addCriterion("item_odds =", value, "itemOdds");
            return (Criteria) this;
        }

        public Criteria andItemOddsNotEqualTo(BigDecimal value) {
            addCriterion("item_odds <>", value, "itemOdds");
            return (Criteria) this;
        }

        public Criteria andItemOddsGreaterThan(BigDecimal value) {
            addCriterion("item_odds >", value, "itemOdds");
            return (Criteria) this;
        }

        public Criteria andItemOddsGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("item_odds >=", value, "itemOdds");
            return (Criteria) this;
        }

        public Criteria andItemOddsLessThan(BigDecimal value) {
            addCriterion("item_odds <", value, "itemOdds");
            return (Criteria) this;
        }

        public Criteria andItemOddsLessThanOrEqualTo(BigDecimal value) {
            addCriterion("item_odds <=", value, "itemOdds");
            return (Criteria) this;
        }

        public Criteria andItemOddsIn(List<BigDecimal> values) {
            addCriterion("item_odds in", values, "itemOdds");
            return (Criteria) this;
        }

        public Criteria andItemOddsNotIn(List<BigDecimal> values) {
            addCriterion("item_odds not in", values, "itemOdds");
            return (Criteria) this;
        }

        public Criteria andItemOddsBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("item_odds between", value1, value2, "itemOdds");
            return (Criteria) this;
        }

        public Criteria andItemOddsNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("item_odds not between", value1, value2, "itemOdds");
            return (Criteria) this;
        }

        public Criteria andBetUserCountIsNull() {
            addCriterion("bet_user_count is null");
            return (Criteria) this;
        }

        public Criteria andBetUserCountIsNotNull() {
            addCriterion("bet_user_count is not null");
            return (Criteria) this;
        }

        public Criteria andBetUserCountEqualTo(Integer value) {
            addCriterion("bet_user_count =", value, "betUserCount");
            return (Criteria) this;
        }

        public Criteria andBetUserCountNotEqualTo(Integer value) {
            addCriterion("bet_user_count <>", value, "betUserCount");
            return (Criteria) this;
        }

        public Criteria andBetUserCountGreaterThan(Integer value) {
            addCriterion("bet_user_count >", value, "betUserCount");
            return (Criteria) this;
        }

        public Criteria andBetUserCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("bet_user_count >=", value, "betUserCount");
            return (Criteria) this;
        }

        public Criteria andBetUserCountLessThan(Integer value) {
            addCriterion("bet_user_count <", value, "betUserCount");
            return (Criteria) this;
        }

        public Criteria andBetUserCountLessThanOrEqualTo(Integer value) {
            addCriterion("bet_user_count <=", value, "betUserCount");
            return (Criteria) this;
        }

        public Criteria andBetUserCountIn(List<Integer> values) {
            addCriterion("bet_user_count in", values, "betUserCount");
            return (Criteria) this;
        }

        public Criteria andBetUserCountNotIn(List<Integer> values) {
            addCriterion("bet_user_count not in", values, "betUserCount");
            return (Criteria) this;
        }

        public Criteria andBetUserCountBetween(Integer value1, Integer value2) {
            addCriterion("bet_user_count between", value1, value2, "betUserCount");
            return (Criteria) this;
        }

        public Criteria andBetUserCountNotBetween(Integer value1, Integer value2) {
            addCriterion("bet_user_count not between", value1, value2, "betUserCount");
            return (Criteria) this;
        }

        public Criteria andSettleStatusIsNull() {
            addCriterion("settle_status is null");
            return (Criteria) this;
        }

        public Criteria andSettleStatusIsNotNull() {
            addCriterion("settle_status is not null");
            return (Criteria) this;
        }

        public Criteria andSettleStatusEqualTo(Integer value) {
            addCriterion("settle_status =", value, "settleStatus");
            return (Criteria) this;
        }

        public Criteria andSettleStatusNotEqualTo(Integer value) {
            addCriterion("settle_status <>", value, "settleStatus");
            return (Criteria) this;
        }

        public Criteria andSettleStatusGreaterThan(Integer value) {
            addCriterion("settle_status >", value, "settleStatus");
            return (Criteria) this;
        }

        public Criteria andSettleStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("settle_status >=", value, "settleStatus");
            return (Criteria) this;
        }

        public Criteria andSettleStatusLessThan(Integer value) {
            addCriterion("settle_status <", value, "settleStatus");
            return (Criteria) this;
        }

        public Criteria andSettleStatusLessThanOrEqualTo(Integer value) {
            addCriterion("settle_status <=", value, "settleStatus");
            return (Criteria) this;
        }

        public Criteria andSettleStatusIn(List<Integer> values) {
            addCriterion("settle_status in", values, "settleStatus");
            return (Criteria) this;
        }

        public Criteria andSettleStatusNotIn(List<Integer> values) {
            addCriterion("settle_status not in", values, "settleStatus");
            return (Criteria) this;
        }

        public Criteria andSettleStatusBetween(Integer value1, Integer value2) {
            addCriterion("settle_status between", value1, value2, "settleStatus");
            return (Criteria) this;
        }

        public Criteria andSettleStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("settle_status not between", value1, value2, "settleStatus");
            return (Criteria) this;
        }

        public Criteria andSettlePatternIsNull() {
            addCriterion("settle_pattern is null");
            return (Criteria) this;
        }

        public Criteria andSettlePatternIsNotNull() {
            addCriterion("settle_pattern is not null");
            return (Criteria) this;
        }

        public Criteria andSettlePatternEqualTo(Integer value) {
            addCriterion("settle_pattern =", value, "settlePattern");
            return (Criteria) this;
        }

        public Criteria andSettlePatternNotEqualTo(Integer value) {
            addCriterion("settle_pattern <>", value, "settlePattern");
            return (Criteria) this;
        }

        public Criteria andSettlePatternGreaterThan(Integer value) {
            addCriterion("settle_pattern >", value, "settlePattern");
            return (Criteria) this;
        }

        public Criteria andSettlePatternGreaterThanOrEqualTo(Integer value) {
            addCriterion("settle_pattern >=", value, "settlePattern");
            return (Criteria) this;
        }

        public Criteria andSettlePatternLessThan(Integer value) {
            addCriterion("settle_pattern <", value, "settlePattern");
            return (Criteria) this;
        }

        public Criteria andSettlePatternLessThanOrEqualTo(Integer value) {
            addCriterion("settle_pattern <=", value, "settlePattern");
            return (Criteria) this;
        }

        public Criteria andSettlePatternIn(List<Integer> values) {
            addCriterion("settle_pattern in", values, "settlePattern");
            return (Criteria) this;
        }

        public Criteria andSettlePatternNotIn(List<Integer> values) {
            addCriterion("settle_pattern not in", values, "settlePattern");
            return (Criteria) this;
        }

        public Criteria andSettlePatternBetween(Integer value1, Integer value2) {
            addCriterion("settle_pattern between", value1, value2, "settlePattern");
            return (Criteria) this;
        }

        public Criteria andSettlePatternNotBetween(Integer value1, Integer value2) {
            addCriterion("settle_pattern not between", value1, value2, "settlePattern");
            return (Criteria) this;
        }

        public Criteria andSettleCountIsNull() {
            addCriterion("settle_count is null");
            return (Criteria) this;
        }

        public Criteria andSettleCountIsNotNull() {
            addCriterion("settle_count is not null");
            return (Criteria) this;
        }

        public Criteria andSettleCountEqualTo(Integer value) {
            addCriterion("settle_count =", value, "settleCount");
            return (Criteria) this;
        }

        public Criteria andSettleCountNotEqualTo(Integer value) {
            addCriterion("settle_count <>", value, "settleCount");
            return (Criteria) this;
        }

        public Criteria andSettleCountGreaterThan(Integer value) {
            addCriterion("settle_count >", value, "settleCount");
            return (Criteria) this;
        }

        public Criteria andSettleCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("settle_count >=", value, "settleCount");
            return (Criteria) this;
        }

        public Criteria andSettleCountLessThan(Integer value) {
            addCriterion("settle_count <", value, "settleCount");
            return (Criteria) this;
        }

        public Criteria andSettleCountLessThanOrEqualTo(Integer value) {
            addCriterion("settle_count <=", value, "settleCount");
            return (Criteria) this;
        }

        public Criteria andSettleCountIn(List<Integer> values) {
            addCriterion("settle_count in", values, "settleCount");
            return (Criteria) this;
        }

        public Criteria andSettleCountNotIn(List<Integer> values) {
            addCriterion("settle_count not in", values, "settleCount");
            return (Criteria) this;
        }

        public Criteria andSettleCountBetween(Integer value1, Integer value2) {
            addCriterion("settle_count between", value1, value2, "settleCount");
            return (Criteria) this;
        }

        public Criteria andSettleCountNotBetween(Integer value1, Integer value2) {
            addCriterion("settle_count not between", value1, value2, "settleCount");
            return (Criteria) this;
        }

        public Criteria andSettleBetAmountIsNull() {
            addCriterion("settle_bet_amount is null");
            return (Criteria) this;
        }

        public Criteria andSettleBetAmountIsNotNull() {
            addCriterion("settle_bet_amount is not null");
            return (Criteria) this;
        }

        public Criteria andSettleBetAmountEqualTo(BigDecimal value) {
            addCriterion("settle_bet_amount =", value, "settleBetAmount");
            return (Criteria) this;
        }

        public Criteria andSettleBetAmountNotEqualTo(BigDecimal value) {
            addCriterion("settle_bet_amount <>", value, "settleBetAmount");
            return (Criteria) this;
        }

        public Criteria andSettleBetAmountGreaterThan(BigDecimal value) {
            addCriterion("settle_bet_amount >", value, "settleBetAmount");
            return (Criteria) this;
        }

        public Criteria andSettleBetAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("settle_bet_amount >=", value, "settleBetAmount");
            return (Criteria) this;
        }

        public Criteria andSettleBetAmountLessThan(BigDecimal value) {
            addCriterion("settle_bet_amount <", value, "settleBetAmount");
            return (Criteria) this;
        }

        public Criteria andSettleBetAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("settle_bet_amount <=", value, "settleBetAmount");
            return (Criteria) this;
        }

        public Criteria andSettleBetAmountIn(List<BigDecimal> values) {
            addCriterion("settle_bet_amount in", values, "settleBetAmount");
            return (Criteria) this;
        }

        public Criteria andSettleBetAmountNotIn(List<BigDecimal> values) {
            addCriterion("settle_bet_amount not in", values, "settleBetAmount");
            return (Criteria) this;
        }

        public Criteria andSettleBetAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("settle_bet_amount between", value1, value2, "settleBetAmount");
            return (Criteria) this;
        }

        public Criteria andSettleBetAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("settle_bet_amount not between", value1, value2, "settleBetAmount");
            return (Criteria) this;
        }

        public Criteria andSettleAmountIsNull() {
            addCriterion("settle_amount is null");
            return (Criteria) this;
        }

        public Criteria andSettleAmountIsNotNull() {
            addCriterion("settle_amount is not null");
            return (Criteria) this;
        }

        public Criteria andSettleAmountEqualTo(BigDecimal value) {
            addCriterion("settle_amount =", value, "settleAmount");
            return (Criteria) this;
        }

        public Criteria andSettleAmountNotEqualTo(BigDecimal value) {
            addCriterion("settle_amount <>", value, "settleAmount");
            return (Criteria) this;
        }

        public Criteria andSettleAmountGreaterThan(BigDecimal value) {
            addCriterion("settle_amount >", value, "settleAmount");
            return (Criteria) this;
        }

        public Criteria andSettleAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("settle_amount >=", value, "settleAmount");
            return (Criteria) this;
        }

        public Criteria andSettleAmountLessThan(BigDecimal value) {
            addCriterion("settle_amount <", value, "settleAmount");
            return (Criteria) this;
        }

        public Criteria andSettleAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("settle_amount <=", value, "settleAmount");
            return (Criteria) this;
        }

        public Criteria andSettleAmountIn(List<BigDecimal> values) {
            addCriterion("settle_amount in", values, "settleAmount");
            return (Criteria) this;
        }

        public Criteria andSettleAmountNotIn(List<BigDecimal> values) {
            addCriterion("settle_amount not in", values, "settleAmount");
            return (Criteria) this;
        }

        public Criteria andSettleAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("settle_amount between", value1, value2, "settleAmount");
            return (Criteria) this;
        }

        public Criteria andSettleAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("settle_amount not between", value1, value2, "settleAmount");
            return (Criteria) this;
        }

        public Criteria andSettleProfitIsNull() {
            addCriterion("settle_profit is null");
            return (Criteria) this;
        }

        public Criteria andSettleProfitIsNotNull() {
            addCriterion("settle_profit is not null");
            return (Criteria) this;
        }

        public Criteria andSettleProfitEqualTo(BigDecimal value) {
            addCriterion("settle_profit =", value, "settleProfit");
            return (Criteria) this;
        }

        public Criteria andSettleProfitNotEqualTo(BigDecimal value) {
            addCriterion("settle_profit <>", value, "settleProfit");
            return (Criteria) this;
        }

        public Criteria andSettleProfitGreaterThan(BigDecimal value) {
            addCriterion("settle_profit >", value, "settleProfit");
            return (Criteria) this;
        }

        public Criteria andSettleProfitGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("settle_profit >=", value, "settleProfit");
            return (Criteria) this;
        }

        public Criteria andSettleProfitLessThan(BigDecimal value) {
            addCriterion("settle_profit <", value, "settleProfit");
            return (Criteria) this;
        }

        public Criteria andSettleProfitLessThanOrEqualTo(BigDecimal value) {
            addCriterion("settle_profit <=", value, "settleProfit");
            return (Criteria) this;
        }

        public Criteria andSettleProfitIn(List<BigDecimal> values) {
            addCriterion("settle_profit in", values, "settleProfit");
            return (Criteria) this;
        }

        public Criteria andSettleProfitNotIn(List<BigDecimal> values) {
            addCriterion("settle_profit not in", values, "settleProfit");
            return (Criteria) this;
        }

        public Criteria andSettleProfitBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("settle_profit between", value1, value2, "settleProfit");
            return (Criteria) this;
        }

        public Criteria andSettleProfitNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("settle_profit not between", value1, value2, "settleProfit");
            return (Criteria) this;
        }

        public Criteria andSettleTimeIsNull() {
            addCriterion("settle_time is null");
            return (Criteria) this;
        }

        public Criteria andSettleTimeIsNotNull() {
            addCriterion("settle_time is not null");
            return (Criteria) this;
        }

        public Criteria andSettleTimeEqualTo(Date value) {
            addCriterion("settle_time =", value, "settleTime");
            return (Criteria) this;
        }

        public Criteria andSettleTimeNotEqualTo(Date value) {
            addCriterion("settle_time <>", value, "settleTime");
            return (Criteria) this;
        }

        public Criteria andSettleTimeGreaterThan(Date value) {
            addCriterion("settle_time >", value, "settleTime");
            return (Criteria) this;
        }

        public Criteria andSettleTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("settle_time >=", value, "settleTime");
            return (Criteria) this;
        }

        public Criteria andSettleTimeLessThan(Date value) {
            addCriterion("settle_time <", value, "settleTime");
            return (Criteria) this;
        }

        public Criteria andSettleTimeLessThanOrEqualTo(Date value) {
            addCriterion("settle_time <=", value, "settleTime");
            return (Criteria) this;
        }

        public Criteria andSettleTimeIn(List<Date> values) {
            addCriterion("settle_time in", values, "settleTime");
            return (Criteria) this;
        }

        public Criteria andSettleTimeNotIn(List<Date> values) {
            addCriterion("settle_time not in", values, "settleTime");
            return (Criteria) this;
        }

        public Criteria andSettleTimeBetween(Date value1, Date value2) {
            addCriterion("settle_time between", value1, value2, "settleTime");
            return (Criteria) this;
        }

        public Criteria andSettleTimeNotBetween(Date value1, Date value2) {
            addCriterion("settle_time not between", value1, value2, "settleTime");
            return (Criteria) this;
        }

        public Criteria andSettleNoteIsNull() {
            addCriterion("settle_note is null");
            return (Criteria) this;
        }

        public Criteria andSettleNoteIsNotNull() {
            addCriterion("settle_note is not null");
            return (Criteria) this;
        }

        public Criteria andSettleNoteEqualTo(String value) {
            addCriterion("settle_note =", value, "settleNote");
            return (Criteria) this;
        }

        public Criteria andSettleNoteNotEqualTo(String value) {
            addCriterion("settle_note <>", value, "settleNote");
            return (Criteria) this;
        }

        public Criteria andSettleNoteGreaterThan(String value) {
            addCriterion("settle_note >", value, "settleNote");
            return (Criteria) this;
        }

        public Criteria andSettleNoteGreaterThanOrEqualTo(String value) {
            addCriterion("settle_note >=", value, "settleNote");
            return (Criteria) this;
        }

        public Criteria andSettleNoteLessThan(String value) {
            addCriterion("settle_note <", value, "settleNote");
            return (Criteria) this;
        }

        public Criteria andSettleNoteLessThanOrEqualTo(String value) {
            addCriterion("settle_note <=", value, "settleNote");
            return (Criteria) this;
        }

        public Criteria andSettleNoteLike(String value) {
            addCriterion("settle_note like", value, "settleNote");
            return (Criteria) this;
        }

        public Criteria andSettleNoteNotLike(String value) {
            addCriterion("settle_note not like", value, "settleNote");
            return (Criteria) this;
        }

        public Criteria andSettleNoteIn(List<String> values) {
            addCriterion("settle_note in", values, "settleNote");
            return (Criteria) this;
        }

        public Criteria andSettleNoteNotIn(List<String> values) {
            addCriterion("settle_note not in", values, "settleNote");
            return (Criteria) this;
        }

        public Criteria andSettleNoteBetween(String value1, String value2) {
            addCriterion("settle_note between", value1, value2, "settleNote");
            return (Criteria) this;
        }

        public Criteria andSettleNoteNotBetween(String value1, String value2) {
            addCriterion("settle_note not between", value1, value2, "settleNote");
            return (Criteria) this;
        }

        public Criteria andCreatedAtIsNull() {
            addCriterion("created_at is null");
            return (Criteria) this;
        }

        public Criteria andCreatedAtIsNotNull() {
            addCriterion("created_at is not null");
            return (Criteria) this;
        }

        public Criteria andCreatedAtEqualTo(Date value) {
            addCriterion("created_at =", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtNotEqualTo(Date value) {
            addCriterion("created_at <>", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtGreaterThan(Date value) {
            addCriterion("created_at >", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtGreaterThanOrEqualTo(Date value) {
            addCriterion("created_at >=", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtLessThan(Date value) {
            addCriterion("created_at <", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtLessThanOrEqualTo(Date value) {
            addCriterion("created_at <=", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtIn(List<Date> values) {
            addCriterion("created_at in", values, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtNotIn(List<Date> values) {
            addCriterion("created_at not in", values, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtBetween(Date value1, Date value2) {
            addCriterion("created_at between", value1, value2, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtNotBetween(Date value1, Date value2) {
            addCriterion("created_at not between", value1, value2, "createdAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtIsNull() {
            addCriterion("updated_at is null");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtIsNotNull() {
            addCriterion("updated_at is not null");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtEqualTo(Date value) {
            addCriterion("updated_at =", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtNotEqualTo(Date value) {
            addCriterion("updated_at <>", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtGreaterThan(Date value) {
            addCriterion("updated_at >", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtGreaterThanOrEqualTo(Date value) {
            addCriterion("updated_at >=", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtLessThan(Date value) {
            addCriterion("updated_at <", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtLessThanOrEqualTo(Date value) {
            addCriterion("updated_at <=", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtIn(List<Date> values) {
            addCriterion("updated_at in", values, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtNotIn(List<Date> values) {
            addCriterion("updated_at not in", values, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtBetween(Date value1, Date value2) {
            addCriterion("updated_at between", value1, value2, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtNotBetween(Date value1, Date value2) {
            addCriterion("updated_at not between", value1, value2, "updatedAt");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {
        protected Criteria() {
            super();
        }
    }

    @Getter
    public static class Criterion {
        private final String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private final String typeHandler;

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }

    }
}