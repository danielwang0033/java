package com.coin.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
public class ReplyExtendExample {
    @Setter
    protected String orderByClause;

    @Setter
    protected boolean distinct;

    protected final List<Criteria> oredCriteria;

    public ReplyExtendExample() {
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

        public Criteria andTowerTypeIsNull() {
            addCriterion("tower_type is null");
            return (Criteria) this;
        }

        public Criteria andTowerTypeIsNotNull() {
            addCriterion("tower_type is not null");
            return (Criteria) this;
        }

        public Criteria andTowerTypeEqualTo(Integer value) {
            addCriterion("tower_type =", value, "towerType");
            return (Criteria) this;
        }

        public Criteria andTowerTypeNotEqualTo(Integer value) {
            addCriterion("tower_type <>", value, "towerType");
            return (Criteria) this;
        }

        public Criteria andTowerTypeGreaterThan(Integer value) {
            addCriterion("tower_type >", value, "towerType");
            return (Criteria) this;
        }

        public Criteria andTowerTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("tower_type >=", value, "towerType");
            return (Criteria) this;
        }

        public Criteria andTowerTypeLessThan(Integer value) {
            addCriterion("tower_type <", value, "towerType");
            return (Criteria) this;
        }

        public Criteria andTowerTypeLessThanOrEqualTo(Integer value) {
            addCriterion("tower_type <=", value, "towerType");
            return (Criteria) this;
        }

        public Criteria andTowerTypeIn(List<Integer> values) {
            addCriterion("tower_type in", values, "towerType");
            return (Criteria) this;
        }

        public Criteria andTowerTypeNotIn(List<Integer> values) {
            addCriterion("tower_type not in", values, "towerType");
            return (Criteria) this;
        }

        public Criteria andTowerTypeBetween(Integer value1, Integer value2) {
            addCriterion("tower_type between", value1, value2, "towerType");
            return (Criteria) this;
        }

