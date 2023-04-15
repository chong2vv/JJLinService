package com.yd.blog.admin.model.dto;

import com.yd.blog.admin.annotation.ExcelTdName;
import lombok.Data;

/**
 * 项目excel对象
 *
 * @author wangyuandong
 * @since 2022/9/18
 */
@Data
public class ProjectExcelDTO {

    /**
     * 项目名称
     */
    @ExcelTdName(value = "title", sort = 0)
    private String title;
    /**
     * 描述
     */
    @ExcelTdName(value = "content", sort = 1)
    private String content;
    /**
     * 简介
     */
    @ExcelTdName(value = "excerpt", sort = 2)
    private String excerpt;
    /**
     * 封面图
     */
    @ExcelTdName(value = "cover_img", sort = 8)
    private String coverImg;
    /**
     * 素材和视频数组，用“，”分隔
     */
    @ExcelTdName(value = "img_list", sort = 9)
    private String imgList;
    /**
     * 标签
     */
    @ExcelTdName(value = "tags", sort = 10)
    private String tags;
    /**
     * 分类id
     */
    @ExcelTdName(value = "classify_id", sort = 11)
    private Integer classifyId;
    /**
     * 是否首页推荐
     */
    @ExcelTdName(value = "is_home_list", sort = 12)
    private Integer isHomeList;
    /**
     * 封面视频地址
     */
    @ExcelTdName(value = "video_url", sort = 13)
    private String videoUrl;
    /**
     * 封面视频图片
     */
    @ExcelTdName(value = "video_img", sort = 14)
    private String videoImg;

}

