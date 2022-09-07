package com.ry.time.admin.model.entity;

import lombok.Data;

import java.util.Date;

/**
 * 消息信息
 *
 * @author gongjiguang
 * @date 2022-09-03
 */
@Data
public class Message  {

    /**
     * 信息id
     */
    private Long id;
    /**
     * 用户名
     */
    private String name;
    /**
     * 主题
     */
    private String title;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 信息内容
     */
    private String content;
    /**
     * 信息来源类型
     */
    private Integer fromType;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 用户状态
     */
    private Integer status;
    /**
     * 时间戳
     */
    private Date timestamp;

}

