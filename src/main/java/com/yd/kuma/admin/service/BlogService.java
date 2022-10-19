package com.yd.kuma.admin.service;

import com.yd.kuma.admin.model.dto.BlogDTO;
import com.yd.kuma.admin.model.vo.BlogPagerRequestVO;

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

    /**
     * 根据id判断文章是否存在
     *
     * @param id 主键id
     * @return true 存在 false 不存在
     */
    boolean existByBlogId(Long id);

    /**
     * 修改分类
     *
     * @param blogDto 文章信息
     * @return 1 成功
     */
    void update(BlogDTO blogDto);
}
