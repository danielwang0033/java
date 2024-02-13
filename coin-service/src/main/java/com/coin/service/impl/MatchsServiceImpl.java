package com.coin.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.*;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.coin.entity.*;
import com.coin.mapper.*;
import com.coin.mapper.ext.MatchLeaguesExtMapper;
import com.coin.mapper.ext.MatchScoresExtMapper;
import com.coin.mapper.ext.MatchVideosExtMapper;
import com.coin.mapper.ext.MatchsExtMapper;
import com.coin.req.MatchsReq;
import com.coin.req.RoomsReq;
import com.coin.resp.LeaguesResp;
import com.coin.resp.MatchScoresResp;
import com.coin.resp.MatchsResp;
import com.coin.resp.match.MatchIdAndStatusVo;
import com.coin.service.ChatsService;
import com.coin.service.MatchScoresService;
import com.coin.service.MatchsService;
import com.coin.service.constant.CodeCons;
import com.coin.service.exception.BizException;
import com.coin.service.util.HttpClientUtil;
import com.coin.i18n.I18nUtil;
import com.coin.service.util.MD5Util;
import com.coin.service.util.PageUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class MatchsServiceImpl implements MatchsService {

    private static final Logger logger = LoggerFactory.getLogger(MatchsServiceImpl.class);

    @Resource
    private MatchsMapper matchsMapper;
    @Resource
    private MatchsExtMapper matchsExtMapper;

    @Value("${sport.sportDomain}")
    private String sportDomain;
    @Value("${sport.sportKey}")
    private String sportKey;
    @Value("${sport.sportTkey}")
    private String sportTkey;

    @Value("${sport.sportStreamAk}")
    private String sportStreamAk;
    @Value("${sport.sportStreamSk}")
    private String sportStreamSk;
    @Value("${sport.sportStreamDomain}")
    private String sportStreamDomain;
    @Value("${sport.sportStreamAppname}")
    private String sportStreamAppname;
    @Value("${sport.sportStreamKey}")
    private String sportStreamKey;
    @Value("${chat.rcRid}")
    private String roomRid;

    @Resource
    private MatchScoresMapper matchScoresMapper;
    @Resource
    private MatchBbtechsMapper matchBbtechsMapper;
    @Resource
    private MatchFbtechsMapper matchFbtechsMapper;
    @Resource
    private ChatsService chatsService;
    @Resource
    private MatchScoresService matchScoresService;
    @Resource
    private MatchVideosMapper matchVideosMapper;
    @Resource
    private MatchVideosExtMapper matchVideosExtMapper;
    @Resource
    private RoomsMapper roomsMapper;
    @Resource
    private MatchScoresExtMapper matchScoresExtMapper;
    @Resource
    private MatchLeaguesMapper matchLeaguesMapper;
    @Resource
    private MatchLeaguesExtMapper matchLeaguesExtMapper;

    private static void restoreOldValue(MatchIdAndStatusVo oldMatch, Matchs matchs) {
        if (ObjectUtil.isNotNull(oldMatch)) {
            Integer sort = oldMatch.getSort();
            if (ObjectUtil.isNotNull(sort)) {
                matchs.setSort(sort);
            } else {
                matchs.setSort(999999);
            }
            String videoUrl = oldMatch.getVideoUrl();
            if (StrUtil.isNotEmpty(videoUrl)) {
                matchs.setVideoUrl(videoUrl);
            }
            String streamName = oldMatch.getStreamName();
            if (StrUtil.isNotEmpty(streamName)) {
                matchs.setStreamname(streamName);
            }
            String rid = oldMatch.getRid();
            if (StrUtil.isNotEmpty(rid)) {
                matchs.setRid(rid);
            }
            Integer publish = oldMatch.getPublish();
            if (ObjectUtil.isNotNull(publish)) {
                matchs.setPublish(publish);
            }
            // 还原playing字段
            if (ObjectUtil.isNull(matchs.getPlaying())) {
                matchs.setPlaying(new Byte(oldMatch.getPlaying() + ""));
            }
        }
        // 设置默认排序
        if (ObjectUtil.isNotNull(matchs) && ObjectUtil.isNull(matchs.getSort())) {
            matchs.setSort(999999);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void batchDelete() {
        Date now = new Date();

        MatchsExample example = new MatchsExample();
        MatchsExample.Criteria criteria = example.createCriteria();
        // long time = com.coin.service.util.DateUtil.addDays(com.coin.service.util.DateUtil.getNoTimeDate(new Date()), -2).getTime();
        criteria.andMatchtimeLessThanOrEqualTo((int) (now.getTime() / 1000) - 3 * 86400);
        List<Matchs> matchsList = matchsMapper.selectByExample(example);
        for (Matchs temps : matchsList) {
            String matchId = temps.getMatchid();
            String rid = temps.getRid();
            matchsMapper.deleteByPrimaryKey(temps.getId());

            MatchScoresExample example2 = new MatchScoresExample();
            MatchScoresExample.Criteria criteria2 = example2.createCriteria();
            criteria2.andMatchidEqualTo(matchId);
            matchScoresMapper.deleteByExample(example2);

            if (StrUtil.isNotEmpty(rid)) {
                RoomsExample example3 = new RoomsExample();
                RoomsExample.Criteria criteria3 = example3.createCriteria();
                criteria3.andRidEqualTo(rid);
                roomsMapper.deleteByExample(example3);

                if (roomRid == null) {
                    RoomsReq req = new RoomsReq();
                    req.setRid(rid);
                    chatsService.deleteRoom(req);
                }
            }
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void batchAddStream() {
        JSONArray footballData = getData("/sports/stream/match/football/list");
        JSONArray basketballData = getData("/sports/stream/match/basketball/list");
        flushMatchData(footballData, basketballData);
    }

    public String getVideoUrl(String streamName) {
        String domain = sportStreamDomain;
        String appName = sportStreamAppname;
        String key = sportStreamKey;
        Date now = new Date();
        int time = DateUtil.timeToSecond(String.valueOf(now.getTime() / 1000));
        String txSecret = MD5Util.MD5(key + streamName + Integer.toHexString(time));
        String txTime = Integer.toHexString(time);
        return domain + "/" + appName + "/" + streamName + ".m3u8?txSecret=" + txSecret + "&txTime=" + txTime;
    }

    public MatchScores process(JSONObject home, String matchId, Integer type) {
        Date now = new Date();
        MatchScores matchScores = new MatchScores();
        matchScores.setId(home.getStr("id"));
        matchScores.setMatchid(matchId);
        matchScores.setType(type);
        matchScores.setName(home.getStr("name"));
        matchScores.setLogo(home.getStr("logo"));
        matchScores.setScore(home.getInt("score"));
        matchScores.setOvertimescore(home.getInt("overTimeScore"));
        matchScores.setHalfscore(home.getInt("halfScore"));
        matchScores.setPenaltyscore(home.getInt("penaltyScore"));
        matchScores.setFirstscore(home.getInt("firstScore"));
        matchScores.setSecondscore(home.getInt("secondScore"));
        matchScores.setThirdscore(home.getInt("thirdScore"));
        matchScores.setFourthscore(home.getInt("fourthScore"));
        matchScores.setCreatedAt(now);
        matchScores.setUpdatedAt(now);
        return matchScores;
    }

    //@Transactional(rollbackFor = Exception.class)
    @Override
    public void update(MatchsReq req) {
        Matchs oldContest = this.getByMatchId(req.getMatchId());
        if (ObjectUtil.isNull(oldContest)) {
            throw new BizException(CodeCons.ERROR, "记录不存在");
        }
        Date now = new Date();
        Matchs updateMatchs = new Matchs();
        if (ObjectUtil.isNotNull(req.getPublish())) {
            updateMatchs.setPublish(req.getPublish());
            this.publish(req);
        }
        if (ObjectUtil.isNotNull(req.getIsHome())) {
            updateMatchs.setIshome(req.getIsHome());
        }
        if (StrUtil.isNotBlank(req.getStreamName())) {
            req.setVideoUrl(getVideoUrl(req.getStreamName()));
            updateMatchs.setStreamname(req.getStreamName());

            MatchVideosExample example = new MatchVideosExample();
            MatchVideosExample.Criteria criteria = example.createCriteria();
            criteria.andStreamnameEqualTo(req.getStreamName());
            List<MatchVideos> matchVideosList = matchVideosMapper.selectByExample(example);
            if (CollectionUtil.isEmpty(matchVideosList)) {
                throw new BizException(CodeCons.ERROR, "流不存在");
            }
            MatchVideos matchVideos = matchVideosList.get(0);
            updateMatchs.setPlaying((byte) (matchVideos.getPushing() == 1 ? 1 : 0));
            updateMatchs.setVideoUrl(req.getVideoUrl());
        }
        if (ObjectUtil.isNotNull(req.getSort())) {
            updateMatchs.setSort(req.getSort());
        }
        updateMatchs.setUpdatedAt(now);

        MatchsExample example = new MatchsExample();
        MatchsExample.Criteria criteria = example.createCriteria();
        criteria.andMatchidEqualTo(req.getMatchId());
        matchsMapper.updateByExampleSelective(updateMatchs, example);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void publish(MatchsReq req) {
        Matchs oldContest = this.getByMatchId(req.getMatchId());
        if (ObjectUtil.isNull(oldContest)) {
            throw new BizException(CodeCons.ERROR, "记录不存在");
        }
        //检查发布条件（有视频源，或 非完成）
        if (req.getPublish() == 1) {
            if (oldContest.getStatusid() == -1) {
                throw new BizException(CodeCons.ERROR, "比赛已完成");
            }
        }
        //创建聊天室
        if (roomRid == null) {
            req.setMatchId(oldContest.getMatchid());
            roomRid = chatsService.createRoom(req);
        }
        Date now = new Date();
        Matchs updateMatchs = new Matchs();
        updateMatchs.setId(req.getId());
        updateMatchs.setPublish(req.getPublish());
        updateMatchs.setUpdatedAt(now);
        updateMatchs.setRid(roomRid);

        MatchsExample example = new MatchsExample();
        MatchsExample.Criteria criteria = example.createCriteria();
        criteria.andMatchidEqualTo(req.getMatchId());
        matchsMapper.updateByExampleSelective(updateMatchs, example);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(MatchsReq req) {
        Long id = req.getId();
        matchsMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Matchs getById(Long id) {
        Matchs matchs = matchsMapper.selectByPrimaryKey(id);
        if (ObjectUtil.isNull(matchs)) {
            throw new BizException(CodeCons.ERROR, "记录不存在");
        }
        return matchs;
    }

    @Override
    public Matchs getByMatchId(String matchId) {
        MatchsExample example = new MatchsExample();
        MatchsExample.Criteria criteria = example.createCriteria();
        criteria.andMatchidEqualTo(matchId);
        List<Matchs> list = matchsMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public MatchsResp getDetailByMatchId(String matchId) {
        MatchsExample example = new MatchsExample();
        MatchsExample.Criteria criteria = example.createCriteria();
        criteria.andMatchidEqualTo(matchId);
        criteria.andPublishEqualTo(1);
        // criteria.andStatusidNotEqualTo(-1);
        List<Matchs> datas = matchsMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(datas)) {
            return null;
        }
        List<MatchsResp> list = datas.stream().map(this::convertRsp).collect(Collectors.toList());
        fillTeamName(list);
        MatchsResp matchsResp = list.get(0);
        if (ObjectUtil.isNull(matchsResp)){
            // 取配置文件中房间id
            matchsResp.setRid(roomRid);
            return matchsResp;
        }
        return null;
    }

    @Override
    public List<LeaguesResp> LeaguePageList(MatchsReq req) {
        return matchsExtMapper.selectLeague();
    }

    @Override
    public PageInfo<MatchsResp> pageList(MatchsReq req) {
        MatchsExample example = new MatchsExample();
        MatchsExample.Criteria criteria = example.createCriteria();
        if (ObjectUtil.isNotNull(req.getMatchId())) {
            criteria.andMatchidEqualTo(req.getMatchId());
        }
        if (ObjectUtil.isNotNull(req.getStarting())) {
            criteria.andStatusidNotIn(Arrays.asList(0, -1));
        }
        if (ObjectUtil.isNotNull(req.getPlaying())) {
            criteria.andPlayingEqualTo(new Byte(req.getPlaying() + ""));
        }
        if (ObjectUtil.isNotNull(req.getPublish())) {
            criteria.andPublishEqualTo(req.getPublish());
        }
        if (ObjectUtil.isNotNull(req.getGameType())) {
            criteria.andGametypeEqualTo(req.getGameType());
        }
        if (ObjectUtil.isNotNull(req.getMatchTimeGe())) {
            criteria.andMatchtimeGreaterThanOrEqualTo(req.getMatchTimeGe());
        }
        if (ObjectUtil.isNotNull(req.getMatchTimeLe())) {
            criteria.andMatchtimeLessThanOrEqualTo(req.getMatchTimeLe());
        }
        if (ObjectUtil.isNotNull(req.getGameType())) {
            criteria.andGametypeEqualTo(req.getGameType());
        }
        if (StrUtil.isNotBlank(req.getLeagueName())) {
            criteria.andLeaguenameLike("%" + req.getLeagueName() + "%");
        }
        if (StrUtil.isNotBlank(req.getLeagueId())) {
            criteria.andLeagueidEqualTo(req.getLeagueId());
        }
        if (StrUtil.isNotEmpty(req.getStatusIds())) {
            String[] idStrs = req.getStatusIds().split(",");
            List<Integer> ids = new ArrayList<>();
            for (String id : idStrs) {
                ids.add(Integer.valueOf(id));
            }
            criteria.andStatusidIn(ids);
        }
        if (ObjectUtil.isNotNull(req.getStatusId())) {
            criteria.andStatusidEqualTo(req.getStatusId());
        }
        String sortOther = ",matchTime desc";
        if (ObjectUtil.isNotNull(req.getSortOther()) && req.getSortOther().equals("matchTime")) {
            sortOther = ",matchTime asc";
        }
        example.setOrderByClause(" sort asc " + sortOther + ", id desc");
        PageHelper.startPage(req.getPage(), req.getPagesize());
        List<Matchs> datas = matchsMapper.selectByExample(example);
        PageInfo<Matchs> page = new PageInfo<>(datas);
        List<MatchsResp> rspList = datas.stream().map(this::convertRsp).collect(Collectors.toList());

        fillTeamName(rspList);
        return PageUtil.pageInfo2PageRsp(page, rspList);
    }

    @Override
    public PageInfo<MatchsResp> matchPageList(MatchsReq req) {
        PageHelper.startPage(req.getPage(), req.getPagesize());
        List<Matchs> datas = matchsExtMapper.selectMatchJoinLeague(req);

        PageInfo<Matchs> page = new PageInfo<>(datas);
        List<MatchsResp> rspList = datas.stream().map(this::convertRsp).collect(Collectors.toList());

        fillTeamName(rspList);
        return PageUtil.pageInfo2PageRsp(page, rspList);
    }

    private void fillTeamName(List<MatchsResp> rspList) {
        List<String> matchIdList = rspList.stream().map(MatchsResp::getMatchId).collect(Collectors.toList());

        List<MatchScores> scoresList = matchScoresService.getByMatchIdBatch(matchIdList);
        if (CollectionUtil.isNotEmpty(scoresList)) {
            for (MatchsResp match : rspList) {
                String matchid = match.getMatchId();
                List<String> videosList = matchVideosExtMapper.selectStreamList(matchid);
                if (CollectionUtil.isEmpty(videosList)) {
                    continue;
                }
                match.setStreamList(videosList);

                for (MatchScores scores : scoresList) {
                    if (StrUtil.isNotBlank(match.getHomeTeamName()) && StrUtil.isNotBlank(match.getAwayTeamName())) {
                        break;
                    }
                    if (StrUtil.equals(scores.getMatchid(), matchid)) {
                        MatchScoresResp scoresResp = new MatchScoresResp();
                        scoresResp.setId(scores.getId());
                        scoresResp.setName(scores.getName());
                        scoresResp.setLogo(scores.getLogo());
                        scoresResp.setType(scores.getType());
                        scoresResp.setScore(scores.getScore());
                        scoresResp.setOverTimeScore(scores.getOvertimescore());
                        scoresResp.setHalfScore(scores.getHalfscore());
                        scoresResp.setPenaltyScore(scores.getPenaltyscore());
                        scoresResp.setFirstScore(scores.getFirstscore());
                        scoresResp.setSecondScore(scores.getSecondscore());
                        scoresResp.setThirdScore(scores.getThirdscore());
                        scoresResp.setFourthScore(scores.getFirstscore());
                        scoresResp.setMatchId(scores.getMatchid());

                        // type: 1主队2客队
                        if (NumberUtil.equals(1, scores.getType())) {
                            match.setHomeTeamName(scores.getName());
                            match.setHome(scoresResp);
                        } else if (NumberUtil.equals(2, scores.getType())) {
                            match.setAwayTeamName(scores.getName());
                            match.setAway(scoresResp);
                        }
                    }
                }
            }
        }
    }

    private MatchsResp convertRsp(Matchs matchs) {
        MatchsResp resp = new MatchsResp();
        BeanUtils.copyProperties(matchs, resp);
        // 运动类型:5:篮球，6:足球
        Integer gametype = matchs.getGametype();
        Integer statusid = matchs.getStatusid();
        String statusidStr = "";
        if (ObjectUtil.isNotNull(gametype) && ObjectUtil.isNotNull(statusid)) {
            if (gametype.equals(5)) {
                resp.setGameTypeStr("篮球");
                // 篮球比赛代码：-1(已完场) 0（未），1-4（第几节）,5（加时） 50(中场)
                switch (statusid) {
                    case -2:
                        statusidStr = "待定";
                        break;
                    case -1:
                        statusidStr = "已完场";
                        break;
                    case 0:
                        statusidStr = "未开赛";
                        break;
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                        statusidStr = "第" + statusid + "节";
                        break;
                    case 5:
                        statusidStr = "加时";
                        break;
                    case 50:
                        statusidStr = "中场";
                        break;
                    default:
                        statusidStr = statusid + "";
                        break;
                }
            } else if (gametype.equals(6)) {
                resp.setGameTypeStr("足球");
                // 足球比赛代码：0（未），-1（完），1（上半场）,2（中场）,3（下半场），4（加时）,5（点球）
                switch (statusid) {
                    case -14:
                        statusidStr = "推迟";
                        break;
                    case -13:
                        statusidStr = "中断";
                        break;
                    case -12:
                        statusidStr = "腰斩";
                        break;
                    case -11:
                        statusidStr = "待定";
                        break;
                    case -1:
                        statusidStr = "完";
                        break;
                    case 0:
                        statusidStr = "未开赛";
                        break;
                    case 1:
                        statusidStr = "上半场";
                        break;
                    case 2:
                        statusidStr = "中场";
                        break;
                    case 3:
                        statusidStr = "中半场";
                        break;
                    case 4:
                        statusidStr = "加时";
                        break;
                    case 5:
                        statusidStr = "点球";
                        break;
                    default:
                        statusidStr = statusid + "";
                        break;
                }
            }
        }
        String videos = matchs.getVideoUrl();
        if (StrUtil.isNotBlank(videos)) {
            resp.setHasVideos("有");
        } else {
            resp.setHasVideos("无");
        }

        // 是否正在播放 1播放 0未播
        Byte playing = matchs.getPlaying();
        if (ObjectUtil.isNotNull(playing)) {
            if (ByteUtil.byteToUnsignedInt(playing) == 0) {
                resp.setPlayingStr("未推流");
            } else if (ByteUtil.byteToUnsignedInt(playing) == 1) {
                resp.setPlayingStr("推流");
            }
        }

        // 国际化
        resp.setGameTypeStr(I18nUtil.translateBiz(resp.getGameTypeStr()));
        resp.setHasVideos(I18nUtil.translateBiz(resp.getHasVideos()));
        resp.setPlayingStr(I18nUtil.translateBiz(resp.getPlayingStr()));
        resp.setStatusIdStr(I18nUtil.translateBiz(statusidStr));

        // 取配置文件中房间id
        resp.setRid(roomRid);
        return resp;
    }

    private JSONArray getData(String uri) {
        Date now = new Date();
        String token = MD5Util.MD5(sportStreamSk + "|" + now.getTime() + "|" + uri);
        token = token.toUpperCase();
        Map<String, String> header = new HashMap<>();
        header.put("Sp-Access-Key", sportStreamAk);
        header.put("Sp-Access-Time", String.valueOf(now.getTime()));
        header.put("Sp-Access-Token", token);
        Map<String, String> param = new HashMap<>();
        logger.info("writeMatch domain={}", sportDomain);
        JSONObject data = HttpClientUtil.doPost(sportDomain + uri, header, param);
        Integer code = data.getInt("code");
        String message = data.getStr("message");
        if (code != 0) {
            logger.error("获取失败:{},{}", code, message);
            throw new BizException(CodeCons.ERROR, "获取失败");
        }
        return data.getJSONArray("data");
    }

    private void flushMatchData(JSONArray footballData, JSONArray basketData) {
        /*
        1.先查询对方拿到的数据中，在本地处于-1状态的数据，拿到3,4, 5，6, 7
        2.将拿到的数据中的3，4，5，6，7去掉，剩下8,9，10， 11
        3.将现有数据中所有非-1的数据全部剑除
        4.将增量数据批量插入数据库：8.9,10.11
        其他表也是一样的逐辑，相当于不再有循环，储环在在逐辑中做，sq一张表只需要执行删/插分别一条
        */

        // 全量获取渠道返回的赛事
        JSONArray channelJsonArray = new JSONArray();
        if (ObjectUtil.isNotNull(footballData)) {
            channelJsonArray.addAll(footballData);
            logger.info("footballData-count:{}", footballData.size());
        }
        if (ObjectUtil.isNotNull(basketData)) {
            channelJsonArray.addAll(basketData);
            logger.info("basketData-count:{}", basketData.size());
        }
        Map<String, JSONObject> channelMatchIdMap = new HashMap<>(channelJsonArray.size() + 100);

        Map<String, JSONObject> channelLeagueIdMap = new HashMap<>(channelJsonArray.size() + 100);
        //找出赛程，及赛程信息
        for (int i = 0; i < channelJsonArray.size(); i++) {
            JSONObject item = channelJsonArray.getJSONObject(i);
            int gameType = item.getInt("gameType");
            if (gameType != 5 && gameType != 6) {
                continue;
            }
            String matchId = item.getStr("matchId");
            if (StrUtil.isBlank(matchId)) {
                continue;
            }
            String leagueId = item.getStr("leagueId");
            if (StrUtil.isNotBlank(leagueId) && channelLeagueIdMap.get(leagueId) == null) {
                channelLeagueIdMap.put(leagueId, item);
            }
            channelMatchIdMap.put(matchId, item);
        }
        List<String> leagueIdList = new ArrayList<>(channelLeagueIdMap.keySet());
        //如果在表里
        MatchLeaguesExample matchLeaguesExample = new MatchLeaguesExample();
        MatchLeaguesExample.Criteria matchLeaguesExampleCriteria = matchLeaguesExample.createCriteria();
        matchLeaguesExampleCriteria.andLeagueIdIn(leagueIdList);
        List<MatchLeagues> matchLeagues = matchLeaguesMapper.selectByExample(matchLeaguesExample);
        if (CollectionUtil.isNotEmpty(matchLeagues)) {
            List<String> leagueIdList2 = matchLeagues.stream().map(MatchLeagues::getLeagueId).collect(Collectors.toList());
            leagueIdList.removeAll(leagueIdList2);
        }
        //插入
        List<MatchLeagues> addLeagueList = new ArrayList<>();
        for (String item : leagueIdList) {
            JSONObject jsonObject = channelLeagueIdMap.get(item);
            MatchLeagues matchLeagues1 = new MatchLeagues();
            matchLeagues1.setLeagueId(jsonObject.getStr("leagueId"));
            matchLeagues1.setLeagueName(jsonObject.getStr("leagueName"));
            matchLeagues1.setLeagueLogo(jsonObject.getStr("leagueLogo"));
            matchLeagues1.setType(jsonObject.getInt("gameType"));
            addLeagueList.add(matchLeagues1);
        }
        if (CollectionUtil.isNotEmpty(addLeagueList)) {
            matchLeaguesExtMapper.insertBatch(addLeagueList);
            logger.info("matchLeaguesAddList-count:{}", addLeagueList.size());
        }

        // 全量获取数据库中赛事的ID和状态
        List<String> deleteScoreMatchIdList = new ArrayList<>();
        List<MatchIdAndStatusVo> matchIdAndStatusVos = matchsExtMapper.selectMatchIdAndStatusId();
        Map<String, MatchIdAndStatusVo> dbMatchIdStatusMap = new HashMap<>();
        for (MatchIdAndStatusVo item : matchIdAndStatusVos) {
            dbMatchIdStatusMap.put(item.getMatchId(), item);

            Integer statusId = item.getStatusId();
            if (!NumberUtil.equals(statusId, -1)) {
                deleteScoreMatchIdList.add(item.getMatchId());
            }
        }

        // 获取渠道返回数据中未结束的赛事
        List<JSONObject> noFinishedJsonList = new ArrayList<>();
        Set<Map.Entry<String, JSONObject>> channelMatchEntry = channelMatchIdMap.entrySet();
        for (Map.Entry<String, JSONObject> entry : channelMatchEntry) {
            String channelMatchId = entry.getKey();
            MatchIdAndStatusVo matchIdAndStatusVo = dbMatchIdStatusMap.get(channelMatchId);
            if (ObjectUtil.isNotNull(matchIdAndStatusVo)) {
                Integer dbMatchStatus = matchIdAndStatusVo.getStatusId();
                if (ObjectUtil.isNotNull(dbMatchStatus) && NumberUtil.equals(dbMatchStatus, -1)) {
                    continue;
                }
            }
            noFinishedJsonList.add(entry.getValue());
        }

        Date now = new Date();
        List<Matchs> noFinishedList = new ArrayList<>();
        List<MatchVideos> matchVideosAddList = new ArrayList<>();
        List<MatchScores> matchScoresAddList = new ArrayList<>();

        // 将增量数据批量插入数据库
        for (JSONObject item : noFinishedJsonList) {
            String matchId = item.getStr("matchId");

            Matchs matchs = new Matchs();
            matchs.setMatchid(matchId);
            matchs.setGametype(item.getInt("gameType"));
            matchs.setMatchtime(item.getInt("matchTime"));
            matchs.setLeagueid(item.getStr("leagueId"));
            matchs.setLeaguename(item.getStr("leagueName"));
            matchs.setLeaguelogo(item.getStr("leagueLogo"));
            matchs.setStatusid(item.getInt("statusId"));
            matchs.setStatus(item.getStr("status"));
            matchs.setStreamid(item.getInt("streamId"));
            matchs.setThirdid(item.getStr("thirdId"));
            matchs.setLineup((byte) (BooleanUtil.isTrue(item.getBool("lineUp")) ? 1 : 0));
            matchs.setCreatedAt(now);
            matchs.setUpdatedAt(now);

            // 赛事比分
            JSONObject home = item.getJSONObject("home");
            JSONObject away = item.getJSONObject("away");
            MatchScores homeScore = process(home, matchId, 1);
            MatchScores awayScore = process(away, matchId, 2);
            matchScoresAddList.add(homeScore);
            matchScoresAddList.add(awayScore);

            JSONArray streams = item.getJSONArray("streams");
            // 还原字段值
            MatchIdAndStatusVo oldMatch = dbMatchIdStatusMap.get(matchId);
            for (int k = 0; k < streams.size(); k++) {
                JSONObject stream = streams.getJSONObject(k);
                Integer streamid = matchs.getStreamid();
                String streamName = stream.getStr("streamName");
                String m3u8 = getVideoUrl(streamName);
                Byte pushing = stream.getByte("pushing");

                MatchVideos video = new MatchVideos();
                video.setMatchid(matchId);
                video.setStreamid(streamid);
                video.setPushing(stream.getInt("pushing"));
                video.setStreamname(streamName);
                video.setStreamtype(stream.getInt("streamType"));
                video.setM3u8(m3u8);
                video.setLine("{\"m3u8\":\"" + m3u8 + "\"}");
                video.setCreatedAt(now);
                video.setUpdatedAt(now);
                matchVideosAddList.add(video);

                // 更新赛事视频url   这次比赛第一次过来，肯定要更新的。  二次  肯定不更新的 pushing
                if (StrUtil.isEmpty(matchs.getVideoUrl())) {
                    matchs.setStreamname(streamName);
                    matchs.setVideoUrl(m3u8);
                }
                if (ObjectUtil.isNotNull(oldMatch)) {
                    if (streamName.equals(oldMatch.getStreamName()) && ObjectUtil.isNotNull(pushing)) {
                        matchs.setPlaying(pushing);
                    }
                }
            }
            restoreOldValue(oldMatch, matchs);
            // 添加到待新增集合
            noFinishedList.add(matchs);
        }

        // 批量清空videos再批量插入
        matchVideosExtMapper.truncateVideos();
        if (CollectionUtil.isNotEmpty(matchVideosAddList)) {
            matchVideosExtMapper.insertBatch(matchVideosAddList);
            logger.info("matchVideosAddList-count:{}", matchVideosAddList.size());
        }

        // 批量清空比分再批量插入
        MatchScoresExample example = new MatchScoresExample();
        MatchScoresExample.Criteria criteria = example.createCriteria();
        criteria.andMatchidIn(deleteScoreMatchIdList);
        matchScoresMapper.deleteByExample(example);
        if (CollectionUtil.isNotEmpty(matchScoresAddList)) {
            matchScoresExtMapper.insertBatch(matchScoresAddList);
            logger.info("matchScoresAddList-count:{}", matchScoresAddList.size());
        }

        // 批量插入未结束的赛事
        matchsExtMapper.deleteNoFinished();
        if (CollectionUtil.isNotEmpty(noFinishedList)) {
            matchsExtMapper.insertBatch(noFinishedList);
            logger.info("noFinishedList-count:{}", noFinishedList.size());
        }
    }
}
