package com.coin.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
public class MatchsExample {
    @Setter
    protected String orderByClause;

    @Setter
    protected boolean distinct;

    protected final List<Criteria> oredCriteria;

    public MatchsExample() {
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

        public Criteria andGametypeIsNull() {
            addCriterion("gameType is null");
            return (Criteria) this;
        }

        public Criteria andGametypeIsNotNull() {
            addCriterion("gameType is not null");
            return (Criteria) this;
        }

        public Criteria andGametypeEqualTo(Integer value) {
            addCriterion("gameType =", value, "gametype");
            return (Criteria) this;
        }

        public Criteria andGametypeNotEqualTo(Integer value) {
            addCriterion("gameType <>", value, "gametype");
            return (Criteria) this;
        }

        public Criteria andGametypeGreaterThan(Integer value) {
            addCriterion("gameType >", value, "gametype");
            return (Criteria) this;
        }

        public Criteria andGametypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("gameType >=", value, "gametype");
            return (Criteria) this;
        }

        public Criteria andGametypeLessThan(Integer value) {
            addCriterion("gameType <", value, "gametype");
            return (Criteria) this;
        }

        public Criteria andGametypeLessThanOrEqualTo(Integer value) {
            addCriterion("gameType <=", value, "gametype");
            return (Criteria) this;
        }

        public Criteria andGametypeIn(List<Integer> values) {
            addCriterion("gameType in", values, "gametype");
            return (Criteria) this;
        }

        public Criteria andGametypeNotIn(List<Integer> values) {
            addCriterion("gameType not in", values, "gametype");
            return (Criteria) this;
        }

        public Criteria andGametypeBetween(Integer value1, Integer value2) {
            addCriterion("gameType between", value1, value2, "gametype");
            return (Criteria) this;
        }

        public Criteria andGametypeNotBetween(Integer value1, Integer value2) {
            addCriterion("gameType not between", value1, value2, "gametype");
            return (Criteria) this;
        }

        public Criteria andMatchtimeIsNull() {
            addCriterion("matchTime is null");
            return (Criteria) this;
        }

        public Criteria andMatchtimeIsNotNull() {
            addCriterion("matchTime is not null");
            return (Criteria) this;
        }

        public Criteria andMatchtimeEqualTo(Integer value) {
            addCriterion("matchTime =", value, "matchtime");
            return (Criteria) this;
        }

        public Criteria andMatchtimeNotEqualTo(Integer value) {
            addCriterion("matchTime <>", value, "matchtime");
            return (Criteria) this;
        }

        public Criteria andMatchtimeGreaterThan(Integer value) {
            addCriterion("matchTime >", value, "matchtime");
            return (Criteria) this;
        }

