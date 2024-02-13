package com.coin.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
public class ActivityExample {
    @Setter
    protected String orderByClause;

    @Setter
    protected boolean distinct;

    protected final List<Criteria> oredCriteria;

    public ActivityExample() {
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

        public Criteria andActivityTitleIsNull() {
            addCriterion("activity_title is null");
            return (Criteria) this;
        }

        public Criteria andActivityTitleIsNotNull() {
            addCriterion("activity_title is not null");
            return (Criteria) this;
        }

        public Criteria andActivityTitleEqualTo(String value) {
            addCriterion("activity_title =", value, "activityTitle");
            return (Criteria) this;
        }

        public Criteria andActivityTitleNotEqualTo(String value) {
            addCriterion("activity_title <>", value, "activityTitle");
            return (Criteria) this;
        }

        public Criteria andActivityTitleGreaterThan(String value) {
            addCriterion("activity_title >", value, "activityTitle");
            return (Criteria) this;
        }

        public Criteria andActivityTitleGreaterThanOrEqualTo(String value) {
            addCriterion("activity_title >=", value, "activityTitle");
            return (Criteria) this;
        }

        public Criteria andActivityTitleLessThan(String value) {
            addCriterion("activity_title <", value, "activityTitle");
            return (Criteria) this;
        }

        public Criteria andActivityTitleLessThanOrEqualTo(String value) {
            addCriterion("activity_title <=", value, "activityTitle");
            return (Criteria) this;
        }

        public Criteria andActivityTitleLike(String value) {
            addCriterion("activity_title like", value, "activityTitle");
            return (Criteria) this;
        }

        public Criteria andActivityTitleNotLike(String value) {
            addCriterion("activity_title not like", value, "activityTitle");
            return (Criteria) this;
        }

        public Criteria andActivityTitleIn(List<String> values) {
            addCriterion("activity_title in", values, "activityTitle");
            return (Criteria) this;
        }

        public Criteria andActivityTitleNotIn(List<String> values) {
            addCriterion("activity_title not in", values, "activityTitle");
            return (Criteria) this;
        }

        public Criteria andActivityTitleBetween(String value1, String value2) {
            addCriterion("activity_title between", value1, value2, "activityTitle");
            return (Criteria) this;
        }

        public Criteria andActivityTitleNotBetween(String value1, String value2) {
            addCriterion("activity_title not between", value1, value2, "activityTitle");
            return (Criteria) this;
        }

        public Criteria andActivityTypeIsNull() {
            addCriterion("activity_type is null");
            return (Criteria) this;
        }

        public Criteria andActivityTypeIsNotNull() {
            addCriterion("activity_type is not null");
            return (Criteria) this;
        }

        public Criteria andActivityTypeEqualTo(Integer value) {
            addCriterion("activity_type =", value, "activityType");
            return (Criteria) this;
        }

        public Criteria andActivityTypeNotEqualTo(Integer value) {
            addCriterion("activity_type <>", value, "activityType");
            return (Criteria) this;
        }

        public Criteria andActivityTypeGreaterThan(Integer value) {
            addCriterion("activity_type >", value, "activityType");
            return (Criteria) this;
        }

        public Criteria andActivityTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("activity_type >=", value, "activityType");
            return (Criteria) this;
        }

        public Criteria andActivityTypeLessThan(Integer value) {
            addCriterion("activity_type <", value, "activityType");
            return (Criteria) this;
        }

        public Criteria andActivityTypeLessThanOrEqualTo(Integer value) {
            addCriterion("activity_type <=", value, "activityType");
            return (Criteria) this;
        }

        public Criteria andActivityTypeIn(List<Integer> values) {
            addCriterion("activity_type in", values, "activityType");
            return (Criteria) this;
        }

        public Criteria andActivityTypeNotIn(List<Integer> values) {
            addCriterion("activity_type not in", values, "activityType");
            return (Criteria) this;
        }

        public Criteria andActivityTypeBetween(Integer value1, Integer value2) {
            addCriterion("activity_type between", value1, value2, "activityType");
            return (Criteria) this;
        }

