package com.coin.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
public class MatchLeaguesExample {
    @Setter
    protected String orderByClause;

    @Setter
    protected boolean distinct;

    protected final List<Criteria> oredCriteria;

    public MatchLeaguesExample() {
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

        public Criteria andLeagueIdIsNull() {
            addCriterion("league_id is null");
            return (Criteria) this;
        }

        public Criteria andLeagueIdIsNotNull() {
            addCriterion("league_id is not null");
            return (Criteria) this;
        }

        public Criteria andLeagueIdEqualTo(String value) {
            addCriterion("league_id =", value, "leagueId");
            return (Criteria) this;
        }

        public Criteria andLeagueIdNotEqualTo(String value) {
            addCriterion("league_id <>", value, "leagueId");
            return (Criteria) this;
        }

        public Criteria andLeagueIdGreaterThan(String value) {
            addCriterion("league_id >", value, "leagueId");
            return (Criteria) this;
        }

        public Criteria andLeagueIdGreaterThanOrEqualTo(String value) {
            addCriterion("league_id >=", value, "leagueId");
            return (Criteria) this;
        }

        public Criteria andLeagueIdLessThan(String value) {
            addCriterion("league_id <", value, "leagueId");
            return (Criteria) this;
        }

        public Criteria andLeagueIdLessThanOrEqualTo(String value) {
            addCriterion("league_id <=", value, "leagueId");
            return (Criteria) this;
        }

        public Criteria andLeagueIdLike(String value) {
            addCriterion("league_id like", value, "leagueId");
            return (Criteria) this;
        }

        public Criteria andLeagueIdNotLike(String value) {
            addCriterion("league_id not like", value, "leagueId");
            return (Criteria) this;
        }

        public Criteria andLeagueIdIn(List<String> values) {
            addCriterion("league_id in", values, "leagueId");
            return (Criteria) this;
        }

        public Criteria andLeagueIdNotIn(List<String> values) {
            addCriterion("league_id not in", values, "leagueId");
            return (Criteria) this;
        }

        public Criteria andLeagueIdBetween(String value1, String value2) {
            addCriterion("league_id between", value1, value2, "leagueId");
            return (Criteria) this;
        }

        public Criteria andLeagueIdNotBetween(String value1, String value2) {
            addCriterion("league_id not between", value1, value2, "leagueId");
            return (Criteria) this;
        }

        public Criteria andLeagueNameIsNull() {
            addCriterion("league_name is null");
            return (Criteria) this;
        }

        public Criteria andLeagueNameIsNotNull() {
            addCriterion("league_name is not null");
            return (Criteria) this;
        }

        public Criteria andLeagueNameEqualTo(String value) {
            addCriterion("league_name =", value, "leagueName");
            return (Criteria) this;
        }

        public Criteria andLeagueNameNotEqualTo(String value) {
            addCriterion("league_name <>", value, "leagueName");
            return (Criteria) this;
        }

        public Criteria andLeagueNameGreaterThan(String value) {
            addCriterion("league_name >", value, "leagueName");
            return (Criteria) this;
        }

        public Criteria andLeagueNameGreaterThanOrEqualTo(String value) {
            addCriterion("league_name >=", value, "leagueName");
            return (Criteria) this;
        }

        public Criteria andLeagueNameLessThan(String value) {
            addCriterion("league_name <", value, "leagueName");
            return (Criteria) this;
        }

        public Criteria andLeagueNameLessThanOrEqualTo(String value) {
            addCriterion("league_name <=", value, "leagueName");
            return (Criteria) this;
        }

        public Criteria andLeagueNameLike(String value) {
            addCriterion("league_name like", value, "leagueName");
            return (Criteria) this;
        }

        public Criteria andLeagueNameNotLike(String value) {
            addCriterion("league_name not like", value, "leagueName");
            return (Criteria) this;
        }

        public Criteria andLeagueNameIn(List<String> values) {
            addCriterion("league_name in", values, "leagueName");
            return (Criteria) this;
        }

        public Criteria andLeagueNameNotIn(List<String> values) {
            addCriterion("league_name not in", values, "leagueName");
            return (Criteria) this;
        }

        public Criteria andLeagueNameBetween(String value1, String value2) {
            addCriterion("league_name between", value1, value2, "leagueName");
            return (Criteria) this;
        }

        public Criteria andLeagueNameNotBetween(String value1, String value2) {
            addCriterion("league_name not between", value1, value2, "leagueName");
            return (Criteria) this;
        }

        public Criteria andLeagueLogoIsNull() {
            addCriterion("league_logo is null");
            return (Criteria) this;
        }

        public Criteria andLeagueLogoIsNotNull() {
            addCriterion("league_logo is not null");
            return (Criteria) this;
        }

        public Criteria andLeagueLogoEqualTo(String value) {
            addCriterion("league_logo =", value, "leagueLogo");
            return (Criteria) this;
        }

        public Criteria andLeagueLogoNotEqualTo(String value) {
            addCriterion("league_logo <>", value, "leagueLogo");
            return (Criteria) this;
        }

        public Criteria andLeagueLogoGreaterThan(String value) {
            addCriterion("league_logo >", value, "leagueLogo");
            return (Criteria) this;
        }

        public Criteria andLeagueLogoGreaterThanOrEqualTo(String value) {
            addCriterion("league_logo >=", value, "leagueLogo");
            return (Criteria) this;
        }

        public Criteria andLeagueLogoLessThan(String value) {
            addCriterion("league_logo <", value, "leagueLogo");
            return (Criteria) this;
        }

        public Criteria andLeagueLogoLessThanOrEqualTo(String value) {
            addCriterion("league_logo <=", value, "leagueLogo");
            return (Criteria) this;
        }

        public Criteria andLeagueLogoLike(String value) {
            addCriterion("league_logo like", value, "leagueLogo");
            return (Criteria) this;
        }

        public Criteria andLeagueLogoNotLike(String value) {
            addCriterion("league_logo not like", value, "leagueLogo");
            return (Criteria) this;
        }

        public Criteria andLeagueLogoIn(List<String> values) {
            addCriterion("league_logo in", values, "leagueLogo");
            return (Criteria) this;
        }

        public Criteria andLeagueLogoNotIn(List<String> values) {
            addCriterion("league_logo not in", values, "leagueLogo");
            return (Criteria) this;
        }

        public Criteria andLeagueLogoBetween(String value1, String value2) {
            addCriterion("league_logo between", value1, value2, "leagueLogo");
            return (Criteria) this;
        }

        public Criteria andLeagueLogoNotBetween(String value1, String value2) {
            addCriterion("league_logo not between", value1, value2, "leagueLogo");
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

        public Criteria andSortIsNull() {
            addCriterion("sort is null");
            return (Criteria) this;
        }

        public Criteria andSortIsNotNull() {
            addCriterion("sort is not null");
            return (Criteria) this;
        }

        public Criteria andSortEqualTo(Integer value) {
            addCriterion("sort =", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotEqualTo(Integer value) {
            addCriterion("sort <>", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortGreaterThan(Integer value) {
            addCriterion("sort >", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortGreaterThanOrEqualTo(Integer value) {
            addCriterion("sort >=", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortLessThan(Integer value) {
            addCriterion("sort <", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortLessThanOrEqualTo(Integer value) {
            addCriterion("sort <=", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortIn(List<Integer> values) {
            addCriterion("sort in", values, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotIn(List<Integer> values) {
            addCriterion("sort not in", values, "sort");
            return (Criteria) this;
        }

        public Criteria andSortBetween(Integer value1, Integer value2) {
            addCriterion("sort between", value1, value2, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotBetween(Integer value1, Integer value2) {
            addCriterion("sort not between", value1, value2, "sort");
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