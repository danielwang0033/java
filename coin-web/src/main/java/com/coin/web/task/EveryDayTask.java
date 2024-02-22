package com.coin.web.task;

import com.coin.i18n.I18nUtil;
import com.coin.req.RoomsReq;
import com.coin.service.GuessService;
import com.coin.service.MatchsService;
import com.coin.service.RoomsService;
import com.coin.service.ThreadTopicsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * 每日定时任务
 */
@Component
public class EveryDayTask {

    private static final Logger logger = LoggerFactory.getLogger(EveryDayTask.class);

    @Value("${chat.rcRid}")
    private String rcRid;
    @Resource
    private MatchsService matchsService;
    @Resource
    private RoomsService roomsService;
    @Resource
    private ThreadTopicsService threadTopicsService;
    @Resource
    private GuessService guessService;

    /**
     * 更新在线状态, 每2分钟执行一次
     * <p>
     * 2023-11-23 20:30:15 星期四
     * 2023-11-23 20:32:15 星期四
     * 2023-11-23 20:34:15 星期四
     * 2023-11-23 20:36:15 星期四
     * 2023-11-23 20:38:15 星期四
     * 2023-11-23 20:40:15 星期四
     */
    @Scheduled(cron = "15 */2 * * * *")
    public void everyMinTask() {
        if (check()) {
            String uuid = UUID.randomUUID().toString();
            logger.info("[" + uuid + "]channelsOnline-start");
            try {
                RoomsReq roomsReq = new RoomsReq();
                roomsReq.setRid(rcRid);
                roomsService.channelsOnline(roomsReq);
            } catch (Exception e) {
                logger.error("更新在线状态", e);
            }
            logger.info("[" + uuid + "]channelsOnline-end");
        }
    }

    /**
     * 采集比赛, 每2分钟执行一次
     * <p>
     * 2023-11-23 20:28:30 星期四
     * 2023-11-23 20:30:30 星期四
     * 2023-11-23 20:32:30 星期四
     * 2023-11-23 20:34:30 星期四
     * 2023-11-23 20:36:30 星期四
     * 2023-11-23 20:38:30 星期四
     */
    // @Scheduled(cron = "30 */2 * * * *")
    // public void everyOneMinTask() {
    //     if (check()) {
    //         String uuid = UUID.randomUUID().toString();
    //         logger.info("[" + uuid + "]batchAddStream-start");
    //         try {
    //             matchsService.batchAddStream();
    //         } catch (Exception e) {
    //             logger.error("采集比赛", e);
    //         }
    //         logger.info("[" + uuid + "]batchAddStream-end");
    //     }
    // }

    /**
     * 删除2天前比赛,每1小时执行一次
     * <p>
     * 2023-11-23 21:10:50 星期四
     * 2023-11-23 22:10:50 星期四
     * 2023-11-23 23:10:50 星期四
     * 2023-11-24 00:10:50 星期五
     */
    // @Scheduled(cron = "50 10 * * * *")
    // public void clearMatchTask() {
    //     if (check()) {
    //         String uuid = UUID.randomUUID().toString();
    //         logger.info("[" + uuid + "]clearMatchTask-start");
    //         try {
    //             matchsService.batchDelete();
    //         } catch (Exception e) {
    //             logger.error("删除2天前比赛", e);
    //         }
    //         logger.info("[" + uuid + "]clearMatchTask-end");
    //     }
    // }

    /**
     * 更新话题的阅读数,每3分钟执行一次
     * <p>
     * 2023-11-23 20:27:00 星期四
     * 2023-11-23 20:30:00 星期四
     * 2023-11-23 20:33:00 星期四
     * 2023-11-23 20:36:00 星期四
     * 2023-11-23 20:39:00 星期四
     * 2023-11-23 20:42:00 星期四
     */
    @Scheduled(cron = "0 */3 * * * *")
    public void everyThreeMinTask() {
        String uuid = UUID.randomUUID().toString();
        logger.info("[" + uuid + "]everyThreeMinTask-start");
        try {
            threadTopicsService.updateBatchReadCount();
        } catch (Exception e) {
            logger.error("更新话题的阅读数", e);
        }
        logger.info("[" + uuid + "]everyThreeMinTask-end");
    }

    /**
     * 同步竞猜状态,每5分钟执行一次
     * <p>
     * 2023-11-23 20:30:10 星期四
     * 2023-11-23 20:35:10 星期四
     * 2023-11-23 20:40:10 星期四
     * 2023-11-23 20:45:10 星期四
     * 2023-11-23 20:50:10 星期四
     */
    @Scheduled(cron = "10 */5 * * * *")
    public void syncGuessStatusTask() {
        String uuid = UUID.randomUUID().toString();
        logger.info("[" + uuid + "]syncGuessStatusTask-start");
        try {
            guessService.syncGuessStatusTask();
        } catch (Exception e) {
            logger.error("同步竞猜状态", e);
        }
        logger.info("[" + uuid + "]syncGuessStatusTask-end");
    }

    private boolean check() {
        return !I18nUtil.ENABLE;
    }
}
