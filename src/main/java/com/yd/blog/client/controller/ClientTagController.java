package com.yd.blog.client.controller;

import com.yd.blog.admin.model.entity.Tag;
import com.yd.blog.admin.model.vo.TagPagerRequestVO;
import com.yd.blog.client.service.ClientTagService;
import com.yd.blog.common.model.ResultGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/client/tag/")
@RequiredArgsConstructor
public class ClientTagController {
    private final ClientTagService tagService;

    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getCategoriesList(TagPagerRequestVO tagPagerRequestVO) {
        List<Tag> tagList = tagService.queryByPage(tagPagerRequestVO);
        if (tagList.isEmpty()) {
            return ResultGenerator.genSuccessResult(null);
        }
        return ResultGenerator.genSuccessResult(tagList);
    }
}