package com.yd.blog.admin.controller;

import com.yd.blog.admin.model.entity.Tag;
import com.yd.blog.admin.model.vo.TagPagerRequestVO;
import com.yd.blog.admin.service.TagService;
import com.yd.blog.common.constant.enums.ResultErrorEnum;
import com.yd.blog.common.model.ResultGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 热门标签(Tag)表控制层
 *
 * @author makejava
 * @since 2023-04-17 12:56:12
 */
@RestController
@RequestMapping("/vue-admin-template/tag/")
@RequiredArgsConstructor
public class TagController {
    /**
     * 服务对象
     */
    @Resource
    private TagService tagService;


    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getTagList(TagPagerRequestVO tagPagerRequestVO) {
        List<Tag> tagList = tagService.queryByPage(tagPagerRequestVO);
        int count = tagService.count(tagPagerRequestVO.getStatus());
        if (tagList.isEmpty()) {
            return ResultGenerator.genSuccessPager(null, 0);
        }
        return ResultGenerator.genSuccessPager(tagList,count);
    }

    @RequestMapping(value = "/detail", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getDetail(@RequestParam Integer id) {
        Tag tag = tagService.queryById(id);
        return ResultGenerator.genSuccessResult(tag);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String update(@RequestBody Tag tag) {
        boolean existTag = tagService.existByTagId(tag.getId());
        if (!existTag) {
            return ResultGenerator.genErrorResult(ResultErrorEnum.TAG_EXISTS_ERROR);
        }
        tagService.update(tag);
        return ResultGenerator.genSuccessResult();
    }

    @RequestMapping(value = "/op", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String updateStatus(@RequestBody Tag tag) {
        boolean existTag = tagService.existByTagId(tag.getId());
        if (!existTag) {
            return ResultGenerator.genErrorResult(ResultErrorEnum.TAG_EXISTS_ERROR);
        }
        tagService.update(tag);
        return ResultGenerator.genSuccessResult();
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String create(@RequestBody Tag tag) {
        boolean existTag = tagService.existByTagTitle(tag.getTitle());
        if (existTag) {
            return ResultGenerator.genErrorResult(ResultErrorEnum.TAG_CREATE_EXISTS_ERROR);
        }
        Tag newTag = tagService.insert(tag);
        return ResultGenerator.genSuccessResult(newTag);
    }
}

