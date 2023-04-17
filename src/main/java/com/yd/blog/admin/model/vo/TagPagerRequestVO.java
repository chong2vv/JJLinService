package com.yd.blog.admin.model.vo;

import com.yd.blog.common.model.PagerRequestVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class TagPagerRequestVO extends PagerRequestVO {

    /**
     * 状态
     */
    private Integer status;

}
