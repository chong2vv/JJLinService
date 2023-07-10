package com.yd.JJLin.admin.model.vo;

import com.yd.JJLin.common.model.PagerRequestVO;
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
public class QuestionPagerRequestVO extends PagerRequestVO {

    private String search_str;

    private Integer status;

    @Override
    public void initPager() {
        super.initPager();
    }
}