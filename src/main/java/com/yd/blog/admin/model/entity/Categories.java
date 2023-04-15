package com.yd.blog.admin.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 项目分类(Classify)实体类
 *
 * @author makejava
 * @since 2022-09-10 19:57:19
 */
@Data
public class Categories implements Serializable {

    private static final long serialVersionUID = 768848908156396882L;
    /**
     * 主键
     */
    private Integer id;
    /**
     * 分类名称
     */
    private String title;
    /**
     * 备注
     */
    private String remark;
    /**
     * 分类状态
     */
    private Integer status;
    /**
     * 图片地址
     */
    @JsonProperty("image_url")
    private String imageUrl;
    /**
     * 创建时间
     */
    @JsonIgnore
    private String createTime;

    /**
     * 跳转类型
     */
    private Integer type;

}

