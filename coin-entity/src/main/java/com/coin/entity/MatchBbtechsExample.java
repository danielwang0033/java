package com.coin.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
public class MatchBbtechsExample {
    @Setter
    protected String orderByClause;

    @Setter
    protected boolean distinct;

    protected final List<Criteria> oredCriteria;

    public MatchBbtechsExample() {
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

        public Criteria andHomeidIsNull() {
            addCriterion("homeId is null");
            return (Criteria) this;
        }

        public Criteria andHomeidIsNotNull() {
            addCriterion("homeId is not null");
            return (Criteria) this;
        }

        public Criteria andHomeidEqualTo(Integer value) {
            addCriterion("homeId =", value, "homeid");
            return (Criteria) this;
        }

        public Criteria andHomeidNotEqualTo(Integer value) {
            addCriterion("homeId <>", value, "homeid");
            return (Criteria) this;
        }

        public Criteria andHomeidGreaterThan(Integer value) {
            addCriterion("homeId >", value, "homeid");
            return (Criteria) this;
        }

        public Criteria andHomeidGreaterThanOrEqualTo(Integer value) {
            addCriterion("homeId >=", value, "homeid");
            return (Criteria) this;
        }

        public Criteria andHomeidLessThan(Integer value) {
            addCriterion("homeId <", value, "homeid");
            return (Criteria) this;
        }

        public Criteria andHomeidLessThanOrEqualTo(Integer value) {
            addCriterion("homeId <=", value, "homeid");
            return (Criteria) this;
        }

        public Criteria andHomeidIn(List<Integer> values) {
            addCriterion("homeId in", values, "homeid");
            return (Criteria) this;
        }

        public Criteria andHomeidNotIn(List<Integer> values) {
            addCriterion("homeId not in", values, "homeid");
            return (Criteria) this;
        }

        public Criteria andHomeidBetween(Integer value1, Integer value2) {
            addCriterion("homeId between", value1, value2, "homeid");
            return (Criteria) this;
        }

        public Criteria andHomeidNotBetween(Integer value1, Integer value2) {
            addCriterion("homeId not between", value1, value2, "homeid");
            return (Criteria) this;
        }

        public Criteria andHomebackboardIsNull() {
            addCriterion("homeBackboard is null");
            return (Criteria) this;
        }

        public Criteria andHomebackboardIsNotNull() {
            addCriterion("homeBackboard is not null");
            return (Criteria) this;
        }

        public Criteria andHomebackboardEqualTo(Integer value) {
            addCriterion("homeBackboard =", value, "homebackboard");
            return (Criteria) this;
        }

        public Criteria andHomebackboardNotEqualTo(Integer value) {
            addCriterion("homeBackboard <>", value, "homebackboard");
            return (Criteria) this;
        }

        public Criteria andHomebackboardGreaterThan(Integer value) {
            addCriterion("homeBackboard >", value, "homebackboard");
            return (Criteria) this;
        }

        public Criteria andHomebackboardGreaterThanOrEqualTo(Integer value) {
            addCriterion("homeBackboard >=", value, "homebackboard");
            return (Criteria) this;
        }

        public Criteria andHomebackboardLessThan(Integer value) {
            addCriterion("homeBackboard <", value, "homebackboard");
            return (Criteria) this;
        }

        public Criteria andHomebackboardLessThanOrEqualTo(Integer value) {
            addCriterion("homeBackboard <=", value, "homebackboard");
            return (Criteria) this;
        }

        public Criteria andHomebackboardIn(List<Integer> values) {
            addCriterion("homeBackboard in", values, "homebackboard");
            return (Criteria) this;
        }

        public Criteria andHomebackboardNotIn(List<Integer> values) {
            addCriterion("homeBackboard not in", values, "homebackboard");
            return (Criteria) this;
        }

        public Criteria andHomebackboardBetween(Integer value1, Integer value2) {
            addCriterion("homeBackboard between", value1, value2, "homebackboard");
            return (Criteria) this;
        }

        public Criteria andHomebackboardNotBetween(Integer value1, Integer value2) {
            addCriterion("homeBackboard not between", value1, value2, "homebackboard");
            return (Criteria) this;
        }

        public Criteria andHomefoulIsNull() {
            addCriterion("homeFoul is null");
            return (Criteria) this;
        }

        public Criteria andHomefoulIsNotNull() {
            addCriterion("homeFoul is not null");
            return (Criteria) this;
        }

        public Criteria andHomefoulEqualTo(Integer value) {
            addCriterion("homeFoul =", value, "homefoul");
            return (Criteria) this;
        }

        public Criteria andHomefoulNotEqualTo(Integer value) {
            addCriterion("homeFoul <>", value, "homefoul");
            return (Criteria) this;
        }

        public Criteria andHomefoulGreaterThan(Integer value) {
            addCriterion("homeFoul >", value, "homefoul");
            return (Criteria) this;
        }

        public Criteria andHomefoulGreaterThanOrEqualTo(Integer value) {
            addCriterion("homeFoul >=", value, "homefoul");
            return (Criteria) this;
        }

        public Criteria andHomefoulLessThan(Integer value) {
            addCriterion("homeFoul <", value, "homefoul");
            return (Criteria) this;
        }

        public Criteria andHomefoulLessThanOrEqualTo(Integer value) {
            addCriterion("homeFoul <=", value, "homefoul");
            return (Criteria) this;
        }

        public Criteria andHomefoulIn(List<Integer> values) {
            addCriterion("homeFoul in", values, "homefoul");
            return (Criteria) this;
        }

        public Criteria andHomefoulNotIn(List<Integer> values) {
            addCriterion("homeFoul not in", values, "homefoul");
            return (Criteria) this;
        }

        public Criteria andHomefoulBetween(Integer value1, Integer value2) {
            addCriterion("homeFoul between", value1, value2, "homefoul");
            return (Criteria) this;
        }

        public Criteria andHomefoulNotBetween(Integer value1, Integer value2) {
            addCriterion("homeFoul not between", value1, value2, "homefoul");
            return (Criteria) this;
        }

        public Criteria andHomethreescoreIsNull() {
            addCriterion("homeThreeScore is null");
            return (Criteria) this;
        }

        public Criteria andHomethreescoreIsNotNull() {
            addCriterion("homeThreeScore is not null");
            return (Criteria) this;
        }

        public Criteria andHomethreescoreEqualTo(Integer value) {
            addCriterion("homeThreeScore =", value, "homethreescore");
            return (Criteria) this;
        }

        public Criteria andHomethreescoreNotEqualTo(Integer value) {
            addCriterion("homeThreeScore <>", value, "homethreescore");
            return (Criteria) this;
        }

        public Criteria andHomethreescoreGreaterThan(Integer value) {
            addCriterion("homeThreeScore >", value, "homethreescore");
            return (Criteria) this;
        }

        public Criteria andHomethreescoreGreaterThanOrEqualTo(Integer value) {
            addCriterion("homeThreeScore >=", value, "homethreescore");
            return (Criteria) this;
        }

        public Criteria andHomethreescoreLessThan(Integer value) {
            addCriterion("homeThreeScore <", value, "homethreescore");
            return (Criteria) this;
        }

        public Criteria andHomethreescoreLessThanOrEqualTo(Integer value) {
            addCriterion("homeThreeScore <=", value, "homethreescore");
            return (Criteria) this;
        }

        public Criteria andHomethreescoreIn(List<Integer> values) {
            addCriterion("homeThreeScore in", values, "homethreescore");
            return (Criteria) this;
        }

        public Criteria andHomethreescoreNotIn(List<Integer> values) {
            addCriterion("homeThreeScore not in", values, "homethreescore");
            return (Criteria) this;
        }

        public Criteria andHomethreescoreBetween(Integer value1, Integer value2) {
            addCriterion("homeThreeScore between", value1, value2, "homethreescore");
            return (Criteria) this;
        }

        public Criteria andHomethreescoreNotBetween(Integer value1, Integer value2) {
            addCriterion("homeThreeScore not between", value1, value2, "homethreescore");
            return (Criteria) this;
        }

        public Criteria andHometwoscoreIsNull() {
            addCriterion("homeTwoScore is null");
            return (Criteria) this;
        }

        public Criteria andHometwoscoreIsNotNull() {
            addCriterion("homeTwoScore is not null");
            return (Criteria) this;
        }

        public Criteria andHometwoscoreEqualTo(Integer value) {
            addCriterion("homeTwoScore =", value, "hometwoscore");
            return (Criteria) this;
        }

        public Criteria andHometwoscoreNotEqualTo(Integer value) {
            addCriterion("homeTwoScore <>", value, "hometwoscore");
            return (Criteria) this;
        }

        public Criteria andHometwoscoreGreaterThan(Integer value) {
            addCriterion("homeTwoScore >", value, "hometwoscore");
            return (Criteria) this;
        }

        public Criteria andHometwoscoreGreaterThanOrEqualTo(Integer value) {
            addCriterion("homeTwoScore >=", value, "hometwoscore");
            return (Criteria) this;
        }

        public Criteria andHometwoscoreLessThan(Integer value) {
            addCriterion("homeTwoScore <", value, "hometwoscore");
            return (Criteria) this;
        }

        public Criteria andHometwoscoreLessThanOrEqualTo(Integer value) {
            addCriterion("homeTwoScore <=", value, "hometwoscore");
            return (Criteria) this;
        }

        public Criteria andHometwoscoreIn(List<Integer> values) {
            addCriterion("homeTwoScore in", values, "hometwoscore");
            return (Criteria) this;
        }

        public Criteria andHometwoscoreNotIn(List<Integer> values) {
            addCriterion("homeTwoScore not in", values, "hometwoscore");
            return (Criteria) this;
        }

        public Criteria andHometwoscoreBetween(Integer value1, Integer value2) {
            addCriterion("homeTwoScore between", value1, value2, "hometwoscore");
            return (Criteria) this;
        }

        public Criteria andHometwoscoreNotBetween(Integer value1, Integer value2) {
            addCriterion("homeTwoScore not between", value1, value2, "hometwoscore");
            return (Criteria) this;
        }

        public Criteria andHomepenaltyIsNull() {
            addCriterion("homePenalty is null");
            return (Criteria) this;
        }

        public Criteria andHomepenaltyIsNotNull() {
            addCriterion("homePenalty is not null");
            return (Criteria) this;
        }

        public Criteria andHomepenaltyEqualTo(Integer value) {
            addCriterion("homePenalty =", value, "homepenalty");
            return (Criteria) this;
        }

        public Criteria andHomepenaltyNotEqualTo(Integer value) {
            addCriterion("homePenalty <>", value, "homepenalty");
            return (Criteria) this;
        }

        public Criteria andHomepenaltyGreaterThan(Integer value) {
            addCriterion("homePenalty >", value, "homepenalty");
            return (Criteria) this;
        }

        public Criteria andHomepenaltyGreaterThanOrEqualTo(Integer value) {
            addCriterion("homePenalty >=", value, "homepenalty");
            return (Criteria) this;
        }

        public Criteria andHomepenaltyLessThan(Integer value) {
            addCriterion("homePenalty <", value, "homepenalty");
            return (Criteria) this;
        }

        public Criteria andHomepenaltyLessThanOrEqualTo(Integer value) {
            addCriterion("homePenalty <=", value, "homepenalty");
            return (Criteria) this;
        }

        public Criteria andHomepenaltyIn(List<Integer> values) {
            addCriterion("homePenalty in", values, "homepenalty");
            return (Criteria) this;
        }

        public Criteria andHomepenaltyNotIn(List<Integer> values) {
            addCriterion("homePenalty not in", values, "homepenalty");
            return (Criteria) this;
        }

        public Criteria andHomepenaltyBetween(Integer value1, Integer value2) {
            addCriterion("homePenalty between", value1, value2, "homepenalty");
            return (Criteria) this;
        }

        public Criteria andHomepenaltyNotBetween(Integer value1, Integer value2) {
            addCriterion("homePenalty not between", value1, value2, "homepenalty");
            return (Criteria) this;
        }

        public Criteria andHomepenaltyrateIsNull() {
            addCriterion("homePenaltyRate is null");
            return (Criteria) this;
        }

        public Criteria andHomepenaltyrateIsNotNull() {
            addCriterion("homePenaltyRate is not null");
            return (Criteria) this;
        }

        public Criteria andHomepenaltyrateEqualTo(String value) {
            addCriterion("homePenaltyRate =", value, "homepenaltyrate");
            return (Criteria) this;
        }

        public Criteria andHomepenaltyrateNotEqualTo(String value) {
            addCriterion("homePenaltyRate <>", value, "homepenaltyrate");
            return (Criteria) this;
        }

        public Criteria andHomepenaltyrateGreaterThan(String value) {
            addCriterion("homePenaltyRate >", value, "homepenaltyrate");
            return (Criteria) this;
        }

        public Criteria andHomepenaltyrateGreaterThanOrEqualTo(String value) {
            addCriterion("homePenaltyRate >=", value, "homepenaltyrate");
            return (Criteria) this;
        }

        public Criteria andHomepenaltyrateLessThan(String value) {
            addCriterion("homePenaltyRate <", value, "homepenaltyrate");
            return (Criteria) this;
        }

        public Criteria andHomepenaltyrateLessThanOrEqualTo(String value) {
            addCriterion("homePenaltyRate <=", value, "homepenaltyrate");
            return (Criteria) this;
        }

        public Criteria andHomepenaltyrateLike(String value) {
            addCriterion("homePenaltyRate like", value, "homepenaltyrate");
            return (Criteria) this;
        }

        public Criteria andHomepenaltyrateNotLike(String value) {
            addCriterion("homePenaltyRate not like", value, "homepenaltyrate");
            return (Criteria) this;
        }

        public Criteria andHomepenaltyrateIn(List<String> values) {
            addCriterion("homePenaltyRate in", values, "homepenaltyrate");
            return (Criteria) this;
        }

        public Criteria andHomepenaltyrateNotIn(List<String> values) {
            addCriterion("homePenaltyRate not in", values, "homepenaltyrate");
            return (Criteria) this;
        }

        public Criteria andHomepenaltyrateBetween(String value1, String value2) {
            addCriterion("homePenaltyRate between", value1, value2, "homepenaltyrate");
            return (Criteria) this;
        }

        public Criteria andHomepenaltyrateNotBetween(String value1, String value2) {
            addCriterion("homePenaltyRate not between", value1, value2, "homepenaltyrate");
            return (Criteria) this;
        }

        public Criteria andHomebackboardfIsNull() {
            addCriterion("homeBackboardF is null");
            return (Criteria) this;
        }

        public Criteria andHomebackboardfIsNotNull() {
            addCriterion("homeBackboardF is not null");
            return (Criteria) this;
        }

        public Criteria andHomebackboardfEqualTo(Integer value) {
            addCriterion("homeBackboardF =", value, "homebackboardf");
            return (Criteria) this;
        }

        public Criteria andHomebackboardfNotEqualTo(Integer value) {
            addCriterion("homeBackboardF <>", value, "homebackboardf");
            return (Criteria) this;
        }

        public Criteria andHomebackboardfGreaterThan(Integer value) {
            addCriterion("homeBackboardF >", value, "homebackboardf");
            return (Criteria) this;
        }

        public Criteria andHomebackboardfGreaterThanOrEqualTo(Integer value) {
            addCriterion("homeBackboardF >=", value, "homebackboardf");
            return (Criteria) this;
        }

        public Criteria andHomebackboardfLessThan(Integer value) {
            addCriterion("homeBackboardF <", value, "homebackboardf");
            return (Criteria) this;
        }

        public Criteria andHomebackboardfLessThanOrEqualTo(Integer value) {
            addCriterion("homeBackboardF <=", value, "homebackboardf");
            return (Criteria) this;
        }

        public Criteria andHomebackboardfIn(List<Integer> values) {
            addCriterion("homeBackboardF in", values, "homebackboardf");
            return (Criteria) this;
        }

        public Criteria andHomebackboardfNotIn(List<Integer> values) {
            addCriterion("homeBackboardF not in", values, "homebackboardf");
            return (Criteria) this;
        }

        public Criteria andHomebackboardfBetween(Integer value1, Integer value2) {
            addCriterion("homeBackboardF between", value1, value2, "homebackboardf");
            return (Criteria) this;
        }

        public Criteria andHomebackboardfNotBetween(Integer value1, Integer value2) {
            addCriterion("homeBackboardF not between", value1, value2, "homebackboardf");
            return (Criteria) this;
        }

        public Criteria andHomebackboardbIsNull() {
            addCriterion("homeBackboardB is null");
            return (Criteria) this;
        }

        public Criteria andHomebackboardbIsNotNull() {
            addCriterion("homeBackboardB is not null");
            return (Criteria) this;
        }

        public Criteria andHomebackboardbEqualTo(Integer value) {
            addCriterion("homeBackboardB =", value, "homebackboardb");
            return (Criteria) this;
        }

        public Criteria andHomebackboardbNotEqualTo(Integer value) {
            addCriterion("homeBackboardB <>", value, "homebackboardb");
            return (Criteria) this;
        }

        public Criteria andHomebackboardbGreaterThan(Integer value) {
            addCriterion("homeBackboardB >", value, "homebackboardb");
            return (Criteria) this;
        }

        public Criteria andHomebackboardbGreaterThanOrEqualTo(Integer value) {
            addCriterion("homeBackboardB >=", value, "homebackboardb");
            return (Criteria) this;
        }

        public Criteria andHomebackboardbLessThan(Integer value) {
            addCriterion("homeBackboardB <", value, "homebackboardb");
            return (Criteria) this;
        }

        public Criteria andHomebackboardbLessThanOrEqualTo(Integer value) {
            addCriterion("homeBackboardB <=", value, "homebackboardb");
            return (Criteria) this;
        }

        public Criteria andHomebackboardbIn(List<Integer> values) {
            addCriterion("homeBackboardB in", values, "homebackboardb");
            return (Criteria) this;
        }

        public Criteria andHomebackboardbNotIn(List<Integer> values) {
            addCriterion("homeBackboardB not in", values, "homebackboardb");
            return (Criteria) this;
        }

        public Criteria andHomebackboardbBetween(Integer value1, Integer value2) {
            addCriterion("homeBackboardB between", value1, value2, "homebackboardb");
            return (Criteria) this;
        }

        public Criteria andHomebackboardbNotBetween(Integer value1, Integer value2) {
            addCriterion("homeBackboardB not between", value1, value2, "homebackboardb");
            return (Criteria) this;
        }

        public Criteria andHomeassistsIsNull() {
            addCriterion("homeAssists is null");
            return (Criteria) this;
        }

        public Criteria andHomeassistsIsNotNull() {
            addCriterion("homeAssists is not null");
            return (Criteria) this;
        }

        public Criteria andHomeassistsEqualTo(Integer value) {
            addCriterion("homeAssists =", value, "homeassists");
            return (Criteria) this;
        }

        public Criteria andHomeassistsNotEqualTo(Integer value) {
            addCriterion("homeAssists <>", value, "homeassists");
            return (Criteria) this;
        }

        public Criteria andHomeassistsGreaterThan(Integer value) {
            addCriterion("homeAssists >", value, "homeassists");
            return (Criteria) this;
        }

        public Criteria andHomeassistsGreaterThanOrEqualTo(Integer value) {
            addCriterion("homeAssists >=", value, "homeassists");
            return (Criteria) this;
        }

        public Criteria andHomeassistsLessThan(Integer value) {
            addCriterion("homeAssists <", value, "homeassists");
            return (Criteria) this;
        }

        public Criteria andHomeassistsLessThanOrEqualTo(Integer value) {
            addCriterion("homeAssists <=", value, "homeassists");
            return (Criteria) this;
        }

        public Criteria andHomeassistsIn(List<Integer> values) {
            addCriterion("homeAssists in", values, "homeassists");
            return (Criteria) this;
        }

        public Criteria andHomeassistsNotIn(List<Integer> values) {
            addCriterion("homeAssists not in", values, "homeassists");
            return (Criteria) this;
        }

        public Criteria andHomeassistsBetween(Integer value1, Integer value2) {
            addCriterion("homeAssists between", value1, value2, "homeassists");
            return (Criteria) this;
        }

        public Criteria andHomeassistsNotBetween(Integer value1, Integer value2) {
            addCriterion("homeAssists not between", value1, value2, "homeassists");
            return (Criteria) this;
        }

        public Criteria andHomesnatchIsNull() {
            addCriterion("homeSnatch is null");
            return (Criteria) this;
        }

        public Criteria andHomesnatchIsNotNull() {
            addCriterion("homeSnatch is not null");
            return (Criteria) this;
        }

        public Criteria andHomesnatchEqualTo(Integer value) {
            addCriterion("homeSnatch =", value, "homesnatch");
            return (Criteria) this;
        }

        public Criteria andHomesnatchNotEqualTo(Integer value) {
            addCriterion("homeSnatch <>", value, "homesnatch");
            return (Criteria) this;
        }

        public Criteria andHomesnatchGreaterThan(Integer value) {
            addCriterion("homeSnatch >", value, "homesnatch");
            return (Criteria) this;
        }

        public Criteria andHomesnatchGreaterThanOrEqualTo(Integer value) {
            addCriterion("homeSnatch >=", value, "homesnatch");
            return (Criteria) this;
        }

        public Criteria andHomesnatchLessThan(Integer value) {
            addCriterion("homeSnatch <", value, "homesnatch");
            return (Criteria) this;
        }

        public Criteria andHomesnatchLessThanOrEqualTo(Integer value) {
            addCriterion("homeSnatch <=", value, "homesnatch");
            return (Criteria) this;
        }

        public Criteria andHomesnatchIn(List<Integer> values) {
            addCriterion("homeSnatch in", values, "homesnatch");
            return (Criteria) this;
        }

        public Criteria andHomesnatchNotIn(List<Integer> values) {
            addCriterion("homeSnatch not in", values, "homesnatch");
            return (Criteria) this;
        }

        public Criteria andHomesnatchBetween(Integer value1, Integer value2) {
            addCriterion("homeSnatch between", value1, value2, "homesnatch");
            return (Criteria) this;
        }

        public Criteria andHomesnatchNotBetween(Integer value1, Integer value2) {
            addCriterion("homeSnatch not between", value1, value2, "homesnatch");
            return (Criteria) this;
        }

        public Criteria andHomeblockIsNull() {
            addCriterion("homeBlock is null");
            return (Criteria) this;
        }

        public Criteria andHomeblockIsNotNull() {
            addCriterion("homeBlock is not null");
            return (Criteria) this;
        }

        public Criteria andHomeblockEqualTo(Integer value) {
            addCriterion("homeBlock =", value, "homeblock");
            return (Criteria) this;
        }

        public Criteria andHomeblockNotEqualTo(Integer value) {
            addCriterion("homeBlock <>", value, "homeblock");
            return (Criteria) this;
        }

        public Criteria andHomeblockGreaterThan(Integer value) {
            addCriterion("homeBlock >", value, "homeblock");
            return (Criteria) this;
        }

        public Criteria andHomeblockGreaterThanOrEqualTo(Integer value) {
            addCriterion("homeBlock >=", value, "homeblock");
            return (Criteria) this;
        }

        public Criteria andHomeblockLessThan(Integer value) {
            addCriterion("homeBlock <", value, "homeblock");
            return (Criteria) this;
        }

        public Criteria andHomeblockLessThanOrEqualTo(Integer value) {
            addCriterion("homeBlock <=", value, "homeblock");
            return (Criteria) this;
        }

        public Criteria andHomeblockIn(List<Integer> values) {
            addCriterion("homeBlock in", values, "homeblock");
            return (Criteria) this;
        }

        public Criteria andHomeblockNotIn(List<Integer> values) {
            addCriterion("homeBlock not in", values, "homeblock");
            return (Criteria) this;
        }

        public Criteria andHomeblockBetween(Integer value1, Integer value2) {
            addCriterion("homeBlock between", value1, value2, "homeblock");
            return (Criteria) this;
        }

        public Criteria andHomeblockNotBetween(Integer value1, Integer value2) {
            addCriterion("homeBlock not between", value1, value2, "homeblock");
            return (Criteria) this;
        }

        public Criteria andHomefaultIsNull() {
            addCriterion("homeFault is null");
            return (Criteria) this;
        }

        public Criteria andHomefaultIsNotNull() {
            addCriterion("homeFault is not null");
            return (Criteria) this;
        }

        public Criteria andHomefaultEqualTo(Integer value) {
            addCriterion("homeFault =", value, "homefault");
            return (Criteria) this;
        }

        public Criteria andHomefaultNotEqualTo(Integer value) {
            addCriterion("homeFault <>", value, "homefault");
            return (Criteria) this;
        }

        public Criteria andHomefaultGreaterThan(Integer value) {
            addCriterion("homeFault >", value, "homefault");
            return (Criteria) this;
        }

        public Criteria andHomefaultGreaterThanOrEqualTo(Integer value) {
            addCriterion("homeFault >=", value, "homefault");
            return (Criteria) this;
        }

        public Criteria andHomefaultLessThan(Integer value) {
            addCriterion("homeFault <", value, "homefault");
            return (Criteria) this;
        }

        public Criteria andHomefaultLessThanOrEqualTo(Integer value) {
            addCriterion("homeFault <=", value, "homefault");
            return (Criteria) this;
        }

        public Criteria andHomefaultIn(List<Integer> values) {
            addCriterion("homeFault in", values, "homefault");
            return (Criteria) this;
        }

        public Criteria andHomefaultNotIn(List<Integer> values) {
            addCriterion("homeFault not in", values, "homefault");
            return (Criteria) this;
        }

        public Criteria andHomefaultBetween(Integer value1, Integer value2) {
            addCriterion("homeFault between", value1, value2, "homefault");
            return (Criteria) this;
        }

        public Criteria andHomefaultNotBetween(Integer value1, Integer value2) {
            addCriterion("homeFault not between", value1, value2, "homefault");
            return (Criteria) this;
        }

        public Criteria andAwayidIsNull() {
            addCriterion("awayId is null");
            return (Criteria) this;
        }

        public Criteria andAwayidIsNotNull() {
            addCriterion("awayId is not null");
            return (Criteria) this;
        }

        public Criteria andAwayidEqualTo(Integer value) {
            addCriterion("awayId =", value, "awayid");
            return (Criteria) this;
        }

        public Criteria andAwayidNotEqualTo(Integer value) {
            addCriterion("awayId <>", value, "awayid");
            return (Criteria) this;
        }

        public Criteria andAwayidGreaterThan(Integer value) {
            addCriterion("awayId >", value, "awayid");
            return (Criteria) this;
        }

        public Criteria andAwayidGreaterThanOrEqualTo(Integer value) {
            addCriterion("awayId >=", value, "awayid");
            return (Criteria) this;
        }

        public Criteria andAwayidLessThan(Integer value) {
            addCriterion("awayId <", value, "awayid");
            return (Criteria) this;
        }

        public Criteria andAwayidLessThanOrEqualTo(Integer value) {
            addCriterion("awayId <=", value, "awayid");
            return (Criteria) this;
        }

        public Criteria andAwayidIn(List<Integer> values) {
            addCriterion("awayId in", values, "awayid");
            return (Criteria) this;
        }

        public Criteria andAwayidNotIn(List<Integer> values) {
            addCriterion("awayId not in", values, "awayid");
            return (Criteria) this;
        }

        public Criteria andAwayidBetween(Integer value1, Integer value2) {
            addCriterion("awayId between", value1, value2, "awayid");
            return (Criteria) this;
        }

        public Criteria andAwayidNotBetween(Integer value1, Integer value2) {
            addCriterion("awayId not between", value1, value2, "awayid");
            return (Criteria) this;
        }

        public Criteria andAwaybackboardIsNull() {
            addCriterion("awayBackboard is null");
            return (Criteria) this;
        }

        public Criteria andAwaybackboardIsNotNull() {
            addCriterion("awayBackboard is not null");
            return (Criteria) this;
        }

        public Criteria andAwaybackboardEqualTo(Integer value) {
            addCriterion("awayBackboard =", value, "awaybackboard");
            return (Criteria) this;
        }

        public Criteria andAwaybackboardNotEqualTo(Integer value) {
            addCriterion("awayBackboard <>", value, "awaybackboard");
            return (Criteria) this;
        }

        public Criteria andAwaybackboardGreaterThan(Integer value) {
            addCriterion("awayBackboard >", value, "awaybackboard");
            return (Criteria) this;
        }

        public Criteria andAwaybackboardGreaterThanOrEqualTo(Integer value) {
            addCriterion("awayBackboard >=", value, "awaybackboard");
            return (Criteria) this;
        }

        public Criteria andAwaybackboardLessThan(Integer value) {
            addCriterion("awayBackboard <", value, "awaybackboard");
            return (Criteria) this;
        }

        public Criteria andAwaybackboardLessThanOrEqualTo(Integer value) {
            addCriterion("awayBackboard <=", value, "awaybackboard");
            return (Criteria) this;
        }

        public Criteria andAwaybackboardIn(List<Integer> values) {
            addCriterion("awayBackboard in", values, "awaybackboard");
            return (Criteria) this;
        }

        public Criteria andAwaybackboardNotIn(List<Integer> values) {
            addCriterion("awayBackboard not in", values, "awaybackboard");
            return (Criteria) this;
        }

        public Criteria andAwaybackboardBetween(Integer value1, Integer value2) {
            addCriterion("awayBackboard between", value1, value2, "awaybackboard");
            return (Criteria) this;
        }

        public Criteria andAwaybackboardNotBetween(Integer value1, Integer value2) {
            addCriterion("awayBackboard not between", value1, value2, "awaybackboard");
            return (Criteria) this;
        }

        public Criteria andAwayfoulIsNull() {
            addCriterion("awayFoul is null");
            return (Criteria) this;
        }

        public Criteria andAwayfoulIsNotNull() {
            addCriterion("awayFoul is not null");
            return (Criteria) this;
        }

        public Criteria andAwayfoulEqualTo(Integer value) {
            addCriterion("awayFoul =", value, "awayfoul");
            return (Criteria) this;
        }

        public Criteria andAwayfoulNotEqualTo(Integer value) {
            addCriterion("awayFoul <>", value, "awayfoul");
            return (Criteria) this;
        }

        public Criteria andAwayfoulGreaterThan(Integer value) {
            addCriterion("awayFoul >", value, "awayfoul");
            return (Criteria) this;
        }

        public Criteria andAwayfoulGreaterThanOrEqualTo(Integer value) {
            addCriterion("awayFoul >=", value, "awayfoul");
            return (Criteria) this;
        }

        public Criteria andAwayfoulLessThan(Integer value) {
            addCriterion("awayFoul <", value, "awayfoul");
            return (Criteria) this;
        }

        public Criteria andAwayfoulLessThanOrEqualTo(Integer value) {
            addCriterion("awayFoul <=", value, "awayfoul");
            return (Criteria) this;
        }

        public Criteria andAwayfoulIn(List<Integer> values) {
            addCriterion("awayFoul in", values, "awayfoul");
            return (Criteria) this;
        }

        public Criteria andAwayfoulNotIn(List<Integer> values) {
            addCriterion("awayFoul not in", values, "awayfoul");
            return (Criteria) this;
        }

        public Criteria andAwayfoulBetween(Integer value1, Integer value2) {
            addCriterion("awayFoul between", value1, value2, "awayfoul");
            return (Criteria) this;
        }

        public Criteria andAwayfoulNotBetween(Integer value1, Integer value2) {
            addCriterion("awayFoul not between", value1, value2, "awayfoul");
            return (Criteria) this;
        }

        public Criteria andAwaythreescoreIsNull() {
            addCriterion("awayThreeScore is null");
            return (Criteria) this;
        }

        public Criteria andAwaythreescoreIsNotNull() {
            addCriterion("awayThreeScore is not null");
            return (Criteria) this;
        }

        public Criteria andAwaythreescoreEqualTo(Integer value) {
            addCriterion("awayThreeScore =", value, "awaythreescore");
            return (Criteria) this;
        }

        public Criteria andAwaythreescoreNotEqualTo(Integer value) {
            addCriterion("awayThreeScore <>", value, "awaythreescore");
            return (Criteria) this;
        }

        public Criteria andAwaythreescoreGreaterThan(Integer value) {
            addCriterion("awayThreeScore >", value, "awaythreescore");
            return (Criteria) this;
        }

        public Criteria andAwaythreescoreGreaterThanOrEqualTo(Integer value) {
            addCriterion("awayThreeScore >=", value, "awaythreescore");
            return (Criteria) this;
        }

        public Criteria andAwaythreescoreLessThan(Integer value) {
            addCriterion("awayThreeScore <", value, "awaythreescore");
            return (Criteria) this;
        }

        public Criteria andAwaythreescoreLessThanOrEqualTo(Integer value) {
            addCriterion("awayThreeScore <=", value, "awaythreescore");
            return (Criteria) this;
        }

        public Criteria andAwaythreescoreIn(List<Integer> values) {
            addCriterion("awayThreeScore in", values, "awaythreescore");
            return (Criteria) this;
        }

        public Criteria andAwaythreescoreNotIn(List<Integer> values) {
            addCriterion("awayThreeScore not in", values, "awaythreescore");
            return (Criteria) this;
        }

        public Criteria andAwaythreescoreBetween(Integer value1, Integer value2) {
            addCriterion("awayThreeScore between", value1, value2, "awaythreescore");
            return (Criteria) this;
        }

        public Criteria andAwaythreescoreNotBetween(Integer value1, Integer value2) {
            addCriterion("awayThreeScore not between", value1, value2, "awaythreescore");
            return (Criteria) this;
        }

        public Criteria andAwaytwoscoreIsNull() {
            addCriterion("awayTwoScore is null");
            return (Criteria) this;
        }

        public Criteria andAwaytwoscoreIsNotNull() {
            addCriterion("awayTwoScore is not null");
            return (Criteria) this;
        }

        public Criteria andAwaytwoscoreEqualTo(Integer value) {
            addCriterion("awayTwoScore =", value, "awaytwoscore");
            return (Criteria) this;
        }

        public Criteria andAwaytwoscoreNotEqualTo(Integer value) {
            addCriterion("awayTwoScore <>", value, "awaytwoscore");
            return (Criteria) this;
        }

        public Criteria andAwaytwoscoreGreaterThan(Integer value) {
            addCriterion("awayTwoScore >", value, "awaytwoscore");
            return (Criteria) this;
        }

        public Criteria andAwaytwoscoreGreaterThanOrEqualTo(Integer value) {
            addCriterion("awayTwoScore >=", value, "awaytwoscore");
            return (Criteria) this;
        }

        public Criteria andAwaytwoscoreLessThan(Integer value) {
            addCriterion("awayTwoScore <", value, "awaytwoscore");
            return (Criteria) this;
        }

        public Criteria andAwaytwoscoreLessThanOrEqualTo(Integer value) {
            addCriterion("awayTwoScore <=", value, "awaytwoscore");
            return (Criteria) this;
        }

        public Criteria andAwaytwoscoreIn(List<Integer> values) {
            addCriterion("awayTwoScore in", values, "awaytwoscore");
            return (Criteria) this;
        }

        public Criteria andAwaytwoscoreNotIn(List<Integer> values) {
            addCriterion("awayTwoScore not in", values, "awaytwoscore");
            return (Criteria) this;
        }

        public Criteria andAwaytwoscoreBetween(Integer value1, Integer value2) {
            addCriterion("awayTwoScore between", value1, value2, "awaytwoscore");
            return (Criteria) this;
        }

        public Criteria andAwaytwoscoreNotBetween(Integer value1, Integer value2) {
            addCriterion("awayTwoScore not between", value1, value2, "awaytwoscore");
            return (Criteria) this;
        }

        public Criteria andAwaypenaltyIsNull() {
            addCriterion("awayPenalty is null");
            return (Criteria) this;
        }

        public Criteria andAwaypenaltyIsNotNull() {
            addCriterion("awayPenalty is not null");
            return (Criteria) this;
        }

        public Criteria andAwaypenaltyEqualTo(Integer value) {
            addCriterion("awayPenalty =", value, "awaypenalty");
            return (Criteria) this;
        }

        public Criteria andAwaypenaltyNotEqualTo(Integer value) {
            addCriterion("awayPenalty <>", value, "awaypenalty");
            return (Criteria) this;
        }

        public Criteria andAwaypenaltyGreaterThan(Integer value) {
            addCriterion("awayPenalty >", value, "awaypenalty");
            return (Criteria) this;
        }

        public Criteria andAwaypenaltyGreaterThanOrEqualTo(Integer value) {
            addCriterion("awayPenalty >=", value, "awaypenalty");
            return (Criteria) this;
        }

        public Criteria andAwaypenaltyLessThan(Integer value) {
            addCriterion("awayPenalty <", value, "awaypenalty");
            return (Criteria) this;
        }

        public Criteria andAwaypenaltyLessThanOrEqualTo(Integer value) {
            addCriterion("awayPenalty <=", value, "awaypenalty");
            return (Criteria) this;
        }

        public Criteria andAwaypenaltyIn(List<Integer> values) {
            addCriterion("awayPenalty in", values, "awaypenalty");
            return (Criteria) this;
        }

        public Criteria andAwaypenaltyNotIn(List<Integer> values) {
            addCriterion("awayPenalty not in", values, "awaypenalty");
            return (Criteria) this;
        }

        public Criteria andAwaypenaltyBetween(Integer value1, Integer value2) {
            addCriterion("awayPenalty between", value1, value2, "awaypenalty");
            return (Criteria) this;
        }

        public Criteria andAwaypenaltyNotBetween(Integer value1, Integer value2) {
            addCriterion("awayPenalty not between", value1, value2, "awaypenalty");
            return (Criteria) this;
        }

        public Criteria andAwaypenaltyrateIsNull() {
            addCriterion("awayPenaltyRate is null");
            return (Criteria) this;
        }

        public Criteria andAwaypenaltyrateIsNotNull() {
            addCriterion("awayPenaltyRate is not null");
            return (Criteria) this;
        }

        public Criteria andAwaypenaltyrateEqualTo(String value) {
            addCriterion("awayPenaltyRate =", value, "awaypenaltyrate");
            return (Criteria) this;
        }

        public Criteria andAwaypenaltyrateNotEqualTo(String value) {
            addCriterion("awayPenaltyRate <>", value, "awaypenaltyrate");
            return (Criteria) this;
        }

        public Criteria andAwaypenaltyrateGreaterThan(String value) {
            addCriterion("awayPenaltyRate >", value, "awaypenaltyrate");
            return (Criteria) this;
        }

        public Criteria andAwaypenaltyrateGreaterThanOrEqualTo(String value) {
            addCriterion("awayPenaltyRate >=", value, "awaypenaltyrate");
            return (Criteria) this;
        }

        public Criteria andAwaypenaltyrateLessThan(String value) {
            addCriterion("awayPenaltyRate <", value, "awaypenaltyrate");
            return (Criteria) this;
        }

        public Criteria andAwaypenaltyrateLessThanOrEqualTo(String value) {
            addCriterion("awayPenaltyRate <=", value, "awaypenaltyrate");
            return (Criteria) this;
        }

        public Criteria andAwaypenaltyrateLike(String value) {
            addCriterion("awayPenaltyRate like", value, "awaypenaltyrate");
            return (Criteria) this;
        }

        public Criteria andAwaypenaltyrateNotLike(String value) {
            addCriterion("awayPenaltyRate not like", value, "awaypenaltyrate");
            return (Criteria) this;
        }

        public Criteria andAwaypenaltyrateIn(List<String> values) {
            addCriterion("awayPenaltyRate in", values, "awaypenaltyrate");
            return (Criteria) this;
        }

        public Criteria andAwaypenaltyrateNotIn(List<String> values) {
            addCriterion("awayPenaltyRate not in", values, "awaypenaltyrate");
            return (Criteria) this;
        }

        public Criteria andAwaypenaltyrateBetween(String value1, String value2) {
            addCriterion("awayPenaltyRate between", value1, value2, "awaypenaltyrate");
            return (Criteria) this;
        }

        public Criteria andAwaypenaltyrateNotBetween(String value1, String value2) {
            addCriterion("awayPenaltyRate not between", value1, value2, "awaypenaltyrate");
            return (Criteria) this;
        }

        public Criteria andAwaybackboardfIsNull() {
            addCriterion("awayBackboardF is null");
            return (Criteria) this;
        }

        public Criteria andAwaybackboardfIsNotNull() {
            addCriterion("awayBackboardF is not null");
            return (Criteria) this;
        }

        public Criteria andAwaybackboardfEqualTo(Integer value) {
            addCriterion("awayBackboardF =", value, "awaybackboardf");
            return (Criteria) this;
        }

        public Criteria andAwaybackboardfNotEqualTo(Integer value) {
            addCriterion("awayBackboardF <>", value, "awaybackboardf");
            return (Criteria) this;
        }

        public Criteria andAwaybackboardfGreaterThan(Integer value) {
            addCriterion("awayBackboardF >", value, "awaybackboardf");
            return (Criteria) this;
        }

        public Criteria andAwaybackboardfGreaterThanOrEqualTo(Integer value) {
            addCriterion("awayBackboardF >=", value, "awaybackboardf");
            return (Criteria) this;
        }

        public Criteria andAwaybackboardfLessThan(Integer value) {
            addCriterion("awayBackboardF <", value, "awaybackboardf");
            return (Criteria) this;
        }

        public Criteria andAwaybackboardfLessThanOrEqualTo(Integer value) {
            addCriterion("awayBackboardF <=", value, "awaybackboardf");
            return (Criteria) this;
        }

        public Criteria andAwaybackboardfIn(List<Integer> values) {
            addCriterion("awayBackboardF in", values, "awaybackboardf");
            return (Criteria) this;
        }

        public Criteria andAwaybackboardfNotIn(List<Integer> values) {
            addCriterion("awayBackboardF not in", values, "awaybackboardf");
            return (Criteria) this;
        }

        public Criteria andAwaybackboardfBetween(Integer value1, Integer value2) {
            addCriterion("awayBackboardF between", value1, value2, "awaybackboardf");
            return (Criteria) this;
        }

        public Criteria andAwaybackboardfNotBetween(Integer value1, Integer value2) {
            addCriterion("awayBackboardF not between", value1, value2, "awaybackboardf");
            return (Criteria) this;
        }

        public Criteria andAwaybackboardbIsNull() {
            addCriterion("awayBackboardB is null");
            return (Criteria) this;
        }

        public Criteria andAwaybackboardbIsNotNull() {
            addCriterion("awayBackboardB is not null");
            return (Criteria) this;
        }

        public Criteria andAwaybackboardbEqualTo(Integer value) {
            addCriterion("awayBackboardB =", value, "awaybackboardb");
            return (Criteria) this;
        }

        public Criteria andAwaybackboardbNotEqualTo(Integer value) {
            addCriterion("awayBackboardB <>", value, "awaybackboardb");
            return (Criteria) this;
        }

        public Criteria andAwaybackboardbGreaterThan(Integer value) {
            addCriterion("awayBackboardB >", value, "awaybackboardb");
            return (Criteria) this;
        }

        public Criteria andAwaybackboardbGreaterThanOrEqualTo(Integer value) {
            addCriterion("awayBackboardB >=", value, "awaybackboardb");
            return (Criteria) this;
        }

        public Criteria andAwaybackboardbLessThan(Integer value) {
            addCriterion("awayBackboardB <", value, "awaybackboardb");
            return (Criteria) this;
        }

        public Criteria andAwaybackboardbLessThanOrEqualTo(Integer value) {
            addCriterion("awayBackboardB <=", value, "awaybackboardb");
            return (Criteria) this;
        }

        public Criteria andAwaybackboardbIn(List<Integer> values) {
            addCriterion("awayBackboardB in", values, "awaybackboardb");
            return (Criteria) this;
        }

        public Criteria andAwaybackboardbNotIn(List<Integer> values) {
            addCriterion("awayBackboardB not in", values, "awaybackboardb");
            return (Criteria) this;
        }

        public Criteria andAwaybackboardbBetween(Integer value1, Integer value2) {
            addCriterion("awayBackboardB between", value1, value2, "awaybackboardb");
            return (Criteria) this;
        }

        public Criteria andAwaybackboardbNotBetween(Integer value1, Integer value2) {
            addCriterion("awayBackboardB not between", value1, value2, "awaybackboardb");
            return (Criteria) this;
        }

        public Criteria andAwayassistsIsNull() {
            addCriterion("awayAssists is null");
            return (Criteria) this;
        }

        public Criteria andAwayassistsIsNotNull() {
            addCriterion("awayAssists is not null");
            return (Criteria) this;
        }

        public Criteria andAwayassistsEqualTo(Integer value) {
            addCriterion("awayAssists =", value, "awayassists");
            return (Criteria) this;
        }

        public Criteria andAwayassistsNotEqualTo(Integer value) {
            addCriterion("awayAssists <>", value, "awayassists");
            return (Criteria) this;
        }

        public Criteria andAwayassistsGreaterThan(Integer value) {
            addCriterion("awayAssists >", value, "awayassists");
            return (Criteria) this;
        }

        public Criteria andAwayassistsGreaterThanOrEqualTo(Integer value) {
            addCriterion("awayAssists >=", value, "awayassists");
            return (Criteria) this;
        }

        public Criteria andAwayassistsLessThan(Integer value) {
            addCriterion("awayAssists <", value, "awayassists");
            return (Criteria) this;
        }

        public Criteria andAwayassistsLessThanOrEqualTo(Integer value) {
            addCriterion("awayAssists <=", value, "awayassists");
            return (Criteria) this;
        }

        public Criteria andAwayassistsIn(List<Integer> values) {
            addCriterion("awayAssists in", values, "awayassists");
            return (Criteria) this;
        }

        public Criteria andAwayassistsNotIn(List<Integer> values) {
            addCriterion("awayAssists not in", values, "awayassists");
            return (Criteria) this;
        }

        public Criteria andAwayassistsBetween(Integer value1, Integer value2) {
            addCriterion("awayAssists between", value1, value2, "awayassists");
            return (Criteria) this;
        }

        public Criteria andAwayassistsNotBetween(Integer value1, Integer value2) {
            addCriterion("awayAssists not between", value1, value2, "awayassists");
            return (Criteria) this;
        }

        public Criteria andAwaysnatchIsNull() {
            addCriterion("awaySnatch is null");
            return (Criteria) this;
        }

        public Criteria andAwaysnatchIsNotNull() {
            addCriterion("awaySnatch is not null");
            return (Criteria) this;
        }

        public Criteria andAwaysnatchEqualTo(Integer value) {
            addCriterion("awaySnatch =", value, "awaysnatch");
            return (Criteria) this;
        }

        public Criteria andAwaysnatchNotEqualTo(Integer value) {
            addCriterion("awaySnatch <>", value, "awaysnatch");
            return (Criteria) this;
        }

        public Criteria andAwaysnatchGreaterThan(Integer value) {
            addCriterion("awaySnatch >", value, "awaysnatch");
            return (Criteria) this;
        }

        public Criteria andAwaysnatchGreaterThanOrEqualTo(Integer value) {
            addCriterion("awaySnatch >=", value, "awaysnatch");
            return (Criteria) this;
        }

        public Criteria andAwaysnatchLessThan(Integer value) {
            addCriterion("awaySnatch <", value, "awaysnatch");
            return (Criteria) this;
        }

        public Criteria andAwaysnatchLessThanOrEqualTo(Integer value) {
            addCriterion("awaySnatch <=", value, "awaysnatch");
            return (Criteria) this;
        }

        public Criteria andAwaysnatchIn(List<Integer> values) {
            addCriterion("awaySnatch in", values, "awaysnatch");
            return (Criteria) this;
        }

        public Criteria andAwaysnatchNotIn(List<Integer> values) {
            addCriterion("awaySnatch not in", values, "awaysnatch");
            return (Criteria) this;
        }

        public Criteria andAwaysnatchBetween(Integer value1, Integer value2) {
            addCriterion("awaySnatch between", value1, value2, "awaysnatch");
            return (Criteria) this;
        }

        public Criteria andAwaysnatchNotBetween(Integer value1, Integer value2) {
            addCriterion("awaySnatch not between", value1, value2, "awaysnatch");
            return (Criteria) this;
        }

        public Criteria andAwayblockIsNull() {
            addCriterion("awayBlock is null");
            return (Criteria) this;
        }

        public Criteria andAwayblockIsNotNull() {
            addCriterion("awayBlock is not null");
            return (Criteria) this;
        }

        public Criteria andAwayblockEqualTo(Integer value) {
            addCriterion("awayBlock =", value, "awayblock");
            return (Criteria) this;
        }

        public Criteria andAwayblockNotEqualTo(Integer value) {
            addCriterion("awayBlock <>", value, "awayblock");
            return (Criteria) this;
        }

        public Criteria andAwayblockGreaterThan(Integer value) {
            addCriterion("awayBlock >", value, "awayblock");
            return (Criteria) this;
        }

        public Criteria andAwayblockGreaterThanOrEqualTo(Integer value) {
            addCriterion("awayBlock >=", value, "awayblock");
            return (Criteria) this;
        }

        public Criteria andAwayblockLessThan(Integer value) {
            addCriterion("awayBlock <", value, "awayblock");
            return (Criteria) this;
        }

        public Criteria andAwayblockLessThanOrEqualTo(Integer value) {
            addCriterion("awayBlock <=", value, "awayblock");
            return (Criteria) this;
        }

        public Criteria andAwayblockIn(List<Integer> values) {
            addCriterion("awayBlock in", values, "awayblock");
            return (Criteria) this;
        }

        public Criteria andAwayblockNotIn(List<Integer> values) {
            addCriterion("awayBlock not in", values, "awayblock");
            return (Criteria) this;
        }

        public Criteria andAwayblockBetween(Integer value1, Integer value2) {
            addCriterion("awayBlock between", value1, value2, "awayblock");
            return (Criteria) this;
        }

        public Criteria andAwayblockNotBetween(Integer value1, Integer value2) {
            addCriterion("awayBlock not between", value1, value2, "awayblock");
            return (Criteria) this;
        }

        public Criteria andAwayfaultIsNull() {
            addCriterion("awayFault is null");
            return (Criteria) this;
        }

        public Criteria andAwayfaultIsNotNull() {
            addCriterion("awayFault is not null");
            return (Criteria) this;
        }

        public Criteria andAwayfaultEqualTo(Integer value) {
            addCriterion("awayFault =", value, "awayfault");
            return (Criteria) this;
        }

        public Criteria andAwayfaultNotEqualTo(Integer value) {
            addCriterion("awayFault <>", value, "awayfault");
            return (Criteria) this;
        }

        public Criteria andAwayfaultGreaterThan(Integer value) {
            addCriterion("awayFault >", value, "awayfault");
            return (Criteria) this;
        }

        public Criteria andAwayfaultGreaterThanOrEqualTo(Integer value) {
            addCriterion("awayFault >=", value, "awayfault");
            return (Criteria) this;
        }

        public Criteria andAwayfaultLessThan(Integer value) {
            addCriterion("awayFault <", value, "awayfault");
            return (Criteria) this;
        }

        public Criteria andAwayfaultLessThanOrEqualTo(Integer value) {
            addCriterion("awayFault <=", value, "awayfault");
            return (Criteria) this;
        }

        public Criteria andAwayfaultIn(List<Integer> values) {
            addCriterion("awayFault in", values, "awayfault");
            return (Criteria) this;
        }

        public Criteria andAwayfaultNotIn(List<Integer> values) {
            addCriterion("awayFault not in", values, "awayfault");
            return (Criteria) this;
        }

        public Criteria andAwayfaultBetween(Integer value1, Integer value2) {
            addCriterion("awayFault between", value1, value2, "awayfault");
            return (Criteria) this;
        }

        public Criteria andAwayfaultNotBetween(Integer value1, Integer value2) {
            addCriterion("awayFault not between", value1, value2, "awayfault");
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

        public Criteria andMatchidEqualTo(Integer value) {
            addCriterion("matchId =", value, "matchid");
            return (Criteria) this;
        }

        public Criteria andMatchidNotEqualTo(Integer value) {
            addCriterion("matchId <>", value, "matchid");
            return (Criteria) this;
        }

        public Criteria andMatchidGreaterThan(Integer value) {
            addCriterion("matchId >", value, "matchid");
            return (Criteria) this;
        }

        public Criteria andMatchidGreaterThanOrEqualTo(Integer value) {
            addCriterion("matchId >=", value, "matchid");
            return (Criteria) this;
        }

        public Criteria andMatchidLessThan(Integer value) {
            addCriterion("matchId <", value, "matchid");
            return (Criteria) this;
        }

        public Criteria andMatchidLessThanOrEqualTo(Integer value) {
            addCriterion("matchId <=", value, "matchid");
            return (Criteria) this;
        }

        public Criteria andMatchidIn(List<Integer> values) {
            addCriterion("matchId in", values, "matchid");
            return (Criteria) this;
        }

        public Criteria andMatchidNotIn(List<Integer> values) {
            addCriterion("matchId not in", values, "matchid");
            return (Criteria) this;
        }

        public Criteria andMatchidBetween(Integer value1, Integer value2) {
            addCriterion("matchId between", value1, value2, "matchid");
            return (Criteria) this;
        }

        public Criteria andMatchidNotBetween(Integer value1, Integer value2) {
            addCriterion("matchId not between", value1, value2, "matchid");
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