package com.yd.JJLin.client.service;

import com.yd.JJLin.admin.model.entity.Categories;
import com.yd.JJLin.admin.model.vo.CategoriesPagerRequestVO;

import java.util.List;

public interface ClientCategoriesService {

    /**
     * 获取分类列表
     *
     * @param categoriesPagerRequestVO 分页请求对象
     * @return 分类列表
     */
    List<Categories> getCategoriesList(CategoriesPagerRequestVO categoriesPagerRequestVO);

    /**
     * 获取分类列表
     *
     * @param categoriesPagerRequestVO 分页请求对象
     * @return 分类列表
     */
    List<Categories> getCategoriesHomeList(CategoriesPagerRequestVO categoriesPagerRequestVO);

    /**
     * 根据id获取分类信息
     *
     * @param id 主键id
     * @return 分类信息
     */
    Categories queryByCategoriesId(Integer id);
}
