package com.yd.blog.admin.model.vo;

import com.yd.blog.common.model.PagerRequestVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 消息请求信息
 *
 * @author wangyuandong
 * @date 2022/9/2
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class MessagePagerRequestVO extends PagerRequestVO {

    private String search_str;

    private Integer status;

    private String uid;

    @Override
    public void initPager() {
        super.initPager();
    }
}
