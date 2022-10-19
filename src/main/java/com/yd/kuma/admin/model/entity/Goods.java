package com.yd.kuma.admin.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;

/**
 * 商品(Goods)实体类
 *
 * @author makejava
 * @since 2022-09-13 17:01:18
 */
@Data
public class Goods implements Serializable {
    private static final long serialVersionUID = -58095648071379633L;
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
     * 素材和视频数组，用“，”分隔
     */
    private String imgList;
    /**
     * ytb视频数组，用“，”分隔
     */
    private String videoList;
    /**
     * 商品富文本
     */
    private String goodsBody;
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
     * 商品状态
     */
    private Integer status;
    /**
     * 创建时间
     */
    @JsonIgnore
    private String createTime;

}

