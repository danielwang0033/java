package com.coin.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
public class MatchFbtechsExample {
    @Setter
    protected String orderByClause;

    @Setter
    protected boolean distinct;

    protected final List<Criteria> oredCriteria;

    public MatchFbtechsExample() {
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

        public Criteria andHomeidIsNull() {
            addCriterion("homeId is null");
            return (Criteria) this;
        }

        public Criteria andHomeidIsNotNull() {
            addCriterion("homeId is not null");
            return (Criteria) this;
        }

        public Criteria andHomeidEqualTo(Integer value) {
            addCriterion("homeId =", value, "homeid");
            return (Criteria) this;
        }

        public Criteria andHomeidNotEqualTo(Integer value) {
            addCriterion("homeId <>", value, "homeid");
            return (Criteria) this;
        }

        public Criteria andHomeidGreaterThan(Integer value) {
            addCriterion("homeId >", value, "homeid");
            return (Criteria) this;
        }

        public Criteria andHomeidGreaterThanOrEqualTo(Integer value) {
            addCriterion("homeId >=", value, "homeid");
            return (Criteria) this;
        }

        public Criteria andHomeidLessThan(Integer value) {
            addCriterion("homeId <", value, "homeid");
            return (Criteria) this;
        }

        public Criteria andHomeidLessThanOrEqualTo(Integer value) {
            addCriterion("homeId <=", value, "homeid");
            return (Criteria) this;
        }

        public Criteria andHomeidIn(List<Integer> values) {
            addCriterion("homeId in", values, "homeid");
            return (Criteria) this;
        }

        public Criteria andHomeidNotIn(List<Integer> values) {
            addCriterion("homeId not in", values, "homeid");
            return (Criteria) this;
        }

        public Criteria andHomeidBetween(Integer value1, Integer value2) {
            addCriterion("homeId between", value1, value2, "homeid");
            return (Criteria) this;
        }

        public Criteria andHomeidNotBetween(Integer value1, Integer value2) {
            addCriterion("homeId not between", value1, value2, "homeid");
            return (Criteria) this;
        }

        public Criteria andHomecornerIsNull() {
            addCriterion("homeCorner is null");
            return (Criteria) this;
        }

        public Criteria andHomecornerIsNotNull() {
            addCriterion("homeCorner is not null");
            return (Criteria) this;
        }

        public Criteria andHomecornerEqualTo(Byte value) {
            addCriterion("homeCorner =", value, "homecorner");
            return (Criteria) this;
        }

        public Criteria andHomecornerNotEqualTo(Byte value) {
            addCriterion("homeCorner <>", value, "homecorner");
            return (Criteria) this;
        }

        public Criteria andHomecornerGreaterThan(Byte value) {
            addCriterion("homeCorner >", value, "homecorner");
            return (Criteria) this;
        }

        public Criteria andHomecornerGreaterThanOrEqualTo(Byte value) {
            addCriterion("homeCorner >=", value, "homecorner");
            return (Criteria) this;
        }

        public Criteria andHomecornerLessThan(Byte value) {
            addCriterion("homeCorner <", value, "homecorner");
            return (Criteria) this;
        }

        public Criteria andHomecornerLessThanOrEqualTo(Byte value) {
            addCriterion("homeCorner <=", value, "homecorner");
            return (Criteria) this;
        }

        public Criteria andHomecornerIn(List<Byte> values) {
            addCriterion("homeCorner in", values, "homecorner");
            return (Criteria) this;
        }

        public Criteria andHomecornerNotIn(List<Byte> values) {
            addCriterion("homeCorner not in", values, "homecorner");
            return (Criteria) this;
        }

        public Criteria andHomecornerBetween(Byte value1, Byte value2) {
            addCriterion("homeCorner between", value1, value2, "homecorner");
            return (Criteria) this;
        }

        public Criteria andHomecornerNotBetween(Byte value1, Byte value2) {
            addCriterion("homeCorner not between", value1, value2, "homecorner");
            return (Criteria) this;
        }

        public Criteria andHomeredIsNull() {
            addCriterion("homeRed is null");
            return (Criteria) this;
        }

        public Criteria andHomeredIsNotNull() {
            addCriterion("homeRed is not null");
            return (Criteria) this;
        }

        public Criteria andHomeredEqualTo(Byte value) {
            addCriterion("homeRed =", value, "homered");
            return (Criteria) this;
        }

        public Criteria andHomeredNotEqualTo(Byte value) {
            addCriterion("homeRed <>", value, "homered");
            return (Criteria) this;
        }

        public Criteria andHomeredGreaterThan(Byte value) {
            addCriterion("homeRed >", value, "homered");
            return (Criteria) this;
        }

        public Criteria andHomeredGreaterThanOrEqualTo(Byte value) {
            addCriterion("homeRed >=", value, "homered");
            return (Criteria) this;
        }

        public Criteria andHomeredLessThan(Byte value) {
            addCriterion("homeRed <", value, "homered");
            return (Criteria) this;
        }

        public Criteria andHomeredLessThanOrEqualTo(Byte value) {
            addCriterion("homeRed <=", value, "homered");
            return (Criteria) this;
        }

        public Criteria andHomeredIn(List<Byte> values) {
            addCriterion("homeRed in", values, "homered");
            return (Criteria) this;
        }

        public Criteria andHomeredNotIn(List<Byte> values) {
            addCriterion("homeRed not in", values, "homered");
            return (Criteria) this;
        }

        public Criteria andHomeredBetween(Byte value1, Byte value2) {
            addCriterion("homeRed between", value1, value2, "homered");
            return (Criteria) this;
        }

        public Criteria andHomeredNotBetween(Byte value1, Byte value2) {
            addCriterion("homeRed not between", value1, value2, "homered");
            return (Criteria) this;
        }

        public Criteria andHomeyellowIsNull() {
            addCriterion("homeYellow is null");
            return (Criteria) this;
        }

        public Criteria andHomeyellowIsNotNull() {
            addCriterion("homeYellow is not null");
            return (Criteria) this;
        }

        public Criteria andHomeyellowEqualTo(Byte value) {
            addCriterion("homeYellow =", value, "homeyellow");
            return (Criteria) this;
        }

        public Criteria andHomeyellowNotEqualTo(Byte value) {
            addCriterion("homeYellow <>", value, "homeyellow");
            return (Criteria) this;
        }

        public Criteria andHomeyellowGreaterThan(Byte value) {
            addCriterion("homeYellow >", value, "homeyellow");
            return (Criteria) this;
        }

        public Criteria andHomeyellowGreaterThanOrEqualTo(Byte value) {
            addCriterion("homeYellow >=", value, "homeyellow");
            return (Criteria) this;
        }

        public Criteria andHomeyellowLessThan(Byte value) {
            addCriterion("homeYellow <", value, "homeyellow");
            return (Criteria) this;
        }

        public Criteria andHomeyellowLessThanOrEqualTo(Byte value) {
            addCriterion("homeYellow <=", value, "homeyellow");
            return (Criteria) this;
        }

        public Criteria andHomeyellowIn(List<Byte> values) {
            addCriterion("homeYellow in", values, "homeyellow");
            return (Criteria) this;
        }

        public Criteria andHomeyellowNotIn(List<Byte> values) {
            addCriterion("homeYellow not in", values, "homeyellow");
            return (Criteria) this;
        }

        public Criteria andHomeyellowBetween(Byte value1, Byte value2) {
            addCriterion("homeYellow between", value1, value2, "homeyellow");
            return (Criteria) this;
        }

        public Criteria andHomeyellowNotBetween(Byte value1, Byte value2) {
            addCriterion("homeYellow not between", value1, value2, "homeyellow");
            return (Criteria) this;
        }

        public Criteria andHomeballrateIsNull() {
            addCriterion("homeBallRate is null");
            return (Criteria) this;
        }

        public Criteria andHomeballrateIsNotNull() {
            addCriterion("homeBallRate is not null");
            return (Criteria) this;
        }

        public Criteria andHomeballrateEqualTo(String value) {
            addCriterion("homeBallRate =", value, "homeballrate");
            return (Criteria) this;
        }

        public Criteria andHomeballrateNotEqualTo(String value) {
            addCriterion("homeBallRate <>", value, "homeballrate");
            return (Criteria) this;
        }

        public Criteria andHomeballrateGreaterThan(String value) {
            addCriterion("homeBallRate >", value, "homeballrate");
            return (Criteria) this;
        }

        public Criteria andHomeballrateGreaterThanOrEqualTo(String value) {
            addCriterion("homeBallRate >=", value, "homeballrate");
            return (Criteria) this;
        }

        public Criteria andHomeballrateLessThan(String value) {
            addCriterion("homeBallRate <", value, "homeballrate");
            return (Criteria) this;
        }

        public Criteria andHomeballrateLessThanOrEqualTo(String value) {
            addCriterion("homeBallRate <=", value, "homeballrate");
            return (Criteria) this;
        }

        public Criteria andHomeballrateLike(String value) {
            addCriterion("homeBallRate like", value, "homeballrate");
            return (Criteria) this;
        }

        public Criteria andHomeballrateNotLike(String value) {
            addCriterion("homeBallRate not like", value, "homeballrate");
            return (Criteria) this;
        }

        public Criteria andHomeballrateIn(List<String> values) {
            addCriterion("homeBallRate in", values, "homeballrate");
            return (Criteria) this;
        }

        public Criteria andHomeballrateNotIn(List<String> values) {
            addCriterion("homeBallRate not in", values, "homeballrate");
            return (Criteria) this;
        }

        public Criteria andHomeballrateBetween(String value1, String value2) {
            addCriterion("homeBallRate between", value1, value2, "homeballrate");
            return (Criteria) this;
        }

        public Criteria andHomeballrateNotBetween(String value1, String value2) {
            addCriterion("homeBallRate not between", value1, value2, "homeballrate");
            return (Criteria) this;
        }

        public Criteria andHomeattackIsNull() {
            addCriterion("homeAttack is null");
            return (Criteria) this;
        }

        public Criteria andHomeattackIsNotNull() {
            addCriterion("homeAttack is not null");
            return (Criteria) this;
        }

        public Criteria andHomeattackEqualTo(Integer value) {
            addCriterion("homeAttack =", value, "homeattack");
            return (Criteria) this;
        }

        public Criteria andHomeattackNotEqualTo(Integer value) {
            addCriterion("homeAttack <>", value, "homeattack");
            return (Criteria) this;
        }

        public Criteria andHomeattackGreaterThan(Integer value) {
            addCriterion("homeAttack >", value, "homeattack");
            return (Criteria) this;
        }

        public Criteria andHomeattackGreaterThanOrEqualTo(Integer value) {
            addCriterion("homeAttack >=", value, "homeattack");
            return (Criteria) this;
        }

        public Criteria andHomeattackLessThan(Integer value) {
            addCriterion("homeAttack <", value, "homeattack");
            return (Criteria) this;
        }

        public Criteria andHomeattackLessThanOrEqualTo(Integer value) {
            addCriterion("homeAttack <=", value, "homeattack");
            return (Criteria) this;
        }

        public Criteria andHomeattackIn(List<Integer> values) {
            addCriterion("homeAttack in", values, "homeattack");
            return (Criteria) this;
        }

        public Criteria andHomeattackNotIn(List<Integer> values) {
            addCriterion("homeAttack not in", values, "homeattack");
            return (Criteria) this;
        }

        public Criteria andHomeattackBetween(Integer value1, Integer value2) {
            addCriterion("homeAttack between", value1, value2, "homeattack");
            return (Criteria) this;
        }

        public Criteria andHomeattackNotBetween(Integer value1, Integer value2) {
            addCriterion("homeAttack not between", value1, value2, "homeattack");
            return (Criteria) this;
        }

        public Criteria andHomedangerattackIsNull() {
            addCriterion("homeDangerAttack is null");
            return (Criteria) this;
        }

        public Criteria andHomedangerattackIsNotNull() {
            addCriterion("homeDangerAttack is not null");
            return (Criteria) this;
        }

        public Criteria andHomedangerattackEqualTo(Integer value) {
            addCriterion("homeDangerAttack =", value, "homedangerattack");
            return (Criteria) this;
        }

        public Criteria andHomedangerattackNotEqualTo(Integer value) {
            addCriterion("homeDangerAttack <>", value, "homedangerattack");
            return (Criteria) this;
        }

        public Criteria andHomedangerattackGreaterThan(Integer value) {
            addCriterion("homeDangerAttack >", value, "homedangerattack");
            return (Criteria) this;
        }

        public Criteria andHomedangerattackGreaterThanOrEqualTo(Integer value) {
            addCriterion("homeDangerAttack >=", value, "homedangerattack");
            return (Criteria) this;
        }

        public Criteria andHomedangerattackLessThan(Integer value) {
            addCriterion("homeDangerAttack <", value, "homedangerattack");
            return (Criteria) this;
        }

        public Criteria andHomedangerattackLessThanOrEqualTo(Integer value) {
            addCriterion("homeDangerAttack <=", value, "homedangerattack");
            return (Criteria) this;
        }

        public Criteria andHomedangerattackIn(List<Integer> values) {
            addCriterion("homeDangerAttack in", values, "homedangerattack");
            return (Criteria) this;
        }

        public Criteria andHomedangerattackNotIn(List<Integer> values) {
            addCriterion("homeDangerAttack not in", values, "homedangerattack");
            return (Criteria) this;
        }

        public Criteria andHomedangerattackBetween(Integer value1, Integer value2) {
            addCriterion("homeDangerAttack between", value1, value2, "homedangerattack");
            return (Criteria) this;
        }

        public Criteria andHomedangerattackNotBetween(Integer value1, Integer value2) {
            addCriterion("homeDangerAttack not between", value1, value2, "homedangerattack");
            return (Criteria) this;
        }

        public Criteria andHomeshootIsNull() {
            addCriterion("homeShoot is null");
            return (Criteria) this;
        }

        public Criteria andHomeshootIsNotNull() {
            addCriterion("homeShoot is not null");
            return (Criteria) this;
        }

        public Criteria andHomeshootEqualTo(Integer value) {
            addCriterion("homeShoot =", value, "homeshoot");
            return (Criteria) this;
        }

        public Criteria andHomeshootNotEqualTo(Integer value) {
            addCriterion("homeShoot <>", value, "homeshoot");
            return (Criteria) this;
        }

        public Criteria andHomeshootGreaterThan(Integer value) {
            addCriterion("homeShoot >", value, "homeshoot");
            return (Criteria) this;
        }

        public Criteria andHomeshootGreaterThanOrEqualTo(Integer value) {
            addCriterion("homeShoot >=", value, "homeshoot");
            return (Criteria) this;
        }

        public Criteria andHomeshootLessThan(Integer value) {
            addCriterion("homeShoot <", value, "homeshoot");
            return (Criteria) this;
        }

        public Criteria andHomeshootLessThanOrEqualTo(Integer value) {
            addCriterion("homeShoot <=", value, "homeshoot");
            return (Criteria) this;
        }

        public Criteria andHomeshootIn(List<Integer> values) {
            addCriterion("homeShoot in", values, "homeshoot");
            return (Criteria) this;
        }

        public Criteria andHomeshootNotIn(List<Integer> values) {
            addCriterion("homeShoot not in", values, "homeshoot");
            return (Criteria) this;
        }

        public Criteria andHomeshootBetween(Integer value1, Integer value2) {
            addCriterion("homeShoot between", value1, value2, "homeshoot");
            return (Criteria) this;
        }

        public Criteria andHomeshootNotBetween(Integer value1, Integer value2) {
            addCriterion("homeShoot not between", value1, value2, "homeshoot");
            return (Criteria) this;
        }

        public Criteria andHomeshootonIsNull() {
            addCriterion("homeShootOn is null");
            return (Criteria) this;
        }

        public Criteria andHomeshootonIsNotNull() {
            addCriterion("homeShootOn is not null");
            return (Criteria) this;
        }

        public Criteria andHomeshootonEqualTo(Integer value) {
            addCriterion("homeShootOn =", value, "homeshooton");
            return (Criteria) this;
        }

        public Criteria andHomeshootonNotEqualTo(Integer value) {
            addCriterion("homeShootOn <>", value, "homeshooton");
            return (Criteria) this;
        }

        public Criteria andHomeshootonGreaterThan(Integer value) {
            addCriterion("homeShootOn >", value, "homeshooton");
            return (Criteria) this;
        }

        public Criteria andHomeshootonGreaterThanOrEqualTo(Integer value) {
            addCriterion("homeShootOn >=", value, "homeshooton");
            return (Criteria) this;
        }

        public Criteria andHomeshootonLessThan(Integer value) {
            addCriterion("homeShootOn <", value, "homeshooton");
            return (Criteria) this;
        }

        public Criteria andHomeshootonLessThanOrEqualTo(Integer value) {
            addCriterion("homeShootOn <=", value, "homeshooton");
            return (Criteria) this;
        }

        public Criteria andHomeshootonIn(List<Integer> values) {
            addCriterion("homeShootOn in", values, "homeshooton");
            return (Criteria) this;
        }

        public Criteria andHomeshootonNotIn(List<Integer> values) {
            addCriterion("homeShootOn not in", values, "homeshooton");
            return (Criteria) this;
        }

        public Criteria andHomeshootonBetween(Integer value1, Integer value2) {
            addCriterion("homeShootOn between", value1, value2, "homeshooton");
            return (Criteria) this;
        }

        public Criteria andHomeshootonNotBetween(Integer value1, Integer value2) {
            addCriterion("homeShootOn not between", value1, value2, "homeshooton");
            return (Criteria) this;
        }

        public Criteria andAwayidIsNull() {
            addCriterion("awayId is null");
            return (Criteria) this;
        }

        public Criteria andAwayidIsNotNull() {
            addCriterion("awayId is not null");
            return (Criteria) this;
        }

        public Criteria andAwayidEqualTo(Integer value) {
            addCriterion("awayId =", value, "awayid");
            return (Criteria) this;
        }

        public Criteria andAwayidNotEqualTo(Integer value) {
            addCriterion("awayId <>", value, "awayid");
            return (Criteria) this;
        }

        public Criteria andAwayidGreaterThan(Integer value) {
            addCriterion("awayId >", value, "awayid");
            return (Criteria) this;
        }

        public Criteria andAwayidGreaterThanOrEqualTo(Integer value) {
            addCriterion("awayId >=", value, "awayid");
            return (Criteria) this;
        }

        public Criteria andAwayidLessThan(Integer value) {
            addCriterion("awayId <", value, "awayid");
            return (Criteria) this;
        }

        public Criteria andAwayidLessThanOrEqualTo(Integer value) {
            addCriterion("awayId <=", value, "awayid");
            return (Criteria) this;
        }

        public Criteria andAwayidIn(List<Integer> values) {
            addCriterion("awayId in", values, "awayid");
            return (Criteria) this;
        }

        public Criteria andAwayidNotIn(List<Integer> values) {
            addCriterion("awayId not in", values, "awayid");
            return (Criteria) this;
        }

        public Criteria andAwayidBetween(Integer value1, Integer value2) {
            addCriterion("awayId between", value1, value2, "awayid");
            return (Criteria) this;
        }

        public Criteria andAwayidNotBetween(Integer value1, Integer value2) {
            addCriterion("awayId not between", value1, value2, "awayid");
            return (Criteria) this;
        }

        public Criteria andAwaycornerIsNull() {
            addCriterion("awayCorner is null");
            return (Criteria) this;
        }

        public Criteria andAwaycornerIsNotNull() {
            addCriterion("awayCorner is not null");
            return (Criteria) this;
        }

        public Criteria andAwaycornerEqualTo(Integer value) {
            addCriterion("awayCorner =", value, "awaycorner");
            return (Criteria) this;
        }

        public Criteria andAwaycornerNotEqualTo(Integer value) {
            addCriterion("awayCorner <>", value, "awaycorner");
            return (Criteria) this;
        }

        public Criteria andAwaycornerGreaterThan(Integer value) {
            addCriterion("awayCorner >", value, "awaycorner");
            return (Criteria) this;
        }

        public Criteria andAwaycornerGreaterThanOrEqualTo(Integer value) {
            addCriterion("awayCorner >=", value, "awaycorner");
            return (Criteria) this;
        }

        public Criteria andAwaycornerLessThan(Integer value) {
            addCriterion("awayCorner <", value, "awaycorner");
            return (Criteria) this;
        }

        public Criteria andAwaycornerLessThanOrEqualTo(Integer value) {
            addCriterion("awayCorner <=", value, "awaycorner");
            return (Criteria) this;
        }

        public Criteria andAwaycornerIn(List<Integer> values) {
            addCriterion("awayCorner in", values, "awaycorner");
            return (Criteria) this;
        }

        public Criteria andAwaycornerNotIn(List<Integer> values) {
            addCriterion("awayCorner not in", values, "awaycorner");
            return (Criteria) this;
        }

        public Criteria andAwaycornerBetween(Integer value1, Integer value2) {
            addCriterion("awayCorner between", value1, value2, "awaycorner");
            return (Criteria) this;
        }

        public Criteria andAwaycornerNotBetween(Integer value1, Integer value2) {
            addCriterion("awayCorner not between", value1, value2, "awaycorner");
            return (Criteria) this;
        }

        public Criteria andAwayredIsNull() {
            addCriterion("awayRed is null");
            return (Criteria) this;
        }

        public Criteria andAwayredIsNotNull() {
            addCriterion("awayRed is not null");
            return (Criteria) this;
        }

        public Criteria andAwayredEqualTo(Integer value) {
            addCriterion("awayRed =", value, "awayred");
            return (Criteria) this;
        }

        public Criteria andAwayredNotEqualTo(Integer value) {
            addCriterion("awayRed <>", value, "awayred");
            return (Criteria) this;
        }

        public Criteria andAwayredGreaterThan(Integer value) {
            addCriterion("awayRed >", value, "awayred");
            return (Criteria) this;
        }

        public Criteria andAwayredGreaterThanOrEqualTo(Integer value) {
            addCriterion("awayRed >=", value, "awayred");
            return (Criteria) this;
        }

        public Criteria andAwayredLessThan(Integer value) {
            addCriterion("awayRed <", value, "awayred");
            return (Criteria) this;
        }

        public Criteria andAwayredLessThanOrEqualTo(Integer value) {
            addCriterion("awayRed <=", value, "awayred");
            return (Criteria) this;
        }

        public Criteria andAwayredIn(List<Integer> values) {
            addCriterion("awayRed in", values, "awayred");
            return (Criteria) this;
        }

        public Criteria andAwayredNotIn(List<Integer> values) {
            addCriterion("awayRed not in", values, "awayred");
            return (Criteria) this;
        }

        public Criteria andAwayredBetween(Integer value1, Integer value2) {
            addCriterion("awayRed between", value1, value2, "awayred");
            return (Criteria) this;
        }

        public Criteria andAwayredNotBetween(Integer value1, Integer value2) {
            addCriterion("awayRed not between", value1, value2, "awayred");
            return (Criteria) this;
        }

        public Criteria andAwayyellowIsNull() {
            addCriterion("awayYellow is null");
            return (Criteria) this;
        }

        public Criteria andAwayyellowIsNotNull() {
            addCriterion("awayYellow is not null");
            return (Criteria) this;
        }

        public Criteria andAwayyellowEqualTo(Integer value) {
            addCriterion("awayYellow =", value, "awayyellow");
            return (Criteria) this;
        }

        public Criteria andAwayyellowNotEqualTo(Integer value) {
            addCriterion("awayYellow <>", value, "awayyellow");
            return (Criteria) this;
        }

        public Criteria andAwayyellowGreaterThan(Integer value) {
            addCriterion("awayYellow >", value, "awayyellow");
            return (Criteria) this;
        }

        public Criteria andAwayyellowGreaterThanOrEqualTo(Integer value) {
            addCriterion("awayYellow >=", value, "awayyellow");
            return (Criteria) this;
        }

        public Criteria andAwayyellowLessThan(Integer value) {
            addCriterion("awayYellow <", value, "awayyellow");
            return (Criteria) this;
        }

        public Criteria andAwayyellowLessThanOrEqualTo(Integer value) {
            addCriterion("awayYellow <=", value, "awayyellow");
            return (Criteria) this;
        }

        public Criteria andAwayyellowIn(List<Integer> values) {
            addCriterion("awayYellow in", values, "awayyellow");
            return (Criteria) this;
        }

        public Criteria andAwayyellowNotIn(List<Integer> values) {
            addCriterion("awayYellow not in", values, "awayyellow");
            return (Criteria) this;
        }

        public Criteria andAwayyellowBetween(Integer value1, Integer value2) {
            addCriterion("awayYellow between", value1, value2, "awayyellow");
            return (Criteria) this;
        }

        public Criteria andAwayyellowNotBetween(Integer value1, Integer value2) {
            addCriterion("awayYellow not between", value1, value2, "awayyellow");
            return (Criteria) this;
        }

        public Criteria andAwayballrateIsNull() {
            addCriterion("awayBallRate is null");
            return (Criteria) this;
        }

        public Criteria andAwayballrateIsNotNull() {
            addCriterion("awayBallRate is not null");
            return (Criteria) this;
        }

        public Criteria andAwayballrateEqualTo(String value) {
            addCriterion("awayBallRate =", value, "awayballrate");
            return (Criteria) this;
        }

        public Criteria andAwayballrateNotEqualTo(String value) {
            addCriterion("awayBallRate <>", value, "awayballrate");
            return (Criteria) this;
        }

        public Criteria andAwayballrateGreaterThan(String value) {
            addCriterion("awayBallRate >", value, "awayballrate");
            return (Criteria) this;
        }

        public Criteria andAwayballrateGreaterThanOrEqualTo(String value) {
            addCriterion("awayBallRate >=", value, "awayballrate");
            return (Criteria) this;
        }

        public Criteria andAwayballrateLessThan(String value) {
            addCriterion("awayBallRate <", value, "awayballrate");
            return (Criteria) this;
        }

        public Criteria andAwayballrateLessThanOrEqualTo(String value) {
            addCriterion("awayBallRate <=", value, "awayballrate");
            return (Criteria) this;
        }

        public Criteria andAwayballrateLike(String value) {
            addCriterion("awayBallRate like", value, "awayballrate");
            return (Criteria) this;
        }

        public Criteria andAwayballrateNotLike(String value) {
            addCriterion("awayBallRate not like", value, "awayballrate");
            return (Criteria) this;
        }

        public Criteria andAwayballrateIn(List<String> values) {
            addCriterion("awayBallRate in", values, "awayballrate");
            return (Criteria) this;
        }

        public Criteria andAwayballrateNotIn(List<String> values) {
            addCriterion("awayBallRate not in", values, "awayballrate");
            return (Criteria) this;
        }

        public Criteria andAwayballrateBetween(String value1, String value2) {
            addCriterion("awayBallRate between", value1, value2, "awayballrate");
            return (Criteria) this;
        }

        public Criteria andAwayballrateNotBetween(String value1, String value2) {
            addCriterion("awayBallRate not between", value1, value2, "awayballrate");
            return (Criteria) this;
        }

        public Criteria andAwayattackIsNull() {
            addCriterion("awayAttack is null");
            return (Criteria) this;
        }

        public Criteria andAwayattackIsNotNull() {
            addCriterion("awayAttack is not null");
            return (Criteria) this;
        }

        public Criteria andAwayattackEqualTo(Integer value) {
            addCriterion("awayAttack =", value, "awayattack");
            return (Criteria) this;
        }

        public Criteria andAwayattackNotEqualTo(Integer value) {
            addCriterion("awayAttack <>", value, "awayattack");
            return (Criteria) this;
        }

        public Criteria andAwayattackGreaterThan(Integer value) {
            addCriterion("awayAttack >", value, "awayattack");
            return (Criteria) this;
        }

        public Criteria andAwayattackGreaterThanOrEqualTo(Integer value) {
            addCriterion("awayAttack >=", value, "awayattack");
            return (Criteria) this;
        }

        public Criteria andAwayattackLessThan(Integer value) {
            addCriterion("awayAttack <", value, "awayattack");
            return (Criteria) this;
        }

        public Criteria andAwayattackLessThanOrEqualTo(Integer value) {
            addCriterion("awayAttack <=", value, "awayattack");
            return (Criteria) this;
        }

        public Criteria andAwayattackIn(List<Integer> values) {
            addCriterion("awayAttack in", values, "awayattack");
            return (Criteria) this;
        }

        public Criteria andAwayattackNotIn(List<Integer> values) {
            addCriterion("awayAttack not in", values, "awayattack");
            return (Criteria) this;
        }

        public Criteria andAwayattackBetween(Integer value1, Integer value2) {
            addCriterion("awayAttack between", value1, value2, "awayattack");
            return (Criteria) this;
        }

        public Criteria andAwayattackNotBetween(Integer value1, Integer value2) {
            addCriterion("awayAttack not between", value1, value2, "awayattack");
            return (Criteria) this;
        }

        public Criteria andAwaydangerattackIsNull() {
            addCriterion("awayDangerAttack is null");
            return (Criteria) this;
        }

        public Criteria andAwaydangerattackIsNotNull() {
            addCriterion("awayDangerAttack is not null");
            return (Criteria) this;
        }

        public Criteria andAwaydangerattackEqualTo(Integer value) {
            addCriterion("awayDangerAttack =", value, "awaydangerattack");
            return (Criteria) this;
        }

        public Criteria andAwaydangerattackNotEqualTo(Integer value) {
            addCriterion("awayDangerAttack <>", value, "awaydangerattack");
            return (Criteria) this;
        }

        public Criteria andAwaydangerattackGreaterThan(Integer value) {
            addCriterion("awayDangerAttack >", value, "awaydangerattack");
            return (Criteria) this;
        }

        public Criteria andAwaydangerattackGreaterThanOrEqualTo(Integer value) {
            addCriterion("awayDangerAttack >=", value, "awaydangerattack");
            return (Criteria) this;
        }

        public Criteria andAwaydangerattackLessThan(Integer value) {
            addCriterion("awayDangerAttack <", value, "awaydangerattack");
            return (Criteria) this;
        }

        public Criteria andAwaydangerattackLessThanOrEqualTo(Integer value) {
            addCriterion("awayDangerAttack <=", value, "awaydangerattack");
            return (Criteria) this;
        }

        public Criteria andAwaydangerattackIn(List<Integer> values) {
            addCriterion("awayDangerAttack in", values, "awaydangerattack");
            return (Criteria) this;
        }

        public Criteria andAwaydangerattackNotIn(List<Integer> values) {
            addCriterion("awayDangerAttack not in", values, "awaydangerattack");
            return (Criteria) this;
        }

        public Criteria andAwaydangerattackBetween(Integer value1, Integer value2) {
            addCriterion("awayDangerAttack between", value1, value2, "awaydangerattack");
            return (Criteria) this;
        }

        public Criteria andAwaydangerattackNotBetween(Integer value1, Integer value2) {
            addCriterion("awayDangerAttack not between", value1, value2, "awaydangerattack");
            return (Criteria) this;
        }

        public Criteria andAwayshootIsNull() {
            addCriterion("awayShoot is null");
            return (Criteria) this;
        }

        public Criteria andAwayshootIsNotNull() {
            addCriterion("awayShoot is not null");
            return (Criteria) this;
        }

        public Criteria andAwayshootEqualTo(Integer value) {
            addCriterion("awayShoot =", value, "awayshoot");
            return (Criteria) this;
        }

        public Criteria andAwayshootNotEqualTo(Integer value) {
            addCriterion("awayShoot <>", value, "awayshoot");
            return (Criteria) this;
        }

        public Criteria andAwayshootGreaterThan(Integer value) {
            addCriterion("awayShoot >", value, "awayshoot");
            return (Criteria) this;
        }

        public Criteria andAwayshootGreaterThanOrEqualTo(Integer value) {
            addCriterion("awayShoot >=", value, "awayshoot");
            return (Criteria) this;
        }

        public Criteria andAwayshootLessThan(Integer value) {
            addCriterion("awayShoot <", value, "awayshoot");
            return (Criteria) this;
        }

        public Criteria andAwayshootLessThanOrEqualTo(Integer value) {
            addCriterion("awayShoot <=", value, "awayshoot");
            return (Criteria) this;
        }

        public Criteria andAwayshootIn(List<Integer> values) {
            addCriterion("awayShoot in", values, "awayshoot");
            return (Criteria) this;
        }

        public Criteria andAwayshootNotIn(List<Integer> values) {
            addCriterion("awayShoot not in", values, "awayshoot");
            return (Criteria) this;
        }

        public Criteria andAwayshootBetween(Integer value1, Integer value2) {
            addCriterion("awayShoot between", value1, value2, "awayshoot");
            return (Criteria) this;
        }

        public Criteria andAwayshootNotBetween(Integer value1, Integer value2) {
            addCriterion("awayShoot not between", value1, value2, "awayshoot");
            return (Criteria) this;
        }

        public Criteria andAwayshootonIsNull() {
            addCriterion("awayShootOn is null");
            return (Criteria) this;
        }

        public Criteria andAwayshootonIsNotNull() {
            addCriterion("awayShootOn is not null");
            return (Criteria) this;
        }

        public Criteria andAwayshootonEqualTo(Integer value) {
            addCriterion("awayShootOn =", value, "awayshooton");
            return (Criteria) this;
        }

        public Criteria andAwayshootonNotEqualTo(Integer value) {
            addCriterion("awayShootOn <>", value, "awayshooton");
            return (Criteria) this;
        }

        public Criteria andAwayshootonGreaterThan(Integer value) {
            addCriterion("awayShootOn >", value, "awayshooton");
            return (Criteria) this;
        }

        public Criteria andAwayshootonGreaterThanOrEqualTo(Integer value) {
            addCriterion("awayShootOn >=", value, "awayshooton");
            return (Criteria) this;
        }

        public Criteria andAwayshootonLessThan(Integer value) {
            addCriterion("awayShootOn <", value, "awayshooton");
            return (Criteria) this;
        }

        public Criteria andAwayshootonLessThanOrEqualTo(Integer value) {
            addCriterion("awayShootOn <=", value, "awayshooton");
            return (Criteria) this;
        }

        public Criteria andAwayshootonIn(List<Integer> values) {
            addCriterion("awayShootOn in", values, "awayshooton");
            return (Criteria) this;
        }

        public Criteria andAwayshootonNotIn(List<Integer> values) {
            addCriterion("awayShootOn not in", values, "awayshooton");
            return (Criteria) this;
        }

        public Criteria andAwayshootonBetween(Integer value1, Integer value2) {
            addCriterion("awayShootOn between", value1, value2, "awayshooton");
            return (Criteria) this;
        }

        public Criteria andAwayshootonNotBetween(Integer value1, Integer value2) {
            addCriterion("awayShootOn not between", value1, value2, "awayshooton");
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

        public Criteria andMatchidEqualTo(Integer value) {
            addCriterion("matchId =", value, "matchid");
            return (Criteria) this;
        }

        public Criteria andMatchidNotEqualTo(Integer value) {
            addCriterion("matchId <>", value, "matchid");
            return (Criteria) this;
        }

        public Criteria andMatchidGreaterThan(Integer value) {
            addCriterion("matchId >", value, "matchid");
            return (Criteria) this;
        }

        public Criteria andMatchidGreaterThanOrEqualTo(Integer value) {
            addCriterion("matchId >=", value, "matchid");
            return (Criteria) this;
        }

        public Criteria andMatchidLessThan(Integer value) {
            addCriterion("matchId <", value, "matchid");
            return (Criteria) this;
        }

        public Criteria andMatchidLessThanOrEqualTo(Integer value) {
            addCriterion("matchId <=", value, "matchid");
            return (Criteria) this;
        }

        public Criteria andMatchidIn(List<Integer> values) {
            addCriterion("matchId in", values, "matchid");
            return (Criteria) this;
        }

        public Criteria andMatchidNotIn(List<Integer> values) {
            addCriterion("matchId not in", values, "matchid");
            return (Criteria) this;
        }

        public Criteria andMatchidBetween(Integer value1, Integer value2) {
            addCriterion("matchId between", value1, value2, "matchid");
            return (Criteria) this;
        }

        public Criteria andMatchidNotBetween(Integer value1, Integer value2) {
            addCriterion("matchId not between", value1, value2, "matchid");
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