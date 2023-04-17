package com.yd.blog.admin.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * 热门标签(Tag)实体类
 *
 * @author makejava
 * @since 2023-04-17 12:56:13
 */
@Data
public class Tag implements Serializable {
    private static final long serialVersionUID = 603858388383390041L;
    
    private Integer id;
    /**
     * tag名
     */
    private String title;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 创建时间
     */
    @JsonIgnore
    private String createTime;


}

