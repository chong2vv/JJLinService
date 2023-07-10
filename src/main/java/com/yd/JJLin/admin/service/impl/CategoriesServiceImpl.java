package com.yd.JJLin.admin.service.impl;

import com.yd.JJLin.admin.dao.CategoriesDao;
import com.yd.JJLin.admin.model.entity.Categories;
import com.yd.JJLin.admin.model.vo.CategoriesPagerRequestVO;
import com.yd.JJLin.admin.service.CategoriesService;
import com.yd.JJLin.common.util.DateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.util.List;

/**
 * 文章分类(Categories)表服务实现类
 *
 * @author makejava
 * @since 2023-04-15 18:21:10
 */
@Service
@RequiredArgsConstructor
public class CategoriesServiceImpl implements CategoriesService {
    @Resource
    private CategoriesDao categoriesDao;

    @Override
    public List<Categories> getCategoriesList(CategoriesPagerRequestVO categoriesPagerRequestVO) {
        categoriesPagerRequestVO.initPager();
        return categoriesDao.queryPager(categoriesPagerRequestVO);
    }

    @Override
    public int count(Integer status) {
        return categoriesDao.count(status);
    }

    @Override
    public Categories queryByCategoriesId(Integer id) {
        return categoriesDao.queryById(id);
    }

    @Override
    public boolean existByCategoriesId(Integer id) {
        Categories categories = categoriesDao.queryById(id);
        return categories != null;
    }

    @Override
    public void update(Categories categories) {
        categoriesDao.update(categories);
    }

    @Override
    public void updateStatus(Categories categories) {
        categoriesDao.updateStatus(categories);
    }

    @Override
    public Categories create(Categories categories) {
        categories.setCreateTime(DateUtil.getCurrentDateTimeStr());
        categoriesDao.insert(categories);
        return categories;
    }
}
