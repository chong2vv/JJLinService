package com.ry.time.admin.model.dto;

import com.ry.time.admin.annotation.ExcelTdName;
import lombok.Data;

/**
 * 商品excel对象
 *
 * @author gongjiguang
 * @since 2022/9/18
 */
@Data
public class GoodsExcelDTO {

    /**
     * 商品名称
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
     * 尺寸
     */
    @ExcelTdName(value = "size", sort = 3)
    private String size;
    /**
     * 材料
     */
    @ExcelTdName(value = "material", sort = 4)
    private String material;
    /**
     * 打包方式
     */
    @ExcelTdName(value = "pack", sort = 5)
    private String pack;
    /**
     * 装箱量
     */
    @ExcelTdName(value = "qty", sort = 6)
    private String qty;
    /**
     * 加工准备时间
     */
    @ExcelTdName(value = "timer", sort = 7)
    private String timer;
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

}

