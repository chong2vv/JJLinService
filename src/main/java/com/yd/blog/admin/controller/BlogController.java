package com.yd.blog.admin.controller;

import com.yd.blog.admin.model.dto.BlogDTO;
import com.yd.blog.admin.model.vo.BlogPagerRequestVO;
import com.yd.blog.admin.service.BlogService;
import com.yd.blog.common.constant.enums.ResultErrorEnum;
import com.yd.blog.common.model.ResultGenerator;
import com.yd.blog.common.util.JsonUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * blog
 *
 * @author wangyuandong
 * @date 2022/9/19
 */
@RestController
@RequestMapping("/vue-admin-template/blog/")
@RequiredArgsConstructor
public class BlogController {

    private final BlogService blogService;

    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getBlogList(@RequestParam Map<String, Object> map) {
        BlogPagerRequestVO blogPagerRequestVO = JsonUtil.mapToObj(map, BlogPagerRequestVO.class);
        List<BlogDTO> blogList = blogService.getBlogList(blogPagerRequestVO);
        int count = blogService.count(blogPagerRequestVO.getStatus());
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
        boolean isBlog = blogService.existByBlogId(blog.getId());
        if (!isBlog) {
            return ResultGenerator.genErrorResult(ResultErrorEnum.BLOG_EXISTS_ERROR);
        }
        blogService.update(blog);
        return ResultGenerator.genSuccessResult();
    }

    @RequestMapping(value = "/update-status", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String updateBlogStatus(@RequestBody BlogDTO blog) {
        boolean isBlog = blogService.existByBlogId(blog.getId());
        if (!isBlog) {
            return ResultGenerator.genErrorResult(ResultErrorEnum.BLOG_EXISTS_ERROR);
        }
        blogService.updateStatus(blog);
        return ResultGenerator.genSuccessResult();
    }

//    @RequestMapping(value = "/hot-tags", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//    public String getHotTags(@RequestParam Map<String, Object> map) {
//        List<String> tagList = Collections.singletonList("");
//        return ResultGenerator.genSuccessPager(tagList,tagList.size());
//    }

}
