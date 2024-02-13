package com.coin.config;

import cn.hutool.core.date.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.TimeZone;

@Slf4j
@Component
public class TimeZoneConfig {

    public static final String TIME_ZONE = getLocalGMTName();

    public static String getLocalGMTName() {
        log.info("正在计算当前时区, now:{}", DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
        TimeZone timeZone = TimeZone.getDefault();
        ZoneId zoneId = ZoneId.of(timeZone.getID());
        ZonedDateTime now = ZonedDateTime.now(zoneId);
        int offsetInSeconds = now.getOffset().getTotalSeconds();
        int offsetInHours = offsetInSeconds / 3600;
        String gmtStr = "GMT" + (offsetInHours >= 0 ? "+" : "") + offsetInHours;
        log.info("当前时区:{}, {}", timeZone.getDisplayName(), gmtStr);
        return gmtStr;
    }
}
