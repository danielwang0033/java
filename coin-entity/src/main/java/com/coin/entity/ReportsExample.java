package com.coin.entity;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
public class ReportsExample {
    @Setter
    protected String orderByClause;

    @Setter
    protected boolean distinct;

    protected final List<Criteria> oredCriteria;

    public ReportsExample() {
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

        public Criteria andReportedWebsiteUrlIsNull() {
            addCriterion("reported_website_url is null");
            return (Criteria) this;
        }

        public Criteria andReportedWebsiteUrlIsNotNull() {
            addCriterion("reported_website_url is not null");
            return (Criteria) this;
        }

        public Criteria andReportedWebsiteUrlEqualTo(String value) {
            addCriterion("reported_website_url =", value, "reportedWebsiteUrl");
            return (Criteria) this;
        }

        public Criteria andReportedWebsiteUrlNotEqualTo(String value) {
            addCriterion("reported_website_url <>", value, "reportedWebsiteUrl");
            return (Criteria) this;
        }

        public Criteria andReportedWebsiteUrlGreaterThan(String value) {
            addCriterion("reported_website_url >", value, "reportedWebsiteUrl");
            return (Criteria) this;
        }

        public Criteria andReportedWebsiteUrlGreaterThanOrEqualTo(String value) {
            addCriterion("reported_website_url >=", value, "reportedWebsiteUrl");
            return (Criteria) this;
        }

        public Criteria andReportedWebsiteUrlLessThan(String value) {
            addCriterion("reported_website_url <", value, "reportedWebsiteUrl");
            return (Criteria) this;
        }

        public Criteria andReportedWebsiteUrlLessThanOrEqualTo(String value) {
            addCriterion("reported_website_url <=", value, "reportedWebsiteUrl");
            return (Criteria) this;
        }

        public Criteria andReportedWebsiteUrlLike(String value) {
            addCriterion("reported_website_url like", value, "reportedWebsiteUrl");
            return (Criteria) this;
        }

        public Criteria andReportedWebsiteUrlNotLike(String value) {
            addCriterion("reported_website_url not like", value, "reportedWebsiteUrl");
            return (Criteria) this;
        }

        public Criteria andReportedWebsiteUrlIn(List<String> values) {
            addCriterion("reported_website_url in", values, "reportedWebsiteUrl");
            return (Criteria) this;
        }

        public Criteria andReportedWebsiteUrlNotIn(List<String> values) {
            addCriterion("reported_website_url not in", values, "reportedWebsiteUrl");
            return (Criteria) this;
        }

        public Criteria andReportedWebsiteUrlBetween(String value1, String value2) {
            addCriterion("reported_website_url between", value1, value2, "reportedWebsiteUrl");
            return (Criteria) this;
        }

        public Criteria andReportedWebsiteUrlNotBetween(String value1, String value2) {
            addCriterion("reported_website_url not between", value1, value2, "reportedWebsiteUrl");
            return (Criteria) this;
        }

        public Criteria andReportedWebsiteNameIsNull() {
            addCriterion("reported_website_name is null");
            return (Criteria) this;
        }

        public Criteria andReportedWebsiteNameIsNotNull() {
            addCriterion("reported_website_name is not null");
            return (Criteria) this;
        }

        public Criteria andReportedWebsiteNameEqualTo(String value) {
            addCriterion("reported_website_name =", value, "reportedWebsiteName");
            return (Criteria) this;
        }

        public Criteria andReportedWebsiteNameNotEqualTo(String value) {
            addCriterion("reported_website_name <>", value, "reportedWebsiteName");
            return (Criteria) this;
        }

        public Criteria andReportedWebsiteNameGreaterThan(String value) {
            addCriterion("reported_website_name >", value, "reportedWebsiteName");
            return (Criteria) this;
        }

        public Criteria andReportedWebsiteNameGreaterThanOrEqualTo(String value) {
            addCriterion("reported_website_name >=", value, "reportedWebsiteName");
            return (Criteria) this;
        }

        public Criteria andReportedWebsiteNameLessThan(String value) {
            addCriterion("reported_website_name <", value, "reportedWebsiteName");
            return (Criteria) this;
        }

        public Criteria andReportedWebsiteNameLessThanOrEqualTo(String value) {
            addCriterion("reported_website_name <=", value, "reportedWebsiteName");
            return (Criteria) this;
        }

        public Criteria andReportedWebsiteNameLike(String value) {
            addCriterion("reported_website_name like", value, "reportedWebsiteName");
            return (Criteria) this;
        }

        public Criteria andReportedWebsiteNameNotLike(String value) {
            addCriterion("reported_website_name not like", value, "reportedWebsiteName");
            return (Criteria) this;
        }

        public Criteria andReportedWebsiteNameIn(List<String> values) {
            addCriterion("reported_website_name in", values, "reportedWebsiteName");
            return (Criteria) this;
        }

        public Criteria andReportedWebsiteNameNotIn(List<String> values) {
            addCriterion("reported_website_name not in", values, "reportedWebsiteName");
            return (Criteria) this;
        }

        public Criteria andReportedWebsiteNameBetween(String value1, String value2) {
            addCriterion("reported_website_name between", value1, value2, "reportedWebsiteName");
            return (Criteria) this;
        }

        public Criteria andReportedWebsiteNameNotBetween(String value1, String value2) {
            addCriterion("reported_website_name not between", value1, value2, "reportedWebsiteName");
            return (Criteria) this;
        }

        public Criteria andReportReasonIdIsNull() {
            addCriterion("report_reason_id is null");
            return (Criteria) this;
        }

        public Criteria andReportReasonIdIsNotNull() {
            addCriterion("report_reason_id is not null");
            return (Criteria) this;
        }

        public Criteria andReportReasonIdEqualTo(Long value) {
            addCriterion("report_reason_id =", value, "reportReasonId");
            return (Criteria) this;
        }

        public Criteria andReportReasonIdNotEqualTo(Long value) {
            addCriterion("report_reason_id <>", value, "reportReasonId");
            return (Criteria) this;
        }

        public Criteria andReportReasonIdGreaterThan(Long value) {
            addCriterion("report_reason_id >", value, "reportReasonId");
            return (Criteria) this;
        }

        public Criteria andReportReasonIdGreaterThanOrEqualTo(Long value) {
            addCriterion("report_reason_id >=", value, "reportReasonId");
            return (Criteria) this;
        }

        public Criteria andReportReasonIdLessThan(Long value) {
            addCriterion("report_reason_id <", value, "reportReasonId");
            return (Criteria) this;
        }

        public Criteria andReportReasonIdLessThanOrEqualTo(Long value) {
            addCriterion("report_reason_id <=", value, "reportReasonId");
            return (Criteria) this;
        }

        public Criteria andReportReasonIdIn(List<Long> values) {
            addCriterion("report_reason_id in", values, "reportReasonId");
            return (Criteria) this;
        }

        public Criteria andReportReasonIdNotIn(List<Long> values) {
            addCriterion("report_reason_id not in", values, "reportReasonId");
            return (Criteria) this;
        }

        public Criteria andReportReasonIdBetween(Long value1, Long value2) {
            addCriterion("report_reason_id between", value1, value2, "reportReasonId");
            return (Criteria) this;
        }

        public Criteria andReportReasonIdNotBetween(Long value1, Long value2) {
            addCriterion("report_reason_id not between", value1, value2, "reportReasonId");
            return (Criteria) this;
        }

        public Criteria andAppealAmountIsNull() {
            addCriterion("appeal_amount is null");
            return (Criteria) this;
        }

        public Criteria andAppealAmountIsNotNull() {
            addCriterion("appeal_amount is not null");
            return (Criteria) this;
        }

        public Criteria andAppealAmountEqualTo(BigDecimal value) {
            addCriterion("appeal_amount =", value, "appealAmount");
            return (Criteria) this;
        }

        public Criteria andAppealAmountNotEqualTo(BigDecimal value) {
            addCriterion("appeal_amount <>", value, "appealAmount");
            return (Criteria) this;
        }

        public Criteria andAppealAmountGreaterThan(BigDecimal value) {
            addCriterion("appeal_amount >", value, "appealAmount");
            return (Criteria) this;
        }

        public Criteria andAppealAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("appeal_amount >=", value, "appealAmount");
            return (Criteria) this;
        }

