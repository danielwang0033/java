package com.coin.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
public class FavoritesExample {
    @Setter
    protected String orderByClause;

    @Setter
    protected boolean distinct;

    protected final List<Criteria> oredCriteria;

    public FavoritesExample() {
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

        public Criteria andFavoriteableTypeIsNull() {
            addCriterion("favoriteable_type is null");
            return (Criteria) this;
        }

        public Criteria andFavoriteableTypeIsNotNull() {
            addCriterion("favoriteable_type is not null");
            return (Criteria) this;
        }

        public Criteria andFavoriteableTypeEqualTo(String value) {
            addCriterion("favoriteable_type =", value, "favoriteableType");
            return (Criteria) this;
        }

        public Criteria andFavoriteableTypeNotEqualTo(String value) {
            addCriterion("favoriteable_type <>", value, "favoriteableType");
            return (Criteria) this;
        }

        public Criteria andFavoriteableTypeGreaterThan(String value) {
            addCriterion("favoriteable_type >", value, "favoriteableType");
            return (Criteria) this;
        }

        public Criteria andFavoriteableTypeGreaterThanOrEqualTo(String value) {
            addCriterion("favoriteable_type >=", value, "favoriteableType");
            return (Criteria) this;
        }

        public Criteria andFavoriteableTypeLessThan(String value) {
            addCriterion("favoriteable_type <", value, "favoriteableType");
            return (Criteria) this;
        }

        public Criteria andFavoriteableTypeLessThanOrEqualTo(String value) {
            addCriterion("favoriteable_type <=", value, "favoriteableType");
            return (Criteria) this;
        }

        public Criteria andFavoriteableTypeLike(String value) {
            addCriterion("favoriteable_type like", value, "favoriteableType");
            return (Criteria) this;
        }

        public Criteria andFavoriteableTypeNotLike(String value) {
            addCriterion("favoriteable_type not like", value, "favoriteableType");
            return (Criteria) this;
        }

        public Criteria andFavoriteableTypeIn(List<String> values) {
            addCriterion("favoriteable_type in", values, "favoriteableType");
            return (Criteria) this;
        }

        public Criteria andFavoriteableTypeNotIn(List<String> values) {
            addCriterion("favoriteable_type not in", values, "favoriteableType");
            return (Criteria) this;
        }

        public Criteria andFavoriteableTypeBetween(String value1, String value2) {
            addCriterion("favoriteable_type between", value1, value2, "favoriteableType");
            return (Criteria) this;
        }

        public Criteria andFavoriteableTypeNotBetween(String value1, String value2) {
            addCriterion("favoriteable_type not between", value1, value2, "favoriteableType");
            return (Criteria) this;
        }

        public Criteria andFavoriteableIdIsNull() {
            addCriterion("favoriteable_id is null");
            return (Criteria) this;
        }

        public Criteria andFavoriteableIdIsNotNull() {
            addCriterion("favoriteable_id is not null");
            return (Criteria) this;
        }

        public Criteria andFavoriteableIdEqualTo(Long value) {
            addCriterion("favoriteable_id =", value, "favoriteableId");
            return (Criteria) this;
        }

        public Criteria andFavoriteableIdNotEqualTo(Long value) {
            addCriterion("favoriteable_id <>", value, "favoriteableId");
            return (Criteria) this;
        }

        public Criteria andFavoriteableIdGreaterThan(Long value) {
            addCriterion("favoriteable_id >", value, "favoriteableId");
            return (Criteria) this;
        }

        public Criteria andFavoriteableIdGreaterThanOrEqualTo(Long value) {
            addCriterion("favoriteable_id >=", value, "favoriteableId");
            return (Criteria) this;
        }

        public Criteria andFavoriteableIdLessThan(Long value) {
            addCriterion("favoriteable_id <", value, "favoriteableId");
            return (Criteria) this;
        }

        public Criteria andFavoriteableIdLessThanOrEqualTo(Long value) {
            addCriterion("favoriteable_id <=", value, "favoriteableId");
            return (Criteria) this;
        }

        public Criteria andFavoriteableIdIn(List<Long> values) {
            addCriterion("favoriteable_id in", values, "favoriteableId");
            return (Criteria) this;
        }

        public Criteria andFavoriteableIdNotIn(List<Long> values) {
            addCriterion("favoriteable_id not in", values, "favoriteableId");
            return (Criteria) this;
        }

        public Criteria andFavoriteableIdBetween(Long value1, Long value2) {
            addCriterion("favoriteable_id between", value1, value2, "favoriteableId");
            return (Criteria) this;
        }

        public Criteria andFavoriteableIdNotBetween(Long value1, Long value2) {
            addCriterion("favoriteable_id not between", value1, value2, "favoriteableId");
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