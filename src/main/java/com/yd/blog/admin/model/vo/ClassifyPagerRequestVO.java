package com.yd.blog.admin.model.vo;

import com.yd.blog.common.model.PagerRequestVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 分类请求信息
 *
 * @author wangyuandong
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
