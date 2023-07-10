package com.yd.JJLin.client.service;

import com.yd.JJLin.admin.model.entity.Tag;
import com.yd.JJLin.admin.model.vo.TagPagerRequestVO;

import java.util.List;

public interface ClientTagService {

    /**
     * 分页查询
     *
     * @param tagPagerRequestVO      分页对象
     * @return 查询结果
     */
    List<Tag> queryByPage(TagPagerRequestVO tagPagerRequestVO);
}
