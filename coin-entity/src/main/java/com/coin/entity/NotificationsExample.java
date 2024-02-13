package com.coin.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
public class NotificationsExample {
    @Setter
    protected String orderByClause;

    @Setter
    protected boolean distinct;

    protected final List<Criteria> oredCriteria;

    public NotificationsExample() {
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

        public Criteria andTypeIsNull() {
            addCriterion("`type` is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("`type` is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(String value) {
            addCriterion("`type` =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(String value) {
            addCriterion("`type` <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(String value) {
            addCriterion("`type` >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(String value) {
            addCriterion("`type` >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(String value) {
            addCriterion("`type` <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(String value) {
            addCriterion("`type` <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLike(String value) {
            addCriterion("`type` like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotLike(String value) {
            addCriterion("`type` not like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<String> values) {
            addCriterion("`type` in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<String> values) {
            addCriterion("`type` not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(String value1, String value2) {
            addCriterion("`type` between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(String value1, String value2) {
            addCriterion("`type` not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andNotifiableTypeIsNull() {
            addCriterion("notifiable_type is null");
            return (Criteria) this;
        }

        public Criteria andNotifiableTypeIsNotNull() {
            addCriterion("notifiable_type is not null");
            return (Criteria) this;
        }

        public Criteria andNotifiableTypeEqualTo(String value) {
            addCriterion("notifiable_type =", value, "notifiableType");
            return (Criteria) this;
        }

        public Criteria andNotifiableTypeNotEqualTo(String value) {
            addCriterion("notifiable_type <>", value, "notifiableType");
            return (Criteria) this;
        }

        public Criteria andNotifiableTypeGreaterThan(String value) {
            addCriterion("notifiable_type >", value, "notifiableType");
            return (Criteria) this;
        }

        public Criteria andNotifiableTypeGreaterThanOrEqualTo(String value) {
            addCriterion("notifiable_type >=", value, "notifiableType");
            return (Criteria) this;
        }

        public Criteria andNotifiableTypeLessThan(String value) {
            addCriterion("notifiable_type <", value, "notifiableType");
            return (Criteria) this;
        }

        public Criteria andNotifiableTypeLessThanOrEqualTo(String value) {
            addCriterion("notifiable_type <=", value, "notifiableType");
            return (Criteria) this;
        }

        public Criteria andNotifiableTypeLike(String value) {
            addCriterion("notifiable_type like", value, "notifiableType");
            return (Criteria) this;
        }

        public Criteria andNotifiableTypeNotLike(String value) {
            addCriterion("notifiable_type not like", value, "notifiableType");
            return (Criteria) this;
        }

        public Criteria andNotifiableTypeIn(List<String> values) {
            addCriterion("notifiable_type in", values, "notifiableType");
            return (Criteria) this;
        }

        public Criteria andNotifiableTypeNotIn(List<String> values) {
            addCriterion("notifiable_type not in", values, "notifiableType");
            return (Criteria) this;
        }

        public Criteria andNotifiableTypeBetween(String value1, String value2) {
            addCriterion("notifiable_type between", value1, value2, "notifiableType");
            return (Criteria) this;
        }

        public Criteria andNotifiableTypeNotBetween(String value1, String value2) {
            addCriterion("notifiable_type not between", value1, value2, "notifiableType");
            return (Criteria) this;
        }

        public Criteria andNotifiableIdIsNull() {
            addCriterion("notifiable_id is null");
            return (Criteria) this;
        }

        public Criteria andNotifiableIdIsNotNull() {
            addCriterion("notifiable_id is not null");
            return (Criteria) this;
        }

        public Criteria andNotifiableIdEqualTo(Long value) {
            addCriterion("notifiable_id =", value, "notifiableId");
            return (Criteria) this;
        }

        public Criteria andNotifiableIdNotEqualTo(Long value) {
            addCriterion("notifiable_id <>", value, "notifiableId");
            return (Criteria) this;
        }

        public Criteria andNotifiableIdGreaterThan(Long value) {
            addCriterion("notifiable_id >", value, "notifiableId");
            return (Criteria) this;
        }

        public Criteria andNotifiableIdGreaterThanOrEqualTo(Long value) {
            addCriterion("notifiable_id >=", value, "notifiableId");
            return (Criteria) this;
        }

        public Criteria andNotifiableIdLessThan(Long value) {
            addCriterion("notifiable_id <", value, "notifiableId");
            return (Criteria) this;
        }

        public Criteria andNotifiableIdLessThanOrEqualTo(Long value) {
            addCriterion("notifiable_id <=", value, "notifiableId");
            return (Criteria) this;
        }

        public Criteria andNotifiableIdIn(List<Long> values) {
            addCriterion("notifiable_id in", values, "notifiableId");
            return (Criteria) this;
        }

        public Criteria andNotifiableIdNotIn(List<Long> values) {
            addCriterion("notifiable_id not in", values, "notifiableId");
            return (Criteria) this;
        }

        public Criteria andNotifiableIdBetween(Long value1, Long value2) {
            addCriterion("notifiable_id between", value1, value2, "notifiableId");
            return (Criteria) this;
        }

        public Criteria andNotifiableIdNotBetween(Long value1, Long value2) {
            addCriterion("notifiable_id not between", value1, value2, "notifiableId");
            return (Criteria) this;
        }

        public Criteria andDataIsNull() {
            addCriterion("`data` is null");
            return (Criteria) this;
        }

        public Criteria andDataIsNotNull() {
            addCriterion("`data` is not null");
            return (Criteria) this;
        }

        public Criteria andDataEqualTo(String value) {
            addCriterion("`data` =", value, "data");
            return (Criteria) this;
        }

        public Criteria andDataNotEqualTo(String value) {
            addCriterion("`data` <>", value, "data");
            return (Criteria) this;
        }

        public Criteria andDataGreaterThan(String value) {
            addCriterion("`data` >", value, "data");
            return (Criteria) this;
        }

        public Criteria andDataGreaterThanOrEqualTo(String value) {
            addCriterion("`data` >=", value, "data");
            return (Criteria) this;
        }

        public Criteria andDataLessThan(String value) {
            addCriterion("`data` <", value, "data");
            return (Criteria) this;
        }

        public Criteria andDataLessThanOrEqualTo(String value) {
            addCriterion("`data` <=", value, "data");
            return (Criteria) this;
        }

        public Criteria andDataLike(String value) {
            addCriterion("`data` like", value, "data");
            return (Criteria) this;
        }

        public Criteria andDataNotLike(String value) {
            addCriterion("`data` not like", value, "data");
            return (Criteria) this;
        }

        public Criteria andDataIn(List<String> values) {
            addCriterion("`data` in", values, "data");
            return (Criteria) this;
        }

        public Criteria andDataNotIn(List<String> values) {
            addCriterion("`data` not in", values, "data");
            return (Criteria) this;
        }

        public Criteria andDataBetween(String value1, String value2) {
            addCriterion("`data` between", value1, value2, "data");
            return (Criteria) this;
        }

        public Criteria andDataNotBetween(String value1, String value2) {
            addCriterion("`data` not between", value1, value2, "data");
            return (Criteria) this;
        }

        public Criteria andReadAtIsNull() {
            addCriterion("read_at is null");
            return (Criteria) this;
        }

        public Criteria andReadAtIsNotNull() {
            addCriterion("read_at is not null");
            return (Criteria) this;
        }

        public Criteria andReadAtEqualTo(Date value) {
            addCriterion("read_at =", value, "readAt");
            return (Criteria) this;
        }

        public Criteria andReadAtNotEqualTo(Date value) {
            addCriterion("read_at <>", value, "readAt");
            return (Criteria) this;
        }

        public Criteria andReadAtGreaterThan(Date value) {
            addCriterion("read_at >", value, "readAt");
            return (Criteria) this;
        }

        public Criteria andReadAtGreaterThanOrEqualTo(Date value) {
            addCriterion("read_at >=", value, "readAt");
            return (Criteria) this;
        }

        public Criteria andReadAtLessThan(Date value) {
            addCriterion("read_at <", value, "readAt");
            return (Criteria) this;
        }

        public Criteria andReadAtLessThanOrEqualTo(Date value) {
            addCriterion("read_at <=", value, "readAt");
            return (Criteria) this;
        }

        public Criteria andReadAtIn(List<Date> values) {
            addCriterion("read_at in", values, "readAt");
            return (Criteria) this;
        }

        public Criteria andReadAtNotIn(List<Date> values) {
            addCriterion("read_at not in", values, "readAt");
            return (Criteria) this;
        }

        public Criteria andReadAtBetween(Date value1, Date value2) {
            addCriterion("read_at between", value1, value2, "readAt");
            return (Criteria) this;
        }

        public Criteria andReadAtNotBetween(Date value1, Date value2) {
            addCriterion("read_at not between", value1, value2, "readAt");
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