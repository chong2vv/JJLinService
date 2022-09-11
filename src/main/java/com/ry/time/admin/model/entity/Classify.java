package com.ry.time.admin.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 商品分类(Classify)实体类
 *
 * @author makejava
 * @since 2022-09-10 19:57:19
 */
@Data
public class Classify implements Serializable {

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

}

