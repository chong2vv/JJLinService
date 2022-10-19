package com.yd.kuma.admin.controller;

import com.yd.kuma.admin.model.dto.BlogDTO;
import com.yd.kuma.admin.model.vo.BlogPagerRequestVO;
import com.yd.kuma.admin.service.BlogService;
import com.yd.kuma.common.constant.enums.ResultErrorEnum;
import com.yd.kuma.common.model.ResultGenerator;
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
        List<BlogDTO> blogList = blogService.getBlogList(blogPagerRequestVO);
        int count = blogService.count();
        if (blogList.isEmpty()) {
            return ResultGenerator.genSuccessPager(null, 0);
        }
        return ResultGenerator.genSuccessPager(blogList,count);
    }

    @RequestMapping(value = "/detail", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getBlogDetail(@RequestParam Long id) {
        BlogDTO blog = blogService.getBlogDetail(id);
        if (blog == null) {
            return ResultGenerator.genErrorResult(ResultErrorEnum.BLOG_EXISTS_ERROR);
        }
        return ResultGenerator.genSuccessResult(blog);
    }


    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String createBlog(@RequestBody BlogDTO blog) {
        return ResultGenerator.genSuccessResult(blogService.createBlog(blog));
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String updateBlog(@RequestBody BlogDTO blog) {
        Boolean isBlog = blogService.existByBlogId(blog.getId());
        if (!isBlog) {
            return ResultGenerator.genErrorResult(ResultErrorEnum.BLOG_EXISTS_ERROR);
        }
        blogService.update(blog);
        return ResultGenerator.genSuccessResult();
    }

}
