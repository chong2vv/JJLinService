package com.yd.blog.admin.dao;

import com.yd.blog.admin.model.entity.Tag;
import com.yd.blog.admin.model.vo.TagPagerRequestVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 热门标签(Tag)表数据库访问层
 *
 * @author makejava
 * @since 2023-04-17 12:56:13
 */
@Mapper
public interface TagDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Tag queryById(Integer id);


    /**
     * 通过Title查询单条数据
     *
     * @param title tag名
     * @return 实例对象
     */
    Tag queryByTitle(String title);

    /**
     * 查询指定行数据
     *
     * @param requestVO         分页对象
     * @return 对象列表
     */
    List<Tag> queryAllByLimit(TagPagerRequestVO requestVO);

    /**
     * 统计总行数
     *
     * @param status 查询条件
     * @return 总行数
     */
    int count(Integer status);

    /**
     * 新增数据
     *
     * @param tag 实例对象
     * @return 影响行数
     */
    int insert(Tag tag);

    /**
     * 修改数据
     *
     * @param tag 实例对象
     * @return 影响行数
     */
    int update(Tag tag);


}

