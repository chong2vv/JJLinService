package com.ry.time.admin.controller;

import com.ry.time.admin.model.entity.Blog;
import com.ry.time.admin.model.vo.BlogPagerRequestVO;
import com.ry.time.admin.service.BlogService;
import com.ry.time.common.constant.enums.ResultErrorEnum;
import com.ry.time.common.model.ResultGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * blog
 *
 * @author gongjiguang
 * @date 2022/9/19
 */
@RestController
@RequestMapping("/vue-admin-template/blog/")
@RequiredArgsConstructor
public class BlogController {

    private final BlogService blogService;

    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getClassifyList(BlogPagerRequestVO blogPagerRequestVO) {
        List<Blog> blogList = blogService.getBlogList(blogPagerRequestVO);
        int count = blogService.count();
        if (blogList.isEmpty()) {
            return ResultGenerator.genSuccessPager(null, 0);
        }
        return ResultGenerator.genSuccessPager(blogList,count);
    }

    @RequestMapping(value = "/detail", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getBlogDetail(@RequestParam Long id) {
        Blog blog = blogService.getBlogDetail(id);
        if (blog == null) {
            return ResultGenerator.genErrorResult(ResultErrorEnum.CLASSIFY_EXISTS_ERROR);
        }
        return ResultGenerator.genSuccessResult(blog);
    }


    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String createBlog(@RequestBody Blog blog) {
        return ResultGenerator.genSuccessResult(blogService.createBlog(blog));
    }

}