        public Criteria andMatchtimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("matchTime >=", value, "matchtime");
            return (Criteria) this;
        }

        public Criteria andMatchtimeLessThan(Integer value) {
            addCriterion("matchTime <", value, "matchtime");
            return (Criteria) this;
        }

        public Criteria andMatchtimeLessThanOrEqualTo(Integer value) {
            addCriterion("matchTime <=", value, "matchtime");
            return (Criteria) this;
        }

        public Criteria andMatchtimeIn(List<Integer> values) {
            addCriterion("matchTime in", values, "matchtime");
            return (Criteria) this;
        }

        public Criteria andMatchtimeNotIn(List<Integer> values) {
            addCriterion("matchTime not in", values, "matchtime");
            return (Criteria) this;
        }

        public Criteria andMatchtimeBetween(Integer value1, Integer value2) {
            addCriterion("matchTime between", value1, value2, "matchtime");
            return (Criteria) this;
        }

        public Criteria andMatchtimeNotBetween(Integer value1, Integer value2) {
            addCriterion("matchTime not between", value1, value2, "matchtime");
            return (Criteria) this;
        }

        public Criteria andLeagueidIsNull() {
            addCriterion("leagueId is null");
            return (Criteria) this;
        }

        public Criteria andLeagueidIsNotNull() {
            addCriterion("leagueId is not null");
            return (Criteria) this;
        }

        public Criteria andLeagueidEqualTo(String value) {
            addCriterion("leagueId =", value, "leagueid");
            return (Criteria) this;
        }

        public Criteria andLeagueidNotEqualTo(String value) {
            addCriterion("leagueId <>", value, "leagueid");
            return (Criteria) this;
        }

        public Criteria andLeagueidGreaterThan(String value) {
            addCriterion("leagueId >", value, "leagueid");
            return (Criteria) this;
        }

        public Criteria andLeagueidGreaterThanOrEqualTo(String value) {
            addCriterion("leagueId >=", value, "leagueid");
            return (Criteria) this;
        }

        public Criteria andLeagueidLessThan(String value) {
            addCriterion("leagueId <", value, "leagueid");
            return (Criteria) this;
        }

        public Criteria andLeagueidLessThanOrEqualTo(String value) {
            addCriterion("leagueId <=", value, "leagueid");
            return (Criteria) this;
        }

        public Criteria andLeagueidLike(String value) {
            addCriterion("leagueId like", value, "leagueid");
            return (Criteria) this;
        }

        public Criteria andLeagueidNotLike(String value) {
            addCriterion("leagueId not like", value, "leagueid");
            return (Criteria) this;
        }

        public Criteria andLeagueidIn(List<String> values) {
            addCriterion("leagueId in", values, "leagueid");
            return (Criteria) this;
        }

        public Criteria andLeagueidNotIn(List<String> values) {
            addCriterion("leagueId not in", values, "leagueid");
            return (Criteria) this;
        }

        public Criteria andLeagueidBetween(String value1, String value2) {
            addCriterion("leagueId between", value1, value2, "leagueid");
            return (Criteria) this;
        }

        public Criteria andLeagueidNotBetween(String value1, String value2) {
            addCriterion("leagueId not between", value1, value2, "leagueid");
            return (Criteria) this;
        }

        public Criteria andLeaguenameIsNull() {
            addCriterion("leagueName is null");
            return (Criteria) this;
        }

        public Criteria andLeaguenameIsNotNull() {
            addCriterion("leagueName is not null");
            return (Criteria) this;
        }

        public Criteria andLeaguenameEqualTo(String value) {
            addCriterion("leagueName =", value, "leaguename");
            return (Criteria) this;
        }

        public Criteria andLeaguenameNotEqualTo(String value) {
            addCriterion("leagueName <>", value, "leaguename");
            return (Criteria) this;
        }

        public Criteria andLeaguenameGreaterThan(String value) {
            addCriterion("leagueName >", value, "leaguename");
            return (Criteria) this;
        }

        public Criteria andLeaguenameGreaterThanOrEqualTo(String value) {
            addCriterion("leagueName >=", value, "leaguename");
            return (Criteria) this;
        }

        public Criteria andLeaguenameLessThan(String value) {
            addCriterion("leagueName <", value, "leaguename");
            return (Criteria) this;
        }

        public Criteria andLeaguenameLessThanOrEqualTo(String value) {
            addCriterion("leagueName <=", value, "leaguename");
            return (Criteria) this;
        }

        public Criteria andLeaguenameLike(String value) {
            addCriterion("leagueName like", value, "leaguename");
            return (Criteria) this;
        }

        public Criteria andLeaguenameNotLike(String value) {
            addCriterion("leagueName not like", value, "leaguename");
            return (Criteria) this;
        }

        public Criteria andLeaguenameIn(List<String> values) {
            addCriterion("leagueName in", values, "leaguename");
            return (Criteria) this;
        }

        public Criteria andLeaguenameNotIn(List<String> values) {
            addCriterion("leagueName not in", values, "leaguename");
            return (Criteria) this;
        }

        public Criteria andLeaguenameBetween(String value1, String value2) {
            addCriterion("leagueName between", value1, value2, "leaguename");
            return (Criteria) this;
        }

        public Criteria andLeaguenameNotBetween(String value1, String value2) {
            addCriterion("leagueName not between", value1, value2, "leaguename");
            return (Criteria) this;
        }

        public Criteria andLeaguelogoIsNull() {
            addCriterion("leagueLogo is null");
            return (Criteria) this;
        }

        public Criteria andLeaguelogoIsNotNull() {
            addCriterion("leagueLogo is not null");
            return (Criteria) this;
        }

        public Criteria andLeaguelogoEqualTo(String value) {
            addCriterion("leagueLogo =", value, "leaguelogo");
            return (Criteria) this;
        }

        public Criteria andLeaguelogoNotEqualTo(String value) {
            addCriterion("leagueLogo <>", value, "leaguelogo");
            return (Criteria) this;
        }

        public Criteria andLeaguelogoGreaterThan(String value) {
            addCriterion("leagueLogo >", value, "leaguelogo");
            return (Criteria) this;
        }

        public Criteria andLeaguelogoGreaterThanOrEqualTo(String value) {
            addCriterion("leagueLogo >=", value, "leaguelogo");
            return (Criteria) this;
        }

        public Criteria andLeaguelogoLessThan(String value) {
            addCriterion("leagueLogo <", value, "leaguelogo");
            return (Criteria) this;
        }

        public Criteria andLeaguelogoLessThanOrEqualTo(String value) {
            addCriterion("leagueLogo <=", value, "leaguelogo");
            return (Criteria) this;
        }

        public Criteria andLeaguelogoLike(String value) {
            addCriterion("leagueLogo like", value, "leaguelogo");
            return (Criteria) this;
        }

        public Criteria andLeaguelogoNotLike(String value) {
            addCriterion("leagueLogo not like", value, "leaguelogo");
            return (Criteria) this;
        }

        public Criteria andLeaguelogoIn(List<String> values) {
            addCriterion("leagueLogo in", values, "leaguelogo");
            return (Criteria) this;
        }

        public Criteria andLeaguelogoNotIn(List<String> values) {
            addCriterion("leagueLogo not in", values, "leaguelogo");
            return (Criteria) this;
        }

        public Criteria andLeaguelogoBetween(String value1, String value2) {
            addCriterion("leagueLogo between", value1, value2, "leaguelogo");
            return (Criteria) this;
        }

        public Criteria andLeaguelogoNotBetween(String value1, String value2) {
            addCriterion("leagueLogo not between", value1, value2, "leaguelogo");
            return (Criteria) this;
        }

        public Criteria andStatusidIsNull() {
            addCriterion("statusId is null");
            return (Criteria) this;
        }

        public Criteria andStatusidIsNotNull() {
            addCriterion("statusId is not null");
            return (Criteria) this;
        }

        public Criteria andStatusidEqualTo(Integer value) {
            addCriterion("statusId =", value, "statusid");
            return (Criteria) this;
        }

        public Criteria andStatusidNotEqualTo(Integer value) {
            addCriterion("statusId <>", value, "statusid");
            return (Criteria) this;
        }

        public Criteria andStatusidGreaterThan(Integer value) {
            addCriterion("statusId >", value, "statusid");
            return (Criteria) this;
        }

        public Criteria andStatusidGreaterThanOrEqualTo(Integer value) {
            addCriterion("statusId >=", value, "statusid");
            return (Criteria) this;
        }

        public Criteria andStatusidLessThan(Integer value) {
            addCriterion("statusId <", value, "statusid");
            return (Criteria) this;
        }

        public Criteria andStatusidLessThanOrEqualTo(Integer value) {
            addCriterion("statusId <=", value, "statusid");
            return (Criteria) this;
        }

        public Criteria andStatusidIn(List<Integer> values) {
            addCriterion("statusId in", values, "statusid");
            return (Criteria) this;
        }

        public Criteria andStatusidNotIn(List<Integer> values) {
            addCriterion("statusId not in", values, "statusid");
            return (Criteria) this;
        }

        public Criteria andStatusidBetween(Integer value1, Integer value2) {
            addCriterion("statusId between", value1, value2, "statusid");
            return (Criteria) this;
        }

        public Criteria andStatusidNotBetween(Integer value1, Integer value2) {
            addCriterion("statusId not between", value1, value2, "statusid");
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

        public Criteria andStatusEqualTo(String value) {
            addCriterion("`status` =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(String value) {
            addCriterion("`status` <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(String value) {
            addCriterion("`status` >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(String value) {
            addCriterion("`status` >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(String value) {
            addCriterion("`status` <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(String value) {
            addCriterion("`status` <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLike(String value) {
            addCriterion("`status` like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotLike(String value) {
            addCriterion("`status` not like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<String> values) {
            addCriterion("`status` in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<String> values) {
            addCriterion("`status` not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(String value1, String value2) {
            addCriterion("`status` between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(String value1, String value2) {
            addCriterion("`status` not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andPlayingIsNull() {
            addCriterion("playing is null");
            return (Criteria) this;
        }

        public Criteria andPlayingIsNotNull() {
            addCriterion("playing is not null");
            return (Criteria) this;
        }

        public Criteria andPlayingEqualTo(Byte value) {
            addCriterion("playing =", value, "playing");
            return (Criteria) this;
        }

        public Criteria andPlayingNotEqualTo(Byte value) {
            addCriterion("playing <>", value, "playing");
            return (Criteria) this;
        }

        public Criteria andPlayingGreaterThan(Byte value) {
            addCriterion("playing >", value, "playing");
            return (Criteria) this;
        }

        public Criteria andPlayingGreaterThanOrEqualTo(Byte value) {
            addCriterion("playing >=", value, "playing");
            return (Criteria) this;
        }

        public Criteria andPlayingLessThan(Byte value) {
            addCriterion("playing <", value, "playing");
            return (Criteria) this;
        }

        public Criteria andPlayingLessThanOrEqualTo(Byte value) {
            addCriterion("playing <=", value, "playing");
            return (Criteria) this;
        }

        public Criteria andPlayingIn(List<Byte> values) {
            addCriterion("playing in", values, "playing");
            return (Criteria) this;
        }

        public Criteria andPlayingNotIn(List<Byte> values) {
            addCriterion("playing not in", values, "playing");
            return (Criteria) this;
        }

        public Criteria andPlayingBetween(Byte value1, Byte value2) {
            addCriterion("playing between", value1, value2, "playing");
            return (Criteria) this;
        }

        public Criteria andPlayingNotBetween(Byte value1, Byte value2) {
            addCriterion("playing not between", value1, value2, "playing");
            return (Criteria) this;
        }

        public Criteria andVideosIsNull() {
            addCriterion("videos is null");
            return (Criteria) this;
        }

        public Criteria andVideosIsNotNull() {
            addCriterion("videos is not null");
            return (Criteria) this;
        }

        public Criteria andVideosEqualTo(String value) {
            addCriterion("videos =", value, "videos");
            return (Criteria) this;
        }

        public Criteria andVideosNotEqualTo(String value) {
            addCriterion("videos <>", value, "videos");
            return (Criteria) this;
        }

        public Criteria andVideosGreaterThan(String value) {
            addCriterion("videos >", value, "videos");
            return (Criteria) this;
        }

        public Criteria andVideosGreaterThanOrEqualTo(String value) {
            addCriterion("videos >=", value, "videos");
            return (Criteria) this;
        }

        public Criteria andVideosLessThan(String value) {
            addCriterion("videos <", value, "videos");
            return (Criteria) this;
        }

        public Criteria andVideosLessThanOrEqualTo(String value) {
            addCriterion("videos <=", value, "videos");
            return (Criteria) this;
        }

        public Criteria andVideosLike(String value) {
            addCriterion("videos like", value, "videos");
            return (Criteria) this;
        }

        public Criteria andVideosNotLike(String value) {
            addCriterion("videos not like", value, "videos");
            return (Criteria) this;
        }

        public Criteria andVideosIn(List<String> values) {
            addCriterion("videos in", values, "videos");
            return (Criteria) this;
        }

        public Criteria andVideosNotIn(List<String> values) {
            addCriterion("videos not in", values, "videos");
            return (Criteria) this;
        }

        public Criteria andVideosBetween(String value1, String value2) {
            addCriterion("videos between", value1, value2, "videos");
            return (Criteria) this;
        }

        public Criteria andVideosNotBetween(String value1, String value2) {
            addCriterion("videos not between", value1, value2, "videos");
            return (Criteria) this;
        }

        public Criteria andThirdidIsNull() {
            addCriterion("thirdId is null");
            return (Criteria) this;
        }

        public Criteria andThirdidIsNotNull() {
            addCriterion("thirdId is not null");
            return (Criteria) this;
        }

        public Criteria andThirdidEqualTo(String value) {
            addCriterion("thirdId =", value, "thirdid");
            return (Criteria) this;
        }

        public Criteria andThirdidNotEqualTo(String value) {
            addCriterion("thirdId <>", value, "thirdid");
            return (Criteria) this;
        }

        public Criteria andThirdidGreaterThan(String value) {
            addCriterion("thirdId >", value, "thirdid");
            return (Criteria) this;
        }

        public Criteria andThirdidGreaterThanOrEqualTo(String value) {
            addCriterion("thirdId >=", value, "thirdid");
            return (Criteria) this;
        }

        public Criteria andThirdidLessThan(String value) {
            addCriterion("thirdId <", value, "thirdid");
            return (Criteria) this;
        }

        public Criteria andThirdidLessThanOrEqualTo(String value) {
            addCriterion("thirdId <=", value, "thirdid");
            return (Criteria) this;
        }

        public Criteria andThirdidLike(String value) {
            addCriterion("thirdId like", value, "thirdid");
            return (Criteria) this;
        }

        public Criteria andThirdidNotLike(String value) {
            addCriterion("thirdId not like", value, "thirdid");
            return (Criteria) this;
        }

        public Criteria andThirdidIn(List<String> values) {
            addCriterion("thirdId in", values, "thirdid");
            return (Criteria) this;
        }

        public Criteria andThirdidNotIn(List<String> values) {
            addCriterion("thirdId not in", values, "thirdid");
            return (Criteria) this;
        }

        public Criteria andThirdidBetween(String value1, String value2) {
            addCriterion("thirdId between", value1, value2, "thirdid");
            return (Criteria) this;
        }

        public Criteria andThirdidNotBetween(String value1, String value2) {
            addCriterion("thirdId not between", value1, value2, "thirdid");
            return (Criteria) this;
        }

        public Criteria andLineupIsNull() {
            addCriterion("lineUp is null");
            return (Criteria) this;
        }

        public Criteria andLineupIsNotNull() {
            addCriterion("lineUp is not null");
            return (Criteria) this;
        }

        public Criteria andLineupEqualTo(Byte value) {
            addCriterion("lineUp =", value, "lineup");
            return (Criteria) this;
        }

        public Criteria andLineupNotEqualTo(Byte value) {
            addCriterion("lineUp <>", value, "lineup");
            return (Criteria) this;
        }

        public Criteria andLineupGreaterThan(Byte value) {
            addCriterion("lineUp >", value, "lineup");
            return (Criteria) this;
        }

        public Criteria andLineupGreaterThanOrEqualTo(Byte value) {
            addCriterion("lineUp >=", value, "lineup");
            return (Criteria) this;
        }

        public Criteria andLineupLessThan(Byte value) {
            addCriterion("lineUp <", value, "lineup");
            return (Criteria) this;
        }

        public Criteria andLineupLessThanOrEqualTo(Byte value) {
            addCriterion("lineUp <=", value, "lineup");
            return (Criteria) this;
        }

        public Criteria andLineupIn(List<Byte> values) {
            addCriterion("lineUp in", values, "lineup");
            return (Criteria) this;
        }

        public Criteria andLineupNotIn(List<Byte> values) {
            addCriterion("lineUp not in", values, "lineup");
            return (Criteria) this;
        }

        public Criteria andLineupBetween(Byte value1, Byte value2) {
            addCriterion("lineUp between", value1, value2, "lineup");
            return (Criteria) this;
        }

        public Criteria andLineupNotBetween(Byte value1, Byte value2) {
            addCriterion("lineUp not between", value1, value2, "lineup");
            return (Criteria) this;
        }

        public Criteria andPublishIsNull() {
            addCriterion("publish is null");
            return (Criteria) this;
        }

        public Criteria andPublishIsNotNull() {
            addCriterion("publish is not null");
            return (Criteria) this;
        }

        public Criteria andPublishEqualTo(Integer value) {
            addCriterion("publish =", value, "publish");
            return (Criteria) this;
        }

        public Criteria andPublishNotEqualTo(Integer value) {
            addCriterion("publish <>", value, "publish");
            return (Criteria) this;
        }

        public Criteria andPublishGreaterThan(Integer value) {
            addCriterion("publish >", value, "publish");
            return (Criteria) this;
        }

        public Criteria andPublishGreaterThanOrEqualTo(Integer value) {
            addCriterion("publish >=", value, "publish");
            return (Criteria) this;
        }

        public Criteria andPublishLessThan(Integer value) {
            addCriterion("publish <", value, "publish");
            return (Criteria) this;
        }

        public Criteria andPublishLessThanOrEqualTo(Integer value) {
            addCriterion("publish <=", value, "publish");
            return (Criteria) this;
        }

        public Criteria andPublishIn(List<Integer> values) {
            addCriterion("publish in", values, "publish");
            return (Criteria) this;
        }

        public Criteria andPublishNotIn(List<Integer> values) {
            addCriterion("publish not in", values, "publish");
            return (Criteria) this;
        }

        public Criteria andPublishBetween(Integer value1, Integer value2) {
            addCriterion("publish between", value1, value2, "publish");
            return (Criteria) this;
        }

        public Criteria andPublishNotBetween(Integer value1, Integer value2) {
            addCriterion("publish not between", value1, value2, "publish");
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

        public Criteria andRidIsNull() {
            addCriterion("rid is null");
            return (Criteria) this;
        }

        public Criteria andRidIsNotNull() {
            addCriterion("rid is not null");
            return (Criteria) this;
        }

        public Criteria andRidEqualTo(String value) {
            addCriterion("rid =", value, "rid");
            return (Criteria) this;
        }

        public Criteria andRidNotEqualTo(String value) {
            addCriterion("rid <>", value, "rid");
            return (Criteria) this;
        }

        public Criteria andRidGreaterThan(String value) {
            addCriterion("rid >", value, "rid");
            return (Criteria) this;
        }

        public Criteria andRidGreaterThanOrEqualTo(String value) {
            addCriterion("rid >=", value, "rid");
            return (Criteria) this;
        }

        public Criteria andRidLessThan(String value) {
            addCriterion("rid <", value, "rid");
            return (Criteria) this;
        }

        public Criteria andRidLessThanOrEqualTo(String value) {
            addCriterion("rid <=", value, "rid");
            return (Criteria) this;
        }

        public Criteria andRidLike(String value) {
            addCriterion("rid like", value, "rid");
            return (Criteria) this;
        }

        public Criteria andRidNotLike(String value) {
            addCriterion("rid not like", value, "rid");
            return (Criteria) this;
        }

        public Criteria andRidIn(List<String> values) {
            addCriterion("rid in", values, "rid");
            return (Criteria) this;
        }

        public Criteria andRidNotIn(List<String> values) {
            addCriterion("rid not in", values, "rid");
            return (Criteria) this;
        }

        public Criteria andRidBetween(String value1, String value2) {
            addCriterion("rid between", value1, value2, "rid");
            return (Criteria) this;
        }

        public Criteria andRidNotBetween(String value1, String value2) {
            addCriterion("rid not between", value1, value2, "rid");
            return (Criteria) this;
        }

        public Criteria andIshomeIsNull() {
            addCriterion("isHome is null");
            return (Criteria) this;
        }

        public Criteria andIshomeIsNotNull() {
            addCriterion("isHome is not null");
            return (Criteria) this;
        }

        public Criteria andIshomeEqualTo(Integer value) {
            addCriterion("isHome =", value, "ishome");
            return (Criteria) this;
        }

        public Criteria andIshomeNotEqualTo(Integer value) {
            addCriterion("isHome <>", value, "ishome");
            return (Criteria) this;
        }

        public Criteria andIshomeGreaterThan(Integer value) {
            addCriterion("isHome >", value, "ishome");
            return (Criteria) this;
        }

        public Criteria andIshomeGreaterThanOrEqualTo(Integer value) {
            addCriterion("isHome >=", value, "ishome");
            return (Criteria) this;
        }

        public Criteria andIshomeLessThan(Integer value) {
            addCriterion("isHome <", value, "ishome");
            return (Criteria) this;
        }

        public Criteria andIshomeLessThanOrEqualTo(Integer value) {
            addCriterion("isHome <=", value, "ishome");
            return (Criteria) this;
        }

        public Criteria andIshomeIn(List<Integer> values) {
            addCriterion("isHome in", values, "ishome");
            return (Criteria) this;
        }

        public Criteria andIshomeNotIn(List<Integer> values) {
            addCriterion("isHome not in", values, "ishome");
            return (Criteria) this;
        }

        public Criteria andIshomeBetween(Integer value1, Integer value2) {
            addCriterion("isHome between", value1, value2, "ishome");
            return (Criteria) this;
        }

        public Criteria andIshomeNotBetween(Integer value1, Integer value2) {
            addCriterion("isHome not between", value1, value2, "ishome");
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

        public Criteria andVideoUrlIsNull() {
            addCriterion("video_url is null");
            return (Criteria) this;
        }

        public Criteria andVideoUrlIsNotNull() {
            addCriterion("video_url is not null");
            return (Criteria) this;
        }

        public Criteria andVideoUrlEqualTo(String value) {
            addCriterion("video_url =", value, "videoUrl");
            return (Criteria) this;
        }

        public Criteria andVideoUrlNotEqualTo(String value) {
            addCriterion("video_url <>", value, "videoUrl");
            return (Criteria) this;
        }

        public Criteria andVideoUrlGreaterThan(String value) {
            addCriterion("video_url >", value, "videoUrl");
            return (Criteria) this;
        }

        public Criteria andVideoUrlGreaterThanOrEqualTo(String value) {
            addCriterion("video_url >=", value, "videoUrl");
            return (Criteria) this;
        }

        public Criteria andVideoUrlLessThan(String value) {
            addCriterion("video_url <", value, "videoUrl");
            return (Criteria) this;
        }

        public Criteria andVideoUrlLessThanOrEqualTo(String value) {
            addCriterion("video_url <=", value, "videoUrl");
            return (Criteria) this;
        }

        public Criteria andVideoUrlLike(String value) {
            addCriterion("video_url like", value, "videoUrl");
            return (Criteria) this;
        }

        public Criteria andVideoUrlNotLike(String value) {
            addCriterion("video_url not like", value, "videoUrl");
            return (Criteria) this;
        }

        public Criteria andVideoUrlIn(List<String> values) {
            addCriterion("video_url in", values, "videoUrl");
            return (Criteria) this;
        }

        public Criteria andVideoUrlNotIn(List<String> values) {
            addCriterion("video_url not in", values, "videoUrl");
            return (Criteria) this;
        }

        public Criteria andVideoUrlBetween(String value1, String value2) {
            addCriterion("video_url between", value1, value2, "videoUrl");
            return (Criteria) this;
        }

        public Criteria andVideoUrlNotBetween(String value1, String value2) {
            addCriterion("video_url not between", value1, value2, "videoUrl");
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