        public Criteria andTowerTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("tower_type not between", value1, value2, "towerType");
            return (Criteria) this;
        }

        public Criteria andTowerIdIsNull() {
            addCriterion("tower_id is null");
            return (Criteria) this;
        }

        public Criteria andTowerIdIsNotNull() {
            addCriterion("tower_id is not null");
            return (Criteria) this;
        }

        public Criteria andTowerIdEqualTo(Long value) {
            addCriterion("tower_id =", value, "towerId");
            return (Criteria) this;
        }

        public Criteria andTowerIdNotEqualTo(Long value) {
            addCriterion("tower_id <>", value, "towerId");
            return (Criteria) this;
        }

        public Criteria andTowerIdGreaterThan(Long value) {
            addCriterion("tower_id >", value, "towerId");
            return (Criteria) this;
        }

        public Criteria andTowerIdGreaterThanOrEqualTo(Long value) {
            addCriterion("tower_id >=", value, "towerId");
            return (Criteria) this;
        }

        public Criteria andTowerIdLessThan(Long value) {
            addCriterion("tower_id <", value, "towerId");
            return (Criteria) this;
        }

        public Criteria andTowerIdLessThanOrEqualTo(Long value) {
            addCriterion("tower_id <=", value, "towerId");
            return (Criteria) this;
        }

        public Criteria andTowerIdIn(List<Long> values) {
            addCriterion("tower_id in", values, "towerId");
            return (Criteria) this;
        }

        public Criteria andTowerIdNotIn(List<Long> values) {
            addCriterion("tower_id not in", values, "towerId");
            return (Criteria) this;
        }

        public Criteria andTowerIdBetween(Long value1, Long value2) {
            addCriterion("tower_id between", value1, value2, "towerId");
            return (Criteria) this;
        }

        public Criteria andTowerIdNotBetween(Long value1, Long value2) {
            addCriterion("tower_id not between", value1, value2, "towerId");
            return (Criteria) this;
        }

        public Criteria andTowerUserIdIsNull() {
            addCriterion("tower_user_id is null");
            return (Criteria) this;
        }

        public Criteria andTowerUserIdIsNotNull() {
            addCriterion("tower_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andTowerUserIdEqualTo(Long value) {
            addCriterion("tower_user_id =", value, "towerUserId");
            return (Criteria) this;
        }

        public Criteria andTowerUserIdNotEqualTo(Long value) {
            addCriterion("tower_user_id <>", value, "towerUserId");
            return (Criteria) this;
        }

        public Criteria andTowerUserIdGreaterThan(Long value) {
            addCriterion("tower_user_id >", value, "towerUserId");
            return (Criteria) this;
        }

        public Criteria andTowerUserIdGreaterThanOrEqualTo(Long value) {
            addCriterion("tower_user_id >=", value, "towerUserId");
            return (Criteria) this;
        }

        public Criteria andTowerUserIdLessThan(Long value) {
            addCriterion("tower_user_id <", value, "towerUserId");
            return (Criteria) this;
        }

        public Criteria andTowerUserIdLessThanOrEqualTo(Long value) {
            addCriterion("tower_user_id <=", value, "towerUserId");
            return (Criteria) this;
        }

        public Criteria andTowerUserIdIn(List<Long> values) {
            addCriterion("tower_user_id in", values, "towerUserId");
            return (Criteria) this;
        }

        public Criteria andTowerUserIdNotIn(List<Long> values) {
            addCriterion("tower_user_id not in", values, "towerUserId");
            return (Criteria) this;
        }

        public Criteria andTowerUserIdBetween(Long value1, Long value2) {
            addCriterion("tower_user_id between", value1, value2, "towerUserId");
            return (Criteria) this;
        }

        public Criteria andTowerUserIdNotBetween(Long value1, Long value2) {
            addCriterion("tower_user_id not between", value1, value2, "towerUserId");
            return (Criteria) this;
        }

        public Criteria andFloorIdIsNull() {
            addCriterion("floor_id is null");
            return (Criteria) this;
        }

        public Criteria andFloorIdIsNotNull() {
            addCriterion("floor_id is not null");
            return (Criteria) this;
        }

        public Criteria andFloorIdEqualTo(Long value) {
            addCriterion("floor_id =", value, "floorId");
            return (Criteria) this;
        }

        public Criteria andFloorIdNotEqualTo(Long value) {
            addCriterion("floor_id <>", value, "floorId");
            return (Criteria) this;
        }

        public Criteria andFloorIdGreaterThan(Long value) {
            addCriterion("floor_id >", value, "floorId");
            return (Criteria) this;
        }

        public Criteria andFloorIdGreaterThanOrEqualTo(Long value) {
            addCriterion("floor_id >=", value, "floorId");
            return (Criteria) this;
        }

        public Criteria andFloorIdLessThan(Long value) {
            addCriterion("floor_id <", value, "floorId");
            return (Criteria) this;
        }

        public Criteria andFloorIdLessThanOrEqualTo(Long value) {
            addCriterion("floor_id <=", value, "floorId");
            return (Criteria) this;
        }

        public Criteria andFloorIdIn(List<Long> values) {
            addCriterion("floor_id in", values, "floorId");
            return (Criteria) this;
        }

        public Criteria andFloorIdNotIn(List<Long> values) {
            addCriterion("floor_id not in", values, "floorId");
            return (Criteria) this;
        }

        public Criteria andFloorIdBetween(Long value1, Long value2) {
            addCriterion("floor_id between", value1, value2, "floorId");
            return (Criteria) this;
        }

        public Criteria andFloorIdNotBetween(Long value1, Long value2) {
            addCriterion("floor_id not between", value1, value2, "floorId");
            return (Criteria) this;
        }

        public Criteria andFloorUserIdIsNull() {
            addCriterion("floor_user_id is null");
            return (Criteria) this;
        }

        public Criteria andFloorUserIdIsNotNull() {
            addCriterion("floor_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andFloorUserIdEqualTo(Long value) {
            addCriterion("floor_user_id =", value, "floorUserId");
            return (Criteria) this;
        }

        public Criteria andFloorUserIdNotEqualTo(Long value) {
            addCriterion("floor_user_id <>", value, "floorUserId");
            return (Criteria) this;
        }

        public Criteria andFloorUserIdGreaterThan(Long value) {
            addCriterion("floor_user_id >", value, "floorUserId");
            return (Criteria) this;
        }

        public Criteria andFloorUserIdGreaterThanOrEqualTo(Long value) {
            addCriterion("floor_user_id >=", value, "floorUserId");
            return (Criteria) this;
        }

        public Criteria andFloorUserIdLessThan(Long value) {
            addCriterion("floor_user_id <", value, "floorUserId");
            return (Criteria) this;
        }

        public Criteria andFloorUserIdLessThanOrEqualTo(Long value) {
            addCriterion("floor_user_id <=", value, "floorUserId");
            return (Criteria) this;
        }

        public Criteria andFloorUserIdIn(List<Long> values) {
            addCriterion("floor_user_id in", values, "floorUserId");
            return (Criteria) this;
        }

        public Criteria andFloorUserIdNotIn(List<Long> values) {
            addCriterion("floor_user_id not in", values, "floorUserId");
            return (Criteria) this;
        }

        public Criteria andFloorUserIdBetween(Long value1, Long value2) {
            addCriterion("floor_user_id between", value1, value2, "floorUserId");
            return (Criteria) this;
        }

        public Criteria andFloorUserIdNotBetween(Long value1, Long value2) {
            addCriterion("floor_user_id not between", value1, value2, "floorUserId");
            return (Criteria) this;
        }

        public Criteria andParentIdIsNull() {
            addCriterion("parent_id is null");
            return (Criteria) this;
        }

        public Criteria andParentIdIsNotNull() {
            addCriterion("parent_id is not null");
            return (Criteria) this;
        }

        public Criteria andParentIdEqualTo(Long value) {
            addCriterion("parent_id =", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotEqualTo(Long value) {
            addCriterion("parent_id <>", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdGreaterThan(Long value) {
            addCriterion("parent_id >", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdGreaterThanOrEqualTo(Long value) {
            addCriterion("parent_id >=", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdLessThan(Long value) {
            addCriterion("parent_id <", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdLessThanOrEqualTo(Long value) {
            addCriterion("parent_id <=", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdIn(List<Long> values) {
            addCriterion("parent_id in", values, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotIn(List<Long> values) {
            addCriterion("parent_id not in", values, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdBetween(Long value1, Long value2) {
            addCriterion("parent_id between", value1, value2, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotBetween(Long value1, Long value2) {
            addCriterion("parent_id not between", value1, value2, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentUserIdIsNull() {
            addCriterion("parent_user_id is null");
            return (Criteria) this;
        }

        public Criteria andParentUserIdIsNotNull() {
            addCriterion("parent_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andParentUserIdEqualTo(Long value) {
            addCriterion("parent_user_id =", value, "parentUserId");
            return (Criteria) this;
        }

        public Criteria andParentUserIdNotEqualTo(Long value) {
            addCriterion("parent_user_id <>", value, "parentUserId");
            return (Criteria) this;
        }

        public Criteria andParentUserIdGreaterThan(Long value) {
            addCriterion("parent_user_id >", value, "parentUserId");
            return (Criteria) this;
        }

        public Criteria andParentUserIdGreaterThanOrEqualTo(Long value) {
            addCriterion("parent_user_id >=", value, "parentUserId");
            return (Criteria) this;
        }

        public Criteria andParentUserIdLessThan(Long value) {
            addCriterion("parent_user_id <", value, "parentUserId");
            return (Criteria) this;
        }

        public Criteria andParentUserIdLessThanOrEqualTo(Long value) {
            addCriterion("parent_user_id <=", value, "parentUserId");
            return (Criteria) this;
        }

        public Criteria andParentUserIdIn(List<Long> values) {
            addCriterion("parent_user_id in", values, "parentUserId");
            return (Criteria) this;
        }

        public Criteria andParentUserIdNotIn(List<Long> values) {
            addCriterion("parent_user_id not in", values, "parentUserId");
            return (Criteria) this;
        }

        public Criteria andParentUserIdBetween(Long value1, Long value2) {
            addCriterion("parent_user_id between", value1, value2, "parentUserId");
            return (Criteria) this;
        }

        public Criteria andParentUserIdNotBetween(Long value1, Long value2) {
            addCriterion("parent_user_id not between", value1, value2, "parentUserId");
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

        public Criteria andTowerOwnerFlagIsNull() {
            addCriterion("tower_owner_flag is null");
            return (Criteria) this;
        }

        public Criteria andTowerOwnerFlagIsNotNull() {
            addCriterion("tower_owner_flag is not null");
            return (Criteria) this;
        }

        public Criteria andTowerOwnerFlagEqualTo(Integer value) {
            addCriterion("tower_owner_flag =", value, "towerOwnerFlag");
            return (Criteria) this;
        }

        public Criteria andTowerOwnerFlagNotEqualTo(Integer value) {
            addCriterion("tower_owner_flag <>", value, "towerOwnerFlag");
            return (Criteria) this;
        }

        public Criteria andTowerOwnerFlagGreaterThan(Integer value) {
            addCriterion("tower_owner_flag >", value, "towerOwnerFlag");
            return (Criteria) this;
        }

        public Criteria andTowerOwnerFlagGreaterThanOrEqualTo(Integer value) {
            addCriterion("tower_owner_flag >=", value, "towerOwnerFlag");
            return (Criteria) this;
        }

        public Criteria andTowerOwnerFlagLessThan(Integer value) {
            addCriterion("tower_owner_flag <", value, "towerOwnerFlag");
            return (Criteria) this;
        }

        public Criteria andTowerOwnerFlagLessThanOrEqualTo(Integer value) {
            addCriterion("tower_owner_flag <=", value, "towerOwnerFlag");
            return (Criteria) this;
        }

        public Criteria andTowerOwnerFlagIn(List<Integer> values) {
            addCriterion("tower_owner_flag in", values, "towerOwnerFlag");
            return (Criteria) this;
        }

        public Criteria andTowerOwnerFlagNotIn(List<Integer> values) {
            addCriterion("tower_owner_flag not in", values, "towerOwnerFlag");
            return (Criteria) this;
        }

        public Criteria andTowerOwnerFlagBetween(Integer value1, Integer value2) {
            addCriterion("tower_owner_flag between", value1, value2, "towerOwnerFlag");
            return (Criteria) this;
        }

        public Criteria andTowerOwnerFlagNotBetween(Integer value1, Integer value2) {
            addCriterion("tower_owner_flag not between", value1, value2, "towerOwnerFlag");
            return (Criteria) this;
        }

        public Criteria andFloorOwnerFlagIsNull() {
            addCriterion("floor_owner_flag is null");
            return (Criteria) this;
        }

        public Criteria andFloorOwnerFlagIsNotNull() {
            addCriterion("floor_owner_flag is not null");
            return (Criteria) this;
        }

        public Criteria andFloorOwnerFlagEqualTo(Integer value) {
            addCriterion("floor_owner_flag =", value, "floorOwnerFlag");
            return (Criteria) this;
        }

        public Criteria andFloorOwnerFlagNotEqualTo(Integer value) {
            addCriterion("floor_owner_flag <>", value, "floorOwnerFlag");
            return (Criteria) this;
        }

        public Criteria andFloorOwnerFlagGreaterThan(Integer value) {
            addCriterion("floor_owner_flag >", value, "floorOwnerFlag");
            return (Criteria) this;
        }

        public Criteria andFloorOwnerFlagGreaterThanOrEqualTo(Integer value) {
            addCriterion("floor_owner_flag >=", value, "floorOwnerFlag");
            return (Criteria) this;
        }

        public Criteria andFloorOwnerFlagLessThan(Integer value) {
            addCriterion("floor_owner_flag <", value, "floorOwnerFlag");
            return (Criteria) this;
        }

        public Criteria andFloorOwnerFlagLessThanOrEqualTo(Integer value) {
            addCriterion("floor_owner_flag <=", value, "floorOwnerFlag");
            return (Criteria) this;
        }

        public Criteria andFloorOwnerFlagIn(List<Integer> values) {
            addCriterion("floor_owner_flag in", values, "floorOwnerFlag");
            return (Criteria) this;
        }

        public Criteria andFloorOwnerFlagNotIn(List<Integer> values) {
            addCriterion("floor_owner_flag not in", values, "floorOwnerFlag");
            return (Criteria) this;
        }

        public Criteria andFloorOwnerFlagBetween(Integer value1, Integer value2) {
            addCriterion("floor_owner_flag between", value1, value2, "floorOwnerFlag");
            return (Criteria) this;
        }

        public Criteria andFloorOwnerFlagNotBetween(Integer value1, Integer value2) {
            addCriterion("floor_owner_flag not between", value1, value2, "floorOwnerFlag");
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