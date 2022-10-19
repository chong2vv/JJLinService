package com.yd.kuma.admin.model.vo;

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

    private Integer status;

}
