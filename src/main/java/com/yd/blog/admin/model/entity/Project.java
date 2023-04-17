package com.yd.blog.admin.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;

/**
 * 项目(Project)实体类
 *
 * @author makejava
 * @since 2022-09-13 17:01:18
 */
@Data
public class Project implements Serializable {
    private static final long serialVersionUID = -58095648071379633L;
    /**
     * 主键id
     */
    private Long id;
    /**
     * 项目名称
     */
    private String title;
    /**
     * 描述
     */
    private String content;
    /**
     * 简介
     */
    private String excerpt;
    /**
     * 封面图
     */
    private String coverImg;
    /**
     * 视频封面图
     */
    private String videoImg;
    /**
     *  封面视频地址
     */
    private String videoUrl;
    /**
     *  项目外链
     */
    private String projectUrl;
    /**
     * 素材和视频数组，用“，”分隔
     */
    private String imgList;
    /**
     * ytb视频数组，用“，”分隔
     */
    private String videoList;
    /**
     * 项目富文本
     */
    private String projectBody;
    /**
     * 标签
     */
    private String tags;
    /**
     * 分类id
     */
    private Integer classifyId;
    /**
     * 是否首页推荐
     */
    private Integer isHomeList;
    /**
     * 项目状态
     */
    private Integer status;
    /**
     * 创建时间
     */
    @JsonIgnore
    private String createTime;

}

