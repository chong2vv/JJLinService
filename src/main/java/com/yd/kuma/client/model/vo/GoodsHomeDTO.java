package com.yd.kuma.client.model.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yd.kuma.admin.model.entity.Classify;
import lombok.Data;

import java.util.List;

/**
 * 商品首页信息
 *
 * @author gongjiguang
 * @date 2022/9/21
 */
@Data
public class GoodsHomeDTO {

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

    @JsonProperty("cover_img")
    private String coverImg;

    /**
     * 商品分类
     */
    private Classify classify;

    @JsonProperty("classify_id")
    private Integer classifyId;

    /**
     * 素材和视频数组，用“，”分隔
     */
    @JsonProperty("img_list")
    private List<String> imgList;


}
