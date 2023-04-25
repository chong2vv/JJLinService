package com.yd.blog.admin.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yd.blog.admin.model.entity.Categories;
import com.yd.blog.admin.model.entity.Classify;
import lombok.Data;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * 博客展示层对象
 *
 * @author wangyuandong
 * @since 2022/9/19
 */
@Data
public class BlogDTO {

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
    private String content;
    /**
     * 标签
     */
    private List<String> tags;
    /**
     * 文章状态
     */
    private Integer status;
    /**
     * 分类id
     */
    @JsonProperty("classify_id")
    private Integer classifyId;
    /**
     * 项目分类
     */
    private Categories categories;
    /**
     * 创建时间
     */
    @JsonProperty("create_at")
    private String createTime;
    /**
     * 素材和视频数组，用“，”分隔
     */
    @JsonProperty("img_list")
    private List<String> imgList;
    /**
     * 视频数组，用“，”分隔
     */
    @JsonProperty("video_list")
    private List<String> videoList;
    /**
     *  时间戳
     */
    private Long timestamp;
    /**
     * 是否首页推荐
     */
    @JsonProperty("is_home_list")
    private Integer isHomeList;

    /**
     * blog id string
     */
    @JsonProperty("id_string")
    private String  idString;

    public Long getTimestamp() {
        long time = (new SimpleDateFormat("yyyy-MM-dd")).parse(createTime, new ParsePosition(0)).getTime();
        return time;
    }

}

