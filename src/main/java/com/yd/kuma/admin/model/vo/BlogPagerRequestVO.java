package com.yd.kuma.admin.model.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yd.kuma.common.model.PagerRequestVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * blog请求信息
 *
 * @author gongjiguang
 * @date 2022/9/2
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BlogPagerRequestVO extends PagerRequestVO {

    private String search_str;
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
