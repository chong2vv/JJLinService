package com.yd.blog.client.service;

import com.yd.blog.admin.model.entity.Tag;
import com.yd.blog.admin.model.vo.TagPagerRequestVO;

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
