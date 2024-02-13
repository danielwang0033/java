package com.coin.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
public class ThreadsExample {
    @Setter
    protected String orderByClause;

    @Setter
    protected boolean distinct;

    protected final List<Criteria> oredCriteria;

    public ThreadsExample() {
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

        public Criteria andForumIdIsNull() {
            addCriterion("forum_id is null");
            return (Criteria) this;
        }

        public Criteria andForumIdIsNotNull() {
            addCriterion("forum_id is not null");
            return (Criteria) this;
        }

        public Criteria andForumIdEqualTo(Long value) {
            addCriterion("forum_id =", value, "forumId");
            return (Criteria) this;
        }

        public Criteria andForumIdNotEqualTo(Long value) {
            addCriterion("forum_id <>", value, "forumId");
            return (Criteria) this;
        }

        public Criteria andForumIdGreaterThan(Long value) {
            addCriterion("forum_id >", value, "forumId");
            return (Criteria) this;
        }

        public Criteria andForumIdGreaterThanOrEqualTo(Long value) {
            addCriterion("forum_id >=", value, "forumId");
            return (Criteria) this;
        }

        public Criteria andForumIdLessThan(Long value) {
            addCriterion("forum_id <", value, "forumId");
            return (Criteria) this;
        }

        public Criteria andForumIdLessThanOrEqualTo(Long value) {
            addCriterion("forum_id <=", value, "forumId");
            return (Criteria) this;
        }

        public Criteria andForumIdIn(List<Long> values) {
            addCriterion("forum_id in", values, "forumId");
            return (Criteria) this;
        }

        public Criteria andForumIdNotIn(List<Long> values) {
            addCriterion("forum_id not in", values, "forumId");
            return (Criteria) this;
        }

        public Criteria andForumIdBetween(Long value1, Long value2) {
            addCriterion("forum_id between", value1, value2, "forumId");
            return (Criteria) this;
        }

        public Criteria andForumIdNotBetween(Long value1, Long value2) {
            addCriterion("forum_id not between", value1, value2, "forumId");
            return (Criteria) this;
        }

        public Criteria andTopicIdIsNull() {
            addCriterion("topic_id is null");
            return (Criteria) this;
        }

        public Criteria andTopicIdIsNotNull() {
            addCriterion("topic_id is not null");
            return (Criteria) this;
        }

        public Criteria andTopicIdEqualTo(Long value) {
            addCriterion("topic_id =", value, "topicId");
            return (Criteria) this;
        }

        public Criteria andTopicIdNotEqualTo(Long value) {
            addCriterion("topic_id <>", value, "topicId");
            return (Criteria) this;
        }

        public Criteria andTopicIdGreaterThan(Long value) {
            addCriterion("topic_id >", value, "topicId");
            return (Criteria) this;
        }

        public Criteria andTopicIdGreaterThanOrEqualTo(Long value) {
            addCriterion("topic_id >=", value, "topicId");
            return (Criteria) this;
        }

        public Criteria andTopicIdLessThan(Long value) {
            addCriterion("topic_id <", value, "topicId");
            return (Criteria) this;
        }

        public Criteria andTopicIdLessThanOrEqualTo(Long value) {
            addCriterion("topic_id <=", value, "topicId");
            return (Criteria) this;
        }

        public Criteria andTopicIdIn(List<Long> values) {
            addCriterion("topic_id in", values, "topicId");
            return (Criteria) this;
        }

        public Criteria andTopicIdNotIn(List<Long> values) {
            addCriterion("topic_id not in", values, "topicId");
            return (Criteria) this;
        }

        public Criteria andTopicIdBetween(Long value1, Long value2) {
            addCriterion("topic_id between", value1, value2, "topicId");
            return (Criteria) this;
        }

        public Criteria andTopicIdNotBetween(Long value1, Long value2) {
            addCriterion("topic_id not between", value1, value2, "topicId");
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

        public Criteria andSubjectIsNull() {
            addCriterion("subject is null");
            return (Criteria) this;
        }

        public Criteria andSubjectIsNotNull() {
            addCriterion("subject is not null");
            return (Criteria) this;
        }

        public Criteria andSubjectEqualTo(String value) {
            addCriterion("subject =", value, "subject");
            return (Criteria) this;
        }

        public Criteria andSubjectNotEqualTo(String value) {
            addCriterion("subject <>", value, "subject");
            return (Criteria) this;
        }

        public Criteria andSubjectGreaterThan(String value) {
            addCriterion("subject >", value, "subject");
            return (Criteria) this;
        }

        public Criteria andSubjectGreaterThanOrEqualTo(String value) {
            addCriterion("subject >=", value, "subject");
            return (Criteria) this;
        }

        public Criteria andSubjectLessThan(String value) {
            addCriterion("subject <", value, "subject");
            return (Criteria) this;
        }

        public Criteria andSubjectLessThanOrEqualTo(String value) {
            addCriterion("subject <=", value, "subject");
            return (Criteria) this;
        }

        public Criteria andSubjectLike(String value) {
            addCriterion("subject like", value, "subject");
            return (Criteria) this;
        }

        public Criteria andSubjectNotLike(String value) {
            addCriterion("subject not like", value, "subject");
            return (Criteria) this;
        }

        public Criteria andSubjectIn(List<String> values) {
            addCriterion("subject in", values, "subject");
            return (Criteria) this;
        }

        public Criteria andSubjectNotIn(List<String> values) {
            addCriterion("subject not in", values, "subject");
            return (Criteria) this;
        }

        public Criteria andSubjectBetween(String value1, String value2) {
            addCriterion("subject between", value1, value2, "subject");
            return (Criteria) this;
        }

        public Criteria andSubjectNotBetween(String value1, String value2) {
            addCriterion("subject not between", value1, value2, "subject");
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

        public Criteria andTagsIsNull() {
            addCriterion("tags is null");
            return (Criteria) this;
        }

        public Criteria andTagsIsNotNull() {
            addCriterion("tags is not null");
            return (Criteria) this;
        }

        public Criteria andTagsEqualTo(String value) {
            addCriterion("tags =", value, "tags");
            return (Criteria) this;
        }

        public Criteria andTagsNotEqualTo(String value) {
            addCriterion("tags <>", value, "tags");
            return (Criteria) this;
        }

        public Criteria andTagsGreaterThan(String value) {
            addCriterion("tags >", value, "tags");
            return (Criteria) this;
        }

        public Criteria andTagsGreaterThanOrEqualTo(String value) {
            addCriterion("tags >=", value, "tags");
            return (Criteria) this;
        }

        public Criteria andTagsLessThan(String value) {
            addCriterion("tags <", value, "tags");
            return (Criteria) this;
        }

        public Criteria andTagsLessThanOrEqualTo(String value) {
            addCriterion("tags <=", value, "tags");
            return (Criteria) this;
        }

        public Criteria andTagsLike(String value) {
            addCriterion("tags like", value, "tags");
            return (Criteria) this;
        }

        public Criteria andTagsNotLike(String value) {
            addCriterion("tags not like", value, "tags");
            return (Criteria) this;
        }

        public Criteria andTagsIn(List<String> values) {
            addCriterion("tags in", values, "tags");
            return (Criteria) this;
        }

        public Criteria andTagsNotIn(List<String> values) {
            addCriterion("tags not in", values, "tags");
            return (Criteria) this;
        }

        public Criteria andTagsBetween(String value1, String value2) {
            addCriterion("tags between", value1, value2, "tags");
            return (Criteria) this;
        }

        public Criteria andTagsNotBetween(String value1, String value2) {
            addCriterion("tags not between", value1, value2, "tags");
            return (Criteria) this;
        }

        public Criteria andPicsIsNull() {
            addCriterion("pics is null");
            return (Criteria) this;
        }

        public Criteria andPicsIsNotNull() {
            addCriterion("pics is not null");
            return (Criteria) this;
        }

        public Criteria andPicsEqualTo(String value) {
            addCriterion("pics =", value, "pics");
            return (Criteria) this;
        }

        public Criteria andPicsNotEqualTo(String value) {
            addCriterion("pics <>", value, "pics");
            return (Criteria) this;
        }

        public Criteria andPicsGreaterThan(String value) {
            addCriterion("pics >", value, "pics");
            return (Criteria) this;
        }

        public Criteria andPicsGreaterThanOrEqualTo(String value) {
            addCriterion("pics >=", value, "pics");
            return (Criteria) this;
        }

        public Criteria andPicsLessThan(String value) {
            addCriterion("pics <", value, "pics");
            return (Criteria) this;
        }

        public Criteria andPicsLessThanOrEqualTo(String value) {
            addCriterion("pics <=", value, "pics");
            return (Criteria) this;
        }

        public Criteria andPicsLike(String value) {
            addCriterion("pics like", value, "pics");
            return (Criteria) this;
        }

        public Criteria andPicsNotLike(String value) {
            addCriterion("pics not like", value, "pics");
            return (Criteria) this;
        }

        public Criteria andPicsIn(List<String> values) {
            addCriterion("pics in", values, "pics");
            return (Criteria) this;
        }

        public Criteria andPicsNotIn(List<String> values) {
            addCriterion("pics not in", values, "pics");
            return (Criteria) this;
        }

        public Criteria andPicsBetween(String value1, String value2) {
            addCriterion("pics between", value1, value2, "pics");
            return (Criteria) this;
        }

        public Criteria andPicsNotBetween(String value1, String value2) {
            addCriterion("pics not between", value1, value2, "pics");
            return (Criteria) this;
        }

        public Criteria andNeedThumbIsNull() {
            addCriterion("need_thumb is null");
            return (Criteria) this;
        }

        public Criteria andNeedThumbIsNotNull() {
            addCriterion("need_thumb is not null");
            return (Criteria) this;
        }

        public Criteria andNeedThumbEqualTo(Integer value) {
            addCriterion("need_thumb =", value, "needThumb");
            return (Criteria) this;
        }

        public Criteria andNeedThumbNotEqualTo(Integer value) {
            addCriterion("need_thumb <>", value, "needThumb");
            return (Criteria) this;
        }

        public Criteria andNeedThumbGreaterThan(Integer value) {
            addCriterion("need_thumb >", value, "needThumb");
            return (Criteria) this;
        }

        public Criteria andNeedThumbGreaterThanOrEqualTo(Integer value) {
            addCriterion("need_thumb >=", value, "needThumb");
            return (Criteria) this;
        }

        public Criteria andNeedThumbLessThan(Integer value) {
            addCriterion("need_thumb <", value, "needThumb");
            return (Criteria) this;
        }

        public Criteria andNeedThumbLessThanOrEqualTo(Integer value) {
            addCriterion("need_thumb <=", value, "needThumb");
            return (Criteria) this;
        }

        public Criteria andNeedThumbIn(List<Integer> values) {
            addCriterion("need_thumb in", values, "needThumb");
            return (Criteria) this;
        }

        public Criteria andNeedThumbNotIn(List<Integer> values) {
            addCriterion("need_thumb not in", values, "needThumb");
            return (Criteria) this;
        }

        public Criteria andNeedThumbBetween(Integer value1, Integer value2) {
            addCriterion("need_thumb between", value1, value2, "needThumb");
            return (Criteria) this;
        }

        public Criteria andNeedThumbNotBetween(Integer value1, Integer value2) {
            addCriterion("need_thumb not between", value1, value2, "needThumb");
            return (Criteria) this;
        }

        public Criteria andThumbsIsNull() {
            addCriterion("thumbs is null");
            return (Criteria) this;
        }

        public Criteria andThumbsIsNotNull() {
            addCriterion("thumbs is not null");
            return (Criteria) this;
        }

        public Criteria andThumbsEqualTo(String value) {
            addCriterion("thumbs =", value, "thumbs");
            return (Criteria) this;
        }

        public Criteria andThumbsNotEqualTo(String value) {
            addCriterion("thumbs <>", value, "thumbs");
            return (Criteria) this;
        }

        public Criteria andThumbsGreaterThan(String value) {
            addCriterion("thumbs >", value, "thumbs");
            return (Criteria) this;
        }

        public Criteria andThumbsGreaterThanOrEqualTo(String value) {
            addCriterion("thumbs >=", value, "thumbs");
            return (Criteria) this;
        }

        public Criteria andThumbsLessThan(String value) {
            addCriterion("thumbs <", value, "thumbs");
            return (Criteria) this;
        }

        public Criteria andThumbsLessThanOrEqualTo(String value) {
            addCriterion("thumbs <=", value, "thumbs");
            return (Criteria) this;
        }

        public Criteria andThumbsLike(String value) {
            addCriterion("thumbs like", value, "thumbs");
            return (Criteria) this;
        }

        public Criteria andThumbsNotLike(String value) {
            addCriterion("thumbs not like", value, "thumbs");
            return (Criteria) this;
        }

        public Criteria andThumbsIn(List<String> values) {
            addCriterion("thumbs in", values, "thumbs");
            return (Criteria) this;
        }

        public Criteria andThumbsNotIn(List<String> values) {
            addCriterion("thumbs not in", values, "thumbs");
            return (Criteria) this;
        }

        public Criteria andThumbsBetween(String value1, String value2) {
            addCriterion("thumbs between", value1, value2, "thumbs");
            return (Criteria) this;
        }

        public Criteria andThumbsNotBetween(String value1, String value2) {
            addCriterion("thumbs not between", value1, value2, "thumbs");
            return (Criteria) this;
        }

        public Criteria andIsTopIsNull() {
            addCriterion("is_top is null");
            return (Criteria) this;
        }

        public Criteria andIsTopIsNotNull() {
            addCriterion("is_top is not null");
            return (Criteria) this;
        }

        public Criteria andIsTopEqualTo(Integer value) {
            addCriterion("is_top =", value, "isTop");
            return (Criteria) this;
        }

        public Criteria andIsTopNotEqualTo(Integer value) {
            addCriterion("is_top <>", value, "isTop");
            return (Criteria) this;
        }

        public Criteria andIsTopGreaterThan(Integer value) {
            addCriterion("is_top >", value, "isTop");
            return (Criteria) this;
        }

        public Criteria andIsTopGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_top >=", value, "isTop");
            return (Criteria) this;
        }

        public Criteria andIsTopLessThan(Integer value) {
            addCriterion("is_top <", value, "isTop");
            return (Criteria) this;
        }

        public Criteria andIsTopLessThanOrEqualTo(Integer value) {
            addCriterion("is_top <=", value, "isTop");
            return (Criteria) this;
        }

        public Criteria andIsTopIn(List<Integer> values) {
            addCriterion("is_top in", values, "isTop");
            return (Criteria) this;
        }

        public Criteria andIsTopNotIn(List<Integer> values) {
            addCriterion("is_top not in", values, "isTop");
            return (Criteria) this;
        }

        public Criteria andIsTopBetween(Integer value1, Integer value2) {
            addCriterion("is_top between", value1, value2, "isTop");
            return (Criteria) this;
        }

        public Criteria andIsTopNotBetween(Integer value1, Integer value2) {
            addCriterion("is_top not between", value1, value2, "isTop");
            return (Criteria) this;
        }

        public Criteria andReadCountIsNull() {
            addCriterion("read_count is null");
            return (Criteria) this;
        }

        public Criteria andReadCountIsNotNull() {
            addCriterion("read_count is not null");
            return (Criteria) this;
        }

        public Criteria andReadCountEqualTo(Integer value) {
            addCriterion("read_count =", value, "readCount");
            return (Criteria) this;
        }

        public Criteria andReadCountNotEqualTo(Integer value) {
            addCriterion("read_count <>", value, "readCount");
            return (Criteria) this;
        }

        public Criteria andReadCountGreaterThan(Integer value) {
            addCriterion("read_count >", value, "readCount");
            return (Criteria) this;
        }

        public Criteria andReadCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("read_count >=", value, "readCount");
            return (Criteria) this;
        }

        public Criteria andReadCountLessThan(Integer value) {
            addCriterion("read_count <", value, "readCount");
            return (Criteria) this;
        }

        public Criteria andReadCountLessThanOrEqualTo(Integer value) {
            addCriterion("read_count <=", value, "readCount");
            return (Criteria) this;
        }

        public Criteria andReadCountIn(List<Integer> values) {
            addCriterion("read_count in", values, "readCount");
            return (Criteria) this;
        }

        public Criteria andReadCountNotIn(List<Integer> values) {
            addCriterion("read_count not in", values, "readCount");
            return (Criteria) this;
        }

        public Criteria andReadCountBetween(Integer value1, Integer value2) {
            addCriterion("read_count between", value1, value2, "readCount");
            return (Criteria) this;
        }

        public Criteria andReadCountNotBetween(Integer value1, Integer value2) {
            addCriterion("read_count not between", value1, value2, "readCount");
            return (Criteria) this;
        }

        public Criteria andReplyCountIsNull() {
            addCriterion("reply_count is null");
            return (Criteria) this;
        }

        public Criteria andReplyCountIsNotNull() {
            addCriterion("reply_count is not null");
            return (Criteria) this;
        }

        public Criteria andReplyCountEqualTo(Integer value) {
            addCriterion("reply_count =", value, "replyCount");
            return (Criteria) this;
        }

        public Criteria andReplyCountNotEqualTo(Integer value) {
            addCriterion("reply_count <>", value, "replyCount");
            return (Criteria) this;
        }

        public Criteria andReplyCountGreaterThan(Integer value) {
            addCriterion("reply_count >", value, "replyCount");
            return (Criteria) this;
        }

        public Criteria andReplyCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("reply_count >=", value, "replyCount");
            return (Criteria) this;
        }

