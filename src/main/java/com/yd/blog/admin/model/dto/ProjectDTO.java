package com.yd.blog.admin.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yd.blog.admin.model.entity.Classify;
import lombok.Data;

import java.util.List;

/**
 * 项目展示层对象
 *
 * @author wangyuandong
 * @since 2022/9/13
 */
@Data
public class ProjectDTO {
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
    @JsonProperty("cover_img")
    private String coverImg;
    /**
     * 素材和视频数组，用“，”分隔
     */
    @JsonProperty("img_list")
    private List<String> imgList;
    /**
     * YTB链接数组，用“，”分隔
     */
    @JsonProperty("video_list")
    private List<String> videoList;
    /**
     * 封面视频地址
     */
    @JsonProperty("video_url")
    private String videoUrl;
    /**
     * 项目外链
     */
    @JsonProperty("project_url")
    private String projectUrl;
    /**
     * 封面视频图片
     */
    @JsonProperty("video_img")
    private String videoImg;
    /**
     * 标签
     */
    private List<String> tags;
    /**
     * 项目富文本
     */
    @JsonProperty("project_body")
    private String projectBody;
    /**
     * 分类id
     */
    @JsonProperty("classify_id")
    private Integer classifyId;
    /**
     * 项目分类
     */
    private Classify classify;
    /**
     * 是否首页推荐
     */
    @JsonProperty("is_home_list")
    private Integer isHomeList;
    /**
     * 项目状态
     */
    private Integer status;

}

