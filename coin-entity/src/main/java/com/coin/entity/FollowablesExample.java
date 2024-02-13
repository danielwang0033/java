package com.coin.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
public class FollowablesExample {
    @Setter
    protected String orderByClause;

    @Setter
    protected boolean distinct;

    protected final List<Criteria> oredCriteria;

    public FollowablesExample() {
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

        public Criteria andFollowableTypeIsNull() {
            addCriterion("followable_type is null");
            return (Criteria) this;
        }

        public Criteria andFollowableTypeIsNotNull() {
            addCriterion("followable_type is not null");
            return (Criteria) this;
        }

        public Criteria andFollowableTypeEqualTo(String value) {
            addCriterion("followable_type =", value, "followableType");
            return (Criteria) this;
        }

        public Criteria andFollowableTypeNotEqualTo(String value) {
            addCriterion("followable_type <>", value, "followableType");
            return (Criteria) this;
        }

        public Criteria andFollowableTypeGreaterThan(String value) {
            addCriterion("followable_type >", value, "followableType");
            return (Criteria) this;
        }

        public Criteria andFollowableTypeGreaterThanOrEqualTo(String value) {
            addCriterion("followable_type >=", value, "followableType");
            return (Criteria) this;
        }

        public Criteria andFollowableTypeLessThan(String value) {
            addCriterion("followable_type <", value, "followableType");
            return (Criteria) this;
        }

        public Criteria andFollowableTypeLessThanOrEqualTo(String value) {
            addCriterion("followable_type <=", value, "followableType");
            return (Criteria) this;
        }

        public Criteria andFollowableTypeLike(String value) {
            addCriterion("followable_type like", value, "followableType");
            return (Criteria) this;
        }

        public Criteria andFollowableTypeNotLike(String value) {
            addCriterion("followable_type not like", value, "followableType");
            return (Criteria) this;
        }

        public Criteria andFollowableTypeIn(List<String> values) {
            addCriterion("followable_type in", values, "followableType");
            return (Criteria) this;
        }

        public Criteria andFollowableTypeNotIn(List<String> values) {
            addCriterion("followable_type not in", values, "followableType");
            return (Criteria) this;
        }

        public Criteria andFollowableTypeBetween(String value1, String value2) {
            addCriterion("followable_type between", value1, value2, "followableType");
            return (Criteria) this;
        }

        public Criteria andFollowableTypeNotBetween(String value1, String value2) {
            addCriterion("followable_type not between", value1, value2, "followableType");
            return (Criteria) this;
        }

        public Criteria andFollowableIdIsNull() {
            addCriterion("followable_id is null");
            return (Criteria) this;
        }

        public Criteria andFollowableIdIsNotNull() {
            addCriterion("followable_id is not null");
            return (Criteria) this;
        }

        public Criteria andFollowableIdEqualTo(Long value) {
            addCriterion("followable_id =", value, "followableId");
            return (Criteria) this;
        }

        public Criteria andFollowableIdNotEqualTo(Long value) {
            addCriterion("followable_id <>", value, "followableId");
            return (Criteria) this;
        }

        public Criteria andFollowableIdGreaterThan(Long value) {
            addCriterion("followable_id >", value, "followableId");
            return (Criteria) this;
        }

        public Criteria andFollowableIdGreaterThanOrEqualTo(Long value) {
            addCriterion("followable_id >=", value, "followableId");
            return (Criteria) this;
        }

        public Criteria andFollowableIdLessThan(Long value) {
            addCriterion("followable_id <", value, "followableId");
            return (Criteria) this;
        }

        public Criteria andFollowableIdLessThanOrEqualTo(Long value) {
            addCriterion("followable_id <=", value, "followableId");
            return (Criteria) this;
        }

        public Criteria andFollowableIdIn(List<Long> values) {
            addCriterion("followable_id in", values, "followableId");
            return (Criteria) this;
        }

        public Criteria andFollowableIdNotIn(List<Long> values) {
            addCriterion("followable_id not in", values, "followableId");
            return (Criteria) this;
        }

        public Criteria andFollowableIdBetween(Long value1, Long value2) {
            addCriterion("followable_id between", value1, value2, "followableId");
            return (Criteria) this;
        }

        public Criteria andFollowableIdNotBetween(Long value1, Long value2) {
            addCriterion("followable_id not between", value1, value2, "followableId");
            return (Criteria) this;
        }

        public Criteria andAcceptedAtIsNull() {
            addCriterion("accepted_at is null");
            return (Criteria) this;
        }

        public Criteria andAcceptedAtIsNotNull() {
            addCriterion("accepted_at is not null");
            return (Criteria) this;
        }

        public Criteria andAcceptedAtEqualTo(Date value) {
            addCriterion("accepted_at =", value, "acceptedAt");
            return (Criteria) this;
        }

        public Criteria andAcceptedAtNotEqualTo(Date value) {
            addCriterion("accepted_at <>", value, "acceptedAt");
            return (Criteria) this;
        }

        public Criteria andAcceptedAtGreaterThan(Date value) {
            addCriterion("accepted_at >", value, "acceptedAt");
            return (Criteria) this;
        }

        public Criteria andAcceptedAtGreaterThanOrEqualTo(Date value) {
            addCriterion("accepted_at >=", value, "acceptedAt");
            return (Criteria) this;
        }

        public Criteria andAcceptedAtLessThan(Date value) {
            addCriterion("accepted_at <", value, "acceptedAt");
            return (Criteria) this;
        }

        public Criteria andAcceptedAtLessThanOrEqualTo(Date value) {
            addCriterion("accepted_at <=", value, "acceptedAt");
            return (Criteria) this;
        }

        public Criteria andAcceptedAtIn(List<Date> values) {
            addCriterion("accepted_at in", values, "acceptedAt");
            return (Criteria) this;
        }

        public Criteria andAcceptedAtNotIn(List<Date> values) {
            addCriterion("accepted_at not in", values, "acceptedAt");
            return (Criteria) this;
        }

        public Criteria andAcceptedAtBetween(Date value1, Date value2) {
            addCriterion("accepted_at between", value1, value2, "acceptedAt");
            return (Criteria) this;
        }

        public Criteria andAcceptedAtNotBetween(Date value1, Date value2) {
            addCriterion("accepted_at not between", value1, value2, "acceptedAt");
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