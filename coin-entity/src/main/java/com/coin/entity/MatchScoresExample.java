package com.coin.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
public class MatchScoresExample {
    @Setter
    protected String orderByClause;

    @Setter
    protected boolean distinct;

    protected final List<Criteria> oredCriteria;

    public MatchScoresExample() {
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

        public Criteria andIdEqualTo(String value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("id like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("id not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("`name` is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("`name` is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("`name` =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("`name` <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("`name` >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("`name` >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("`name` <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("`name` <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("`name` like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("`name` not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("`name` in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("`name` not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("`name` between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("`name` not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andLogoIsNull() {
            addCriterion("logo is null");
            return (Criteria) this;
        }

        public Criteria andLogoIsNotNull() {
            addCriterion("logo is not null");
            return (Criteria) this;
        }

        public Criteria andLogoEqualTo(String value) {
            addCriterion("logo =", value, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoNotEqualTo(String value) {
            addCriterion("logo <>", value, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoGreaterThan(String value) {
            addCriterion("logo >", value, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoGreaterThanOrEqualTo(String value) {
            addCriterion("logo >=", value, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoLessThan(String value) {
            addCriterion("logo <", value, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoLessThanOrEqualTo(String value) {
            addCriterion("logo <=", value, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoLike(String value) {
            addCriterion("logo like", value, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoNotLike(String value) {
            addCriterion("logo not like", value, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoIn(List<String> values) {
            addCriterion("logo in", values, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoNotIn(List<String> values) {
            addCriterion("logo not in", values, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoBetween(String value1, String value2) {
            addCriterion("logo between", value1, value2, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoNotBetween(String value1, String value2) {
            addCriterion("logo not between", value1, value2, "logo");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("`type` is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("`type` is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(Integer value) {
            addCriterion("`type` =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(Integer value) {
            addCriterion("`type` <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(Integer value) {
            addCriterion("`type` >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("`type` >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(Integer value) {
            addCriterion("`type` <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(Integer value) {
            addCriterion("`type` <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<Integer> values) {
            addCriterion("`type` in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<Integer> values) {
            addCriterion("`type` not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(Integer value1, Integer value2) {
            addCriterion("`type` between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("`type` not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andScoreIsNull() {
            addCriterion("score is null");
            return (Criteria) this;
        }

        public Criteria andScoreIsNotNull() {
            addCriterion("score is not null");
            return (Criteria) this;
        }

        public Criteria andScoreEqualTo(Integer value) {
            addCriterion("score =", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreNotEqualTo(Integer value) {
            addCriterion("score <>", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreGreaterThan(Integer value) {
            addCriterion("score >", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreGreaterThanOrEqualTo(Integer value) {
            addCriterion("score >=", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreLessThan(Integer value) {
            addCriterion("score <", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreLessThanOrEqualTo(Integer value) {
            addCriterion("score <=", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreIn(List<Integer> values) {
            addCriterion("score in", values, "score");
            return (Criteria) this;
        }

        public Criteria andScoreNotIn(List<Integer> values) {
            addCriterion("score not in", values, "score");
            return (Criteria) this;
        }

        public Criteria andScoreBetween(Integer value1, Integer value2) {
            addCriterion("score between", value1, value2, "score");
            return (Criteria) this;
        }

        public Criteria andScoreNotBetween(Integer value1, Integer value2) {
            addCriterion("score not between", value1, value2, "score");
            return (Criteria) this;
        }

        public Criteria andOvertimescoreIsNull() {
            addCriterion("overTimeScore is null");
            return (Criteria) this;
        }

        public Criteria andOvertimescoreIsNotNull() {
            addCriterion("overTimeScore is not null");
            return (Criteria) this;
        }

        public Criteria andOvertimescoreEqualTo(Integer value) {
            addCriterion("overTimeScore =", value, "overtimescore");
            return (Criteria) this;
        }

        public Criteria andOvertimescoreNotEqualTo(Integer value) {
            addCriterion("overTimeScore <>", value, "overtimescore");
            return (Criteria) this;
        }

        public Criteria andOvertimescoreGreaterThan(Integer value) {
            addCriterion("overTimeScore >", value, "overtimescore");
            return (Criteria) this;
        }

        public Criteria andOvertimescoreGreaterThanOrEqualTo(Integer value) {
            addCriterion("overTimeScore >=", value, "overtimescore");
            return (Criteria) this;
        }

        public Criteria andOvertimescoreLessThan(Integer value) {
            addCriterion("overTimeScore <", value, "overtimescore");
            return (Criteria) this;
        }

        public Criteria andOvertimescoreLessThanOrEqualTo(Integer value) {
            addCriterion("overTimeScore <=", value, "overtimescore");
            return (Criteria) this;
        }

        public Criteria andOvertimescoreIn(List<Integer> values) {
            addCriterion("overTimeScore in", values, "overtimescore");
            return (Criteria) this;
        }

        public Criteria andOvertimescoreNotIn(List<Integer> values) {
            addCriterion("overTimeScore not in", values, "overtimescore");
            return (Criteria) this;
        }

        public Criteria andOvertimescoreBetween(Integer value1, Integer value2) {
            addCriterion("overTimeScore between", value1, value2, "overtimescore");
            return (Criteria) this;
        }

        public Criteria andOvertimescoreNotBetween(Integer value1, Integer value2) {
            addCriterion("overTimeScore not between", value1, value2, "overtimescore");
            return (Criteria) this;
        }

        public Criteria andHalfscoreIsNull() {
            addCriterion("halfScore is null");
            return (Criteria) this;
        }

        public Criteria andHalfscoreIsNotNull() {
            addCriterion("halfScore is not null");
            return (Criteria) this;
        }

        public Criteria andHalfscoreEqualTo(Integer value) {
            addCriterion("halfScore =", value, "halfscore");
            return (Criteria) this;
        }

        public Criteria andHalfscoreNotEqualTo(Integer value) {
            addCriterion("halfScore <>", value, "halfscore");
            return (Criteria) this;
        }

        public Criteria andHalfscoreGreaterThan(Integer value) {
            addCriterion("halfScore >", value, "halfscore");
            return (Criteria) this;
        }

        public Criteria andHalfscoreGreaterThanOrEqualTo(Integer value) {
            addCriterion("halfScore >=", value, "halfscore");
            return (Criteria) this;
        }

        public Criteria andHalfscoreLessThan(Integer value) {
            addCriterion("halfScore <", value, "halfscore");
            return (Criteria) this;
        }

        public Criteria andHalfscoreLessThanOrEqualTo(Integer value) {
            addCriterion("halfScore <=", value, "halfscore");
            return (Criteria) this;
        }

        public Criteria andHalfscoreIn(List<Integer> values) {
            addCriterion("halfScore in", values, "halfscore");
            return (Criteria) this;
        }

        public Criteria andHalfscoreNotIn(List<Integer> values) {
            addCriterion("halfScore not in", values, "halfscore");
            return (Criteria) this;
        }

        public Criteria andHalfscoreBetween(Integer value1, Integer value2) {
            addCriterion("halfScore between", value1, value2, "halfscore");
            return (Criteria) this;
        }

        public Criteria andHalfscoreNotBetween(Integer value1, Integer value2) {
            addCriterion("halfScore not between", value1, value2, "halfscore");
            return (Criteria) this;
        }

        public Criteria andPenaltyscoreIsNull() {
            addCriterion("penaltyScore is null");
            return (Criteria) this;
        }

        public Criteria andPenaltyscoreIsNotNull() {
            addCriterion("penaltyScore is not null");
            return (Criteria) this;
        }

        public Criteria andPenaltyscoreEqualTo(Integer value) {
            addCriterion("penaltyScore =", value, "penaltyscore");
            return (Criteria) this;
        }

        public Criteria andPenaltyscoreNotEqualTo(Integer value) {
            addCriterion("penaltyScore <>", value, "penaltyscore");
            return (Criteria) this;
        }

        public Criteria andPenaltyscoreGreaterThan(Integer value) {
            addCriterion("penaltyScore >", value, "penaltyscore");
            return (Criteria) this;
        }

        public Criteria andPenaltyscoreGreaterThanOrEqualTo(Integer value) {
            addCriterion("penaltyScore >=", value, "penaltyscore");
            return (Criteria) this;
        }

        public Criteria andPenaltyscoreLessThan(Integer value) {
            addCriterion("penaltyScore <", value, "penaltyscore");
            return (Criteria) this;
        }

        public Criteria andPenaltyscoreLessThanOrEqualTo(Integer value) {
            addCriterion("penaltyScore <=", value, "penaltyscore");
            return (Criteria) this;
        }

        public Criteria andPenaltyscoreIn(List<Integer> values) {
            addCriterion("penaltyScore in", values, "penaltyscore");
            return (Criteria) this;
        }

        public Criteria andPenaltyscoreNotIn(List<Integer> values) {
            addCriterion("penaltyScore not in", values, "penaltyscore");
            return (Criteria) this;
        }

        public Criteria andPenaltyscoreBetween(Integer value1, Integer value2) {
            addCriterion("penaltyScore between", value1, value2, "penaltyscore");
            return (Criteria) this;
        }

        public Criteria andPenaltyscoreNotBetween(Integer value1, Integer value2) {
            addCriterion("penaltyScore not between", value1, value2, "penaltyscore");
            return (Criteria) this;
        }

        public Criteria andFirstscoreIsNull() {
            addCriterion("firstScore is null");
            return (Criteria) this;
        }

        public Criteria andFirstscoreIsNotNull() {
            addCriterion("firstScore is not null");
            return (Criteria) this;
        }

        public Criteria andFirstscoreEqualTo(Integer value) {
            addCriterion("firstScore =", value, "firstscore");
            return (Criteria) this;
        }

        public Criteria andFirstscoreNotEqualTo(Integer value) {
            addCriterion("firstScore <>", value, "firstscore");
            return (Criteria) this;
        }

        public Criteria andFirstscoreGreaterThan(Integer value) {
            addCriterion("firstScore >", value, "firstscore");
            return (Criteria) this;
        }

        public Criteria andFirstscoreGreaterThanOrEqualTo(Integer value) {
            addCriterion("firstScore >=", value, "firstscore");
            return (Criteria) this;
        }

        public Criteria andFirstscoreLessThan(Integer value) {
            addCriterion("firstScore <", value, "firstscore");
            return (Criteria) this;
        }

        public Criteria andFirstscoreLessThanOrEqualTo(Integer value) {
            addCriterion("firstScore <=", value, "firstscore");
            return (Criteria) this;
        }

        public Criteria andFirstscoreIn(List<Integer> values) {
            addCriterion("firstScore in", values, "firstscore");
            return (Criteria) this;
        }

        public Criteria andFirstscoreNotIn(List<Integer> values) {
            addCriterion("firstScore not in", values, "firstscore");
            return (Criteria) this;
        }

        public Criteria andFirstscoreBetween(Integer value1, Integer value2) {
            addCriterion("firstScore between", value1, value2, "firstscore");
            return (Criteria) this;
        }

        public Criteria andFirstscoreNotBetween(Integer value1, Integer value2) {
            addCriterion("firstScore not between", value1, value2, "firstscore");
            return (Criteria) this;
        }

        public Criteria andSecondscoreIsNull() {
            addCriterion("secondScore is null");
            return (Criteria) this;
        }

        public Criteria andSecondscoreIsNotNull() {
            addCriterion("secondScore is not null");
            return (Criteria) this;
        }

        public Criteria andSecondscoreEqualTo(Integer value) {
            addCriterion("secondScore =", value, "secondscore");
            return (Criteria) this;
        }

        public Criteria andSecondscoreNotEqualTo(Integer value) {
            addCriterion("secondScore <>", value, "secondscore");
            return (Criteria) this;
        }

        public Criteria andSecondscoreGreaterThan(Integer value) {
            addCriterion("secondScore >", value, "secondscore");
            return (Criteria) this;
        }

        public Criteria andSecondscoreGreaterThanOrEqualTo(Integer value) {
            addCriterion("secondScore >=", value, "secondscore");
            return (Criteria) this;
        }

        public Criteria andSecondscoreLessThan(Integer value) {
            addCriterion("secondScore <", value, "secondscore");
            return (Criteria) this;
        }

        public Criteria andSecondscoreLessThanOrEqualTo(Integer value) {
            addCriterion("secondScore <=", value, "secondscore");
            return (Criteria) this;
        }

        public Criteria andSecondscoreIn(List<Integer> values) {
            addCriterion("secondScore in", values, "secondscore");
            return (Criteria) this;
        }

        public Criteria andSecondscoreNotIn(List<Integer> values) {
            addCriterion("secondScore not in", values, "secondscore");
            return (Criteria) this;
        }

        public Criteria andSecondscoreBetween(Integer value1, Integer value2) {
            addCriterion("secondScore between", value1, value2, "secondscore");
            return (Criteria) this;
        }

        public Criteria andSecondscoreNotBetween(Integer value1, Integer value2) {
            addCriterion("secondScore not between", value1, value2, "secondscore");
            return (Criteria) this;
        }

        public Criteria andThirdscoreIsNull() {
            addCriterion("thirdScore is null");
            return (Criteria) this;
        }

        public Criteria andThirdscoreIsNotNull() {
            addCriterion("thirdScore is not null");
            return (Criteria) this;
        }

        public Criteria andThirdscoreEqualTo(Integer value) {
            addCriterion("thirdScore =", value, "thirdscore");
            return (Criteria) this;
        }

        public Criteria andThirdscoreNotEqualTo(Integer value) {
            addCriterion("thirdScore <>", value, "thirdscore");
            return (Criteria) this;
        }

        public Criteria andThirdscoreGreaterThan(Integer value) {
            addCriterion("thirdScore >", value, "thirdscore");
            return (Criteria) this;
        }

        public Criteria andThirdscoreGreaterThanOrEqualTo(Integer value) {
            addCriterion("thirdScore >=", value, "thirdscore");
            return (Criteria) this;
        }

        public Criteria andThirdscoreLessThan(Integer value) {
            addCriterion("thirdScore <", value, "thirdscore");
            return (Criteria) this;
        }

        public Criteria andThirdscoreLessThanOrEqualTo(Integer value) {
            addCriterion("thirdScore <=", value, "thirdscore");
            return (Criteria) this;
        }

        public Criteria andThirdscoreIn(List<Integer> values) {
            addCriterion("thirdScore in", values, "thirdscore");
            return (Criteria) this;
        }

        public Criteria andThirdscoreNotIn(List<Integer> values) {
            addCriterion("thirdScore not in", values, "thirdscore");
            return (Criteria) this;
        }

        public Criteria andThirdscoreBetween(Integer value1, Integer value2) {
            addCriterion("thirdScore between", value1, value2, "thirdscore");
            return (Criteria) this;
        }

        public Criteria andThirdscoreNotBetween(Integer value1, Integer value2) {
            addCriterion("thirdScore not between", value1, value2, "thirdscore");
            return (Criteria) this;
        }

        public Criteria andFourthscoreIsNull() {
            addCriterion("fourthScore is null");
            return (Criteria) this;
        }

        public Criteria andFourthscoreIsNotNull() {
            addCriterion("fourthScore is not null");
            return (Criteria) this;
        }

        public Criteria andFourthscoreEqualTo(Integer value) {
            addCriterion("fourthScore =", value, "fourthscore");
            return (Criteria) this;
        }

        public Criteria andFourthscoreNotEqualTo(Integer value) {
            addCriterion("fourthScore <>", value, "fourthscore");
            return (Criteria) this;
        }

        public Criteria andFourthscoreGreaterThan(Integer value) {
            addCriterion("fourthScore >", value, "fourthscore");
            return (Criteria) this;
        }

        public Criteria andFourthscoreGreaterThanOrEqualTo(Integer value) {
            addCriterion("fourthScore >=", value, "fourthscore");
            return (Criteria) this;
        }

        public Criteria andFourthscoreLessThan(Integer value) {
            addCriterion("fourthScore <", value, "fourthscore");
            return (Criteria) this;
        }

        public Criteria andFourthscoreLessThanOrEqualTo(Integer value) {
            addCriterion("fourthScore <=", value, "fourthscore");
            return (Criteria) this;
        }

        public Criteria andFourthscoreIn(List<Integer> values) {
            addCriterion("fourthScore in", values, "fourthscore");
            return (Criteria) this;
        }

        public Criteria andFourthscoreNotIn(List<Integer> values) {
            addCriterion("fourthScore not in", values, "fourthscore");
            return (Criteria) this;
        }

        public Criteria andFourthscoreBetween(Integer value1, Integer value2) {
            addCriterion("fourthScore between", value1, value2, "fourthscore");
            return (Criteria) this;
        }

        public Criteria andFourthscoreNotBetween(Integer value1, Integer value2) {
            addCriterion("fourthScore not between", value1, value2, "fourthscore");
            return (Criteria) this;
        }

        public Criteria andMatchidIsNull() {
            addCriterion("matchId is null");
            return (Criteria) this;
        }

        public Criteria andMatchidIsNotNull() {
            addCriterion("matchId is not null");
            return (Criteria) this;
        }

        public Criteria andMatchidEqualTo(String value) {
            addCriterion("matchId =", value, "matchid");
            return (Criteria) this;
        }

        public Criteria andMatchidNotEqualTo(String value) {
            addCriterion("matchId <>", value, "matchid");
            return (Criteria) this;
        }

        public Criteria andMatchidGreaterThan(String value) {
            addCriterion("matchId >", value, "matchid");
            return (Criteria) this;
        }

        public Criteria andMatchidGreaterThanOrEqualTo(String value) {
            addCriterion("matchId >=", value, "matchid");
            return (Criteria) this;
        }

        public Criteria andMatchidLessThan(String value) {
            addCriterion("matchId <", value, "matchid");
            return (Criteria) this;
        }

        public Criteria andMatchidLessThanOrEqualTo(String value) {
            addCriterion("matchId <=", value, "matchid");
            return (Criteria) this;
        }

        public Criteria andMatchidLike(String value) {
            addCriterion("matchId like", value, "matchid");
            return (Criteria) this;
        }

        public Criteria andMatchidNotLike(String value) {
            addCriterion("matchId not like", value, "matchid");
            return (Criteria) this;
        }

        public Criteria andMatchidIn(List<String> values) {
            addCriterion("matchId in", values, "matchid");
            return (Criteria) this;
        }

        public Criteria andMatchidNotIn(List<String> values) {
            addCriterion("matchId not in", values, "matchid");
            return (Criteria) this;
        }

        public Criteria andMatchidBetween(String value1, String value2) {
            addCriterion("matchId between", value1, value2, "matchid");
            return (Criteria) this;
        }

        public Criteria andMatchidNotBetween(String value1, String value2) {
            addCriterion("matchId not between", value1, value2, "matchid");
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