package com.yd.blog.admin.model.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.yd.blog.common.model.PagerRequestVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * blog请求信息
 *
 * @author wangyuandong
 * @date 2022/9/2
 */
@Data
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class BlogPagerRequestVO extends PagerRequestVO {

    /**
     * 关键词
     */
    private String search_str;
    /**
     * tag标签搜索
     */
    private String search_tag;

    /**
     * 状态
     */
    private Integer status;
    /**
     * 分类id
     */
    @JsonProperty("classify_id")
    private Integer classifyId;


}