        public Criteria andReplyCountLessThan(Integer value) {
            addCriterion("reply_count <", value, "replyCount");
            return (Criteria) this;
        }

        public Criteria andReplyCountLessThanOrEqualTo(Integer value) {
            addCriterion("reply_count <=", value, "replyCount");
            return (Criteria) this;
        }

        public Criteria andReplyCountIn(List<Integer> values) {
            addCriterion("reply_count in", values, "replyCount");
            return (Criteria) this;
        }

        public Criteria andReplyCountNotIn(List<Integer> values) {
            addCriterion("reply_count not in", values, "replyCount");
            return (Criteria) this;
        }

        public Criteria andReplyCountBetween(Integer value1, Integer value2) {
            addCriterion("reply_count between", value1, value2, "replyCount");
            return (Criteria) this;
        }

        public Criteria andReplyCountNotBetween(Integer value1, Integer value2) {
            addCriterion("reply_count not between", value1, value2, "replyCount");
            return (Criteria) this;
        }

        public Criteria andRankScoreIsNull() {
            addCriterion("rank_score is null");
            return (Criteria) this;
        }

        public Criteria andRankScoreIsNotNull() {
            addCriterion("rank_score is not null");
            return (Criteria) this;
        }

        public Criteria andRankScoreEqualTo(Integer value) {
            addCriterion("rank_score =", value, "rankScore");
            return (Criteria) this;
        }

