package com.coin.entity;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
public class GuessBetExample {
    @Setter
    protected String orderByClause;

    @Setter
    protected boolean distinct;

    protected final List<Criteria> oredCriteria;

    public GuessBetExample() {
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

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(Long value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(Long value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(Long value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(Long value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(Long value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(Long value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<Long> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<Long> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(Long value1, Long value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(Long value1, Long value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserNameIsNull() {
            addCriterion("user_name is null");
            return (Criteria) this;
        }

        public Criteria andUserNameIsNotNull() {
            addCriterion("user_name is not null");
            return (Criteria) this;
        }

        public Criteria andUserNameEqualTo(String value) {
            addCriterion("user_name =", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotEqualTo(String value) {
            addCriterion("user_name <>", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameGreaterThan(String value) {
            addCriterion("user_name >", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("user_name >=", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLessThan(String value) {
            addCriterion("user_name <", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLessThanOrEqualTo(String value) {
            addCriterion("user_name <=", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLike(String value) {
            addCriterion("user_name like", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotLike(String value) {
            addCriterion("user_name not like", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameIn(List<String> values) {
            addCriterion("user_name in", values, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotIn(List<String> values) {
            addCriterion("user_name not in", values, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameBetween(String value1, String value2) {
            addCriterion("user_name between", value1, value2, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotBetween(String value1, String value2) {
            addCriterion("user_name not between", value1, value2, "userName");
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

        public Criteria andGuessItemIdIsNull() {
            addCriterion("guess_item_id is null");
            return (Criteria) this;
        }

        public Criteria andGuessItemIdIsNotNull() {
            addCriterion("guess_item_id is not null");
            return (Criteria) this;
        }

        public Criteria andGuessItemIdEqualTo(Long value) {
            addCriterion("guess_item_id =", value, "guessItemId");
            return (Criteria) this;
        }

        public Criteria andGuessItemIdNotEqualTo(Long value) {
            addCriterion("guess_item_id <>", value, "guessItemId");
            return (Criteria) this;
        }

        public Criteria andGuessItemIdGreaterThan(Long value) {
            addCriterion("guess_item_id >", value, "guessItemId");
            return (Criteria) this;
        }

        public Criteria andGuessItemIdGreaterThanOrEqualTo(Long value) {
            addCriterion("guess_item_id >=", value, "guessItemId");
            return (Criteria) this;
        }

        public Criteria andGuessItemIdLessThan(Long value) {
            addCriterion("guess_item_id <", value, "guessItemId");
            return (Criteria) this;
        }

        public Criteria andGuessItemIdLessThanOrEqualTo(Long value) {
            addCriterion("guess_item_id <=", value, "guessItemId");
            return (Criteria) this;
        }

        public Criteria andGuessItemIdIn(List<Long> values) {
            addCriterion("guess_item_id in", values, "guessItemId");
            return (Criteria) this;
        }

        public Criteria andGuessItemIdNotIn(List<Long> values) {
            addCriterion("guess_item_id not in", values, "guessItemId");
            return (Criteria) this;
        }

        public Criteria andGuessItemIdBetween(Long value1, Long value2) {
            addCriterion("guess_item_id between", value1, value2, "guessItemId");
            return (Criteria) this;
        }

        public Criteria andGuessItemIdNotBetween(Long value1, Long value2) {
            addCriterion("guess_item_id not between", value1, value2, "guessItemId");
            return (Criteria) this;
        }

        public Criteria andGuessItemNameIsNull() {
            addCriterion("guess_item_name is null");
            return (Criteria) this;
        }

        public Criteria andGuessItemNameIsNotNull() {
            addCriterion("guess_item_name is not null");
            return (Criteria) this;
        }

        public Criteria andGuessItemNameEqualTo(String value) {
            addCriterion("guess_item_name =", value, "guessItemName");
            return (Criteria) this;
        }

        public Criteria andGuessItemNameNotEqualTo(String value) {
            addCriterion("guess_item_name <>", value, "guessItemName");
            return (Criteria) this;
        }

        public Criteria andGuessItemNameGreaterThan(String value) {
            addCriterion("guess_item_name >", value, "guessItemName");
            return (Criteria) this;
        }

        public Criteria andGuessItemNameGreaterThanOrEqualTo(String value) {
            addCriterion("guess_item_name >=", value, "guessItemName");
            return (Criteria) this;
        }

        public Criteria andGuessItemNameLessThan(String value) {
            addCriterion("guess_item_name <", value, "guessItemName");
            return (Criteria) this;
        }

        public Criteria andGuessItemNameLessThanOrEqualTo(String value) {
            addCriterion("guess_item_name <=", value, "guessItemName");
            return (Criteria) this;
        }

        public Criteria andGuessItemNameLike(String value) {
            addCriterion("guess_item_name like", value, "guessItemName");
            return (Criteria) this;
        }

        public Criteria andGuessItemNameNotLike(String value) {
            addCriterion("guess_item_name not like", value, "guessItemName");
            return (Criteria) this;
        }

        public Criteria andGuessItemNameIn(List<String> values) {
            addCriterion("guess_item_name in", values, "guessItemName");
            return (Criteria) this;
        }

        public Criteria andGuessItemNameNotIn(List<String> values) {
            addCriterion("guess_item_name not in", values, "guessItemName");
            return (Criteria) this;
        }

        public Criteria andGuessItemNameBetween(String value1, String value2) {
            addCriterion("guess_item_name between", value1, value2, "guessItemName");
            return (Criteria) this;
        }

        public Criteria andGuessItemNameNotBetween(String value1, String value2) {
            addCriterion("guess_item_name not between", value1, value2, "guessItemName");
            return (Criteria) this;
        }

        public Criteria andBetAmountIsNull() {
            addCriterion("bet_amount is null");
            return (Criteria) this;
        }

        public Criteria andBetAmountIsNotNull() {
            addCriterion("bet_amount is not null");
            return (Criteria) this;
        }

        public Criteria andBetAmountEqualTo(BigDecimal value) {
            addCriterion("bet_amount =", value, "betAmount");
            return (Criteria) this;
        }

        public Criteria andBetAmountNotEqualTo(BigDecimal value) {
            addCriterion("bet_amount <>", value, "betAmount");
            return (Criteria) this;
        }

        public Criteria andBetAmountGreaterThan(BigDecimal value) {
            addCriterion("bet_amount >", value, "betAmount");
            return (Criteria) this;
        }

        public Criteria andBetAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("bet_amount >=", value, "betAmount");
            return (Criteria) this;
        }

        public Criteria andBetAmountLessThan(BigDecimal value) {
            addCriterion("bet_amount <", value, "betAmount");
            return (Criteria) this;
        }

        public Criteria andBetAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("bet_amount <=", value, "betAmount");
            return (Criteria) this;
        }

        public Criteria andBetAmountIn(List<BigDecimal> values) {
            addCriterion("bet_amount in", values, "betAmount");
            return (Criteria) this;
        }

        public Criteria andBetAmountNotIn(List<BigDecimal> values) {
            addCriterion("bet_amount not in", values, "betAmount");
            return (Criteria) this;
        }

        public Criteria andBetAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("bet_amount between", value1, value2, "betAmount");
            return (Criteria) this;
        }

        public Criteria andBetAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("bet_amount not between", value1, value2, "betAmount");
            return (Criteria) this;
        }

        public Criteria andBetOddsIsNull() {
            addCriterion("bet_odds is null");
            return (Criteria) this;
        }

        public Criteria andBetOddsIsNotNull() {
            addCriterion("bet_odds is not null");
            return (Criteria) this;
        }

        public Criteria andBetOddsEqualTo(BigDecimal value) {
            addCriterion("bet_odds =", value, "betOdds");
            return (Criteria) this;
        }

        public Criteria andBetOddsNotEqualTo(BigDecimal value) {
            addCriterion("bet_odds <>", value, "betOdds");
            return (Criteria) this;
        }

        public Criteria andBetOddsGreaterThan(BigDecimal value) {
            addCriterion("bet_odds >", value, "betOdds");
            return (Criteria) this;
        }

        public Criteria andBetOddsGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("bet_odds >=", value, "betOdds");
            return (Criteria) this;
        }

        public Criteria andBetOddsLessThan(BigDecimal value) {
            addCriterion("bet_odds <", value, "betOdds");
            return (Criteria) this;
        }

        public Criteria andBetOddsLessThanOrEqualTo(BigDecimal value) {
            addCriterion("bet_odds <=", value, "betOdds");
            return (Criteria) this;
        }

        public Criteria andBetOddsIn(List<BigDecimal> values) {
            addCriterion("bet_odds in", values, "betOdds");
            return (Criteria) this;
        }

        public Criteria andBetOddsNotIn(List<BigDecimal> values) {
            addCriterion("bet_odds not in", values, "betOdds");
            return (Criteria) this;
        }

        public Criteria andBetOddsBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("bet_odds between", value1, value2, "betOdds");
            return (Criteria) this;
        }

        public Criteria andBetOddsNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("bet_odds not between", value1, value2, "betOdds");
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

        public Criteria andAwardAmountIsNull() {
            addCriterion("award_amount is null");
            return (Criteria) this;
        }

        public Criteria andAwardAmountIsNotNull() {
            addCriterion("award_amount is not null");
            return (Criteria) this;
        }

        public Criteria andAwardAmountEqualTo(BigDecimal value) {
            addCriterion("award_amount =", value, "awardAmount");
            return (Criteria) this;
        }

        public Criteria andAwardAmountNotEqualTo(BigDecimal value) {
            addCriterion("award_amount <>", value, "awardAmount");
            return (Criteria) this;
        }

        public Criteria andAwardAmountGreaterThan(BigDecimal value) {
            addCriterion("award_amount >", value, "awardAmount");
            return (Criteria) this;
        }

        public Criteria andAwardAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("award_amount >=", value, "awardAmount");
            return (Criteria) this;
        }

        public Criteria andAwardAmountLessThan(BigDecimal value) {
            addCriterion("award_amount <", value, "awardAmount");
            return (Criteria) this;
        }

        public Criteria andAwardAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("award_amount <=", value, "awardAmount");
            return (Criteria) this;
        }

        public Criteria andAwardAmountIn(List<BigDecimal> values) {
            addCriterion("award_amount in", values, "awardAmount");
            return (Criteria) this;
        }

        public Criteria andAwardAmountNotIn(List<BigDecimal> values) {
            addCriterion("award_amount not in", values, "awardAmount");
            return (Criteria) this;
        }

        public Criteria andAwardAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("award_amount between", value1, value2, "awardAmount");
            return (Criteria) this;
        }

        public Criteria andAwardAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("award_amount not between", value1, value2, "awardAmount");
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