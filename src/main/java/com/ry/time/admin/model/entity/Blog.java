package com.ry.time.admin.model.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 博客(Blog)实体类
 *
 * @author makejava
 * @since 2022-09-19 11:57:25
 */
@Data
public class Blog implements Serializable {

    private static final long serialVersionUID = -25914820508805180L;
    /**
     * 主键
     */
    private Long id;
    /**
     * 封面图
     */
    private String img;
    /**
     * 作者
     */
    private String author;
    /**
     * 浏览人数
     */
    private Long view;
    /**
     * 标题
     */
    private String title;
    /**
     * 节选
     */
    private String excerpt;
    /**
     * 文章内容
     */
    private String body;
    /**
     * 标签
     */
    private String tags;
    /**
     * 文章状态
     */
    private Integer status;
    /**
     * 创建时间
     */
    private String createTime;

}