        public Criteria andRankScoreNotEqualTo(Integer value) {
            addCriterion("rank_score <>", value, "rankScore");
            return (Criteria) this;
        }

        public Criteria andRankScoreGreaterThan(Integer value) {
            addCriterion("rank_score >", value, "rankScore");
            return (Criteria) this;
        }

        public Criteria andRankScoreGreaterThanOrEqualTo(Integer value) {
            addCriterion("rank_score >=", value, "rankScore");
            return (Criteria) this;
        }

        public Criteria andRankScoreLessThan(Integer value) {
            addCriterion("rank_score <", value, "rankScore");
            return (Criteria) this;
        }

        public Criteria andRankScoreLessThanOrEqualTo(Integer value) {
            addCriterion("rank_score <=", value, "rankScore");
            return (Criteria) this;
        }

        public Criteria andRankScoreIn(List<Integer> values) {
            addCriterion("rank_score in", values, "rankScore");
            return (Criteria) this;
        }

        public Criteria andRankScoreNotIn(List<Integer> values) {
            addCriterion("rank_score not in", values, "rankScore");
            return (Criteria) this;
        }

        public Criteria andRankScoreBetween(Integer value1, Integer value2) {
            addCriterion("rank_score between", value1, value2, "rankScore");
            return (Criteria) this;
        }

