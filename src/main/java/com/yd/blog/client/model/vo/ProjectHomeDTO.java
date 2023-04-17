package com.yd.blog.client.model.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yd.blog.admin.model.entity.Classify;
import lombok.Data;

import java.util.List;

/**
 * 项目首页信息
 *
 * @author wangyuandong
 * @date 2022/9/21
 */
@Data
public class ProjectHomeDTO {

    private Long id;

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
     * 加工准备时间
     */
    private String timer;

    @JsonProperty("cover_img")
    private String coverImg;

    /**
     * 项目分类
     */
    private Classify classify;

    @JsonProperty("classify_id")
    private Integer classifyId;

    @JsonProperty("project_url")
    private String projectUrl;


    /**
     * 素材和视频数组，用“，”分隔
     */
    @JsonProperty("img_list")
    private List<String> imgList;


}
