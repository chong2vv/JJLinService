package com.ry.time.admin.model.vo;

import com.ry.time.common.model.PagerRequestVO;
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

    public void initPager() {
        super.initPager();
        if (status == null || status < -1) {
            status = 1;
        }
    }
}
