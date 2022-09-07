package com.ry.time.common.util;

import org.apache.commons.lang3.math.NumberUtils;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 数字相关工具类
 *
 * @author gongjiguang
 */
public class NumberUtil extends NumberUtils {

    private static final AtomicInteger SINCE_NUMBER;

    private static final int SINCE_NUMBER_MAX = 100;

    /**
     * 匹配手机号正则
     */
    private static final Pattern VALID_PHONE_REGEX =
            Pattern.compile("^1\\d{10}$");

    static {
        SINCE_NUMBER = new AtomicInteger(0);
    }

    private NumberUtil() {

    }


    /**
     * 判断是否为11位电话号码
     *
     * @param phone 手机号
     * @return boolean
     */
    public static boolean isPhone(String phone) {
        try {
            Matcher matcher = VALID_PHONE_REGEX.matcher(phone);
            return matcher.matches();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 生成指定长度的随机数
     *
     * @param length 长度
     * @return 随机数int
     */
    public static int genRandomNum(int length) {
        int num = 1;
        double random = Math.random();
        if (random < 0.1) {
            random = random + 0.1;
        }
        for (int i = 0; i < length; i++) {
            num = num * 10;
        }
        return (int) ((random * num));
    }

    /**
     * 生成uid
     *
     * @return uid Long
     */
    public static long genUid() {
        StringBuilder builder = new StringBuilder(String.valueOf(System.currentTimeMillis()));
        if (SINCE_NUMBER.get() >= SINCE_NUMBER_MAX) {
            SINCE_NUMBER.set(0);
        }
        String automatic = automaticFilling(SINCE_NUMBER.incrementAndGet(), 2);
        builder.append(automatic);
        int num = genRandomNum(3);
        builder.append(num);
        return Long.parseLong(builder.toString());
    }

    /**
     * 自动补位
     *
     * @param code 数值
     * @param num  保留的位数
     * @return 补位数字
     */
    public static String automaticFilling(int code, int num) {
        return String.format("%0" + num + "d", code);
    }
}
