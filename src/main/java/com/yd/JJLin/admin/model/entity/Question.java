package com.yd.JJLin.admin.model.entity;

import lombok.Data;

@Data
public class Question {
    /**
     * 信息id
     */
    private Integer id;
    /**
     * 用户名
     */
    private String question;
    /**
     * 主题
     */
    private String answer;

    /**
     * 用户状态
     */
    private Integer status;
    /**
     * 创建时间
     */
    private String createTime;
}
