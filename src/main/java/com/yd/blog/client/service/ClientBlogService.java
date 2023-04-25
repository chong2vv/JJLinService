package com.yd.blog.client.service;

import com.yd.blog.admin.model.dto.BlogDTO;
import com.yd.blog.admin.model.vo.BlogPagerRequestVO;

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
    int count(int status);

    /**
     * 获取首页推荐总数量
     *
     * @return 数量
     */
    int homeListCount();


    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    BlogDTO getBlogDetail(Long id);
}