        public Criteria andActivityTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("activity_type not between", value1, value2, "activityType");
            return (Criteria) this;
        }

        public Criteria andPriorityIsNull() {
            addCriterion("priority is null");
            return (Criteria) this;
        }

        public Criteria andPriorityIsNotNull() {
            addCriterion("priority is not null");
            return (Criteria) this;
        }

        public Criteria andPriorityEqualTo(Integer value) {
            addCriterion("priority =", value, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityNotEqualTo(Integer value) {
            addCriterion("priority <>", value, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityGreaterThan(Integer value) {
            addCriterion("priority >", value, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityGreaterThanOrEqualTo(Integer value) {
            addCriterion("priority >=", value, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityLessThan(Integer value) {
            addCriterion("priority <", value, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityLessThanOrEqualTo(Integer value) {
            addCriterion("priority <=", value, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityIn(List<Integer> values) {
            addCriterion("priority in", values, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityNotIn(List<Integer> values) {
            addCriterion("priority not in", values, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityBetween(Integer value1, Integer value2) {
            addCriterion("priority between", value1, value2, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityNotBetween(Integer value1, Integer value2) {
            addCriterion("priority not between", value1, value2, "priority");
            return (Criteria) this;
        }

        public Criteria andShowTimeStartIsNull() {
            addCriterion("show_time_start is null");
            return (Criteria) this;
        }

        public Criteria andShowTimeStartIsNotNull() {
            addCriterion("show_time_start is not null");
            return (Criteria) this;
        }

        public Criteria andShowTimeStartEqualTo(Date value) {
            addCriterion("show_time_start =", value, "showTimeStart");
            return (Criteria) this;
        }

        public Criteria andShowTimeStartNotEqualTo(Date value) {
            addCriterion("show_time_start <>", value, "showTimeStart");
            return (Criteria) this;
        }

        public Criteria andShowTimeStartGreaterThan(Date value) {
            addCriterion("show_time_start >", value, "showTimeStart");
            return (Criteria) this;
        }

        public Criteria andShowTimeStartGreaterThanOrEqualTo(Date value) {
            addCriterion("show_time_start >=", value, "showTimeStart");
            return (Criteria) this;
        }

        public Criteria andShowTimeStartLessThan(Date value) {
            addCriterion("show_time_start <", value, "showTimeStart");
            return (Criteria) this;
        }

        public Criteria andShowTimeStartLessThanOrEqualTo(Date value) {
            addCriterion("show_time_start <=", value, "showTimeStart");
            return (Criteria) this;
        }

        public Criteria andShowTimeStartIn(List<Date> values) {
            addCriterion("show_time_start in", values, "showTimeStart");
            return (Criteria) this;
        }

        public Criteria andShowTimeStartNotIn(List<Date> values) {
            addCriterion("show_time_start not in", values, "showTimeStart");
            return (Criteria) this;
        }

        public Criteria andShowTimeStartBetween(Date value1, Date value2) {
            addCriterion("show_time_start between", value1, value2, "showTimeStart");
            return (Criteria) this;
        }

        public Criteria andShowTimeStartNotBetween(Date value1, Date value2) {
            addCriterion("show_time_start not between", value1, value2, "showTimeStart");
            return (Criteria) this;
        }

        public Criteria andShowTimeEndIsNull() {
            addCriterion("show_time_end is null");
            return (Criteria) this;
        }

        public Criteria andShowTimeEndIsNotNull() {
            addCriterion("show_time_end is not null");
            return (Criteria) this;
        }

        public Criteria andShowTimeEndEqualTo(Date value) {
            addCriterion("show_time_end =", value, "showTimeEnd");
            return (Criteria) this;
        }

        public Criteria andShowTimeEndNotEqualTo(Date value) {
            addCriterion("show_time_end <>", value, "showTimeEnd");
            return (Criteria) this;
        }

        public Criteria andShowTimeEndGreaterThan(Date value) {
            addCriterion("show_time_end >", value, "showTimeEnd");
            return (Criteria) this;
        }

        public Criteria andShowTimeEndGreaterThanOrEqualTo(Date value) {
            addCriterion("show_time_end >=", value, "showTimeEnd");
            return (Criteria) this;
        }

        public Criteria andShowTimeEndLessThan(Date value) {
            addCriterion("show_time_end <", value, "showTimeEnd");
            return (Criteria) this;
        }

        public Criteria andShowTimeEndLessThanOrEqualTo(Date value) {
            addCriterion("show_time_end <=", value, "showTimeEnd");
            return (Criteria) this;
        }

        public Criteria andShowTimeEndIn(List<Date> values) {
            addCriterion("show_time_end in", values, "showTimeEnd");
            return (Criteria) this;
        }

        public Criteria andShowTimeEndNotIn(List<Date> values) {
            addCriterion("show_time_end not in", values, "showTimeEnd");
            return (Criteria) this;
        }

        public Criteria andShowTimeEndBetween(Date value1, Date value2) {
            addCriterion("show_time_end between", value1, value2, "showTimeEnd");
            return (Criteria) this;
        }

        public Criteria andShowTimeEndNotBetween(Date value1, Date value2) {
            addCriterion("show_time_end not between", value1, value2, "showTimeEnd");
            return (Criteria) this;
        }

        public Criteria andActivityTimeStartIsNull() {
            addCriterion("activity_time_start is null");
            return (Criteria) this;
        }

        public Criteria andActivityTimeStartIsNotNull() {
            addCriterion("activity_time_start is not null");
            return (Criteria) this;
        }

        public Criteria andActivityTimeStartEqualTo(Date value) {
            addCriterion("activity_time_start =", value, "activityTimeStart");
            return (Criteria) this;
        }

        public Criteria andActivityTimeStartNotEqualTo(Date value) {
            addCriterion("activity_time_start <>", value, "activityTimeStart");
            return (Criteria) this;
        }

        public Criteria andActivityTimeStartGreaterThan(Date value) {
            addCriterion("activity_time_start >", value, "activityTimeStart");
            return (Criteria) this;
        }

        public Criteria andActivityTimeStartGreaterThanOrEqualTo(Date value) {
            addCriterion("activity_time_start >=", value, "activityTimeStart");
            return (Criteria) this;
        }

        public Criteria andActivityTimeStartLessThan(Date value) {
            addCriterion("activity_time_start <", value, "activityTimeStart");
            return (Criteria) this;
        }

        public Criteria andActivityTimeStartLessThanOrEqualTo(Date value) {
            addCriterion("activity_time_start <=", value, "activityTimeStart");
            return (Criteria) this;
        }

        public Criteria andActivityTimeStartIn(List<Date> values) {
            addCriterion("activity_time_start in", values, "activityTimeStart");
            return (Criteria) this;
        }

        public Criteria andActivityTimeStartNotIn(List<Date> values) {
            addCriterion("activity_time_start not in", values, "activityTimeStart");
            return (Criteria) this;
        }

        public Criteria andActivityTimeStartBetween(Date value1, Date value2) {
            addCriterion("activity_time_start between", value1, value2, "activityTimeStart");
            return (Criteria) this;
        }

        public Criteria andActivityTimeStartNotBetween(Date value1, Date value2) {
            addCriterion("activity_time_start not between", value1, value2, "activityTimeStart");
            return (Criteria) this;
        }

        public Criteria andActivityTimeEndIsNull() {
            addCriterion("activity_time_end is null");
            return (Criteria) this;
        }

        public Criteria andActivityTimeEndIsNotNull() {
            addCriterion("activity_time_end is not null");
            return (Criteria) this;
        }

        public Criteria andActivityTimeEndEqualTo(Date value) {
            addCriterion("activity_time_end =", value, "activityTimeEnd");
            return (Criteria) this;
        }

        public Criteria andActivityTimeEndNotEqualTo(Date value) {
            addCriterion("activity_time_end <>", value, "activityTimeEnd");
            return (Criteria) this;
        }

        public Criteria andActivityTimeEndGreaterThan(Date value) {
            addCriterion("activity_time_end >", value, "activityTimeEnd");
            return (Criteria) this;
        }

        public Criteria andActivityTimeEndGreaterThanOrEqualTo(Date value) {
            addCriterion("activity_time_end >=", value, "activityTimeEnd");
            return (Criteria) this;
        }

        public Criteria andActivityTimeEndLessThan(Date value) {
            addCriterion("activity_time_end <", value, "activityTimeEnd");
            return (Criteria) this;
        }

        public Criteria andActivityTimeEndLessThanOrEqualTo(Date value) {
            addCriterion("activity_time_end <=", value, "activityTimeEnd");
            return (Criteria) this;
        }

        public Criteria andActivityTimeEndIn(List<Date> values) {
            addCriterion("activity_time_end in", values, "activityTimeEnd");
            return (Criteria) this;
        }

        public Criteria andActivityTimeEndNotIn(List<Date> values) {
            addCriterion("activity_time_end not in", values, "activityTimeEnd");
            return (Criteria) this;
        }

        public Criteria andActivityTimeEndBetween(Date value1, Date value2) {
            addCriterion("activity_time_end between", value1, value2, "activityTimeEnd");
            return (Criteria) this;
        }

        public Criteria andActivityTimeEndNotBetween(Date value1, Date value2) {
            addCriterion("activity_time_end not between", value1, value2, "activityTimeEnd");
            return (Criteria) this;
        }

        public Criteria andFloatButtonImageIsNull() {
            addCriterion("float_button_image is null");
            return (Criteria) this;
        }

        public Criteria andFloatButtonImageIsNotNull() {
            addCriterion("float_button_image is not null");
            return (Criteria) this;
        }

        public Criteria andFloatButtonImageEqualTo(String value) {
            addCriterion("float_button_image =", value, "floatButtonImage");
            return (Criteria) this;
        }

        public Criteria andFloatButtonImageNotEqualTo(String value) {
            addCriterion("float_button_image <>", value, "floatButtonImage");
            return (Criteria) this;
        }

        public Criteria andFloatButtonImageGreaterThan(String value) {
            addCriterion("float_button_image >", value, "floatButtonImage");
            return (Criteria) this;
        }

        public Criteria andFloatButtonImageGreaterThanOrEqualTo(String value) {
            addCriterion("float_button_image >=", value, "floatButtonImage");
            return (Criteria) this;
        }

        public Criteria andFloatButtonImageLessThan(String value) {
            addCriterion("float_button_image <", value, "floatButtonImage");
            return (Criteria) this;
        }

        public Criteria andFloatButtonImageLessThanOrEqualTo(String value) {
            addCriterion("float_button_image <=", value, "floatButtonImage");
            return (Criteria) this;
        }

        public Criteria andFloatButtonImageLike(String value) {
            addCriterion("float_button_image like", value, "floatButtonImage");
            return (Criteria) this;
        }

        public Criteria andFloatButtonImageNotLike(String value) {
            addCriterion("float_button_image not like", value, "floatButtonImage");
            return (Criteria) this;
        }

        public Criteria andFloatButtonImageIn(List<String> values) {
            addCriterion("float_button_image in", values, "floatButtonImage");
            return (Criteria) this;
        }

        public Criteria andFloatButtonImageNotIn(List<String> values) {
            addCriterion("float_button_image not in", values, "floatButtonImage");
            return (Criteria) this;
        }

        public Criteria andFloatButtonImageBetween(String value1, String value2) {
            addCriterion("float_button_image between", value1, value2, "floatButtonImage");
            return (Criteria) this;
        }

        public Criteria andFloatButtonImageNotBetween(String value1, String value2) {
            addCriterion("float_button_image not between", value1, value2, "floatButtonImage");
            return (Criteria) this;
        }

        public Criteria andFloatButtonImageH5IsNull() {
            addCriterion("float_button_image_h5 is null");
            return (Criteria) this;
        }

        public Criteria andFloatButtonImageH5IsNotNull() {
            addCriterion("float_button_image_h5 is not null");
            return (Criteria) this;
        }

        public Criteria andFloatButtonImageH5EqualTo(String value) {
            addCriterion("float_button_image_h5 =", value, "floatButtonImageH5");
            return (Criteria) this;
        }

        public Criteria andFloatButtonImageH5NotEqualTo(String value) {
            addCriterion("float_button_image_h5 <>", value, "floatButtonImageH5");
            return (Criteria) this;
        }

        public Criteria andFloatButtonImageH5GreaterThan(String value) {
            addCriterion("float_button_image_h5 >", value, "floatButtonImageH5");
            return (Criteria) this;
        }

        public Criteria andFloatButtonImageH5GreaterThanOrEqualTo(String value) {
            addCriterion("float_button_image_h5 >=", value, "floatButtonImageH5");
            return (Criteria) this;
        }

        public Criteria andFloatButtonImageH5LessThan(String value) {
            addCriterion("float_button_image_h5 <", value, "floatButtonImageH5");
            return (Criteria) this;
        }

        public Criteria andFloatButtonImageH5LessThanOrEqualTo(String value) {
            addCriterion("float_button_image_h5 <=", value, "floatButtonImageH5");
            return (Criteria) this;
        }

        public Criteria andFloatButtonImageH5Like(String value) {
            addCriterion("float_button_image_h5 like", value, "floatButtonImageH5");
            return (Criteria) this;
        }

        public Criteria andFloatButtonImageH5NotLike(String value) {
            addCriterion("float_button_image_h5 not like", value, "floatButtonImageH5");
            return (Criteria) this;
        }

        public Criteria andFloatButtonImageH5In(List<String> values) {
            addCriterion("float_button_image_h5 in", values, "floatButtonImageH5");
            return (Criteria) this;
        }

        public Criteria andFloatButtonImageH5NotIn(List<String> values) {
            addCriterion("float_button_image_h5 not in", values, "floatButtonImageH5");
            return (Criteria) this;
        }

        public Criteria andFloatButtonImageH5Between(String value1, String value2) {
            addCriterion("float_button_image_h5 between", value1, value2, "floatButtonImageH5");
            return (Criteria) this;
        }

        public Criteria andFloatButtonImageH5NotBetween(String value1, String value2) {
            addCriterion("float_button_image_h5 not between", value1, value2, "floatButtonImageH5");
            return (Criteria) this;
        }

        public Criteria andBackgroundImageIsNull() {
            addCriterion("background_image is null");
            return (Criteria) this;
        }

        public Criteria andBackgroundImageIsNotNull() {
            addCriterion("background_image is not null");
            return (Criteria) this;
        }

        public Criteria andBackgroundImageEqualTo(String value) {
            addCriterion("background_image =", value, "backgroundImage");
            return (Criteria) this;
        }

        public Criteria andBackgroundImageNotEqualTo(String value) {
            addCriterion("background_image <>", value, "backgroundImage");
            return (Criteria) this;
        }

        public Criteria andBackgroundImageGreaterThan(String value) {
            addCriterion("background_image >", value, "backgroundImage");
            return (Criteria) this;
        }

        public Criteria andBackgroundImageGreaterThanOrEqualTo(String value) {
            addCriterion("background_image >=", value, "backgroundImage");
            return (Criteria) this;
        }

        public Criteria andBackgroundImageLessThan(String value) {
            addCriterion("background_image <", value, "backgroundImage");
            return (Criteria) this;
        }

        public Criteria andBackgroundImageLessThanOrEqualTo(String value) {
            addCriterion("background_image <=", value, "backgroundImage");
            return (Criteria) this;
        }

        public Criteria andBackgroundImageLike(String value) {
            addCriterion("background_image like", value, "backgroundImage");
            return (Criteria) this;
        }

        public Criteria andBackgroundImageNotLike(String value) {
            addCriterion("background_image not like", value, "backgroundImage");
            return (Criteria) this;
        }

        public Criteria andBackgroundImageIn(List<String> values) {
            addCriterion("background_image in", values, "backgroundImage");
            return (Criteria) this;
        }

        public Criteria andBackgroundImageNotIn(List<String> values) {
            addCriterion("background_image not in", values, "backgroundImage");
            return (Criteria) this;
        }

        public Criteria andBackgroundImageBetween(String value1, String value2) {
            addCriterion("background_image between", value1, value2, "backgroundImage");
            return (Criteria) this;
        }

        public Criteria andBackgroundImageNotBetween(String value1, String value2) {
            addCriterion("background_image not between", value1, value2, "backgroundImage");
            return (Criteria) this;
        }

        public Criteria andBackgroundImageH5IsNull() {
            addCriterion("background_image_h5 is null");
            return (Criteria) this;
        }

        public Criteria andBackgroundImageH5IsNotNull() {
            addCriterion("background_image_h5 is not null");
            return (Criteria) this;
        }

        public Criteria andBackgroundImageH5EqualTo(String value) {
            addCriterion("background_image_h5 =", value, "backgroundImageH5");
            return (Criteria) this;
        }

        public Criteria andBackgroundImageH5NotEqualTo(String value) {
            addCriterion("background_image_h5 <>", value, "backgroundImageH5");
            return (Criteria) this;
        }

        public Criteria andBackgroundImageH5GreaterThan(String value) {
            addCriterion("background_image_h5 >", value, "backgroundImageH5");
            return (Criteria) this;
        }

        public Criteria andBackgroundImageH5GreaterThanOrEqualTo(String value) {
            addCriterion("background_image_h5 >=", value, "backgroundImageH5");
            return (Criteria) this;
        }

        public Criteria andBackgroundImageH5LessThan(String value) {
            addCriterion("background_image_h5 <", value, "backgroundImageH5");
            return (Criteria) this;
        }

        public Criteria andBackgroundImageH5LessThanOrEqualTo(String value) {
            addCriterion("background_image_h5 <=", value, "backgroundImageH5");
            return (Criteria) this;
        }

        public Criteria andBackgroundImageH5Like(String value) {
            addCriterion("background_image_h5 like", value, "backgroundImageH5");
            return (Criteria) this;
        }

        public Criteria andBackgroundImageH5NotLike(String value) {
            addCriterion("background_image_h5 not like", value, "backgroundImageH5");
            return (Criteria) this;
        }

        public Criteria andBackgroundImageH5In(List<String> values) {
            addCriterion("background_image_h5 in", values, "backgroundImageH5");
            return (Criteria) this;
        }

        public Criteria andBackgroundImageH5NotIn(List<String> values) {
            addCriterion("background_image_h5 not in", values, "backgroundImageH5");
            return (Criteria) this;
        }

        public Criteria andBackgroundImageH5Between(String value1, String value2) {
            addCriterion("background_image_h5 between", value1, value2, "backgroundImageH5");
            return (Criteria) this;
        }

        public Criteria andBackgroundImageH5NotBetween(String value1, String value2) {
            addCriterion("background_image_h5 not between", value1, value2, "backgroundImageH5");
            return (Criteria) this;
        }

        public Criteria andHeadImageIsNull() {
            addCriterion("head_image is null");
            return (Criteria) this;
        }

        public Criteria andHeadImageIsNotNull() {
            addCriterion("head_image is not null");
            return (Criteria) this;
        }

        public Criteria andHeadImageEqualTo(String value) {
            addCriterion("head_image =", value, "headImage");
            return (Criteria) this;
        }

        public Criteria andHeadImageNotEqualTo(String value) {
            addCriterion("head_image <>", value, "headImage");
            return (Criteria) this;
        }

        public Criteria andHeadImageGreaterThan(String value) {
            addCriterion("head_image >", value, "headImage");
            return (Criteria) this;
        }

        public Criteria andHeadImageGreaterThanOrEqualTo(String value) {
            addCriterion("head_image >=", value, "headImage");
            return (Criteria) this;
        }

        public Criteria andHeadImageLessThan(String value) {
            addCriterion("head_image <", value, "headImage");
            return (Criteria) this;
        }

        public Criteria andHeadImageLessThanOrEqualTo(String value) {
            addCriterion("head_image <=", value, "headImage");
            return (Criteria) this;
        }

        public Criteria andHeadImageLike(String value) {
            addCriterion("head_image like", value, "headImage");
            return (Criteria) this;
        }

        public Criteria andHeadImageNotLike(String value) {
            addCriterion("head_image not like", value, "headImage");
            return (Criteria) this;
        }

        public Criteria andHeadImageIn(List<String> values) {
            addCriterion("head_image in", values, "headImage");
            return (Criteria) this;
        }

        public Criteria andHeadImageNotIn(List<String> values) {
            addCriterion("head_image not in", values, "headImage");
            return (Criteria) this;
        }

        public Criteria andHeadImageBetween(String value1, String value2) {
            addCriterion("head_image between", value1, value2, "headImage");
            return (Criteria) this;
        }

        public Criteria andHeadImageNotBetween(String value1, String value2) {
            addCriterion("head_image not between", value1, value2, "headImage");
            return (Criteria) this;
        }

        public Criteria andHeadImageH5IsNull() {
            addCriterion("head_image_h5 is null");
            return (Criteria) this;
        }

        public Criteria andHeadImageH5IsNotNull() {
            addCriterion("head_image_h5 is not null");
            return (Criteria) this;
        }

        public Criteria andHeadImageH5EqualTo(String value) {
            addCriterion("head_image_h5 =", value, "headImageH5");
            return (Criteria) this;
        }

        public Criteria andHeadImageH5NotEqualTo(String value) {
            addCriterion("head_image_h5 <>", value, "headImageH5");
            return (Criteria) this;
        }

        public Criteria andHeadImageH5GreaterThan(String value) {
            addCriterion("head_image_h5 >", value, "headImageH5");
            return (Criteria) this;
        }

        public Criteria andHeadImageH5GreaterThanOrEqualTo(String value) {
            addCriterion("head_image_h5 >=", value, "headImageH5");
            return (Criteria) this;
        }

        public Criteria andHeadImageH5LessThan(String value) {
            addCriterion("head_image_h5 <", value, "headImageH5");
            return (Criteria) this;
        }

        public Criteria andHeadImageH5LessThanOrEqualTo(String value) {
            addCriterion("head_image_h5 <=", value, "headImageH5");
            return (Criteria) this;
        }

        public Criteria andHeadImageH5Like(String value) {
            addCriterion("head_image_h5 like", value, "headImageH5");
            return (Criteria) this;
        }

        public Criteria andHeadImageH5NotLike(String value) {
            addCriterion("head_image_h5 not like", value, "headImageH5");
            return (Criteria) this;
        }

        public Criteria andHeadImageH5In(List<String> values) {
            addCriterion("head_image_h5 in", values, "headImageH5");
            return (Criteria) this;
        }

        public Criteria andHeadImageH5NotIn(List<String> values) {
            addCriterion("head_image_h5 not in", values, "headImageH5");
            return (Criteria) this;
        }

        public Criteria andHeadImageH5Between(String value1, String value2) {
            addCriterion("head_image_h5 between", value1, value2, "headImageH5");
            return (Criteria) this;
        }

        public Criteria andHeadImageH5NotBetween(String value1, String value2) {
            addCriterion("head_image_h5 not between", value1, value2, "headImageH5");
            return (Criteria) this;
        }

        public Criteria andRuleImageIsNull() {
            addCriterion("rule_image is null");
            return (Criteria) this;
        }

        public Criteria andRuleImageIsNotNull() {
            addCriterion("rule_image is not null");
            return (Criteria) this;
        }

        public Criteria andRuleImageEqualTo(String value) {
            addCriterion("rule_image =", value, "ruleImage");
            return (Criteria) this;
        }

        public Criteria andRuleImageNotEqualTo(String value) {
            addCriterion("rule_image <>", value, "ruleImage");
            return (Criteria) this;
        }

        public Criteria andRuleImageGreaterThan(String value) {
            addCriterion("rule_image >", value, "ruleImage");
            return (Criteria) this;
        }

        public Criteria andRuleImageGreaterThanOrEqualTo(String value) {
            addCriterion("rule_image >=", value, "ruleImage");
            return (Criteria) this;
        }

        public Criteria andRuleImageLessThan(String value) {
            addCriterion("rule_image <", value, "ruleImage");
            return (Criteria) this;
        }

        public Criteria andRuleImageLessThanOrEqualTo(String value) {
            addCriterion("rule_image <=", value, "ruleImage");
            return (Criteria) this;
        }

        public Criteria andRuleImageLike(String value) {
            addCriterion("rule_image like", value, "ruleImage");
            return (Criteria) this;
        }

        public Criteria andRuleImageNotLike(String value) {
            addCriterion("rule_image not like", value, "ruleImage");
            return (Criteria) this;
        }

        public Criteria andRuleImageIn(List<String> values) {
            addCriterion("rule_image in", values, "ruleImage");
            return (Criteria) this;
        }

        public Criteria andRuleImageNotIn(List<String> values) {
            addCriterion("rule_image not in", values, "ruleImage");
            return (Criteria) this;
        }

        public Criteria andRuleImageBetween(String value1, String value2) {
            addCriterion("rule_image between", value1, value2, "ruleImage");
            return (Criteria) this;
        }

        public Criteria andRuleImageNotBetween(String value1, String value2) {
            addCriterion("rule_image not between", value1, value2, "ruleImage");
            return (Criteria) this;
        }

        public Criteria andRuleImageH5IsNull() {
            addCriterion("rule_image_h5 is null");
            return (Criteria) this;
        }

        public Criteria andRuleImageH5IsNotNull() {
            addCriterion("rule_image_h5 is not null");
            return (Criteria) this;
        }

        public Criteria andRuleImageH5EqualTo(String value) {
            addCriterion("rule_image_h5 =", value, "ruleImageH5");
            return (Criteria) this;
        }

        public Criteria andRuleImageH5NotEqualTo(String value) {
            addCriterion("rule_image_h5 <>", value, "ruleImageH5");
            return (Criteria) this;
        }

        public Criteria andRuleImageH5GreaterThan(String value) {
            addCriterion("rule_image_h5 >", value, "ruleImageH5");
            return (Criteria) this;
        }

        public Criteria andRuleImageH5GreaterThanOrEqualTo(String value) {
            addCriterion("rule_image_h5 >=", value, "ruleImageH5");
            return (Criteria) this;
        }

        public Criteria andRuleImageH5LessThan(String value) {
            addCriterion("rule_image_h5 <", value, "ruleImageH5");
            return (Criteria) this;
        }

        public Criteria andRuleImageH5LessThanOrEqualTo(String value) {
            addCriterion("rule_image_h5 <=", value, "ruleImageH5");
            return (Criteria) this;
        }

        public Criteria andRuleImageH5Like(String value) {
            addCriterion("rule_image_h5 like", value, "ruleImageH5");
            return (Criteria) this;
        }

        public Criteria andRuleImageH5NotLike(String value) {
            addCriterion("rule_image_h5 not like", value, "ruleImageH5");
            return (Criteria) this;
        }

        public Criteria andRuleImageH5In(List<String> values) {
            addCriterion("rule_image_h5 in", values, "ruleImageH5");
            return (Criteria) this;
        }

        public Criteria andRuleImageH5NotIn(List<String> values) {
            addCriterion("rule_image_h5 not in", values, "ruleImageH5");
            return (Criteria) this;
        }

        public Criteria andRuleImageH5Between(String value1, String value2) {
            addCriterion("rule_image_h5 between", value1, value2, "ruleImageH5");
            return (Criteria) this;
        }

        public Criteria andRuleImageH5NotBetween(String value1, String value2) {
            addCriterion("rule_image_h5 not between", value1, value2, "ruleImageH5");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("`status` is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("`status` is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("`status` =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("`status` <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("`status` >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("`status` >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("`status` <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("`status` <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("`status` in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("`status` not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("`status` between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("`status` not between", value1, value2, "status");
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

        public Criteria andUpdatedByIsNull() {
            addCriterion("updated_by is null");
            return (Criteria) this;
        }

        public Criteria andUpdatedByIsNotNull() {
            addCriterion("updated_by is not null");
            return (Criteria) this;
        }

        public Criteria andUpdatedByEqualTo(String value) {
            addCriterion("updated_by =", value, "updatedBy");
            return (Criteria) this;
        }

        public Criteria andUpdatedByNotEqualTo(String value) {
            addCriterion("updated_by <>", value, "updatedBy");
            return (Criteria) this;
        }

        public Criteria andUpdatedByGreaterThan(String value) {
            addCriterion("updated_by >", value, "updatedBy");
            return (Criteria) this;
        }

        public Criteria andUpdatedByGreaterThanOrEqualTo(String value) {
            addCriterion("updated_by >=", value, "updatedBy");
            return (Criteria) this;
        }

        public Criteria andUpdatedByLessThan(String value) {
            addCriterion("updated_by <", value, "updatedBy");
            return (Criteria) this;
        }

        public Criteria andUpdatedByLessThanOrEqualTo(String value) {
            addCriterion("updated_by <=", value, "updatedBy");
            return (Criteria) this;
        }

        public Criteria andUpdatedByLike(String value) {
            addCriterion("updated_by like", value, "updatedBy");
            return (Criteria) this;
        }

        public Criteria andUpdatedByNotLike(String value) {
            addCriterion("updated_by not like", value, "updatedBy");
            return (Criteria) this;
        }

        public Criteria andUpdatedByIn(List<String> values) {
            addCriterion("updated_by in", values, "updatedBy");
            return (Criteria) this;
        }

        public Criteria andUpdatedByNotIn(List<String> values) {
            addCriterion("updated_by not in", values, "updatedBy");
            return (Criteria) this;
        }

        public Criteria andUpdatedByBetween(String value1, String value2) {
            addCriterion("updated_by between", value1, value2, "updatedBy");
            return (Criteria) this;
        }

        public Criteria andUpdatedByNotBetween(String value1, String value2) {
            addCriterion("updated_by not between", value1, value2, "updatedBy");
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

        public Criteria andDeletedByIsNull() {
            addCriterion("deleted_by is null");
            return (Criteria) this;
        }

        public Criteria andDeletedByIsNotNull() {
            addCriterion("deleted_by is not null");
            return (Criteria) this;
        }

        public Criteria andDeletedByEqualTo(String value) {
            addCriterion("deleted_by =", value, "deletedBy");
            return (Criteria) this;
        }

        public Criteria andDeletedByNotEqualTo(String value) {
            addCriterion("deleted_by <>", value, "deletedBy");
            return (Criteria) this;
        }

        public Criteria andDeletedByGreaterThan(String value) {
            addCriterion("deleted_by >", value, "deletedBy");
            return (Criteria) this;
        }

        public Criteria andDeletedByGreaterThanOrEqualTo(String value) {
            addCriterion("deleted_by >=", value, "deletedBy");
            return (Criteria) this;
        }

        public Criteria andDeletedByLessThan(String value) {
            addCriterion("deleted_by <", value, "deletedBy");
            return (Criteria) this;
        }

        public Criteria andDeletedByLessThanOrEqualTo(String value) {
            addCriterion("deleted_by <=", value, "deletedBy");
            return (Criteria) this;
        }

        public Criteria andDeletedByLike(String value) {
            addCriterion("deleted_by like", value, "deletedBy");
            return (Criteria) this;
        }

        public Criteria andDeletedByNotLike(String value) {
            addCriterion("deleted_by not like", value, "deletedBy");
            return (Criteria) this;
        }

        public Criteria andDeletedByIn(List<String> values) {
            addCriterion("deleted_by in", values, "deletedBy");
            return (Criteria) this;
        }

        public Criteria andDeletedByNotIn(List<String> values) {
            addCriterion("deleted_by not in", values, "deletedBy");
            return (Criteria) this;
        }

        public Criteria andDeletedByBetween(String value1, String value2) {
            addCriterion("deleted_by between", value1, value2, "deletedBy");
            return (Criteria) this;
        }

        public Criteria andDeletedByNotBetween(String value1, String value2) {
            addCriterion("deleted_by not between", value1, value2, "deletedBy");
            return (Criteria) this;
        }

        public Criteria andDeletedAtIsNull() {
            addCriterion("deleted_at is null");
            return (Criteria) this;
        }

        public Criteria andDeletedAtIsNotNull() {
            addCriterion("deleted_at is not null");
            return (Criteria) this;
        }

        public Criteria andDeletedAtEqualTo(Date value) {
            addCriterion("deleted_at =", value, "deletedAt");
            return (Criteria) this;
        }

        public Criteria andDeletedAtNotEqualTo(Date value) {
            addCriterion("deleted_at <>", value, "deletedAt");
            return (Criteria) this;
        }

        public Criteria andDeletedAtGreaterThan(Date value) {
            addCriterion("deleted_at >", value, "deletedAt");
            return (Criteria) this;
        }

        public Criteria andDeletedAtGreaterThanOrEqualTo(Date value) {
            addCriterion("deleted_at >=", value, "deletedAt");
            return (Criteria) this;
        }

        public Criteria andDeletedAtLessThan(Date value) {
            addCriterion("deleted_at <", value, "deletedAt");
            return (Criteria) this;
        }

        public Criteria andDeletedAtLessThanOrEqualTo(Date value) {
            addCriterion("deleted_at <=", value, "deletedAt");
            return (Criteria) this;
        }

        public Criteria andDeletedAtIn(List<Date> values) {
            addCriterion("deleted_at in", values, "deletedAt");
            return (Criteria) this;
        }

        public Criteria andDeletedAtNotIn(List<Date> values) {
            addCriterion("deleted_at not in", values, "deletedAt");
            return (Criteria) this;
        }

        public Criteria andDeletedAtBetween(Date value1, Date value2) {
            addCriterion("deleted_at between", value1, value2, "deletedAt");
            return (Criteria) this;
        }

        public Criteria andDeletedAtNotBetween(Date value1, Date value2) {
            addCriterion("deleted_at not between", value1, value2, "deletedAt");
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