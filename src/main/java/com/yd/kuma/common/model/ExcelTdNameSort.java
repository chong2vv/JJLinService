package com.yd.kuma.common.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * describe:
 *
 * @author gongjiguang
 * @date 2022/9/18
 */
@AllArgsConstructor
@Getter
public class ExcelTdNameSort {
    /**
     * 标题名称
     */
    private final String titleName;
    /**
     * 方法名称
     */
    private final String methodName;
    /**
     * 单元格格式  0：默认字符串格式；1：金额格式；2：百分比格式
     */
    private final int cellFormat;
    /**
     * 字段类型
     */
    private final Class<?> parameterType;
}
