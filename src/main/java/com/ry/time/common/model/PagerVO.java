package com.ry.time.common.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * 分页对象
 *
 * @author gongjiguang
 * @date 2022/9/2
 */
@Data
public class PagerVO<T> {

    @JsonProperty("total_count")
    private Integer totalCount;
    private List<T> items;
}
