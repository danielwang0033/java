package com.coin.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ActivityCheckInExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ActivityCheckInExample() {
        oredCriteria = new ArrayList<>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
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
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
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

        public Criteria andActivityIdIsNull() {
            addCriterion("activity_id is null");
            return (Criteria) this;
        }

        public Criteria andActivityIdIsNotNull() {
            addCriterion("activity_id is not null");
            return (Criteria) this;
        }

        public Criteria andActivityIdEqualTo(Long value) {
            addCriterion("activity_id =", value, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdNotEqualTo(Long value) {
            addCriterion("activity_id <>", value, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdGreaterThan(Long value) {
            addCriterion("activity_id >", value, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdGreaterThanOrEqualTo(Long value) {
            addCriterion("activity_id >=", value, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdLessThan(Long value) {
            addCriterion("activity_id <", value, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdLessThanOrEqualTo(Long value) {
            addCriterion("activity_id <=", value, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdIn(List<Long> values) {
            addCriterion("activity_id in", values, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdNotIn(List<Long> values) {
            addCriterion("activity_id not in", values, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdBetween(Long value1, Long value2) {
            addCriterion("activity_id between", value1, value2, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdNotBetween(Long value1, Long value2) {
            addCriterion("activity_id not between", value1, value2, "activityId");
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

        public Criteria andDayPrizeExchangeIdIsNull() {
            addCriterion("day_prize_exchange_id is null");
            return (Criteria) this;
        }

        public Criteria andDayPrizeExchangeIdIsNotNull() {
            addCriterion("day_prize_exchange_id is not null");
            return (Criteria) this;
        }

        public Criteria andDayPrizeExchangeIdEqualTo(Long value) {
            addCriterion("day_prize_exchange_id =", value, "dayPrizeExchangeId");
            return (Criteria) this;
        }

        public Criteria andDayPrizeExchangeIdNotEqualTo(Long value) {
            addCriterion("day_prize_exchange_id <>", value, "dayPrizeExchangeId");
            return (Criteria) this;
        }

        public Criteria andDayPrizeExchangeIdGreaterThan(Long value) {
            addCriterion("day_prize_exchange_id >", value, "dayPrizeExchangeId");
            return (Criteria) this;
        }

        public Criteria andDayPrizeExchangeIdGreaterThanOrEqualTo(Long value) {
            addCriterion("day_prize_exchange_id >=", value, "dayPrizeExchangeId");
            return (Criteria) this;
        }

        public Criteria andDayPrizeExchangeIdLessThan(Long value) {
            addCriterion("day_prize_exchange_id <", value, "dayPrizeExchangeId");
            return (Criteria) this;
        }

        public Criteria andDayPrizeExchangeIdLessThanOrEqualTo(Long value) {
            addCriterion("day_prize_exchange_id <=", value, "dayPrizeExchangeId");
            return (Criteria) this;
        }

        public Criteria andDayPrizeExchangeIdIn(List<Long> values) {
            addCriterion("day_prize_exchange_id in", values, "dayPrizeExchangeId");
            return (Criteria) this;
        }

        public Criteria andDayPrizeExchangeIdNotIn(List<Long> values) {
            addCriterion("day_prize_exchange_id not in", values, "dayPrizeExchangeId");
            return (Criteria) this;
        }

        public Criteria andDayPrizeExchangeIdBetween(Long value1, Long value2) {
            addCriterion("day_prize_exchange_id between", value1, value2, "dayPrizeExchangeId");
            return (Criteria) this;
        }

        public Criteria andDayPrizeExchangeIdNotBetween(Long value1, Long value2) {
            addCriterion("day_prize_exchange_id not between", value1, value2, "dayPrizeExchangeId");
            return (Criteria) this;
        }

        public Criteria andSerialDaysIsNull() {
            addCriterion("serial_days is null");
            return (Criteria) this;
        }

        public Criteria andSerialDaysIsNotNull() {
            addCriterion("serial_days is not null");
            return (Criteria) this;
        }

        public Criteria andSerialDaysEqualTo(Integer value) {
            addCriterion("serial_days =", value, "serialDays");
            return (Criteria) this;
        }

        public Criteria andSerialDaysNotEqualTo(Integer value) {
            addCriterion("serial_days <>", value, "serialDays");
            return (Criteria) this;
        }

        public Criteria andSerialDaysGreaterThan(Integer value) {
            addCriterion("serial_days >", value, "serialDays");
            return (Criteria) this;
        }

        public Criteria andSerialDaysGreaterThanOrEqualTo(Integer value) {
            addCriterion("serial_days >=", value, "serialDays");
            return (Criteria) this;
        }

        public Criteria andSerialDaysLessThan(Integer value) {
            addCriterion("serial_days <", value, "serialDays");
            return (Criteria) this;
        }

        public Criteria andSerialDaysLessThanOrEqualTo(Integer value) {
            addCriterion("serial_days <=", value, "serialDays");
            return (Criteria) this;
        }

        public Criteria andSerialDaysIn(List<Integer> values) {
            addCriterion("serial_days in", values, "serialDays");
            return (Criteria) this;
        }

        public Criteria andSerialDaysNotIn(List<Integer> values) {
            addCriterion("serial_days not in", values, "serialDays");
            return (Criteria) this;
        }

        public Criteria andSerialDaysBetween(Integer value1, Integer value2) {
            addCriterion("serial_days between", value1, value2, "serialDays");
            return (Criteria) this;
        }

        public Criteria andSerialDaysNotBetween(Integer value1, Integer value2) {
            addCriterion("serial_days not between", value1, value2, "serialDays");
            return (Criteria) this;
        }

        public Criteria andSerialPrizeExchangeIdIsNull() {
            addCriterion("serial_prize_exchange_id is null");
            return (Criteria) this;
        }

        public Criteria andSerialPrizeExchangeIdIsNotNull() {
            addCriterion("serial_prize_exchange_id is not null");
            return (Criteria) this;
        }

        public Criteria andSerialPrizeExchangeIdEqualTo(Long value) {
            addCriterion("serial_prize_exchange_id =", value, "serialPrizeExchangeId");
            return (Criteria) this;
        }

        public Criteria andSerialPrizeExchangeIdNotEqualTo(Long value) {
            addCriterion("serial_prize_exchange_id <>", value, "serialPrizeExchangeId");
            return (Criteria) this;
        }

        public Criteria andSerialPrizeExchangeIdGreaterThan(Long value) {
            addCriterion("serial_prize_exchange_id >", value, "serialPrizeExchangeId");
            return (Criteria) this;
        }

        public Criteria andSerialPrizeExchangeIdGreaterThanOrEqualTo(Long value) {
            addCriterion("serial_prize_exchange_id >=", value, "serialPrizeExchangeId");
            return (Criteria) this;
        }

        public Criteria andSerialPrizeExchangeIdLessThan(Long value) {
            addCriterion("serial_prize_exchange_id <", value, "serialPrizeExchangeId");
            return (Criteria) this;
        }

        public Criteria andSerialPrizeExchangeIdLessThanOrEqualTo(Long value) {
            addCriterion("serial_prize_exchange_id <=", value, "serialPrizeExchangeId");
            return (Criteria) this;
        }

        public Criteria andSerialPrizeExchangeIdIn(List<Long> values) {
            addCriterion("serial_prize_exchange_id in", values, "serialPrizeExchangeId");
            return (Criteria) this;
        }

        public Criteria andSerialPrizeExchangeIdNotIn(List<Long> values) {
            addCriterion("serial_prize_exchange_id not in", values, "serialPrizeExchangeId");
            return (Criteria) this;
        }

        public Criteria andSerialPrizeExchangeIdBetween(Long value1, Long value2) {
            addCriterion("serial_prize_exchange_id between", value1, value2, "serialPrizeExchangeId");
            return (Criteria) this;
        }

        public Criteria andSerialPrizeExchangeIdNotBetween(Long value1, Long value2) {
            addCriterion("serial_prize_exchange_id not between", value1, value2, "serialPrizeExchangeId");
            return (Criteria) this;
        }

        public Criteria andCreatedByIsNull() {
            addCriterion("created_by is null");
            return (Criteria) this;
        }

        public Criteria andCreatedByIsNotNull() {
            addCriterion("created_by is not null");
            return (Criteria) this;
        }

        public Criteria andCreatedByEqualTo(String value) {
            addCriterion("created_by =", value, "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreatedByNotEqualTo(String value) {
            addCriterion("created_by <>", value, "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreatedByGreaterThan(String value) {
            addCriterion("created_by >", value, "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreatedByGreaterThanOrEqualTo(String value) {
            addCriterion("created_by >=", value, "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreatedByLessThan(String value) {
            addCriterion("created_by <", value, "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreatedByLessThanOrEqualTo(String value) {
            addCriterion("created_by <=", value, "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreatedByLike(String value) {
            addCriterion("created_by like", value, "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreatedByNotLike(String value) {
            addCriterion("created_by not like", value, "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreatedByIn(List<String> values) {
            addCriterion("created_by in", values, "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreatedByNotIn(List<String> values) {
            addCriterion("created_by not in", values, "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreatedByBetween(String value1, String value2) {
            addCriterion("created_by between", value1, value2, "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreatedByNotBetween(String value1, String value2) {
            addCriterion("created_by not between", value1, value2, "createdBy");
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

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

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