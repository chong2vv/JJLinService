package com.yd.JJLin.client.controller;

import com.yd.JJLin.admin.model.entity.Categories;
import com.yd.JJLin.admin.model.vo.CategoriesPagerRequestVO;
import com.yd.JJLin.client.service.ClientCategoriesService;
import com.yd.JJLin.common.model.ResultGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/client/categories/")
@RequiredArgsConstructor
public class ClientCategoriesController {
    private final ClientCategoriesService categoriesService;

    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getCategoriesList(CategoriesPagerRequestVO categoriesPagerRequestVO) {
        List<Categories> categoriesList = categoriesService.getCategoriesList(categoriesPagerRequestVO);
        if (categoriesList.isEmpty()) {
            return ResultGenerator.genSuccessResult(null);
        }
        return ResultGenerator.genSuccessResult(categoriesList);
    }

    @RequestMapping(value = "/home_list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getHomeCategoriesList(CategoriesPagerRequestVO categoriesPagerRequestVO) {
        List<Categories> categoriesList = categoriesService.getCategoriesHomeList(categoriesPagerRequestVO);
        if (categoriesList.isEmpty()) {
            return ResultGenerator.genSuccessResult(null);
        }
        return ResultGenerator.genSuccessResult(categoriesList);
    }
}
