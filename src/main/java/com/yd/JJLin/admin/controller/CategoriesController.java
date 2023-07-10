package com.yd.JJLin.admin.controller;

import com.yd.JJLin.admin.model.entity.Categories;
import com.yd.JJLin.admin.model.vo.CategoriesPagerRequestVO;
import com.yd.JJLin.admin.service.CategoriesService;
import com.yd.JJLin.common.constant.enums.ResultErrorEnum;
import com.yd.JJLin.common.model.ResultGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 文章分类(Categories)表控制层
 *
 * @author makejava
 * @since 2023-04-15 18:21:06
 */
@RestController
@RequestMapping("/vue-admin-template/categories/")
@RequiredArgsConstructor
public class CategoriesController {
    /**
     * 服务对象
     */
    @Resource
    private CategoriesService categoriesService;

    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getCategoriesList(CategoriesPagerRequestVO categoriesPagerRequestVO) {
        List<Categories> categoriesList = categoriesService.getCategoriesList(categoriesPagerRequestVO);
        int count = categoriesService.count(categoriesPagerRequestVO.getStatus());
        if (categoriesList.isEmpty()) {
            return ResultGenerator.genSuccessPager(null, 0);
        }
        return ResultGenerator.genSuccessPager(categoriesList,count);
    }

    @RequestMapping(value = "/detail", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getDetail(@RequestParam Integer id) {
        Categories categories = categoriesService.queryByCategoriesId(id);
        return ResultGenerator.genSuccessResult(categories);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String update(@RequestBody Categories categories) {
        boolean existCategories = categoriesService.existByCategoriesId(categories.getId());
        if (!existCategories) {
            return ResultGenerator.genErrorResult(ResultErrorEnum.CLASSIFY_EXISTS_ERROR);
        }
        categoriesService.update(categories);
        return ResultGenerator.genSuccessResult();
    }

    @RequestMapping(value = "/op", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String updateStatus(@RequestBody Categories categories) {
        boolean existCategories = categoriesService.existByCategoriesId(categories.getId());
        if (!existCategories) {
            return ResultGenerator.genErrorResult(ResultErrorEnum.CLASSIFY_EXISTS_ERROR);
        }
        categoriesService.updateStatus(categories);
        return ResultGenerator.genSuccessResult();
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String create(@RequestBody Categories categories) {
        Categories newCategories = categoriesService.create(categories);
        return ResultGenerator.genSuccessResult(newCategories);
    }

}

