package com.coin.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
public class GuessExample {
    @Setter
    protected String orderByClause;

    @Setter
    protected boolean distinct;

    protected final List<Criteria> oredCriteria;

    public GuessExample() {
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

        public Criteria andTitleIsNull() {
            addCriterion("title is null");
            return (Criteria) this;
        }

        public Criteria andTitleIsNotNull() {
            addCriterion("title is not null");
            return (Criteria) this;
        }

        public Criteria andTitleEqualTo(String value) {
            addCriterion("title =", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotEqualTo(String value) {
            addCriterion("title <>", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThan(String value) {
            addCriterion("title >", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThanOrEqualTo(String value) {
            addCriterion("title >=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThan(String value) {
            addCriterion("title <", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThanOrEqualTo(String value) {
            addCriterion("title <=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLike(String value) {
            addCriterion("title like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotLike(String value) {
            addCriterion("title not like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleIn(List<String> values) {
            addCriterion("title in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotIn(List<String> values) {
            addCriterion("title not in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleBetween(String value1, String value2) {
            addCriterion("title between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotBetween(String value1, String value2) {
            addCriterion("title not between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andGuessSubjectIsNull() {
            addCriterion("guess_subject is null");
            return (Criteria) this;
        }

        public Criteria andGuessSubjectIsNotNull() {
            addCriterion("guess_subject is not null");
            return (Criteria) this;
        }

        public Criteria andGuessSubjectEqualTo(String value) {
            addCriterion("guess_subject =", value, "guessSubject");
            return (Criteria) this;
        }

        public Criteria andGuessSubjectNotEqualTo(String value) {
            addCriterion("guess_subject <>", value, "guessSubject");
            return (Criteria) this;
        }

        public Criteria andGuessSubjectGreaterThan(String value) {
            addCriterion("guess_subject >", value, "guessSubject");
            return (Criteria) this;
        }

        public Criteria andGuessSubjectGreaterThanOrEqualTo(String value) {
            addCriterion("guess_subject >=", value, "guessSubject");
            return (Criteria) this;
        }

        public Criteria andGuessSubjectLessThan(String value) {
            addCriterion("guess_subject <", value, "guessSubject");
            return (Criteria) this;
        }

        public Criteria andGuessSubjectLessThanOrEqualTo(String value) {
            addCriterion("guess_subject <=", value, "guessSubject");
            return (Criteria) this;
        }

        public Criteria andGuessSubjectLike(String value) {
            addCriterion("guess_subject like", value, "guessSubject");
            return (Criteria) this;
        }

        public Criteria andGuessSubjectNotLike(String value) {
            addCriterion("guess_subject not like", value, "guessSubject");
            return (Criteria) this;
        }

        public Criteria andGuessSubjectIn(List<String> values) {
            addCriterion("guess_subject in", values, "guessSubject");
            return (Criteria) this;
        }

        public Criteria andGuessSubjectNotIn(List<String> values) {
            addCriterion("guess_subject not in", values, "guessSubject");
            return (Criteria) this;
        }

        public Criteria andGuessSubjectBetween(String value1, String value2) {
            addCriterion("guess_subject between", value1, value2, "guessSubject");
            return (Criteria) this;
        }

        public Criteria andGuessSubjectNotBetween(String value1, String value2) {
            addCriterion("guess_subject not between", value1, value2, "guessSubject");
            return (Criteria) this;
        }

        public Criteria andCampTypeIsNull() {
            addCriterion("camp_type is null");
            return (Criteria) this;
        }

        public Criteria andCampTypeIsNotNull() {
            addCriterion("camp_type is not null");
            return (Criteria) this;
        }

        public Criteria andCampTypeEqualTo(Integer value) {
            addCriterion("camp_type =", value, "campType");
            return (Criteria) this;
        }

        public Criteria andCampTypeNotEqualTo(Integer value) {
            addCriterion("camp_type <>", value, "campType");
            return (Criteria) this;
        }

        public Criteria andCampTypeGreaterThan(Integer value) {
            addCriterion("camp_type >", value, "campType");
            return (Criteria) this;
        }

        public Criteria andCampTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("camp_type >=", value, "campType");
            return (Criteria) this;
        }

        public Criteria andCampTypeLessThan(Integer value) {
            addCriterion("camp_type <", value, "campType");
            return (Criteria) this;
        }

        public Criteria andCampTypeLessThanOrEqualTo(Integer value) {
            addCriterion("camp_type <=", value, "campType");
            return (Criteria) this;
        }

        public Criteria andCampTypeIn(List<Integer> values) {
            addCriterion("camp_type in", values, "campType");
            return (Criteria) this;
        }

        public Criteria andCampTypeNotIn(List<Integer> values) {
            addCriterion("camp_type not in", values, "campType");
            return (Criteria) this;
        }

        public Criteria andCampTypeBetween(Integer value1, Integer value2) {
            addCriterion("camp_type between", value1, value2, "campType");
            return (Criteria) this;
        }

        public Criteria andCampTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("camp_type not between", value1, value2, "campType");
            return (Criteria) this;
        }

        public Criteria andGuessTypeIdIsNull() {
            addCriterion("guess_type_id is null");
            return (Criteria) this;
        }

        public Criteria andGuessTypeIdIsNotNull() {
            addCriterion("guess_type_id is not null");
            return (Criteria) this;
        }

        public Criteria andGuessTypeIdEqualTo(Long value) {
            addCriterion("guess_type_id =", value, "guessTypeId");
            return (Criteria) this;
        }

        public Criteria andGuessTypeIdNotEqualTo(Long value) {
            addCriterion("guess_type_id <>", value, "guessTypeId");
            return (Criteria) this;
        }

        public Criteria andGuessTypeIdGreaterThan(Long value) {
            addCriterion("guess_type_id >", value, "guessTypeId");
            return (Criteria) this;
        }

        public Criteria andGuessTypeIdGreaterThanOrEqualTo(Long value) {
            addCriterion("guess_type_id >=", value, "guessTypeId");
            return (Criteria) this;
        }

        public Criteria andGuessTypeIdLessThan(Long value) {
            addCriterion("guess_type_id <", value, "guessTypeId");
            return (Criteria) this;
        }

        public Criteria andGuessTypeIdLessThanOrEqualTo(Long value) {
            addCriterion("guess_type_id <=", value, "guessTypeId");
            return (Criteria) this;
        }

        public Criteria andGuessTypeIdIn(List<Long> values) {
            addCriterion("guess_type_id in", values, "guessTypeId");
            return (Criteria) this;
        }

        public Criteria andGuessTypeIdNotIn(List<Long> values) {
            addCriterion("guess_type_id not in", values, "guessTypeId");
            return (Criteria) this;
        }

        public Criteria andGuessTypeIdBetween(Long value1, Long value2) {
            addCriterion("guess_type_id between", value1, value2, "guessTypeId");
            return (Criteria) this;
        }

        public Criteria andGuessTypeIdNotBetween(Long value1, Long value2) {
            addCriterion("guess_type_id not between", value1, value2, "guessTypeId");
            return (Criteria) this;
        }

        public Criteria andVisitsIsNull() {
            addCriterion("visits is null");
            return (Criteria) this;
        }

        public Criteria andVisitsIsNotNull() {
            addCriterion("visits is not null");
            return (Criteria) this;
        }

        public Criteria andVisitsEqualTo(Integer value) {
            addCriterion("visits =", value, "visits");
            return (Criteria) this;
        }

        public Criteria andVisitsNotEqualTo(Integer value) {
            addCriterion("visits <>", value, "visits");
            return (Criteria) this;
        }

        public Criteria andVisitsGreaterThan(Integer value) {
            addCriterion("visits >", value, "visits");
            return (Criteria) this;
        }

        public Criteria andVisitsGreaterThanOrEqualTo(Integer value) {
            addCriterion("visits >=", value, "visits");
            return (Criteria) this;
        }

        public Criteria andVisitsLessThan(Integer value) {
            addCriterion("visits <", value, "visits");
            return (Criteria) this;
        }

        public Criteria andVisitsLessThanOrEqualTo(Integer value) {
            addCriterion("visits <=", value, "visits");
            return (Criteria) this;
        }

        public Criteria andVisitsIn(List<Integer> values) {
            addCriterion("visits in", values, "visits");
            return (Criteria) this;
        }

        public Criteria andVisitsNotIn(List<Integer> values) {
            addCriterion("visits not in", values, "visits");
            return (Criteria) this;
        }

        public Criteria andVisitsBetween(Integer value1, Integer value2) {
            addCriterion("visits between", value1, value2, "visits");
            return (Criteria) this;
        }

        public Criteria andVisitsNotBetween(Integer value1, Integer value2) {
            addCriterion("visits not between", value1, value2, "visits");
            return (Criteria) this;
        }

        public Criteria andCommentsIsNull() {
            addCriterion("comments is null");
            return (Criteria) this;
        }

        public Criteria andCommentsIsNotNull() {
            addCriterion("comments is not null");
            return (Criteria) this;
        }

        public Criteria andCommentsEqualTo(Integer value) {
            addCriterion("comments =", value, "comments");
            return (Criteria) this;
        }

        public Criteria andCommentsNotEqualTo(Integer value) {
            addCriterion("comments <>", value, "comments");
            return (Criteria) this;
        }

        public Criteria andCommentsGreaterThan(Integer value) {
            addCriterion("comments >", value, "comments");
            return (Criteria) this;
        }

        public Criteria andCommentsGreaterThanOrEqualTo(Integer value) {
            addCriterion("comments >=", value, "comments");
            return (Criteria) this;
        }

        public Criteria andCommentsLessThan(Integer value) {
            addCriterion("comments <", value, "comments");
            return (Criteria) this;
        }

        public Criteria andCommentsLessThanOrEqualTo(Integer value) {
            addCriterion("comments <=", value, "comments");
            return (Criteria) this;
        }

        public Criteria andCommentsIn(List<Integer> values) {
            addCriterion("comments in", values, "comments");
            return (Criteria) this;
        }

        public Criteria andCommentsNotIn(List<Integer> values) {
            addCriterion("comments not in", values, "comments");
            return (Criteria) this;
        }

        public Criteria andCommentsBetween(Integer value1, Integer value2) {
            addCriterion("comments between", value1, value2, "comments");
            return (Criteria) this;
        }

        public Criteria andCommentsNotBetween(Integer value1, Integer value2) {
            addCriterion("comments not between", value1, value2, "comments");
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

        public Criteria andBetUserCountIsNull() {
            addCriterion("bet_user_count is null");
            return (Criteria) this;
        }

        public Criteria andBetUserCountIsNotNull() {
            addCriterion("bet_user_count is not null");
            return (Criteria) this;
        }

        public Criteria andBetUserCountEqualTo(Integer value) {
            addCriterion("bet_user_count =", value, "betUserCount");
            return (Criteria) this;
        }

        public Criteria andBetUserCountNotEqualTo(Integer value) {
            addCriterion("bet_user_count <>", value, "betUserCount");
            return (Criteria) this;
        }

        public Criteria andBetUserCountGreaterThan(Integer value) {
            addCriterion("bet_user_count >", value, "betUserCount");
            return (Criteria) this;
        }

        public Criteria andBetUserCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("bet_user_count >=", value, "betUserCount");
            return (Criteria) this;
        }

        public Criteria andBetUserCountLessThan(Integer value) {
            addCriterion("bet_user_count <", value, "betUserCount");
            return (Criteria) this;
        }

        public Criteria andBetUserCountLessThanOrEqualTo(Integer value) {
            addCriterion("bet_user_count <=", value, "betUserCount");
            return (Criteria) this;
        }

        public Criteria andBetUserCountIn(List<Integer> values) {
            addCriterion("bet_user_count in", values, "betUserCount");
            return (Criteria) this;
        }

        public Criteria andBetUserCountNotIn(List<Integer> values) {
            addCriterion("bet_user_count not in", values, "betUserCount");
            return (Criteria) this;
        }

        public Criteria andBetUserCountBetween(Integer value1, Integer value2) {
            addCriterion("bet_user_count between", value1, value2, "betUserCount");
            return (Criteria) this;
        }

        public Criteria andBetUserCountNotBetween(Integer value1, Integer value2) {
            addCriterion("bet_user_count not between", value1, value2, "betUserCount");
            return (Criteria) this;
        }

        public Criteria andContentSnippetIsNull() {
            addCriterion("content_snippet is null");
            return (Criteria) this;
        }

        public Criteria andContentSnippetIsNotNull() {
            addCriterion("content_snippet is not null");
            return (Criteria) this;
        }

        public Criteria andContentSnippetEqualTo(String value) {
            addCriterion("content_snippet =", value, "contentSnippet");
            return (Criteria) this;
        }

        public Criteria andContentSnippetNotEqualTo(String value) {
            addCriterion("content_snippet <>", value, "contentSnippet");
            return (Criteria) this;
        }

        public Criteria andContentSnippetGreaterThan(String value) {
            addCriterion("content_snippet >", value, "contentSnippet");
            return (Criteria) this;
        }

        public Criteria andContentSnippetGreaterThanOrEqualTo(String value) {
            addCriterion("content_snippet >=", value, "contentSnippet");
            return (Criteria) this;
        }

        public Criteria andContentSnippetLessThan(String value) {
            addCriterion("content_snippet <", value, "contentSnippet");
            return (Criteria) this;
        }

        public Criteria andContentSnippetLessThanOrEqualTo(String value) {
            addCriterion("content_snippet <=", value, "contentSnippet");
            return (Criteria) this;
        }

        public Criteria andContentSnippetLike(String value) {
            addCriterion("content_snippet like", value, "contentSnippet");
            return (Criteria) this;
        }

        public Criteria andContentSnippetNotLike(String value) {
            addCriterion("content_snippet not like", value, "contentSnippet");
            return (Criteria) this;
        }

        public Criteria andContentSnippetIn(List<String> values) {
            addCriterion("content_snippet in", values, "contentSnippet");
            return (Criteria) this;
        }

        public Criteria andContentSnippetNotIn(List<String> values) {
            addCriterion("content_snippet not in", values, "contentSnippet");
            return (Criteria) this;
        }

        public Criteria andContentSnippetBetween(String value1, String value2) {
            addCriterion("content_snippet between", value1, value2, "contentSnippet");
            return (Criteria) this;
        }

        public Criteria andContentSnippetNotBetween(String value1, String value2) {
            addCriterion("content_snippet not between", value1, value2, "contentSnippet");
            return (Criteria) this;
        }

        public Criteria andContentImagesIsNull() {
            addCriterion("content_images is null");
            return (Criteria) this;
        }

        public Criteria andContentImagesIsNotNull() {
            addCriterion("content_images is not null");
            return (Criteria) this;
        }

        public Criteria andContentImagesEqualTo(String value) {
            addCriterion("content_images =", value, "contentImages");
            return (Criteria) this;
        }

        public Criteria andContentImagesNotEqualTo(String value) {
            addCriterion("content_images <>", value, "contentImages");
            return (Criteria) this;
        }

        public Criteria andContentImagesGreaterThan(String value) {
            addCriterion("content_images >", value, "contentImages");
            return (Criteria) this;
        }

        public Criteria andContentImagesGreaterThanOrEqualTo(String value) {
            addCriterion("content_images >=", value, "contentImages");
            return (Criteria) this;
        }

        public Criteria andContentImagesLessThan(String value) {
            addCriterion("content_images <", value, "contentImages");
            return (Criteria) this;
        }

        public Criteria andContentImagesLessThanOrEqualTo(String value) {
            addCriterion("content_images <=", value, "contentImages");
            return (Criteria) this;
        }

        public Criteria andContentImagesLike(String value) {
            addCriterion("content_images like", value, "contentImages");
            return (Criteria) this;
        }

        public Criteria andContentImagesNotLike(String value) {
            addCriterion("content_images not like", value, "contentImages");
            return (Criteria) this;
        }

        public Criteria andContentImagesIn(List<String> values) {
            addCriterion("content_images in", values, "contentImages");
            return (Criteria) this;
        }

        public Criteria andContentImagesNotIn(List<String> values) {
            addCriterion("content_images not in", values, "contentImages");
            return (Criteria) this;
        }

        public Criteria andContentImagesBetween(String value1, String value2) {
            addCriterion("content_images between", value1, value2, "contentImages");
            return (Criteria) this;
        }

        public Criteria andContentImagesNotBetween(String value1, String value2) {
            addCriterion("content_images not between", value1, value2, "contentImages");
            return (Criteria) this;
        }

        public Criteria andAdminUserIdIsNull() {
            addCriterion("admin_user_id is null");
            return (Criteria) this;
        }

        public Criteria andAdminUserIdIsNotNull() {
            addCriterion("admin_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andAdminUserIdEqualTo(Long value) {
            addCriterion("admin_user_id =", value, "adminUserId");
            return (Criteria) this;
        }

        public Criteria andAdminUserIdNotEqualTo(Long value) {
            addCriterion("admin_user_id <>", value, "adminUserId");
            return (Criteria) this;
        }

        public Criteria andAdminUserIdGreaterThan(Long value) {
            addCriterion("admin_user_id >", value, "adminUserId");
            return (Criteria) this;
        }

        public Criteria andAdminUserIdGreaterThanOrEqualTo(Long value) {
            addCriterion("admin_user_id >=", value, "adminUserId");
            return (Criteria) this;
        }

        public Criteria andAdminUserIdLessThan(Long value) {
            addCriterion("admin_user_id <", value, "adminUserId");
            return (Criteria) this;
        }

        public Criteria andAdminUserIdLessThanOrEqualTo(Long value) {
            addCriterion("admin_user_id <=", value, "adminUserId");
            return (Criteria) this;
        }

        public Criteria andAdminUserIdIn(List<Long> values) {
            addCriterion("admin_user_id in", values, "adminUserId");
            return (Criteria) this;
        }

        public Criteria andAdminUserIdNotIn(List<Long> values) {
            addCriterion("admin_user_id not in", values, "adminUserId");
            return (Criteria) this;
        }

        public Criteria andAdminUserIdBetween(Long value1, Long value2) {
            addCriterion("admin_user_id between", value1, value2, "adminUserId");
            return (Criteria) this;
        }

        public Criteria andAdminUserIdNotBetween(Long value1, Long value2) {
            addCriterion("admin_user_id not between", value1, value2, "adminUserId");
            return (Criteria) this;
        }

        public Criteria andAdminUserNameIsNull() {
            addCriterion("admin_user_name is null");
            return (Criteria) this;
        }

        public Criteria andAdminUserNameIsNotNull() {
            addCriterion("admin_user_name is not null");
            return (Criteria) this;
        }

        public Criteria andAdminUserNameEqualTo(String value) {
            addCriterion("admin_user_name =", value, "adminUserName");
            return (Criteria) this;
        }

        public Criteria andAdminUserNameNotEqualTo(String value) {
            addCriterion("admin_user_name <>", value, "adminUserName");
            return (Criteria) this;
        }

        public Criteria andAdminUserNameGreaterThan(String value) {
            addCriterion("admin_user_name >", value, "adminUserName");
            return (Criteria) this;
        }

        public Criteria andAdminUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("admin_user_name >=", value, "adminUserName");
            return (Criteria) this;
        }

        public Criteria andAdminUserNameLessThan(String value) {
            addCriterion("admin_user_name <", value, "adminUserName");
            return (Criteria) this;
        }

        public Criteria andAdminUserNameLessThanOrEqualTo(String value) {
            addCriterion("admin_user_name <=", value, "adminUserName");
            return (Criteria) this;
        }

        public Criteria andAdminUserNameLike(String value) {
            addCriterion("admin_user_name like", value, "adminUserName");
            return (Criteria) this;
        }

        public Criteria andAdminUserNameNotLike(String value) {
            addCriterion("admin_user_name not like", value, "adminUserName");
            return (Criteria) this;
        }

        public Criteria andAdminUserNameIn(List<String> values) {
            addCriterion("admin_user_name in", values, "adminUserName");
            return (Criteria) this;
        }

        public Criteria andAdminUserNameNotIn(List<String> values) {
            addCriterion("admin_user_name not in", values, "adminUserName");
            return (Criteria) this;
        }

        public Criteria andAdminUserNameBetween(String value1, String value2) {
            addCriterion("admin_user_name between", value1, value2, "adminUserName");
            return (Criteria) this;
        }

        public Criteria andAdminUserNameNotBetween(String value1, String value2) {
            addCriterion("admin_user_name not between", value1, value2, "adminUserName");
            return (Criteria) this;
        }

        public Criteria andBeginTimeIsNull() {
            addCriterion("begin_time is null");
            return (Criteria) this;
        }

        public Criteria andBeginTimeIsNotNull() {
            addCriterion("begin_time is not null");
            return (Criteria) this;
        }

        public Criteria andBeginTimeEqualTo(Date value) {
            addCriterion("begin_time =", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeNotEqualTo(Date value) {
            addCriterion("begin_time <>", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeGreaterThan(Date value) {
            addCriterion("begin_time >", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("begin_time >=", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeLessThan(Date value) {
            addCriterion("begin_time <", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeLessThanOrEqualTo(Date value) {
            addCriterion("begin_time <=", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeIn(List<Date> values) {
            addCriterion("begin_time in", values, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeNotIn(List<Date> values) {
            addCriterion("begin_time not in", values, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeBetween(Date value1, Date value2) {
            addCriterion("begin_time between", value1, value2, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeNotBetween(Date value1, Date value2) {
            addCriterion("begin_time not between", value1, value2, "beginTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNull() {
            addCriterion("end_time is null");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNotNull() {
            addCriterion("end_time is not null");
            return (Criteria) this;
        }

        public Criteria andEndTimeEqualTo(Date value) {
            addCriterion("end_time =", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotEqualTo(Date value) {
            addCriterion("end_time <>", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThan(Date value) {
            addCriterion("end_time >", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("end_time >=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThan(Date value) {
            addCriterion("end_time <", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThanOrEqualTo(Date value) {
            addCriterion("end_time <=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIn(List<Date> values) {
            addCriterion("end_time in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotIn(List<Date> values) {
            addCriterion("end_time not in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeBetween(Date value1, Date value2) {
            addCriterion("end_time between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotBetween(Date value1, Date value2) {
            addCriterion("end_time not between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andSecondConfirmIsNull() {
            addCriterion("second_confirm is null");
            return (Criteria) this;
        }

        public Criteria andSecondConfirmIsNotNull() {
            addCriterion("second_confirm is not null");
            return (Criteria) this;
        }

        public Criteria andSecondConfirmEqualTo(String value) {
            addCriterion("second_confirm =", value, "secondConfirm");
            return (Criteria) this;
        }

        public Criteria andSecondConfirmNotEqualTo(String value) {
            addCriterion("second_confirm <>", value, "secondConfirm");
            return (Criteria) this;
        }

        public Criteria andSecondConfirmGreaterThan(String value) {
            addCriterion("second_confirm >", value, "secondConfirm");
            return (Criteria) this;
        }

        public Criteria andSecondConfirmGreaterThanOrEqualTo(String value) {
            addCriterion("second_confirm >=", value, "secondConfirm");
            return (Criteria) this;
        }

        public Criteria andSecondConfirmLessThan(String value) {
            addCriterion("second_confirm <", value, "secondConfirm");
            return (Criteria) this;
        }

        public Criteria andSecondConfirmLessThanOrEqualTo(String value) {
            addCriterion("second_confirm <=", value, "secondConfirm");
            return (Criteria) this;
        }

        public Criteria andSecondConfirmLike(String value) {
            addCriterion("second_confirm like", value, "secondConfirm");
            return (Criteria) this;
        }

        public Criteria andSecondConfirmNotLike(String value) {
            addCriterion("second_confirm not like", value, "secondConfirm");
            return (Criteria) this;
        }

        public Criteria andSecondConfirmIn(List<String> values) {
            addCriterion("second_confirm in", values, "secondConfirm");
            return (Criteria) this;
        }

        public Criteria andSecondConfirmNotIn(List<String> values) {
            addCriterion("second_confirm not in", values, "secondConfirm");
            return (Criteria) this;
        }

        public Criteria andSecondConfirmBetween(String value1, String value2) {
            addCriterion("second_confirm between", value1, value2, "secondConfirm");
            return (Criteria) this;
        }

        public Criteria andSecondConfirmNotBetween(String value1, String value2) {
            addCriterion("second_confirm not between", value1, value2, "secondConfirm");
            return (Criteria) this;
        }

        public Criteria andFlagImageIsNull() {
            addCriterion("flag_image is null");
            return (Criteria) this;
        }

        public Criteria andFlagImageIsNotNull() {
            addCriterion("flag_image is not null");
            return (Criteria) this;
        }

        public Criteria andFlagImageEqualTo(String value) {
            addCriterion("flag_image =", value, "flagImage");
            return (Criteria) this;
        }

        public Criteria andFlagImageNotEqualTo(String value) {
            addCriterion("flag_image <>", value, "flagImage");
            return (Criteria) this;
        }

        public Criteria andFlagImageGreaterThan(String value) {
            addCriterion("flag_image >", value, "flagImage");
            return (Criteria) this;
        }

        public Criteria andFlagImageGreaterThanOrEqualTo(String value) {
            addCriterion("flag_image >=", value, "flagImage");
            return (Criteria) this;
        }

        public Criteria andFlagImageLessThan(String value) {
            addCriterion("flag_image <", value, "flagImage");
            return (Criteria) this;
        }

        public Criteria andFlagImageLessThanOrEqualTo(String value) {
            addCriterion("flag_image <=", value, "flagImage");
            return (Criteria) this;
        }

        public Criteria andFlagImageLike(String value) {
            addCriterion("flag_image like", value, "flagImage");
            return (Criteria) this;
        }

        public Criteria andFlagImageNotLike(String value) {
            addCriterion("flag_image not like", value, "flagImage");
            return (Criteria) this;
        }

        public Criteria andFlagImageIn(List<String> values) {
            addCriterion("flag_image in", values, "flagImage");
            return (Criteria) this;
        }

        public Criteria andFlagImageNotIn(List<String> values) {
            addCriterion("flag_image not in", values, "flagImage");
            return (Criteria) this;
        }

        public Criteria andFlagImageBetween(String value1, String value2) {
            addCriterion("flag_image between", value1, value2, "flagImage");
            return (Criteria) this;
        }

        public Criteria andFlagImageNotBetween(String value1, String value2) {
            addCriterion("flag_image not between", value1, value2, "flagImage");
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

        public Criteria andMatchStartTimeIsNull() {
            addCriterion("match_start_time is null");
            return (Criteria) this;
        }

        public Criteria andMatchStartTimeIsNotNull() {
            addCriterion("match_start_time is not null");
            return (Criteria) this;
        }

        public Criteria andMatchStartTimeEqualTo(Date value) {
            addCriterion("match_start_time =", value, "matchStartTime");
            return (Criteria) this;
        }

        public Criteria andMatchStartTimeNotEqualTo(Date value) {
            addCriterion("match_start_time <>", value, "matchStartTime");
            return (Criteria) this;
        }

        public Criteria andMatchStartTimeGreaterThan(Date value) {
            addCriterion("match_start_time >", value, "matchStartTime");
            return (Criteria) this;
        }

        public Criteria andMatchStartTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("match_start_time >=", value, "matchStartTime");
            return (Criteria) this;
        }

        public Criteria andMatchStartTimeLessThan(Date value) {
            addCriterion("match_start_time <", value, "matchStartTime");
            return (Criteria) this;
        }

        public Criteria andMatchStartTimeLessThanOrEqualTo(Date value) {
            addCriterion("match_start_time <=", value, "matchStartTime");
            return (Criteria) this;
        }

        public Criteria andMatchStartTimeIn(List<Date> values) {
            addCriterion("match_start_time in", values, "matchStartTime");
            return (Criteria) this;
        }

        public Criteria andMatchStartTimeNotIn(List<Date> values) {
            addCriterion("match_start_time not in", values, "matchStartTime");
            return (Criteria) this;
        }

        public Criteria andMatchStartTimeBetween(Date value1, Date value2) {
            addCriterion("match_start_time between", value1, value2, "matchStartTime");
            return (Criteria) this;
        }

        public Criteria andMatchStartTimeNotBetween(Date value1, Date value2) {
            addCriterion("match_start_time not between", value1, value2, "matchStartTime");
            return (Criteria) this;
        }

        public Criteria andHomeTeamNameIsNull() {
            addCriterion("home_team_name is null");
            return (Criteria) this;
        }

        public Criteria andHomeTeamNameIsNotNull() {
            addCriterion("home_team_name is not null");
            return (Criteria) this;
        }

        public Criteria andHomeTeamNameEqualTo(String value) {
            addCriterion("home_team_name =", value, "homeTeamName");
            return (Criteria) this;
        }

        public Criteria andHomeTeamNameNotEqualTo(String value) {
            addCriterion("home_team_name <>", value, "homeTeamName");
            return (Criteria) this;
        }

        public Criteria andHomeTeamNameGreaterThan(String value) {
            addCriterion("home_team_name >", value, "homeTeamName");
            return (Criteria) this;
        }

        public Criteria andHomeTeamNameGreaterThanOrEqualTo(String value) {
            addCriterion("home_team_name >=", value, "homeTeamName");
            return (Criteria) this;
        }

        public Criteria andHomeTeamNameLessThan(String value) {
            addCriterion("home_team_name <", value, "homeTeamName");
            return (Criteria) this;
        }

        public Criteria andHomeTeamNameLessThanOrEqualTo(String value) {
            addCriterion("home_team_name <=", value, "homeTeamName");
            return (Criteria) this;
        }

        public Criteria andHomeTeamNameLike(String value) {
            addCriterion("home_team_name like", value, "homeTeamName");
            return (Criteria) this;
        }

        public Criteria andHomeTeamNameNotLike(String value) {
            addCriterion("home_team_name not like", value, "homeTeamName");
            return (Criteria) this;
        }

        public Criteria andHomeTeamNameIn(List<String> values) {
            addCriterion("home_team_name in", values, "homeTeamName");
            return (Criteria) this;
        }

        public Criteria andHomeTeamNameNotIn(List<String> values) {
            addCriterion("home_team_name not in", values, "homeTeamName");
            return (Criteria) this;
        }

        public Criteria andHomeTeamNameBetween(String value1, String value2) {
            addCriterion("home_team_name between", value1, value2, "homeTeamName");
            return (Criteria) this;
        }

        public Criteria andHomeTeamNameNotBetween(String value1, String value2) {
            addCriterion("home_team_name not between", value1, value2, "homeTeamName");
            return (Criteria) this;
        }

        public Criteria andGuestTeamNameIsNull() {
            addCriterion("guest_team_name is null");
            return (Criteria) this;
        }

        public Criteria andGuestTeamNameIsNotNull() {
            addCriterion("guest_team_name is not null");
            return (Criteria) this;
        }

        public Criteria andGuestTeamNameEqualTo(String value) {
            addCriterion("guest_team_name =", value, "guestTeamName");
            return (Criteria) this;
        }

        public Criteria andGuestTeamNameNotEqualTo(String value) {
            addCriterion("guest_team_name <>", value, "guestTeamName");
            return (Criteria) this;
        }

        public Criteria andGuestTeamNameGreaterThan(String value) {
            addCriterion("guest_team_name >", value, "guestTeamName");
            return (Criteria) this;
        }

        public Criteria andGuestTeamNameGreaterThanOrEqualTo(String value) {
            addCriterion("guest_team_name >=", value, "guestTeamName");
            return (Criteria) this;
        }

        public Criteria andGuestTeamNameLessThan(String value) {
            addCriterion("guest_team_name <", value, "guestTeamName");
            return (Criteria) this;
        }

        public Criteria andGuestTeamNameLessThanOrEqualTo(String value) {
            addCriterion("guest_team_name <=", value, "guestTeamName");
            return (Criteria) this;
        }

        public Criteria andGuestTeamNameLike(String value) {
            addCriterion("guest_team_name like", value, "guestTeamName");
            return (Criteria) this;
        }

        public Criteria andGuestTeamNameNotLike(String value) {
            addCriterion("guest_team_name not like", value, "guestTeamName");
            return (Criteria) this;
        }

        public Criteria andGuestTeamNameIn(List<String> values) {
            addCriterion("guest_team_name in", values, "guestTeamName");
            return (Criteria) this;
        }

        public Criteria andGuestTeamNameNotIn(List<String> values) {
            addCriterion("guest_team_name not in", values, "guestTeamName");
            return (Criteria) this;
        }

        public Criteria andGuestTeamNameBetween(String value1, String value2) {
            addCriterion("guest_team_name between", value1, value2, "guestTeamName");
            return (Criteria) this;
        }

        public Criteria andGuestTeamNameNotBetween(String value1, String value2) {
            addCriterion("guest_team_name not between", value1, value2, "guestTeamName");
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