package com.yd.JJLin.common.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 日期工具类
 *
 * @author wangyuandong
 * @date 2022/9/11
 */
public class DateUtil {

    /**
     * 日期的默认格式
     */
    public static final String DATE_PATTON_DEFAULT = "yyyy-MM-dd";
    /**
     * 日期时间的默认格式
     */
    public static final String TIME_PATTON_DEFAULT = "yyyy-MM-dd HH:mm:ss";

    /**
     * 获取当前时间戳
     *
     * @return 时间戳
     */
    public static long getTimestamp() {
        return System.currentTimeMillis();
    }

    /**
     * 获取当前日期
     *
     * @return 日期
     */
    public static String getCurrentDateStr() {
        LocalDate date = LocalDate.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(DATE_PATTON_DEFAULT);
        return date.format(dtf);
    }

    /**
     * 获取当前日期时间
     *
     * @return 日期时间
     */
    public static String getCurrentDateTimeStr() {
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(TIME_PATTON_DEFAULT);
        return date.format(dtf);
    }
}
