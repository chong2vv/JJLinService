package com.yd.JJLin.admin.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yd.JJLin.admin.model.entity.Classify;
import lombok.Data;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @Author: wangyuandong
 * @Date: 2022/10/24 11:17
 */
@Data
public class DiaryDTO {
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
    private Classify classify;
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
     * blog id string
     */
    @JsonProperty("id_string")
    private String  idString;

    public Long getTimestamp() {
        long time = (new SimpleDateFormat("yyyy-MM-dd")).parse(createTime, new ParsePosition(0)).getTime();
        return time;
    }
}