        public Criteria andRankScoreNotBetween(Integer value1, Integer value2) {
            addCriterion("rank_score not between", value1, value2, "rankScore");
            return (Criteria) this;
        }

        public Criteria andLastModifyAtIsNull() {
            addCriterion("last_modify_at is null");
            return (Criteria) this;
        }

        public Criteria andLastModifyAtIsNotNull() {
            addCriterion("last_modify_at is not null");
            return (Criteria) this;
        }

        public Criteria andLastModifyAtEqualTo(Date value) {
            addCriterion("last_modify_at =", value, "lastModifyAt");
            return (Criteria) this;
        }

        public Criteria andLastModifyAtNotEqualTo(Date value) {
            addCriterion("last_modify_at <>", value, "lastModifyAt");
            return (Criteria) this;
        }

        public Criteria andLastModifyAtGreaterThan(Date value) {
            addCriterion("last_modify_at >", value, "lastModifyAt");
            return (Criteria) this;
        }

        public Criteria andLastModifyAtGreaterThanOrEqualTo(Date value) {
            addCriterion("last_modify_at >=", value, "lastModifyAt");
            return (Criteria) this;
        }

        public Criteria andLastModifyAtLessThan(Date value) {
            addCriterion("last_modify_at <", value, "lastModifyAt");
            return (Criteria) this;
        }

        public Criteria andLastModifyAtLessThanOrEqualTo(Date value) {
            addCriterion("last_modify_at <=", value, "lastModifyAt");
            return (Criteria) this;
        }

        public Criteria andLastModifyAtIn(List<Date> values) {
            addCriterion("last_modify_at in", values, "lastModifyAt");
            return (Criteria) this;
        }

        public Criteria andLastModifyAtNotIn(List<Date> values) {
            addCriterion("last_modify_at not in", values, "lastModifyAt");
            return (Criteria) this;
        }

        public Criteria andLastModifyAtBetween(Date value1, Date value2) {
            addCriterion("last_modify_at between", value1, value2, "lastModifyAt");
            return (Criteria) this;
        }

        public Criteria andLastModifyAtNotBetween(Date value1, Date value2) {
            addCriterion("last_modify_at not between", value1, value2, "lastModifyAt");
            return (Criteria) this;
        }

        public Criteria andLastModifyUserIdIsNull() {
            addCriterion("last_modify_user_id is null");
            return (Criteria) this;
        }

