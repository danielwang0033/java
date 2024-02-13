package com.coin.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
public class MatchVideosExample {
    @Setter
    protected String orderByClause;

    @Setter
    protected boolean distinct;

    protected final List<Criteria> oredCriteria;

    public MatchVideosExample() {
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

        public Criteria andStreamidIsNull() {
            addCriterion("streamId is null");
            return (Criteria) this;
        }

        public Criteria andStreamidIsNotNull() {
            addCriterion("streamId is not null");
            return (Criteria) this;
        }

        public Criteria andStreamidEqualTo(Integer value) {
            addCriterion("streamId =", value, "streamid");
            return (Criteria) this;
        }

        public Criteria andStreamidNotEqualTo(Integer value) {
            addCriterion("streamId <>", value, "streamid");
            return (Criteria) this;
        }

        public Criteria andStreamidGreaterThan(Integer value) {
            addCriterion("streamId >", value, "streamid");
            return (Criteria) this;
        }

        public Criteria andStreamidGreaterThanOrEqualTo(Integer value) {
            addCriterion("streamId >=", value, "streamid");
            return (Criteria) this;
        }

        public Criteria andStreamidLessThan(Integer value) {
            addCriterion("streamId <", value, "streamid");
            return (Criteria) this;
        }

        public Criteria andStreamidLessThanOrEqualTo(Integer value) {
            addCriterion("streamId <=", value, "streamid");
            return (Criteria) this;
        }

        public Criteria andStreamidIn(List<Integer> values) {
            addCriterion("streamId in", values, "streamid");
            return (Criteria) this;
        }

        public Criteria andStreamidNotIn(List<Integer> values) {
            addCriterion("streamId not in", values, "streamid");
            return (Criteria) this;
        }

        public Criteria andStreamidBetween(Integer value1, Integer value2) {
            addCriterion("streamId between", value1, value2, "streamid");
            return (Criteria) this;
        }

        public Criteria andStreamidNotBetween(Integer value1, Integer value2) {
            addCriterion("streamId not between", value1, value2, "streamid");
            return (Criteria) this;
        }

        public Criteria andPushingIsNull() {
            addCriterion("pushing is null");
            return (Criteria) this;
        }

        public Criteria andPushingIsNotNull() {
            addCriterion("pushing is not null");
            return (Criteria) this;
        }

        public Criteria andPushingEqualTo(Integer value) {
            addCriterion("pushing =", value, "pushing");
            return (Criteria) this;
        }

        public Criteria andPushingNotEqualTo(Integer value) {
            addCriterion("pushing <>", value, "pushing");
            return (Criteria) this;
        }

        public Criteria andPushingGreaterThan(Integer value) {
            addCriterion("pushing >", value, "pushing");
            return (Criteria) this;
        }

        public Criteria andPushingGreaterThanOrEqualTo(Integer value) {
            addCriterion("pushing >=", value, "pushing");
            return (Criteria) this;
        }

        public Criteria andPushingLessThan(Integer value) {
            addCriterion("pushing <", value, "pushing");
            return (Criteria) this;
        }

        public Criteria andPushingLessThanOrEqualTo(Integer value) {
            addCriterion("pushing <=", value, "pushing");
            return (Criteria) this;
        }

        public Criteria andPushingIn(List<Integer> values) {
            addCriterion("pushing in", values, "pushing");
            return (Criteria) this;
        }

        public Criteria andPushingNotIn(List<Integer> values) {
            addCriterion("pushing not in", values, "pushing");
            return (Criteria) this;
        }

        public Criteria andPushingBetween(Integer value1, Integer value2) {
            addCriterion("pushing between", value1, value2, "pushing");
            return (Criteria) this;
        }

        public Criteria andPushingNotBetween(Integer value1, Integer value2) {
            addCriterion("pushing not between", value1, value2, "pushing");
            return (Criteria) this;
        }

        public Criteria andStreamtypeIsNull() {
            addCriterion("streamType is null");
            return (Criteria) this;
        }

        public Criteria andStreamtypeIsNotNull() {
            addCriterion("streamType is not null");
            return (Criteria) this;
        }

        public Criteria andStreamtypeEqualTo(Integer value) {
            addCriterion("streamType =", value, "streamtype");
            return (Criteria) this;
        }

        public Criteria andStreamtypeNotEqualTo(Integer value) {
            addCriterion("streamType <>", value, "streamtype");
            return (Criteria) this;
        }

        public Criteria andStreamtypeGreaterThan(Integer value) {
            addCriterion("streamType >", value, "streamtype");
            return (Criteria) this;
        }

        public Criteria andStreamtypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("streamType >=", value, "streamtype");
            return (Criteria) this;
        }

        public Criteria andStreamtypeLessThan(Integer value) {
            addCriterion("streamType <", value, "streamtype");
            return (Criteria) this;
        }

