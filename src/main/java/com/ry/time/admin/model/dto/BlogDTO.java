package com.ry.time.admin.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * 博客展示层对象
 *
 * @author gongjiguang
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
     * 创建时间
     */
    @JsonProperty("create_at")
    private String createTime;

    /**
     *  时间戳
     */
    private Long timestamp;

    public Long getTimestamp() {
        long time = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).parse(createTime, new ParsePosition(0)).getTime();
        return time;
    }

}

