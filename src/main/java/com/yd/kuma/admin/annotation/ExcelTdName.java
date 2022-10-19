package com.yd.kuma.admin.annotation;

import java.lang.annotation.*;

/**
 * excel表头注解
 *
 * @author gongjiguang
 * @date 2022/09/18
 */
@Inherited
@Target({ElementType.FIELD})//在属性上起作用
@Retention(RetentionPolicy.RUNTIME) //运行时起作用
@Documented
public @interface ExcelTdName {

    /**
     * 表头名称
     *
     * @return 名称
     */
    String[] value();

    /**
     * 顺序
     *
     * @return 序列
     */
    int[] sort();

    /**
     * 单元格格式  0：默认字符串类型；1：金额格式($#,##0.0000)；2：百分比格式(0.00%)
     *
     * @return 单元格格式
     */
    int cellFormat() default 0;
}
