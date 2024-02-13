package com.coin.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
public class ReportTagsExample {
    @Setter
    protected String orderByClause;

    @Setter
    protected boolean distinct;

    protected final List<Criteria> oredCriteria;

    public ReportTagsExample() {
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

        public Criteria andTagContentIsNull() {
            addCriterion("tag_content is null");
            return (Criteria) this;
        }

        public Criteria andTagContentIsNotNull() {
            addCriterion("tag_content is not null");
            return (Criteria) this;
        }

        public Criteria andTagContentEqualTo(String value) {
            addCriterion("tag_content =", value, "tagContent");
            return (Criteria) this;
        }

        public Criteria andTagContentNotEqualTo(String value) {
            addCriterion("tag_content <>", value, "tagContent");
            return (Criteria) this;
        }

        public Criteria andTagContentGreaterThan(String value) {
            addCriterion("tag_content >", value, "tagContent");
            return (Criteria) this;
        }

        public Criteria andTagContentGreaterThanOrEqualTo(String value) {
            addCriterion("tag_content >=", value, "tagContent");
            return (Criteria) this;
        }

        public Criteria andTagContentLessThan(String value) {
            addCriterion("tag_content <", value, "tagContent");
            return (Criteria) this;
        }

        public Criteria andTagContentLessThanOrEqualTo(String value) {
            addCriterion("tag_content <=", value, "tagContent");
            return (Criteria) this;
        }

        public Criteria andTagContentLike(String value) {
            addCriterion("tag_content like", value, "tagContent");
            return (Criteria) this;
        }

        public Criteria andTagContentNotLike(String value) {
            addCriterion("tag_content not like", value, "tagContent");
            return (Criteria) this;
        }

        public Criteria andTagContentIn(List<String> values) {
            addCriterion("tag_content in", values, "tagContent");
            return (Criteria) this;
        }

        public Criteria andTagContentNotIn(List<String> values) {
            addCriterion("tag_content not in", values, "tagContent");
            return (Criteria) this;
        }

        public Criteria andTagContentBetween(String value1, String value2) {
            addCriterion("tag_content between", value1, value2, "tagContent");
            return (Criteria) this;
        }

        public Criteria andTagContentNotBetween(String value1, String value2) {
            addCriterion("tag_content not between", value1, value2, "tagContent");
            return (Criteria) this;
        }

        public Criteria andTagImageUrlIsNull() {
            addCriterion("tag_image_url is null");
            return (Criteria) this;
        }

        public Criteria andTagImageUrlIsNotNull() {
            addCriterion("tag_image_url is not null");
            return (Criteria) this;
        }

        public Criteria andTagImageUrlEqualTo(String value) {
            addCriterion("tag_image_url =", value, "tagImageUrl");
            return (Criteria) this;
        }

        public Criteria andTagImageUrlNotEqualTo(String value) {
            addCriterion("tag_image_url <>", value, "tagImageUrl");
            return (Criteria) this;
        }

        public Criteria andTagImageUrlGreaterThan(String value) {
            addCriterion("tag_image_url >", value, "tagImageUrl");
            return (Criteria) this;
        }

        public Criteria andTagImageUrlGreaterThanOrEqualTo(String value) {
            addCriterion("tag_image_url >=", value, "tagImageUrl");
            return (Criteria) this;
        }

        public Criteria andTagImageUrlLessThan(String value) {
            addCriterion("tag_image_url <", value, "tagImageUrl");
            return (Criteria) this;
        }

        public Criteria andTagImageUrlLessThanOrEqualTo(String value) {
            addCriterion("tag_image_url <=", value, "tagImageUrl");
            return (Criteria) this;
        }

        public Criteria andTagImageUrlLike(String value) {
            addCriterion("tag_image_url like", value, "tagImageUrl");
            return (Criteria) this;
        }

        public Criteria andTagImageUrlNotLike(String value) {
            addCriterion("tag_image_url not like", value, "tagImageUrl");
            return (Criteria) this;
        }

        public Criteria andTagImageUrlIn(List<String> values) {
            addCriterion("tag_image_url in", values, "tagImageUrl");
            return (Criteria) this;
        }

        public Criteria andTagImageUrlNotIn(List<String> values) {
            addCriterion("tag_image_url not in", values, "tagImageUrl");
            return (Criteria) this;
        }

        public Criteria andTagImageUrlBetween(String value1, String value2) {
            addCriterion("tag_image_url between", value1, value2, "tagImageUrl");
            return (Criteria) this;
        }

        public Criteria andTagImageUrlNotBetween(String value1, String value2) {
            addCriterion("tag_image_url not between", value1, value2, "tagImageUrl");
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