        public Criteria andStreamtypeLessThanOrEqualTo(Integer value) {
            addCriterion("streamType <=", value, "streamtype");
            return (Criteria) this;
        }

        public Criteria andStreamtypeIn(List<Integer> values) {
            addCriterion("streamType in", values, "streamtype");
            return (Criteria) this;
        }

        public Criteria andStreamtypeNotIn(List<Integer> values) {
            addCriterion("streamType not in", values, "streamtype");
            return (Criteria) this;
        }

        public Criteria andStreamtypeBetween(Integer value1, Integer value2) {
            addCriterion("streamType between", value1, value2, "streamtype");
            return (Criteria) this;
        }

        public Criteria andStreamtypeNotBetween(Integer value1, Integer value2) {
            addCriterion("streamType not between", value1, value2, "streamtype");
            return (Criteria) this;
        }

        public Criteria andStreamnameIsNull() {
            addCriterion("streamName is null");
            return (Criteria) this;
        }

        public Criteria andStreamnameIsNotNull() {
            addCriterion("streamName is not null");
            return (Criteria) this;
        }

        public Criteria andStreamnameEqualTo(String value) {
            addCriterion("streamName =", value, "streamname");
            return (Criteria) this;
        }

        public Criteria andStreamnameNotEqualTo(String value) {
            addCriterion("streamName <>", value, "streamname");
            return (Criteria) this;
        }

        public Criteria andStreamnameGreaterThan(String value) {
            addCriterion("streamName >", value, "streamname");
            return (Criteria) this;
        }

        public Criteria andStreamnameGreaterThanOrEqualTo(String value) {
            addCriterion("streamName >=", value, "streamname");
            return (Criteria) this;
        }

        public Criteria andStreamnameLessThan(String value) {
            addCriterion("streamName <", value, "streamname");
            return (Criteria) this;
        }

        public Criteria andStreamnameLessThanOrEqualTo(String value) {
            addCriterion("streamName <=", value, "streamname");
            return (Criteria) this;
        }

        public Criteria andStreamnameLike(String value) {
            addCriterion("streamName like", value, "streamname");
            return (Criteria) this;
        }

        public Criteria andStreamnameNotLike(String value) {
            addCriterion("streamName not like", value, "streamname");
            return (Criteria) this;
        }

        public Criteria andStreamnameIn(List<String> values) {
            addCriterion("streamName in", values, "streamname");
            return (Criteria) this;
        }

        public Criteria andStreamnameNotIn(List<String> values) {
            addCriterion("streamName not in", values, "streamname");
            return (Criteria) this;
        }

        public Criteria andStreamnameBetween(String value1, String value2) {
            addCriterion("streamName between", value1, value2, "streamname");
            return (Criteria) this;
        }

        public Criteria andStreamnameNotBetween(String value1, String value2) {
            addCriterion("streamName not between", value1, value2, "streamname");
            return (Criteria) this;
        }

        public Criteria andM3u8IsNull() {
            addCriterion("m3u8 is null");
            return (Criteria) this;
        }

        public Criteria andM3u8IsNotNull() {
            addCriterion("m3u8 is not null");
            return (Criteria) this;
        }

        public Criteria andM3u8EqualTo(String value) {
            addCriterion("m3u8 =", value, "m3u8");
            return (Criteria) this;
        }

        public Criteria andM3u8NotEqualTo(String value) {
            addCriterion("m3u8 <>", value, "m3u8");
            return (Criteria) this;
        }

        public Criteria andM3u8GreaterThan(String value) {
            addCriterion("m3u8 >", value, "m3u8");
            return (Criteria) this;
        }

        public Criteria andM3u8GreaterThanOrEqualTo(String value) {
            addCriterion("m3u8 >=", value, "m3u8");
            return (Criteria) this;
        }

        public Criteria andM3u8LessThan(String value) {
            addCriterion("m3u8 <", value, "m3u8");
            return (Criteria) this;
        }

        public Criteria andM3u8LessThanOrEqualTo(String value) {
            addCriterion("m3u8 <=", value, "m3u8");
            return (Criteria) this;
        }

        public Criteria andM3u8Like(String value) {
            addCriterion("m3u8 like", value, "m3u8");
            return (Criteria) this;
        }

        public Criteria andM3u8NotLike(String value) {
            addCriterion("m3u8 not like", value, "m3u8");
            return (Criteria) this;
        }

        public Criteria andM3u8In(List<String> values) {
            addCriterion("m3u8 in", values, "m3u8");
            return (Criteria) this;
        }

        public Criteria andM3u8NotIn(List<String> values) {
            addCriterion("m3u8 not in", values, "m3u8");
            return (Criteria) this;
        }

        public Criteria andM3u8Between(String value1, String value2) {
            addCriterion("m3u8 between", value1, value2, "m3u8");
            return (Criteria) this;
        }

        public Criteria andM3u8NotBetween(String value1, String value2) {
            addCriterion("m3u8 not between", value1, value2, "m3u8");
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