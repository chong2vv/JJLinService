package com.ry.time.admin.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ry.time.admin.model.entity.Classify;
import lombok.Data;

import java.util.List;

/**
 * 商品展示层对象
 *
 * @author gongjiguang
 * @since 2022/9/13
 */
@Data
public class GoodsDTO {
    /**
     * 主键id
     */
    private Long id;
    /**
     * 商品名称
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
     * 尺寸
     */
    private String size;
    /**
     * 材料
     */
    private String material;
    /**
     * 打包方式
     */
    private String pack;
    /**
     * 装箱量
     */
    private String qty;
    /**
     * 加工准备时间
     */
    private String timer;
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
     * 标签
     */
    private List<String> tags;
    /**
     * 分类id
     */
    @JsonProperty("classify_id")
    private Integer classifyId;
    /**
     * 商品分类
     */
    private Classify classify;
    /**
     * 是否首页推荐
     */
    @JsonProperty("is_home_list")
    private Integer isHomeList;
    /**
     * 商品状态
     */
    private Integer status;

}

