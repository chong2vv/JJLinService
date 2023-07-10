package com.yd.JJLin.admin.model.vo;

import com.yd.JJLin.common.model.PagerRequestVO;
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
