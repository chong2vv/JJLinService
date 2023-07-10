package com.yd.JJLin.common.model;

import lombok.Data;

import java.io.Serializable;

/**
 * 返回信息封装类
 *
 * @author wangyuandong
 */
@Data
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 错误码
     */
    private int code;
    /**
     * 错误信息
     */
    private String message = "";
    /**
     * 返回数据
     */
    private T data;

}
