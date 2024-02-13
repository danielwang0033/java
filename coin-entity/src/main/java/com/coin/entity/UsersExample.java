package com.coin.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
public class UsersExample {
    @Setter
    protected String orderByClause;

    @Setter
    protected boolean distinct;

    protected final List<Criteria> oredCriteria;

    public UsersExample() {
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

        public Criteria andNameIsNull() {
            addCriterion("`name` is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("`name` is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("`name` =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("`name` <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("`name` >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("`name` >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("`name` <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("`name` <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("`name` like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("`name` not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("`name` in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("`name` not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("`name` between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("`name` not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andEmailIsNull() {
            addCriterion("email is null");
            return (Criteria) this;
        }

        public Criteria andEmailIsNotNull() {
            addCriterion("email is not null");
            return (Criteria) this;
        }

        public Criteria andEmailEqualTo(String value) {
            addCriterion("email =", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotEqualTo(String value) {
            addCriterion("email <>", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThan(String value) {
            addCriterion("email >", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThanOrEqualTo(String value) {
            addCriterion("email >=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThan(String value) {
            addCriterion("email <", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThanOrEqualTo(String value) {
            addCriterion("email <=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLike(String value) {
            addCriterion("email like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotLike(String value) {
            addCriterion("email not like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailIn(List<String> values) {
            addCriterion("email in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotIn(List<String> values) {
            addCriterion("email not in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailBetween(String value1, String value2) {
            addCriterion("email between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotBetween(String value1, String value2) {
            addCriterion("email not between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andEmailModifiedAtIsNull() {
            addCriterion("email_modified_at is null");
            return (Criteria) this;
        }

        public Criteria andEmailModifiedAtIsNotNull() {
            addCriterion("email_modified_at is not null");
            return (Criteria) this;
        }

        public Criteria andEmailModifiedAtEqualTo(Date value) {
            addCriterion("email_modified_at =", value, "emailModifiedAt");
            return (Criteria) this;
        }

        public Criteria andEmailModifiedAtNotEqualTo(Date value) {
            addCriterion("email_modified_at <>", value, "emailModifiedAt");
            return (Criteria) this;
        }

        public Criteria andEmailModifiedAtGreaterThan(Date value) {
            addCriterion("email_modified_at >", value, "emailModifiedAt");
            return (Criteria) this;
        }

        public Criteria andEmailModifiedAtGreaterThanOrEqualTo(Date value) {
            addCriterion("email_modified_at >=", value, "emailModifiedAt");
            return (Criteria) this;
        }

        public Criteria andEmailModifiedAtLessThan(Date value) {
            addCriterion("email_modified_at <", value, "emailModifiedAt");
            return (Criteria) this;
        }

        public Criteria andEmailModifiedAtLessThanOrEqualTo(Date value) {
            addCriterion("email_modified_at <=", value, "emailModifiedAt");
            return (Criteria) this;
        }

        public Criteria andEmailModifiedAtIn(List<Date> values) {
            addCriterion("email_modified_at in", values, "emailModifiedAt");
            return (Criteria) this;
        }

        public Criteria andEmailModifiedAtNotIn(List<Date> values) {
            addCriterion("email_modified_at not in", values, "emailModifiedAt");
            return (Criteria) this;
        }

        public Criteria andEmailModifiedAtBetween(Date value1, Date value2) {
            addCriterion("email_modified_at between", value1, value2, "emailModifiedAt");
            return (Criteria) this;
        }

        public Criteria andEmailModifiedAtNotBetween(Date value1, Date value2) {
            addCriterion("email_modified_at not between", value1, value2, "emailModifiedAt");
            return (Criteria) this;
        }

        public Criteria andEmailVerifiedAtIsNull() {
            addCriterion("email_verified_at is null");
            return (Criteria) this;
        }

        public Criteria andEmailVerifiedAtIsNotNull() {
            addCriterion("email_verified_at is not null");
            return (Criteria) this;
        }

        public Criteria andEmailVerifiedAtEqualTo(Date value) {
            addCriterion("email_verified_at =", value, "emailVerifiedAt");
            return (Criteria) this;
        }

        public Criteria andEmailVerifiedAtNotEqualTo(Date value) {
            addCriterion("email_verified_at <>", value, "emailVerifiedAt");
            return (Criteria) this;
        }

        public Criteria andEmailVerifiedAtGreaterThan(Date value) {
            addCriterion("email_verified_at >", value, "emailVerifiedAt");
            return (Criteria) this;
        }

        public Criteria andEmailVerifiedAtGreaterThanOrEqualTo(Date value) {
            addCriterion("email_verified_at >=", value, "emailVerifiedAt");
            return (Criteria) this;
        }

        public Criteria andEmailVerifiedAtLessThan(Date value) {
            addCriterion("email_verified_at <", value, "emailVerifiedAt");
            return (Criteria) this;
        }

        public Criteria andEmailVerifiedAtLessThanOrEqualTo(Date value) {
            addCriterion("email_verified_at <=", value, "emailVerifiedAt");
            return (Criteria) this;
        }

        public Criteria andEmailVerifiedAtIn(List<Date> values) {
            addCriterion("email_verified_at in", values, "emailVerifiedAt");
            return (Criteria) this;
        }

        public Criteria andEmailVerifiedAtNotIn(List<Date> values) {
            addCriterion("email_verified_at not in", values, "emailVerifiedAt");
            return (Criteria) this;
        }

        public Criteria andEmailVerifiedAtBetween(Date value1, Date value2) {
            addCriterion("email_verified_at between", value1, value2, "emailVerifiedAt");
            return (Criteria) this;
        }

        public Criteria andEmailVerifiedAtNotBetween(Date value1, Date value2) {
            addCriterion("email_verified_at not between", value1, value2, "emailVerifiedAt");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNull() {
            addCriterion("`password` is null");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNotNull() {
            addCriterion("`password` is not null");
            return (Criteria) this;
        }

        public Criteria andPasswordEqualTo(String value) {
            addCriterion("`password` =", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotEqualTo(String value) {
            addCriterion("`password` <>", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThan(String value) {
            addCriterion("`password` >", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThanOrEqualTo(String value) {
            addCriterion("`password` >=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThan(String value) {
            addCriterion("`password` <", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThanOrEqualTo(String value) {
            addCriterion("`password` <=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLike(String value) {
            addCriterion("`password` like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotLike(String value) {
            addCriterion("`password` not like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordIn(List<String> values) {
            addCriterion("`password` in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotIn(List<String> values) {
            addCriterion("`password` not in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordBetween(String value1, String value2) {
            addCriterion("`password` between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotBetween(String value1, String value2) {
            addCriterion("`password` not between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andIsBanedIsNull() {
            addCriterion("is_baned is null");
            return (Criteria) this;
        }

        public Criteria andIsBanedIsNotNull() {
            addCriterion("is_baned is not null");
            return (Criteria) this;
        }

        public Criteria andIsBanedEqualTo(Integer value) {
            addCriterion("is_baned =", value, "isBaned");
            return (Criteria) this;
        }

        public Criteria andIsBanedNotEqualTo(Integer value) {
            addCriterion("is_baned <>", value, "isBaned");
            return (Criteria) this;
        }

        public Criteria andIsBanedGreaterThan(Integer value) {
            addCriterion("is_baned >", value, "isBaned");
            return (Criteria) this;
        }

        public Criteria andIsBanedGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_baned >=", value, "isBaned");
            return (Criteria) this;
        }

        public Criteria andIsBanedLessThan(Integer value) {
            addCriterion("is_baned <", value, "isBaned");
            return (Criteria) this;
        }

        public Criteria andIsBanedLessThanOrEqualTo(Integer value) {
            addCriterion("is_baned <=", value, "isBaned");
            return (Criteria) this;
        }

        public Criteria andIsBanedIn(List<Integer> values) {
            addCriterion("is_baned in", values, "isBaned");
            return (Criteria) this;
        }

        public Criteria andIsBanedNotIn(List<Integer> values) {
            addCriterion("is_baned not in", values, "isBaned");
            return (Criteria) this;
        }

        public Criteria andIsBanedBetween(Integer value1, Integer value2) {
            addCriterion("is_baned between", value1, value2, "isBaned");
            return (Criteria) this;
        }

        public Criteria andIsBanedNotBetween(Integer value1, Integer value2) {
            addCriterion("is_baned not between", value1, value2, "isBaned");
            return (Criteria) this;
        }

        public Criteria andExpIsNull() {
            addCriterion("`exp` is null");
            return (Criteria) this;
        }

        public Criteria andExpIsNotNull() {
            addCriterion("`exp` is not null");
            return (Criteria) this;
        }

        public Criteria andExpEqualTo(Integer value) {
            addCriterion("`exp` =", value, "exp");
            return (Criteria) this;
        }

        public Criteria andExpNotEqualTo(Integer value) {
            addCriterion("`exp` <>", value, "exp");
            return (Criteria) this;
        }

        public Criteria andExpGreaterThan(Integer value) {
            addCriterion("`exp` >", value, "exp");
            return (Criteria) this;
        }

        public Criteria andExpGreaterThanOrEqualTo(Integer value) {
            addCriterion("`exp` >=", value, "exp");
            return (Criteria) this;
        }

        public Criteria andExpLessThan(Integer value) {
            addCriterion("`exp` <", value, "exp");
            return (Criteria) this;
        }

        public Criteria andExpLessThanOrEqualTo(Integer value) {
            addCriterion("`exp` <=", value, "exp");
            return (Criteria) this;
        }

        public Criteria andExpIn(List<Integer> values) {
            addCriterion("`exp` in", values, "exp");
            return (Criteria) this;
        }

        public Criteria andExpNotIn(List<Integer> values) {
            addCriterion("`exp` not in", values, "exp");
            return (Criteria) this;
        }

        public Criteria andExpBetween(Integer value1, Integer value2) {
            addCriterion("`exp` between", value1, value2, "exp");
            return (Criteria) this;
        }

        public Criteria andExpNotBetween(Integer value1, Integer value2) {
            addCriterion("`exp` not between", value1, value2, "exp");
            return (Criteria) this;
        }

        public Criteria andIsForumAdminIsNull() {
            addCriterion("is_forum_admin is null");
            return (Criteria) this;
        }

        public Criteria andIsForumAdminIsNotNull() {
            addCriterion("is_forum_admin is not null");
            return (Criteria) this;
        }

        public Criteria andIsForumAdminEqualTo(Integer value) {
            addCriterion("is_forum_admin =", value, "isForumAdmin");
            return (Criteria) this;
        }

        public Criteria andIsForumAdminNotEqualTo(Integer value) {
            addCriterion("is_forum_admin <>", value, "isForumAdmin");
            return (Criteria) this;
        }

        public Criteria andIsForumAdminGreaterThan(Integer value) {
            addCriterion("is_forum_admin >", value, "isForumAdmin");
            return (Criteria) this;
        }

        public Criteria andIsForumAdminGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_forum_admin >=", value, "isForumAdmin");
            return (Criteria) this;
        }

        public Criteria andIsForumAdminLessThan(Integer value) {
            addCriterion("is_forum_admin <", value, "isForumAdmin");
            return (Criteria) this;
        }

        public Criteria andIsForumAdminLessThanOrEqualTo(Integer value) {
            addCriterion("is_forum_admin <=", value, "isForumAdmin");
            return (Criteria) this;
        }

        public Criteria andIsForumAdminIn(List<Integer> values) {
            addCriterion("is_forum_admin in", values, "isForumAdmin");
            return (Criteria) this;
        }

        public Criteria andIsForumAdminNotIn(List<Integer> values) {
            addCriterion("is_forum_admin not in", values, "isForumAdmin");
            return (Criteria) this;
        }

        public Criteria andIsForumAdminBetween(Integer value1, Integer value2) {
            addCriterion("is_forum_admin between", value1, value2, "isForumAdmin");
            return (Criteria) this;
        }

        public Criteria andIsForumAdminNotBetween(Integer value1, Integer value2) {
            addCriterion("is_forum_admin not between", value1, value2, "isForumAdmin");
            return (Criteria) this;
        }

        public Criteria andIsSuperAdminIsNull() {
            addCriterion("is_super_admin is null");
            return (Criteria) this;
        }

        public Criteria andIsSuperAdminIsNotNull() {
            addCriterion("is_super_admin is not null");
            return (Criteria) this;
        }

        public Criteria andIsSuperAdminEqualTo(Integer value) {
            addCriterion("is_super_admin =", value, "isSuperAdmin");
            return (Criteria) this;
        }

        public Criteria andIsSuperAdminNotEqualTo(Integer value) {
            addCriterion("is_super_admin <>", value, "isSuperAdmin");
            return (Criteria) this;
        }

        public Criteria andIsSuperAdminGreaterThan(Integer value) {
            addCriterion("is_super_admin >", value, "isSuperAdmin");
            return (Criteria) this;
        }

        public Criteria andIsSuperAdminGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_super_admin >=", value, "isSuperAdmin");
            return (Criteria) this;
        }

        public Criteria andIsSuperAdminLessThan(Integer value) {
            addCriterion("is_super_admin <", value, "isSuperAdmin");
            return (Criteria) this;
        }

        public Criteria andIsSuperAdminLessThanOrEqualTo(Integer value) {
            addCriterion("is_super_admin <=", value, "isSuperAdmin");
            return (Criteria) this;
        }

        public Criteria andIsSuperAdminIn(List<Integer> values) {
            addCriterion("is_super_admin in", values, "isSuperAdmin");
            return (Criteria) this;
        }

        public Criteria andIsSuperAdminNotIn(List<Integer> values) {
            addCriterion("is_super_admin not in", values, "isSuperAdmin");
            return (Criteria) this;
        }

        public Criteria andIsSuperAdminBetween(Integer value1, Integer value2) {
            addCriterion("is_super_admin between", value1, value2, "isSuperAdmin");
            return (Criteria) this;
        }

        public Criteria andIsSuperAdminNotBetween(Integer value1, Integer value2) {
            addCriterion("is_super_admin not between", value1, value2, "isSuperAdmin");
            return (Criteria) this;
        }

        public Criteria andIsBanPostIsNull() {
            addCriterion("is_ban_post is null");
            return (Criteria) this;
        }

        public Criteria andIsBanPostIsNotNull() {
            addCriterion("is_ban_post is not null");
            return (Criteria) this;
        }

        public Criteria andIsBanPostEqualTo(Integer value) {
            addCriterion("is_ban_post =", value, "isBanPost");
            return (Criteria) this;
        }

        public Criteria andIsBanPostNotEqualTo(Integer value) {
            addCriterion("is_ban_post <>", value, "isBanPost");
            return (Criteria) this;
        }

        public Criteria andIsBanPostGreaterThan(Integer value) {
            addCriterion("is_ban_post >", value, "isBanPost");
            return (Criteria) this;
        }

        public Criteria andIsBanPostGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_ban_post >=", value, "isBanPost");
            return (Criteria) this;
        }

        public Criteria andIsBanPostLessThan(Integer value) {
            addCriterion("is_ban_post <", value, "isBanPost");
            return (Criteria) this;
        }

        public Criteria andIsBanPostLessThanOrEqualTo(Integer value) {
            addCriterion("is_ban_post <=", value, "isBanPost");
            return (Criteria) this;
        }

        public Criteria andIsBanPostIn(List<Integer> values) {
            addCriterion("is_ban_post in", values, "isBanPost");
            return (Criteria) this;
        }

        public Criteria andIsBanPostNotIn(List<Integer> values) {
            addCriterion("is_ban_post not in", values, "isBanPost");
            return (Criteria) this;
        }

        public Criteria andIsBanPostBetween(Integer value1, Integer value2) {
            addCriterion("is_ban_post between", value1, value2, "isBanPost");
            return (Criteria) this;
        }

        public Criteria andIsBanPostNotBetween(Integer value1, Integer value2) {
            addCriterion("is_ban_post not between", value1, value2, "isBanPost");
            return (Criteria) this;
        }

        public Criteria andIsBanForumIsNull() {
            addCriterion("is_ban_forum is null");
            return (Criteria) this;
        }

        public Criteria andIsBanForumIsNotNull() {
            addCriterion("is_ban_forum is not null");
            return (Criteria) this;
        }

        public Criteria andIsBanForumEqualTo(Integer value) {
            addCriterion("is_ban_forum =", value, "isBanForum");
            return (Criteria) this;
        }

        public Criteria andIsBanForumNotEqualTo(Integer value) {
            addCriterion("is_ban_forum <>", value, "isBanForum");
            return (Criteria) this;
        }

        public Criteria andIsBanForumGreaterThan(Integer value) {
            addCriterion("is_ban_forum >", value, "isBanForum");
            return (Criteria) this;
        }

        public Criteria andIsBanForumGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_ban_forum >=", value, "isBanForum");
            return (Criteria) this;
        }

        public Criteria andIsBanForumLessThan(Integer value) {
            addCriterion("is_ban_forum <", value, "isBanForum");
            return (Criteria) this;
        }

        public Criteria andIsBanForumLessThanOrEqualTo(Integer value) {
            addCriterion("is_ban_forum <=", value, "isBanForum");
            return (Criteria) this;
        }

        public Criteria andIsBanForumIn(List<Integer> values) {
            addCriterion("is_ban_forum in", values, "isBanForum");
            return (Criteria) this;
        }

        public Criteria andIsBanForumNotIn(List<Integer> values) {
            addCriterion("is_ban_forum not in", values, "isBanForum");
            return (Criteria) this;
        }

        public Criteria andIsBanForumBetween(Integer value1, Integer value2) {
            addCriterion("is_ban_forum between", value1, value2, "isBanForum");
            return (Criteria) this;
        }

        public Criteria andIsBanForumNotBetween(Integer value1, Integer value2) {
            addCriterion("is_ban_forum not between", value1, value2, "isBanForum");
            return (Criteria) this;
        }

        public Criteria andRocketUsernameIsNull() {
            addCriterion("rocket_username is null");
            return (Criteria) this;
        }

        public Criteria andRocketUsernameIsNotNull() {
            addCriterion("rocket_username is not null");
            return (Criteria) this;
        }

        public Criteria andRocketUsernameEqualTo(String value) {
            addCriterion("rocket_username =", value, "rocketUsername");
            return (Criteria) this;
        }

        public Criteria andRocketUsernameNotEqualTo(String value) {
            addCriterion("rocket_username <>", value, "rocketUsername");
            return (Criteria) this;
        }

        public Criteria andRocketUsernameGreaterThan(String value) {
            addCriterion("rocket_username >", value, "rocketUsername");
            return (Criteria) this;
        }

        public Criteria andRocketUsernameGreaterThanOrEqualTo(String value) {
            addCriterion("rocket_username >=", value, "rocketUsername");
            return (Criteria) this;
        }

        public Criteria andRocketUsernameLessThan(String value) {
            addCriterion("rocket_username <", value, "rocketUsername");
            return (Criteria) this;
        }

        public Criteria andRocketUsernameLessThanOrEqualTo(String value) {
            addCriterion("rocket_username <=", value, "rocketUsername");
            return (Criteria) this;
        }

        public Criteria andRocketUsernameLike(String value) {
            addCriterion("rocket_username like", value, "rocketUsername");
            return (Criteria) this;
        }

        public Criteria andRocketUsernameNotLike(String value) {
            addCriterion("rocket_username not like", value, "rocketUsername");
            return (Criteria) this;
        }

        public Criteria andRocketUsernameIn(List<String> values) {
            addCriterion("rocket_username in", values, "rocketUsername");
            return (Criteria) this;
        }

        public Criteria andRocketUsernameNotIn(List<String> values) {
            addCriterion("rocket_username not in", values, "rocketUsername");
            return (Criteria) this;
        }

        public Criteria andRocketUsernameBetween(String value1, String value2) {
            addCriterion("rocket_username between", value1, value2, "rocketUsername");
            return (Criteria) this;
        }

        public Criteria andRocketUsernameNotBetween(String value1, String value2) {
            addCriterion("rocket_username not between", value1, value2, "rocketUsername");
            return (Criteria) this;
        }

        public Criteria andRocketUserLoginTokenIsNull() {
            addCriterion("rocket_user_login_token is null");
            return (Criteria) this;
        }

        public Criteria andRocketUserLoginTokenIsNotNull() {
            addCriterion("rocket_user_login_token is not null");
            return (Criteria) this;
        }

        public Criteria andRocketUserLoginTokenEqualTo(String value) {
            addCriterion("rocket_user_login_token =", value, "rocketUserLoginToken");
            return (Criteria) this;
        }

        public Criteria andRocketUserLoginTokenNotEqualTo(String value) {
            addCriterion("rocket_user_login_token <>", value, "rocketUserLoginToken");
            return (Criteria) this;
        }

        public Criteria andRocketUserLoginTokenGreaterThan(String value) {
            addCriterion("rocket_user_login_token >", value, "rocketUserLoginToken");
            return (Criteria) this;
        }

        public Criteria andRocketUserLoginTokenGreaterThanOrEqualTo(String value) {
            addCriterion("rocket_user_login_token >=", value, "rocketUserLoginToken");
            return (Criteria) this;
        }

        public Criteria andRocketUserLoginTokenLessThan(String value) {
            addCriterion("rocket_user_login_token <", value, "rocketUserLoginToken");
            return (Criteria) this;
        }

        public Criteria andRocketUserLoginTokenLessThanOrEqualTo(String value) {
            addCriterion("rocket_user_login_token <=", value, "rocketUserLoginToken");
            return (Criteria) this;
        }

        public Criteria andRocketUserLoginTokenLike(String value) {
            addCriterion("rocket_user_login_token like", value, "rocketUserLoginToken");
            return (Criteria) this;
        }

        public Criteria andRocketUserLoginTokenNotLike(String value) {
            addCriterion("rocket_user_login_token not like", value, "rocketUserLoginToken");
            return (Criteria) this;
        }

        public Criteria andRocketUserLoginTokenIn(List<String> values) {
            addCriterion("rocket_user_login_token in", values, "rocketUserLoginToken");
            return (Criteria) this;
        }

        public Criteria andRocketUserLoginTokenNotIn(List<String> values) {
            addCriterion("rocket_user_login_token not in", values, "rocketUserLoginToken");
            return (Criteria) this;
        }

        public Criteria andRocketUserLoginTokenBetween(String value1, String value2) {
            addCriterion("rocket_user_login_token between", value1, value2, "rocketUserLoginToken");
            return (Criteria) this;
        }

        public Criteria andRocketUserLoginTokenNotBetween(String value1, String value2) {
            addCriterion("rocket_user_login_token not between", value1, value2, "rocketUserLoginToken");
            return (Criteria) this;
        }

        public Criteria andRocketUserIdIsNull() {
            addCriterion("rocket_user_id is null");
            return (Criteria) this;
        }

        public Criteria andRocketUserIdIsNotNull() {
            addCriterion("rocket_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andRocketUserIdEqualTo(String value) {
            addCriterion("rocket_user_id =", value, "rocketUserId");
            return (Criteria) this;
        }

        public Criteria andRocketUserIdNotEqualTo(String value) {
            addCriterion("rocket_user_id <>", value, "rocketUserId");
            return (Criteria) this;
        }

        public Criteria andRocketUserIdGreaterThan(String value) {
            addCriterion("rocket_user_id >", value, "rocketUserId");
            return (Criteria) this;
        }

        public Criteria andRocketUserIdGreaterThanOrEqualTo(String value) {
            addCriterion("rocket_user_id >=", value, "rocketUserId");
            return (Criteria) this;
        }

        public Criteria andRocketUserIdLessThan(String value) {
            addCriterion("rocket_user_id <", value, "rocketUserId");
            return (Criteria) this;
        }

        public Criteria andRocketUserIdLessThanOrEqualTo(String value) {
            addCriterion("rocket_user_id <=", value, "rocketUserId");
            return (Criteria) this;
        }

        public Criteria andRocketUserIdLike(String value) {
            addCriterion("rocket_user_id like", value, "rocketUserId");
            return (Criteria) this;
        }

        public Criteria andRocketUserIdNotLike(String value) {
            addCriterion("rocket_user_id not like", value, "rocketUserId");
            return (Criteria) this;
        }

        public Criteria andRocketUserIdIn(List<String> values) {
            addCriterion("rocket_user_id in", values, "rocketUserId");
            return (Criteria) this;
        }

        public Criteria andRocketUserIdNotIn(List<String> values) {
            addCriterion("rocket_user_id not in", values, "rocketUserId");
            return (Criteria) this;
        }

        public Criteria andRocketUserIdBetween(String value1, String value2) {
            addCriterion("rocket_user_id between", value1, value2, "rocketUserId");
            return (Criteria) this;
        }

        public Criteria andRocketUserIdNotBetween(String value1, String value2) {
            addCriterion("rocket_user_id not between", value1, value2, "rocketUserId");
            return (Criteria) this;
        }

        public Criteria andOnlineUntilIsNull() {
            addCriterion("online_until is null");
            return (Criteria) this;
        }

        public Criteria andOnlineUntilIsNotNull() {
            addCriterion("online_until is not null");
            return (Criteria) this;
        }

        public Criteria andOnlineUntilEqualTo(Date value) {
            addCriterion("online_until =", value, "onlineUntil");
            return (Criteria) this;
        }

        public Criteria andOnlineUntilNotEqualTo(Date value) {
            addCriterion("online_until <>", value, "onlineUntil");
            return (Criteria) this;
        }

        public Criteria andOnlineUntilGreaterThan(Date value) {
            addCriterion("online_until >", value, "onlineUntil");
            return (Criteria) this;
        }

        public Criteria andOnlineUntilGreaterThanOrEqualTo(Date value) {
            addCriterion("online_until >=", value, "onlineUntil");
            return (Criteria) this;
        }

        public Criteria andOnlineUntilLessThan(Date value) {
            addCriterion("online_until <", value, "onlineUntil");
            return (Criteria) this;
        }

        public Criteria andOnlineUntilLessThanOrEqualTo(Date value) {
            addCriterion("online_until <=", value, "onlineUntil");
            return (Criteria) this;
        }

        public Criteria andOnlineUntilIn(List<Date> values) {
            addCriterion("online_until in", values, "onlineUntil");
            return (Criteria) this;
        }

        public Criteria andOnlineUntilNotIn(List<Date> values) {
            addCriterion("online_until not in", values, "onlineUntil");
            return (Criteria) this;
        }

        public Criteria andOnlineUntilBetween(Date value1, Date value2) {
            addCriterion("online_until between", value1, value2, "onlineUntil");
            return (Criteria) this;
        }

        public Criteria andOnlineUntilNotBetween(Date value1, Date value2) {
            addCriterion("online_until not between", value1, value2, "onlineUntil");
            return (Criteria) this;
        }

        public Criteria andLastLoginDateIsNull() {
            addCriterion("last_login_date is null");
            return (Criteria) this;
        }

        public Criteria andLastLoginDateIsNotNull() {
            addCriterion("last_login_date is not null");
            return (Criteria) this;
        }

        public Criteria andLastLoginDateEqualTo(Date value) {
            addCriterion("last_login_date =", value, "lastLoginDate");
            return (Criteria) this;
        }

        public Criteria andLastLoginDateNotEqualTo(Date value) {
            addCriterion("last_login_date <>", value, "lastLoginDate");
            return (Criteria) this;
        }

        public Criteria andLastLoginDateGreaterThan(Date value) {
            addCriterion("last_login_date >", value, "lastLoginDate");
            return (Criteria) this;
        }

        public Criteria andLastLoginDateGreaterThanOrEqualTo(Date value) {
            addCriterion("last_login_date >=", value, "lastLoginDate");
            return (Criteria) this;
        }

        public Criteria andLastLoginDateLessThan(Date value) {
            addCriterion("last_login_date <", value, "lastLoginDate");
            return (Criteria) this;
        }

        public Criteria andLastLoginDateLessThanOrEqualTo(Date value) {
            addCriterion("last_login_date <=", value, "lastLoginDate");
            return (Criteria) this;
        }

        public Criteria andLastLoginDateIn(List<Date> values) {
            addCriterion("last_login_date in", values, "lastLoginDate");
            return (Criteria) this;
        }

        public Criteria andLastLoginDateNotIn(List<Date> values) {
            addCriterion("last_login_date not in", values, "lastLoginDate");
            return (Criteria) this;
        }

        public Criteria andLastLoginDateBetween(Date value1, Date value2) {
            addCriterion("last_login_date between", value1, value2, "lastLoginDate");
            return (Criteria) this;
        }

        public Criteria andLastLoginDateNotBetween(Date value1, Date value2) {
            addCriterion("last_login_date not between", value1, value2, "lastLoginDate");
            return (Criteria) this;
        }

        public Criteria andBioIsNull() {
            addCriterion("bio is null");
            return (Criteria) this;
        }

        public Criteria andBioIsNotNull() {
            addCriterion("bio is not null");
            return (Criteria) this;
        }

        public Criteria andBioEqualTo(String value) {
            addCriterion("bio =", value, "bio");
            return (Criteria) this;
        }

        public Criteria andBioNotEqualTo(String value) {
            addCriterion("bio <>", value, "bio");
            return (Criteria) this;
        }

        public Criteria andBioGreaterThan(String value) {
            addCriterion("bio >", value, "bio");
            return (Criteria) this;
        }

        public Criteria andBioGreaterThanOrEqualTo(String value) {
            addCriterion("bio >=", value, "bio");
            return (Criteria) this;
        }

        public Criteria andBioLessThan(String value) {
            addCriterion("bio <", value, "bio");
            return (Criteria) this;
        }

        public Criteria andBioLessThanOrEqualTo(String value) {
            addCriterion("bio <=", value, "bio");
            return (Criteria) this;
        }

        public Criteria andBioLike(String value) {
            addCriterion("bio like", value, "bio");
            return (Criteria) this;
        }

        public Criteria andBioNotLike(String value) {
            addCriterion("bio not like", value, "bio");
            return (Criteria) this;
        }

        public Criteria andBioIn(List<String> values) {
            addCriterion("bio in", values, "bio");
            return (Criteria) this;
        }

        public Criteria andBioNotIn(List<String> values) {
            addCriterion("bio not in", values, "bio");
            return (Criteria) this;
        }

        public Criteria andBioBetween(String value1, String value2) {
            addCriterion("bio between", value1, value2, "bio");
            return (Criteria) this;
        }

        public Criteria andBioNotBetween(String value1, String value2) {
            addCriterion("bio not between", value1, value2, "bio");
            return (Criteria) this;
        }

        public Criteria andAvatarIsNull() {
            addCriterion("avatar is null");
            return (Criteria) this;
        }

        public Criteria andAvatarIsNotNull() {
            addCriterion("avatar is not null");
            return (Criteria) this;
        }

        public Criteria andAvatarEqualTo(String value) {
            addCriterion("avatar =", value, "avatar");
            return (Criteria) this;
        }

        public Criteria andAvatarNotEqualTo(String value) {
            addCriterion("avatar <>", value, "avatar");
            return (Criteria) this;
        }

        public Criteria andAvatarGreaterThan(String value) {
            addCriterion("avatar >", value, "avatar");
            return (Criteria) this;
        }

        public Criteria andAvatarGreaterThanOrEqualTo(String value) {
            addCriterion("avatar >=", value, "avatar");
            return (Criteria) this;
        }

        public Criteria andAvatarLessThan(String value) {
            addCriterion("avatar <", value, "avatar");
            return (Criteria) this;
        }

        public Criteria andAvatarLessThanOrEqualTo(String value) {
            addCriterion("avatar <=", value, "avatar");
            return (Criteria) this;
        }

        public Criteria andAvatarLike(String value) {
            addCriterion("avatar like", value, "avatar");
            return (Criteria) this;
        }

        public Criteria andAvatarNotLike(String value) {
            addCriterion("avatar not like", value, "avatar");
            return (Criteria) this;
        }

        public Criteria andAvatarIn(List<String> values) {
            addCriterion("avatar in", values, "avatar");
            return (Criteria) this;
        }

        public Criteria andAvatarNotIn(List<String> values) {
            addCriterion("avatar not in", values, "avatar");
            return (Criteria) this;
        }

        public Criteria andAvatarBetween(String value1, String value2) {
            addCriterion("avatar between", value1, value2, "avatar");
            return (Criteria) this;
        }

        public Criteria andAvatarNotBetween(String value1, String value2) {
            addCriterion("avatar not between", value1, value2, "avatar");
            return (Criteria) this;
        }

        public Criteria andRememberTokenIsNull() {
            addCriterion("remember_token is null");
            return (Criteria) this;
        }

        public Criteria andRememberTokenIsNotNull() {
            addCriterion("remember_token is not null");
            return (Criteria) this;
        }

        public Criteria andRememberTokenEqualTo(String value) {
            addCriterion("remember_token =", value, "rememberToken");
            return (Criteria) this;
        }

        public Criteria andRememberTokenNotEqualTo(String value) {
            addCriterion("remember_token <>", value, "rememberToken");
            return (Criteria) this;
        }

        public Criteria andRememberTokenGreaterThan(String value) {
            addCriterion("remember_token >", value, "rememberToken");
            return (Criteria) this;
        }

        public Criteria andRememberTokenGreaterThanOrEqualTo(String value) {
            addCriterion("remember_token >=", value, "rememberToken");
            return (Criteria) this;
        }

        public Criteria andRememberTokenLessThan(String value) {
            addCriterion("remember_token <", value, "rememberToken");
            return (Criteria) this;
        }

        public Criteria andRememberTokenLessThanOrEqualTo(String value) {
            addCriterion("remember_token <=", value, "rememberToken");
            return (Criteria) this;
        }

        public Criteria andRememberTokenLike(String value) {
            addCriterion("remember_token like", value, "rememberToken");
            return (Criteria) this;
        }

        public Criteria andRememberTokenNotLike(String value) {
            addCriterion("remember_token not like", value, "rememberToken");
            return (Criteria) this;
        }

        public Criteria andRememberTokenIn(List<String> values) {
            addCriterion("remember_token in", values, "rememberToken");
            return (Criteria) this;
        }

        public Criteria andRememberTokenNotIn(List<String> values) {
            addCriterion("remember_token not in", values, "rememberToken");
            return (Criteria) this;
        }

        public Criteria andRememberTokenBetween(String value1, String value2) {
            addCriterion("remember_token between", value1, value2, "rememberToken");
            return (Criteria) this;
        }

        public Criteria andRememberTokenNotBetween(String value1, String value2) {
            addCriterion("remember_token not between", value1, value2, "rememberToken");
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

        public Criteria andQqIsNull() {
            addCriterion("qq is null");
            return (Criteria) this;
        }

        public Criteria andQqIsNotNull() {
            addCriterion("qq is not null");
            return (Criteria) this;
        }

        public Criteria andQqEqualTo(String value) {
            addCriterion("qq =", value, "qq");
            return (Criteria) this;
        }

        public Criteria andQqNotEqualTo(String value) {
            addCriterion("qq <>", value, "qq");
            return (Criteria) this;
        }

        public Criteria andQqGreaterThan(String value) {
            addCriterion("qq >", value, "qq");
            return (Criteria) this;
        }

        public Criteria andQqGreaterThanOrEqualTo(String value) {
            addCriterion("qq >=", value, "qq");
            return (Criteria) this;
        }

        public Criteria andQqLessThan(String value) {
            addCriterion("qq <", value, "qq");
            return (Criteria) this;
        }

        public Criteria andQqLessThanOrEqualTo(String value) {
            addCriterion("qq <=", value, "qq");
            return (Criteria) this;
        }

        public Criteria andQqLike(String value) {
            addCriterion("qq like", value, "qq");
            return (Criteria) this;
        }

        public Criteria andQqNotLike(String value) {
            addCriterion("qq not like", value, "qq");
            return (Criteria) this;
        }

        public Criteria andQqIn(List<String> values) {
            addCriterion("qq in", values, "qq");
            return (Criteria) this;
        }

        public Criteria andQqNotIn(List<String> values) {
            addCriterion("qq not in", values, "qq");
            return (Criteria) this;
        }

        public Criteria andQqBetween(String value1, String value2) {
            addCriterion("qq between", value1, value2, "qq");
            return (Criteria) this;
        }

        public Criteria andQqNotBetween(String value1, String value2) {
            addCriterion("qq not between", value1, value2, "qq");
            return (Criteria) this;
        }

        public Criteria andWechatIsNull() {
            addCriterion("wechat is null");
            return (Criteria) this;
        }

        public Criteria andWechatIsNotNull() {
            addCriterion("wechat is not null");
            return (Criteria) this;
        }

        public Criteria andWechatEqualTo(String value) {
            addCriterion("wechat =", value, "wechat");
            return (Criteria) this;
        }

        public Criteria andWechatNotEqualTo(String value) {
            addCriterion("wechat <>", value, "wechat");
            return (Criteria) this;
        }

        public Criteria andWechatGreaterThan(String value) {
            addCriterion("wechat >", value, "wechat");
            return (Criteria) this;
        }

        public Criteria andWechatGreaterThanOrEqualTo(String value) {
            addCriterion("wechat >=", value, "wechat");
            return (Criteria) this;
        }

        public Criteria andWechatLessThan(String value) {
            addCriterion("wechat <", value, "wechat");
            return (Criteria) this;
        }

        public Criteria andWechatLessThanOrEqualTo(String value) {
            addCriterion("wechat <=", value, "wechat");
            return (Criteria) this;
        }

        public Criteria andWechatLike(String value) {
            addCriterion("wechat like", value, "wechat");
            return (Criteria) this;
        }

        public Criteria andWechatNotLike(String value) {
            addCriterion("wechat not like", value, "wechat");
            return (Criteria) this;
        }

        public Criteria andWechatIn(List<String> values) {
            addCriterion("wechat in", values, "wechat");
            return (Criteria) this;
        }

        public Criteria andWechatNotIn(List<String> values) {
            addCriterion("wechat not in", values, "wechat");
            return (Criteria) this;
        }

        public Criteria andWechatBetween(String value1, String value2) {
            addCriterion("wechat between", value1, value2, "wechat");
            return (Criteria) this;
        }

        public Criteria andWechatNotBetween(String value1, String value2) {
            addCriterion("wechat not between", value1, value2, "wechat");
            return (Criteria) this;
        }

        public Criteria andPhoneIsNull() {
            addCriterion("phone is null");
            return (Criteria) this;
        }

        public Criteria andPhoneIsNotNull() {
            addCriterion("phone is not null");
            return (Criteria) this;
        }

        public Criteria andPhoneEqualTo(String value) {
            addCriterion("phone =", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotEqualTo(String value) {
            addCriterion("phone <>", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThan(String value) {
            addCriterion("phone >", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("phone >=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThan(String value) {
            addCriterion("phone <", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThanOrEqualTo(String value) {
            addCriterion("phone <=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLike(String value) {
            addCriterion("phone like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotLike(String value) {
            addCriterion("phone not like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneIn(List<String> values) {
            addCriterion("phone in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotIn(List<String> values) {
            addCriterion("phone not in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneBetween(String value1, String value2) {
            addCriterion("phone between", value1, value2, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotBetween(String value1, String value2) {
            addCriterion("phone not between", value1, value2, "phone");
            return (Criteria) this;
        }

        public Criteria andPwdIsNull() {
            addCriterion("pwd is null");
            return (Criteria) this;
        }

        public Criteria andPwdIsNotNull() {
            addCriterion("pwd is not null");
            return (Criteria) this;
        }

        public Criteria andPwdEqualTo(String value) {
            addCriterion("pwd =", value, "pwd");
            return (Criteria) this;
        }

        public Criteria andPwdNotEqualTo(String value) {
            addCriterion("pwd <>", value, "pwd");
            return (Criteria) this;
        }

        public Criteria andPwdGreaterThan(String value) {
            addCriterion("pwd >", value, "pwd");
            return (Criteria) this;
        }

        public Criteria andPwdGreaterThanOrEqualTo(String value) {
            addCriterion("pwd >=", value, "pwd");
            return (Criteria) this;
        }

        public Criteria andPwdLessThan(String value) {
            addCriterion("pwd <", value, "pwd");
            return (Criteria) this;
        }

        public Criteria andPwdLessThanOrEqualTo(String value) {
            addCriterion("pwd <=", value, "pwd");
            return (Criteria) this;
        }

        public Criteria andPwdLike(String value) {
            addCriterion("pwd like", value, "pwd");
            return (Criteria) this;
        }

        public Criteria andPwdNotLike(String value) {
            addCriterion("pwd not like", value, "pwd");
            return (Criteria) this;
        }

        public Criteria andPwdIn(List<String> values) {
            addCriterion("pwd in", values, "pwd");
            return (Criteria) this;
        }

        public Criteria andPwdNotIn(List<String> values) {
            addCriterion("pwd not in", values, "pwd");
            return (Criteria) this;
        }

        public Criteria andPwdBetween(String value1, String value2) {
            addCriterion("pwd between", value1, value2, "pwd");
            return (Criteria) this;
        }

        public Criteria andPwdNotBetween(String value1, String value2) {
            addCriterion("pwd not between", value1, value2, "pwd");
            return (Criteria) this;
        }

        public Criteria andRocketStatusIsNull() {
            addCriterion("rocket_status is null");
            return (Criteria) this;
        }

        public Criteria andRocketStatusIsNotNull() {
            addCriterion("rocket_status is not null");
            return (Criteria) this;
        }

        public Criteria andRocketStatusEqualTo(String value) {
            addCriterion("rocket_status =", value, "rocketStatus");
            return (Criteria) this;
        }

        public Criteria andRocketStatusNotEqualTo(String value) {
            addCriterion("rocket_status <>", value, "rocketStatus");
            return (Criteria) this;
        }

        public Criteria andRocketStatusGreaterThan(String value) {
            addCriterion("rocket_status >", value, "rocketStatus");
            return (Criteria) this;
        }

        public Criteria andRocketStatusGreaterThanOrEqualTo(String value) {
            addCriterion("rocket_status >=", value, "rocketStatus");
            return (Criteria) this;
        }

        public Criteria andRocketStatusLessThan(String value) {
            addCriterion("rocket_status <", value, "rocketStatus");
            return (Criteria) this;
        }

        public Criteria andRocketStatusLessThanOrEqualTo(String value) {
            addCriterion("rocket_status <=", value, "rocketStatus");
            return (Criteria) this;
        }

        public Criteria andRocketStatusLike(String value) {
            addCriterion("rocket_status like", value, "rocketStatus");
            return (Criteria) this;
        }

        public Criteria andRocketStatusNotLike(String value) {
            addCriterion("rocket_status not like", value, "rocketStatus");
            return (Criteria) this;
        }

        public Criteria andRocketStatusIn(List<String> values) {
            addCriterion("rocket_status in", values, "rocketStatus");
            return (Criteria) this;
        }

        public Criteria andRocketStatusNotIn(List<String> values) {
            addCriterion("rocket_status not in", values, "rocketStatus");
            return (Criteria) this;
        }

        public Criteria andRocketStatusBetween(String value1, String value2) {
            addCriterion("rocket_status between", value1, value2, "rocketStatus");
            return (Criteria) this;
        }

        public Criteria andRocketStatusNotBetween(String value1, String value2) {
            addCriterion("rocket_status not between", value1, value2, "rocketStatus");
            return (Criteria) this;
        }

        public Criteria andAUidIsNull() {
            addCriterion("a_uid is null");
            return (Criteria) this;
        }

        public Criteria andAUidIsNotNull() {
            addCriterion("a_uid is not null");
            return (Criteria) this;
        }

        public Criteria andAUidEqualTo(Integer value) {
            addCriterion("a_uid =", value, "aUid");
            return (Criteria) this;
        }

        public Criteria andAUidNotEqualTo(Integer value) {
            addCriterion("a_uid <>", value, "aUid");
            return (Criteria) this;
        }

        public Criteria andAUidGreaterThan(Integer value) {
            addCriterion("a_uid >", value, "aUid");
            return (Criteria) this;
        }

        public Criteria andAUidGreaterThanOrEqualTo(Integer value) {
            addCriterion("a_uid >=", value, "aUid");
            return (Criteria) this;
        }

        public Criteria andAUidLessThan(Integer value) {
            addCriterion("a_uid <", value, "aUid");
            return (Criteria) this;
        }

        public Criteria andAUidLessThanOrEqualTo(Integer value) {
            addCriterion("a_uid <=", value, "aUid");
            return (Criteria) this;
        }

        public Criteria andAUidIn(List<Integer> values) {
            addCriterion("a_uid in", values, "aUid");
            return (Criteria) this;
        }

        public Criteria andAUidNotIn(List<Integer> values) {
            addCriterion("a_uid not in", values, "aUid");
            return (Criteria) this;
        }

        public Criteria andAUidBetween(Integer value1, Integer value2) {
            addCriterion("a_uid between", value1, value2, "aUid");
            return (Criteria) this;
        }

        public Criteria andAUidNotBetween(Integer value1, Integer value2) {
            addCriterion("a_uid not between", value1, value2, "aUid");
            return (Criteria) this;
        }

        public Criteria andDrawNumberBalanceIsNull() {
            addCriterion("draw_number_balance is null");
            return (Criteria) this;
        }

        public Criteria andDrawNumberBalanceIsNotNull() {
            addCriterion("draw_number_balance is not null");
            return (Criteria) this;
        }

        public Criteria andDrawNumberBalanceEqualTo(Integer value) {
            addCriterion("draw_number_balance =", value, "drawNumberBalance");
            return (Criteria) this;
        }

        public Criteria andDrawNumberBalanceNotEqualTo(Integer value) {
            addCriterion("draw_number_balance <>", value, "drawNumberBalance");
            return (Criteria) this;
        }

        public Criteria andDrawNumberBalanceGreaterThan(Integer value) {
            addCriterion("draw_number_balance >", value, "drawNumberBalance");
            return (Criteria) this;
        }

        public Criteria andDrawNumberBalanceGreaterThanOrEqualTo(Integer value) {
            addCriterion("draw_number_balance >=", value, "drawNumberBalance");
            return (Criteria) this;
        }

        public Criteria andDrawNumberBalanceLessThan(Integer value) {
            addCriterion("draw_number_balance <", value, "drawNumberBalance");
            return (Criteria) this;
        }

        public Criteria andDrawNumberBalanceLessThanOrEqualTo(Integer value) {
            addCriterion("draw_number_balance <=", value, "drawNumberBalance");
            return (Criteria) this;
        }

        public Criteria andDrawNumberBalanceIn(List<Integer> values) {
            addCriterion("draw_number_balance in", values, "drawNumberBalance");
            return (Criteria) this;
        }

        public Criteria andDrawNumberBalanceNotIn(List<Integer> values) {
            addCriterion("draw_number_balance not in", values, "drawNumberBalance");
            return (Criteria) this;
        }

        public Criteria andDrawNumberBalanceBetween(Integer value1, Integer value2) {
            addCriterion("draw_number_balance between", value1, value2, "drawNumberBalance");
            return (Criteria) this;
        }

        public Criteria andDrawNumberBalanceNotBetween(Integer value1, Integer value2) {
            addCriterion("draw_number_balance not between", value1, value2, "drawNumberBalance");
            return (Criteria) this;
        }

        public Criteria andNameCardBalanceIsNull() {
            addCriterion("name_card_balance is null");
            return (Criteria) this;
        }

        public Criteria andNameCardBalanceIsNotNull() {
            addCriterion("name_card_balance is not null");
            return (Criteria) this;
        }

        public Criteria andNameCardBalanceEqualTo(Integer value) {
            addCriterion("name_card_balance =", value, "nameCardBalance");
            return (Criteria) this;
        }

        public Criteria andNameCardBalanceNotEqualTo(Integer value) {
            addCriterion("name_card_balance <>", value, "nameCardBalance");
            return (Criteria) this;
        }

        public Criteria andNameCardBalanceGreaterThan(Integer value) {
            addCriterion("name_card_balance >", value, "nameCardBalance");
            return (Criteria) this;
        }

        public Criteria andNameCardBalanceGreaterThanOrEqualTo(Integer value) {
            addCriterion("name_card_balance >=", value, "nameCardBalance");
            return (Criteria) this;
        }

        public Criteria andNameCardBalanceLessThan(Integer value) {
            addCriterion("name_card_balance <", value, "nameCardBalance");
            return (Criteria) this;
        }

        public Criteria andNameCardBalanceLessThanOrEqualTo(Integer value) {
            addCriterion("name_card_balance <=", value, "nameCardBalance");
            return (Criteria) this;
        }

        public Criteria andNameCardBalanceIn(List<Integer> values) {
            addCriterion("name_card_balance in", values, "nameCardBalance");
            return (Criteria) this;
        }

        public Criteria andNameCardBalanceNotIn(List<Integer> values) {
            addCriterion("name_card_balance not in", values, "nameCardBalance");
            return (Criteria) this;
        }

        public Criteria andNameCardBalanceBetween(Integer value1, Integer value2) {
            addCriterion("name_card_balance between", value1, value2, "nameCardBalance");
            return (Criteria) this;
        }

        public Criteria andNameCardBalanceNotBetween(Integer value1, Integer value2) {
            addCriterion("name_card_balance not between", value1, value2, "nameCardBalance");
            return (Criteria) this;
        }

        public Criteria andInviteCodeIsNull() {
            addCriterion("invite_code is null");
            return (Criteria) this;
        }

        public Criteria andInviteCodeIsNotNull() {
            addCriterion("invite_code is not null");
            return (Criteria) this;
        }

        public Criteria andInviteCodeEqualTo(String value) {
            addCriterion("invite_code =", value, "inviteCode");
            return (Criteria) this;
        }

        public Criteria andInviteCodeNotEqualTo(String value) {
            addCriterion("invite_code <>", value, "inviteCode");
            return (Criteria) this;
        }

        public Criteria andInviteCodeGreaterThan(String value) {
            addCriterion("invite_code >", value, "inviteCode");
            return (Criteria) this;
        }

        public Criteria andInviteCodeGreaterThanOrEqualTo(String value) {
            addCriterion("invite_code >=", value, "inviteCode");
            return (Criteria) this;
        }

        public Criteria andInviteCodeLessThan(String value) {
            addCriterion("invite_code <", value, "inviteCode");
            return (Criteria) this;
        }

        public Criteria andInviteCodeLessThanOrEqualTo(String value) {
            addCriterion("invite_code <=", value, "inviteCode");
            return (Criteria) this;
        }

        public Criteria andInviteCodeLike(String value) {
            addCriterion("invite_code like", value, "inviteCode");
            return (Criteria) this;
        }

        public Criteria andInviteCodeNotLike(String value) {
            addCriterion("invite_code not like", value, "inviteCode");
            return (Criteria) this;
        }

        public Criteria andInviteCodeIn(List<String> values) {
            addCriterion("invite_code in", values, "inviteCode");
            return (Criteria) this;
        }

        public Criteria andInviteCodeNotIn(List<String> values) {
            addCriterion("invite_code not in", values, "inviteCode");
            return (Criteria) this;
        }

        public Criteria andInviteCodeBetween(String value1, String value2) {
            addCriterion("invite_code between", value1, value2, "inviteCode");
            return (Criteria) this;
        }

        public Criteria andInviteCodeNotBetween(String value1, String value2) {
            addCriterion("invite_code not between", value1, value2, "inviteCode");
            return (Criteria) this;
        }

        public Criteria andInvitedByUserIdIsNull() {
            addCriterion("invited_by_user_id is null");
            return (Criteria) this;
        }

        public Criteria andInvitedByUserIdIsNotNull() {
            addCriterion("invited_by_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andInvitedByUserIdEqualTo(Long value) {
            addCriterion("invited_by_user_id =", value, "invitedByUserId");
            return (Criteria) this;
        }

        public Criteria andInvitedByUserIdNotEqualTo(Long value) {
            addCriterion("invited_by_user_id <>", value, "invitedByUserId");
            return (Criteria) this;
        }

        public Criteria andInvitedByUserIdGreaterThan(Long value) {
            addCriterion("invited_by_user_id >", value, "invitedByUserId");
            return (Criteria) this;
        }

        public Criteria andInvitedByUserIdGreaterThanOrEqualTo(Long value) {
            addCriterion("invited_by_user_id >=", value, "invitedByUserId");
            return (Criteria) this;
        }

        public Criteria andInvitedByUserIdLessThan(Long value) {
            addCriterion("invited_by_user_id <", value, "invitedByUserId");
            return (Criteria) this;
        }

        public Criteria andInvitedByUserIdLessThanOrEqualTo(Long value) {
            addCriterion("invited_by_user_id <=", value, "invitedByUserId");
            return (Criteria) this;
        }

        public Criteria andInvitedByUserIdIn(List<Long> values) {
            addCriterion("invited_by_user_id in", values, "invitedByUserId");
            return (Criteria) this;
        }

        public Criteria andInvitedByUserIdNotIn(List<Long> values) {
            addCriterion("invited_by_user_id not in", values, "invitedByUserId");
            return (Criteria) this;
        }

        public Criteria andInvitedByUserIdBetween(Long value1, Long value2) {
            addCriterion("invited_by_user_id between", value1, value2, "invitedByUserId");
            return (Criteria) this;
        }

        public Criteria andInvitedByUserIdNotBetween(Long value1, Long value2) {
            addCriterion("invited_by_user_id not between", value1, value2, "invitedByUserId");
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