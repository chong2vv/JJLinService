package com.ry.time.admin.service;

import com.ry.time.admin.model.dto.BlogDTO;
import com.ry.time.admin.model.vo.BlogPagerRequestVO;

import java.util.List;

/**
 * blog的业务接口
 *
 * @author gongjiguang
 * @date 2022/9/19
 */
public interface BlogService {

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

    /**
     * 创建新的blog对象
     *
     * @param blogDto blog对象
     * @return blog对象
     */
    BlogDTO createBlog(BlogDTO blogDto);
}
