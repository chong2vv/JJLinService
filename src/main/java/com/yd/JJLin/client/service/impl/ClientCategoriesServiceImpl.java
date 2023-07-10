package com.yd.JJLin.client.service.impl;


import com.yd.JJLin.admin.dao.CategoriesDao;
import com.yd.JJLin.admin.model.entity.Categories;
import com.yd.JJLin.admin.model.vo.CategoriesPagerRequestVO;
import com.yd.JJLin.client.service.ClientCategoriesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientCategoriesServiceImpl implements ClientCategoriesService {

    private final CategoriesDao categoriesDao;

    /**
     * @param categoriesPagerRequestVO 分页请求对象
     * @return List<Categories>
     */
    @Override
    public List<Categories> getCategoriesList(CategoriesPagerRequestVO categoriesPagerRequestVO) {
        categoriesPagerRequestVO.initPager();
        categoriesPagerRequestVO.setStatus(1);
        return categoriesDao.queryPager(categoriesPagerRequestVO);
    }

    /**
     * @param categoriesPagerRequestVO 分页请求对象
     * @return List
     */
    @Override
    public List<Categories> getCategoriesHomeList(CategoriesPagerRequestVO categoriesPagerRequestVO) {
        categoriesPagerRequestVO.initPager();
        categoriesPagerRequestVO.setStatus(1);
        return categoriesDao.getCategoryCounts(categoriesPagerRequestVO);
    }

    /**
     * @param id 主键id
     * @return Categories
     */
    @Override
    public Categories queryByCategoriesId(Integer id) {
        return categoriesDao.queryById(id);
    }
}
