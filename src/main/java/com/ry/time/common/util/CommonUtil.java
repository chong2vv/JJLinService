package com.ry.time.common.util;

import org.springframework.beans.BeanUtils;

import java.util.Arrays;
import java.util.List;

/**
 * 通用工具类
 *
 * @author gongjiguang
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
    public static  <R> R copyVo(Object source, Class<R> target) {
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
     * @param string 字符串
     * @return 集合对象
     */
    public static List<String> stringsToList(String string) {
        return Arrays.asList(string.split(","));
    }
}
