package com.yd.kuma.client.service;

import com.yd.kuma.admin.model.dto.BlogDTO;
import com.yd.kuma.admin.model.vo.BlogPagerRequestVO;

import java.util.List;

/**
 * @author wangyuandong
 */

public interface ClientBlogService {

    /**
     * 获取blog列表
     *
     * @param blogPagerRequestVO blog请求信息
     * @return blog列表
     */
    List<BlogDTO> getBlogList(BlogPagerRequestVO blogPagerRequestVO);

    /**
     * 获取总数量
     *
     * @return 数量
     */
    int count();

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    BlogDTO getBlogDetail(Long id);
}
