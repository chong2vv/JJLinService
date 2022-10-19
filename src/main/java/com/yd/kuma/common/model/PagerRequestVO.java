package com.yd.kuma.common.model;

import lombok.Data;

/**
 * 分页请求对象
 *
 * @author gongjiguang
 * @date 2022/9/2
 */
@Data
public class PagerRequestVO {

    private Integer page;
    private Integer count;

    public Integer getOffset() {
        return (page - 1) * count;
    }

    public void initPager() {
        if (page == null || page < 1) {
            page = 1;
        }
        if (count == null || count < 1) {
            count = 20;
        }
    }
}
