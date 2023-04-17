package com.yd.blog.admin.service;

import com.yd.blog.admin.model.entity.Tag;
import com.yd.blog.admin.model.vo.TagPagerRequestVO;

import java.util.List;

/**
 * 热门标签(Tag)表服务接口
 *
 * @author makejava
 * @since 2023-04-17 12:56:16
 */
public interface TagService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Tag queryById(Integer id);

    /**
     * 通过title查询单条数据
     *
     * @param title 主键
     * @return 实例对象
     */
    boolean existByTagTitle(String title);

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 是否存在
     */
    boolean existByTagId(Integer id);

    /**
     * 分页查询
     *
     * @param tagPagerRequestVO      分页对象
     * @return 查询结果
     */
    List<Tag> queryByPage(TagPagerRequestVO tagPagerRequestVO);

    /**
     * 新增数据
     *
     * @param tag 实例对象
     * @return 实例对象
     */
    Tag insert(Tag tag);

    /**
     * 修改数据
     *
     * @param tag 实例对象
     * @return 实例对象
     */
    Tag update(Tag tag);

    /**
     * 获取分类总数
     *
     * @param status 分类状态
     * @return 分类总数
     */
    int count(Integer status);


}
