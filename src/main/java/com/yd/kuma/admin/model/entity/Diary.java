package com.yd.kuma.admin.model.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 随手记(Diary)表实体类
 *
 * @author makejava
 * @since 2022-10-24 11:10:31
 */
@Data
public class Diary implements Serializable {

    private static final long serialVersionUID = -25914820508805180L;
    /**
     * 主键
     */
    private Long id;
    /**
     * 作者
     */
    private String author;
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
    private String content;
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
    /**
     * 素材和视频数组，用“，”分隔
     */
    private String imgList;
    /**
     * ytb视频数组，用“，”分隔
     */
    private String videoList;
    /**
     * 分类id
     */
    private Integer classifyId;

}
