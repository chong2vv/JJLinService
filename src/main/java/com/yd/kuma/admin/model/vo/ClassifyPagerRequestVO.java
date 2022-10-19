package com.yd.kuma.admin.model.vo;

import com.yd.kuma.common.model.PagerRequestVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 分类请求信息
 *
 * @author gongjiguang
 * @date 2022/9/10
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ClassifyPagerRequestVO extends PagerRequestVO {

    /**
     * 状态
     */
    private Integer status;

}
