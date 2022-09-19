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

    private String search_str;

    private Integer status;

    @Override
    public void initPager() {
        super.initPager();
    }
}
