package com.ry.time.admin.model.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ry.time.common.model.PagerRequestVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 消息请求信息
 *
 * @author gongjiguang
 * @date 2022/9/2
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class GoodsPagerRequestVO extends PagerRequestVO {

    /**
     * 模糊搜索
     */
    @JsonProperty("search_str")
    private String searchString;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 分类id
     */
    @JsonProperty("classify_id")
    private Integer classifyId;
    /**
     * 是否首页推荐
     */
    @JsonProperty("is_home_list")
    private Integer isHomeList;

    public String getKeyword() {
        return "%" + searchString + "%";
    }
}
