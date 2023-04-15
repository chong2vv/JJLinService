package com.yd.blog.common.util;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


/**
 * 通用工具类
 *
 * @author wangyuandong
 * @date 2022/9/4
 */
public class CommonUtil {

    /**
     * 复制对象
     *
     * @param source 源对象
     * @param target 目标对象
     * @param <R>    目标对象类型
     * @return 目标对象
     */
    public static <R> R copyVo(Object source, Class<R> target) {
        R vo = null;
        try {
            vo = target.newInstance();
            BeanUtils.copyProperties(source, vo);
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return vo;
    }

    /**
     * 将字符串转化成集合
     *
     * @param string 字符串
     * @return 集合对象
     */
    public static List<String> stringsToList(String string) {
        if (StringUtils.isBlank(string)) {
            return Collections.emptyList();
        }
        return Arrays.asList(string.split(","));
    }

    /**
     * 将集合转化成字符串，中间用“,”分隔
     *
     * @param list 集合
     * @return 字符串
     */
    public static String listToString(List<String> list) {
        if (CollectionUtils.isNotEmpty(list)) {
            return String.join(",", list);
        }
        return "";
    }
}
