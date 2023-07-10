package com.yd.JJLin.admin.model.vo;

import com.yd.JJLin.common.model.PagerRequestVO;
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
public class CategoriesPagerRequestVO extends PagerRequestVO {

    /**
     * 状态
     */
    private Integer status;

}
