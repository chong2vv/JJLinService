package com.yd.JJLin.admin.service;

import com.yd.JJLin.admin.model.entity.Categories;
import com.yd.JJLin.admin.model.vo.CategoriesPagerRequestVO;

import java.util.List;

/**
 * 文章分类(Categories)表服务接口
 *
 * @author makejava
 * @since 2023-04-15 18:21:10
 */
public interface CategoriesService {

    /**
     * 获取分类列表
     *
     * @param categoriesPagerRequestVO 分页请求对象
     * @return 分类列表
     */
    List<Categories> getCategoriesList(CategoriesPagerRequestVO categoriesPagerRequestVO);

    /**
     * 获取分类总数
     *
     * @param status 分类状态
     * @return 分类总数
     */
    int count(Integer status);

    /**
     * 根据id获取分类信息
     *
     * @param id 主键id
     * @return 分类信息
     */
    Categories queryByCategoriesId(Integer id);

    /**
     * 根据id判断分类是否存在
     *
     * @param id 主键id
     * @return true 存在 false 不存在
     */
    boolean existByCategoriesId(Integer id);

    /**
     * 修改分类
     *
     * @param categories 分类信息
     */
    void update(Categories categories);

    /**
     * 修改分类状态
     *
     * @param categories 分类信息
     */
    void updateStatus(Categories categories);

    /**
     * 创建分类
     *
     * @param categories 分类信息
     * @return 分类信息
     */
    Categories create(Categories categories);

}