        public Criteria andLastModifyUserIdIsNotNull() {
            addCriterion("last_modify_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andLastModifyUserIdEqualTo(Integer value) {
            addCriterion("last_modify_user_id =", value, "lastModifyUserId");
            return (Criteria) this;
        }

        public Criteria andLastModifyUserIdNotEqualTo(Integer value) {
            addCriterion("last_modify_user_id <>", value, "lastModifyUserId");
            return (Criteria) this;
        }

        public Criteria andLastModifyUserIdGreaterThan(Integer value) {
            addCriterion("last_modify_user_id >", value, "lastModifyUserId");
            return (Criteria) this;
        }

        public Criteria andLastModifyUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("last_modify_user_id >=", value, "lastModifyUserId");
            return (Criteria) this;
        }

        public Criteria andLastModifyUserIdLessThan(Integer value) {
            addCriterion("last_modify_user_id <", value, "lastModifyUserId");
            return (Criteria) this;
        }

        public Criteria andLastModifyUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("last_modify_user_id <=", value, "lastModifyUserId");
            return (Criteria) this;
        }

        public Criteria andLastModifyUserIdIn(List<Integer> values) {
            addCriterion("last_modify_user_id in", values, "lastModifyUserId");
            return (Criteria) this;
        }

        public Criteria andLastModifyUserIdNotIn(List<Integer> values) {
            addCriterion("last_modify_user_id not in", values, "lastModifyUserId");
            return (Criteria) this;
        }

        public Criteria andLastModifyUserIdBetween(Integer value1, Integer value2) {
            addCriterion("last_modify_user_id between", value1, value2, "lastModifyUserId");
            return (Criteria) this;
        }

        public Criteria andLastModifyUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("last_modify_user_id not between", value1, value2, "lastModifyUserId");
            return (Criteria) this;
        }

        public Criteria andNotAllowedDeleteIsNull() {
            addCriterion("not_allowed_delete is null");
            return (Criteria) this;
        }

        public Criteria andNotAllowedDeleteIsNotNull() {
            addCriterion("not_allowed_delete is not null");
            return (Criteria) this;
        }

        public Criteria andNotAllowedDeleteEqualTo(Integer value) {
            addCriterion("not_allowed_delete =", value, "notAllowedDelete");
            return (Criteria) this;
        }

        public Criteria andNotAllowedDeleteNotEqualTo(Integer value) {
            addCriterion("not_allowed_delete <>", value, "notAllowedDelete");
            return (Criteria) this;
        }

        public Criteria andNotAllowedDeleteGreaterThan(Integer value) {
            addCriterion("not_allowed_delete >", value, "notAllowedDelete");
            return (Criteria) this;
        }

        public Criteria andNotAllowedDeleteGreaterThanOrEqualTo(Integer value) {
            addCriterion("not_allowed_delete >=", value, "notAllowedDelete");
            return (Criteria) this;
        }

        public Criteria andNotAllowedDeleteLessThan(Integer value) {
            addCriterion("not_allowed_delete <", value, "notAllowedDelete");
            return (Criteria) this;
        }

        public Criteria andNotAllowedDeleteLessThanOrEqualTo(Integer value) {
            addCriterion("not_allowed_delete <=", value, "notAllowedDelete");
            return (Criteria) this;
        }

        public Criteria andNotAllowedDeleteIn(List<Integer> values) {
            addCriterion("not_allowed_delete in", values, "notAllowedDelete");
            return (Criteria) this;
        }

        public Criteria andNotAllowedDeleteNotIn(List<Integer> values) {
            addCriterion("not_allowed_delete not in", values, "notAllowedDelete");
            return (Criteria) this;
        }

        public Criteria andNotAllowedDeleteBetween(Integer value1, Integer value2) {
            addCriterion("not_allowed_delete between", value1, value2, "notAllowedDelete");
            return (Criteria) this;
        }

        public Criteria andNotAllowedDeleteNotBetween(Integer value1, Integer value2) {
            addCriterion("not_allowed_delete not between", value1, value2, "notAllowedDelete");
            return (Criteria) this;
        }

        public Criteria andNotAllowedModifyIsNull() {
            addCriterion("not_allowed_modify is null");
            return (Criteria) this;
        }

        public Criteria andNotAllowedModifyIsNotNull() {
            addCriterion("not_allowed_modify is not null");
            return (Criteria) this;
        }

        public Criteria andNotAllowedModifyEqualTo(Integer value) {
            addCriterion("not_allowed_modify =", value, "notAllowedModify");
            return (Criteria) this;
        }

        public Criteria andNotAllowedModifyNotEqualTo(Integer value) {
            addCriterion("not_allowed_modify <>", value, "notAllowedModify");
            return (Criteria) this;
        }

        public Criteria andNotAllowedModifyGreaterThan(Integer value) {
            addCriterion("not_allowed_modify >", value, "notAllowedModify");
            return (Criteria) this;
        }

        public Criteria andNotAllowedModifyGreaterThanOrEqualTo(Integer value) {
            addCriterion("not_allowed_modify >=", value, "notAllowedModify");
            return (Criteria) this;
        }

        public Criteria andNotAllowedModifyLessThan(Integer value) {
            addCriterion("not_allowed_modify <", value, "notAllowedModify");
            return (Criteria) this;
        }

        public Criteria andNotAllowedModifyLessThanOrEqualTo(Integer value) {
            addCriterion("not_allowed_modify <=", value, "notAllowedModify");
            return (Criteria) this;
        }

        public Criteria andNotAllowedModifyIn(List<Integer> values) {
            addCriterion("not_allowed_modify in", values, "notAllowedModify");
            return (Criteria) this;
        }

        public Criteria andNotAllowedModifyNotIn(List<Integer> values) {
            addCriterion("not_allowed_modify not in", values, "notAllowedModify");
            return (Criteria) this;
        }

        public Criteria andNotAllowedModifyBetween(Integer value1, Integer value2) {
            addCriterion("not_allowed_modify between", value1, value2, "notAllowedModify");
            return (Criteria) this;
        }

        public Criteria andNotAllowedModifyNotBetween(Integer value1, Integer value2) {
            addCriterion("not_allowed_modify not between", value1, value2, "notAllowedModify");
            return (Criteria) this;
        }

        public Criteria andLastReplyTimeIsNull() {
            addCriterion("last_reply_time is null");
            return (Criteria) this;
        }

        public Criteria andLastReplyTimeIsNotNull() {
            addCriterion("last_reply_time is not null");
            return (Criteria) this;
        }

        public Criteria andLastReplyTimeEqualTo(Date value) {
            addCriterion("last_reply_time =", value, "lastReplyTime");
            return (Criteria) this;
        }

        public Criteria andLastReplyTimeNotEqualTo(Date value) {
            addCriterion("last_reply_time <>", value, "lastReplyTime");
            return (Criteria) this;
        }

        public Criteria andLastReplyTimeGreaterThan(Date value) {
            addCriterion("last_reply_time >", value, "lastReplyTime");
            return (Criteria) this;
        }

        public Criteria andLastReplyTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("last_reply_time >=", value, "lastReplyTime");
            return (Criteria) this;
        }

        public Criteria andLastReplyTimeLessThan(Date value) {
            addCriterion("last_reply_time <", value, "lastReplyTime");
            return (Criteria) this;
        }

        public Criteria andLastReplyTimeLessThanOrEqualTo(Date value) {
            addCriterion("last_reply_time <=", value, "lastReplyTime");
            return (Criteria) this;
        }

        public Criteria andLastReplyTimeIn(List<Date> values) {
            addCriterion("last_reply_time in", values, "lastReplyTime");
            return (Criteria) this;
        }

        public Criteria andLastReplyTimeNotIn(List<Date> values) {
            addCriterion("last_reply_time not in", values, "lastReplyTime");
            return (Criteria) this;
        }

        public Criteria andLastReplyTimeBetween(Date value1, Date value2) {
            addCriterion("last_reply_time between", value1, value2, "lastReplyTime");
            return (Criteria) this;
        }

        public Criteria andLastReplyTimeNotBetween(Date value1, Date value2) {
            addCriterion("last_reply_time not between", value1, value2, "lastReplyTime");
            return (Criteria) this;
        }

        public Criteria andLastReplyUserIdIsNull() {
            addCriterion("last_reply_user_id is null");
            return (Criteria) this;
        }

        public Criteria andLastReplyUserIdIsNotNull() {
            addCriterion("last_reply_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andLastReplyUserIdEqualTo(Integer value) {
            addCriterion("last_reply_user_id =", value, "lastReplyUserId");
            return (Criteria) this;
        }

        public Criteria andLastReplyUserIdNotEqualTo(Integer value) {
            addCriterion("last_reply_user_id <>", value, "lastReplyUserId");
            return (Criteria) this;
        }

        public Criteria andLastReplyUserIdGreaterThan(Integer value) {
            addCriterion("last_reply_user_id >", value, "lastReplyUserId");
            return (Criteria) this;
        }

        public Criteria andLastReplyUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("last_reply_user_id >=", value, "lastReplyUserId");
            return (Criteria) this;
        }

        public Criteria andLastReplyUserIdLessThan(Integer value) {
            addCriterion("last_reply_user_id <", value, "lastReplyUserId");
            return (Criteria) this;
        }

        public Criteria andLastReplyUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("last_reply_user_id <=", value, "lastReplyUserId");
            return (Criteria) this;
        }

        public Criteria andLastReplyUserIdIn(List<Integer> values) {
            addCriterion("last_reply_user_id in", values, "lastReplyUserId");
            return (Criteria) this;
        }

        public Criteria andLastReplyUserIdNotIn(List<Integer> values) {
            addCriterion("last_reply_user_id not in", values, "lastReplyUserId");
            return (Criteria) this;
        }

        public Criteria andLastReplyUserIdBetween(Integer value1, Integer value2) {
            addCriterion("last_reply_user_id between", value1, value2, "lastReplyUserId");
            return (Criteria) this;
        }

        public Criteria andLastReplyUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("last_reply_user_id not between", value1, value2, "lastReplyUserId");
            return (Criteria) this;
        }

        public Criteria andContentIsNull() {
            addCriterion("content is null");
            return (Criteria) this;
        }

        public Criteria andContentIsNotNull() {
            addCriterion("content is not null");
            return (Criteria) this;
        }

        public Criteria andContentEqualTo(String value) {
            addCriterion("content =", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotEqualTo(String value) {
            addCriterion("content <>", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentGreaterThan(String value) {
            addCriterion("content >", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentGreaterThanOrEqualTo(String value) {
            addCriterion("content >=", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLessThan(String value) {
            addCriterion("content <", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLessThanOrEqualTo(String value) {
            addCriterion("content <=", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLike(String value) {
            addCriterion("content like", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotLike(String value) {
            addCriterion("content not like", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentIn(List<String> values) {
            addCriterion("content in", values, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotIn(List<String> values) {
            addCriterion("content not in", values, "content");
            return (Criteria) this;
        }

        public Criteria andContentBetween(String value1, String value2) {
            addCriterion("content between", value1, value2, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotBetween(String value1, String value2) {
            addCriterion("content not between", value1, value2, "content");
            return (Criteria) this;
        }

        public Criteria andDescIsNull() {
            addCriterion("`desc` is null");
            return (Criteria) this;
        }

        public Criteria andDescIsNotNull() {
            addCriterion("`desc` is not null");
            return (Criteria) this;
        }

        public Criteria andDescEqualTo(String value) {
            addCriterion("`desc` =", value, "desc");
            return (Criteria) this;
        }

        public Criteria andDescNotEqualTo(String value) {
            addCriterion("`desc` <>", value, "desc");
            return (Criteria) this;
        }

        public Criteria andDescGreaterThan(String value) {
            addCriterion("`desc` >", value, "desc");
            return (Criteria) this;
        }

        public Criteria andDescGreaterThanOrEqualTo(String value) {
            addCriterion("`desc` >=", value, "desc");
            return (Criteria) this;
        }

        public Criteria andDescLessThan(String value) {
            addCriterion("`desc` <", value, "desc");
            return (Criteria) this;
        }

        public Criteria andDescLessThanOrEqualTo(String value) {
            addCriterion("`desc` <=", value, "desc");
            return (Criteria) this;
        }

        public Criteria andDescLike(String value) {
            addCriterion("`desc` like", value, "desc");
            return (Criteria) this;
        }

        public Criteria andDescNotLike(String value) {
            addCriterion("`desc` not like", value, "desc");
            return (Criteria) this;
        }

        public Criteria andDescIn(List<String> values) {
            addCriterion("`desc` in", values, "desc");
            return (Criteria) this;
        }

        public Criteria andDescNotIn(List<String> values) {
            addCriterion("`desc` not in", values, "desc");
            return (Criteria) this;
        }

        public Criteria andDescBetween(String value1, String value2) {
            addCriterion("`desc` between", value1, value2, "desc");
            return (Criteria) this;
        }

        public Criteria andDescNotBetween(String value1, String value2) {
            addCriterion("`desc` not between", value1, value2, "desc");
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

        public Criteria andIncrLikesIsNull() {
            addCriterion("incr_likes is null");
            return (Criteria) this;
        }

        public Criteria andIncrLikesIsNotNull() {
            addCriterion("incr_likes is not null");
            return (Criteria) this;
        }

        public Criteria andIncrLikesEqualTo(Integer value) {
            addCriterion("incr_likes =", value, "incrLikes");
            return (Criteria) this;
        }

        public Criteria andIncrLikesNotEqualTo(Integer value) {
            addCriterion("incr_likes <>", value, "incrLikes");
            return (Criteria) this;
        }

        public Criteria andIncrLikesGreaterThan(Integer value) {
            addCriterion("incr_likes >", value, "incrLikes");
            return (Criteria) this;
        }

        public Criteria andIncrLikesGreaterThanOrEqualTo(Integer value) {
            addCriterion("incr_likes >=", value, "incrLikes");
            return (Criteria) this;
        }

        public Criteria andIncrLikesLessThan(Integer value) {
            addCriterion("incr_likes <", value, "incrLikes");
            return (Criteria) this;
        }

        public Criteria andIncrLikesLessThanOrEqualTo(Integer value) {
            addCriterion("incr_likes <=", value, "incrLikes");
            return (Criteria) this;
        }

        public Criteria andIncrLikesIn(List<Integer> values) {
            addCriterion("incr_likes in", values, "incrLikes");
            return (Criteria) this;
        }

        public Criteria andIncrLikesNotIn(List<Integer> values) {
            addCriterion("incr_likes not in", values, "incrLikes");
            return (Criteria) this;
        }

        public Criteria andIncrLikesBetween(Integer value1, Integer value2) {
            addCriterion("incr_likes between", value1, value2, "incrLikes");
            return (Criteria) this;
        }

        public Criteria andIncrLikesNotBetween(Integer value1, Integer value2) {
            addCriterion("incr_likes not between", value1, value2, "incrLikes");
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

        public Criteria andTitleIsBoldIsNull() {
            addCriterion("title_is_bold is null");
            return (Criteria) this;
        }

        public Criteria andTitleIsBoldIsNotNull() {
            addCriterion("title_is_bold is not null");
            return (Criteria) this;
        }

        public Criteria andTitleIsBoldEqualTo(Integer value) {
            addCriterion("title_is_bold =", value, "titleIsBold");
            return (Criteria) this;
        }

        public Criteria andTitleIsBoldNotEqualTo(Integer value) {
            addCriterion("title_is_bold <>", value, "titleIsBold");
            return (Criteria) this;
        }

        public Criteria andTitleIsBoldGreaterThan(Integer value) {
            addCriterion("title_is_bold >", value, "titleIsBold");
            return (Criteria) this;
        }

        public Criteria andTitleIsBoldGreaterThanOrEqualTo(Integer value) {
            addCriterion("title_is_bold >=", value, "titleIsBold");
            return (Criteria) this;
        }

        public Criteria andTitleIsBoldLessThan(Integer value) {
            addCriterion("title_is_bold <", value, "titleIsBold");
            return (Criteria) this;
        }

        public Criteria andTitleIsBoldLessThanOrEqualTo(Integer value) {
            addCriterion("title_is_bold <=", value, "titleIsBold");
            return (Criteria) this;
        }

        public Criteria andTitleIsBoldIn(List<Integer> values) {
            addCriterion("title_is_bold in", values, "titleIsBold");
            return (Criteria) this;
        }

        public Criteria andTitleIsBoldNotIn(List<Integer> values) {
            addCriterion("title_is_bold not in", values, "titleIsBold");
            return (Criteria) this;
        }

        public Criteria andTitleIsBoldBetween(Integer value1, Integer value2) {
            addCriterion("title_is_bold between", value1, value2, "titleIsBold");
            return (Criteria) this;
        }

        public Criteria andTitleIsBoldNotBetween(Integer value1, Integer value2) {
            addCriterion("title_is_bold not between", value1, value2, "titleIsBold");
            return (Criteria) this;
        }

        public Criteria andTitleColorIsNull() {
            addCriterion("title_color is null");
            return (Criteria) this;
        }

        public Criteria andTitleColorIsNotNull() {
            addCriterion("title_color is not null");
            return (Criteria) this;
        }

        public Criteria andTitleColorEqualTo(String value) {
            addCriterion("title_color =", value, "titleColor");
            return (Criteria) this;
        }

        public Criteria andTitleColorNotEqualTo(String value) {
            addCriterion("title_color <>", value, "titleColor");
            return (Criteria) this;
        }

        public Criteria andTitleColorGreaterThan(String value) {
            addCriterion("title_color >", value, "titleColor");
            return (Criteria) this;
        }

        public Criteria andTitleColorGreaterThanOrEqualTo(String value) {
            addCriterion("title_color >=", value, "titleColor");
            return (Criteria) this;
        }

        public Criteria andTitleColorLessThan(String value) {
            addCriterion("title_color <", value, "titleColor");
            return (Criteria) this;
        }

        public Criteria andTitleColorLessThanOrEqualTo(String value) {
            addCriterion("title_color <=", value, "titleColor");
            return (Criteria) this;
        }

        public Criteria andTitleColorLike(String value) {
            addCriterion("title_color like", value, "titleColor");
            return (Criteria) this;
        }

        public Criteria andTitleColorNotLike(String value) {
            addCriterion("title_color not like", value, "titleColor");
            return (Criteria) this;
        }

        public Criteria andTitleColorIn(List<String> values) {
            addCriterion("title_color in", values, "titleColor");
            return (Criteria) this;
        }

        public Criteria andTitleColorNotIn(List<String> values) {
            addCriterion("title_color not in", values, "titleColor");
            return (Criteria) this;
        }

        public Criteria andTitleColorBetween(String value1, String value2) {
            addCriterion("title_color between", value1, value2, "titleColor");
            return (Criteria) this;
        }

        public Criteria andTitleColorNotBetween(String value1, String value2) {
            addCriterion("title_color not between", value1, value2, "titleColor");
            return (Criteria) this;
        }

        public Criteria andTitleIsItalicizedIsNull() {
            addCriterion("title_is_Italicized is null");
            return (Criteria) this;
        }

        public Criteria andTitleIsItalicizedIsNotNull() {
            addCriterion("title_is_Italicized is not null");
            return (Criteria) this;
        }

        public Criteria andTitleIsItalicizedEqualTo(Integer value) {
            addCriterion("title_is_Italicized =", value, "titleIsItalicized");
            return (Criteria) this;
        }

        public Criteria andTitleIsItalicizedNotEqualTo(Integer value) {
            addCriterion("title_is_Italicized <>", value, "titleIsItalicized");
            return (Criteria) this;
        }

        public Criteria andTitleIsItalicizedGreaterThan(Integer value) {
            addCriterion("title_is_Italicized >", value, "titleIsItalicized");
            return (Criteria) this;
        }

        public Criteria andTitleIsItalicizedGreaterThanOrEqualTo(Integer value) {
            addCriterion("title_is_Italicized >=", value, "titleIsItalicized");
            return (Criteria) this;
        }

        public Criteria andTitleIsItalicizedLessThan(Integer value) {
            addCriterion("title_is_Italicized <", value, "titleIsItalicized");
            return (Criteria) this;
        }

        public Criteria andTitleIsItalicizedLessThanOrEqualTo(Integer value) {
            addCriterion("title_is_Italicized <=", value, "titleIsItalicized");
            return (Criteria) this;
        }

        public Criteria andTitleIsItalicizedIn(List<Integer> values) {
            addCriterion("title_is_Italicized in", values, "titleIsItalicized");
            return (Criteria) this;
        }

        public Criteria andTitleIsItalicizedNotIn(List<Integer> values) {
            addCriterion("title_is_Italicized not in", values, "titleIsItalicized");
            return (Criteria) this;
        }

        public Criteria andTitleIsItalicizedBetween(Integer value1, Integer value2) {
            addCriterion("title_is_Italicized between", value1, value2, "titleIsItalicized");
            return (Criteria) this;
        }

        public Criteria andTitleIsItalicizedNotBetween(Integer value1, Integer value2) {
            addCriterion("title_is_Italicized not between", value1, value2, "titleIsItalicized");
            return (Criteria) this;
        }

        public Criteria andVidsIsNull() {
            addCriterion("vids is null");
            return (Criteria) this;
        }

        public Criteria andVidsIsNotNull() {
            addCriterion("vids is not null");
            return (Criteria) this;
        }

        public Criteria andVidsEqualTo(String value) {
            addCriterion("vids =", value, "vids");
            return (Criteria) this;
        }

        public Criteria andVidsNotEqualTo(String value) {
            addCriterion("vids <>", value, "vids");
            return (Criteria) this;
        }

        public Criteria andVidsGreaterThan(String value) {
            addCriterion("vids >", value, "vids");
            return (Criteria) this;
        }

        public Criteria andVidsGreaterThanOrEqualTo(String value) {
            addCriterion("vids >=", value, "vids");
            return (Criteria) this;
        }

        public Criteria andVidsLessThan(String value) {
            addCriterion("vids <", value, "vids");
            return (Criteria) this;
        }

        public Criteria andVidsLessThanOrEqualTo(String value) {
            addCriterion("vids <=", value, "vids");
            return (Criteria) this;
        }

        public Criteria andVidsLike(String value) {
            addCriterion("vids like", value, "vids");
            return (Criteria) this;
        }

        public Criteria andVidsNotLike(String value) {
            addCriterion("vids not like", value, "vids");
            return (Criteria) this;
        }

        public Criteria andVidsIn(List<String> values) {
            addCriterion("vids in", values, "vids");
            return (Criteria) this;
        }

        public Criteria andVidsNotIn(List<String> values) {
            addCriterion("vids not in", values, "vids");
            return (Criteria) this;
        }

        public Criteria andVidsBetween(String value1, String value2) {
            addCriterion("vids between", value1, value2, "vids");
            return (Criteria) this;
        }

        public Criteria andVidsNotBetween(String value1, String value2) {
            addCriterion("vids not between", value1, value2, "vids");
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