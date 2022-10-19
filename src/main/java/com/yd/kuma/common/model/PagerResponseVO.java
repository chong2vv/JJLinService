package com.yd.kuma.common.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 分页对象
 *
 * @author gongjiguang
 * @date 2022/9/2
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class PagerResponseVO<T> extends Result<T> {

    @JsonProperty("total_count")
    private Integer totalCount;

}