        public Criteria andAppealAmountLessThan(BigDecimal value) {
            addCriterion("appeal_amount <", value, "appealAmount");
            return (Criteria) this;
        }

        public Criteria andAppealAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("appeal_amount <=", value, "appealAmount");
            return (Criteria) this;
        }

        public Criteria andAppealAmountIn(List<BigDecimal> values) {
            addCriterion("appeal_amount in", values, "appealAmount");
            return (Criteria) this;
        }

        public Criteria andAppealAmountNotIn(List<BigDecimal> values) {
            addCriterion("appeal_amount not in", values, "appealAmount");
            return (Criteria) this;
        }

        public Criteria andAppealAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("appeal_amount between", value1, value2, "appealAmount");
            return (Criteria) this;
        }

        public Criteria andAppealAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("appeal_amount not between", value1, value2, "appealAmount");
            return (Criteria) this;
        }

        public Criteria andReportContentIsNull() {
            addCriterion("report_content is null");
            return (Criteria) this;
        }

        public Criteria andReportContentIsNotNull() {
            addCriterion("report_content is not null");
            return (Criteria) this;
        }

        public Criteria andReportContentEqualTo(String value) {
            addCriterion("report_content =", value, "reportContent");
            return (Criteria) this;
        }

        public Criteria andReportContentNotEqualTo(String value) {
            addCriterion("report_content <>", value, "reportContent");
            return (Criteria) this;
        }

        public Criteria andReportContentGreaterThan(String value) {
            addCriterion("report_content >", value, "reportContent");
            return (Criteria) this;
        }

        public Criteria andReportContentGreaterThanOrEqualTo(String value) {
            addCriterion("report_content >=", value, "reportContent");
            return (Criteria) this;
        }

        public Criteria andReportContentLessThan(String value) {
            addCriterion("report_content <", value, "reportContent");
            return (Criteria) this;
        }

        public Criteria andReportContentLessThanOrEqualTo(String value) {
            addCriterion("report_content <=", value, "reportContent");
            return (Criteria) this;
        }

        public Criteria andReportContentLike(String value) {
            addCriterion("report_content like", value, "reportContent");
            return (Criteria) this;
        }

        public Criteria andReportContentNotLike(String value) {
            addCriterion("report_content not like", value, "reportContent");
            return (Criteria) this;
        }

        public Criteria andReportContentIn(List<String> values) {
            addCriterion("report_content in", values, "reportContent");
            return (Criteria) this;
        }

        public Criteria andReportContentNotIn(List<String> values) {
            addCriterion("report_content not in", values, "reportContent");
            return (Criteria) this;
        }

        public Criteria andReportContentBetween(String value1, String value2) {
            addCriterion("report_content between", value1, value2, "reportContent");
            return (Criteria) this;
        }

        public Criteria andReportContentNotBetween(String value1, String value2) {
            addCriterion("report_content not between", value1, value2, "reportContent");
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

        public Criteria andNameTagIdIsNull() {
            addCriterion("name_tag_id is null");
            return (Criteria) this;
        }

        public Criteria andNameTagIdIsNotNull() {
            addCriterion("name_tag_id is not null");
            return (Criteria) this;
        }

        public Criteria andNameTagIdEqualTo(Integer value) {
            addCriterion("name_tag_id =", value, "nameTagId");
            return (Criteria) this;
        }

        public Criteria andNameTagIdNotEqualTo(Integer value) {
            addCriterion("name_tag_id <>", value, "nameTagId");
            return (Criteria) this;
        }

        public Criteria andNameTagIdGreaterThan(Integer value) {
            addCriterion("name_tag_id >", value, "nameTagId");
            return (Criteria) this;
        }

        public Criteria andNameTagIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("name_tag_id >=", value, "nameTagId");
            return (Criteria) this;
        }

        public Criteria andNameTagIdLessThan(Integer value) {
            addCriterion("name_tag_id <", value, "nameTagId");
            return (Criteria) this;
        }

        public Criteria andNameTagIdLessThanOrEqualTo(Integer value) {
            addCriterion("name_tag_id <=", value, "nameTagId");
            return (Criteria) this;
        }

        public Criteria andNameTagIdIn(List<Integer> values) {
            addCriterion("name_tag_id in", values, "nameTagId");
            return (Criteria) this;
        }

        public Criteria andNameTagIdNotIn(List<Integer> values) {
            addCriterion("name_tag_id not in", values, "nameTagId");
            return (Criteria) this;
        }

        public Criteria andNameTagIdBetween(Integer value1, Integer value2) {
            addCriterion("name_tag_id between", value1, value2, "nameTagId");
            return (Criteria) this;
        }

        public Criteria andNameTagIdNotBetween(Integer value1, Integer value2) {
            addCriterion("name_tag_id not between", value1, value2, "nameTagId");
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

        public Criteria andSubmissionTimeIsNull() {
            addCriterion("submission_time is null");
            return (Criteria) this;
        }

        public Criteria andSubmissionTimeIsNotNull() {
            addCriterion("submission_time is not null");
            return (Criteria) this;
        }

        public Criteria andSubmissionTimeEqualTo(Date value) {
            addCriterion("submission_time =", value, "submissionTime");
            return (Criteria) this;
        }

        public Criteria andSubmissionTimeNotEqualTo(Date value) {
            addCriterion("submission_time <>", value, "submissionTime");
            return (Criteria) this;
        }

        public Criteria andSubmissionTimeGreaterThan(Date value) {
            addCriterion("submission_time >", value, "submissionTime");
            return (Criteria) this;
        }

        public Criteria andSubmissionTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("submission_time >=", value, "submissionTime");
            return (Criteria) this;
        }

        public Criteria andSubmissionTimeLessThan(Date value) {
            addCriterion("submission_time <", value, "submissionTime");
            return (Criteria) this;
        }

        public Criteria andSubmissionTimeLessThanOrEqualTo(Date value) {
            addCriterion("submission_time <=", value, "submissionTime");
            return (Criteria) this;
        }

        public Criteria andSubmissionTimeIn(List<Date> values) {
            addCriterion("submission_time in", values, "submissionTime");
            return (Criteria) this;
        }

        public Criteria andSubmissionTimeNotIn(List<Date> values) {
            addCriterion("submission_time not in", values, "submissionTime");
            return (Criteria) this;
        }

        public Criteria andSubmissionTimeBetween(Date value1, Date value2) {
            addCriterion("submission_time between", value1, value2, "submissionTime");
            return (Criteria) this;
        }

        public Criteria andSubmissionTimeNotBetween(Date value1, Date value2) {
            addCriterion("submission_time not between", value1, value2, "submissionTime");
            return (Criteria) this;
        }

        public Criteria andAcceptanceTimeIsNull() {
            addCriterion("acceptance_time is null");
            return (Criteria) this;
        }

        public Criteria andAcceptanceTimeIsNotNull() {
            addCriterion("acceptance_time is not null");
            return (Criteria) this;
        }

        public Criteria andAcceptanceTimeEqualTo(Date value) {
            addCriterion("acceptance_time =", value, "acceptanceTime");
            return (Criteria) this;
        }

        public Criteria andAcceptanceTimeNotEqualTo(Date value) {
            addCriterion("acceptance_time <>", value, "acceptanceTime");
            return (Criteria) this;
        }

        public Criteria andAcceptanceTimeGreaterThan(Date value) {
            addCriterion("acceptance_time >", value, "acceptanceTime");
            return (Criteria) this;
        }

        public Criteria andAcceptanceTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("acceptance_time >=", value, "acceptanceTime");
            return (Criteria) this;
        }

        public Criteria andAcceptanceTimeLessThan(Date value) {
            addCriterion("acceptance_time <", value, "acceptanceTime");
            return (Criteria) this;
        }

        public Criteria andAcceptanceTimeLessThanOrEqualTo(Date value) {
            addCriterion("acceptance_time <=", value, "acceptanceTime");
            return (Criteria) this;
        }

        public Criteria andAcceptanceTimeIn(List<Date> values) {
            addCriterion("acceptance_time in", values, "acceptanceTime");
            return (Criteria) this;
        }

        public Criteria andAcceptanceTimeNotIn(List<Date> values) {
            addCriterion("acceptance_time not in", values, "acceptanceTime");
            return (Criteria) this;
        }

        public Criteria andAcceptanceTimeBetween(Date value1, Date value2) {
            addCriterion("acceptance_time between", value1, value2, "acceptanceTime");
            return (Criteria) this;
        }

        public Criteria andAcceptanceTimeNotBetween(Date value1, Date value2) {
            addCriterion("acceptance_time not between", value1, value2, "acceptanceTime");
            return (Criteria) this;
        }

        public Criteria andCompletionTimeIsNull() {
            addCriterion("completion_time is null");
            return (Criteria) this;
        }

        public Criteria andCompletionTimeIsNotNull() {
            addCriterion("completion_time is not null");
            return (Criteria) this;
        }

        public Criteria andCompletionTimeEqualTo(Date value) {
            addCriterion("completion_time =", value, "completionTime");
            return (Criteria) this;
        }

        public Criteria andCompletionTimeNotEqualTo(Date value) {
            addCriterion("completion_time <>", value, "completionTime");
            return (Criteria) this;
        }

        public Criteria andCompletionTimeGreaterThan(Date value) {
            addCriterion("completion_time >", value, "completionTime");
            return (Criteria) this;
        }

        public Criteria andCompletionTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("completion_time >=", value, "completionTime");
            return (Criteria) this;
        }

        public Criteria andCompletionTimeLessThan(Date value) {
            addCriterion("completion_time <", value, "completionTime");
            return (Criteria) this;
        }

        public Criteria andCompletionTimeLessThanOrEqualTo(Date value) {
            addCriterion("completion_time <=", value, "completionTime");
            return (Criteria) this;
        }

        public Criteria andCompletionTimeIn(List<Date> values) {
            addCriterion("completion_time in", values, "completionTime");
            return (Criteria) this;
        }

        public Criteria andCompletionTimeNotIn(List<Date> values) {
            addCriterion("completion_time not in", values, "completionTime");
            return (Criteria) this;
        }

        public Criteria andCompletionTimeBetween(Date value1, Date value2) {
            addCriterion("completion_time between", value1, value2, "completionTime");
            return (Criteria) this;
        }

        public Criteria andCompletionTimeNotBetween(Date value1, Date value2) {
            addCriterion("completion_time not between", value1, value2, "completionTime");
            return (Criteria) this;
        }

        public Criteria andProcessIsNull() {
            addCriterion("`process` is null");
            return (Criteria) this;
        }

        public Criteria andProcessIsNotNull() {
            addCriterion("`process` is not null");
            return (Criteria) this;
        }

        public Criteria andProcessEqualTo(String value) {
            addCriterion("`process` =", value, "process");
            return (Criteria) this;
        }

        public Criteria andProcessNotEqualTo(String value) {
            addCriterion("`process` <>", value, "process");
            return (Criteria) this;
        }

        public Criteria andProcessGreaterThan(String value) {
            addCriterion("`process` >", value, "process");
            return (Criteria) this;
        }

        public Criteria andProcessGreaterThanOrEqualTo(String value) {
            addCriterion("`process` >=", value, "process");
            return (Criteria) this;
        }

        public Criteria andProcessLessThan(String value) {
            addCriterion("`process` <", value, "process");
            return (Criteria) this;
        }

        public Criteria andProcessLessThanOrEqualTo(String value) {
            addCriterion("`process` <=", value, "process");
            return (Criteria) this;
        }

        public Criteria andProcessLike(String value) {
            addCriterion("`process` like", value, "process");
            return (Criteria) this;
        }

        public Criteria andProcessNotLike(String value) {
            addCriterion("`process` not like", value, "process");
            return (Criteria) this;
        }

        public Criteria andProcessIn(List<String> values) {
            addCriterion("`process` in", values, "process");
            return (Criteria) this;
        }

        public Criteria andProcessNotIn(List<String> values) {
            addCriterion("`process` not in", values, "process");
            return (Criteria) this;
        }

        public Criteria andProcessBetween(String value1, String value2) {
            addCriterion("`process` between", value1, value2, "process");
            return (Criteria) this;
        }

        public Criteria andProcessNotBetween(String value1, String value2) {
            addCriterion("`process` not between", value1, value2, "process");
            return (Criteria) this;
        }

        public Criteria andResultIsNull() {
            addCriterion("`result` is null");
            return (Criteria) this;
        }

        public Criteria andResultIsNotNull() {
            addCriterion("`result` is not null");
            return (Criteria) this;
        }

        public Criteria andResultEqualTo(String value) {
            addCriterion("`result` =", value, "result");
            return (Criteria) this;
        }

        public Criteria andResultNotEqualTo(String value) {
            addCriterion("`result` <>", value, "result");
            return (Criteria) this;
        }

        public Criteria andResultGreaterThan(String value) {
            addCriterion("`result` >", value, "result");
            return (Criteria) this;
        }

        public Criteria andResultGreaterThanOrEqualTo(String value) {
            addCriterion("`result` >=", value, "result");
            return (Criteria) this;
        }

        public Criteria andResultLessThan(String value) {
            addCriterion("`result` <", value, "result");
            return (Criteria) this;
        }

        public Criteria andResultLessThanOrEqualTo(String value) {
            addCriterion("`result` <=", value, "result");
            return (Criteria) this;
        }

        public Criteria andResultLike(String value) {
            addCriterion("`result` like", value, "result");
            return (Criteria) this;
        }

        public Criteria andResultNotLike(String value) {
            addCriterion("`result` not like", value, "result");
            return (Criteria) this;
        }

        public Criteria andResultIn(List<String> values) {
            addCriterion("`result` in", values, "result");
            return (Criteria) this;
        }

        public Criteria andResultNotIn(List<String> values) {
            addCriterion("`result` not in", values, "result");
            return (Criteria) this;
        }

        public Criteria andResultBetween(String value1, String value2) {
            addCriterion("`result` between", value1, value2, "result");
            return (Criteria) this;
        }

        public Criteria andResultNotBetween(String value1, String value2) {
            addCriterion("`result` not between", value1, value2, "result");
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

        public Criteria andShowHandtagIsNull() {
            addCriterion("show_handtag is null");
            return (Criteria) this;
        }

        public Criteria andShowHandtagIsNotNull() {
            addCriterion("show_handtag is not null");
            return (Criteria) this;
        }

        public Criteria andShowHandtagEqualTo(Integer value) {
            addCriterion("show_handtag =", value, "showHandtag");
            return (Criteria) this;
        }

        public Criteria andShowHandtagNotEqualTo(Integer value) {
            addCriterion("show_handtag <>", value, "showHandtag");
            return (Criteria) this;
        }

        public Criteria andShowHandtagGreaterThan(Integer value) {
            addCriterion("show_handtag >", value, "showHandtag");
            return (Criteria) this;
        }

        public Criteria andShowHandtagGreaterThanOrEqualTo(Integer value) {
            addCriterion("show_handtag >=", value, "showHandtag");
            return (Criteria) this;
        }

        public Criteria andShowHandtagLessThan(Integer value) {
            addCriterion("show_handtag <", value, "showHandtag");
            return (Criteria) this;
        }

        public Criteria andShowHandtagLessThanOrEqualTo(Integer value) {
            addCriterion("show_handtag <=", value, "showHandtag");
            return (Criteria) this;
        }

        public Criteria andShowHandtagIn(List<Integer> values) {
            addCriterion("show_handtag in", values, "showHandtag");
            return (Criteria) this;
        }

        public Criteria andShowHandtagNotIn(List<Integer> values) {
            addCriterion("show_handtag not in", values, "showHandtag");
            return (Criteria) this;
        }

        public Criteria andShowHandtagBetween(Integer value1, Integer value2) {
            addCriterion("show_handtag between", value1, value2, "showHandtag");
            return (Criteria) this;
        }

        public Criteria andShowHandtagNotBetween(Integer value1, Integer value2) {
            addCriterion("show_handtag not between", value1, value2, "showHandtag");
            return (Criteria) this;
        }

        public Criteria andHandtagIsNull() {
            addCriterion("handtag is null");
            return (Criteria) this;
        }

        public Criteria andHandtagIsNotNull() {
            addCriterion("handtag is not null");
            return (Criteria) this;
        }

        public Criteria andHandtagEqualTo(String value) {
            addCriterion("handtag =", value, "handtag");
            return (Criteria) this;
        }

        public Criteria andHandtagNotEqualTo(String value) {
            addCriterion("handtag <>", value, "handtag");
            return (Criteria) this;
        }

        public Criteria andHandtagGreaterThan(String value) {
            addCriterion("handtag >", value, "handtag");
            return (Criteria) this;
        }

        public Criteria andHandtagGreaterThanOrEqualTo(String value) {
            addCriterion("handtag >=", value, "handtag");
            return (Criteria) this;
        }

        public Criteria andHandtagLessThan(String value) {
            addCriterion("handtag <", value, "handtag");
            return (Criteria) this;
        }

        public Criteria andHandtagLessThanOrEqualTo(String value) {
            addCriterion("handtag <=", value, "handtag");
            return (Criteria) this;
        }

        public Criteria andHandtagLike(String value) {
            addCriterion("handtag like", value, "handtag");
            return (Criteria) this;
        }

        public Criteria andHandtagNotLike(String value) {
            addCriterion("handtag not like", value, "handtag");
            return (Criteria) this;
        }

        public Criteria andHandtagIn(List<String> values) {
            addCriterion("handtag in", values, "handtag");
            return (Criteria) this;
        }

        public Criteria andHandtagNotIn(List<String> values) {
            addCriterion("handtag not in", values, "handtag");
            return (Criteria) this;
        }

        public Criteria andHandtagBetween(String value1, String value2) {
            addCriterion("handtag between", value1, value2, "handtag");
            return (Criteria) this;
        }

        public Criteria andHandtagNotBetween(String value1, String value2) {
            addCriterion("handtag not between", value1, value2, "handtag");
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