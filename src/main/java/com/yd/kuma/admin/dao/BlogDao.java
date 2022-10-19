package com.yd.kuma.admin.dao;

import com.yd.kuma.admin.model.entity.Blog;
import com.yd.kuma.admin.model.vo.BlogPagerRequestVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 博客(Blog)表数据库访问层
 *
 * @author makejava
 * @since 2022-09-19 11:57:24
 */
@Mapper
public interface BlogDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Blog queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param blogPagerRequestVO 查询条件
     * @return 对象列表
     */
    List<Blog> queryPager(BlogPagerRequestVO blogPagerRequestVO);

    /**
     * 统计总行数
     *
     * @return 总行数
     */
    int count();

    /**
     * 新增数据
     *
     * @param blog 实例对象
     * @return 影响行数
     */
    int insert(Blog blog);

    /**
     * 更新数据
     *
     * @param blog 实例对象
     * @return 影响行数
     */
    int update(Blog blog);
}

