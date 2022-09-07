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
public class MessagePagerRequestVO extends PagerRequestVO {

    @JsonProperty("search_str")
    private String searchString;
}
