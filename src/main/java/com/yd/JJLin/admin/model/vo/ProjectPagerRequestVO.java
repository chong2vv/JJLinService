package com.yd.JJLin.admin.model.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yd.JJLin.common.model.PagerRequestVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 消息请求信息
 *
 * @author wangyuandong
 * @date 2022/9/2
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ProjectPagerRequestVO extends PagerRequestVO {

    /**
     * 模糊搜索
     */
    @JsonProperty("search_str")
    private String searchString;
    /**
     * tag标签搜索
     */
    @JsonProperty("search_tag")
    private String searchTag;
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

    /**
     * 查询年
     */
    private Integer year;

    /**
     * 查询月份
     */
    private Integer month;

    public void setYear(Integer year) {
        if (year == null || year == 0) {
            return;
        }
        this.year = year;
    }

    public void setMonth(Integer month) {
        if (month == null || month ==0) {
            return;
        }
        this.month = month;
    }
}
