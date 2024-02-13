package com.coin.service.util;

import cn.hutool.core.util.StrUtil;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtil {

    public static final String tz_ms_dt_format = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    public static final String base_dt_format = "yyyy-MM-dd HH:mm:ss";
    public static final String terse_dt_format = "yyyyMMddHHmmss";
    public static final String ms_dt_format = "yyyyMMddHHmmssSSS";
    public static final String year_format = "yyyy";
    public static final String month_format = "MM";

    /**
     * 获取当天年月日时分秒的字符串
     */
    public static String getTodayStr(String fmtStr) {
        if (StrUtil.isBlank(fmtStr)) {
            fmtStr = DateUtil.terse_dt_format;
        }
        //获取当前时间
        LocalDateTime now = LocalDateTime.now();
        //创建日期时间对象格式化器，日期格式类似： 2020-02-23 22:18:38
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(fmtStr);
        //将时间转化为对应格式的字符串
        return now.format(formatter);
    }

    /**
     * 将字符串转化为时间，默认是 yyyy-MM-dd HH:mm:ss
     */
    public static Date getDateByStr(String dataStr) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat(DateUtil.base_dt_format);
        return sdf.parse(dataStr);
    }

    public static Date getDateByStrTz(String dataStr) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat(DateUtil.tz_ms_dt_format);
        return sdf.parse(dataStr);
    }

    /**
     * 将时间转化为指定格式字符串，默认格式为 ‘yyyy-MM-dd HH:mm:ss’
     */
    public static String getStrByDate(Date date, String fmtStr) {
        if (StrUtil.isBlank(fmtStr)) {
            fmtStr = "yyyy-MM-dd HH:mm:ss";
        }
        return cn.hutool.core.date.DateUtil.format(date, fmtStr);
    }

    public static String getStrByDate(Date date) {
        return DateUtil.getStrByDate(date, null);
    }
}
